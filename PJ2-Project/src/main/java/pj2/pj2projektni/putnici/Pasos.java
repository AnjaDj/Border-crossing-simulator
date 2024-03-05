package pj2.pj2projektni.putnici;

import java.io.Serializable;

public class Pasos implements Serializable {

    private static int BROJ_PASOSA = 0, BROJ_NEISPRAVNIH_PASOSA = 0;
    private boolean pasosJeNeispravan = false;

    public Pasos(){

        BROJ_PASOSA++;

        if((int)(3*BROJ_PASOSA/100) > BROJ_NEISPRAVNIH_PASOSA){

            this.pasosJeNeispravan = true;
            BROJ_NEISPRAVNIH_PASOSA++;
        }
    }
    public boolean pasosJeNeispravan(){
        return pasosJeNeispravan;
    }
}
