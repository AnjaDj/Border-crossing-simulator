package studenti;
import java.util.*;

public class Student implements java.io.Serializable{
	
	private String 	ime, prezime;
	private int		index;
	private List<Integer> IDPredmeta;
	
	public Student(int index, String ime, String prezime, List<Integer> IDPredmeta){
		this.index = index;
		this.ime = ime;
		this.prezime = prezime;
		this.IDPredmeta = IDPredmeta;
	}
	
	public String getIme(){return ime;}
	public int 	  getIndex(){return index;}
	public String getPrezime(){return prezime;}
	public List<Integer> getIDPredmeta(){return IDPredmeta;}
	
}