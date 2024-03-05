public class NapredniIgrac extends Amater{

	public NapredniIgrac(String ID){
		super(ID);
		vjerovatnova_strajka = (50 + Igrac.rand.nextInt(51))/100.0;
	}
}