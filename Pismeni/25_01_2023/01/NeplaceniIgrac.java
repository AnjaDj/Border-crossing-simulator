public class NeplaceniIgrac extends Profesionalac{

	public NeplaceniIgrac(String ID){
		super(ID);
		vjerovatnova_strajka = (70 + Igrac.rand.nextInt(31))/100.0;
	}
}