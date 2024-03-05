import java.util.*;

public class Simulacija{
	
	public static final int BROJ_TIMOVA = 64;
	public static final int BROJ_LIGA = 8;

	public static List<Tim> kreiranjeKolekcijeTimova(){
		
		List<Tim> kolekcija = new ArrayList<>();
		for(int i = 1; i <= BROJ_TIMOVA; i++)
			kolekcija.add(new Tim("Tim_"+i,List.of(new Pocetnik("Pocetnik_"+i), new NapredniIgrac("NaprediIgrac_"+i), 
										   new PlaceniIgrac("PlaceniIgrac_"+i), new NeplaceniIgrac("NeplaceniIgrac_"+i))));
		return kolekcija;
	}


	public static void main(String[] args){
		
		// Kreiranje timova
		List<Tim> timovi = kreiranjeKolekcijeTimova();
		
		// Kreiranje liga
		List<Liga> lige = new ArrayList<>();
		for(int i = 0; i < BROJ_LIGA; i++)
			lige.add(new Liga("Liga_"+(i+1),timovi.subList(i*8,(i*8)+8)));
		Liga finale = new Liga("Liga_finale",Liga.TIMOVI_ZA_FINALE);

		try{
			for(Iterator<Liga> it = lige.iterator(); it.hasNext();)
				(it.next()).start();
			
			for(Iterator<Liga> it = lige.iterator(); it.hasNext();)
				(it.next()).join();
			
			finale.start();
			finale.join();
		}catch(InterruptedException e){}
	
	}
}