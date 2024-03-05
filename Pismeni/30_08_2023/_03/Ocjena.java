public enum Ocjena{
	JEDAN(1,"Napomena_1"),DVA(2,"Napomena_2"),TRI(3,"Napomena_3"),CETIRI(4,"Napomena_4"),PET(5,"Napomena_5");
	
	private int vrijednost;
	private String napomenta;
	
	Ocjena(int vrijednost,String napomenta){this.vrijednost = vrijednost; this.napomenta = napomenta;}
	
	public int getVrijednost(){return vrijednost;}
	public String getNapomena(){return napomenta;}
}