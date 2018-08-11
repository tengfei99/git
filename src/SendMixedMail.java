import java.io.File;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/** */
/**
 * java mail 发送复杂邮件,包括两个附件,html消息,html中嵌入两张图片 还包括发送alternative邮件的源码.
 * 
 * @author zhangle
 * 
 */
public class SendMixedMail {
	// 以下属性根据自己情况设置.
	// 以下属性根据自己情况设置.
	 private static String protocol = "smtp";
	 private static String from = "tengfei99@163.com";
	 private static String to = "lishicun@huawei.com";
	 private static String body = "<html><body><a href='http://www.csdn.net'>I love you! csdn </a><img src='cid:img1'/><img src='cid:img2'/></body></html>";
	 private static String subject = "mail test";
	 private static String server = "smtp.163.com";
	 private static  String username = "tengfei99";// from mail name
	 private static  String password = "java168..";// from mail password

	public static void main(String[] args) throws Exception, MessagingException {
		Properties prop = new Properties();
		prop.setProperty("mail.transport.protocol", protocol);
		prop.setProperty("mail.smtp.auth", "true");

		Session session = Session.getInstance(prop, new Authenticator() {// 用户连接认证
					public PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});
		session.setDebug(true);// 开启调试

		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(from));
		message.setRecipients(Message.RecipientType.TO, InternetAddress
				.parse(to));
		message.setSubject(subject);
		message.setSentDate(new Date());
		// message.setText(body);//发送纯文本消息
		message.setContent(getAlternativeMultipart());//发送alternative邮件
		//message.setContent(getMultipart());// 发送复杂文本消息
		message.saveChanges();// 保存消息

		Transport trans = session.getTransport();
		trans.connect(server, username, password);
		trans.sendMessage(message, message
				.getRecipients(Message.RecipientType.TO));// 发送
		trans.close();
	}

	/** */
	/**
	 * 获得复杂邮件Multipart对象
	 * 
	 * @return
	 * @throws MessagingException
	 */
	private static Multipart getMultipart() throws MessagingException {

		Multipart multi = new MimeMultipart("mixed");// 混合MIME消息

		multi.addBodyPart(createContent());
		multi.addBodyPart(createAttachment(new File("D:/photo.xls")));// 嵌入附件
		multi.addBodyPart(createAttachment(new File("D:/wall_3d.jpg")));

		return multi;

	}

	/** */
	/**
	 * 创建正文
	 * 
	 * @return
	 * @throws MessagingException
	 */
	private static BodyPart createContent() throws MessagingException {
		BodyPart content = new MimeBodyPart();
		Multipart relate = new MimeMultipart("related");// 组合MIME消息

		relate.addBodyPart(createHtmlBody());
		relate.addBodyPart(createImagePart(new File("D:/a4f29fea2efbe1bbc15a76cbad6e1b52984883010d482ac8b4f786a04c647771b1285dca13fe037517dddb54812aa5eb94a6bddc5ce0624b6f085add77f3e42e.jpg"), "img1"));// 嵌入图片
		relate.addBodyPart(createImagePart(new File("D:/IMG_3148.JPG"), "img2"));

		content.setContent(relate);
		return content;
	}

	/** */
	/**
	 * 创建图片
	 * 
	 * @param file
	 * @param name
	 * @return
	 * @throws MessagingException
	 */
	private static BodyPart createImagePart(File file, String name)
			throws MessagingException {
		MimeBodyPart image = new MimeBodyPart();

		DataSource ds = new FileDataSource(file);
		image.setDataHandler(new DataHandler(ds));
		image.setFileName(name);
		// image.setContentID(name);

		return image;
	}

	/** */
	/**
	 * 创建html消息
	 * 
	 * @return
	 * @throws MessagingException
	 */
	private static BodyPart createHtmlBody() throws MessagingException {
		BodyPart html = new MimeBodyPart();
		html.setContent(body, "text/html;charset=gbk");
		return html;
	}

	/** */
	/**
	 * 创建附件
	 * 
	 * @param file
	 * @return
	 * @throws MessagingException
	 */
	private static BodyPart createAttachment(File file)
			throws MessagingException {
		BodyPart attach = new MimeBodyPart();
		DataSource ds = new FileDataSource(file);

		attach.setDataHandler(new DataHandler(ds));
		attach.setFileName(ds.getName());

		return attach;
	}

	/** */
	/**
	 * 获取alternative邮件
	 * 
	 * @return
	 * @throws MessagingException
	 */
	private static Multipart getAlternativeMultipart()
			throws MessagingException {

		Multipart alternative = new MimeMultipart("alternative");// 二选一消息

		BodyPart text = new MimeBodyPart();
		text.setContent("请浏览HTML", "text/plain;charset=gbk");
		alternative.addBodyPart(text);

		BodyPart html = new MimeBodyPart();
		html.setContent(body, "text/html;charset=gbk");
		alternative.addBodyPart(html);

		return alternative;
	}
}
