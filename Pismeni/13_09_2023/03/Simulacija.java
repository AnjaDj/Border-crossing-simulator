import java.io.*;
import java.util.*;
import java.nio.file.*;
import java.util.stream.*;

public class Simulacija{
	
	private static List<Knjiga> kreiranjeBiblioteke1(){
		List<Knjiga> biblioteka = new LinkedList<>();
		
		biblioteka.add(new Knjiga( Zanr.FIKCIJA, Povez.TVRDI, "Naslov1", "Pisac1", 2001, 198 ));
		biblioteka.add(new Knjiga( Zanr.FIKCIJA, Povez.MEKI, "Naslov2", "Pisac2", 2004, 563 ));
		biblioteka.add(new Knjiga( Zanr.PUTOPIS, Povez.TVRDI, "Naslov3", "Pisac3", 2009, 745 ));
		biblioteka.add(new Knjiga( Zanr.PUTOPIS, Povez.MEKI, "Naslov4", "Pisac4", 2008, 264 ));
		biblioteka.add(new Knjiga( Zanr.PUTOPIS, Povez.TVRDI, "W_Naslov5", "Pisac5", 1998, 136 ));
		biblioteka.add(new Knjiga( Zanr.PUTOPIS, Povez.TVRDI, "A_Naslov5", "Pisac5", 1998, 136 ));
		biblioteka.add(new Knjiga( Zanr.PUTOPIS, Povez.TVRDI, "Z_Naslov5", "Pisac5", 1998, 136 ));
		biblioteka.add(new Knjiga( Zanr.PUTOPIS, Povez.TVRDI, "K_Naslov5", "Pisac5", 1998, 136 ));
		biblioteka.add(new Knjiga( Zanr.PUTOPIS, Povez.TVRDI, "S_Naslov5", "Pisac5", 1998, 136 ));
		biblioteka.add(new Knjiga( Zanr.PUTOPIS, Povez.TVRDI, "M_Naslov5", "Pisac5", 1998, 136 ));
		biblioteka.add(new Knjiga( Zanr.PUTOPIS, Povez.MEKI, "Naslov6", "Pisac6", 1963, 963 ));
		biblioteka.add(new Knjiga( Zanr.BELETRISTIKA, Povez.TVRDI, "Naslov7", "Pisac7", 1944, 786 ));
		biblioteka.add(new Knjiga( Zanr.BELETRISTIKA, Povez.MEKI, "Naslov8", "Pisac8", 1893, 369 ));
		biblioteka.add(new Knjiga( Zanr.BELETRISTIKA, Povez.TVRDI, "Naslov9", "Pisac9", 1996, 656 ));
		biblioteka.add(new Knjiga( Zanr.FIKCIJA, Povez.MEKI, "Naslov10", "Pisac10", 1963, 198 ));
		
		return biblioteka;
	}
	
	private static List<Knjiga> kreiranjeBiblioteke2(){
		List<Knjiga> biblioteka = new LinkedList<>();
		
		biblioteka.add(new Knjiga( Zanr.PUTOPIS, Povez.MEKI, "Naslov11", "Pisac11", 1964, 555 ));
		biblioteka.add(new Knjiga( Zanr.FIKCIJA, Povez.MEKI, "Naslov12", "Pisac12", 1972, 163 ));
		biblioteka.add(new Knjiga( Zanr.BELETRISTIKA, Povez.TVRDI, "Naslov13", "Pisac13", 1974, 159 ));
		biblioteka.add(new Knjiga( Zanr.FIKCIJA, Povez.MEKI, "Naslov14", "Pisac14", 1955, 288 ));
		biblioteka.add(new Knjiga( Zanr.PUTOPIS, Povez.TVRDI, "Naslov15", "Pisac15", 1964, 336 ));
		biblioteka.add(new Knjiga( Zanr.FIKCIJA, Povez.MEKI, "Naslov16", "Pisac16", 1893, 913 ));
		biblioteka.add(new Knjiga( Zanr.BELETRISTIKA, Povez.TVRDI, "Naslov17", "Pisac17", 1944, 286 ));
		biblioteka.add(new Knjiga( Zanr.PUTOPIS, Povez.MEKI, "Naslov18", "Pisac18", 1942, 309 ));
		biblioteka.add(new Knjiga( Zanr.BELETRISTIKA, Povez.TVRDI, "Naslov19", "Pisac19", 1996, 206 ));
		biblioteka.add(new Knjiga( Zanr.PUTOPIS, Povez.TVRDI, "Naslov20", "Pisac20", 1949, 1980 ));
		
		return biblioteka;
	}
	
	private static void print(List<Knjiga> l){
		for( Iterator<Knjiga> it = l.iterator(); it.hasNext(); )
			System.out.println("   "+it.next());
		System.out.println();
	}
	
	public static void main(String[] args){
		
		List<Knjiga> biblioteka1 = kreiranjeBiblioteke1();
		List<Knjiga> biblioteka2 = kreiranjeBiblioteke2();
		
		System.out.println("a. Spojiti 2 grupe knjiga u novu listu tako sto se spajaju one sa tvrdim povezom, broj stranica > 600");
		List<Knjiga> a = Stream.concat(
										  biblioteka1.stream().filter(knjiga -> knjiga.povez == Povez.TVRDI && knjiga.brojStranica > 600),
										  biblioteka2.stream().filter(knjiga -> knjiga.povez == Povez.TVRDI && knjiga.brojStranica > 600)
										).toList();
		print(a);
		
		System.out.println("b. Sortirati grupu knjiga po godini objavljivanja opadajuce");
		List<Knjiga> b = biblioteka1.stream().
											sorted(Comparator.comparing(Knjiga::getGodinaObjavljivanja).reversed())
											.toList();
		print(b);
		
		System.out.print("c. Sumirati broj stranica svih knjiga koji su FIKCIJA i godinaObjavljivanja > 2000 : ");
		double c = biblioteka1.stream()
										.filter(knjiga -> knjiga.zanr == Zanr.FIKCIJA && knjiga.godinaObjavljivanja > 2000)
										.map(Knjiga::getBrojStranica)
										.reduce(0, (x, y) -> x+y);
		System.out.println(c);
		System.out.println();
		
		
		System.out.print("d. Prikazati prosjecan broj stranica za knjige zadatog zanra : ");
		double d = biblioteka1.stream()
										.filter(knjiga -> knjiga.zanr == Zanr.PUTOPIS)
										.mapToInt(Knjiga::getBrojStranica)
										.average()
										.getAsDouble();
		System.out.println(d);
		System.out.print("   Knjiga koja je najbliza prosjecmom broju stranica : ");
		Knjiga dd =	biblioteka1.stream()
										.min(Comparator.comparing( knjiga-> Math.abs(knjiga.brojStranica-d) ))
										.get();
		System.out.println(dd);
		
		
		System.out.println("e. Prikazati sve knjige zadatog pisca sortirane po naslovu A-Z");
		List<Knjiga> e = biblioteka1.stream()
											.filter(knjiga -> knjiga.pisac.equals("Pisac5"))
											.sorted(Comparator.comparing( knjiga -> knjiga.naslov ))
											.toList();
		print(e);
	
	}
}