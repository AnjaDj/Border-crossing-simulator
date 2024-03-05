import java.util.*;

public class Pjesak extends Takmicar implements SavladajVoda, SavladajStijena {
	
	public Pjesak(Object[] mapa) {
		super(mapa);
	}
	
	@Override
	public void savladajVodu() {
		System.out.println("Vozac je savladao vodu.");
	}
	
	@Override
	public void savladajStijenu() {
		System.out.println("Vozac je savladao stijenu.");
	}
	
	@Override
	public String toString() {
		return new String("Pjesak");
	}
}