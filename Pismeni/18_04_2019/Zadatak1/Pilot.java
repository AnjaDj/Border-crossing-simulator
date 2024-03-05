import java.util.*;

public class Pilot extends Takmicar implements SavladajVatra, SavladajStijena{
	
	public Pilot(Object[] mapa) {
		super(mapa);
	}
	
	public void savladajVatru() {
		System.out.println("Pilot je savladao vatru.");
	}
	
	@Override
	public void savladajStijenu() {
		System.out.println("Pilot je savladao stijenu.");
	}
	
	@Override
	public String toString() {
		return new String("Pilot");
	}
	
}