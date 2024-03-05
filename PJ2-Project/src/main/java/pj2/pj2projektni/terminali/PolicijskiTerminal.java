package pj2.pj2projektni.terminali;
import pj2.pj2projektni.mapa.*;
import pj2.pj2projektni.simulacija.Simulacija;
import pj2.pj2projektni.vozila.*;
import pj2.pj2projektni.vozila.kamioni.*;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PolicijskiTerminal extends Thread{

    private int ID;
    private boolean samoZaKamione = false;
    public static final Object PT_NOTIFY_CAR = new Object();
    public static final Object PT_NOTIFY_TRUCK = new Object();
    private Consumer<Integer> ukloniVoziloSaPolja;
    private BiConsumer<Vozilo, Integer> voziloNaPK;
    private Consumer<Integer> ukloniVoziloSaPk;
    private BiConsumer<Integer, Boolean> omoguciPT;
    private Consumer<String> dodajDogadjaj;

    private boolean radi;

    public boolean isRadi() {
        return radi;
    }
    private boolean mozeDaObradi(Vozilo vozilo){

        if (samoZaKamione && "K".equals(vozilo.getOznaka())) return true;
        if (!samoZaKamione && !("K".equals(vozilo.getOznaka()))) return true;
        return false;
    }
    private int procesirajVozilo(Vozilo vozilo){
        return vozilo.policijskaKontrola();
    }
    private void notifikujCarinskiTerminal(Vozilo vozilo){

        if(samoZaKamione)
            synchronized (CarinskiTerminal.CT_NOTIFY_TRUCK){
                CarinskiTerminal.TRUCK_INTERNAL_ROW.offer(vozilo);
                CarinskiTerminal.CT_NOTIFY_TRUCK.notify();
            }
        else
            synchronized (CarinskiTerminal.CT_NOTIFY_CAR){
                CarinskiTerminal.CAR_INTERNAL_ROW.offer(vozilo);
                CarinskiTerminal.CT_NOTIFY_CAR.notify();
            }

        synchronized (vozilo){
            try{
                vozilo.wait();
                ukloniVoziloSaPk.accept(ID);
            }catch (InterruptedException e){

            }
        }
    }
    private void provjeraKontrole(){
        if (Kontrola.PAUZA){
            synchronized (Kontrola.LOCK){
                try {
                    Kontrola.LOCK.wait();
                } catch (InterruptedException e) {

                }
            }
        }
    }
    public int getID() {
        return ID;
    }

    @Override
    public void run() {
        radi = Kontrola.PT_ableToWork(ID);

            while (true) {
                if (Kontrola.KRAJ) return;
                provjeraKontrole();

                if (!Kontrola.PT_ableToWork(ID)) {
                    radi = false;
                    omoguciPT.accept(ID, radi);
                    dodajDogadjaj.accept("PT_" + ID + " je blokiran.");
                    synchronized (this) {
                        try {
                            this.wait();
                            radi = true;
                        } catch (InterruptedException e) {
                            Logger.getLogger(Simulacija.class.getName()).log(Level.SEVERE, e.fillInStackTrace().toString());
                        }
                    }
                }
                omoguciPT.accept(ID, radi);

                if ((Mapa.STAZA[Mapa.VELICINA_STAZE - 1].getNaPolju() != null) && (mozeDaObradi(Mapa.STAZA[Mapa.VELICINA_STAZE - 1].getNaPolju()))) {

                    Vozilo vozilo = null;

                    synchronized (Mapa.STAZA[Mapa.VELICINA_STAZE - 1]) {    // sinhronizacija na nivou POSLJEDNJEG POLJA

                        if ((Mapa.STAZA[Mapa.VELICINA_STAZE - 1].getNaPolju() != null) && (mozeDaObradi(Mapa.STAZA[Mapa.VELICINA_STAZE - 1].getNaPolju()))) {

                            vozilo = Mapa.STAZA[Mapa.VELICINA_STAZE - 1].getNaPolju();
                            String opis = "PT_" + ID + " preuzeo vozilo " + vozilo.getID() + " na obradu";
                            System.out.println(opis);
                            dodajDogadjaj.accept(opis);
                            Mapa.STAZA[Mapa.VELICINA_STAZE - 1].setNaPolju(null);
                            ukloniVoziloSaPolja.accept(0);
                            Mapa.STAZA[Mapa.VELICINA_STAZE - 1].notify();
                        }
                    }

                    if (vozilo != null) {
                        String tempOpis = "PT_" + ID + " procesira " + vozilo + " ...";
                        System.out.println(tempOpis);
                        dodajDogadjaj.accept(tempOpis);
                        voziloNaPK.accept(vozilo, ID);
                        int temp = procesirajVozilo(vozilo);

                        if (temp == 1) {
                            tempOpis = "PT_" + ID + " je ZAVRSION sa procesiranjem vozila " + vozilo.getID() + ". Ceka se na CT...";
                            System.out.println(tempOpis);
                            dodajDogadjaj.accept(tempOpis);
                            notifikujCarinskiTerminal(vozilo);

                        } else if (temp == -1) {
                            tempOpis = "PT_" + ID + " je ZAVRSIO sa procesiranjem vozila " + vozilo.getID() + " . Vozilo nijie proslo " +
                                    " policijsku kontrolu i iskljucuje se iz saobracaja.";
                            System.out.println(tempOpis);
                            dodajDogadjaj.accept(tempOpis);
                            synchronized (vozilo.krajProcesiranja) {
                                ukloniVoziloSaPk.accept(ID);
                                vozilo.krajProcesiranja.notify();
                            }

                        }
                    }
                } else {
                    if (samoZaKamione) {
                        synchronized (PT_NOTIFY_TRUCK) {
                            try {
                                System.out.println("PT_" + ID + " ceka da na kraj staze pristigne vozilo koje moze da obradi...");
                                PT_NOTIFY_TRUCK.wait();
                            } catch (InterruptedException e) {
                                //Thread.currentThread().interrupt();
                            }
                        }
                    } else {
                        synchronized (PT_NOTIFY_CAR) {
                            try {
                                System.out.println("PT_" + ID + " ceka da na kraj staze pristigne vozilo koje moze da obradi...");
                                PT_NOTIFY_CAR.wait();
                            } catch (InterruptedException e) {
                                //Thread.currentThread().interrupt();
                            }
                        }
                    }
                }
            }
    }

    public PolicijskiTerminal(boolean samoZaKamione, int ID, Consumer<Integer> ukloniVoziloSaPolja, BiConsumer<Vozilo, Integer> voziloNaPK,
                              Consumer<Integer> ukloniVoziloSaPk, BiConsumer<Integer, Boolean> omoguciPT, Consumer<String> dodajDogadjaj)
    {
        this.samoZaKamione = samoZaKamione;
        this.ID = ID;
        this.ukloniVoziloSaPolja = ukloniVoziloSaPolja;
        this.voziloNaPK = voziloNaPK;
        this.ukloniVoziloSaPk = ukloniVoziloSaPk;
        this.omoguciPT = omoguciPT;
        this.dodajDogadjaj = dodajDogadjaj;
    }

    @Override
    public String toString() {
        return "PT_"+ID;
    }
}
