public class ExampleData2<T> implements Data<T>{
	
	String size, type;
	T value;
	
	public ExampleData2(String size, T value){
		this.size = size;
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
		return size + " " + value;
	}
}