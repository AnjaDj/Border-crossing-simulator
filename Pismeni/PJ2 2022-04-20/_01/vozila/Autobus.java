package vozila;

public class Autobus extends Vozilo implements MogucnostPrevozaVecegBrojaPutnika{
	
	private static Random random = new Random();
	
	public Autobus(Motor motor, String identifikator){
		super(motor, identifikator, "Ime Prezime", random.nextInt(1000001)*1.0, random.nextInt(45)+8);
	}
	
}