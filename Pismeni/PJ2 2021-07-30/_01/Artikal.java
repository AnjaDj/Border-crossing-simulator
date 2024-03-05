public enum Artikal{
	HLJEB(1.0),MLIJEKO(1.5),CIGARETE(5.0),PIVO(1.0),SOK(1.0),SLATKIS(0.5);

	double cijenaArtikla;
	public double getCijenaArtikla(){return cijenaArtikla;}
	
	Artikal(double cijenaArtikla){this.cijenaArtikla = cijenaArtikla;}
}