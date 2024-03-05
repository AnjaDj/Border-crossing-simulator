import java.util.*;

public class CivilnaPodmornica extends Podmornica {
	
	public ArrayList<Civil> civili = new ArrayList<Civil>();
	
	public CivilnaPodmornica(String naziv) {
		super(naziv);
		
		Random random = new Random();
		int brojCivila = 2 + random.nextInt(9);
		for(int i=0;i<brojCivila;i++) {
			Civil civil = new Civil();
			civili.add(civil);
		}
	}
	
	
	public void run() {
		
		while(x < Main.dimenzijaX && y < Main.dimenzijaY) {
			
			try {
				kreciSe();
				
				Thread.sleep(brzinaKretanja);//promjeniti
			} catch (InterruptedException ex) {
				
			}
		}
		
		Main.mapa[1][x - 1][y] = null; 
	}
	
	public String toString() {
		return "CP";
	}
	
	public int getBrojOsoba() {
		return civili.size();
	}
}