import java.io.*;
import java.util.*;
import java.nio.file.*;
import java.util.stream.*;

public class FileSearcher{
	
	public static void search(String DIR, String COPY_DIR, String ekstenzija) throws Exception{
	
	try{
		List<Path> l = 
		Files.walk(Paths.get(DIR))
			.filter(path -> path.toFile().getName().endsWith("."+ekstenzija))
			.collect(Collectors.toList());
			
		System.out.println("Rezultat pretrage : listu sa putanjom do fajlova koji imaju navedenu ekstenziju :");
		l.forEach(path -> System.out.println(path.toFile().getAbsolutePath()));
		System.out.println("Broj pojavljivanja fajlova = "+l.size());
		
		
		l.stream()
			.forEach(path-> { 
								try{
									Files.copy(path, Paths.get( COPY_DIR+File.separator+path.toFile().getName() ) );
								}catch(Exception e){} 
							} );

	}catch(Exception e){}
	}
	
	public static void main(String... args)throws Exception{
		
		String DIR = "", COPY_DIR = "", ekstenzija = "";
		
		for(int i = 0; i < args.length; i++){
			if ("-d".equals(args[i])) DIR = args[i+1];
			if ("-c".equals(args[i])) COPY_DIR = args[i+1];
			if ("-e".equals(args[i])) ekstenzija = args[i+1];
		}
		
		search(DIR,COPY_DIR,ekstenzija);
	}
	
}