package pj2.pj2projektni.terminali;
import pj2.pj2projektni.simulacija.Simulacija;
import pj2.pj2projektni.vozila.*;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CarinskiTerminal extends Thread{

    private int ID;
    private boolean samoZaKamione = false;
    public static Object CT_NOTIFY_CAR = new Object();
    public static Object CT_NOTIFY_TRUCK = new Object();
    public static Queue<Vozilo> CAR_INTERNAL_ROW = new LinkedList<>();
    public static Queue<Vozilo> TRUCK_INTERNAL_ROW = new LinkedList<>();
    private BiConsumer<Vozilo, Integer> voziloNaCT;
    private Consumer<Integer> ukloniVoziloSaCT;
    private BiConsumer<Integer, Boolean> omoguciCT;
    private Consumer<String> dodajDogadjaj;

    private boolean radi;

    public boolean isRadi() {
        return radi;
    }

    private void procesirajVozilo(Vozilo vozilo){
        vozilo.carinskaKontrola();
        synchronized (vozilo.krajProcesiranja){
            vozilo.krajProcesiranja.notify();       // konacan kraj kretanja vozila, tj terminacija niti vozila
        }
    }

    public int getID() {
        return ID;
    }

    private void provjeraKontrole(){
        if (Kontrola.PAUZA){
            synchronized (Kontrola.LOCK){
                try {
                    Kontrola.LOCK.wait();
                } catch (InterruptedException e) {
                    //e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void run() {
            radi = Kontrola.CT_ableToWork(ID);

            while (true) {
                if (Kontrola.KRAJ) return;
                provjeraKontrole();

                if (!Kontrola.CT_ableToWork(ID)) {
                    radi = false;
                    omoguciCT.accept(ID, radi);
                    dodajDogadjaj.accept("CT_" + ID + " je blokiran.");
                    synchronized (this) {
                        try {
                            this.wait();
                            radi = true;
                        } catch (InterruptedException e) {
                            Logger.getLogger(Simulacija.class.getName()).log(Level.SEVERE, e.fillInStackTrace().toString());
                        }
                    }
                }
                omoguciCT.accept(ID, radi);

                if (samoZaKamione) {

                    if (!TRUCK_INTERNAL_ROW.isEmpty()) {
                        Vozilo vozilo = null;
                        synchronized (CT_NOTIFY_TRUCK) {
                            vozilo = TRUCK_INTERNAL_ROW.poll();
                            String tempOpis = this + " preuzima " + vozilo + " od PT na obradu...";
                            System.out.println(tempOpis);
                            dodajDogadjaj.accept(tempOpis);
                            synchronized (vozilo) {
                                vozilo.notify();
                            }

                        }
                        voziloNaCT.accept(vozilo, ID);
                        procesirajVozilo(vozilo);
                        ukloniVoziloSaCT.accept(ID);
                    } else {
                        synchronized (CT_NOTIFY_TRUCK) {
                            try {
                                System.out.println(this + " ceka da mu pristigne vozilo na obradu...");
                                CT_NOTIFY_TRUCK.wait();
                            } catch (InterruptedException e) {
                                //Thread.currentThread().interrupt();
                            }
                        }
                    }

                } else {

                    if (!CAR_INTERNAL_ROW.isEmpty()) {
                        Vozilo vozilo = null;
                        synchronized (CT_NOTIFY_CAR) {
                            vozilo = CAR_INTERNAL_ROW.poll();
                            String tempOpis = this + " preuzima " + vozilo + " od PT na obradu...";
                            System.out.println(tempOpis);
                            dodajDogadjaj.accept(tempOpis);
                            synchronized (vozilo) {
                                vozilo.notify();    // obavestava se PT da je CT preuzeo na obradu vozilo koje je stiglo
                            }                       // upravo sa njega (bas sa tog PolicijskogTerminala) te PT moze nastaviti
                        }                           // dalje obradu nekog novog vozila.
                        voziloNaCT.accept(vozilo, ID);
                        procesirajVozilo(vozilo);
                        ukloniVoziloSaCT.accept(ID);
                    } else {
                        synchronized (CT_NOTIFY_CAR) {
                            try {
                                System.out.println(this + " ceka da mu pristigne vozilo na obradu...");
                                CT_NOTIFY_CAR.wait();
                            } catch (InterruptedException e) {
                                //Thread.currentThread().interrupt();
                            }
                        }
                    }

                }
            }
    }

    public CarinskiTerminal(boolean samoZaKamione, int ID,  BiConsumer<Vozilo, Integer> voziloNaCt, Consumer<Integer> ukloniVoziloSaCt,
                            BiConsumer<Integer, Boolean> omoguciCT, Consumer<String> dodajDogadjaj){
        this.samoZaKamione = samoZaKamione;
        this.ID = ID;
        this.voziloNaCT = voziloNaCt;
        this.ukloniVoziloSaCT = ukloniVoziloSaCt;
        this.omoguciCT = omoguciCT;
        this.dodajDogadjaj = dodajDogadjaj;
    }

    @Override
    public String toString() {
        return "CT_"+ID;
    }
}
