import java.util.stream.*;
import java.nio.file.*;
import java.util.*;
import java.io.*;

public class Skener extends EksterniUredjaj{
	
	public Skener(String naziv, String proizvodjac){
		super(naziv, proizvodjac);
	}
	
	@Override
	public boolean radiSaFolderima(){
		return true;
	}
	@Override
	public boolean radiSaFajlovima(){
		return false;
	}
	@Override
	public void obrada(Path pathToFolder){
		
		if (!blokiran()){
			long startTime = System.nanoTime();
			try{
				System.out.println(this + " skeniranje " + pathToFolder + "...");
				long numberOdElements = Files.walk(pathToFolder)	// Stream<Path>
											 .count();
				System.out.println(this + " skeniranje " + pathToFolder + "zavrseno. Broj pronadjenih foldera/fajlova = " + numberOdElements);
			}catch(IOException e){
				e.printStackTrace();
			}finally{
				brojRadnihSekundi += System.nanoTime() - startTime;
			}
		}else 
			blokiranPoruka();
	}
	
	public void filtriranje(Path pathToFolder, String ekstenzija){
		
		if (!blokiran()){
			long startTime = System.nanoTime();
			try{
				System.out.println(this + "filtriranje fajlova unutar " + pathToFolder + "po ."+ekstenzija+" ekstenziji : ");
				Files.walk(pathToFolder)	// Stream<Path>
					 .filter(path -> path.toString().endsWith(ekstenzija))
					 .forEach(path -> System.out.println("  "+path.toString()));
			}catch(IOException e){
				e.printStackTrace();
			}finally{
				brojRadnihSekundi += System.nanoTime() - startTime;
			}
		} else
			blokiranPoruka();
	}
}