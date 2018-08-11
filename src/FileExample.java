import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileExample {

	String FilePath = null;

	/**
	 * ��ָ�����ַ�������Ȼ��д��ָ���ļ�
	 * 
	 * @param FileContent
	 */
	public void WriteFile(String FileContent) {

		try {
			FileOutputStream outf = new FileOutputStream(FilePath);
			BufferedOutputStream bufferout = new BufferedOutputStream(outf);
			// FileContent = des.getEncString(FileContent);//����
			byte b[] = FileContent.getBytes();
			bufferout.write(b);
			bufferout.flush();
			bufferout.close();

			System.out.println("���ܺ�д���ļ���ϣ��ļ�·����");

		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
	}

	/**
	 * ��ȡ�ļ������ݲ����ܣ�Ȼ��д��ָ���ļ�
	 */
	public void ReadFile() {
		int size;
		try {
			FileInputStream f = new FileInputStream(FilePath);
			size = f.available();
			System.out.println("�ļ��ܼƿɶ����ֽ���: " + size);
			BufferedInputStream buffer1 = new BufferedInputStream(f);
			byte bufferArray[] = new byte[size];
			int n = 0;
			System.out.println("��ʼ������/���ܺ���ļ�д���ĵ���");
			while ((n = buffer1.read(bufferArray)) != -1) {
				String temp = new String(bufferArray, 0, n);
				this.WriteFile(temp);
				System.out.println("��������");
			}
			buffer1.close();
			f.close();
			System.out.println("��ϣ�");

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
