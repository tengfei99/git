package file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutputStreamExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int size;
		FileOutputStream outf = null;
		BufferedInputStream buffer1 = null;
		FileInputStream fis = null;

		try {
			outf = new FileOutputStream("newFile1.jsp");
		} catch (FileNotFoundException e) {
			System.out.print("没有找到文件。");
		}

		BufferedOutputStream bufferout = new BufferedOutputStream(outf);

		byte b[] = "asdfsafds".getBytes();

		try {
			bufferout.write(b);
			bufferout.flush();//
			bufferout.close();
			
			fis = new FileInputStream("newFile1.jsp");

			size = fis.available();//?
			System.out.println("sdfsafasdfs: " + size);
			
			buffer1 = new BufferedInputStream(fis);
			byte bufferArray[] = new byte[90];
			int n = 0;
			while ((n = buffer1.read(bufferArray)) != -1) {
				String temp = new String(bufferArray, 0, n);
				System.out.print(temp);
			}
			buffer1.close();
			fis.close();
		} catch (IOException e) {
			System.out.print("sadfsadf");
		} finally {

		}

	}

}
