package file;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Unltool {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		if (args.length != 2) {
			System.out.println("����Ĳ���Ϊ��������һ������Ϊ����ʼ���룻�ڶ�������Ϊ��������");
			System.exit(0);
		}

		int num = Integer.valueOf(args[1]).intValue();

		FileWriter outfile1 = null;

		try {
			outfile1 = new FileWriter("newFile1.jsp", true);
			BufferedWriter outbuffer1 = new BufferedWriter(outfile1);

			for (int i = 1; i <= 100; i++) {
				outbuffer1.write(args[0] + (num + i) + "\n");
				outbuffer1.flush();

			}

			System.out.println("�����ļ��������ϣ�");
			outfile1.close();

		} catch (IOException e) {
			System.out.println("����쳣��");
		} finally {

		}

	}

}
