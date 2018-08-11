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
	 * 功能：根据输入的ResultSet和文件路径FilePath，在指定的文件路径将ResultSet的内容生成Excel.
	 * 
	 * @param rs
	 *            转入的ResultSet
	 * @param FilePath
	 *            保存Excel的文件路径+文件名
	 */
	public void WriteExcel(ResultSet rs, String FilePath) {

		int row = 1; // 设置表格的行数变量

		int count = 0;// 字段数

		int height = 15;// 表格的行高

		String fieldvalue = ""; // 保存字段值

		if (FilePath == null || "".equals(FilePath.trim())) {
			System.out.println("信息：生成Excel文件路径为空，请先选择保存的文件路径，再操作！");
			return;
		}

		FilePath = FilePath.replace('\\', '/');

		try {
			WritableWorkbook wwb = Workbook.createWorkbook(new File(FilePath));
			WritableSheet ws = wwb.createSheet("Sheet1", 0);

			/**
			 * 页面打印格式设定：jxl.format.PageOrientation.LANDSCAPE为横打印.PORTRAIT为竖打印
			 */
			ws.setPageSetup(jxl.format.PageOrientation.LANDSCAPE);

			Label labelC;

			// 制作表格式显示格式
			// begin-------------------------------------------------------------------

			// 制作表格标题的显示格式:title 宋体加粗，居中
			WritableFont wfc = new WritableFont(WritableFont.TAHOMA, 10,
					WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
					jxl.format.Colour.BLACK);
			WritableCellFormat title = new WritableCellFormat(wfc);

			title.setBackground(jxl.format.Colour.YELLOW);
			title.setAlignment(jxl.format.Alignment.CENTRE);
			title.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			title.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);

			title.setWrap(true);// 自动换行
			ws.setRowView(0, height); // 行高

			// 制作普通的显示格式：body 宋体，居中
			WritableFont wf = new WritableFont(jxl.write.WritableFont
					.createFont("SimSun"), 10);
			WritableCellFormat body = new WritableCellFormat(wf);

			body.setAlignment(jxl.format.Alignment.CENTRE);
			body.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			body.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);
			// body.setWrap(true);

			// 制作时间的显示格式:dateFormat
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

			// 制作表格式显示格式 end
			// -------------------------------------------------------------------

			// 读取ResultSet中的字段名设置表格的标题
			ResultSetMetaData rsm = rs.getMetaData();
			count = rsm.getColumnCount();
			for (int i = 1; i <= count; i++) {
				labelC = new Label(i - 1, 0, rsm.getColumnName(i), title);
				ws.setColumnView(i - 1, 15);
				ws.addCell(labelC);
			}

			// 从ResultSet中读取数据填充Excel表格
			while (rs.next()) {

				ws.setRowView(row, height); // 行高

				for (int i = 1; i <= count; i++) {
					fieldvalue = rs.getString(i);
					labelC = new Label(i - 1, row, (fieldvalue == null ? ""
							: fieldvalue.trim()), body);
					ws.addCell(labelC);
				}

				row += 1; // 行数加1.
			}

			// 写入并关闭
			wwb.write();
			System.out.println("信息：将数据导入到Excel完毕，文件路径为：" + FilePath);
			wwb.close();

		} catch (SQLException sqle) {
			System.out.println(sqle.getMessage());
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}

	/**
	 * 功能:读取Excel表格的数据到一个二维数组,返回这个二维数组,其中第一维为行,第二维为列.
	 * <p>
	 * 附:设这个二维数组为st,得到第一维的长度为:st.length,第二维的长度为: st[0].length.
	 * <p>
	 * 
	 * @param FileName
	 *            文件路径+文件名
	 * @return 二维数组
	 */
	public String[][] ReadWrite(String FileName) {

		if (FileName == null || "".equals(FileName.trim())) {
			System.out.println("信息：读入的Excel文件路径为空，请先选择读取的文件，再操作！");
			return null;
		}

		FileName = FileName.replace('\\', '/');

		Workbook workbook;
		Sheet sheet;
		String[][] rc = null;

		try {
			workbook = Workbook.getWorkbook(new File(FileName));
			sheet = workbook.getSheet(0);// 读取第一个工作表

			int colnum = sheet.getColumns();// 得到列数
			int row = sheet.getRows();// 得到行数

			rc = new String[row][colnum];

			for (int j = 0; j < row; j++) {// j为行
				for (int i = 0; i < colnum; i++) {// i为列
					Cell cell = sheet.getCell(i, j);
					rc[j][i] = cell.getContents();// 获得单元数据
				}
			}

			workbook.close();

		} catch (Exception e) {

			System.out.print(e.getMessage());
		}

		return rc;
	}

	/**
	 * 功能:函数入口调用演示
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
			System.out.println("信息:执行批量插入数据成功!" );
		}

	}

}
