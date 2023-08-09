package simulacija;
import mapa.*;
import terminali.CarinskiTerminal;
import terminali.PolicijskiTerminal;
import vozila.*;
import vozila.kamioni.*;
import vozila.autobusi.*;
import vozila.licnavozila.*;
import putnici.*;
import java.util.*;


public class Simulacija {

    private static Random random = new Random();

    private static List<Putnik> kreiranjePutnika(int broj){
        List<Putnik> lp = new ArrayList<>();
        for(int i = 0; i < broj; i++)
            lp.add(new Putnik());

        return lp;
    }

    private static List<Vozilo> kreiranjeLicnihVozila(int broj) throws EmptyCollectionException{
        List<Vozilo> llv = new ArrayList<>();
        for(int i = 0; i < broj; i++)
            llv.add(new LicnoVozilo( kreiranjePutnika(random.nextInt(5)+1) ));

        return llv;
    }

    private static List<Vozilo> kreiranjeKamiona(int broj) throws EmptyCollectionException{
        List<Vozilo> lk = new ArrayList<>();
        for(int i = 0; i < broj; i++)
            lk.add(new Kamion( kreiranjePutnika(random.nextInt(3)+1) ));

        return lk;
    }

    private static List<Vozilo> kreiranjeAutobusa(int broj) throws EmptyCollectionException{
        List<Vozilo> la = new ArrayList<>();
        for(int i = 0; i < broj; i++)
            la.add(new Autobus( kreiranjePutnika(random.nextInt(52)+1) ));

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

    public static void main(String[] args) throws EmptyCollectionException{

        Queue<Vozilo> vozila = kreiranjeKolekcijeVozila(35,10,5);    // kolekcija vozila

        CarinskiTerminal ct1 = new CarinskiTerminal(false);
        CarinskiTerminal ct2 = new CarinskiTerminal(true);

        ct1.start(); ct2.start();

        PolicijskiTerminal pt1 = new PolicijskiTerminal(false,1);
        PolicijskiTerminal pt2 = new PolicijskiTerminal(false,2);
        PolicijskiTerminal pt3 = new PolicijskiTerminal(true,3);

        pt1.start(); pt2.start(); pt3.start();

        for(Iterator<Vozilo> i = vozila.iterator(); i.hasNext(); )
            i.next().start();           // kretanje vozila kroz stazu mape


    }


}

