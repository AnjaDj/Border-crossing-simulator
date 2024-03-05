package vozila;
import java.util.Random;

public class Automobil extends Vozilo{
	
	private static Random random = new Random();
	
	public Automobil(Motor motor, String identifikator){
		super(motor, identifikator, "Ime Prezime", random.nextInt(1000001)*1.0, random.nextInt(8)+1);
	}

}