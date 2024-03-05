import java.util.*;
import java.util.stream.*;
import java.lang.Math;

public class Simulacija{
	
	public static void main(String[] args){
	
	List<Vozilo> grupa1 = new LinkedList<>(); 
	List<Vozilo> grupa2 = new LinkedList<>();
	List<Vozilo> grupa3 = new LinkedList<>();
	List<Vozilo> grupa4 = new LinkedList<>();
	
	grupa1.add(new Vozilo(Tip.SUV,144,2015,5,Boja.CRVENA));
	grupa1.add(new Vozilo(Tip.SUV,155,2020,5,Boja.CRVENA));
	grupa1.add(new Vozilo(Tip.SUV,132,1988,5,Boja.PLAVA));
	grupa1.add(new Vozilo(Tip.SUV,290,2022,5,Boja.PLAVA));
	grupa1.add(new Vozilo(Tip.SUV,189,1996,5,Boja.BIJELA));
	
	grupa2.add(new Vozilo(Tip.KARAVAN,144,2000,5,Boja.CRVENA));
	grupa2.add(new Vozilo(Tip.KARAVAN,155,1989,5,Boja.CRVENA));
	grupa2.add(new Vozilo(Tip.KARAVAN,132,1988,5,Boja.PLAVA));
	grupa2.add(new Vozilo(Tip.KARAVAN,290,1999,5,Boja.PLAVA));
	grupa2.add(new Vozilo(Tip.KARAVAN,189,2025,5,Boja.BIJELA));
	
	System.out.println("a. Spojiti 2 grupe vozila u novu listu . Spajaju se samo crvena vozila cija je snaga > 120 ");
	
	List<Vozilo> z1 =  Stream.concat(grupa1.stream(),grupa2.stream())			// Stream<Vozila>
												.filter(vozilo -> ((vozilo.getBoja() == Boja.CRVENA) && (vozilo.getSnaga() > 120)) ) // Stream<Vozila>
												.collect(Collectors.toList());	// List<Vozila>

	z1.forEach(System.out::println);
	
	
	System.out.println("b. Sortiranje grupe vozila po godini proizvodje OPADAJUCE");
	
	grupa1.stream()
			.sorted((v1,v2)-> { 
								if (v1.getGodinaProizvodnje() > v2.getGodinaProizvodnje()) return -1;
								else if (v1.getGodinaProizvodnje() < v2.getGodinaProizvodnje()) return 1;
								else return 0;
								})
			.forEach(vozilo -> System.out.println(vozilo));
			
	
	
	System.out.print("c. Sumirati broj sjedista svih vozila iz grupe koji su tipa SUV i godina proizvodnje im je < 2000 = ");
	
	int sum = grupa1.stream()
						.filter(vozilo -> ((vozilo.getTip() == Tip.SUV) && (vozilo.getGodinaProizvodnje() < 2000)))
						.collect(Collectors.summingInt(vozilo -> vozilo.getBrojSjedista()));
						
	System.out.println(sum);					
						
						
	System.out.println("d. Prikazati vozilo koje je najblize prosjecnoj snazi");	

	double avg = grupa1.stream()
						.collect(Collectors.averagingInt(vozilo -> vozilo.getSnaga()));
	System.out.println("prosjek = "+avg);
						
	grupa1.stream()
				.collect(Collectors.minBy( (v1,v2) -> {
														if (Math.abs(v1.getSnaga()-avg) < Math.abs(v2.getSnaga()-avg)) return -1;
														else if ( Math.abs(v1.getSnaga()-avg) > Math.abs(v2.getSnaga()-avg)) return 1;
														else return 0;
														} ))
				.ifPresent(vozilo -> System.out.println("Vozilo koje je najblize projeecnoj snazi je "+vozilo));
									
	
	}
	
}