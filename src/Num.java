import java.text.SimpleDateFormat;
import java.util.Date;

 /* 
  * 
  * 求1到300中数字1出现的次数. 
  * 
  */ 

public class Num{

	public int nums(int a ){
		String b = String.valueOf(a);
		int intr=0;
		for(int i=0;i<b.length();i++){
			if(String.valueOf(b.charAt(i)).equals("1")) intr++;
		}

		return intr;
	}

	public static void main(String[] args){
		
		long tf = System.currentTimeMillis();
        int all =0;
        
		Num num = new Num();
		
        for(int j=1;j<=300;j++){
        	
           all += num.nums(j);
        }
        
        long tf2 = System.currentTimeMillis() - tf;
        
		System.out.println(all);
        System.out.println("用时: "+tf2+" 毫秒.");
        
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        
        System.out.println(dateString.toString());
	}
}
