package sqlserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * 
 * @author Administrator
 * @Description ���ݿ������
 */
public class Sqlserver {

	private Statement stmt = null;
	private Connection conn = null;
	private ResultSet rs = null;

	private static String driver = "";
	private static String url = "";
	private static String user = "";
	private static String password = "";

	/**
	 * static ��������ִ��
	 */
	static {
		try {
			Properties prop = new Properties();
			DesEncrypt des = new DesEncrypt();
			des.getKey();
			prop.load(Sqlserver.class.getResourceAsStream("conn.properties"));

			driver = prop.getProperty("driver");
			url = prop.getProperty("url");
			user = prop.getProperty("user");
			password = des.getDesString(prop.getProperty("password"));

		} catch (Exception e) {
		}
	}

	public Sqlserver() {
		try {
			Class.forName(driver);
		} catch (java.lang.ClassNotFoundException e) {
			System.err.println("sqlserver():" + e.getMessage());
		}
	}

	/**
	 * ����JDBC�����Ӵ�
	 * 
	 * @param JdbcUrl
	 */
	public void setJdbcUrl(String JdbcUrl) {
		url = JdbcUrl;
	}

	/**
	 * ���õ�¼���ݿ���û���
	 * 
	 * @param hostuser
	 */
	public void setUser(String dbUser) {
		user = dbUser;
	}

	/**
	 * ���õ�¼���ݵ�����
	 * 
	 * @param hostpassword
	 */
	public void setPassword(String dbPassword) {
		password = dbPassword;
	}

	/**
	 * �õ����ݿ�����:connection
	 * 
	 * @return connection
	 */
	public Connection getConnection() {
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			System.out.println("�������ݿ�ʧ��:" + e.toString());
		}

		return conn;
	}

	/**
	 * createStatement( int Type ,int concurency) ����˵�����£�
	 * <p>
	 * ����Type ������ִ�в�ѯ��Ľ�������α��ƶ���ʽ������������ȡֵ�� ResultSet.TYPE_FORWARD_ONLY
	 * ������α�ֻ�������ƶ� ResultSet.TYPE_SCROLL_INSENSITIVE
	 * ������α���������ƶ��������ݿ����ݷ����仯���������䡣 ResultSet.TYPE_SCROLL_SENSITIVE
	 * ������α���������ƶ������ݿ����ݷ����仯����������ͬ���仯��
	 * <p>
	 * ����concurency�������Ƿ�����ý����¼���������ݿ��еı�ȡֵ���£� ResultSet.CONCUR_READ_ONLY
	 * �����ֻ�ܶ������ܸ������ݱ��е����ݡ� ResultSet.CONCUR_UPDATABLE �ý����¼�����Ը������ݱ��е����ݡ�
	 * 
	 * @param sql
	 *            �����ѯ��sql���
	 * @return ResultSet �����α꼯
	 */
	public ResultSet executeQuery(String sql) {
		try {
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery(sql);
		} catch (SQLException ex) {
			System.out.println("sqlserver.executeQuery:" + ex.toString());
		}

		return rs;
	}

	public boolean executeUpdate(String sql) {
		try {
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			return true;
		} catch (SQLException exx) {
			System.err.println("sqlserver.executeQuery:" + exx.getMessage());
			return false;
		}
	}

	public void freememory() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception ex) {
			System.err.println("sqlserver.close:" + ex.getMessage());
		}
	}
}
