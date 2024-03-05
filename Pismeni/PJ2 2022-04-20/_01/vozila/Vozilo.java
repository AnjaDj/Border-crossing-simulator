package vozila;
import simulacija.*;
import staza.*;

public class Vozilo extends Thread{
	
	public static Polje[] staza;				// staza[i] = Polje OBJEKAT                                                                      
	public static int velicinaStaze;
	
	private String vozac, identifikator;
	private int brojPutnika;
	private double cijena;
	private Motor motor;
	
	private Polje[] stazaKaTerminalu(){
		if(this instanceof MogucnostPrevozaTereta || this instanceof MogucnostPrevozaVecegBrojaPutnika) return Simulacija.stazaKaT2;
		return Simulacija.stazaKaT1;
	}
	
	public Vozilo(Motor motor, String identifikator, String vozac, double cijena, int brojPutnika){
		this.identifikator = identifikator;
		this.brojPutnika = brojPutnika;
		this.cijena = cijena;
		this.vozac = vozac;
		this.motor = motor;
	}
	public String getIdentifikator(){return identifikator;}
	public int getBrojPutnika(){return brojPutnika;}
	public double getCijena(){return cijena;}
	public String getVozac(){return vozac;}
	public Motor getMotor(){return motor;}
	
	@Override 
	public void run(){
		
		for(int i = -1; i < velicinaStaze-1; i++){
			
			if(staza[i+1].getNaPolju() != null) // polje ispred nas je zauzeto-> cekaj
				synchronized(staza[i+1]){
					try{ staza[i+1].wait(); }catch(InterruptedException e){e.printStackTrace(); throw new RuntimeException(e);} }
			
			synchronized(staza[i+1]){
				if(staza[i+1].getNaPolju() == null){
					staza[i+1].setNaPolju(this); System.out.println("staza["+(i+1)+"] = "+this);
					
					if(i != -1) 
						synchronized(staza[i]){
							staza[i].setNaPolju(null);
							staza[i].notifyAll();
						}
				}
				else i--;
			}
		}
		
		Polje[] stazaKaTerminalu = stazaKaTerminalu(); 
		
		for(int i = 0; i < 3; i++){
			
			if (stazaKaTerminalu[i].getNaPolju() == null){
				
				synchronized(stazaKaTerminalu[i]){ 
				
					stazaKaTerminalu[i].setNaPolju(this); System.out.println("stazaKaterminalu["+i+"] = "+this);
					if(i == 0) synchronized(staza[14]){
								staza[14].setNaPolju(null);
								staza[14].notifyAll();
							 }
							 
					if(i != 0) synchronized(stazaKaTerminalu[i-1]){ 
								stazaKaTerminalu[i-1].setNaPolju(null);
								}	
				}
					
			}
			
			else i--;
			
			
		}
		
		
	}
	
	
	@Override
	public String toString(){
		return "Vozilo ID:"+identifikator+" "+motor;
	}
}