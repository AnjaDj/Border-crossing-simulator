import java.util.*;

public class Predmet{
	
	public String naziv;
	public List<Ocjena> listaOcjena;
	
	public Predmet(String naziv, List<Ocjena> listaOcjena){
		this.naziv = naziv;
		this.listaOcjena = listaOcjena;
	}
}