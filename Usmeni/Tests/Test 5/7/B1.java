public class B1{

  public void metoda1(){
  System.out.println(" B1 metoda 1");
  }
  public static void metoda2(){
  System.out.println("B1 metoda 2");
  }
  B1(){
  System.out.println("B1");
  }
  public static void main(String args[]){
  B1 b1=new B1();
  B1 b2=new B2(){ //iako je klasa B2 neimenovana prvo se poziva konstruktor klase A u ovom slucaju podrazumijevani pa se poziva konstruktor klase B pa se redefinisu metode
    public void metoda1(){
      System.out.println("B2 metoda 1");
    }
    //public static void metoda2()
    //{
      //System.out.println("AA");
    //}
  };
  BI b3=new B2(){
    public void metoda1(){
    System.out.println("B2 m12");
    }
    //public static void metoda2(){
    //System.out.println("B2 m22");
    //}
  };
  B1 niz[]={b1,b2,(B1)b3};
    for(B1 b:niz){
    b.metoda1();
    b.metoda2();
    }
  }
}
abstract class B2 extends B1 implements BI{
  public static  void metoda2(){
  System.out.println("B2 metoda 2");
  }
  public B2(){
  System.out.println("B2");
  }
}
interface BI{
abstract void metoda1();
//static void metoda2();
}
