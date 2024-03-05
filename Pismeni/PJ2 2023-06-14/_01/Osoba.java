import java.util.*;

public abstract class Osoba implements java.io.Serializable{
	
	private static Random random = new Random();
	
	protected String ime, prezime;
	protected int novacNaRaspolaganju;
	
	Osoba(String prezime, String ime){
		
		this.ime = ime;
		this.prezime = prezime;
		this.novacNaRaspolaganju = random.nextInt(521)+80;
	}
	
	public int getNovacNaRaspolaganju(){return novacNaRaspolaganju;}
	public void umanjiIznosNovcaNaRaspolaganju(int iznos){novacNaRaspolaganju = novacNaRaspolaganju-iznos;}
	public abstract int getNovacZaNaplatu();
	public abstract String getID();
	
	@Override
	public String toString(){
		return prezime+" "+ime+" "+novacNaRaspolaganju+"KM";
	}
}