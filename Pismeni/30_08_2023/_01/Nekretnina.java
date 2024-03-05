public abstract class Nekretnina{
	
	public int povrsina, cijena;
	public String ID, adresa;
	
	Nekretnina(String ID, int povrsina, int cijena, String adresa){
		this.ID = ID;
		this.povrsina = povrsina;
		this.cijena = cijena;
		this.adresa = adresa;
	}
	
}