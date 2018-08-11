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
 * @Description 数据库操作类
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
	 * static 都会首先执行
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
	 * 设置JDBC的连接串
	 * 
	 * @param JdbcUrl
	 */
	public void setJdbcUrl(String JdbcUrl) {
		url = JdbcUrl;
	}

	/**
	 * 设置登录数据库的用户名
	 * 
	 * @param hostuser
	 */
	public void setUser(String dbUser) {
		user = dbUser;
	}

	/**
	 * 设置登录数据的密码
	 * 
	 * @param hostpassword
	 */
	public void setPassword(String dbPassword) {
		password = dbPassword;
	}

	/**
	 * 得到数据库连接:connection
	 * 
	 * @return connection
	 */
	public Connection getConnection() {
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			System.out.println("连接数据库失败:" + e.toString());
		}

		return conn;
	}

	/**
	 * createStatement( int Type ,int concurency) 参数说明如下：
	 * <p>
	 * 参数Type 决定了执行查询后的结果集的游标移动方式。有如下三种取值： ResultSet.TYPE_FORWARD_ONLY
	 * 结果集游标只能向下移动 ResultSet.TYPE_SCROLL_INSENSITIVE
	 * 结果集游标可以上下移动，但数据库内容发生变化后结果集不变。 ResultSet.TYPE_SCROLL_SENSITIVE
	 * 结果集游标可以上下移动，数据库内容发生变化后结果集内容同步变化。
	 * <p>
	 * 参数concurency决定了是否可以用结果记录集更新数据库中的表，取值如下： ResultSet.CONCUR_READ_ONLY
	 * 结果集只能读，不能更新数据表中的数据。 ResultSet.CONCUR_UPDATABLE 用结果记录集可以更新数据表中的数据。
	 * 
	 * @param sql
	 *            传入查询的sql语句
	 * @return ResultSet 返回游标集
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
