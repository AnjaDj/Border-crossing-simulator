public class TestThread
{
	public static void main(String[]args)
	{
		System.out.println("main start");
		new T1().start();
		new T2().start();
		
		int i = 0;
		while(i < 10_000)
			i++;
		System.out.println("main end");
	}
}

class T1 extends Thread
{
	public T1()
	{
		this.setDaemon(true);
	}
	
	@Override
	public void run()
	{
		System.out.println("T1:"+this.isDaemon());
	}
}

class T2 extends T1
{
	@Override
	public void run()
	{
		System.out.println("T2:"+this.isDaemon());
	}
}

