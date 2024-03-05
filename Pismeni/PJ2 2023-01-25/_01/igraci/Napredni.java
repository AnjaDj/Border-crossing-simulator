package igraci;
import java.util.Random;

public class Napredni extends Amater {
	
	private static Random rand = new Random();
	
	public Napredni(){ 									// 0.50 - 1.00
		super( (rand.nextInt(51)+50)/100 );
	}
	
}