import java.util.*;

public class PravnoLice extends Osoba implements java.io.Serializable{
	
	private String jib, nazivFirme;
	private List<Vozilo> vozila = null;
	
	public PravnoLice(String prezime, String ime, String nazivFirme, String jib){
		super(prezime,ime);
		this.nazivFirme = nazivFirme;
		this.jib = jib;
	}
	
	public void setVozilaZaRegistraciju(List<Vozilo> vozila){this.vozila = vozila;}
	
	public String getID(){return jib;}
	
	@Override
	public int getNovacZaNaplatu(){
		
		int suma = 0;
		
		for(Vozilo vozilo : vozila)
			suma += vozilo.getTipRegistracije().getCijenaUslugeRegistacije() + vozilo.getTaksa();
		
		return suma;
	}
}