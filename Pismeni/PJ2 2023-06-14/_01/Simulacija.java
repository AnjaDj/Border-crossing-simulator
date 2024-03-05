import java.util.*;

public class Simulacija{
	
	private static Random random = new Random();
	
	public static void main(String[] args){
		
		List<Osoba> kolekcijaOsoba = kreiranjeKolekcijeOsoba();
			Collections.shuffle(kolekcijaOsoba);
		Queue<Osoba> redOsoba = new LinkedList<>();
			redOsoba.addAll(kolekcijaOsoba);
			
		Salter.redOsoba = redOsoba;	
			
		Salter salter1 = new Salter("Salter_1");	
		Salter salter2 = new Salter("Salter_2");	
		
		salter1.start();
		salter2.start();
		
	}
	
	private static List<Osoba> kreiranjeKolekcijeOsoba(){
		
		List<Osoba> kolekcijaOsoba = new ArrayList<>();
		
			FizickoLice f1 = new FizickoLice("Arezina","Mihajlo","1004000105065");  f1.setVozilaZaRegistraciju(new Automobil());  kolekcijaOsoba.add(f1);
			FizickoLice f2 = new FizickoLice("Branesic","Dusan","1004000105065");  f2.setVozilaZaRegistraciju(new Kamion());  kolekcijaOsoba.add(f2);
			FizickoLice f3 = new FizickoLice("Teodosic","Bojana","1004000105065");  f3.setVozilaZaRegistraciju(new Motor());  kolekcijaOsoba.add(f3);
			FizickoLice f4 = new FizickoLice("Zigic","Nikolina","1004000105065");  f4.setVozilaZaRegistraciju(new Kamion());  kolekcijaOsoba.add(f4);
			FizickoLice f5 = new FizickoLice("Mikic","Milica","1004000105065");  f5.setVozilaZaRegistraciju(new Kamion());  kolekcijaOsoba.add(f5);
			FizickoLice f6 = new FizickoLice("Stojanovic","Jelena","1004000105065");  f6.setVozilaZaRegistraciju(new Motor());  kolekcijaOsoba.add(f6);
			FizickoLice f7 = new FizickoLice("Puzic","Tara","1004000105065");  f7.setVozilaZaRegistraciju(new Autobus());  kolekcijaOsoba.add(f7);
			FizickoLice f8 = new FizickoLice("Terzic","Sloboda","1004000105065");  f8.setVozilaZaRegistraciju(new Autobus());  kolekcijaOsoba.add(f8);
			FizickoLice f9 = new FizickoLice("Vukovic","Ljiljana","1004000105065");  f9.setVozilaZaRegistraciju(new Automobil());  kolekcijaOsoba.add(f9);
			FizickoLice f10 = new FizickoLice("Ninkovic","Zeljka","1004000105065");  f10.setVozilaZaRegistraciju(new Automobil());  kolekcijaOsoba.add(f10);
			
			PravnoLice p1 = new PravnoLice("Milovic","Zeljana","Savo okovi doo","1004000105065");  
					   List<Vozilo> pl1 = new ArrayList<>();	pl1.add(new Automobil());	pl1.add(new Kamion());	pl1.add(new Automobil());
					   p1.setVozilaZaRegistraciju(pl1);  
					   kolekcijaOsoba.add(p1);
					   
			PravnoLice p2 = new PravnoLice("Ares","Viktorija","Kamelija doo","1004000105065");  
					   List<Vozilo> pl2 = new ArrayList<>();	pl2.add(new Automobil());	pl2.add(new Kamion());	pl2.add(new Automobil());	pl2.add(new Autobus());
					   p2.setVozilaZaRegistraciju(pl2);  
					   kolekcijaOsoba.add(p2);
					   
			PravnoLice p3 = new PravnoLice("Linkovic","Mina","Obeliks doo","1004000105065");  
					   List<Vozilo> pl3 = new ArrayList<>();	pl3.add(new Automobil());	pl3.add(new Kamion());	pl3.add(new Automobil());
					   p3.setVozilaZaRegistraciju(pl3);  
					   kolekcijaOsoba.add(p3);
					   
			PravnoLice p4 = new PravnoLice("Lazarevic","Snjezana","Lindex doo","1004000105065");  
					   List<Vozilo> pl4 = new ArrayList<>();	pl4.add(new Automobil());	pl4.add(new Kamion());	pl4.add(new Automobil());	pl4.add(new Motor());
					   p4.setVozilaZaRegistraciju(pl4);  
					   kolekcijaOsoba.add(p4);	

			PravnoLice p5 = new PravnoLice("Pusic","Lidija","Apoteke B+ doo","1004000105065");  
					   List<Vozilo> pl5 = new ArrayList<>();	pl5.add(new Automobil());	pl5.add(new Kamion());	
					   p5.setVozilaZaRegistraciju(pl5);  
					   kolekcijaOsoba.add(p5);	
				   
		
		return kolekcijaOsoba;
	}
	
	
	
}