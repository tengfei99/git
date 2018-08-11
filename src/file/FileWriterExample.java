package file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriterExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try
		{
		  FileWriter outfile1=new FileWriter("newFile1.jsp",true);
		  BufferedWriter outbuffer1=new BufferedWriter(outfile1);
		  //BufferedWriter outbuffer1=new BufferedWriter(new FileWriter("newFile1.jsp",true));
		  		  
		  outbuffer1.write("okok");
		  outbuffer1.write("\n");
		  outbuffer1.write("asdsdfsadf");
		  outbuffer1.flush();
		  outfile1.close();
		  
		  FileReader f=new FileReader("newFile1.jsp"); 
		  
		  //InputStreamReader isr = new InputStreamReader(is, "UTF-8");// 指定UTF-8字符集，解决乱码
		  //BufferedReader br = new BufferedReader(isr);
			
		  BufferedReader buffer1=new BufferedReader(f);
		  String tempString=null;
		  while((tempString=buffer1.readLine())!=null)
		  {
		   System.out.println("<br>"+tempString);
		  }
		  buffer1.close();
		  f.close();
		}
		catch(IOException e)
		{
		  System.out.println("sdfa");
		}

	}

}
