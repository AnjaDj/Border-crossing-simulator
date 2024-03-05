public class Simulation{
	
	public static void main(String... args){
		
		Skladiste<Alarm> s1 = new Skladiste<Alarm>();
		Skladiste<Obavijestenje> s2 = new Skladiste<Obavijestenje>();
		
		s1.offer(new Alarm("10-04-2000", "Alarm opis"));
		s1.offer(new Alarm("09-04-2000", "Alarm opis"));
		s1.offer(new Alarm("08-04-2000", "Alarm opis"));
		s1.offer(new Alarm("07-04-2000", "Alarm opis"));
		s1.offer(new Alarm("06-04-2000", "Alarm opis"));
		s1.offer(new Alarm("05-04-2000", "Alarm opis"));
		s1.offer(new Alarm("04-04-2000", "Alarm opis"));
		s1.offer(new Alarm("03-04-2000", "Alarm opis"));
		s1.offer(new Alarm("02-04-2000", "Alarm opis"));
		s1.offer(new Alarm("01-04-2000", "Alarm opis"));
		
		s2.offer(new Obavijestenje("Obavijestenje naslov 1", "Obavijestenje opis"));
		s2.offer(new Obavijestenje("Obavijestenje naslov 2", "Obavijestenje opis"));
		s2.offer(new Obavijestenje("Obavijestenje naslov 3", "Obavijestenje opis"));
		s2.offer(new Obavijestenje("Obavijestenje naslov 4", "Obavijestenje opis"));
		s2.offer(new Obavijestenje("Obavijestenje naslov 5", "Obavijestenje opis"));
		
	}
	
}