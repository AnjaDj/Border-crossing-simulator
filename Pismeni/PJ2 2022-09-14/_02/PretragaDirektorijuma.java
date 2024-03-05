import java.io.*;
import java.util.*;

public class PretragaDirektorijuma{
	
	private static int brojPojavljivanja(String s, String kljucnaRijec){
		
		int brojac = 0;
		
		for(int i = 0; i < s.length(); i++){
		
			if ( s.regionMatches(i,kljucnaRijec,0,kljucnaRijec.length()) ){
				brojac++;
				i += kljucnaRijec.length();
			} 
		
		}
		
		return brojac;
	}
	
	private static int traziKljucnuRijecUDatoteci(File file, String kljucnaRijec){
		
		int brojac = 0;
		
		try(BufferedReader br = new BufferedReader(new FileReader(file))){
			
			String line = br.readLine();
			
			while(line != null){
				
				int temp = brojPojavljivanja(line,kljucnaRijec);
				brojac += temp;
				
				line = br.readLine();
			}
			
		}catch(IOException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		return brojac;
	}
	
	private static void algoritam(File direktorijumZaPretragu, Map<File,Integer> datotekeUKojimaJePronadjenaKljucnaRijec,String kljucnaRijec,String ekstenzija){
		
		File[] sadrzajDirektorijumaZaPretragu = direktorijumZaPretragu.listFiles();
		
		for(File file : sadrzajDirektorijumaZaPretragu){
			
			if(file.isFile()) {
				
				if (file.getName().endsWith("."+ekstenzija)){
					int brojPojavljivanja = traziKljucnuRijecUDatoteci(file,kljucnaRijec);
					if (brojPojavljivanja > 0) datotekeUKojimaJePronadjenaKljucnaRijec.put(file,(Integer)brojPojavljivanja);
				}
				
			}
			else 
				algoritam(file,datotekeUKojimaJePronadjenaKljucnaRijec,kljucnaRijec,ekstenzija);
			
		}
		
	}
	
	public static void main(String[] args){
		
		System.out.print("----------> PRETRAGA DIREKTORIJUMA : ");
		
		String putanja = "", kljucnaRijec = "", ekstenzija = "";
		for(int i = 0; i < args.length; i++){
			if ("-s".equals(args[i])) putanja = args[i+1];
			if ("-w".equals(args[i])) kljucnaRijec = args[i+1];
			if ("-e".equals(args[i])) ekstenzija = args[i+1];
		}
		
		File direktorijumZaPretragu = new File(putanja);
		Map<File,Integer> datotekeUKojimaJePronadjenaKljucnaRijec = new HashMap<>();
		
		if(direktorijumZaPretragu.exists() && direktorijumZaPretragu.isDirectory()){
			
			System.out.println(direktorijumZaPretragu.getAbsolutePath());
			
			algoritam(direktorijumZaPretragu,datotekeUKojimaJePronadjenaKljucnaRijec,kljucnaRijec,ekstenzija);
			
			datotekeUKojimaJePronadjenaKljucnaRijec.forEach((file,broj)->{
				System.out.println(file.getAbsolutePath()+"  ->  "+broj);
			});
			
			
		}else
			System.out.println("Putanja neispravna!");
		
	}
	
}

/*
	File(String pathname) prilikom cega pathname moze predstavljati :
		- apsolutnu putanju
		- relativnu putanju u odnosu na tekuci direktorijum
*/