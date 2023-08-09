package mapa;
import vozila.Vozilo;

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
