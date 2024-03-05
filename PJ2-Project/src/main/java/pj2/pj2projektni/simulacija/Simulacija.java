package pj2.pj2projektni.simulacija;
import pj2.pj2projektni.incident.Incident;
import pj2.pj2projektni.putnici.*;
import pj2.pj2projektni.terminali.CarinskiTerminal;
import pj2.pj2projektni.terminali.FileWatcher;
import pj2.pj2projektni.terminali.Kontrola;
import pj2.pj2projektni.terminali.PolicijskiTerminal;
import pj2.pj2projektni.vozila.*;
import pj2.pj2projektni.vozila.autobusi.*;
import pj2.pj2projektni.vozila.kamioni.*;
import pj2.pj2projektni.vozila.licnavozila.*;
import java.io.*;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import static pj2.pj2projektni.terminali.Kontrola.*;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Simulacija {

    private static final String LOGGER_PATH = "src"+File.separator+"main"+File.separator+"resources"+
                                                    File.separator+"logs"+File.separator+"Simulacija.log";

    static {
        try {
            Handler fileHandler = new FileHandler(LOGGER_PATH, true);
            Logger.getLogger(Simulacija.class.getName()).setUseParentHandlers(false);
            Logger.getLogger(Simulacija.class.getName()).addHandler(fileHandler);
        } catch (IOException e) {
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.SEVERE, e.fillInStackTrace().toString());
        }
    }

    private static Random random = new Random();
    private static  BiConsumer<Vozilo, Integer> pomjeriVoziloNaPolje;
    private static Consumer<Integer> ukloniVoziloSaPolja;
    private static  BiConsumer<Vozilo, Integer> voziloNaPK;
    private static Consumer<Integer> ukloniVoziloSaPk;
    private static BiConsumer<Vozilo, Integer> voziloNaCT;
    private static Consumer<Integer> ukloniVoziloSaCT;
    private static  BiConsumer<Vozilo, Integer> pomjeriVoziloNaPoljeOstatak;
    private static Consumer<Integer> ukloniVoziloSaPoljaOstatak;
    private static BiConsumer<Integer, Boolean> omoguciPT;
    private static BiConsumer<Integer, Boolean> omoguciCT;
    private static Consumer<String> dodajDogadjaj;

    public static Consumer<Integer> getUkloniVoziloSaPolja() {
        return ukloniVoziloSaPolja;
    }

    public static BiConsumer<Vozilo, Integer> getPomjeriVoziloNaPolje() {
        return pomjeriVoziloNaPolje;
    }

    public static void setUkloniVoziloSaPolja(Consumer<Integer> ukloniVoziloSaPolja) {
        Simulacija.ukloniVoziloSaPolja = ukloniVoziloSaPolja;
    }

    public static void setPomjeriVoziloNaPolje(BiConsumer<Vozilo, Integer> pomjeriVoziloNaPolje) {
        Simulacija.pomjeriVoziloNaPolje = pomjeriVoziloNaPolje;
    }

    public static BiConsumer<Vozilo, Integer> getVoziloNaPK() {
        return voziloNaPK;
    }

    public static void setVoziloNaPK(BiConsumer<Vozilo, Integer> voziloNaPK) {
        Simulacija.voziloNaPK = voziloNaPK;
    }

    public static Consumer<Integer> getUkloniVoziloSaPk() {
        return ukloniVoziloSaPk;
    }

    public static void setUkloniVoziloSaPk(Consumer<Integer> ukloniVoziloSaPk) {
        Simulacija.ukloniVoziloSaPk = ukloniVoziloSaPk;
    }

    public static BiConsumer<Vozilo, Integer> getVoziloNaCT() {
        return voziloNaCT;
    }

    public static void setVoziloNaCT(BiConsumer<Vozilo, Integer> voziloNaCT) {
        Simulacija.voziloNaCT = voziloNaCT;
    }

    public static Consumer<Integer> getUkloniVoziloSaCT() {
        return ukloniVoziloSaCT;
    }

    public static void setUkloniVoziloSaCT(Consumer<Integer> ukloniVoziloSaCT) {
        Simulacija.ukloniVoziloSaCT = ukloniVoziloSaCT;
    }

    public static BiConsumer<Vozilo, Integer> getPomjeriVoziloNaPoljeOstatak() {
        return pomjeriVoziloNaPoljeOstatak;
    }

    public static void setPomjeriVoziloNaPoljeOstatak(BiConsumer<Vozilo, Integer> pomjeriVoziloNaPoljeOstatak) {
        Simulacija.pomjeriVoziloNaPoljeOstatak = pomjeriVoziloNaPoljeOstatak;
    }

    public static Consumer<Integer> getUkloniVoziloSaPoljaOstatak() {
        return ukloniVoziloSaPoljaOstatak;
    }

    public static void setUkloniVoziloSaPoljaOstatak(Consumer<Integer> ukloniVoziloSaPoljaOstatak) {
        Simulacija.ukloniVoziloSaPoljaOstatak = ukloniVoziloSaPoljaOstatak;
    }

    public static BiConsumer<Integer, Boolean> getOmoguciPT() {
        return omoguciPT;
    }

    public static void setOmoguciPT(BiConsumer<Integer, Boolean> omoguciPT) {
        Simulacija.omoguciPT = omoguciPT;
    }

    public static BiConsumer<Integer, Boolean> getOmoguciCT() {
        return omoguciCT;
    }

    public static void setDodajDogadjaj(Consumer<String> dodajDogadjaj) {
        Simulacija.dodajDogadjaj = dodajDogadjaj;
    }

    public static void setOmoguciCT(BiConsumer<Integer, Boolean> omoguciCT) {
        Simulacija.omoguciCT = omoguciCT;
    }

    private static List<Putnik> kreiranjePutnika(int broj){
        List<Putnik> lp = new ArrayList<>();
        for(int i = 0; i < broj; i++)
            lp.add(new Putnik());

        return lp;
    }

    private static List<Vozilo> kreiranjeLicnihVozila(int broj) throws EmptyCollectionException{
        List<Vozilo> llv = new ArrayList<>();
        for(int i = 0; i < broj; i++)
            llv.add(new LicnoVozilo( kreiranjePutnika(random.nextInt(5)+1), ukloniVoziloSaPolja, pomjeriVoziloNaPolje, pomjeriVoziloNaPoljeOstatak, ukloniVoziloSaPoljaOstatak));

        return llv;
    }

    private static List<Vozilo> kreiranjeKamiona(int broj) throws EmptyCollectionException{
        List<Vozilo> lk = new ArrayList<>();
        for(int i = 0; i < broj; i++)
            lk.add(new Kamion( kreiranjePutnika(random.nextInt(3)+1), ukloniVoziloSaPolja, pomjeriVoziloNaPolje, pomjeriVoziloNaPoljeOstatak, ukloniVoziloSaPoljaOstatak ));

        return lk;
    }

    private static List<Vozilo> kreiranjeAutobusa(int broj) throws EmptyCollectionException{
        List<Vozilo> la = new ArrayList<>();
        for(int i = 0; i < broj; i++)
            la.add(new Autobus( kreiranjePutnika(random.nextInt(52)+1), ukloniVoziloSaPolja, pomjeriVoziloNaPolje, pomjeriVoziloNaPoljeOstatak, ukloniVoziloSaPoljaOstatak));

        return la;
    }

    private static Queue<Vozilo> kreiranjeKolekcijeVozila(int brojLV, int brojK, int brojA) throws EmptyCollectionException{
        List<Vozilo> temp = new ArrayList<>();
            temp.addAll(kreiranjeLicnihVozila(brojLV));
            temp.addAll(kreiranjeAutobusa(brojA));
            temp.addAll(kreiranjeKamiona(brojK));

            Collections.shuffle(temp);

        Queue<Vozilo> red = new LinkedList<>();
            red.addAll(temp);
        return red;
    }

    public static long START_TIME = 0;
    public static final long SLEEP_TIME = 500;

    public static void main(String[] args) throws EmptyCollectionException{

        START_TIME = System.nanoTime();

        List<Thread> terminali = new ArrayList<>();

        Queue<Vozilo> vozila = kreiranjeKolekcijeVozila(35,10,5);    // kolekcija vozila

        CarinskiTerminal ct1 = new CarinskiTerminal(false,1, voziloNaCT, ukloniVoziloSaCT, omoguciCT, dodajDogadjaj);
        CarinskiTerminal ct2 = new CarinskiTerminal(true,2, voziloNaCT, ukloniVoziloSaCT, omoguciCT, dodajDogadjaj);

        terminali.add(ct1);
        terminali.add(ct2);

        PolicijskiTerminal pt1 = new PolicijskiTerminal(false,1, ukloniVoziloSaPolja, voziloNaPK, ukloniVoziloSaPk, omoguciPT, dodajDogadjaj);
        PolicijskiTerminal pt2 = new PolicijskiTerminal(false,2, ukloniVoziloSaPolja, voziloNaPK, ukloniVoziloSaPk, omoguciPT, dodajDogadjaj);
        PolicijskiTerminal pt3 = new PolicijskiTerminal(true,3, ukloniVoziloSaPolja, voziloNaPK, ukloniVoziloSaPk, omoguciPT, dodajDogadjaj);

        terminali.add(pt1);
        terminali.add(pt2);
        terminali.add(pt3);

        new FileWatcher(List.of(ct1, ct2), List.of(pt1, pt2, pt3)).start();

        for (Thread t : terminali) t.start();

        for(Vozilo v : vozila) v.start();           // kretanje vozila kroz stazu mape

        while(!Kontrola.KRAJ) {

            if(!Kontrola.PAUZA)
                synchronized (Kontrola.LOCK) {
                    Kontrola.LOCK.notifyAll();
                }

            try {
                Thread.sleep(SLEEP_TIME);
            } catch (InterruptedException e) {
                //throw new RuntimeException(e);
            }
        }

        for (Vozilo v : vozila) v.interrupt();

        for(Thread t : terminali) t.interrupt();

        System.out.println("KRAJ");
    }
}

