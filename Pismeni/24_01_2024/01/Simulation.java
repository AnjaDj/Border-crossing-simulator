import java.io.*;
import java.util.*;
import java.nio.file.*;

public class Simulation{
	
	public static void main(String[] args){
		
		String putanjaDoObracunFajlaS = "";
		
		for(int i = 0; i < args.length; i++){
			if ("-p".equals(args[i])) putanjaDoObracunFajlaS = args[i+1];
		}
		
		try{
			long startTime = System.nanoTime();
			
			RadnikProdaje rp = new RadnikProdaje("Ime1","Prezime1",3,2);
			RadnikNabavke rn = new RadnikNabavke("Ime2","Prezime2",5,6);
			Racunovodja rv   = new Racunovodja("Ime3","Prezime3",1,1);
				rv.listaRadnika = Arrays.asList(rp, rn, rv);
				rv.putanjaDoObracunFajla = putanjaDoObracunFajlaS;
			rp.start();
			rn.start();
			rv.start();
			
			String input = "";
			Scanner sc = new Scanner(System.in);
			
			while(!"KRAJ".equals(input)){
				
				input = sc.nextLine();
			}
			
			if( "KRAJ".equals(input) )
				Zaposleni.KRAJ = true;
			
			rp.join();
			rn.join();
			rv.join();
			
			System.out.println("Ukupno vrijeme trajanja simulacije : " + ( (System.nanoTime() - startTime)*1000000000) );
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}