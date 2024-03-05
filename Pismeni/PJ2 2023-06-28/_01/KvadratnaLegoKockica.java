public class KvadratnaLegoKockica extends LegoKockica implements MogucnostGradjenja,MogucnostRastavljanja{

	private int duzina;

	KvadratnaLegoKockica(int duzina, String boja){
		
		super(duzina*duzina*1.0,4.0*duzina,boja);
		this.duzina = duzina;
	}
	
	public int getDimenzija(){return duzina;}
	
	@Override
	public String toString(){
		return "K "+duzina;
	}
}