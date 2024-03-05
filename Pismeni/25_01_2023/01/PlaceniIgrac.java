public class PlaceniIgrac extends Profesionalac{

	public PlaceniIgrac(String ID){
		super(ID);
		vjerovatnova_strajka = (75 + Igrac.rand.nextInt(26))/100.0;
	}
}