package igraci;

public class Tim{
	
	private static int counter = 0;
	private Igrac[] clanoviTima;
	private int brojPoena = 0;
	private String nazivTima;
	
	public Tim(){
		
		counter++;
		nazivTima = "Tim_"+counter;
		clanoviTima = new Igrac[4];
		
		clanoviTima[0] = new Pocetnik();
		clanoviTima[1] = new Napredni();
		clanoviTima[2] = new Placeni();
		clanoviTima[3] = new Neplaceni();
	}
	
	@Override
	public String toString(){
		return nazivTima+" -> clanovi tima : "+clanoviTima[0]+","
											  +clanoviTima[1]+","	
								  			  +clanoviTima[2]+","
											  +clanoviTima[3];
	}
	
	public void uvecajBrojPoena(int i){
		brojPoena += i;
	}
	public int getBrojPoena(){
		return brojPoena;
	}
	public Igrac[] getClanoviTima(){
		return clanoviTima;
	}
	public String getNazivTima(){
		return nazivTima;
	}
	
}