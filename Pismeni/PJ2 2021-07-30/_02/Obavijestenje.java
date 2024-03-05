public class Obavijestenje implements Interfejs{
	
	private String naslov, opis;
	
	public Obavijestenje(String naslov, String opis){
		this.naslov = naslov;
		this.opis = opis;
	}
	
	@Override
	public void action(){
		System.out.println(naslov);
	}
	@Override
	public String toString(){
		return naslov+" "+opis;
	}
}