package vozila.autobusi;
import putnici.*;
import vozila.*;

import java.io.*;
import java.util.*;


public class Autobus extends Vozilo implements java.io.Serializable{

    public Autobus(List<Putnik> putniciUVozilu) throws EmptyCollectionException {
        if (putniciUVozilu.size() == 0)
            throw new EmptyCollectionException("Ne mozete kreirati vozilo bez i jednog putnika!");

        if (putniciUVozilu.size() > 52)
            super.putniciUVozilu = putniciUVozilu.subList(0,52);
        else
            super.putniciUVozilu = putniciUVozilu;

        vozac = super.putniciUVozilu.get(0);
    }

    @Override
    public void carinskaKontrola(){
        List<Putnik> putniciZaIzbacivanje = new ArrayList<>();

        for(Iterator<Putnik> iterator = putniciUVozilu.iterator(); iterator.hasNext(); ){

                    Putnik putnik = iterator.next(); // putnik ciji se kofer trenutno provjerava

                    if (putnik.imaKofer() && putnik.koferSadrziNedozvoljeneStvari()) {

                            try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("CT_S_kaznjeni_putnici.txt"),true))){
                                oos.writeObject(putnik);
                            }catch(IOException e){
                                e.printStackTrace();
                            }
                            try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File("CT_T_kaznjeni_putnici.txt"),true)))){
                                pw.println(putnik+" je sa sobom imao kofer koji sadrzi nedozvoljene stvari. Putnik se izbacuje" +
                                                  " iz autobusa i ne moze da predje granicu.");
                            }catch (IOException e){
                                e.printStackTrace();
                            }

                            putniciZaIzbacivanje.add(putnik);
                    }

                    try {
                        sleep(getVrijemeProcesiranjaPoPutniku());
                    } catch (InterruptedException e) {e.printStackTrace();}
        }
        putniciUVozilu.removeAll(putniciZaIzbacivanje);
        System.out.println(this+" PRELAZI GRANICU!");
    }
    @Override
    public long getVrijemeProcesiranjaPoPutniku() {return 100;}
}
