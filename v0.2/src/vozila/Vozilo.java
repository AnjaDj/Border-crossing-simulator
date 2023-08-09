package vozila;
import mapa.*;
import putnici.Putnik;
import terminali.PolicijskiTerminal;
import vozila.autobusi.Autobus;
import java.io.*;
import java.util.*;


public abstract class Vozilo extends Thread implements java.io.Serializable{   // staza[i] = new Polje();

    public transient Object krajProcesiranja = new Object();  // kraj kretanja vozila
    protected List<Putnik> putniciUVozilu;
    protected Putnik vozac;

    public Putnik getVozac(){return vozac;}
    public List<Putnik> getPutniciUVozilu(){return putniciUVozilu;}

    protected void notifikujPolicijskiTerminal() {
        synchronized (PolicijskiTerminal.PT_NOTIFY_CAR) {
            PolicijskiTerminal.PT_NOTIFY_CAR.notify();
        }
    }
    private int procesirajPutnika(Putnik putnik){

        if (putnik.pasosJeNeispravan()) { // putnik ima neispravan pasos

            try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("PT_S_kaznjeni_putnici.txt"),true))){
                oos.writeObject(putnik);
            }catch(IOException e){
                e.printStackTrace();
            }
            try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File("PT_T_kaznjeni_putnici.txt"),true)))){
                pw.println(putnik+" je imao neispravne dokumente te s toga ne moze da predje granicu.");
            }catch(IOException e){
                e.printStackTrace();
            }
            return -1;
        }

        return 1;
    }
    public abstract void carinskaKontrola();
    public long getVrijemeProcesiranjaPoPutniku(){ return 500; }
    public int policijskaKontrola(){

        List<Putnik> putniciZaIzbacivanje = new ArrayList<>();

        for(int i = 0; i < putniciUVozilu.size(); i++) {

            try {
                sleep(getVrijemeProcesiranjaPoPutniku());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            int temp = procesirajPutnika(putniciUVozilu.get(i));
            if(temp == -1) putniciZaIzbacivanje.add(putniciUVozilu.get(i));
        }

        // sada su svi putnici sa neispravnim pasosima serijalizovani, uvedeni u tekstualnu evideciju i dodani u kolekciju putniciZaIzbacivanje

        if (putniciZaIzbacivanje.contains(vozac)){ //potencijalno se moze desiti da je i sam vozac u kolekciji putniciZaIzbacivanje

            try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("PT_S_kaznjena_vozila.txt"),true))){
                oos.writeObject(this);
            }catch(IOException e){
                e.printStackTrace();
            }
            try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File("PT_T_kaznjena_vozila.txt"),true)))){
                pw.println(this+" Vozac ovog vozila ima neispravna dokumenta. Vozilo se uklanja iz saobracaja");
            }catch (IOException e){
                e.printStackTrace();
            }
            return -1; // vozilo se uklanja iz saobracaja

        }else{
            putniciUVozilu.removeAll(putniciZaIzbacivanje);
            return 1;   // nastavi dalje kretanje
        }

    }

    @Override
    public void run() {

        for(int i = 0; i < Mapa.VELICINA_STAZE; i++){

                Polje zeljenoPolje = Mapa.STAZA[i];

                synchronized (zeljenoPolje) {

                    if (zeljenoPolje.getNaPolju() != null)      // na polju se vec nalazi neko vozilo
                            try {
                                zeljenoPolje.wait();            // cekaj na oslobodjenje tog polja
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                    zeljenoPolje.setNaPolju(this);
                    if (i > 0) {
                        synchronized (Mapa.STAZA[i - 1]) {
                            Mapa.STAZA[i - 1].setNaPolju(null);
                            Mapa.STAZA[i - 1].notify();
                        }
                    }
                }
        }

        System.out.println("Vozilo "+this+" je doslo na kraj staze.Ceka na PT...");
        notifikujPolicijskiTerminal();               // obavestava se PT da je stiglo vozilo na kraj staze.
        synchronized (krajProcesiranja) {
            try {
                krajProcesiranja.wait();            // ceka se na obavestenje da je dosao kraj kretanja vozila
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Vozilo "+this+" je zavrsilo sa svojim kretanjem.");
    }

}
