public class Exec{
	public static void main(String args[]){
		String path="calc.exe";
		try{
			Runtime.getRuntime().exec("cmd /c start "+ path);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}