import java.util.*;

public class Ucenik{
	
	public String ime, prezime;
	public List<Predmet> listaPredmeta;
	
	public Ucenik(String ime, String prezime,List<Predmet> lista){
		this.ime = ime;
		this.prezime = prezime;
		this.listaPredmeta = lista;
	}
}