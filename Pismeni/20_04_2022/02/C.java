public class C implements I{
		
	Status status;
	Double value;
	String name;
	
	public C(Status status, Double value, String name){
		this.status = status;
		this.value = value;
		this.name = name;
	}
	
	public Double getValue(){
		return value;
	}
	public String getName(){
		return name;
	}
	public Status getStatus(){
		return status;
	}
	
}