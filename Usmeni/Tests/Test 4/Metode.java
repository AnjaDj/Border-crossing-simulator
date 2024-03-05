public class Metode extends Klasa1 implements I1{
	
	public static void main(String args[]) {
		
		Metode m = new Metode();
		m.m();
	}
}

class Klasa1{
	protected void m() {
		System.out.println("Klasa1 m()");
	}
}
interface I1{
	default void m() {
		System.out.println("I1 m()");
	}
}
