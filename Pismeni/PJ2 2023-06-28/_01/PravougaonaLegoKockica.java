public class PravougaonaLegoKockica extends LegoKockica implements MogucnostGradjenja,MogucnostRastavljanja,MogucnostRotiranja{

	private int duzina, sirina;

	PravougaonaLegoKockica(int duzina, int sirina, String boja){
		
		super(duzina*sirina*1.0,((2*sirina)+(2*duzina))*1.0,boja);
		this.duzina = duzina;
		this.sirina = sirina;
	}
	
	public int getDuzina(){return duzina;}
	public int getSirina(){return sirina;}
	
	public int getDimenzija(){return duzina;}
	
	@Override
	public String toString(){
		return "P "+duzina+","+sirina;
	}
	
	@Override
	public boolean regionMatch(int currentPosX, int currentPosY){
		
		boolean temp1 = true , temp2 = true;
		try{
				l1 : for(int i = currentPosX; i < currentPosX+getDuzina(); i++){
						for(int j = currentPosY; j < currentPosY+getSirina(); j++){
							if ((Mapa.PLOCA[i][j]).naPolju != null) { temp1 = false; break l1; }
						}
					}
					
				l2 : for(int i = currentPosX; i < currentPosX+getSirina(); i++){
						for(int j = currentPosY; j < currentPosY+getDuzina(); j++){
							if ((Mapa.PLOCA[i][j]).naPolju != null) { temp2 = false; break l2; }
						}
					}	
		}catch(ArrayIndexOutOfBoundsException e) {return false;}			
		return (temp1 || temp2);	
	}
	
	@Override
	public void regionSet(int currentPosX, int currentPosY){
		
		boolean temp1 = true , temp2 = true;
		
		l1 : for(int i = currentPosX; i < currentPosX+getDuzina(); i++){
				for(int j = currentPosY; j < currentPosY+getSirina(); j++){
					if ((Mapa.PLOCA[i][j]).naPolju != null) { temp1 = false; break l1; }
				}
			}
			
		l2 : for(int i = currentPosX; i < currentPosX+getSirina(); i++){
				for(int j = currentPosY; j < currentPosY+getDuzina(); j++){
					if ((Mapa.PLOCA[i][j]).naPolju != null) { temp2 = false; break l2; }
				}
			}	
		
		if (temp1)
			
				for(int i = currentPosX; i < currentPosX+getDuzina(); i++){
					for(int j = currentPosY; j < currentPosY+getSirina(); j++){
						(Mapa.PLOCA[i][j]).naPolju = this;
					}
				}
		
		else 
				for(int i = currentPosX; i < currentPosX+getSirina(); i++){
					for(int j = currentPosY; j < currentPosY+getDuzina(); j++){
						(Mapa.PLOCA[i][j]).naPolju = this;
					}
				}
				
		System.out.println("POSTAVLJANJE "+this);		
		
	}
}

