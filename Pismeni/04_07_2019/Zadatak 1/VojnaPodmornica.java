import java.util.*;

public class VojnaPodmornica extends Podmornica {
	
	public ArrayList<Vojnik> vojnici = new ArrayList<Vojnik>();
	
	public VojnaPodmornica(String naziv) {
		super(naziv);
		
		Random random = new Random();
		int brojVojnika = 2 + random.nextInt(9);
		for(int i=0;i<brojVojnika;i++) {
			Vojnik vojnik = new Vojnik();
			vojnici.add(vojnik);
		}
	}
	
	public void run() {
		
		long vrijeme = new Date().getTime();
		
		while(x < Main.dimenzijaX && y < Main.dimenzijaY) {
			try {
				
				long novoVrijeme = new Date().getTime();
				if(novoVrijeme - vrijeme > 3000 && novoVrijeme - vrijeme < 5000) {
					synchronized(Main.mapa) {
						for(int i = x + 1; i < Main.dimenzijaX; i++) {
							if(Main.mapa[0][i][y] != null) {
								Main.mapa[0][i][y].interrupt();
								//Ispis da je unisten brod
								System.out.println(this.toString() + " je potopio brod " + Main.mapa[0][i][y].toString() + ". Broj zrtava je " + Main.mapa[0][i][y].getBrojOsoba());
								Main.mapa[0][i][y].unisten();
								Main.mapa[0][i][y] = null;
							}
						}
					}
					vrijeme = novoVrijeme;
				}
			
				kreciSe();
			
				Thread.sleep(brzinaKretanja);//promjeniti
			} catch (InterruptedException ex) {
				
			}
			
		}
		
		Main.mapa[1][x - 1][y] = null; 
	}
	
	public String toString() {
		return "VP";
	}
	
	public int getBrojOsoba() {
		return vojnici.size();
	}
	
}