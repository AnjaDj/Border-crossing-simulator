import java.util.*;

public class Kategorija{

	public String naziv;
	public Kategorija podkategorija = null;
	
	public Kategorija(String naziv, Kategorija podkategorija){
		this.naziv = naziv;
		this.podkategorija = podkategorija;
	}
	
	@Override
	public String toString(){		
		if (podkategorija == null)
			return naziv;
		else
			return naziv+" "+podkategorija;
	}
}