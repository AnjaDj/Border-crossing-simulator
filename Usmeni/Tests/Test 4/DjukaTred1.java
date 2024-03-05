// E1.java

public class E1 {

    static public void main(String[] args) {
        System.out.println("main 1");
       // E3 e3 = new E3();
        E2 e2 =  new E2();
		E4 e4 = new E4();
        e2.start();
		e4.start();
		System.out.println(105 / e4.id);
    }
    
}

class E2 extends Thread{
    public E2(){
		this.setDaemon(true);
        System.out.println("E2");
    }
    
    public void run() {
		Thread niz[] = {new E3(5),new E3(2),new E3(3),new E3(0)};
		for(var el : niz){
			if(el instanceof E3){
				E3 temp = (E3)el;
				if(temp.id == 2 || temp.id == 3){
					temp.start();
				}else{
					try{
						temp.start();
						temp.join();
					}catch(InterruptedException ex){
						
					}
				}

			}else
				break;
		}
    }
    
    public synchronized void start() {
        super.start();
        //new Thread(new E3(10)).start();
    }
}

class E3 extends Thread{
	static int br = 0;
	int id;
    public E3() {
        System.out.println("E3");
    }
	public E3(int id) {
		if(id % 2 == 0){
			this.id = id;
		}else
			this.id = id + 2;
        System.out.println("E3(" + id + ")");
    }
    public void run() {
        for (int i = 1; i < 100; i++)
            System.out.println("E3( " + id + ") is running : " + i + " " + this.isDaemon());
    }
    
}
class E4 extends E2{
	int id = 0;
	public E4(){
		System.out.println("E4()");
	}
	public void run() {
        for (int i = 1; i < 100; i++)
            System.out.println("E4( " + id + ") is running : " + i + " " + this.isDaemon());
    }
	
}