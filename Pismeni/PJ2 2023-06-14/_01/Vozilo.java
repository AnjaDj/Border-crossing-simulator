import java.util.*;

public abstract class Vozilo implements java.io.Serializable{

	private static Random random = new Random();
	
	private Registracija tipRegistracije;
	
	Vozilo(){
		
		Registracija[] uslugeRegistracije  = Registracija.values();
		this.tipRegistracije = uslugeRegistracije[random.nextInt(uslugeRegistracije.length)];
	}
	
	public Registracija getTipRegistracije(){return tipRegistracije;}
	
	public int getTaksa(){return 0;}

}