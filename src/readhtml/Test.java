package readhtml;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

	public static void main(String args[]) {
		String html = "<div align='center'> 04 13 17 32 35 + 03 06</div>";
		// 简单示例，相当于String html=getHtml(String urlString);
		List<String> resultList = getContext(html);
		for (Iterator<String> iterator = resultList.iterator(); iterator
				.hasNext();) {
			String context = (String) iterator.next();
			System.out.println(context);
		}
	}

	/**
	 * 提取"<title>XXXX</title>"中的文字XXXX
	 * 
	 * @param html
	 *            要解析的html文档内容
	 * @return 解析结果，可以多次匹配，每次匹配的结果按文档中出现的先后顺序添加进结果List
	 */
	public static List<String> getContext(String html) {
		List<String> resultList = new ArrayList<String>();
		Pattern p = Pattern.compile("<div align='center'>([^</div>]*)");// 匹配<title>开头，</title>结尾的文档
		Matcher m = p.matcher(html);// 开始编译
		while (m.find()) {
			resultList.add(m.group(1));// 获取被匹配的部分
		}
		return resultList;
	}
}
