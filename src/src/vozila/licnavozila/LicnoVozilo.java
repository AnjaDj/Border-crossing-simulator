package pj2.pj2projektni.vozila.licnavozila;

import pj2.pj2projektni.putnici.Putnik;
import pj2.pj2projektni.vozila.*;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class LicnoVozilo extends Vozilo implements java.io.Serializable{

    public LicnoVozilo(List<Putnik> putniciUVozilu, Consumer<Integer> ukloniVoziloSaPolja, BiConsumer<Vozilo, Integer> pomjeriVoziloNaPolje,
                       BiConsumer<Vozilo, Integer> pomjeriVoziloNaPoljeOstatak, Consumer<Integer> ukloniVoziloSaPoljaOstatak) throws EmptyCollectionException {
        super(ukloniVoziloSaPolja, pomjeriVoziloNaPolje, pomjeriVoziloNaPoljeOstatak, ukloniVoziloSaPoljaOstatak);

        if (putniciUVozilu.size() == 0)
            throw new EmptyCollectionException("Ne mozete kreirati vozilo bez i jednog putnika!");

        if (putniciUVozilu.size() > 5)
                super.putniciUVozilu = putniciUVozilu.subList(0,5);
        else
                super.putniciUVozilu = putniciUVozilu;

        vozac = super.putniciUVozilu.get(0);
    }

    @Override
    public String getOznaka() {
        return "V";
    }

    @Override
    public String getBoja() {
        return "#C00000";
    }

    private static final long SLEEP_TIME = 2000;
    @Override
    public void carinskaKontrola(){
        try{
            sleep(SLEEP_TIME);
        }catch (InterruptedException e){
            //e.printStackTrace();
        }
        System.out.println(this+" PRELAZI GRANICU!");
    }

}
