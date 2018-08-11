import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test {

	/**
	 * д��־��ת����Ҫд����־��Ϣ����־�ļ������� c:\log.log
	 * @param log
	 */
	public static void writeLog(String log) {
		
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);

		try {

			//true��ʾ׷�ӣ�false��ʾ����
			FileWriter outfile1 = new FileWriter("c:/log.log", true);
			BufferedWriter outbuffer1 = new BufferedWriter(outfile1);

			outbuffer1.write("[" + dateString + "]  "+log);
			outbuffer1.write("\n");

			outbuffer1.flush();
			outfile1.close();

		} catch (IOException e) {
			System.out.println("sdfa");
		}
	}

	/**
	 * ����һ�����ڣ����������ڼ����ַ���
	 * 
	 * @param sdate
	 * @return
	 */
	public static String getWeek() {
		// ��ת��Ϊʱ��
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int hour = c.get(Calendar.DAY_OF_WEEK);
		// hour�д�ľ������ڼ��ˣ��䷶Χ 1~7
		// 1=������ 7=����������������
		return String.valueOf(hour);// new
									// SimpleDateFormat("EEEE").format(c.getTime());
	}

	public static void main(String[] args) {

		Test.writeLog("Send mail successfully!");
	}

}
