import java.util.stream.*;
import java.nio.file.*;
import java.util.*;
import java.io.*;

public abstract class EksterniUredjaj extends Thread{
	
	public static String ROOT_DIR;
	public static boolean STOP = false;
	public static final int MAX = 2000000000; // 2ns * 1 000 000 000 = 2 s
	public String naziv, proizvodjac;
	public int brojRadnihSekundi = 0;
	
	public EksterniUredjaj(String naziv, String proizvodjac){
		this.proizvodjac = proizvodjac;
		this.naziv = naziv;
	}
	
	@Override
	public String toString(){
		return naziv + " " + proizvodjac + " " + brojRadnihSekundi;
	}
	
	public abstract boolean radiSaFolderima();
	public abstract boolean radiSaFajlovima();
	public abstract void obrada(Path pathToFolderOrFile);
	
	public boolean blokiran(){
		if (brojRadnihSekundi >= MAX)
			return true;
		else 
			return false;
	}
	public void blokiranPoruka(){
		System.out.println(this+" BLOKIRAN! "+" brojRadnihSekundi = "+ brojRadnihSekundi);
	}
	
	@Override
	public void run(){
		
		//while(!STOP && !blokiran()){
			obrada(ROOT_DIR);
		//}
		
	}
}