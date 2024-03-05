import java.util.stream.*;
import java.nio.file.*;
import java.util.*;
import java.io.*;

public class Kopiraparat extends EksterniUredjaj{
	
	public static int brojKopiranihFajlova = 0;
	
	public Kopiraparat(String naziv, String proizvodjac){
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
	public void obrada(Path pathToFile){
		
		if (!blokiran()){
			long startTime = System.nanoTime();
			System.out.println(this + "kopiranje " + pathToFile + "...");
			
			int index1 = pathToFile.toString().lastIndexOf('/');
			int index2 = pathToFile.toString().lastIndexOf('.');
			String name = pathToFile.toString().substring(index1+1, index2);
			String location = pathToFile.toString().substring(0, index1);
			String extension = pathToFile.toString().substring(index2+1, pathToFile.toString().length() - 1);
			
			try{
				Files.copy(pathToFile, Paths.get(location+"/COPY-"+name+"."+extension));
			}catch(IOException e){
				e.printStackTrace();
			}finally{
				brojRadnihSekundi += System.nanoTime() - startTime;
			}
			
			brojKopiranihFajlova++;
			System.out.println(this + " zavrseno kopiranje. Broj kopiranih fajlova = " + brojKopiranihFajlova);
		}else
			blokiranPoruka();
	}
}