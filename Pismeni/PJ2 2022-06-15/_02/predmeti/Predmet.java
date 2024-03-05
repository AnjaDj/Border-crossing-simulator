package predmeti;

public class Predmet implements java.io.Serializable{
	
	private String 	nazivPredmeta;
	private int		IDPredmeta;
	
	public Predmet(int IDPredmeta, String nazivPredmeta){
		this.IDPredmeta = IDPredmeta;
		this.nazivPredmeta = nazivPredmeta;
	}
	
	public int getIDPredmeta(){return IDPredmeta;}
	public String getNazivPredmeta(){return nazivPredmeta;}
}