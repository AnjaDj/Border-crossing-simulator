package firma;
import java.util.*;

public class Rafa{
	
	private int idRafe;
	private Map<String,Integer> artikliNaRafi; 	// String -> barkod artikla   Integer -> kolicina odredjenog artikla na toj rafi
	
	public Rafa(int idRafe, List<Artikal> artikli){
		
		this.idRafe = idRafe;
		artikliNaRafi = new HashMap<>();
		
		Random rand = new Random();
		
		for(Iterator<Artikal> i = artikli.iterator(); i.hasNext();)
			artikliNaRafi.put( i.next().getBarkod(), rand.nextInt(100) + 1);
		
	}
	
	public int getIdRafe(){return idRafe;}
	
	public Map<String,Integer> getArtikliNaRafi(){return artikliNaRafi;}
	
}