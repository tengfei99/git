package mylog4j;

public class Log4jTest {

	public static void main(String[] args) {

		Log.getLogger("SSQ");

		Log.info("info");
		Log.error("error");
		Log.debug("debug");
		Log.fatal("fatal");
		Log.warn("warn");

	}

}
