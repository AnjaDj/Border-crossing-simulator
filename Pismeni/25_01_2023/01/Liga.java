import java.util.*;
import java.util.stream.*;

public class Liga extends Thread{
	
	public static final Object OBJECT_LOCK = new Object();
	public static final List<Tim> TIMOVI_ZA_FINALE = new ArrayList<>();
	public List<Tim> clanovi_lige;
	public String naziv_lige;
	
	public Liga(String naziv_lige, List<Tim> clanovi_lige){
		this.naziv_lige = naziv_lige;
		this.clanovi_lige = clanovi_lige;
	}
	@Override
	public String toString(){
		return naziv_lige+" :\n"+"  "+clanovi_lige.get(0)+",\n"+"  "+clanovi_lige.get(1)+",\n"+"  "+clanovi_lige.get(2)+",\n"+"  "+clanovi_lige.get(3)+"\n"
							    +"  "+clanovi_lige.get(4)+",\n"+"  "+clanovi_lige.get(5)+",\n"+"  "+clanovi_lige.get(6)+",\n"+"  "+clanovi_lige.get(7);
	}
	
	@Override 
	public void run(){
		
		try{
		System.out.println(naziv_lige+" KOLO_1");
		Kolo kolo1 = new Kolo(List.of(  new Partija(clanovi_lige.get(0),clanovi_lige.get(1)), 
										new Partija(clanovi_lige.get(2),clanovi_lige.get(3)), 
										new Partija(clanovi_lige.get(4),clanovi_lige.get(5)), 
										new Partija(clanovi_lige.get(6),clanovi_lige.get(7)) ));
		kolo1.start();
		kolo1.join();
										
		System.out.println(naziv_lige+" KOLO_2");
		Kolo kolo2 = new Kolo(List.of(  new Partija(clanovi_lige.get(0),clanovi_lige.get(2)), 
										new Partija(clanovi_lige.get(1),clanovi_lige.get(3)), 
										new Partija(clanovi_lige.get(4),clanovi_lige.get(6)), 
										new Partija(clanovi_lige.get(5),clanovi_lige.get(7)) ));
		kolo2.start();
		kolo2.join();								
										
		System.out.println(naziv_lige+" KOLO_3");
		Kolo kolo3 = new Kolo(List.of(  new Partija(clanovi_lige.get(0),clanovi_lige.get(3)), 
										new Partija(clanovi_lige.get(7),clanovi_lige.get(4)), 
										new Partija(clanovi_lige.get(2),clanovi_lige.get(5)), 
										new Partija(clanovi_lige.get(6),clanovi_lige.get(1)) ));
		kolo3.start();
		kolo3.join();

		System.out.println(naziv_lige+" KOLO_4");
		Kolo kolo4 = new Kolo(List.of(  new Partija(clanovi_lige.get(0),clanovi_lige.get(4)), 
										new Partija(clanovi_lige.get(7),clanovi_lige.get(3)), 
										new Partija(clanovi_lige.get(6),clanovi_lige.get(5)), 
										new Partija(clanovi_lige.get(2),clanovi_lige.get(1)) ));
		kolo4.start();
		kolo4.join();
										
		System.out.println(naziv_lige+" KOLO_5");
		Kolo kolo5 = new Kolo(List.of(  new Partija(clanovi_lige.get(0),clanovi_lige.get(5)), 
										new Partija(clanovi_lige.get(7),clanovi_lige.get(2)), 
										new Partija(clanovi_lige.get(6),clanovi_lige.get(3)), 
										new Partija(clanovi_lige.get(1),clanovi_lige.get(4)) ));
		kolo5.start();
		kolo5.join();
										
		System.out.println(naziv_lige+" KOLO_6");
		Kolo kolo6 = new Kolo(List.of(  new Partija(clanovi_lige.get(0),clanovi_lige.get(6)), 
										new Partija(clanovi_lige.get(7),clanovi_lige.get(1)), 
										new Partija(clanovi_lige.get(5),clanovi_lige.get(3)), 
										new Partija(clanovi_lige.get(4),clanovi_lige.get(2)) ));
		kolo6.start();
		kolo6.join();
										
		System.out.println(naziv_lige+" KOLO_7");
		Kolo kolo7 = new Kolo(List.of(  new Partija(clanovi_lige.get(0),clanovi_lige.get(7)), 
										new Partija(clanovi_lige.get(1),clanovi_lige.get(5)), 
										new Partija(clanovi_lige.get(6),clanovi_lige.get(2)), 
										new Partija(clanovi_lige.get(3),clanovi_lige.get(4)) ));
		kolo7.start();
		kolo7.join();
		
		}catch(InterruptedException e){}
		Collections.sort(clanovi_lige,Comparator.comparingInt(tim -> tim.broj_pobijeda));
									
		System.out.println("Pobjednik "+naziv_lige+" --> "+clanovi_lige.get(clanovi_lige.size()-1));
		
		synchronized(OBJECT_LOCK){
			TIMOVI_ZA_FINALE.add( clanovi_lige.get(clanovi_lige.size()-1) );
		}
	}
}