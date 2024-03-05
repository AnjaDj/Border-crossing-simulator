import java.io.*;
import java.util.*;

public class DailyQuotes{
	
	public static boolean END = false;
	public static int BROJ_NOVIH_FAJLOVA = 0;
	
	public static void main(String... args){
		
		long startTime = System.nanoTime();
		
		QuoteStorage qs = new QuoteStorage();
		FileSystemReader fsr = new FileSystemReader(qs,"quotes","dan");
		QuoteReader qr = new QuoteReader(qs);
		
		fsr.start();
		qr.start();
		
		Scanner sc = new Scanner(System.in);
		String temp = "";
		while(!"STOP".equals(temp)){
			temp = sc.nextLine();
		}
		if ( "STOP".equals(temp) )
			END = true;
		System.out.println("---");
		try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File("statistic"+File.separator+"rezultat_simulacije.txt"),false)))){
		
			pw.println("Vrijeme trajanja simulacije = "+(System.nanoTime()-startTime));
			pw.println("Pretrazivana tema = dan");
			pw.println("Broj novih fajlova = "+BROJ_NOVIH_FAJLOVA);
		}catch(Exception e){}
		System.out.println("***");
	}
	
}