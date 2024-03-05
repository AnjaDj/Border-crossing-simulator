public class A1{

	private A1 a1;
	static{
		System.out.println("A1-S");
	}
	{
		System.out.println("A1-N");
	}
	public A1(){
		System.out.println("A1");
	}
	public A1(A1 a1){
		System.out.println("A1(A1)");
		this.a1 = a1;
		new A2(a1);
	}
	void metoda(){
		System.out.println("metoda A1");
	}
	
	public static void main(String... args){
		A1 a1 = new A1();
		System.out.println("=1=");
		A2 a2 = new A2();
		System.out.println("=2=");
		A3 a3 = new A3(a2,a1);
		System.out.println("=3=");
	}
}
class A2 extends A1{
	A1 a1 = new A1();
	static{
		System.out.println("A2-S");
	}
	{
		System.out.println("A2-N");
	}
	public A2(){
		this(new A1());
		System.out.println("A2");
	}
	public A2(A1 a1){
		this.a1 = a1;
		System.out.println("A2(A1)");
	}
	private void metoda2(){
		System.out.println("metoda2 A2");
	}
}
class A3 extends A2 implements java.io.Serializable{
	static{
		System.out.println("A3-S");
	}
	{
		System.out.println("A3-N");
	}
	public A3(){
		System.out.println("A3");
	}
	public A3(A2 a2){
		this();
		System.out.println("A3(A2)");
	}
	public A3(A2 a2, A1 a1){
		this(a2);
		System.out.println("A3(A2,A1)");
	}
	void metoda(){
		System.out.println("metoda A3");
	}
	void metoda2(){
		System.out.println("metoda2 A3");
	}
}