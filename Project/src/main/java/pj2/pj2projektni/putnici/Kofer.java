package pj2.pj2projektni.putnici;

import java.io.Serializable;

public class Kofer implements Serializable {

    private static int BROJ_KOFERA = 0, BROJ_KOFERA_SA_ZABRANJENIM_STVARIMA = 0;
    private boolean koferSadrziNedozvoljeneStvari = false;

    public Kofer(){

        BROJ_KOFERA++;
        if ((int)(10*BROJ_KOFERA/100) > BROJ_KOFERA_SA_ZABRANJENIM_STVARIMA) {

            this.koferSadrziNedozvoljeneStvari = true;
            BROJ_KOFERA_SA_ZABRANJENIM_STVARIMA++;
        }
    }

    public boolean koferSadrziNedozvoljeneStvari(){
        return koferSadrziNedozvoljeneStvari;
    }


}
