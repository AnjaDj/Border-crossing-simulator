import java.util.*;
import java.io.*;

public class Main {
	
	public static String NAZIV_DOKUMENTA = "emailAdrese.txt";
	public static String DOKUMENT_POZIVNICE = "pozivnice";
	
	public static void main(String[] args) {
		
		ObradaPozivnice obradaPozivnice = new ObradaPozivnice(new File(DOKUMENT_POZIVNICE));
		obradaPozivnice.start();
		
		File file = new File(DOKUMENT_POZIVNICE);
		if(!file.exists()) {
			file.mkdirs();
		}
		
		try {
			BufferedReader bf = new BufferedReader(new FileReader(new File(NAZIV_DOKUMENTA)));
			
			String line = "";
			
			while((line = bf.readLine()) != null) {
				String[] splitImePartnera = line.split("#");
				String imePartnera = splitImePartnera[0];
				
				String pozivnica = pozivnica(imePartnera);
				
				PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File(DOKUMENT_POZIVNICE + File.separator + line + ".txt"))));
				pw.println(pozivnica);
				pw.close();
			}
			
			bf.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
	}
	
	public static String pozivnica(String ime) {
		int size = "Pozivamo vas na svecanu proslavu godisnjice nase kompanije koja ce se odrzati u".length();
		int sredina = size / 2;
		String pozivnica = "";
		String pocetnaRecenica = "Postovani partneru " + ime + " kompanije Java";
		for(int i=0;i<sredina - pocetnaRecenica.length() / 2; i++ ) {
			pozivnica += " ";
		}
		pozivnica += pocetnaRecenica + System.getProperty("line.separator");
		pozivnica += System.getProperty("line.separator");
		pozivnica += "Pozivamo vas na svecanu proslavu godisnjice nase kompanije koja ce se odrzati u" + System.getProperty("line.separator");
		pozivnica += "nasim prostorijama" + System.getProperty("line.separator");
		pozivnica += System.getProperty("line.separator");
		String uPetak = "u petak, 05.07.2019.godine, sa pocetkom u 18:00 casova.";
		for(int i=0;i<sredina - uPetak.length() / 2; i++ ) {
			pozivnica += " ";
		}
		pozivnica += uPetak + System.getProperty("line.separator");
		pozivnica += System.getProperty("line.separator");
		String zvjezdice = "*****************************";
		for(int i=0;i<sredina - zvjezdice.length() / 2; i++ ) {
			pozivnica += " ";
		}
		pozivnica += zvjezdice;
		pozivnica += System.getProperty("line.separator");
		String zadnjaRecenica = "Java kompanija, jun 2019.";
		for(int i=0;i<sredina - zadnjaRecenica.length() / 2; i++ ) {
			pozivnica += " ";
		}
		pozivnica += zadnjaRecenica + System.getProperty("line.separator");	
		return pozivnica;
	}
	
}