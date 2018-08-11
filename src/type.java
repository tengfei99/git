
/**
 * 
 * translate : int --> float --> double 
 *
 */

public class type{
	
	public type(){
	}
	
	public static void main(String args[]){
		int int1=123;
		float float1=12.23f;
		double double1=123.456;
		
		float temf;
		int temi;
		double temd;
		String str;
		
		temd=double1+float1;
		
		int1+=float1;// covert to int. equtals int1=(int)(int1+float1);
		float1=int1+float1;
		
		//convert to String
		str=String.valueOf(temd);
		str=new Double(temd).toString();
		
		// convert to Other Types except String
		temd=java.lang.Double.valueOf(str).doubleValue();
		temd=java.lang.Double.parseDouble(str);
		
		System.out.println(str+"OK.");
		System.out.println(float1);
		
	}
}