import java.nio.file.*;
import java.util.*;
import java.io.*;

public class Kopiskener extends EksterniUredjaj{
	
	public Kopiskener(String naziv, String proizvodjac){
		super(naziv, proizvodjac);
	}
	
	@Override
	public boolean radiSaFolderima(){
		return true;
	}
	@Override
	public boolean radiSaFajlovima(){
		return true;
	}
	
	@Override
	public void obrada(Path pathToFolderOrFile){
		
		if (!blokiran()){
			
			long startTime = System.nanoTime();
			
			if (pathToFolderOrFile.toFile().isDirectory()) {
				
				try{
					System.out.println(this + "skeniranje " + pathToFolderOrFile + "...");
					long numberOdElements = Files.walk(pathToFolderOrFile)	// Stream<Path>
												 .count();
					System.out.println(this + "skeniranje zavrseno. Broj foldera/fajlova = " + numberOdElements);
				}catch(IOException e){
					e.printStackTrace();
				}finally{
					brojRadnihSekundi += System.nanoTime() - startTime;
				}
			}
			else if (pathToFolderOrFile.toFile().isFile()){
				
				System.out.println(this + "kopiranje " + pathToFolderOrFile + "...");
				
				int index1 = pathToFolderOrFile.toString().lastIndexOf('/');
				int index2 = pathToFolderOrFile.toString().lastIndexOf('.');
				String name = pathToFolderOrFile.toString().substring(index1+1, index2);
				String location = pathToFolderOrFile.toString().substring(0, index1);
				String extension = pathToFolderOrFile.toString().substring(index2+1, pathToFolderOrFile.toString().length() - 1);
				
				try{
					Files.copy(pathToFolderOrFile, Paths.get(location+"/COPY-"+name+"."+extension));
				}catch(IOException e){
					e.printStackTrace();
				}finally{
					brojRadnihSekundi += System.nanoTime() - startTime;
				}
				
				Kopiraparat.brojKopiranihFajlova++;
				System.out.println("Zavrseno kopiranje. Broj kopiranih fajlova = " + Kopiraparat.brojKopiranihFajlova);
			
			}
		}else 
			blokiranPoruka();
	}
	
}