import java.nio.file.*;
import java.util.*;
import java.io.*;

public class Simulacija{
	
	public static boolean KRAJ = false;
	
	public static void main(String[] args){
		
		List<Klijent> kolekcijaKlijenata = new ArrayList<>();
			kolekcijaKlijenata.add(new Klijent("Uros",List.of("STAN","KUCA"),new File("uros.txt")));
			kolekcijaKlijenata.add(new Klijent("Viktorija",List.of("STAN","KUCA"),new File("viktorija.txt")));
			kolekcijaKlijenata.add(new Klijent("Gloria",List.of("STAN"),new File("gloria.txt")));
			kolekcijaKlijenata.add(new Klijent("Vukasin",List.of("POSLOVNI"),new File("vukasin.txt")));
			kolekcijaKlijenata.add(new Klijent("Nevena",List.of("POSLOVNI"),new File("nevena.txt")));	
			
		FileReaderGenerator fileReaferGenerator = new FileReaderGenerator();
			fileReaferGenerator.start();
			
			for(Klijent klijent : kolekcijaKlijenata) {
				klijent.start();	
			}	
			
			Agencija agencija = new Agencija(); 
			agencija.start();	
		
	}
	
}