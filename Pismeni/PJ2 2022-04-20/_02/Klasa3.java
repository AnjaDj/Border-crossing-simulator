public class Klasa3 implements Interfejs{
	
	private Status status;
	private Double value;
	private String name;
	
	public Klasa3(String name,Double value,Status status){
		this.status = status;
		this.value = value;
		this.name = name;
	}
	
	public Status getStatus() {return status;}
	public Double getValue() {return value;}
	public String getName() {return name;}
}