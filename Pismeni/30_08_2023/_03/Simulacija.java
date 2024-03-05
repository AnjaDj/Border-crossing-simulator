import java.util.*;
import java.util.stream.*;

public class Simulacija{

	public static void main(String... args) throws Exception{
		
		List<Ucenik> kolekcijaUcenika = new ArrayList<>();
		
		// 1.
		try(Stream<Ucenik> stream = kolekcijaUcenika.stream()){
			
			stream.max(Comparator.comparing())
		}
		
	}
}