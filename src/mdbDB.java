
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * ����DNS��Access���ݿ�����
 */

public class mdbDB {
	public mdbDB() {
		// ��ʼ������
	}

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = null;
		String conStr = null;

		String path = System.getProperties().getProperty("user.dir");// ��ǰĿ¼·��

		path = path + "\\test.mdb";

		System.out.println("���ݿ�����Ŀ¼��" + path);
		System.out.println();

		conStr = "jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ="
				+ path;

		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			conn = DriverManager.getConnection(conStr, "o", "struts");
			stmt = conn.createStatement();
			
			stmt.executeUpdate("INSERT INTO log4j (sdate,sthread,slevel,scategory,smessage) VALUES ('555','555','555','555','555')");
			
			sql = "select * from mdb";
			rs = stmt.executeQuery(sql);

			System.out.println("���" + " ����");
			System.out.println("==========================");

			while (rs.next()) {
				System.out.print(rs.getInt("id") + "        ");
				System.out.print(rs.getString("aa"));
				System.out.println();
			}

			rs.close();
			stmt.close();
			conn.close();
		} catch (java.lang.Exception e) {
			System.out.println(e);
		}
	}

}