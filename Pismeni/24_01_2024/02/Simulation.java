import java.io.*;
import java.util.*;
import java.nio.file.*;
import java.util.stream.*;

public class Simulation{
	
	public static String putanjaDoRootDIR = "";
	public static String nazivPodFoldera = "";
	
	public static void main(String[] args){
		
		long startTime = System.nanoTime();
		
		for(int i = 0; i < args.length; i++){
			if ("-p".equals(args[i])) putanjaDoRootDIR = args[i+1];
			if ("-s".equals(args[i])) nazivPodFoldera = args[i+1];
		}
		
		
		try( Stream<Path> stream = Files.walk(Paths.get(putanjaDoRootDIR)) ){
			
			
			Map<String, List<Path>> groupedByExtension = 
			stream.filter( path -> path.toFile().isFile() )		// samo fajlovi
				  .collect(Collectors.groupingBy( path -> {
															String fileName = path.toFile().getName();
															int index = fileName.lastIndexOf(".");
															return fileName.substring(index + 1, fileName.length());
					  
				  } ));
			
			Files.createDirectory(Paths.get(putanjaDoRootDIR + "/" + nazivPodFoldera));
				  
			groupedByExtension.forEach( (ekstenzija, listaFajlova) -> {
				try{
					Files.createDirectory( Paths.get(putanjaDoRootDIR + "/" + nazivPodFoldera + "/" + ekstenzija) );

					listaFajlova.forEach( path -> {
						try{
							Files.copy(path, Paths.get(putanjaDoRootDIR + "/" + nazivPodFoldera + "/" + ekstenzija + "/" + path.toFile().getName()) , StandardCopyOption.REPLACE_EXISTING);
						}catch(Exception e){
							e.printStackTrace();
						}
					});
					
				}catch(Exception e){
					e.printStackTrace();
				}
			} );
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		try( Stream<Path> stream = Files.walk( Paths.get(putanjaDoRootDIR + "/" + nazivPodFoldera) ) ){
		
			stream.filter( path -> path.toFile().isDirectory() )		// samo tipski folderi Stream<Path>
					.forEach( path -> {
										File[] temp = path.toFile().listFiles();
										System.out.println("Broj fajlova unutar foldera " + path.toFile().getName() + " " + temp.length);
										long size = 0;
										for(File f : temp)
											size += f.length();
										System.out.println("Velicina foldera " + path.toFile().getName() + " " + size);
										
					} );
		
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("Vrijeme trajanja simulacije : " + (System.nanoTime() - startTime)*1000000000);
	}
}