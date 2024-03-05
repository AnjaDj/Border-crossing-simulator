import java.util.stream.*;
import java.nio.file.*;
import java.util.*;
import java.io.*;

public class Stampac extends EksterniUredjaj{
	
	public Stampac(String naziv, String proizvodjac){
		super(naziv, proizvodjac);
	}
	
	@Override
	public boolean radiSaFolderima(){
		return false;
	}
	@Override
	public boolean radiSaFajlovima(){
		return true;
	}
	@Override
	public void obrada(Path pathToFolder){
		
		if (!blokiran()){
			long startTime = System.nanoTime();
			try{
				System.out.println(this + "stampanje txt fajlova" + pathToFolder + "...");
				
				List<Path> tekstualniFajlovi = Files.walk(pathToFolder)	// Stream<Path>
													 .filter(path -> path.toString().endsWith(".txt"))
													 .toList();
													 
				for(Iterator<Path> it = tekstualniFajlovi.iterator(); it.hasNext(); ){
					
					Path path = it.next();
					System.out.print("  " + path);
					if (path.toFile().length() <= 2000) {
						System.out.println("  sadrzaj :");
						try(Stream<String> stream = Files.lines(path)){
							stream.forEach(System.out::println);
						}
					}
					System.out.println();
					
				}
				
			}catch(IOException e){
				e.printStackTrace();
			}finally{
				brojRadnihSekundi += System.nanoTime() - startTime;
			}
		}else 
			blokiranPoruka();
	}
}