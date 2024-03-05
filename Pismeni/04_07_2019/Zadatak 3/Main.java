import java.util.*;
import java.util.function.*;

public class Main {
		
	public static <T> T metoda(ArrayList<T> listaObjekata, ArrayList<Predicate<T>> listaPredikata, int pocetak, int kraj) {
		if (pocetak > listaObjekata.size() || kraj > listaObjekata.size() || pocetak < 0 || kraj < 0 || pocetak > kraj) {
			return null;
		}
		
		ArrayList<T> listaObjekataKojiZadovoljavajuUslove = new ArrayList<T>();
		
		for(int i=pocetak;i<kraj;i++) {
			T objekat = listaObjekata.get(i);
			boolean ok = true;
			for(int j = 0; j < listaPredikata.size() && ok; j++) {
				ok = listaPredikata.get(j).test(objekat);
			}
			if(ok) {
				listaObjekataKojiZadovoljavajuUslove.add(objekat);
				//System.out.println(objekat.toString() + System.getProperty("line.separator") + "HashCode: " + objekat.hashCode());
			}
		}
		
		T objekat = listaObjekataKojiZadovoljavajuUslove.stream().max((a,b) -> a.hashCode() - b.hashCode()).orElse(null);
		return objekat;
	}
	
	public static void main(String[] args) {
		Osoba osoba1 = new Osoba("Marko","Markovic",1996);
		Osoba osoba2 = new Osoba("Nikola","Nikolic",1997);
		Osoba osoba3 = new Osoba("Jovan","Jovanovic",1998);
		Osoba osoba4 = new Osoba("Mirko","Mirkovic",1999);
		Osoba osoba5 = new Osoba("Bojan","Bojanovic",2000);
		
		Predicate<Osoba> predicate1 = (osoba) -> osoba.ime.length() >= 5;
		Predicate<Osoba> predicate2 = (osoba) -> osoba.prezime.length() >= 5;
		Predicate<Osoba> predicate3 = (osoba) -> osoba.godiste >= 1997;
		
		ArrayList<Osoba> osobe = new ArrayList<Osoba>();
		osobe.add(osoba1);
		osobe.add(osoba2);
		osobe.add(osoba3);
		osobe.add(osoba4);
		osobe.add(osoba5);
		
		ArrayList<Predicate<Osoba>> predikati = new ArrayList<Predicate<Osoba>>();
		predikati.add(predicate1);
		predikati.add(predicate2);
		predikati.add(predicate3);
		
		Osoba osobaSaNajvecimHashCode = metoda(osobe,predikati,0,5);
		System.out.println("=====================================");
		System.out.println(osobaSaNajvecimHashCode);
		System.out.println("=====================================");
	}
}