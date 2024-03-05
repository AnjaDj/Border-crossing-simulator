import java.io.*;
import java.nio.file.*;
import java.util.stream.*;
import java.util.*;
import java.util.function.*;

public class Simulacija{
	
	public static String PUTANJA_DO_FOLDERA, RIJEC;
	
	public static void main(String[] args){
		
		for(int i = 0; i < args.length; i++){
			if ( "-d".equals(args[i]) ) PUTANJA_DO_FOLDERA = args[i+1];
			if ( "-w".equals(args[i]) ) RIJEC = args[i+1];
		}
		List<String> rezultantnaKolekcija = new ArrayList<>();
		rekurzivnaPretraga((new File(PUTANJA_DO_FOLDERA)).listFiles(),rezultantnaKolekcija);
		
		Collections.sort(rezultantnaKolekcija, Comparator.comparing(linija -> {
																					String[] parts = linija.split("=");
																					return Integer.valueOf(parts[1]);
																				}));
		Collections.reverse(rezultantnaKolekcija);	
		
		rezultantnaKolekcija.forEach(System.out::println);																		
		
	}
	
	private static void rekurzivnaPretraga(File[] sadrzajFoldera, List<String> rezultantnaKolekcija){
		
		for(File file : sadrzajFoldera){
			
			if (file.isDirectory()) rekurzivnaPretraga(file.listFiles(),rezultantnaKolekcija);	// folder
			else if (file.isFile() && file.getName().endsWith(".txt")){										// tekstualna datoteka														
				
				try( Stream<String> linijeIzDatoteke = Files.lines(file.toPath())){
					
					int brojPojavljivanja = linijeIzDatoteke.filter(linija -> linija.contains(RIJEC))
															.mapToInt(linija -> {
																					String[] parts = linija.split(RIJEC);
																					return parts.length - 1;
																				})
															.sum();					
					
					if (brojPojavljivanja > 0)
						rezultantnaKolekcija.add(new String(file.getAbsolutePath() +"="+brojPojavljivanja));
					
				}catch(Exception e){e.printStackTrace();}
				
			}
		}
		
	}
}