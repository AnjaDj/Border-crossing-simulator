import java.util.*;

public class CivilniBrod extends Brod{
	
	public ArrayList<Civil> civili = new ArrayList<Civil>();
	
	public CivilniBrod(String naziv) {
		super(naziv);
		
		Random random = new Random();
		int brojCivila = 10 + random.nextInt(11);
		for(int i=0;i<brojCivila;i++) {
			Civil civil = new Civil();
			civili.add(civil);
		}
	}
	
	public String toString() {
		return "CB";
	}
	
	public int getBrojOsoba() {
		return civili.size();
	}
	
}