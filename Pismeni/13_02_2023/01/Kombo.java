import java.util.stream.*;
import java.nio.file.*;
import java.util.*;
import java.io.*;

public class Kombo extends EksterniUredjaj{
	
	public Kombo(String naziv, String proizvodjac){
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
	@Override public void obrada(Path pathToFolderOrFile){
		
		if (!blokiran()){
			long startTime = System.nanoTime();
			
			if (pathToFolderOrFile.toFile().isDirectory()){
				try{
						System.out.println(this + " skeniranje " + pathToFolderOrFile + "...");
						long numberOdElements = Files.walk(pathToFolderOrFile)	// Stream<Path>
													 .count();
						System.out.println(this + " skeniranje " + pathToFolderOrFile + "zavrseno. Broj pronadjenih foldera/fajlova = " + numberOdElements);
						
						System.out.println(this + "stampanje txt fajlova" + pathToFolderOrFile + "...");
						
						List<Path> tekstualniFajlovi = Files.walk(pathToFolderOrFile)	// Stream<Path>
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
			}else if (pathToFolderOrFile.toFile().isFile()){
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
				System.out.println(this + " zavrseno kopiranje. Broj kopiranih fajlova = " + Kopiraparat.brojKopiranihFajlova);
			}
			
			
		} else
			blokiranPoruka();
	}
	
	
	
	
	
	
	
}