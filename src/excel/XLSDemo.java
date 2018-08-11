package excel;

/**
* <p>读取Excel表格,拷贝、更新Excel工作薄 </p>
* <p>Description: 可以读取Excel文件的内容,更新Excel工作薄
* </p>
* <p>Copyright: Copyright (c) Corparation 2005</p>
* <p>程序开发环境为eclipse</p>
* @author Walker
* @version 1.0
*/

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Vector;

import jxl.CellType;
import jxl.Workbook;
import jxl.format.CellFormat;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Formula;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class XLSDemo
{
    private static final int TITLE_LENGTH = 7;
    private static final int SHEET_WIDTH = 32;
    private static final int SHEET_HEIGHT = 116;
    
    /**
     * 创建Excel
     */
    private void makeXls()
    {
        Workbook workbook = null;
        try
        {
            // 构建Workbook对象, 只读Workbook对象
            // 直接从本地文件创建Workbook, 从输入流创建Workbook
            InputStream ins = new FileInputStream("D:/Workspace/testproj/source.xls");
            workbook = Workbook.getWorkbook(ins);

            // 利用已经创建的Excel工作薄创建新的可写入的Excel工作薄
            File outFile = new File("D:/Workspace/testproj/test.xls");
            WritableWorkbook wwb = Workbook.createWorkbook(outFile, workbook);
            // 读取第一张工作表
            WritableSheet dataSheet = wwb.getSheet(0);
            //  设置冻结单元格
            dataSheet.getSettings().setVerticalFreeze(7);
            dataSheet.getSettings().setHorizontalFreeze(2);
            
            // 测试模拟数据
            Vector vecData = new Vector();
            for(int i = 0; i < 50; i ++)
            {
                VireObj obj = new VireObj();
                obj.setOrgNo("00" + i + "0");
                obj.setOrgName("机构" + (i + 1));
                obj.setOpenAcc((int)(100 * Math.random()));
                obj.setDestoryAcc((int)(10 * Math.random()));
                obj.setTotalAcc((int)(500 * Math.random()));
                obj.setMonthInCount((int)(500 * Math.random()));
                obj.setMonthInMoney(500 * Math.random());
                obj.setMonthOutCount((int)(500 * Math.random()));
                obj.setMonthOutMoney(500 * Math.random());
                
                vecData.add(obj);
            }            
            // 插入数据
            insertData(wwb, dataSheet, vecData);            
            // 插入模拟图像数据
            Vector vecImg = new Vector();
            for(int i = 0; i < 3; i ++)
            {
                ChartImg img = new ChartImg();
                img.setImgTitle("图像" + (i + 1));
                img.setImgName("D:/Workspace/testproj/images/barchart.png");
                vecImg.add(img);
            }
            // 插入图表
            insertImgsheet(wwb, vecImg);
            //写入Excel对象
            wwb.write();
            wwb.close();
        } catch (Exception e)
        {
            YTLogger.logDebug(e);
        } finally
        {
            // 操作完成时，关闭对象，释放占用的内存空间
            workbook.close();
        }
    }
    
    /**
     * 插入数据
     * @param wwb WritableWorkbook : 工作簿
     * @param dataSheet WritableSheet : 工作表
     * @throws RowsExceededException
     * @throws WriteException
     */
    private void insertData(WritableWorkbook wwb, WritableSheet dataSheet, Vector vecData) throws RowsExceededException, WriteException
    {
        // 获得标题单元格对象        
        modiStrCell(dataSheet, 2, 0, "工商银行江苏省分行 个人网上银行业务种类/开销户明细报表（2005-12）", null);
        // 修改数据单元格数据
        for(int i = 0; i < vecData.size(); i ++)
        {
            VireObj obj = (VireObj)vecData.get(i);
            modiStrCell(dataSheet, 0, TITLE_LENGTH + i, obj.getOrgNo(), null);
            modiStrCell(dataSheet, 1, TITLE_LENGTH + i, obj.getOrgName(), null);
            modiNumCell(dataSheet, 2, TITLE_LENGTH + i, obj.getOpenAcc(), null);
            modiNumCell(dataSheet, 3, TITLE_LENGTH + i, obj.getDestoryAcc(), null);
            modiNumCell(dataSheet, 4, TITLE_LENGTH + i, obj.getTotalAcc(), null);
            modiNumCell(dataSheet, 5, TITLE_LENGTH + i, obj.getMonthInCount(), null);
            modiNumCell(dataSheet, 6, TITLE_LENGTH + i, obj.getTotalInMoney(), null);
            modiNumCell(dataSheet, 7, TITLE_LENGTH + i, obj.getMonthOutCount(), null);
            modiNumCell(dataSheet, 8, TITLE_LENGTH + i, obj.getMonthOutMoney(), null);
        }    
        // 删除空行
        for (int j = vecData.size() + TITLE_LENGTH; j < SHEET_HEIGHT; j++)
        {
            dataSheet.removeRow(vecData.size() + TITLE_LENGTH);
        }        
        // 插入公式
        for(int i = 2; i < SHEET_WIDTH; i ++)
        {
            modiFormulaCell(dataSheet, i, vecData.size() + TITLE_LENGTH, 8, vecData.size() + TITLE_LENGTH, null);
        }        
    }

    /**
     * 修改字符单元格的值
     * @param dataSheet WritableSheet : 工作表
     * @param col int : 列
     * @param row int : 行
     * @param str String : 字符
     * @param format CellFormat : 单元格的样式
     * @throws RowsExceededException
     * @throws WriteException
     */
    private void modiStrCell(WritableSheet dataSheet, int col, int row, String str, CellFormat format) throws RowsExceededException, WriteException
    {
        // 获得单元格对象
        WritableCell cell = dataSheet.getWritableCell(col, row);
        // 判断单元格的类型, 做出相应的转化
        if (cell.getType() == CellType.EMPTY)
        {
            Label lbl = new Label(col, row, str);
            if(null != format)
            {
                lbl.setCellFormat(format);
            } else
            {
                lbl.setCellFormat(cell.getCellFormat());
            }
            dataSheet.addCell(lbl);
        } else if (cell.getType() == CellType.LABEL)
        {
            Label lbl = (Label)cell;
            lbl.setString(str);
        } else if (cell.getType() == CellType.NUMBER)
        {
            // 数字单元格修改
            Number n1 = (Number)cell;
            n1.setValue(42.05);
        }
    }
    
    /**
     * 修改数字单元格的值
     * @param dataSheet WritableSheet : 工作表
     * @param col int : 列
     * @param row int : 行
     * @param num double : 数值
     * @param format CellFormat : 单元格的样式
     * @throws RowsExceededException
     * @throws WriteException
     */
    private void modiNumCell(WritableSheet dataSheet, int col, int row, double num, CellFormat format) throws RowsExceededException, WriteException
    {
        // 获得单元格对象
        WritableCell cell = dataSheet.getWritableCell(col, row);
        // 判断单元格的类型, 做出相应的转化
        if (cell.getType() == CellType.EMPTY)
        {
            Number lbl = new Number(col, row, num);
            if(null != format)
            {
                lbl.setCellFormat(format);
            } else
            {
                lbl.setCellFormat(cell.getCellFormat());
            }
            dataSheet.addCell(lbl);
        } else if (cell.getType() == CellType.NUMBER)
        {
            // 数字单元格修改
            Number lbl = (Number)cell;
            lbl.setValue(num);
        } else if (cell.getType() == CellType.LABEL)
        {
            Label lbl = (Label)cell;
            lbl.setString(String.valueOf(num));
        }
    }
    
    /**
     * 修改公式单元格的值
     * @param dataSheet WritableSheet : 工作表
     * @param col int : 列
     * @param row int : 行
     * @param startPos int : 开始位置
     * @param endPos int : 结束位置
     * @param format
     * @throws RowsExceededException
     * @throws WriteException
     */
    private void modiFormulaCell(WritableSheet dataSheet, int col, int row, int startPos, int endPos, CellFormat format) throws RowsExceededException, WriteException
    {
        String f = getFormula(col, row, startPos, endPos);
        // 插入公式（只支持插入，不支持修改）
        WritableCell cell = dataSheet.getWritableCell(col, row);
        if (cell.getType() == CellType.EMPTY)
        {                    
            // 公式单元格
            Formula lbl = new Formula(col, row, f);
            if(null != format)
            {
                lbl.setCellFormat(format);
            } else
            {
                lbl.setCellFormat(cell.getCellFormat());
            }
            dataSheet.addCell(lbl);
        } else if (cell.getType() == CellType.STRING_FORMULA)
        {
            YTLogger.logWarn("Formula modify not supported!");
        }
    }
    
    /**
     * 得到公式
     * @param col int : 列
     * @param row int : 行
     * @param startPos int : 开始位置
     * @param endPos int : 结束位置
     * @return String
     * @throws RowsExceededException
     * @throws WriteException
     */
    private String getFormula(int col, int row, int startPos, int endPos)
            throws RowsExceededException, WriteException
    {
        char base = 'A';
        char c1 = base;
        StringBuffer formula = new StringBuffer(128);
        // 组装公式
        formula.append("SUM(");
        if (col <= 25)
        {
            c1 = (char) (col % 26 + base);
            formula.append(c1).append(startPos).append(":")
                   .append(c1).append(endPos).append(")");
        } else if (col > 25)
        {
            char c2 = (char) ((col - 26) / 26 + base);
            c1 = (char) ((col - 26) % 26 + base);
            formula.append(c2).append(c1).append(startPos).append(":")
                   .append(c2).append(c1).append(endPos).append(")");
        }

        return formula.toString();
    }
    
    /**
     * 插入图表工作表
     * @param wwb WritableWorkbook : 工作簿
     * @param vecImg Vector : 图像链表
     * @throws RowsExceededException
     * @throws WriteException
     */
    private void insertImgsheet(WritableWorkbook wwb, Vector vecImg)
            throws RowsExceededException, WriteException
    {
        // 插入图像
        WritableSheet imgSheet;
        if((wwb.getSheets()).length < 2)
        {
            imgSheet = wwb.createSheet("图表", 1);
        } else
        {
            imgSheet = wwb.getSheet(1);
        }
        
        for (int i = 0; i < vecImg.size(); i++)
        {
            ChartImg chart = (ChartImg) vecImg.get(i);
            // 插入图像标题
            Label lbl = new Label(0, 2 + 20 * i, chart.getImgTitle());
            WritableFont font = new WritableFont(WritableFont.ARIAL,
                    WritableFont.DEFAULT_POINT_SIZE, WritableFont.NO_BOLD, false,
                    UnderlineStyle.NO_UNDERLINE, Colour.DARK_BLUE2);
            WritableCellFormat background = new WritableCellFormat(font);
            background.setWrap(true);
            background.setBackground(Colour.GRAY_25);
            imgSheet.mergeCells(0, 2 + 20 * i, 9, 2 + 20 * i);
            lbl.setCellFormat(background);
            imgSheet.addCell(lbl);
            // 插入图像单元格
            insertImgCell(imgSheet, 2, 4 + 20 * i, 8, 15, chart.getImgName());
        }
    }

    /**
     * 插入图像到单元格（图像格式只支持png）
     * @param dataSheet WritableSheet : 工作表
     * @param col int : 列
     * @param row int : 行
     * @param width int : 宽
     * @param height int : 高
     * @param imgName String : 图像的全路径
     * @throws RowsExceededException
     * @throws WriteException
     */
    private void insertImgCell(WritableSheet dataSheet, int col, int row, int width,
            int height, String imgName) throws RowsExceededException, WriteException
    {
        File imgFile = new File(imgName);
        WritableImage img = new WritableImage(col, row, width, height, imgFile);
        dataSheet.addImage(img);
    }
    
    /**
     * 测试
     * @param args
     */
    public static void main(String[] args)
    {
        XLSDemo demo = new XLSDemo();
        demo.makeXls();
    }
}

