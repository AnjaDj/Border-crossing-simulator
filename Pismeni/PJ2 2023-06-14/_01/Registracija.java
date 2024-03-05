public enum Registracija implements java.io.Serializable{
	
	OBICNA_REGISTRACIJA(100), REGISTRACIJA_PLUS_KASKO(200), REGISTRACIJA_PLUS_KASKO_PLUS_POMOC_NA_PUTU(250);
	
	private int cijenaUslugeRegistacije;
	
	Registracija(int cijenaUslugeRegistacije){ 
		this.cijenaUslugeRegistacije = cijenaUslugeRegistacije; }

	public int getCijenaUslugeRegistacije(){ 
		return cijenaUslugeRegistacije; }
	
}