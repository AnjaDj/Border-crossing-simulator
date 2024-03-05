package guests;

public class Guest{
	
	private double accountState = 100.0;
	
	public void setAccountState(double d){
		
		accountState = d;
	}
	
	public double getAccountState(){
		
		return accountState;
	}
	
	public void decreaseAccountState(double d){
		
		accountState -= d;
	}
}