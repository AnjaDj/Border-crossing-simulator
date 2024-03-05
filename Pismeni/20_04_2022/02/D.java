public class D implements I{
	
	Double value;
	String name;
	
	public D(Double value, String name){
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
		return null;
	}
	
	@Override
	public String toString(){
		return name + " " + value;
	}
	
}