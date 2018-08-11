/**
 *  功能:往指定表中插入二维数组的数据,其中strArray[0][i]为表的字段名.
 */
package excel;

import java.sql.PreparedStatement;

import sqlserver.Sqlserver;

public class InsertBatch {

	/**
	 * 功能:往指定表中插入二维数组的数据,其中strArray[0][i]为表的字段名.
	 * 
	 * @param tableName
	 *            传入的表名
	 * @param strArray
	 *            转入的二维数据
	 * 
	 * @return true/false
	 */
	public boolean batchExceute(String tableName, String[][] strArray) {
		Sqlserver db = new Sqlserver();

		boolean flag = false;

		String sql = "insert into " + tableName + "(";
		String ask = "";

		// 读取二维数据的第一行得到操作的表的字段名
		for (int i = 0; i < strArray[0].length; i++) {
			sql = sql + strArray[0][i] + ",";
			ask = ask + "?,";
		}

		sql = sql.substring(0, sql.length() - 1) + ") values ("
				+ ask.substring(0, ask.length() - 1) + ")";

		System.out.println("预编译的语句:" + sql);

		try {
			PreparedStatement pstmt = db.getConnection().prepareStatement(sql);

			db.getConnection().setAutoCommit(false); // 不自动提交事务

			for (int j = 1; j < strArray.length; j++) {// j为行
				for (int i = 0; i < strArray[0].length; i++) {// i为列

					pstmt.setString(i + 1, strArray[j][i]);

				}
				pstmt.execute();
			}

			db.getConnection().commit();// 提交事务

			flag = true;

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return flag;

	}

}
