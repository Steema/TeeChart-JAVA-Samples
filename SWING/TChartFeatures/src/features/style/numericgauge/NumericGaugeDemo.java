/*
 * CircularGaugeDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.style.numericgauge;

import com.steema.teechart.IBaseChart;
import com.steema.teechart.styles.NumericGauge;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import features.ChartSamplePanel;
import java.util.Random;
import javax.swing.JCheckBox;
import javax.swing.Timer;

/**
 *
 * @author tom
 */
public class NumericGaugeDemo extends ChartSamplePanel implements ActionListener {

    private NumericGauge series, series2;

    /** Creates a new instance of WaterfallDemo */
    public NumericGaugeDemo() {
        super();
    }

    
    protected void initComponents() {
        super.initComponents();
        t = new Timer(100, this);
        t.addActionListener(this);
        t.start();
        series = new NumericGauge((IBaseChart) chart1.getChart());
        series.fillSampleValues();
        series2 = new NumericGauge((IBaseChart) chart1.getChart());
        series2.fillSampleValues();
        Float f = new Float(series.getValue());
        series.setValue(f);
        jCheckBox1 = new JCheckBox();
        jCheckBox2 = new JCheckBox();
        jCheckBox1.setText("Animate Top Gauges");
        jCheckBox2.setText("Animate Bottom Gauges");
        jCheckBox1.addActionListener(this);
        jCheckBox2.addActionListener(this);
        jCheckBox1.setSelected(true);
        jCheckBox2.setSelected(true);
        Float f2 = new Float(series2.getValue());
        series2.setValue(f2);
    }

    
    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(jCheckBox1);
            tmpPane.add(jCheckBox2);
        }
    }

    public void actionPerformed(ActionEvent e) {
        Object aux = e.getSource();
        if (aux == jCheckBox1 || aux == jCheckBox2) {
            if (jCheckBox1.isSelected() || jCheckBox2.isSelected()) {
                t.start();
            } else if (!jCheckBox1.isSelected() && !jCheckBox2.isSelected()) {
                t.stop();
            }
        } else if (aux == t) {
            if (jCheckBox1.isSelected() && jCheckBox2.isSelected()) {
                Random rnd = new Random();
                double value2 = rnd.nextDouble() * 1000;
                series.setValue(value2);
                while (value2 == series.getValue()) {
                    value2 = rnd.nextDouble() * 100;
                }
                series2.setValue(value2);
            } else if (jCheckBox1.isSelected() && !jCheckBox2.isSelected()) {
                Random rnd = new Random();
                double value2 = rnd.nextDouble() * 1000;
                series.setValue(value2);
            } else if (!jCheckBox1.isSelected() && jCheckBox2.isSelected()) {
                Random rnd = new Random();
                double value2 = rnd.nextDouble() * 100;
                series2.setValue(value2);
            }
        }
    }
    private javax.swing.JCheckBox jCheckBox1, jCheckBox2;
    private Timer t;
}
