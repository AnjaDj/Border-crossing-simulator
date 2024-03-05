import java.util.*;

public class ObjektiVode extends Thread {
	
	public String naziv;
	
	public int brzinaKretanja;
	
	public ObjektiVode(String naziv) {
		this.naziv = naziv;
		
		Random random = new Random();
		brzinaKretanja = 1 + random.nextInt(1);
		brzinaKretanja *= 1000;
	}
	
	public int x;
	public int y;
	
	public void setKoordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void run() {
		
	}
	
	public String toString() {
		return "OV";
	}
	
	public int getBrojOsoba() {
		return 0;
	}
	
	public void unisten() {
		return;
	}
}