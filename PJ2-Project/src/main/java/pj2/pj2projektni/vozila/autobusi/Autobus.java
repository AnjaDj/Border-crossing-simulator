package pj2.pj2projektni.vozila.autobusi;
import pj2.pj2projektni.putnici.*;
import pj2.pj2projektni.simulacija.Simulacija;
import pj2.pj2projektni.vozila.*;
import pj2.pj2projektni.incident.*;
import java.io.*;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Autobus extends Vozilo implements Serializable{

    public Autobus(List<Putnik> putniciUVozilu, Consumer<Integer> ukloniVoziloSaPolja, BiConsumer<Vozilo, Integer> pomjeriVoziloNaPolje,
                   BiConsumer<Vozilo, Integer> pomjeriVoziloNaPoljeOstatak, Consumer<Integer> ukloniVoziloSaPoljaOstatak) throws EmptyCollectionException {
        super(ukloniVoziloSaPolja, pomjeriVoziloNaPolje, pomjeriVoziloNaPoljeOstatak, ukloniVoziloSaPoljaOstatak);

        if (putniciUVozilu.size() == 0)
            throw new EmptyCollectionException("Ne mozete kreirati vozilo bez i jednog putnika!");

        if (putniciUVozilu.size() > 52)
            super.putniciUVozilu = putniciUVozilu.subList(0,52);
        else
            super.putniciUVozilu = putniciUVozilu;

        vozac = super.putniciUVozilu.get(0);
    }

    @Override
    public String getOznaka() {
        return "A";
    }

    @Override
    public String getBoja() {
        return "#00B0F0";
    }

    private static Object CT_evidencija = new Object();
    private static ObjectOutputStream oos;
    static{
        try{
            oos = new ObjectOutputStream(new FileOutputStream(new File("CT_S_kaznjeni_putnici.txt"), true));
        }catch (IOException e){
            Logger.getLogger(Simulacija.class.getName()).log(Level.SEVERE, e.fillInStackTrace().toString());
        }
    }

    @Override
    public void carinskaKontrola(){
        List<Putnik> putniciZaIzbacivanje = new ArrayList<>();

        for(Iterator<Putnik> iterator = putniciUVozilu.iterator(); iterator.hasNext(); ){

                    Putnik putnik = iterator.next(); // putnik ciji se kofer trenutno provjerava

                    if (putnik.imaKofer() && putnik.koferSadrziNedozvoljeneStvari()) {

                            synchronized (CT_evidencija) {
                                try {
                                    oos.writeObject(putnik);
                                } catch (IOException e) {
                                    Logger.getLogger(Simulacija.class.getName()).log(Level.SEVERE, e.fillInStackTrace().toString());
                                }
                                try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File("CT_T_kaznjeni_putnici.txt"), true)))) {
                                    pw.println(putnik + " je sa sobom imao kofer koji sadrzi nedozvoljene stvari. Putnik se izbacuje" +
                                            " iz autobusa i ne moze da predje granicu.");
                                } catch (IOException e) {
                                    Logger.getLogger(Simulacija.class.getName()).log(Level.SEVERE, e.fillInStackTrace().toString());
                                }
                            }

                            putniciZaIzbacivanje.add(putnik);
                    }
                    try {
                        sleep(getVrijemeProcesiranjaPoPutniku());
                    } catch (InterruptedException e) {

                    }
        }
        if ( putniciZaIzbacivanje.isEmpty() == false ){

            String dodatniOpis= "Vozilo "+this+" je imalo incident na carisnkom terminalu."+putniciZaIzbacivanje.size()+" putnika su imali nedozvoljene"+
                                "stvari u koferu.Takvi putnici se izbacuju iz vozila, a vozilo nastavlja dalje svoj put.";
            Incident incident = new Incident(this,putniciZaIzbacivanje,dodatniOpis);
                incident.evidentiranjeIncidenta();
            putniciUVozilu.removeAll(putniciZaIzbacivanje);
        }
        System.out.println(this+" PRELAZI GRANICU!");
    }
    @Override
    public long getVrijemeProcesiranjaPoPutniku() {return 100;}
}
