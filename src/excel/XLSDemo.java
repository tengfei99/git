package excel;

/**
* <p>��ȡExcel���,����������Excel������ </p>
* <p>Description: ���Զ�ȡExcel�ļ�������,����Excel������
* </p>
* <p>Copyright: Copyright (c) Corparation 2005</p>
* <p>���򿪷�����Ϊeclipse</p>
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
     * ����Excel
     */
    private void makeXls()
    {
        Workbook workbook = null;
        try
        {
            // ����Workbook����, ֻ��Workbook����
            // ֱ�Ӵӱ����ļ�����Workbook, ������������Workbook
            InputStream ins = new FileInputStream("D:/Workspace/testproj/source.xls");
            workbook = Workbook.getWorkbook(ins);

            // �����Ѿ�������Excel�����������µĿ�д���Excel������
            File outFile = new File("D:/Workspace/testproj/test.xls");
            WritableWorkbook wwb = Workbook.createWorkbook(outFile, workbook);
            // ��ȡ��һ�Ź�����
            WritableSheet dataSheet = wwb.getSheet(0);
            //  ���ö��ᵥԪ��
            dataSheet.getSettings().setVerticalFreeze(7);
            dataSheet.getSettings().setHorizontalFreeze(2);
            
            // ����ģ������
            Vector vecData = new Vector();
            for(int i = 0; i < 50; i ++)
            {
                VireObj obj = new VireObj();
                obj.setOrgNo("00" + i + "0");
                obj.setOrgName("����" + (i + 1));
                obj.setOpenAcc((int)(100 * Math.random()));
                obj.setDestoryAcc((int)(10 * Math.random()));
                obj.setTotalAcc((int)(500 * Math.random()));
                obj.setMonthInCount((int)(500 * Math.random()));
                obj.setMonthInMoney(500 * Math.random());
                obj.setMonthOutCount((int)(500 * Math.random()));
                obj.setMonthOutMoney(500 * Math.random());
                
                vecData.add(obj);
            }            
            // ��������
            insertData(wwb, dataSheet, vecData);            
            // ����ģ��ͼ������
            Vector vecImg = new Vector();
            for(int i = 0; i < 3; i ++)
            {
                ChartImg img = new ChartImg();
                img.setImgTitle("ͼ��" + (i + 1));
                img.setImgName("D:/Workspace/testproj/images/barchart.png");
                vecImg.add(img);
            }
            // ����ͼ��
            insertImgsheet(wwb, vecImg);
            //д��Excel����
            wwb.write();
            wwb.close();
        } catch (Exception e)
        {
            YTLogger.logDebug(e);
        } finally
        {
            // �������ʱ���رն����ͷ�ռ�õ��ڴ�ռ�
            workbook.close();
        }
    }
    
    /**
     * ��������
     * @param wwb WritableWorkbook : ������
     * @param dataSheet WritableSheet : ������
     * @throws RowsExceededException
     * @throws WriteException
     */
    private void insertData(WritableWorkbook wwb, WritableSheet dataSheet, Vector vecData) throws RowsExceededException, WriteException
    {
        // ��ñ��ⵥԪ�����        
        modiStrCell(dataSheet, 2, 0, "�������н���ʡ���� ������������ҵ������/��������ϸ����2005-12��", null);
        // �޸����ݵ�Ԫ������
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
        // ɾ������
        for (int j = vecData.size() + TITLE_LENGTH; j < SHEET_HEIGHT; j++)
        {
            dataSheet.removeRow(vecData.size() + TITLE_LENGTH);
        }        
        // ���빫ʽ
        for(int i = 2; i < SHEET_WIDTH; i ++)
        {
            modiFormulaCell(dataSheet, i, vecData.size() + TITLE_LENGTH, 8, vecData.size() + TITLE_LENGTH, null);
        }        
    }

    /**
     * �޸��ַ���Ԫ���ֵ
     * @param dataSheet WritableSheet : ������
     * @param col int : ��
     * @param row int : ��
     * @param str String : �ַ�
     * @param format CellFormat : ��Ԫ�����ʽ
     * @throws RowsExceededException
     * @throws WriteException
     */
    private void modiStrCell(WritableSheet dataSheet, int col, int row, String str, CellFormat format) throws RowsExceededException, WriteException
    {
        // ��õ�Ԫ�����
        WritableCell cell = dataSheet.getWritableCell(col, row);
        // �жϵ�Ԫ�������, ������Ӧ��ת��
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
            // ���ֵ�Ԫ���޸�
            Number n1 = (Number)cell;
            n1.setValue(42.05);
        }
    }
    
    /**
     * �޸����ֵ�Ԫ���ֵ
     * @param dataSheet WritableSheet : ������
     * @param col int : ��
     * @param row int : ��
     * @param num double : ��ֵ
     * @param format CellFormat : ��Ԫ�����ʽ
     * @throws RowsExceededException
     * @throws WriteException
     */
    private void modiNumCell(WritableSheet dataSheet, int col, int row, double num, CellFormat format) throws RowsExceededException, WriteException
    {
        // ��õ�Ԫ�����
        WritableCell cell = dataSheet.getWritableCell(col, row);
        // �жϵ�Ԫ�������, ������Ӧ��ת��
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
            // ���ֵ�Ԫ���޸�
            Number lbl = (Number)cell;
            lbl.setValue(num);
        } else if (cell.getType() == CellType.LABEL)
        {
            Label lbl = (Label)cell;
            lbl.setString(String.valueOf(num));
        }
    }
    
    /**
     * �޸Ĺ�ʽ��Ԫ���ֵ
     * @param dataSheet WritableSheet : ������
     * @param col int : ��
     * @param row int : ��
     * @param startPos int : ��ʼλ��
     * @param endPos int : ����λ��
     * @param format
     * @throws RowsExceededException
     * @throws WriteException
     */
    private void modiFormulaCell(WritableSheet dataSheet, int col, int row, int startPos, int endPos, CellFormat format) throws RowsExceededException, WriteException
    {
        String f = getFormula(col, row, startPos, endPos);
        // ���빫ʽ��ֻ֧�ֲ��룬��֧���޸ģ�
        WritableCell cell = dataSheet.getWritableCell(col, row);
        if (cell.getType() == CellType.EMPTY)
        {                    
            // ��ʽ��Ԫ��
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
     * �õ���ʽ
     * @param col int : ��
     * @param row int : ��
     * @param startPos int : ��ʼλ��
     * @param endPos int : ����λ��
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
        // ��װ��ʽ
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
     * ����ͼ������
     * @param wwb WritableWorkbook : ������
     * @param vecImg Vector : ͼ������
     * @throws RowsExceededException
     * @throws WriteException
     */
    private void insertImgsheet(WritableWorkbook wwb, Vector vecImg)
            throws RowsExceededException, WriteException
    {
        // ����ͼ��
        WritableSheet imgSheet;
        if((wwb.getSheets()).length < 2)
        {
            imgSheet = wwb.createSheet("ͼ��", 1);
        } else
        {
            imgSheet = wwb.getSheet(1);
        }
        
        for (int i = 0; i < vecImg.size(); i++)
        {
            ChartImg chart = (ChartImg) vecImg.get(i);
            // ����ͼ�����
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
            // ����ͼ��Ԫ��
            insertImgCell(imgSheet, 2, 4 + 20 * i, 8, 15, chart.getImgName());
        }
    }

    /**
     * ����ͼ�񵽵�Ԫ��ͼ���ʽֻ֧��png��
     * @param dataSheet WritableSheet : ������
     * @param col int : ��
     * @param row int : ��
     * @param width int : ��
     * @param height int : ��
     * @param imgName String : ͼ���ȫ·��
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
     * ����
     * @param args
     */
    public static void main(String[] args)
    {
        XLSDemo demo = new XLSDemo();
        demo.makeXls();
    }
}

