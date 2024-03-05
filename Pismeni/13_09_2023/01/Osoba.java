import java.io.*;

public abstract class Osoba implements java.io.Serializable{

	public String ime, prezime, jmbg;
	public UslugeNaPasosima tipUsluge;
	public Pasos pasos;
	
	public Osoba(String ime, String prezime, String jmbg){
		this.prezime = prezime;
		this.jmbg = jmbg;
		this.ime = ime;
	}
	
	public void setTipUsluge(UslugeNaPasosima tipUsluge){
		this.tipUsluge = tipUsluge;
	}
	
	public abstract void setNovac(int novac);
	
	public boolean podnosenjeZahtjevaZaPasos(){
		if 		(tipUsluge == UslugeNaPasosima.PONISTAVANJE_VAZECEG_PASOSA)    pasos = null;
		else if (tipUsluge == UslugeNaPasosima.IZDAVANJE_OBICNOG_PASOSA)       pasos = new ObicanPasos(getVrijemeTrajanja(),this.toString());
		else if (tipUsluge == UslugeNaPasosima.IZDAVANJE_BRZOG_PASOSA)         pasos = new BrziPasos(getVrijemeTrajanja(),this.toString());
		else if (tipUsluge == UslugeNaPasosima.IZDAVANJE_DIPLOMATSKOG_PASOSA)  pasos = new DiplomatskiPasos(getVrijemeTrajanja(),this.toString());
		
		return true;
	}
	
	public int getVrijemeTrajanja(){
		return 10;
	}
	
	@Override
	public String toString(){
		return ime + " " + prezime + jmbg;
	}
	
	public boolean prioritet(){
		if (tipUsluge == UslugeNaPasosima.IZDAVANJE_DIPLOMATSKOG_PASOSA) return true;
		return false;
	}
	
}