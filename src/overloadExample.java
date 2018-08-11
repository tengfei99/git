
class C
{
	public void print(){
		System.out.println("No parameters");
	}

	public void print(String s){
		System.out.println(s);
	}
}

public class overloadExample
{
	public static void main(String[] args){
		C objA=new C();
		objA.print();
		objA.print("Have parameters");
	}
}