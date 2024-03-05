class T1{
	public static void main(String... args){
		
		System.out.println(new C() instanceof D);
		
	}
}
class A{}
class B extends A{}
class D extends B{}
class C{}
