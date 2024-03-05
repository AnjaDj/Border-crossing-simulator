import java.io.*;
import java.util.*;
import java.nio.file.*;

public abstract class Zaposleni extends Thread{
	
	public static boolean KRAJ = false;
	
	private Random rand = new Random();
	
	public String ime, prezime;
	public int godineRada, cijenaRada;
	public List<String> uradjeniZadaci;
	public boolean uzeoJePauzu = false;
	public long trenutakPauze = rand.nextInt(16) + 5;	// 5 - 20
	
	
	public Zaposleni(String ime, String prezime, int godineRada, int cijenaRada){
		
		this.ime = ime;
		this.prezime = prezime;
		this.godineRada = godineRada;
		this.cijenaRada = cijenaRada;
		this.uradjeniZadaci = new ArrayList<>();
	}
	
	@Override
	public String toString(){
		return ime + " " + prezime + " " + godineRada + " " + cijenaRada;
	}
	
	public abstract String getMessage();
	public abstract long sleepTime();
	
	@Override
	public void run(){
		
		long startTime = System.nanoTime();
		System.out.println("Radnik " + ime + " " + prezime + " pocinje sa radom...");
		int redniBrojPoruke = 0;
		
		try{
			while(!KRAJ){
			
				String poruka = getMessage() + "#" + (++redniBrojPoruke);
				uradjeniZadaci.add(poruka);
				System.out.println( poruka );
				
				if(uzeoJePauzu == false){
					
					long tempTime = (System.nanoTime() - startTime)*1000000000;
					
					if ( tempTime == trenutakPauze ){
						uzeoJePauzu = true;
						Thread.sleep(5000);
					}
					
				}
				Thread.sleep( sleepTime() );
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
	
}