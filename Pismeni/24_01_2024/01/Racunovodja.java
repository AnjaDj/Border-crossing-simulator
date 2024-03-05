import java.io.*;
import java.util.*;
import java.nio.file.*;

public class Racunovodja extends Zaposleni{
	
	public List<Zaposleni> listaRadnika;
	private boolean obracunKreiran = false;
	public String putanjaDoObracunFajla = "";
	
	public Racunovodja(String ime, String prezime, int godineRada, int cijenaRada){
		super(ime,prezime,godineRada,cijenaRada);
	}
	
	@Override
	public String getMessage(){
		return "Obracun troskova ";
	}
	@Override
	public long sleepTime(){
		return 10000;
	}
	
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
				
				if (!obracunKreiran){
					int temp = 0;
					for(Iterator<Zaposleni> it = listaRadnika.iterator(); it.hasNext(); ){
						if (it.next().uradjeniZadaci.size() >= 10) temp++;
					}
					if (temp == listaRadnika.size()){	// obracun
						obracun();
						obracunKreiran = true;
					}
				}
				
				Thread.sleep( sleepTime() );
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
	
	private void obracun(){
		
		try{
			for(Iterator<Zaposleni> it = listaRadnika.iterator(); it.hasNext(); ){
				
				Zaposleni zaposleni = it.next();
				long iznos = zaposleni.cijenaRada + zaposleni.godineRada;
				Files.writeString(Paths.get(putanjaDoObracunFajla), zaposleni.ime + " " + zaposleni.prezime + iznos , StandardOpenOption.APPEND);
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
}