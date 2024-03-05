import java.io.*;
import java.util.*;
import java.util.stream.*;
import java.util.function.*;

public class Util{
	
	public static<T> void processData( List<Predicate<Data<T>>> conditions, Consumer<Data<T>> consumer, List<Data<T>>... data ){
		
		for( List<Data<T>> list : data ){		// iteriranje lista po lista
			
			List<Data<T>> resTemp = new ArrayList<>();
				resTemp.addAll(list);
			for( Iterator<Predicate<Data<T>>> it = conditions.iterator(); it.hasNext(); ){
			
				Predicate<Data<T>> condition = it.next();
				
				resTemp.retainAll( list.stream() // Stream<Data<T>>
										.filter(condition)
										.toList() );
								
			}
			
			resTemp.forEach(consumer);
			
		}
		
	}
	
}