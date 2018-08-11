
public class Stringbuffer{
	
	public static void main(String[] args){
	
	StringBuffer abc = new StringBuffer();
	StringBuffer def = new StringBuffer();
	abc.append("abc_value");
	
	def = abc;
	
	def.append("  abcValue is changed via the def.");
	
	System.out.println(abc);
	
	}
}