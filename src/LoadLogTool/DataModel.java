package LoadLogTool;

/**
 * ���ܣ�����ģ�ͣ�ͨ��set,get��ȡ���ݣ����������ڴ˶������
 * 
 * @author lishicun l00101203
 * 
 */
public class DataModel {

	// ƽ̨��IP��ַ
	private String IP;

	// ��¼ƽ̨���û���
	private String user;

	// ��¼ƽ̨������
	private String password;

	// ��־��ƽ̨�����ڵ�·��
	private String unixpath;

	private String localpath;

	private String logfilename;

	private String zipfilename;

	private String viewtime;

	private String iscompress;

	private String ftpmode;

	/**
	 * ��ȡIP��ַ
	 * 
	 * @return
	 */
	public String getIP() {
		return IP;
	}

	/**
	 * ����IP��ַ
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