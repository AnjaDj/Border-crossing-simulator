package pj2.pj2projektni.putnici;

import java.util.Random;

public class Putnik implements java.io.Serializable{

    private static Random random = new Random();
    private Kofer kofer = null;
    private Pasos pasos;

    public Putnik( ){
        this.pasos = new Pasos();
        if (random.nextInt(100) > 70) kofer = new Kofer();
    }

    public boolean koferSadrziNedozvoljeneStvari(){
        if (imaKofer())
            return kofer.koferSadrziNedozvoljeneStvari();
        return false;
    }
    public boolean pasosJeNeispravan(){
        return pasos.pasosJeNeispravan();
    }
    public boolean imaKofer(){
        if (kofer == null) return false;
        else return true;
    }

    @Override
    public String toString() {
        return "Putnik -> pasos ispravan = "+(!pasosJeNeispravan())+" , sadrzi kofer = "+imaKofer()+
                " , kofer sadrzi nedozvoljene stvari = "+koferSadrziNedozvoljeneStvari()+" ";
    }
}
