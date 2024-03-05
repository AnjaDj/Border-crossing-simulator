public class Kuca extends Nekretnina{

	public int brojSpratova, povrsinaDvorista;

	public Kuca(String ID, int povrsina, int cijena, String adresa,int brojSpratova, int povrsinaDvorista){
		super(ID,povrsina,cijena,adresa);
		
		this.brojSpratova = brojSpratova;
		this.povrsinaDvorista = povrsinaDvorista;
	}

}