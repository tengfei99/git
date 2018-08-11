import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileExample {

	String FilePath = null;

	/**
	 * 将指定的字符串加密然后写入指定文件
	 * 
	 * @param FileContent
	 */
	public void WriteFile(String FileContent) {

		try {
			FileOutputStream outf = new FileOutputStream(FilePath);
			BufferedOutputStream bufferout = new BufferedOutputStream(outf);
			// FileContent = des.getEncString(FileContent);//加密
			byte b[] = FileContent.getBytes();
			bufferout.write(b);
			bufferout.flush();
			bufferout.close();

			System.out.println("加密后写入文件完毕！文件路径：");

		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
	}

	/**
	 * 读取文件的内容并解密，然后写入指定文件
	 */
	public void ReadFile() {
		int size;
		try {
			FileInputStream f = new FileInputStream(FilePath);
			size = f.available();
			System.out.println("文件总计可读的字节数: " + size);
			BufferedInputStream buffer1 = new BufferedInputStream(f);
			byte bufferArray[] = new byte[size];
			int n = 0;
			System.out.println("开始将加密/解密后的文件写入文档：");
			while ((n = buffer1.read(bufferArray)) != -1) {
				String temp = new String(bufferArray, 0, n);
				this.WriteFile(temp);
				System.out.println("将近工期");
			}
			buffer1.close();
			f.close();
			System.out.println("完毕！");

		} catch (FileNotFoundException fnfe) {
			System.out.println(fnfe.getMessage());
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
	}

	public static void main(String[] args) {

		FileExample fe = new FileExample();

		fe.FilePath = "d:/test.txt";

		fe.ReadFile();

	}

}
