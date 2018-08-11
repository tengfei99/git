package sqlserver;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Invoke {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ResultSet rs = null;

		Sqlserver ss = new Sqlserver();

		rs = ss.executeQuery("select * from t_treeview1");

		try {
			while (rs.next()) {
				
				System.out.println("tree.nodes[\"" + rs.getString("notesid") + "\"] = \"text:" + rs.getString("text") +"; url:"+ rs.getString("url") +";target:"+ rs.getString("target") +"\";");

			}

		} catch (SQLException e) {
			System.err.println("sqlserver.executeQuery:"+e.getMessage());
		}
		finally
		{
			ss.freememory();
		}

	}
}
