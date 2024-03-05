import java.util.*;

public class Tanker extends CivilniBrod {
	
	public ArrayList<Civil> civili = new ArrayList<Civil>();
	
	public Tanker(String naziv) {
		super(naziv);
		
		Random random = new Random();
		int brojCivila = 10 + random.nextInt(11);
		for(int i=0;i<brojCivila;i++) {
			Civil civil = new Civil();
			civili.add(civil);
		}
	}
	
	public String toString() {
		return "TB";
	}
	@Override
	public void unisten() {
		System.out.println("Ekoloska katastrofa.");
	}
	
	public int getBrojOsoba() {
		return civili.size();
	}
	
}