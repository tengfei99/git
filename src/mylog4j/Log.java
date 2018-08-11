package mylog4j;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


/**
 * 功能：自定义Log4j日志类，Log4j的配置文件在此定义
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
