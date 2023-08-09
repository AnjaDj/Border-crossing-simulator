package terminali;
import vozila.*;
import java.util.*;

public class CarinskiTerminal extends Thread{

    private int ID;
    private boolean samoZaKamione = false;
    public static Object CT_NOTIFY_CAR = new Object();
    public static Object CT_NOTIFY_TRUCK = new Object();
    public static Queue<Vozilo> CAR_INTERNAL_ROW = new LinkedList<>();
    public static Queue<Vozilo> TRUCK_INTERNAL_ROW = new LinkedList<>();

    private void procesirajVozilo(Vozilo vozilo){
        vozilo.carinskaKontrola();
        synchronized (vozilo.krajProcesiranja){
            vozilo.krajProcesiranja.notify();       // konacan kraj kretanja vozila, tj terminacija niti vozila
        }
    }
    private void provjeraKontrole(){
        if (Kontrola.pauza){
            synchronized (Kontrola.lock){
                try {
                    Kontrola.lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void run() {
        if(Kontrola.CT_ableToWork(ID)) {

            while (true) {
                provjeraKontrole();

                if (samoZaKamione) {

                    if (!TRUCK_INTERNAL_ROW.isEmpty()) {
                        Vozilo vozilo = null;
                        synchronized (CT_NOTIFY_TRUCK) {
                            vozilo = TRUCK_INTERNAL_ROW.poll();
                            System.out.println(this + " preuzima " + vozilo + " od PT na obradu...");
                            synchronized (vozilo) {
                                vozilo.notify();
                            }
                        }
                        procesirajVozilo(vozilo);
                    } else {
                        synchronized (CT_NOTIFY_TRUCK) {
                            try {
                                System.out.println(this + " ceka da mu pristigne vozilo na obradu...");
                                CT_NOTIFY_TRUCK.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                } else {

                    if (!CAR_INTERNAL_ROW.isEmpty()) {
                        Vozilo vozilo = null;
                        synchronized (CT_NOTIFY_CAR) {
                            vozilo = CAR_INTERNAL_ROW.poll();
                            System.out.println(this + " preuzima " + vozilo + " od PT na obradu...");
                            synchronized (vozilo) {
                                vozilo.notify();    // obavestava se PT da je CT preuzeo na obradu vozilo koje je stiglo
                            }                       // upravo sa njega (bas sa tog PolicijskogTerminala) te PT moze nastaviti
                        }                           // dalje obradu nekog novog vozila.
                        procesirajVozilo(vozilo);
                    } else {
                        synchronized (CT_NOTIFY_CAR) {
                            try {
                                System.out.println(this + " ceka da mu pristigne vozilo na obradu...");
                                CT_NOTIFY_CAR.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                }

            }
        }
        else{
            System.out.println(this+" je BLOKIRAN!");
        }
    }

    public CarinskiTerminal(boolean samoZaKamione, int ID){
        this.samoZaKamione = samoZaKamione;
        this.ID = ID;
    }
    @Override
    public String toString() {
        return "CT_"+ID;
    }
}
