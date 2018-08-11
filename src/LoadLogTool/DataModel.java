package LoadLogTool;

/**
 * 功能：数据模型，通过set,get存取数据，所有属性在此定义管理
 * 
 * @author lishicun l00101203
 * 
 */
public class DataModel {

	// 平台的IP地址
	private String IP;

	// 登录平台的用户名
	private String user;

	// 登录平台的密码
	private String password;

	// 日志在平台中所在的路径
	private String unixpath;

	private String localpath;

	private String logfilename;

	private String zipfilename;

	private String viewtime;

	private String iscompress;

	private String ftpmode;

	/**
	 * 获取IP地址
	 * 
	 * @return
	 */
	public String getIP() {
		return IP;
	}

	/**
	 * 设置IP地址
	 * 
	 * @param IP
	 */
	public void setIP(String IP) {
		this.IP = IP;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUnixpath() {
		return unixpath;
	}

	public void setUnixpath(String unixpath) {
		this.unixpath = unixpath;
	}

	public String getLocalpath() {
		return localpath;
	}

	public void setLocalpath(String localpath) {
		this.localpath = localpath;
	}

	public String getLogfilename() {
		return logfilename;
	}

	public void setLogfilename(String logfilename) {
		this.logfilename = logfilename;
	}

	public String getZipfilename() {
		return zipfilename;
	}

	public void setZipfilename(String zipfilename) {
		this.zipfilename = zipfilename;
	}

	public String getViewtime() {
		return viewtime;
	}

	public void setViewtime(String viewtime) {
		this.viewtime = viewtime;
	}

	public String getIscompress() {
		return iscompress;
	}

	public void setIscompress(String iscompress) {
		this.iscompress = iscompress;
	}

	public String getFtpmode() {
		return ftpmode;
	}

	public void setFtpmode(String ftpmode) {
		this.ftpmode = ftpmode;
	}
}