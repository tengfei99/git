import java.io.*;
import java.util.*;

public class Reconf {

	private static String url = "ffffjdbc:microsoft:sqlserver://javali:1433;DatabaseName=Strutsdb";
	
	/**
	 * static 都会首先执行
	 */
	static {
		try {

			Properties prop = new Properties();
			prop.load(Reconf.class.getResourceAsStream("conn.properties"));
			url = prop.getProperty("url");


		} catch (Exception e) {
		}
	}

	public Reconf() {
		System.out.println("ssstest");
	}

	public static void main(String[] arg) {

		Reconf rf = new Reconf();
		System.out.println(url);
	}
}