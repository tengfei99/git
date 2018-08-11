import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test {

	/**
	 * 写日志，转入需要写的日志信息，日志文件保存在 c:\log.log
	 * @param log
	 */
	public static void writeLog(String log) {
		
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);

		try {

			//true表示追加，false表示覆盖
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
	 * 根据一个日期，返回是星期几的字符串
	 * 
	 * @param sdate
	 * @return
	 */
	public static String getWeek() {
		// 再转换为时间
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int hour = c.get(Calendar.DAY_OF_WEEK);
		// hour中存的就是星期几了，其范围 1~7
		// 1=星期日 7=星期六，其他类推
		return String.valueOf(hour);// new
									// SimpleDateFormat("EEEE").format(c.getTime());
	}

	public static void main(String[] args) {

		Test.writeLog("Send mail successfully!");
	}

}
