public class CilindricnaLegoKockica extends LegoKockica{

	private int poluprecnik;

	CilindricnaLegoKockica(int poluprecnik, String boja){
		
		super(poluprecnik*poluprecnik*3.14,4*poluprecnik*3.14,boja);
		this.poluprecnik = poluprecnik;
	}
	
	public int getDimenzija(){return poluprecnik;}
	
	@Override
	public String toString(){
		return "C "+ poluprecnik;
	}
}