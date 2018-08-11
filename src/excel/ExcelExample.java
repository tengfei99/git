package excel;

import java.io.File;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import sqlserver.Sqlserver;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.DateFormat;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExcelExample {

	/**
	 * ���ܣ����������ResultSet���ļ�·��FilePath����ָ�����ļ�·����ResultSet����������Excel.
	 * 
	 * @param rs
	 *            ת���ResultSet
	 * @param FilePath
	 *            ����Excel���ļ�·��+�ļ���
	 */
	public void WriteExcel(ResultSet rs, String FilePath) {

		int row = 1; // ���ñ�����������

		int count = 0;// �ֶ���

		int height = 15;// �����и�

		String fieldvalue = ""; // �����ֶ�ֵ

		if (FilePath == null || "".equals(FilePath.trim())) {
			System.out.println("��Ϣ������Excel�ļ�·��Ϊ�գ�����ѡ�񱣴���ļ�·�����ٲ�����");
			return;
		}

		FilePath = FilePath.replace('\\', '/');

		try {
			WritableWorkbook wwb = Workbook.createWorkbook(new File(FilePath));
			WritableSheet ws = wwb.createSheet("Sheet1", 0);

			/**
			 * ҳ���ӡ��ʽ�趨��jxl.format.PageOrientation.LANDSCAPEΪ���ӡ.PORTRAITΪ����ӡ
			 */
			ws.setPageSetup(jxl.format.PageOrientation.LANDSCAPE);

			Label labelC;

			// �������ʽ��ʾ��ʽ
			// begin-------------------------------------------------------------------

			// �������������ʾ��ʽ:title ����Ӵ֣�����
			WritableFont wfc = new WritableFont(WritableFont.TAHOMA, 10,
					WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
					jxl.format.Colour.BLACK);
			WritableCellFormat title = new WritableCellFormat(wfc);

			title.setBackground(jxl.format.Colour.YELLOW);
			title.setAlignment(jxl.format.Alignment.CENTRE);
			title.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			title.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);

			title.setWrap(true);// �Զ�����
			ws.setRowView(0, height); // �и�

			// ������ͨ����ʾ��ʽ��body ���壬����
			WritableFont wf = new WritableFont(jxl.write.WritableFont
					.createFont("SimSun"), 10);
			WritableCellFormat body = new WritableCellFormat(wf);

			body.setAlignment(jxl.format.Alignment.CENTRE);
			body.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			body.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);
			// body.setWrap(true);

			// ����ʱ�����ʾ��ʽ:dateFormat
			WritableFont datewf = new WritableFont(jxl.write.WritableFont
					.createFont("SimSun"), 10);
			DateFormat customDateFormat = new DateFormat("yyyy-mm-dd");
			WritableCellFormat dateFormat = new WritableCellFormat(datewf,
					customDateFormat);

			dateFormat.setAlignment(jxl.format.Alignment.CENTRE);
			dateFormat
					.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			dateFormat.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);

			// �������ʽ��ʾ��ʽ end
			// -------------------------------------------------------------------

			// ��ȡResultSet�е��ֶ������ñ��ı���
			ResultSetMetaData rsm = rs.getMetaData();
			count = rsm.getColumnCount();
			for (int i = 1; i <= count; i++) {
				labelC = new Label(i - 1, 0, rsm.getColumnName(i), title);
				ws.setColumnView(i - 1, 15);
				ws.addCell(labelC);
			}

			// ��ResultSet�ж�ȡ�������Excel���
			while (rs.next()) {

				ws.setRowView(row, height); // �и�

				for (int i = 1; i <= count; i++) {
					fieldvalue = rs.getString(i);
					labelC = new Label(i - 1, row, (fieldvalue == null ? ""
							: fieldvalue.trim()), body);
					ws.addCell(labelC);
				}

				row += 1; // ������1.
			}

			// д�벢�ر�
			wwb.write();
			System.out.println("��Ϣ�������ݵ��뵽Excel��ϣ��ļ�·��Ϊ��" + FilePath);
			wwb.close();

		} catch (SQLException sqle) {
			System.out.println(sqle.getMessage());
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}

	/**
	 * ����:��ȡExcel�������ݵ�һ����ά����,���������ά����,���е�һάΪ��,�ڶ�άΪ��.
	 * <p>
	 * ��:�������ά����Ϊst,�õ���һά�ĳ���Ϊ:st.length,�ڶ�ά�ĳ���Ϊ: st[0].length.
	 * <p>
	 * 
	 * @param FileName
	 *            �ļ�·��+�ļ���
	 * @return ��ά����
	 */
	public String[][] ReadWrite(String FileName) {

		if (FileName == null || "".equals(FileName.trim())) {
			System.out.println("��Ϣ�������Excel�ļ�·��Ϊ�գ�����ѡ���ȡ���ļ����ٲ�����");
			return null;
		}

		FileName = FileName.replace('\\', '/');

		Workbook workbook;
		Sheet sheet;
		String[][] rc = null;

		try {
			workbook = Workbook.getWorkbook(new File(FileName));
			sheet = workbook.getSheet(0);// ��ȡ��һ��������

			int colnum = sheet.getColumns();// �õ�����
			int row = sheet.getRows();// �õ�����

			rc = new String[row][colnum];

			for (int j = 0; j < row; j++) {// jΪ��
				for (int i = 0; i < colnum; i++) {// iΪ��
					Cell cell = sheet.getCell(i, j);
					rc[j][i] = cell.getContents();// ��õ�Ԫ����
				}
			}

			workbook.close();

		} catch (Exception e) {

			System.out.print(e.getMessage());
		}

		return rc;
	}

	/**
	 * ����:������ڵ�����ʾ
	 * @param args
	 */
	public static void main(String[] args) {

		ExcelExample excel = new ExcelExample();
		InsertBatch batch = new InsertBatch();
		
		Sqlserver db = new Sqlserver();

		String sql = "select test1,test2,test3 from t_test order by id desc";
		ResultSet rs = db.executeQuery(sql);

		excel.WriteExcel(rs, "D:\\test.xls");

		String[][] st = excel.ReadWrite("D:\\test.xls");

		if(batch.batchExceute("t_test", st))
		{
			System.out.println("��Ϣ:ִ�������������ݳɹ�!" );
		}

	}

}
