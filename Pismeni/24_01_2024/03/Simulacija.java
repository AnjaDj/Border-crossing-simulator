import java.io.*;
import java.util.*;
import java.util.stream.*;
import java.util.function.*;

public class Simulacija{
	
	public static void main(String[] args){
		
		List<Knjiga> knjige = new ArrayList<>();
			knjige.add(new Knjiga("Knjiga1","ImeA1","PrezimeA1"));
			knjige.add(new Knjiga("Knjiga2","ImeA1","PrezimeA1"));
			knjige.add(new Knjiga("Knjiga3","ImeA2","PrezimeA2"));
			knjige.add(new Knjiga("Knjiga4","ImeA2","PrezimeA2"));
			knjige.add(new Knjiga("Knjiga5","ImeA1","PrezimeA3"));
			knjige.add(new Knjiga("Knjiga6","ImeA1","PrezimeA4"));
			knjige.add(new Knjiga("Knjiga7","ImeA5","PrezimeA5"));
			knjige.add(new Knjiga("Knjiga8","ImeA5","PrezimeA5"));
			knjige.add(new Knjiga("Knjiga9","ImeA5","PrezimeA5"));
			knjige.add(new Knjiga("Knjiga10","ImeA1","PrezimeA6"));
			knjige.add(new Knjiga("Knjiga11","ImeA7","PrezimeA7"));
			knjige.add(new Knjiga("Knjiga12","ImeA3","PrezimeA5"));
			knjige.add(new Knjiga("Knjiga13","ImeA3","PrezimeA2"));
			knjige.add(new Knjiga("Knjiga14","ImeA3","PrezimeA4"));
			knjige.add(new Knjiga("Knjiga15","ImeA1","PrezimeA6"));
			knjige.add(new Knjiga("Knjiga16","ImeA7","PrezimeA7"));
		
		Polica p1 = new Polica(1, Arrays.asList( knjige.get(0), knjige.get(1), knjige.get(2), knjige.get(3) ));
		Polica p2 = new Polica(2, Arrays.asList( knjige.get(4), knjige.get(5), knjige.get(6), knjige.get(7) ));
		Polica p3 = new Polica(3, Arrays.asList( knjige.get(8), knjige.get(9), knjige.get(10), knjige.get(11) ));
		Polica p4 = new Polica(4, Arrays.asList( knjige.get(12), knjige.get(13), knjige.get(14), knjige.get(15) ));
		
		Sekcija s1 = new Sekcija(1, Arrays.asList(p1, p2));
		Sekcija s2 = new Sekcija(2, Arrays.asList(p3, p4));
		
		Biblioteka biblioteka = new Biblioteka(Arrays.asList(s1,s2));
		
		
		// 1 ----------------------------------------------------------------------------------------------------------------------
		
		biblioteka.sekcijeBiblioteke.stream()		// Stream<Sekcija>
									.map( sekcija -> sekcija.)
		
		
		
		
	}
	
}