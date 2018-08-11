
interface Car{
	int MAX_WEIGHT = 5;
	int LoadWare();
}

interface Taxs{
	int MAX_NUMBER = 4;
	int LoadMan();
}

class FartherCar{
	
	int intSpeed = 150;
	int DriveSpeed(){
		return intSpeed;
	}
}

class Bus extends FartherCar implements Car,Taxs{
	
	private int intWare;
	private int intMan;
	
	public int LoadWare(){
		intWare = MAX_WEIGHT;
		return intWare;
	}
	
	public int LoadMan(){
		intMan = MAX_NUMBER;
		return intMan;
	}
}

public class InterfaceExample{
	
	public static void main(String[] args){
		Bus objBus = new Bus();
		System.out.println("The max drive speed of bus:" + objBus.DriveSpeed());
		System.out.println("The max ware weight of bus:" + objBus.LoadWare());
		System.out.println("The max man number of bus:" +objBus.LoadMan());
	}
}