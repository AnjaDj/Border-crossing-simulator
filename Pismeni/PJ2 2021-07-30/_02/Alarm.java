public class Alarm implements Interfejs{
	
	private static int counter = 0;
	private int ID;
	private String opis, datum;
	
	public Alarm(String datum, String opis){
		this.ID = ++counter;
		this.datum = datum;
		this.opis = opis;
	}
	
	@Override
	public void action(){
		System.out.println(ID+" "+opis);
	}
	@Override
	public String toString(){
		return ID+" "+opis+" "+datum;
	}
}