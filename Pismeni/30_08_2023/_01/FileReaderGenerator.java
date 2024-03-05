import java.nio.file.*;
import java.util.*;

public class FileReaderGenerator extends Thread{
	
	public static List<Nekretnina> PONUDA_NEKRETNINA = new ArrayList<>();
	public static final Object PONUDA_NEKRETNINA_LOCK = new Object();
	
	@Override
	public void run(){
		try{
		
			List<String> linijeIzUlazneDatoteke = Files.readAllLines(Paths.get("Prvi_zadatak.txt"));
			
			for(Iterator<String> it = linijeIzUlazneDatoteke.iterator(); it.hasNext();){
				
				String linijaZaObradu = it.next();
				
					String[] parts = linijaZaObradu.split("#");
					Nekretnina nekretnina = null;
					
					if("STAN".equals(parts[0])) {
						
						nekretnina = new Stan(parts[1], Integer.valueOf(parts[2]), Integer.valueOf(parts[3]), parts[4], Integer.valueOf(parts[5]), Integer.valueOf(parts[6]));
					}
					else if("KUCA".equals(parts[0])) {
						
						nekretnina = new Kuca(parts[1], Integer.valueOf(parts[2]), Integer.valueOf(parts[3]), parts[4], Integer.valueOf(parts[5]), Integer.valueOf(parts[6]));
					}
					else if("POSLOVNI".equals(parts[0])) {
						
						nekretnina = new PoslovniProstor(parts[1], Integer.valueOf(parts[2]), Integer.valueOf(parts[3]), parts[4]);
					}
					
					if (nekretnina != null){
							synchronized(PONUDA_NEKRETNINA_LOCK){
								PONUDA_NEKRETNINA.add(nekretnina);
							}
							System.out.println("Dodavanje nove nekretnine"+ nekretnina + "u ponudu agencije...");	
					}		
							
					sleep(1000);
							
			}
			
			
		}catch(Exception e){e.printStackTrace();}
		
		Simulacija.KRAJ = true;
	}
	
	
}