package vozila.kamioni;
import putnici.*;
import terminali.PolicijskiTerminal;
import vozila.*;

import java.io.*;
import java.util.*;

public class Kamion extends Vozilo implements java.io.Serializable{

    protected static int brojKamiona = 0, brojKamionaSaPretovarom = 0;
    private Teret teret;

    public Kamion(List<Putnik> putniciUVozilu) throws EmptyCollectionException {

        brojKamiona++;
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
    protected void notifikujPolicijskiTerminal() {
        synchronized (PolicijskiTerminal.PT_NOTIFY_TRUCK) {
            PolicijskiTerminal.PT_NOTIFY_TRUCK.notify();
        }
    }
    @Override
    public void carinskaKontrola(){

        if (imaUrednuMasu() == false) {

            try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("CT_S_kaznjena_vozila.txt"),true))){
                oos.writeObject(this);
            }catch (IOException e){
                e.printStackTrace();
            }

            try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File("CT_T_kaznjena_vozila.txt"),true)))){
                pw.println(this+ " je bio preopterecen te ne moze preci granicu.");
            }catch (IOException e){
                e.printStackTrace();
            }

            System.out.println(this+" NE PRELAZI GRANICU!");

        }else System.out.println(this+" PRELAZI GRANICU!");
    }

}
