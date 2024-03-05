import java.io.*;
import java.util.*;

public class Takmicenje{
	
	public static String NAZIV_DATOTEKE = "";
	public static int BROJ_NITI;
	public static int BROJ;
	
	public static void main(String[] args){
		
		for(int i = 0; i < args.length; i++){
			if("-br_niti".equals(args[i])) BROJ_NITI = Integer.valueOf(args[i+1]);
			if("-br".equals(args[i])) BROJ = Integer.valueOf(args[i+1]);
			if("-naziv_dat".equals(args[i])) NAZIV_DATOTEKE = args[i+1];
		}
		
		if (BROJ_NITI <= 0 || BROJ <= 0){
			System.out.println("Nevalidan unos pocetnih parametara!");
			return;
		}
	
		try{
			File datoteka = new File(NAZIV_DATOTEKE);
			if (!datoteka.exists()) datoteka.createNewFile();
			try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(datoteka,false)))){
				pw.print(0);
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		
		List<Nit> niti = new ArrayList<>();
		for(int i = 0; i < BROJ_NITI; i++)
			niti.add(new Nit());
		
		for(Nit nit : niti) nit.start();
		
	}
	
}