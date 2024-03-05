public class Proizvod{
	
	public Kategorija kategorija;
	public String naziv; 
	public double cijena;
	
	public Proizvod(Kategorija kategorija, String naziv, double cijena){
		this.kategorija = kategorija;
		this.cijena = cijena;
		this.naziv = naziv;
	}
	
	@Override 
	public String toString(){
		return naziv+" "+kategorija+" "+cijena;
	}
}