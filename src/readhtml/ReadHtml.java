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
 * Function:读取网页上的Html源码
 * 
 * @author li
 * 
 */
public class ReadHtml {

	public static void getContext(String html) {
		// List<String> resultList = new ArrayList<String>();
		Pattern p = Pattern.compile("<div align=\"center\">([^</div>]*)");// 匹配<title>开头，</title>结尾的文档
		Matcher m = p.matcher(html);// 开始编译
		while (m.find()) {
			
			System.out.println(m.group(1));// 获取被匹配的部分
		}
	}

	void display() {
		//byte buf[] = new byte[1024];
		try {
			// System.out.print("请输入文件的URL地址:");
			// 读取用户输入的URL
			// InputStream consoleis = System.in;
			// int count = consoleis.read(buf);
			// String addr = new String(buf, 0, count);
			// System.out.println("内容类型: " + addr);
			// 将用户输入的URL字符串传入URL类对象
			String addr = "http://lotterychart.sports.cn/table/index_letouzh_table.jsp?lotteryid=41&startNum=09056&endNum=09069";
			URL url = new URL(addr);
			// 创建URLConnection对象，用URL的openConnection方法将连接通过返回给URLConnection的对象
			// 实际上URL的openConnection的返回值就是一个URLConnection
			URLConnection c = url.openConnection(); // *
			// 用URLConnection的connect()方法建立连接
			c.connect(); // *
			// 显示该连接的相关信息，这些都是URLConnection的方法
			// System.out.println("内容类型: " + c.getContentType());
			// System.out.println("内容长度: " + c.getContentLength());
			// System.out.println("创建日期: " + new Date(c.getDate()));
			// System.out.println("最后修改日期: " + new Date(c.getLastModified()));
			// System.out.println("终止日期: " + new Date(c.getExpiration()));

			InputStream is = c.getInputStream(); // *
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String str = null;
			int sid = 0; // 到第76行时才打印
			while ((str = br.readLine()) != null) {
				// System.out.println(str);
				sid = sid + 1;
				if (sid >= 76) {
					getContext(str);
				}
			}

		} catch (IOException e) {
			System.out.println("输入的网址有误,或电脑没有联网.系统错误信息:" +e.getMessage());
		}
	}

	public static void main(String[] args) {
		ReadHtml app = new ReadHtml();
		app.display();
	}
}
