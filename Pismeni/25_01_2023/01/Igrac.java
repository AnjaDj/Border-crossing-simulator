import java.util.*;

public abstract class Igrac{
	public static final Random rand = new Random();
	
	public double vjerovatnova_strajka;	
	public String ID;
	
	public Igrac(String ID){
		this.ID = ID;
	}
	
	@Override
	public String toString(){
		return ID + " : "+ vjerovatnova_strajka;
	}
	
	public double bacanjeKugle(){
		if (vjerovatnova_strajka < 1.0)
			return (rand.nextDouble()*10)+1;
		else 
			return 20;
	}
}