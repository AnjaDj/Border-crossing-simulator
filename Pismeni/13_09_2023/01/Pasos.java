import java.io.*;

public abstract class Pasos implements java.io.Serializable{
	
	public int vrijemeTrajanja;
	public String sadrzajPasosa;
	
	public Pasos(int vrijemeTrajanja, String sadrzajPasosa){
		this.vrijemeTrajanja = vrijemeTrajanja;
		this.sadrzajPasosa = sadrzajPasosa;
	}
	
	public String getSadrzajPasosa(){
		return sadrzajPasosa;
	}
	
	public int getVrijemeTrajanja(){
		return vrijemeTrajanja;
	}
}