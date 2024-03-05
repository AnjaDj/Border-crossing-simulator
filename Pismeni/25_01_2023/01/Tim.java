import java.util.*;

public class Tim{
	
	public List<Igrac> clanovi_tima;
	public String naziv_tima;
	public int broj_pobijeda = 0;
	
	public Tim(String naziv_tima, List<Igrac> clanovi_tima){
		
		this.naziv_tima = naziv_tima;
		this.clanovi_tima = clanovi_tima;
	}
	
	@Override
	public String toString(){
		return naziv_tima + " : " + broj_pobijeda;
		// clanovi_tima.get(0)+", "+clanovi_tima.get(1)+", "+clanovi_tima.get(2)+", "+clanovi_tima.get(3);
	}
}