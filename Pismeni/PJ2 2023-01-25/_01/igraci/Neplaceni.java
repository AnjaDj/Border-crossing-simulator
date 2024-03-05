package igraci;
import java.util.Random;

public class Neplaceni extends Profesionalac{
	
	private static Random rand = new Random();
	
	public Neplaceni(){		// 0.70 - 1.00
		super( (rand.nextInt(31)+70)/100 );
	}
}