import java.io.*;

public abstract class LegoKockica extends Thread{
	
	protected double povrsina, obim;
	protected String boja;
	
	public double getPovrsina(){return povrsina;}
	public double getObim(){return obim;}
	public String getBoja(){return boja;}
	public abstract int getDimenzija();
	public boolean regionMatch(int currentPosX, int currentPosY){
		try{
			for(int i = currentPosX; i < currentPosX+getDimenzija(); i++){
				for(int j = currentPosY; j < currentPosY+getDimenzija(); j++){
					if ((Mapa.PLOCA[i][j]).naPolju != null) return false;
				}
			}
		}catch(ArrayIndexOutOfBoundsException e) {return false;}	
		return true;	
	}
	
	public void regionSet(int currentPosX, int currentPosY){
		
		for(int i = currentPosX; i < currentPosX+getDimenzija(); i++){
			for(int j = currentPosY; j < currentPosY+getDimenzija(); j++){
				(Mapa.PLOCA[i][j]).naPolju = this;
			}
		}System.out.println("POSTAVLJANJE "+this);}
	
	LegoKockica(double povrsina, double obim, String boja){
		
		this.povrsina = povrsina;
		this.obim = obim; 
		this.boja = boja;
	}
	
	
	@Override 
	public void run(){
		
		for(int i = 0; i < Mapa.VELICINA_X; i++){
			for(int j = 0; j < Mapa.VELICINA_Y; j++){
				
				if (regionMatch(i,j)){
					synchronized (Mapa.LOCK_MAP_OBJECT){
						if (regionMatch(i,j)){
							regionSet(i,j);
							return;
						}
					}
				}
				
			}
		}
		
		try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File("kockike_koje_nisu_postavljene.txt"),true)))){
			pw.println(this+ "nije postavljena jer za nju nije bilo mjesta na ploci");
		}catch(Exception e){}
		
	}
	
}