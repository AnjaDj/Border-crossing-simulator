package vozila;

public class Motor{
	
	private String identifikator, tip;
	
	public Motor(String identifikator, String tip){
		this.identifikator = identifikator;
		this.tip = tip;
	}
	public String getIdentifikator(){return identifikator;}
	public String getTip(){return tip;}
	
	@Override
	public String toString(){
		return "motor ID:"+identifikator+" "+tip;
	}
}