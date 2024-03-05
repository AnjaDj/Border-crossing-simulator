package igraci;
import java.util.Random;

public class Placeni extends Profesionalac{
	
	private static Random rand = new Random();
	
	public Placeni(){	// 0.75 - 1.00
		super( (rand.nextInt(26)+75)/100 );
	}
}