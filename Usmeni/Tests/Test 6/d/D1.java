public class D1 extends D2 implements DI{
public static void main(String args[])
{
  
D3 niz[]={new D3(),new D2(),new D1()};
for(int i=0;i<niz.length;i++)
{
niz[i].metoda();
}
}
 

}

class D2 extends D3 implements DI{
protected void metoda(){
System.out.println("D2 :metoda()");
//return new D2();
}
}

class D3{
protected void metoda(){
System.out.println("D3 : metoda()");
//return new D1();
}
}
interface DI{
void metoda();
}