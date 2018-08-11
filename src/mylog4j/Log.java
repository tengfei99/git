package mylog4j;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


/**
 * ���ܣ��Զ���Log4j��־�࣬Log4j�������ļ��ڴ˶���
 * 
 * @author li
 * 
 */
public class Log {

	static Logger logger = null;

	static {
		PropertyConfigurator.configure("log4j.properties");
	}

	public static void getLogger(String classname) {

		logger = Logger.getLogger(classname);

	}

	public static void info(String info) {
		logger.info(info);
	}

	public static void debug(String message) {
		logger.debug(message);
	}

	public static void fatal(String message) {
		logger.fatal(message);
	}

	public static void warn(String message) {
		logger.warn(message);
	}

	public static void error(String message) {
		logger.error(message);
	}

}
