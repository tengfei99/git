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
public class LotteryData {

	// 匹配<div align="center">开头，</div>结尾的文档
	Pattern p = Pattern.compile("<div align=\"center\">([^</div>]*)");
	Matcher m = null;
	String data = null;

	// 得到html的div里的内容
	private String getContext(String html) {

		m = p.matcher(html);// 开始编译
		data = null; // 因是类的变量,这里要清掉,否则保持前先值

		while (m.find()) {

			data = m.group(1);// 获取被匹配的部分
		}

		return data;
	}

	void getDataAndWriteFile(String startNum, String endNum) {

		try {

			// 为false则清空原文件再写入,true则追加写入
			FileWriter outfile1 = new FileWriter("lotterydata.txt", false);
			BufferedWriter outbuffer1 = new BufferedWriter(outfile1);

			String addr = "http://lotterychart.sports.cn/table/index_letouzh_table.jsp?lotteryid=41&startNum="
					+ startNum + "&endNum=" + endNum;
			URL url = new URL(addr);
			URLConnection c = url.openConnection();
			// 用URLConnection的connect()方法建立连接
			c.connect();
			InputStream is = c.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String str = null;
			String tem = null;
			int sid = 0; // 到第76行时才打印
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

		if (args.length != 2) {
			System.out.println(" 请输入两个参数,第一个参数为开始的期号,第二个参数为结束的期号!期号格式:09066.");
			System.exit(0);
		}
		LotteryData ld = new LotteryData();

		System.out.println("===========================================");
		System.out.println();
		System.out.println("正在从体彩网下载号码数据,此窗口请不要关闭,请稍候……");
		System.out.println();
		System.out.println("===========================================");
		ld.getDataAndWriteFile(args[0].trim(), args[1].trim());

		Thread.sleep(3000);
	}
}
