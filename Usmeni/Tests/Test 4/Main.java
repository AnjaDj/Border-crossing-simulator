class TestThread extends Thread{

	public TestThread()
	{
		setDaemon(true);
	}
	
	@Override
	public void run()
	{
		System.out.println(isDaemon());
	}
}

public class Main{
	public static void main(String[] m){
		System.out.println("main start");
		
			new TestThread().start();
			new X().start();
		System.out.println("main end");
	}
}

class X extends TestThread{
	
}