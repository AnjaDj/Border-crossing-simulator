package vozila.licnavozila;

import putnici.Putnik;
import vozila.*;
import java.util.List;

public class LicnoVozilo extends Vozilo implements java.io.Serializable{

    public LicnoVozilo(List<Putnik> putniciUVozilu) throws EmptyCollectionException {

        if (putniciUVozilu.size() == 0)
            throw new EmptyCollectionException("Ne mozete kreirati vozilo bez i jednog putnika!");

        if (putniciUVozilu.size() > 5)
                super.putniciUVozilu = putniciUVozilu.subList(0,5);
        else
                super.putniciUVozilu = putniciUVozilu;

        vozac = super.putniciUVozilu.get(0);
    }

    @Override
    public void carinskaKontrola(){
        try{
            sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(this+" PRELAZI GRANICU!");
    }

}
