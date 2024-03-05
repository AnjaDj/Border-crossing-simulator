
//1300MB
class A
{
	public long[]longs=new long[10_000_000];
	public char[]chars=new char[15_000_000];
	public A a;
	public B b=new B();
	public A[]am[]=new A[2][3];
	public int id;
	static int globalId=1;
	public A()
	{
		this.id=globalId++;
	}
	public A(A a)
	{
		this();
		this.a=a;
	}
	public A(A a,B b)
	{
		this(a);
		this.b=b;
	}
	
	public static void main(String[]args)
	{
		A a1=new A();
		A a2=new A(a1);
		A a3=new A(a2);
		B b1=new B();
		A a4=new A(a1,b1);
		a4.am[0][0]=new A(a4);
		a4.am[0][1]=new A(a4.am[0][0],new B());
		a1=a2=a3=null;
		a4.am[1][0]=new A();
		System.gc();
		A tmp[]=new A[3];
		tmp[0]=new A();
		tmp[1]=new A(tmp[1],b1);
		tmp[2]=new A(tmp[0]);
		b1=null;
		a4.am[0]=tmp;
		a4.am[0][0]=null;
		System.gc();
	}
}

class B
{
	public float[][]floats=new float[10_000][1000];
	public int[]ints=new int[5_000_000];
	
}