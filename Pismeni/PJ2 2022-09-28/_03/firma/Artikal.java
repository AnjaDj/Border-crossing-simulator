package firma;

public class Artikal{
	
	private String nazivArtikla, barkod;
	private Double cijenaArtikla;
	
	public Artikal(String barkod, String nazivArtikla, Double cijenaArtikla){
		this.barkod = barkod;
		this.nazivArtikla = nazivArtikla;
		this.cijenaArtikla = cijenaArtikla;
	}
	public String getNazivArtikla(){return nazivArtikla;}
	public String getBarkod(){return barkod;}
	public Double getCijenaArtikla(){return cijenaArtikla;}
	
	@Override
	public String toString(){
		return barkod+" "+nazivArtikla+" "+cijenaArtikla;
	}
	
}