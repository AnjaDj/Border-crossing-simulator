import java.util.*;

public class VojniBrod extends Brod {
	
	public ArrayList<Vojnik> vojnici = new ArrayList<Vojnik>();
	
	public VojniBrod(String naziv) {
		super(naziv);
		
		Random random = new Random();
		int brojVojnika = 10 + random.nextInt(11);
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
						for(int i = y + 1; i < Main.dimenzijaY; i++) {
							if(Main.mapa[0][x][i] != null) {
								Main.mapa[0][x][i].interrupt();
								//Ispis da je unisten brod
								System.out.println(this.toString() + " je potopio brod " + Main.mapa[0][x][i].toString() + ". Broj zrtava je " + Main.mapa[0][x][i].getBrojOsoba());
								Main.mapa[0][x][i].unisten();
								Main.mapa[0][x][i] = null;
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
		
		Main.mapa[0][x][y - 1] = null; 
	}
	
	public String toString() {
		return "VB";
	}
	
	public int getBrojOsoba() {
		return vojnici.size();
	}
	
}