package pj2.pj2projektni.vozila.kamioni;
import pj2.pj2projektni.putnici.*;
import pj2.pj2projektni.simulacija.Simulacija;
import pj2.pj2projektni.terminali.*;
import pj2.pj2projektni.vozila.*;
import pj2.pj2projektni.incident.*;
import java.io.*;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Kamion extends Vozilo implements Serializable{

    protected static int BROJ_KAMIONA = 0, BROJ_KAMIONA_SA_PRETOVAROM = 0;
    private Teret teret;

    public Kamion(List<Putnik> putniciUVozilu, Consumer<Integer> ukloniVoziloSaPolja, BiConsumer<Vozilo, Integer> pomjeriVoziloNaPolje,
                  BiConsumer<Vozilo, Integer> pomjeriVoziloNaPoljeOstatak, Consumer<Integer> ukloniVoziloSaPoljaOstatak) throws EmptyCollectionException {
        super(ukloniVoziloSaPolja, pomjeriVoziloNaPolje, pomjeriVoziloNaPoljeOstatak, ukloniVoziloSaPoljaOstatak);

        BROJ_KAMIONA++;
        if (putniciUVozilu.size() == 0)
            throw new EmptyCollectionException("Ne mozete kreirati vozilo bez i jednog putnika!");
        if (putniciUVozilu.size() > 3)
            super.putniciUVozilu = putniciUVozilu.subList(0,3);
        else
            super.putniciUVozilu = putniciUVozilu;

        vozac = super.putniciUVozilu.get(0);
        teret = new Teret();
    }
    public boolean imaCarinskuDokumentacijuZaTeret(){
        return teret.imaCarinskuDokumentaciju();
    }
    public boolean imaUrednuMasu(){

        return teret.imaUrednuMasu();
    }

    @Override
    public String getOznaka() {
        return "K";
    }

    @Override
    public String getBoja() {
        return "#002060";
    }

    @Override
    protected void notifikujPolicijskiTerminal() {
        synchronized (PolicijskiTerminal.PT_NOTIFY_TRUCK) {
            PolicijskiTerminal.PT_NOTIFY_TRUCK.notify();
        }
    }

    private static Object CT_evidencija = new Object();
    private static ObjectOutputStream oos;
    static{
        try{
            oos = new ObjectOutputStream(new FileOutputStream(new File("CT_S_kaznjena_vozila.txt"),true));
        }catch (IOException e){
            Logger.getLogger(Simulacija.class.getName()).log(Level.SEVERE, e.fillInStackTrace().toString());
        }
    }
    private static final long SLEEP_TIME = 500;
    @Override
    public void carinskaKontrola(){

        try {
            Thread.sleep(SLEEP_TIME);
        } catch (InterruptedException e) {
           // throw new RuntimeException(e);
        }
        if (imaUrednuMasu() == false) {

            synchronized (CT_evidencija) {
                try {
                    oos.writeObject(this);
                } catch (IOException e) {
                    Logger.getLogger(Simulacija.class.getName()).log(Level.SEVERE, e.fillInStackTrace().toString());
                }
                try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File("CT_T_kaznjena_vozila.txt"), true)))) {
                    pw.println(this + " je bio preopterecen te ne moze preci granicu.");
                } catch (IOException e) {
                    Logger.getLogger(Simulacija.class.getName()).log(Level.SEVERE, e.fillInStackTrace().toString());
                }
            }

            String dodatniOpis= "Vozilo "+this+" je imalo incident na carinskom terminalu. Vozilo je bilo preoptereceno te se iskljucuje iz saobracaja";
            Incident incident = new Incident(this,null,dodatniOpis);
                incident.evidentiranjeIncidenta();
            System.out.println(this+" NE PRELAZI GRANICU!");

        }else
            System.out.println(this+" PRELAZI GRANICU!");
    }

    @Override
    public String opisVozila() {
        return super.opisVozila() + " Deklarisana masa tereta: " + teret.getDeklarisanaMasa() +
                " kg. Stvarna masa tereta: " + teret.getStvarnaMasa() + " kg.";
    }

}
