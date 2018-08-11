package ssqmail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMultipart;
import javax.mail.Transport;

import mylog4j.Log;

/**
 * 功能：于周二，周四，周日这三日的22:15:00发送双色球开奖信息到指定邮箱.
 * 
 * @author lishicun
 * 
 */
public class SSQMail extends Thread {

	public SSQMail() {
	}

	/**
	 * 以html形式发送邮件
	 * 
	 * @param body
	 * @return
	 * @throws MessagingException
	 */
	private static Multipart getAlternativeMultipart(String body)
			throws MessagingException {

		Multipart alternative = new MimeMultipart("alternative");// 二选一消息

		BodyPart text = new MimeBodyPart();
		text.setContent("请浏览HTML", "text/plain;charset=UTF-8");
		alternative.addBodyPart(text);

		BodyPart html = new MimeBodyPart();
		html.setContent(body, "text/html;charset=UTF-8");
		alternative.addBodyPart(html);

		return alternative;
	}

	/**
	 * 发送邮件的方法
	 * 
	 * @param Subject
	 * @param Content
	 */
	public void sendMail(String Subject, String Content) {

		Log.info("Begin to send mail!");

		Properties mailprop = new Properties();

		String from = null;
		String to = null;
		String server = null;

		
		
		try {
			mailprop
					.load(SSQMail.class.getResourceAsStream("mailinfo.properties"));

			from = mailprop.getProperty("From");

			to = mailprop.getProperty("To");

			server = mailprop.getProperty("SMTPHost");

		
		} catch (IOException ioe) {
			Log.error("SSQMail read log4j.properties file error!");
		}

		final String username = mailprop.getProperty("SMTPUsername");
		final String password = mailprop.getProperty("SMTPPassword");

		try {
			Properties prop = new Properties();
			prop.setProperty("mail.transport.protocol", "smtp");
			prop.setProperty("mail.smtp.auth", "true");

			Session session = Session.getInstance(prop, new Authenticator() {// 用户连接认证
						public PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(username,
									password);
						}
					});

			session.setDebug(true);// 开启调试

			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, InternetAddress
					.parse(to));
			message.setSubject(Subject);
			message.setSentDate(new Date());

			message.setContent(getAlternativeMultipart(Content));// 发送alternative邮件

			// message.saveChanges();// 在邮件服务中保存 送的邮件

			Transport trans = session.getTransport();
			trans.connect(server, username, password);
			trans.sendMessage(message, message
					.getRecipients(Message.RecipientType.TO));// 发送
			trans.close();

			Log.info("The mail have sent successfully!");

		} catch (Exception e) {
			Log.error(e.toString());
			e.printStackTrace();
		} finally {
		}

		Log.info("End to send mail!");
	}

	/**
	 * 获取指定网页的Html代码
	 * 
	 * @param url
	 * @return
	 */
	public String getWebContent(String url) {

		Log.info("Begin to get web html source from " + url);

		String ret = "";

		try {
			URL addr = new URL(url);
			URLConnection c = addr.openConnection();
			c.connect();
			InputStream is = c.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");// 指定UTF-8字符集，解决乱码
			BufferedReader br = new BufferedReader(isr);

			String str = null;

			while ((str = br.readLine()) != null) {

				ret = ret + str;
			}
		}

		catch (IOException e) {
			Log.error(e.toString());
		}
		Log.info("End to get web html source from " + url);
		return ret;
	}

	/**
	 * 返回当前日期是星期几
	 * 
	 * @return
	 */
	public int getWeek() {
		// 再转换为时间
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int week = c.get(Calendar.DAY_OF_WEEK);
		// hour中存的就是星期几了，其范围 1~7
		// 1=星期日 7=星期六，其他类推
		return week;// new SimpleDateFormat("EEEE").format(c.getTime());
	}

	public void run() {

		Log.info("Begin to run thread!");

		try {

			String url = "http://kaijiang.zhcw.com/kaijiang/zhcw_ssq_index.html";

			SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");

			SSQMail mail = new SSQMail();

			while (true) {

				Date currentTime = new Date();

				String dateString = formatter.format(currentTime);

				int week = mail.getWeek();

				// 周二，周四，周日开奖，于这三日的22:30:00发送开奖信息
				if (week == 1 || week == 3 || week == 5) {

					if ("22:40:00".equals(dateString.substring(11, 19))) {
						long tf = System.currentTimeMillis();

						String htmlSource = mail.getWebContent(url);
						mail.sendMail("SSQ资讯(" + dateString + ")", htmlSource);

						long tf2 = (System.currentTimeMillis() - tf) / 1000;

						Log.info("Send mail spend time:" + tf2 + " seconds!");
					}

				}

				sleep(1000);
			}

		} catch (InterruptedException e) {
			Log.error(e.toString());
		}

		Log.info("End to run thread!");
	}

	public static void main(String args[]) {
		Log.getLogger("SSQMail");
		Log.info("Enter to main program!");
		new SSQMail().run();
		Log.info("Exit to main program!");
	}
}
