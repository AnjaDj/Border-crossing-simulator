package staza;
import vozila.*;

public class Staza{
	
	private int velicinaStaze;
	private Polje[] staza;
	
	public Staza(int velicinaStaze){
		this.velicinaStaze = velicinaStaze;
		staza = new Polje[velicinaStaze];
		
		for(int i = 0; i < velicinaStaze; i++)
			staza[i] = new Polje();
	}
	
	public int getVelicinaStaze(){return velicinaStaze;}
	
	public Polje[] getStaza(){return staza;}
}

