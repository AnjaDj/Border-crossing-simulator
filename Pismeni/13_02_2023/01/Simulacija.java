import java.nio.file.*;
import java.util.*;
import java.io.*;

public class Simulacija{
	
	public static void main(String[] args){
		
		Skener skener1   = new Skener("Skener","P1");
		Stampac stampac1 = new Stampac("Stampac","P1");
		Kopiraparat ka1  = new Kopiraparat("Kopiraparat","P1");
		Kopiskener ks1   = new Kopiskener("Kopiskener","P1");
		Kombo k1         = new Kombo("Kombo","P1");
		
		Skener skener2   = new Skener("Skener","P1");
		Stampac stampac2 = new Stampac("Stampac","P1");
		Kopiraparat ka2  = new Kopiraparat("Kopiraparat","P1");
		Kopiskener ks2   = new Kopiskener("Kopiskener","P1");
		Kombo k2         = new Kombo("Kombo","P1");
		
		Skener skener3   = new Skener("Skener","P1");
		Stampac stampac3 = new Stampac("Stampac","P1");
		Kopiraparat ka3  = new Kopiraparat("Kopiraparat","P1");
		Kopiskener ks3   = new Kopiskener("Kopiskener","P1");
		Kombo k13        = new Kombo("Kombo","P1");
		
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		
		EksterniUredjaj.ROOT_DIR = "";
		
		skener1.start();
		
	}
}