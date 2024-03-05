public class Pocetnik extends Amater{
	
	public Pocetnik(String ID){
		super(ID);
		vjerovatnova_strajka = (30 + Igrac.rand.nextInt(21))/100.0;
	}
}