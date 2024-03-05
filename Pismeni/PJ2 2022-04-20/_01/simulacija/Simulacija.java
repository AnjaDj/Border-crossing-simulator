package simulacija;
import vozila.*;
import staza.*;

public class Simulacija{
	
	public static Polje[] stazaKaT2 = new Polje[3]; 
	public static Polje[] stazaKaT1 = new Polje[3];
	
	public static void main(String[] args){
		
		for(int i = 0; i < 3; i++){
			stazaKaT1[i] = new Polje();
			stazaKaT2[i] = new Polje();
		}
		
		Staza staza = new Staza(15);
		
		Vozilo.staza = staza.getStaza();
		Vozilo.velicinaStaze = staza.getVelicinaStaze();
		
		Automobil a1 = new Automobil(new Motor("Motor1","DIZEL"),"Autic1");
		Automobil a2 = new Automobil(new Motor("Motor2","DIZEL"),"Autic2");
		Automobil a3 = new Automobil(new Motor("Motor3","DIZEL"),"Autic3");
		Automobil a4 = new Automobil(new Motor("Motor4","DIZEL"),"Autic4");
		Automobil a5 = new Automobil(new Motor("Motor5","DIZEL"),"Autic5");
		
		a1.start();
		a2.start();
		a3.start();
		a4.start();
		a5.start();
		
	}
}