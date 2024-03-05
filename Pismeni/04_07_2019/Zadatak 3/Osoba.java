import java.util.*;

public class Osoba {
	
	public String ime;
	public String prezime;
	public Integer godiste;
	
	public Osoba(String ime,String prezime,Integer godiste) {
		this.ime = ime;
		this.prezime = prezime;
		this.godiste = godiste;
	}
	
	public String toString() {
		String string = "";
		
		string += "Ime: " + ime + System.getProperty("line.separator");
		string += "Prezime: " + prezime + System.getProperty("line.separator");
		string += "Godiste: " + godiste;
		
		return string;
	}
}