import java.util.*;

public class Vozac extends Takmicar implements SavladajVoda {
	
	public Vozac(Object[] mapa) {
		super(mapa);
	}
	
	@Override
	public void savladajVodu() {
		System.out.println("Vozac je savladao vodu.");
	}
	
	@Override
	public String toString() {
		return new String("Vozac");
	}
	
}