import java.io.*;
import java.util.*;
import java.nio.file.*;
import java.util.stream.*;

public class Simulacija{
	
	private static final Random rand = new Random();
	
	private static List<Osoba> kreiranjeKolekcijeOsoba(){
		
		List<Osoba> kolekcijaOsoba = new ArrayList<>();
		
		for(int i = 1; i <= 10; i++)
			kolekcijaOsoba.add(new OdraslaOsoba("Ime_O"+i, "Prezime_O"+i, "jmbg_O"+i));
		
		for(int i = 1; i <= 7; i++)
			kolekcijaOsoba.add(new MaloljetnaOsoba("Ime_D"+i, "Prezime_D"+i, "jmbg_D"+i, new OdraslaOsoba("Ime_R"+i, "Prezime_R"+i, "jmbg_R"+i)));
		
		kolekcijaOsoba.add(new MaloljetnaOsoba("Ime_D8", "Prezime_D8", "jmbg_D8", null));
		kolekcijaOsoba.add(new MaloljetnaOsoba("Ime_D9", "Prezime_D9", "jmbg_D9", null));
		kolekcijaOsoba.add(new MaloljetnaOsoba("Ime_D10", "Prezime_D10", "jmbg_D10", null));
		
		int i = 0;
		
		while(i < 20){
			kolekcijaOsoba.get(i).setTipUsluge(UslugeNaPasosima.PONISTAVANJE_VAZECEG_PASOSA);
			kolekcijaOsoba.get(i).setNovac(rand.nextInt(1000));
			
			kolekcijaOsoba.get(i+1).setTipUsluge(UslugeNaPasosima.IZDAVANJE_OBICNOG_PASOSA);
			kolekcijaOsoba.get(i).setNovac(rand.nextInt(1000));
			
			kolekcijaOsoba.get(i+2).setTipUsluge(UslugeNaPasosima.IZDAVANJE_BRZOG_PASOSA);
			kolekcijaOsoba.get(i).setNovac(rand.nextInt(1000));
			
			kolekcijaOsoba.get(i+3).setTipUsluge(UslugeNaPasosima.IZDAVANJE_DIPLOMATSKOG_PASOSA);
			kolekcijaOsoba.get(i).setNovac(rand.nextInt(1000));
			
			i += 4;
		}
		
		Collections.shuffle(kolekcijaOsoba);
		return kolekcijaOsoba;
	}
	
	public static void main(String[] args){
		
		PriorityQueue<Osoba> red = new PriorityQueue(Comparator.comparing( Osoba::prioritet ).reversed());
		red.addAll(kreiranjeKolekcijeOsoba());
		
		Salter.RED = red;
		
		Salter salter1 = new Salter(1);
		Salter salter2 = new Salter(2);
		
		salter1.start();
		salter2.start();
		
	}
}