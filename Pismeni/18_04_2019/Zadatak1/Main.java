import java.util.*;
import java.util.function.*;

public class Main {
	
	public static Object[] mapa = new Object[100];
	
	public static void main(String[] args) {
		
		Random random = new Random();
		for(int i=0; i<3; i++) {
			for(int j=0; j<10; j++) {
				int pozicijaPrepreke = random.nextInt(100);
				while(mapa[pozicijaPrepreke] == null) {
					if(mapa[pozicijaPrepreke] == null) {
						int prepreka = 1 + random.nextInt(3);
						if(prepreka == 1) {
							mapa[pozicijaPrepreke] = new Voda();
						} else if (prepreka == 2) {
							mapa[pozicijaPrepreke] = new Vatra();
						} else {
							mapa[pozicijaPrepreke] = new Stijena();
						}
					} else {
						pozicijaPrepreke = random.nextInt(100);
					}
				}
			}
		}
		
		for(int i=0; i<15; i++) {
			int pozicijaBodova = random.nextInt(100);
			while(mapa[pozicijaBodova] == null) {
				if(mapa[pozicijaBodova] == null) {
					int bodovi = 2 + random.nextInt(4);
					mapa[pozicijaBodova] = new Bodovi(bodovi);
				} else {
					pozicijaBodova = random.nextInt(100);
				}
			}
		}
		
		Pjesak pjesak = new Pjesak(mapa);
		Pilot pilot = new Pilot(mapa);
		Vozac vozac = new Vozac(mapa);
		
		pjesak.start();
		pilot.start();
		vozac.start();
		
		try {
			pjesak.join();
			pilot.join();
			vozac.join();
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		
		Consumer<Takmicar> ukupanBrojBodova = (t) -> System.out.println("Bodovi: " + t.brojBodova);
		Consumer<Takmicar> ukupanoVrijeme = (t) -> System.out.println("Vrijeme: " + t.vrijeme);
		Consumer<Takmicar> ukupanBrojSavladanihPrepreka = (t) -> System.out.println("Prepreke: " + t.brojPrepreka);
		ArrayList<Consumer<Takmicar>> listaConsumera = new ArrayList<Consumer<Takmicar>>();
		listaConsumera.add(ukupanBrojBodova);
		listaConsumera.add(ukupanoVrijeme);
		listaConsumera.add(ukupanBrojSavladanihPrepreka);
		
		System.out.println("----------------------------------------------------------------------");
		System.out.println("Pjesak");
		pjesak.statistika(listaConsumera);
		System.out.println("----------------------------------------------------------------------");
		System.out.println("Pilot");
		pilot.statistika(listaConsumera);
		System.out.println("----------------------------------------------------------------------");
		System.out.println("Vozac");
		vozac.statistika(listaConsumera);
		System.out.println("----------------------------------------------------------------------");
		System.out.println("KRAJ");
	}
	
}