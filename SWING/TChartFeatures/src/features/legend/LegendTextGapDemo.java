/*
 * LegendTitleDemo.java
 *
 * <p>Copy.getRight(): Copy.getRight() (c) 2004-2007 by Steema Software SL. All .getRight()s
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.legend;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.GradientDirection;
import com.steema.teechart.legend.Legend;
import com.steema.teechart.styles.Line;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JPanel;
import features.ChartSamplePanel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author tom
 */
public class LegendTextGapDemo extends ChartSamplePanel
         implements ActionListener ,ChangeListener {

    /** Creates a new instance of LegendTitleDemo */
    public LegendTextGapDemo() {
        super();
        jCheckBox1.addActionListener(this);
        jSpinner1.addChangeListener(this);
        jSpinner2.addChangeListener(this);
        jSpinner3.addChangeListener(this);   
    }


    protected void initChart() {
        super.initChart();
        Legend legend = chart1.getLegend();
        /*legend.getGradient().setDirection(GradientDirection.HORIZONTAL);
        legend.getGradient().setStartColor(Color.GRAY);
        legend.getGradient().setMiddleColor(Color.WHITE);
        legend.getGradient().setEndColor(Color.GRAY);
        legend.getGradient().setVisible(true);*/
        legend.getTitle().setText("Values");
        legend.getTitle().setTransparent(false);
        legend.getTitle().getFont().setColor(Color.BLUE);
    }

    protected void initComponents() {
        super.initComponents();
        Line tmpSeries = new Line(chart1.getChart());
        tmpSeries.getPointer().setVisible(false);

        tmpSeries.fillSampleValues(20);

        //jPanel1 = new javax.swing.JPanel();
        //jPanel2 = new javax.swing.JPanel();
        jSpinner1 = new javax.swing.JSpinner();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jSpinner2 = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSpinner3 = new javax.swing.JSpinner();
        jSpinner1.setModel(new javax.swing.SpinnerNumberModel(30, 0, 100, 1));
        jSpinner1.setToolTipText("");
        jSpinner1.setEnabled(false);
        jSpinner1.setMaximumSize(new java.awt.Dimension(100, 100));

        jCheckBox1.setMnemonic('A');
        jCheckBox1.setSelected(true);
        jCheckBox1.setText("Automatic widths");

        jLabel1.setText("Column 1 width: ");

        jSpinner2.setModel(new javax.swing.SpinnerNumberModel(14, 0, 100, 1));
        jSpinner2.setToolTipText("");
        jSpinner2.setEnabled(false);
        jSpinner2.setMaximumSize(new java.awt.Dimension(100, 100));

        jLabel2.setText("Column 2 width: ");

        jLabel3.setText("Adjust gap Symbol-Text:");

        jSpinner3.setModel(new javax.swing.SpinnerNumberModel(2, -5, 30, 1));
        jSpinner3.setToolTipText("");
        jSpinner3.setMaximumSize(new java.awt.Dimension(100, 100));

          chart1.getAxes().getBottom().getLabels().setDateTimeFormat("dd/MM/yyyy");
          chart1.getAxes().getBottom().setMaximumOffset(5);
          chart1.getAxes().getBottom().setMinimumOffset(5);

          chart1.getAxes().getLeft().setMaximumOffset(5);
          chart1.getAxes().getLeft().setMinimumOffset(5);
          chart1.getHeader().getFont().setColor(new Color(64,64,64));
           chart1.getHeader().getFont().setSize(11);
           chart1.getHeader().setLines(new String[] {"Adjusting Legend spacing"});
          // 
          // 
          // 
          chart1.getLegend().setBorderRound(8);
          // 
          // 
          // 
          chart1.getLegend().getPen().setColor(new Color(255,128,0));
          // 
          // 
          // 
          chart1.getLegend().getShadow().setVisible(false);
          // 
          // 
          // 
          // 
          // 
          // 
          chart1.getPanel().getBrush().setColor(new Color(254,255,255,255));
          // 
          // 
          // 
          chart1.getPanel().getBrush().getGradient().setEndColor(new Color(185,185,225));
         chart1.getPanel().getBrush().getGradient().setStartColor(new Color(234,234,254));
          chart1.getSeries().add(tmpSeries);
          chart1.setSize(466,133);
          // 
          // 
          // 
          // 
          // 
          // 
          // 
          // 
          // 
           chart1.getWalls().getBack().getPen().setVisible(false);
           // 
          // 
          // 
          // 
          // 
          // 
          chart1.getWalls().getBottom().getPen().setVisible(false);
          chart1.getWalls().getBottom().setSize(5);
          // 
          // 
          // 
          // 
          // 
          // 
          chart1.getWalls().getLeft().getPen().setVisible(false);
          chart1.getWalls().getLeft().setSize(5);
         
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(jCheckBox1);
            tmpPane.add(Box.createHorizontalStrut(50));
            tmpPane.add(jLabel1);
            tmpPane.add(Box.createHorizontalStrut(0));
            tmpPane.add(jSpinner1);
            tmpPane.add(Box.createHorizontalStrut(50));
            tmpPane.add(jLabel2);
            tmpPane.add(Box.createHorizontalStrut(0));
            tmpPane.add(jSpinner2);
            tmpPane.add(Box.createHorizontalStrut(50));
            tmpPane.add(jLabel3);
            tmpPane.add(Box.createHorizontalStrut(0));
            tmpPane.add(jSpinner3);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    // Variables declaration - do not modify
    //private javax.swing.JPanel jPanel1;
    //private javax.swing.JPanel jPanel2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
     private javax.swing.JSpinner jSpinner3;
     private javax.swing.JLabel jLabel3;

    public void actionPerformed(ActionEvent e) {
        if(jCheckBox1.isSelected())
        {
            jSpinner1.setEnabled(false);
            jSpinner2.setEnabled(false);
            chart1.getLegend().setColumnWidthAuto(true);
        }
        else
        {
            jSpinner1.setEnabled(true);
            jSpinner2.setEnabled(true);
             chart1.getLegend().setColumnWidthAuto(false);
            jSpinner1.setValue(chart1.getLegend().getColumnWidth(0));
            jSpinner2.setValue(chart1.getLegend().getColumnWidth(1));
            
        }
    }


    public void stateChanged(ChangeEvent e) {
       Object aux = e.getSource();
        if(aux == jSpinner1)
        {
            SpinnerNumberModel model = (SpinnerNumberModel)jSpinner1.getModel();
            int value = model.getNumber().intValue();
            if(value<0)
            {
                jSpinner1.setValue(0);
                chart1.refreshControl();
            }
            
            else if(value>100)
            {
                jSpinner1.setValue(100);
                chart1.refreshControl();
            }
            else
            {
              chart1.getLegend().setColumnWidth(0, value);
              chart1.refreshControl();
            }
        }
        else if(aux== jSpinner2)
        {
            SpinnerNumberModel model = (SpinnerNumberModel)jSpinner2.getModel();
            int value = model.getNumber().intValue();
            if(value<0)
            {
                jSpinner2.setValue(0);
                chart1.refreshControl();
            }
            
            else if(value>100)
            {
                jSpinner2.setValue(100);
                chart1.refreshControl();
            }
            else
            {
             
             chart1.getLegend().setColumnWidth(1, value);
             chart1.refreshControl();

            }
        }
        else if(aux==jSpinner3)
        {
            SpinnerNumberModel model = (SpinnerNumberModel)jSpinner3.getModel();
            int value = model.getNumber().intValue();
                 if(value<-5)
                 {
                    jSpinner3.setValue(-5);
                      chart1.refreshControl();
                 }
            
            else if(value>30)
            {
                jSpinner3.setValue(30);
                  chart1.refreshControl();
            }
            else
            {
                    chart1.getLegend().setTextSymbolGap(value);
                    chart1.refreshControl();
            }
        }
    }


    // End of variables declaration
}
