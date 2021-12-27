/*
 * JDBCImportDemo.java
 *
 * <p>Copyright (c) 2006-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.imports;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import features.ChartSamplePanel;

import com.steema.teechart.styles.MarksStyle;
import com.steema.teechart.styles.Bar;
import com.steema.teechart.legend.LegendAlignment;
import com.steema.teechart.styles.Series;

import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;

import java.awt.Cursor;

/**
 *
 * @author db
 */
public class JDBCImportDemo extends ChartSamplePanel implements ActionListener {

    /**
     * Creates a new instance of XmlImportDemo
     */
    public JDBCImportDemo() {
        super();
        loadButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();

        if (source == loadButton) {

            chart1.getLegend().setAlignment(LegendAlignment.BOTTOM);

            chart1.getSeries().clear();

            Bar bar1 = new Bar(chart1.getChart());
            bar1.setColorEach(true);
            bar1.getMarks().setStyle(MarksStyle.VALUE);

            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

            try {
                JDBC_addData(bar1);
            } finally {
                setCursor(Cursor.getDefaultCursor());
            }
        }
    }

    /**
     * Note, please confirm that you have
     * com.microsoft.jdbc.sqlserver.SQLServerDriver
     * on visible path
     */
    public void JDBC_addData(Series series) {
        try {
            Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
            Connection c = DriverManager.getConnection(
                    "jdbc:microsoft:sqlserver://" + urlTextField.getText(),
                    "teechart", "teechart");

            Statement s = c.createStatement();
            ResultSet r = s.executeQuery(
                    "SELECT * from [Category Sales for 1997]");

            series.getYValues().setDataMember("CategorySales");
            series.setLabelMember("CategoryName");

            series.setDataSource(r);
            s.close();
            c.close();

        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getHeader().setText("JDBC Data from www.steema.net");
    }

    protected void initComponents() {
        super.initComponents();
        loadButton = new JButton("Load!");
        urlTextField = new JTextField(
                "www.steema.net;DatabaseName=Northwind;SelectMethod=cursor");
        urlTextField.setEditable(false);
    }

    protected void initGUI() {
        super.initGUI();
        JLabel tmpLabel;
        JPanel tmpPane = getButtonPane();
        {
            tmpLabel = new JLabel("URL:");
            tmpLabel.setDisplayedMnemonic('U');
            tmpLabel.setLabelFor(urlTextField);
            tmpPane.add(tmpLabel);
            tmpPane.add(urlTextField);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(loadButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JButton loadButton;
    private JTextField urlTextField;
}
