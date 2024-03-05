package profesori;
import java.util.*;

public class Profesor implements java.io.Serializable{
	
	private String 	ime, prezime;
	private int		JMB;
	private List<Integer> IDPredmeta;
	
	public Profesor(int JMB, String ime, String prezime, List<Integer> IDPredmeta){
		this.JMB = JMB;
		this.ime = ime;
		this.prezime = prezime;
		this.IDPredmeta = IDPredmeta;
	}
	public String getIme(){return ime;}
	public int 	  getJMB(){return JMB;}
	public String getPrezime(){return prezime;}
	public List<Integer> getIDPredmeta(){return IDPredmeta;}
	
}