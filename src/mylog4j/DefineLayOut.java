package mylog4j;

import org.apache.log4j.HTMLLayout;

/**
 * ���Log4j�з����ʼ����������⣬����ָ���ַ�ΪUTF-8
 * @author li
 *
 */
public class DefineLayOut extends HTMLLayout{
	public String getContentType() {
		return "text/html;charset=UTF-8";

	}
}
