public enum UslugeNaPasosima{
	
	PONISTAVANJE_VAZECEG_PASOSA(10), IZDAVANJE_OBICNOG_PASOSA(50), IZDAVANJE_BRZOG_PASOSA(400), IZDAVANJE_DIPLOMATSKOG_PASOSA(0);
	
	public int cijenaUsluge;
	
	UslugeNaPasosima(int cijenaUsluge){
		this.cijenaUsluge = cijenaUsluge;
	}
	
	public int getCijenaUsluge(){
		return cijenaUsluge;
	}
	
}