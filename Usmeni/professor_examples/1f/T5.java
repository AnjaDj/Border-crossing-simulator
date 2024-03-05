public class T5<X extends Object>{
	private X x;
	public T5(X x){
		this.x = x;
	}
	private double getDouble(){
		return ((Double)x);
	}
	
	public static void main(String... ags){
		Double d = 10d;
		Integer i = (Integer)d;
		T5<Integer> a = new T5<>(1);
		a.getDouble();
	}
}