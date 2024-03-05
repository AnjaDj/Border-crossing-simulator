import java.util.*;
import java.io.*;

public class Klijent extends Thread{
	
	public Random random = new Random();
	public String imeKlijenta;
	public File fajlKlijent;
	public List<String> zainteresovanZaVrstuNekeretnine;
	
	public Klijent(String imeKlijenta, List<String> zainteresovanZaVrstuNekeretnine, File fajlKlijent){
		this.imeKlijenta = imeKlijenta;
		this.zainteresovanZaVrstuNekeretnine = zainteresovanZaVrstuNekeretnine;
		this.fajlKlijent = fajlKlijent;
	}
	
	@Override
	public void run(){
		try{
			
			try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(fajlKlijent,true)))){
				pw.print(imeKlijenta+",");
				
				for(String str : zainteresovanZaVrstuNekeretnine)
					pw.print(str+",");
				pw.println();
			}catch(Exception e){}
			
		while(Simulacija.KRAJ == false){
			
			System.out.println("Klijent "+this+" razgleda ponudu agencije...");
			
			synchronized(Agencija.NOVA_PONUDA_NOTIFY){
				Agencija.NOVA_PONUDA_NOTIFY.wait();
			}
			
			if ( ((random.nextInt(11)) < 20) && ( mozeDaKupi(Agencija.aktuelnaPonuda) ) ){
				
				synchronized(Agencija.aktuelnaPonuda){
				try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(fajlKlijent,true)))){
					pw.println("Kupljena nekretnina : "+Agencija.aktuelnaPonuda);
				}
				}
				
			}else 
				synchronized(Agencija.NOVA_PONUDA_NOTIFY){
				Agencija.NOVA_PONUDA_NOTIFY.wait();
			}
	
		}
		
		}catch(Exception e){}
		
	}
	
	private boolean mozeDaKupi(Nekretnina nekretnina){return true; } 
	
}