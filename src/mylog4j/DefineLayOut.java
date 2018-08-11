package mylog4j;

import org.apache.log4j.HTMLLayout;

/**
 * 解决Log4j中发送邮件中乱码问题，重新指定字符为UTF-8
 * @author li
 *
 */
public class DefineLayOut extends HTMLLayout{
	public String getContentType() {
		return "text/html;charset=UTF-8";

	}
}
