package readhtml;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

	public static void main(String args[]) {
		String html = "<div align='center'> 04 13 17 32 35 + 03 06</div>";
		// ��ʾ�����൱��String html=getHtml(String urlString);
		List<String> resultList = getContext(html);
		for (Iterator<String> iterator = resultList.iterator(); iterator
				.hasNext();) {
			String context = (String) iterator.next();
			System.out.println(context);
		}
	}

	/**
	 * ��ȡ"<title>XXXX</title>"�е�����XXXX
	 * 
	 * @param html
	 *            Ҫ������html�ĵ�����
	 * @return ������������Զ��ƥ�䣬ÿ��ƥ��Ľ�����ĵ��г��ֵ��Ⱥ�˳����ӽ����List
	 */
	public static List<String> getContext(String html) {
		List<String> resultList = new ArrayList<String>();
		Pattern p = Pattern.compile("<div align='center'>([^</div>]*)");// ƥ��<title>��ͷ��</title>��β���ĵ�
		Matcher m = p.matcher(html);// ��ʼ����
		while (m.find()) {
			resultList.add(m.group(1));// ��ȡ��ƥ��Ĳ���
		}
		return resultList;
	}
}
