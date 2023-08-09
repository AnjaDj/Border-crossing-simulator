package putnici;

import java.io.Serializable;

public class Kofer implements Serializable {

    private static int brojKofera = 0, brojKoferaSaZabranjenimStvarima = 0;
    private boolean koferSadrziNedozvoljeneStvari = false;

    public Kofer(){

        brojKofera++;
        if ((int)(10*brojKofera/100) > brojKoferaSaZabranjenimStvarima) {

            this.koferSadrziNedozvoljeneStvari = true;
            brojKoferaSaZabranjenimStvarima++;
        }
    }

    public boolean koferSadrziNedozvoljeneStvari(){
        return koferSadrziNedozvoljeneStvari;
    }


}
