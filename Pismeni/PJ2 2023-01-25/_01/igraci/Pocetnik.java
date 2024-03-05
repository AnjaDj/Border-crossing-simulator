package igraci;
import java.util.Random;

public class Pocetnik extends Amater{
	
	private static Random rand = new Random();
	
	public Pocetnik(){ 									// 0.30 - 0.50
		super( (rand.nextInt(21)+30)/100 );
	}
	
}