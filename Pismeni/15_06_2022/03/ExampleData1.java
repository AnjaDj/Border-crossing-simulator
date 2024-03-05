public class ExampleData1<T> implements Data<T>{
	
	String color, type;
	T value;
	
	public ExampleData1(String color, T value){
		this.color = color;
		this.value = value;
	}
	
	public String getType(){
		return value.getClass().getName();
	}
	public T getValue(){
		return value;
	}
	
	@Override
	public String toString(){
		return color + " " + value;
	}
} 