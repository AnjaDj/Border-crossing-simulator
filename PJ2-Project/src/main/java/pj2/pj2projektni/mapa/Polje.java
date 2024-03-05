package pj2.pj2projektni.mapa;
import pj2.pj2projektni.vozila.Vozilo;

public class Polje {

    private Vozilo naPolju;

    public Polje(){
        naPolju = null;
    }

    public Vozilo getNaPolju() {
        return naPolju;
    }

    public void setNaPolju(Vozilo vozilo) {
        naPolju = vozilo;
    }
}
