import java.util.*;
import java.util.stream.*;
import java.util.function.*;

public class Util{
	
	public <T extends Podatak> List<T> method( List<Predicate<T>> uslovi, int fromIndex, int toIndex, List<T>... kolekcije ){
		
		List<T> rezultat = new ArrayList<>();
		
		for(int i = 0; i < kolekcije.length; i++){
			
			List<T> kolekcijaPodataka = kolekcije[i];
			
			for(T podatak :  kolekcijaPodataka){
				
				int count = 0;
				
				for( Predicate<T> uslov : uslovi )
					if (uslov.test(podatak)) count++;
				
				if (count == uslovi.size()) rezultat.add(podatak);
				
			}
			
		}
		
		rezultat.stream()
	
		return rezultat.subList(fromIndex, toIndex);
	}

	 
}