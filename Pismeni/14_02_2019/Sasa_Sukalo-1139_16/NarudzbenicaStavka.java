
import java.util.*;

public class NarudzbenicaStavka implements StringUObjekat<NarudzbenicaStavka> {
	public String sifra;
	public String nazivProizvoda;
	public double kolicina;
	public double cijenaPoKomadu;
	
	public NarudzbenicaStavka() {
		super();
	}
	
	public NarudzbenicaStavka(String sifra) {
		this.sifra = sifra;
	}
	
	public NarudzbenicaStavka pretvoriStringUObjekat(String string) {
		NarudzbenicaStavka ns = new NarudzbenicaStavka();
		String[] split = string.split(",");
		ns.sifra = split[1];
		ns.nazivProizvoda = split[2];
		ns.kolicina = Double.parseDouble(split[3]);
		ns.cijenaPoKomadu = Double.parseDouble(split[4]);
		return ns;
	}
}