
class A{
	void selfPrint(){
		System.out.println("No overRide");
	}
	
		void selfPrint2(){
		System.out.println("No overRide");
	}
}

class B extends A{
	void selfPrint(){
		System.out.println("Yes,overRide");
	}
}

public class overRide{
	public static void main(String[] args){
		B ab = new B();
		ab.selfPrint2();
	}
}