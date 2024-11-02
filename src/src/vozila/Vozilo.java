package pj2.pj2projektni.vozila;
import pj2.pj2projektni.mapa.*;
import pj2.pj2projektni.putnici.Putnik;
import pj2.pj2projektni.simulacija.Simulacija;
import pj2.pj2projektni.terminali.Kontrola;
import pj2.pj2projektni.terminali.PolicijskiTerminal;
import pj2.pj2projektni.incident.*;
import java.io.*;
import java.util.*;
import java.util.function.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Vozilo extends Thread implements Serializable{   // staza[i] = new Polje();

    public transient Object krajProcesiranja = new Object();  // kraj kretanja vozila
    protected List<Putnik> putniciUVozilu;
    protected Putnik vozac;

    private transient Consumer<Integer> ukloniVoziloSaPolja;
    private transient BiConsumer<Vozilo, Integer> pomjeriVoziloNaPolje;
    private transient BiConsumer<Vozilo, Integer> pomjeriVoziloNaPoljeOstatak;
    private transient Consumer<Integer> ukloniVoziloSaPoljaOstatak;
    private static final int VRIJEME_PAUZE = 1000;

    private static int COUNTER = 0;
    private static Object PT_evidencija = new Object();
    private static ObjectOutputStream oos;
    static {
        try{
            oos = new ObjectOutputStream(new FileOutputStream(new File("PT_S_kaznjena_vozila.txt"), true));
        }catch (IOException e){
            Logger.getLogger(Simulacija.class.getName()).log(Level.SEVERE, e.fillInStackTrace().toString());
        }
    }
    private int id = 0;
    public Vozilo(Consumer<Integer> ukloniVoziloSaPolja, BiConsumer<Vozilo, Integer> pomjeriVoziloNaPolje,
                  BiConsumer<Vozilo, Integer> pomjeriVoziloNaPoljeOstatak, Consumer<Integer> ukloniVoziloSaPoljaOstatak) {
        COUNTER++;
        id = COUNTER;
        this.pomjeriVoziloNaPolje = pomjeriVoziloNaPolje;
        this.ukloniVoziloSaPolja = ukloniVoziloSaPolja;
        this.pomjeriVoziloNaPoljeOstatak = pomjeriVoziloNaPoljeOstatak;
        this.ukloniVoziloSaPoljaOstatak = ukloniVoziloSaPoljaOstatak;
    }

    public abstract String getOznaka();

    public abstract String getBoja();

    public int getID() {
        return id;
    }

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
                Logger.getLogger(Simulacija.class.getName()).log(Level.SEVERE, e.fillInStackTrace().toString());
            }
            try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File("PT_T_kaznjeni_putnici.txt"),true)))){
                pw.println(putnik+" je imao neispravne dokumente te s toga ne moze da predje granicu.");
            }catch(IOException e){
                Logger.getLogger(Simulacija.class.getName()).log(Level.SEVERE, e.fillInStackTrace().toString());
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
                Thread.currentThread().interrupt();
                return 1;
            }

            int temp = procesirajPutnika(putniciUVozilu.get(i));
            if(temp == -1) putniciZaIzbacivanje.add(putniciUVozilu.get(i));
        }

        // sada su svi putnici sa neispravnim pasosima serijalizovani, uvedeni u tekstualnu evideciju i dodani u kolekciju putniciZaIzbacivanje
        if (!putniciZaIzbacivanje.isEmpty()) {
            if (putniciZaIzbacivanje.contains(vozac)) { // i sam vozac je u kolekciji putniciZaIzbacivanje tj i on ima nevazeci pasos

                synchronized (PT_evidencija) {
                    try {
                        oos.writeObject(this);
                    } catch (IOException e) {
                        Logger.getLogger(Simulacija.class.getName()).log(Level.SEVERE, e.fillInStackTrace().toString());
                    }
                    try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File("PT_T_kaznjena_vozila.txt"), true)))) {
                        pw.println(this + " Vozac ovog vozila ima neispravna dokumenta. Vozilo se uklanja iz saobracaja");
                    } catch (IOException e) {
                        Logger.getLogger(Simulacija.class.getName()).log(Level.SEVERE, e.fillInStackTrace().toString());
                    }
                }

                String dodatniOpis = "Vozilo " + this + " je imalo incident na policijskom terminalu.Vozaceva dokumenta su neispravna te se vozilo izbacuje" +
                        " iz saobracaja.";
                Incident incident = new Incident(this, putniciZaIzbacivanje, dodatniOpis);
                incident.evidentiranjeIncidenta();
                return -1; // vozilo se uklanja iz saobracaja

            } else {  // vozacev pasos je uredan, vozilo moze da nastavi dalje kretanje ali se prvo moraju izbaciti svi putnici sa nev. pasosem iz njega

                String dodatniOpis = "Vozilo " + this + " je imalo incident na policijskom terminalu.Vozaceva dokumenta su ispravna te vozilo moze da" +
                        " nastavi dalje svoje kretanje, ali se pre toga iz vozila izbacuju svi putnici sa nevazecim dokumentima. Broj takvih putnika = "+
                        putniciZaIzbacivanje.size();
                Incident incident = new Incident(this, putniciZaIzbacivanje, dodatniOpis);
                incident.evidentiranjeIncidenta();

                putniciUVozilu.removeAll(putniciZaIzbacivanje);
                return 1;   // nastavi dalje kretanje
            }
        }
        return 1;   // nastavi dalje kretanje jer vozilo nije imalo incidenata
    }
    private void provjeraKontrole(){
        if (Kontrola.PAUZA){
            synchronized (Kontrola.LOCK){
                try {
                    Kontrola.LOCK.wait();
                    //System.out.println("nastavljam " + getID());
                } catch (InterruptedException e) {
                    //Logger.getLogger(Simulacija.class.getName()).log(Level.SEVERE, e.fillInStackTrace().toString());
                }
            }
        }
    }
    @Override
    public void run() {
        for(int i = 0; i < Mapa.VELICINA_STAZE; i++){

            if (Kontrola.KRAJ == true) return;
            provjeraKontrole();

            Polje zeljenoPolje = Mapa.STAZA[i];
            try {
                Thread.sleep(VRIJEME_PAUZE);
            } catch (InterruptedException e) {
                //Logger.getLogger(Simulacija.class.getName()).log(Level.SEVERE, e.fillInStackTrace().toString());
            }
                synchronized (zeljenoPolje) {

                    if (zeljenoPolje.getNaPolju() != null)      // na polju se vec nalazi neko vozilo
                            try {
                                zeljenoPolje.wait();            // cekaj na oslobodjenje tog polja
                            } catch (InterruptedException e) {
                               // Logger.getLogger(Simulacija.class.getName()).log(Level.SEVERE, e.fillInStackTrace().toString());
                                Thread.currentThread().interrupt();
                                return;
                            }
                    zeljenoPolje.setNaPolju(this);
                    if (Mapa.VELICINA_STAZE - i - 1 < 5)
                        pomjeriVoziloNaPolje.accept(this, Mapa.VELICINA_STAZE - 1 - i);
                    else
                        pomjeriVoziloNaPoljeOstatak.accept(this, Mapa.VELICINA_STAZE - 1 - i - 5);
                    if (i > 0) {
                        synchronized (Mapa.STAZA[i - 1]) {
                            Mapa.STAZA[i - 1].setNaPolju(null);
                            if (Mapa.VELICINA_STAZE - i < 5)
                                ukloniVoziloSaPolja.accept(Mapa.VELICINA_STAZE - i);
                            else
                                ukloniVoziloSaPoljaOstatak.accept(Mapa.VELICINA_STAZE - i - 5);
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
                Thread.currentThread().interrupt();
                return;
            }
        }
        System.out.println("Vozilo "+this+" je zavrsilo sa svojim kretanjem.");
    }

    @Override
    public String toString(){
        return "Vozilo_"+id;
    }

    public String opisVozila() { return "Vozilo_" + id + " - " + getClass().getSimpleName() + ". Broj putnika: " + putniciUVozilu.size() + ".";}
}
