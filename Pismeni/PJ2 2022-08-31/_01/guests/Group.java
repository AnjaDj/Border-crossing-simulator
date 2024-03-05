package guests;

public class Group{
	
	private boolean hasSmokers, hasVegeterians;
	private int numberOfMembers;
	private Guest[] members;
	
	public Group(int numberOfMembers, boolean hasSmokers, boolean hasVegeterians){
		
		this.hasSmokers = hasSmokers;
		this.hasVegeterians = hasVegeterians;
		this.numberOfMembers = numberOfMembers;
		members = new Guest[numberOfMembers];
		
		int temp = 0;
		if (hasSmokers) { 
			
			members[temp] = new Smoker(); temp++; 
		}
		if (hasVegeterians && (numberOfMembers > temp)) { 
			
			members[temp] = new Vegeterian(); temp++; 
		}
		
		for(int i = temp; i < numberOfMembers; i++)
			members[i] = new Guest();
	}
	
	public int getNumberOfMembers(){
		
		return numberOfMembers;
	}
	
	public Guest[] getMembers() {
		
		return members;
	}
	
	public boolean getHasSmokers(){
		
		return hasSmokers;
	}
	
	public boolean getHasVegeterians(){
		
		return hasVegeterians;
	}
	
	public double get
}