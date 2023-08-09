package putnici;

import java.io.Serializable;

public class Pasos implements Serializable {

    private static int brojPasosa = 0, brojNeispravnihPasosa = 0;
    private boolean pasosJeNeispravan = false;

    public Pasos(){

        brojPasosa++;

        if((int)(3*brojPasosa/100) > brojNeispravnihPasosa){

            this.pasosJeNeispravan = true;
            brojNeispravnihPasosa++;
        }
    }
    public boolean pasosJeNeispravan(){
        return pasosJeNeispravan;
    }
}
