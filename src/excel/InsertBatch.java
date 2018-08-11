/**
 *  ����:��ָ�����в����ά���������,����strArray[0][i]Ϊ����ֶ���.
 */
package excel;

import java.sql.PreparedStatement;

import sqlserver.Sqlserver;

public class InsertBatch {

	/**
	 * ����:��ָ�����в����ά���������,����strArray[0][i]Ϊ����ֶ���.
	 * 
	 * @param tableName
	 *            ����ı���
	 * @param strArray
	 *            ת��Ķ�ά����
	 * 
	 * @return true/false
	 */
	public boolean batchExceute(String tableName, String[][] strArray) {
		Sqlserver db = new Sqlserver();

		boolean flag = false;

		String sql = "insert into " + tableName + "(";
		String ask = "";

		// ��ȡ��ά���ݵĵ�һ�еõ������ı���ֶ���
		for (int i = 0; i < strArray[0].length; i++) {
			sql = sql + strArray[0][i] + ",";
			ask = ask + "?,";
		}

		sql = sql.substring(0, sql.length() - 1) + ") values ("
				+ ask.substring(0, ask.length() - 1) + ")";

		System.out.println("Ԥ��������:" + sql);

		try {
			PreparedStatement pstmt = db.getConnection().prepareStatement(sql);

			db.getConnection().setAutoCommit(false); // ���Զ��ύ����

			for (int j = 1; j < strArray.length; j++) {// jΪ��
				for (int i = 0; i < strArray[0].length; i++) {// iΪ��

					pstmt.setString(i + 1, strArray[j][i]);

				}
				pstmt.execute();
			}

			db.getConnection().commit();// �ύ����

			flag = true;

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return flag;

	}

}
