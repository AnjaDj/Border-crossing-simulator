package vozila;

public class Kamion extends Vozilo implements MogucnostPrevozaTereta{
	
	private static Random random = new Random();
	private List<Vozilo> automobiliZaUvoz;
	
	public Kamion(Motor motor, String identifikator, List<Vozilo> automobiliZaUvoz){
		
		super(motor, identifikator, "Ime Prezime", random.nextInt(1000001)*1.0, random.nextInt(3)+1);
		this.automobiliZaUvoz = automobiliZaUvoz;
	}
	
	public List<Vozilo> getAutomobiliZaUvoz(){return automobiliZaUvoz;}
	
}


	

	