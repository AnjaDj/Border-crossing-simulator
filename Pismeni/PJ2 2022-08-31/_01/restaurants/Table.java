package restaurants;

public class Table{
	
	private boolean tableForSmokers, isFree;
	private int tableFor;
	
	public Table(boolean tableForSmokers, int tableFor){
		
		this.tableForSmokers = tableForSmokers;
		this.tableFor = tableFor;
		this.isFree = true;
	}
	
	public boolean takeTheTable(){
		
		if(isFree){
			isFree = false;
			return true;
		}
		return false;
	}
	
	public void freeTheTable(){
		
		isFree = true;
	}
	
	public boolean getTableForSmokers(){
		
		return tableForSmokers;
	}
	
	public boolean getIsFree(){
		
		return isFree;
	}
	
	public int getTableFor(){
		
		return tableFor;
	}
}