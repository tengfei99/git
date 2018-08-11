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
public class SsqData {

	// ƥ��<td id=">��ͷ��</td>��β���ĵ�
	Pattern p = Pattern.compile("<td.*id=\".*\">(.+)</td>");
	Matcher m = null;
	String data = null;

	private String getContext(String html) {

		m = p.matcher(html);// ��ʼ����
		data = null; // ������ı���,����Ҫ���,���򱣳�ǰ��ֵ

		while (m.find()) {

			data = m.group(1);// ��ȡ��ƥ��Ĳ���
		}
		// System.out.println(data);
		return data;
	}

	/**
	 * 
	 * @param number
	 *            ת�������
	 * @param flag
	 *            �Ƿ�ֻ����10��,ȡֵ:0:���������������,1:ֻ��10��
	 */
	void getDataAndWriteFile(String number, String flag) {

		try {

			String addr = null;

			// Ϊfalse�����ԭ�ļ���д��,true��׷��д��
			FileWriter outfile1 = null;

			if (flag.equals("0")) {
				outfile1 = new FileWriter("SsqData.txt", false);
				addr = "http://map2.zhcw.com/ssq/ssq/ssqInc/ssqZongHeFengBuTuAscselect="
						+ number + ".html";
			} else {
				outfile1 = new FileWriter("SsqData10.txt", false);
				addr = "http://map2.zhcw.com/ssq/ssq/ssqInc/ssqZongHeFengBuTuAscselect=10.html";
				number = "10";
			}

			BufferedWriter outbuffer1 = new BufferedWriter(outfile1);

			URL url = new URL(addr);
			URLConnection c = url.openConnection();
			// ��URLConnection��connect()������������
			c.connect();
			InputStream is = c.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			
			String str = null;
			String tem = null;
			int sid = 0;
			int cid = 1;
			int times = 0;
			int i = 154; // ����sid��ʱ�Ŵ�ӡ
			int j = 1;
			String st = "";

			times = Integer.valueOf(number).intValue() * 9; // ����ӡ������,ÿһ�����9�У���ʣ��9

			while ((str = br.readLine()) != null) {
				sid = sid + 1;
				if (sid >= i) {

					tem = getContext(str);

					if (tem != null && cid <= times) {
						cid = cid + 1;
						tem = tem.trim();

						if (j <= 2) {
							outbuffer1.write(tem);
							outbuffer1.newLine();
							j = j + 1;
						} else {

							if (j == 8) {
								st = st + tem + " + ";
							} else if (j == 9) {
								st = st + tem;
								outbuffer1.write(st.trim());
								outbuffer1.newLine();
								j = 0;
								st = "";
							} else {
								st = st + tem + " ";
							}
							j = j + 1;
						}

					}

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

		// if (args.length != 1) {
		// System.out.println("��ѡ���������ٽ��в�����");
		// System.exit(0);
		// }

		SsqData ld = new SsqData();

		System.out.println("===========================================");
		System.out.println();
		System.out.println("���ڴ��в������غ�������,�˴����벻Ҫ�ر�,���Ժ򡭡�");
		System.out.println();
		System.out.println("===========================================");
		ld.getDataAndWriteFile("10", "1");

		Thread.sleep(3000); 
	}
}
