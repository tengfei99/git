package readhtml;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Function:��ȡ��ҳ�ϵ�HtmlԴ��
 * 
 * @author li
 * 
 */
public class ReadHtml {

	public static void getContext(String html) {
		// List<String> resultList = new ArrayList<String>();
		Pattern p = Pattern.compile("<div align=\"center\">([^</div>]*)");// ƥ��<title>��ͷ��</title>��β���ĵ�
		Matcher m = p.matcher(html);// ��ʼ����
		while (m.find()) {
			
			System.out.println(m.group(1));// ��ȡ��ƥ��Ĳ���
		}
	}

	void display() {
		//byte buf[] = new byte[1024];
		try {
			// System.out.print("�������ļ���URL��ַ:");
			// ��ȡ�û������URL
			// InputStream consoleis = System.in;
			// int count = consoleis.read(buf);
			// String addr = new String(buf, 0, count);
			// System.out.println("��������: " + addr);
			// ���û������URL�ַ�������URL�����
			String addr = "http://lotterychart.sports.cn/table/index_letouzh_table.jsp?lotteryid=41&startNum=09056&endNum=09069";
			URL url = new URL(addr);
			// ����URLConnection������URL��openConnection����������ͨ�����ظ�URLConnection�Ķ���
			// ʵ����URL��openConnection�ķ���ֵ����һ��URLConnection
			URLConnection c = url.openConnection(); // *
			// ��URLConnection��connect()������������
			c.connect(); // *
			// ��ʾ�����ӵ������Ϣ����Щ����URLConnection�ķ���
			// System.out.println("��������: " + c.getContentType());
			// System.out.println("���ݳ���: " + c.getContentLength());
			// System.out.println("��������: " + new Date(c.getDate()));
			// System.out.println("����޸�����: " + new Date(c.getLastModified()));
			// System.out.println("��ֹ����: " + new Date(c.getExpiration()));

			InputStream is = c.getInputStream(); // *
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String str = null;
			int sid = 0; // ����76��ʱ�Ŵ�ӡ
			while ((str = br.readLine()) != null) {
				// System.out.println(str);
				sid = sid + 1;
				if (sid >= 76) {
					getContext(str);
				}
			}

		} catch (IOException e) {
			System.out.println("�������ַ����,�����û������.ϵͳ������Ϣ:" +e.getMessage());
		}
	}

	public static void main(String[] args) {
		ReadHtml app = new ReadHtml();
		app.display();
	}
}
