public class Knjiga{
	
	public String naslov, imeAutora, prezimeAutora;
	
	public Knjiga(String naslov, String imeAutora, String prezimeAutora){
		this.prezimeAutora = prezimeAutora;
		this.imeAutora = imeAutora;
		this.naslov = naslov;
	}
	
	@Override
	public String toString(){
		return naslov + " " + imeAutora + " " + prezimeAutora;
	}
	
}