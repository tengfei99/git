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
 * Function:读取网页上的Html源码中指定的数据
 * 
 * @author lishicun
 * 
 */
public class SsqData {

	// 匹配<td id=">开头，</td>结尾的文档
	Pattern p = Pattern.compile("<td.*id=\".*\">(.+)</td>");
	Matcher m = null;
	String data = null;

	private String getContext(String html) {

		m = p.matcher(html);// 开始编译
		data = null; // 因是类的变量,这里要清掉,否则保持前先值

		while (m.find()) {

			data = m.group(1);// 获取被匹配的部分
		}
		// System.out.println(data);
		return data;
	}

	/**
	 * 
	 * @param number
	 *            转入的期数
	 * @param flag
	 *            是否只生成10期,取值:0:按输入的期数下载,1:只下10期
	 */
	void getDataAndWriteFile(String number, String flag) {

		try {

			String addr = null;

			// 为false则清空原文件再写入,true则追加写入
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
			// 用URLConnection的connect()方法建立连接
			c.connect();
			InputStream is = c.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			
			String str = null;
			String tem = null;
			int sid = 0;
			int cid = 1;
			int times = 0;
			int i = 154; // 到第sid行时才打印
			int j = 1;
			String st = "";

			times = Integer.valueOf(number).intValue() * 9; // 共打印的期数,每一期相隔9行，故剩以9

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
			System.out.println("下载信息：号码数据下载完毕！");
			System.out.println();
			System.out.println("===========================================");

		} catch (IOException e) {
			System.out.println();
			System.out.println("下载信息：下载网址有误,或电脑没有联网.系统错误信息:" + e.getMessage());
			System.out.println();
			System.out.println("===========================================");
		}
	}

	public static void main(String[] args) throws InterruptedException {

		// if (args.length != 1) {
		// System.out.println("请选择期数，再进行操作。");
		// System.exit(0);
		// }

		SsqData ld = new SsqData();

		System.out.println("===========================================");
		System.out.println();
		System.out.println("正在从中彩网下载号码数据,此窗口请不要关闭,请稍候……");
		System.out.println();
		System.out.println("===========================================");
		ld.getDataAndWriteFile("10", "1");

		Thread.sleep(3000); 
	}
}
