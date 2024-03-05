public class Stan extends Nekretnina{

	public int brojSprata, brojBalkona;

	public Stan(String ID, int povrsina, int cijena, String adresa, int brojSprata, int brojBalkona){
		super(ID,povrsina,cijena,adresa);
		
		this.brojSprata = brojSprata;
		this.brojBalkona = brojBalkona;
	}

}