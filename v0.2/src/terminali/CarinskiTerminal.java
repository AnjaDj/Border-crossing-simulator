package terminali;
import vozila.*;
import java.util.*;

public class CarinskiTerminal extends Thread{

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

    @Override
    public void run() {
        while(true){

            if(samoZaKamione){

                        if(!TRUCK_INTERNAL_ROW.isEmpty()) {
                            Vozilo vozilo = null;
                            synchronized (CT_NOTIFY_TRUCK) {
                                vozilo = TRUCK_INTERNAL_ROW.poll();
                                System.out.println("CT preuzima "+vozilo+" od PT na obradu...");
                                synchronized (vozilo){
                                     vozilo.notify();
                                }
                            }
                            procesirajVozilo(vozilo);
                        }
                        else {
                            synchronized (CT_NOTIFY_TRUCK) {
                                try {
                                    System.out.println("CT ceka da mu pristigne vozilo na obradu...");
                                    CT_NOTIFY_TRUCK.wait();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

            }

            else{

                        if(!CAR_INTERNAL_ROW.isEmpty()) {
                            Vozilo vozilo = null;
                            synchronized (CT_NOTIFY_CAR) {
                                vozilo = CAR_INTERNAL_ROW.poll();
                                System.out.println("CT preuzima "+vozilo+" od PT na obradu...");
                                synchronized (vozilo){
                                    vozilo.notify();    // obavestava se PT da je CT preuzeo na obradu vozilo koje je stiglo
                                }                       // upravo sa njega (bas sa tog PolicijskogTerminala) te PT moze nastaviti
                            }                           // dalje obradu nekog novog vozila.
                            procesirajVozilo(vozilo);
                        }
                        else{
                            synchronized (CT_NOTIFY_CAR){
                                try{
                                    System.out.println("CT ceka da mu pristigne vozilo na obradu...");
                                    CT_NOTIFY_CAR.wait();
                                }catch (InterruptedException e){
                                    e.printStackTrace();
                                }
                            }
                        }

            }

        }
    }

    public CarinskiTerminal(boolean samoZaKamione){
        this.samoZaKamione = samoZaKamione;
    }
}
