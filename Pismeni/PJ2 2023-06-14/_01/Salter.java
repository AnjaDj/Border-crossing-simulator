import java.util.*;
import java.io.*;

public class Salter extends Thread{
	
	public static final Object LOCK_FILE = new Object();
	public static Queue<Osoba> redOsoba;
	private String ID;
	
	public Salter(String ID){this.ID = ID;}
	
	@Override
	public void run(){
		
		while(!redOsoba.isEmpty()){
			
			Osoba osoba = null;
			
			synchronized(redOsoba){ if (!redOsoba.isEmpty()) osoba = redOsoba.poll(); System.out.println(this+" preuzima osobu "+osoba); }
			
			if(osoba != null){
				
				try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(ID+"_"+osoba.getID()+"_S.txt")))){
					oos.writeObject(osoba);
				}catch(Exception e){e.printStackTrace();}
				
				if (osoba.getNovacNaRaspolaganju() < osoba.getNovacZaNaplatu()) 
					synchronized(LOCK_FILE){
							try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File("osobe_koje_zbog_iznosa_nisu_mogle_izvrsiti_registraciju.txt"),true)))){
								pw.println("Osoba "+osoba+" nije imala dovoljno novca da izvrsi uslugu registracije vozila ("+osoba.getNovacZaNaplatu()+"KM).");
								System.out.println("Osoba "+osoba+" nije imala dovoljno novca da izvrsi uslugu registracije vozila ("+osoba.getNovacZaNaplatu()+"KM).");
							}catch(Exception e){e.printStackTrace();}
					}
				else { 
						System.out.println("Osoba "+osoba+" je imala dovoljno novca da izvrsi uslugu registracije vozila ("+osoba.getNovacZaNaplatu()+"KM).");
						osoba.umanjiIznosNovcaNaRaspolaganju(osoba.getNovacZaNaplatu());
				}
			}
		}
		
	}
	
	@Override
	public String toString(){
		return "Salter_"+ID;
	}
	
}