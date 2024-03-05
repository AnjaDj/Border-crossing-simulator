
import java.util.*;

public class FakturaStavka implements StringUObjekat<FakturaStavka>{
	public String sifra;
	public String nazivProizvoda;
	public double kolicina;
	public double cijenaPoKomadu;
	
	public FakturaStavka() {
		super();
	}
	
	public FakturaStavka(String sifra) {
		this.sifra = sifra;
	}
	
	public FakturaStavka pretvoriStringUObjekat(String string) {
		FakturaStavka fs = new FakturaStavka();
		String[] split = string.split(",");
		fs.sifra = split[1];
		fs.nazivProizvoda = split[2];
		fs.kolicina = Double.parseDouble(split[3]);
		fs.cijenaPoKomadu = Double.parseDouble(split[4]);
		return fs;
	}
}