package terminali;
import vozila.*;
import mapa.*;
import vozila.kamioni.*;

public class PolicijskiTerminal extends Thread{

    private int ID;
    private boolean samoZaKamione = false;
    public static Object PT_NOTIFY_CAR = new Object();
    public static Object PT_NOTIFY_TRUCK = new Object();

    private boolean mozeDaObradi(Vozilo vozilo){

        if (samoZaKamione && (vozilo instanceof Kamion)) return true;
        if (!samoZaKamione && !(vozilo instanceof Kamion)) return true;
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
            try{ vozilo.wait(); }catch (InterruptedException e){e.printStackTrace();}
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
        if(Kontrola.PT_ableToWork(ID)) {

            while (true) {
                provjeraKontrole();

                if ((Mapa.STAZA[Mapa.VELICINA_STAZE - 1].getNaPolju() != null) && (mozeDaObradi(Mapa.STAZA[Mapa.VELICINA_STAZE - 1].getNaPolju()))) {

                    Vozilo vozilo = null;

                    synchronized (Mapa.STAZA[Mapa.VELICINA_STAZE - 1]) {    // sinhronizacija na nivou POSLJEDNJEG POLJA

                        if ((Mapa.STAZA[Mapa.VELICINA_STAZE - 1].getNaPolju() != null) && (mozeDaObradi(Mapa.STAZA[Mapa.VELICINA_STAZE - 1].getNaPolju()))) {

                            vozilo = Mapa.STAZA[Mapa.VELICINA_STAZE - 1].getNaPolju();
                            System.out.println("PT_" + ID + " skida " + vozilo + " sa zadnje pozicije na stazi.");
                            Mapa.STAZA[Mapa.VELICINA_STAZE - 1].setNaPolju(null);
                            Mapa.STAZA[Mapa.VELICINA_STAZE - 1].notify();
                        }
                    }

                    if (vozilo != null) {

                        System.out.println("PT_" + ID + " procesira " + vozilo + " ...");
                        int temp = procesirajVozilo(vozilo);

                        if (temp == 1) {

                            System.out.println("PT_" + ID + " je ZAVRSION sa procesiranjem " + vozilo + ". Ceka se na CT...");
                            notifikujCarinskiTerminal(vozilo);

                        } else if (temp == -1) {

                            System.out.println("PT_" + ID + " je ZAVRSIO sa procesiranjem " + vozilo + " . Vozilo nijie proslo " +
                                    " policijsku kontrolu i iskljucuje se iz saobracaja.");
                            synchronized (vozilo.krajProcesiranja) {
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
                                e.printStackTrace();
                            }
                        }
                    } else {
                        synchronized (PT_NOTIFY_CAR) {
                            try {
                                System.out.println("PT_" + ID + " ceka da na kraj staze pristigne vozilo koje moze da obradi...");
                                PT_NOTIFY_CAR.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }else {
            System.out.println(this+" je BLOKIRAN!");
        }
    }

    public PolicijskiTerminal(boolean samoZaKamione, int ID){this.samoZaKamione = samoZaKamione; this.ID = ID;}

    @Override
    public String toString() {
        return "PT_"+ID;
    }
}
