package Excel;



import java.io.*;
import java.sql.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import jxl.*;
import jxl.format.*;
import jxl.write.*;
import jxl.write.Label;
import java.awt.Color;
import javax.swing.border.*;

/**
 * <p>Title: 盢戈旧Excelゅン</p>
 * <p>Description: 盢戈旧Excelゅン</p>
 * <p>Copyright: Copyright (c) 1505</p>
 * <p>Company: YuHeng </p>
 * @author 
 * @version 1.0
 */

public class inforDown extends JFrame {

  JFileChooser jFileChooser1 = new JFileChooser();
  ImageIcon Excel;
  ImageIcon Exit;
  JButton jButton1 = new JButton();
  JButton jButton2 = new JButton();
  java.awt.Label label1 = new java.awt.Label();
  TextField From = new TextField(6);
  java.awt.Label label2 = new java.awt.Label();
  TextField To = new TextField(6);
  JPanel jPanel1 = new JPanel();
  TitledBorder titledBorder1;
  TitledBorder titledBorder2;


  public inforDown() {
    try {
      jbInit();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  void jbInit() throws Exception {

    titledBorder1 = new TitledBorder("");
    titledBorder2 = new TitledBorder("");
    this.getContentPane().setLayout();
    this.setResizable(false);
    this.setSize(285,125);
    this.setTitle("\u804C\u5458獺\u8F7D");
    Excel = new ImageIcon(db.Main.class.getResource("imag/Excel.gif"));
    Exit = new ImageIcon(db.Main.class.getResource("imag/close.gif"));
    jButton1.setFont(new java.awt.Font("Dialog", 0, 12));
    jButton1.setIcon(Excel);
    jButton1.setText("Excel");
    jButton1.addActionListener(new inforDown_jButton1_actionAdapter(this));
    jButton2.setFont(new java.awt.Font("Dialog", 0, 12));
    jButton2.setHorizontalAlignment(SwingConstants.LEFT);
    jButton2.setHorizontalTextPosition(SwingConstants.RIGHT);
    jButton2.setIcon(Exit);
    jButton2.setText("Exit");
    jButton2.addActionListener(new inforDown_jButton2_actionAdapter(this));
    xYLayout1.setWidth(285);
    xYLayout1.setHeight(125);
    label1.setDropTarget(null);
    label1.setFont(new java.awt.Font("SimSun", 0, 12));
    label1.setLocale(java.util.Locale.getDefault());
    label1.setText("戮絪腹");
    label2.setFont(new java.awt.Font("SimSun", 0, 12));
    label2.setText("");
    From.setFont(new java.awt.Font("Tahoma", 0, 12));
    From.setForeground(new Color(144, 171, 0));
    From.setText("");
    To.setFont(new java.awt.Font("Tahoma", 0, 12));
    To.setForeground(new Color(144, 171, 0));
    To.setText("");
    jPanel1.setBorder(titledBorder2);
    this.getContentPane().add(jPanel1,         new XYConstraints(3, 47, 268, 2));
    this.getContentPane().add(jButton1,      new XYConstraints(88, 60, 85, 22));
    this.getContentPane().add(jButton2,   new XYConstraints(178, 60, 85, 22));
    this.getContentPane().add(From,  new XYConstraints(62, 17, 89, 20));
    this.getContentPane().add(To, new XYConstraints(176, 17, 89, 20));
    this.getContentPane().add(label1,   new XYConstraints(5, 16, 57, -1));
    this.getContentPane().add(label2,  new XYConstraints(155, 17, 17, 18));
  }

  void jButton1_actionPerformed(ActionEvent e) {

//fetch the date from the table
    dabaseOperator db = new dabaseOperator();
    ResultSet rs = null;
    String sql = null;
    String filepath=null;
    int temi = 1;
    String from=From.getText().toUpperCase().trim();
    String to=To.getText().toUpperCase().trim();
    String tem=null;

    if (from.length() == 0&&to.length() == 0) {
      JOptionPane.showMessageDialog(this, "\u8BF7ぶ\u8F93\u4E2A琿\u804C\u5458\u7F16\u53F7!", "矗ボ", 2);
      From.setFocusable(true);
    }else{


      if (from.compareTo(to) >= 0) {
        tem = to;
        to = from;
        from = tem;
      }

      sql = "select empl_no,name,post_no,sal_qty,datejoin,cnational,education from infor where leave=0 and sal_qty is not null and empl_no between '" +
          from + "' and '" + to + "' order by empl_no asc";

      rs = db.executeQuery(sql);
System.out.println(sql);
// fetch end


////////////////// set the xls save to ///////////////
      jFileChooser1 = new JFileChooser(".");
      jFileChooser1.addChoosableFileFilter(new MyFilter());

      int returnVal = jFileChooser1.showSaveDialog(this);
      if (returnVal == JFileChooser.APPROVE_OPTION) {
        File file = jFileChooser1.getSelectedFile();
        filepath = file.getPath().replace('\\', '/'); ;
        int i = filepath.toLowerCase().lastIndexOf(".xls");
        if (i < 0) { // user do not enter the file extension
          filepath = filepath + ".xls";
        }
      }

/////////////////////////////////////////////////////////////////////

      if (filepath != null) { // select the save then save the file
        try {
          WritableWorkbook wwb = Workbook.createWorkbook(new File(filepath));
          WritableSheet ws = wwb.createSheet("Sheet1", 0);
          ws.setPageSetup(jxl.format.PageOrientation.PORTRAIT); //LANDSCAPE

          jxl.write.Label labelC;

// patten title begin
          WritableFont wfc = new WritableFont(WritableFont.TAHOMA, 10,
                                              WritableFont.BOLD, false,
                                              UnderlineStyle.NO_UNDERLINE,
                                              jxl.format.Colour.BLACK);
          WritableCellFormat title = new WritableCellFormat(wfc);
          title.setBackground(jxl.format.Colour.YELLOW);
          title.setAlignment(jxl.format.Alignment.CENTRE);
          title.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
          title.setBorder(jxl.format.Border.ALL,
                          jxl.format.BorderLineStyle.THIN);
          title.setWrap(true);
// patten title end

// patten body begin
          WritableFont wf = new WritableFont(jxl.write.WritableFont.createFont(
              "SimSun"), 10);
          WritableCellFormat body = new WritableCellFormat(wf);
          body.setAlignment(jxl.format.Alignment.CENTRE);
          body.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
          body.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
          //body.setWrap(true);

          // display date
          WritableFont datewf = new WritableFont(jxl.write.WritableFont.
                                                 createFont("SimSun"), 10);
          DateFormat customDateFormat = new DateFormat("yyyy-mm-dd");
          WritableCellFormat dateFormat = new WritableCellFormat(datewf,
              customDateFormat);
          dateFormat.setAlignment(jxl.format.Alignment.CENTRE);
          dateFormat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
          dateFormat.setBorder(jxl.format.Border.ALL,
                               jxl.format.BorderLineStyle.THIN);

// patten body end



// the title Row
          ws.setRowView(0, 350); //︽蔼

          labelC = new Label(0, 0, "戮腹", title);
          ws.setColumnView(0, 15);
          ws.addCell(labelC);
          labelC = new Label(1, 0, "戮﹎", title);
          ws.setColumnView(1, 15);
          ws.addCell(labelC);
          labelC = new Label(2, 0, "", title);
          ws.addCell(labelC);
          labelC = new Label(3, 0, "戈", title);
          ws.addCell(labelC);
          labelC = new Label(4, 0, "紅丁", title);
          ws.setColumnView(4, 15);
          ws.addCell(labelC);
          labelC = new Label(5, 0, "膟砮", title);
          ws.addCell(labelC);
          labelC = new Label(6, 0, "戮厩菌", title);
          ws.setColumnView(6, 15);
          ws.addCell(labelC);
// the title Row end

 //////////// fit the main content////////////
          while (rs.next()) {

            ws.setRowView(temi, 300); //︽蔼

            labelC = new Label(0, temi, rs.getString("empl_no"), body);
            ws.addCell(labelC);
            labelC = new Label(1, temi, rs.getString("name"), body);
            ws.addCell(labelC);
            labelC = new Label(2, temi, rs.getString("post_no"), body);
            ws.addCell(labelC);
            jxl.write.Number number = new jxl.write.Number(3, temi,
                rs.getFloat("sal_qty"), body);
            ws.addCell(number);
            jxl.write.DateTime dateCell = new jxl.write.DateTime(4, temi,
                rs.getDate("datejoin"), dateFormat);
            ws.addCell(dateCell);
            labelC = new Label(5, temi, rs.getString("cnational"), body);
            ws.addCell(labelC);
            labelC = new Label(6, temi, rs.getString("education"), body);
            ws.addCell(labelC);

            temi += 1;
          }

//////////////////////////////////////////////
          wwb.write();

          wwb.close();
          db.freeMemory();

          Object[] options = {"琌", ""};
          int n = JOptionPane.showOptionDialog(this,
                                    "ゅン玂: " + filepath+". 眤\u73B0璶ゴ\u5F00\u5417",
                                    "玹\u8BE2",
                                    JOptionPane.YES_NO_OPTION,
                                    JOptionPane.QUESTION_MESSAGE,
                                    null,
                                    options,
                                    options[0]);

          if (n == JOptionPane.YES_OPTION) {
            try {
              Runtime.getRuntime().exec("cmd /c start " + filepath);
            }
            catch (Exception open) {
              open.printStackTrace();
            }
          } else if (n == JOptionPane.NO_OPTION) {} else {}

        }
        catch (Exception abc) {
          System.out.println(abc);
        }

      }
    }
  }


  void jButton2_actionPerformed(ActionEvent e) {
    this.dispose();
  }
}

class inforDown_jButton1_actionAdapter implements java.awt.event.ActionListener {
  inforDown adaptee;

  inforDown_jButton1_actionAdapter(inforDown adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
  adaptee.jButton1_actionPerformed(e);
  }
}

class inforDown_jButton2_actionAdapter implements java.awt.event.ActionListener {
  inforDown adaptee;

  inforDown_jButton2_actionAdapter(inforDown adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton2_actionPerformed(e);
  }
}
