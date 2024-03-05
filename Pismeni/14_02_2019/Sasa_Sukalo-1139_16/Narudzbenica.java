
import java.util.*;

public class Narudzbenica implements StringUObjekat<Narudzbenica>{
	public String sifra;
	public String datumKupovine;
	public ArrayList<NarudzbenicaStavka> nizStavki;
	
	public Narudzbenica() {
		super();
		nizStavki = new ArrayList<NarudzbenicaStavka>();
	}
	
	public Narudzbenica(String sifra) {
		this.sifra = sifra;
		nizStavki = new ArrayList<NarudzbenicaStavka>();
	}
	
	public Narudzbenica pretvoriStringUObjekat(String string) {
		Narudzbenica n = new Narudzbenica();
		String[] split = string.split(",");
		n.sifra = split[1];
		n.datumKupovine = split[2];
		return n;
	}
}