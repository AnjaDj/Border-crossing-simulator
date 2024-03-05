
import java.util.*;

public class Faktura implements StringUObjekat<Faktura>{
	public String sifra;
	public String nazivKupca;
	public double ukupanIznos;
	public ArrayList<FakturaStavka> nizStavki;
	
	public Faktura() {
		super();
		nizStavki = new ArrayList<FakturaStavka>();
	}
	
	public Faktura(String sifra) {
		this.sifra = sifra;
		nizStavki = new ArrayList<FakturaStavka>();
	}
	
	public Faktura pretvoriStringUObjekat(String string) {
		Faktura f = new Faktura();
		String[] split = string.split(",");
		f.sifra = split[1];
		f.nazivKupca = split[2];
		f.ukupanIznos = Double.parseDouble(split[3]);
		return f;
	}
	
	@Override
	public String toString() {
		return "Faktura "+sifra;
	}
}