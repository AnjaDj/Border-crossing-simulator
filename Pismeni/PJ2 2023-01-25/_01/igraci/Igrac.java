package igraci;

public abstract class Igrac{
	
	private double vjerovatnocaPotpunogPogotka;
	private static int counter = 0;
	private int redniBroj;
	
	Igrac(double vjerovatnocaPotpunogPogotka){
		this.vjerovatnocaPotpunogPogotka = vjerovatnocaPotpunogPogotka;
		counter++;
		redniBroj = counter;
	}
	
	public double getVjerovatnocaPotpunogPogotka(){
		return vjerovatnocaPotpunogPogotka;
	}
	
	@Override
	public String toString(){
		return "Igrac_"+redniBroj;
	}
	
}