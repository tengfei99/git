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
			System.out.println("输入的参数为两个，第一个参数为：起始号码；第二个参数为：生成数");
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

			System.out.println("数据文件生成完结毕！");
			outfile1.close();

		} catch (IOException e) {
			System.out.println("输出异常！");
		} finally {

		}

	}

}
