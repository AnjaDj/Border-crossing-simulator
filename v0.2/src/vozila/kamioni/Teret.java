package vozila.kamioni;
import java.util.*;

public class Teret implements java.io.Serializable {

    private static  Random random = new Random();
    private double deklarisanaMasa, stvarnaMasa;
    private CarinskaDokumentacija carinskaDokumentacija = null;
    private void algoritam(){

        int zeljeniBroj = (int)(20*Kamion.brojKamiona/100);
        if(zeljeniBroj > Kamion.brojKamionaSaPretovarom){
            stvarnaMasa = random.nextInt( (int)(30*deklarisanaMasa/100) ) + deklarisanaMasa;
            Kamion.brojKamionaSaPretovarom++;
        }else
            stvarnaMasa = deklarisanaMasa;
    }

    public Teret(){

        if(random.nextInt(100) > 50) carinskaDokumentacija = new CarinskaDokumentacija();
        deklarisanaMasa = random.nextInt(1000000)+1; // 1 - 1 000 000
        algoritam();
    }
    public boolean imaCarinskuDokumentaciju() {

        if(carinskaDokumentacija == null) return false;
        else return true;
    }
    public boolean imaUrednuMasu(){

        if (stvarnaMasa == deklarisanaMasa) return true;
        else return false;
    }
}
