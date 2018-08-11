
import java.util.*;
import java.text.*;

public class SortFind{
	
	Date currTime = new Date();
	
	public static void main(String[] args){
		
		SortFind sf = new SortFind();
		int ok = sf.bitSearch(189);
		System.out.println(ok);
		
		//Date currTime = new Date();
		try{
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.US);
		String webdate=new String(formatter.format(sf.currTime).getBytes("ISO-8859-1"));
		System.out.println(webdate);
		}catch(Exception e){}

	}
	
	public int bitSearch(int key){
		
		int[] farray = {12,10,15,14,21,31,80,78,88,79,90,1,189,20};
		int low = 0,high = farray.length-1,mid,tem;
		
		for(int i=0;i<farray.length-1;i++){ // sort the Array before search
			for(int j =0;j<farray.length-1-i;j++){
				
				if(farray[j]>farray[j+1]){
					
					tem = farray[j+1];
					farray[j+1] = farray[j];
					farray[j] = tem;
				}
			}
		}
		
		for(int i=0;i<farray.length;i++) System.out.print(farray[i]+" , ");
		System.out.println();
		
		while(low<=high){ // search the key
			
			mid = (low+high)/2;
			if(farray[mid]==key) return mid;
			else if(farray[mid]>key) high = mid - 1;
			else low = mid + 1;
		}
		
		return -1; 
		
		}
		
  }