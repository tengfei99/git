package lottery;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Function:��ȡ��ҳ�ϵ�HtmlԴ����ָ��������
 * 
 * @author lishicun
 * 
 */
public class LotteryData {

	// ƥ��<div align="center">��ͷ��</div>��β���ĵ�
	Pattern p = Pattern.compile("<div align=\"center\">([^</div>]*)");
	Matcher m = null;
	String data = null;

	// �õ�html��div�������
	private String getContext(String html) {

		m = p.matcher(html);// ��ʼ����
		data = null; // ������ı���,����Ҫ���,���򱣳�ǰ��ֵ

		while (m.find()) {

			data = m.group(1);// ��ȡ��ƥ��Ĳ���
		}

		return data;
	}

	void getDataAndWriteFile(String startNum, String endNum) {

		try {

			// Ϊfalse�����ԭ�ļ���д��,true��׷��д��
			FileWriter outfile1 = new FileWriter("lotterydata.txt", false);
			BufferedWriter outbuffer1 = new BufferedWriter(outfile1);

			String addr = "http://lotterychart.sports.cn/table/index_letouzh_table.jsp?lotteryid=41&startNum="
					+ startNum + "&endNum=" + endNum;
			URL url = new URL(addr);
			URLConnection c = url.openConnection();
			// ��URLConnection��connect()������������
			c.connect();
			InputStream is = c.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String str = null;
			String tem = null;
			int sid = 0; // ����76��ʱ�Ŵ�ӡ
			while ((str = br.readLine()) != null) {
				sid = sid + 1;
				tem = getContext(str);
				
				if (sid >= 76 && tem != null) {

					outbuffer1.write(tem.trim());
					outbuffer1.newLine();
					// System.out.println(getContext(str));
				}
			}

			outbuffer1.flush();
			outfile1.close();
			System.out.println();
			System.out.println("������Ϣ����������������ϣ�");
			System.out.println();
			System.out.println("===========================================");

		} catch (IOException e) {
			System.out.println();
			System.out.println("������Ϣ��������ַ����,�����û������.ϵͳ������Ϣ:" + e.getMessage());
			System.out.println();
			System.out.println("===========================================");
		}
	}

	public static void main(String[] args) throws InterruptedException {

		if (args.length != 2) {
			System.out.println(" ��������������,��һ������Ϊ��ʼ���ں�,�ڶ�������Ϊ�������ں�!�ںŸ�ʽ:09066.");
			System.exit(0);
		}
		LotteryData ld = new LotteryData();

		System.out.println("===========================================");
		System.out.println();
		System.out.println("���ڴ���������غ�������,�˴����벻Ҫ�ر�,���Ժ򡭡�");
		System.out.println();
		System.out.println("===========================================");
		ld.getDataAndWriteFile(args[0].trim(), args[1].trim());

		Thread.sleep(3000);
	}
}
