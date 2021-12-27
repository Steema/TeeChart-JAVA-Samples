/*
 * CircularGaugeDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.style.circulargauge;

import com.steema.teechart.Rectangle;
import com.steema.teechart.styles.CircularGauge;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import javax.swing.Box;
import javax.swing.JPanel;
import features.ChartSamplePanel;
import java.awt.event.ComponentListener;
import java.util.Random;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author tom
 */
public class CircularGauge_lGaugeDemo extends ChartSamplePanel
        implements ActionListener, ChangeListener, ComponentListener {

    private CircularGauge cGauge;

    /** Creates a new instance of WaterfallDemo */
    public CircularGauge_lGaugeDemo() {
        super();

    }

    
    protected void initComponents() {

        super.initComponents();

        t1 = new Timer(50, this);
        t1.addActionListener(this);
        t1.start();

        t2 = new Timer(500, this);
        t2.addActionListener(this);

        jCheckBox1 = new JCheckBox();
        jCheckBox1.setText("Same Value");
        jCheckBox1.setSelected(true);
        jCheckBox1.addActionListener(this);
        jCheckBox2 = new JCheckBox();
        jCheckBox2.setText("Horizontal");
        jCheckBox2.setSelected(false);
        jCheckBox2.addActionListener(this);
        jCheckBox3 = new JCheckBox();
        jCheckBox3.setText("Auto Position");
        jCheckBox3.setSelected(true);
        jCheckBox3.addActionListener(this);

        jLabel1 = new JLabel();
        jLabel1.setText("Top: ");
        jLabel2 = new JLabel();
        jLabel2.setText("Left: ");

        cGauge = new CircularGauge(chart1.getChart());
        cGauge.fillSampleValues();
        cGauge.setTotalAngle(280);
        cGauge.getLinearGauge().setVisible(true);

        jSpinner1 = new javax.swing.JSpinner();
        jSpinner1.setEnabled(false);
        jSpinner1.addChangeListener(this);
        jSpinner1.setToolTipText("");
        jSpinner1.setValue(cGauge.getLinearGauge().getCustomBounds().getTop());
        jSpinner2 = new javax.swing.JSpinner();
        jSpinner2.setEnabled(false);
        jSpinner2.addChangeListener(this);
        jSpinner2.setValue(cGauge.getLinearGauge().getCustomBounds().getLeft());
    }

    
    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(jCheckBox1);
            tmpPane.add(Box.createHorizontalStrut(20));
            tmpPane.add(jCheckBox2);
            tmpPane.add(Box.createHorizontalStrut(20));
            tmpPane.add(jCheckBox3);
            tmpPane.add(Box.createHorizontalStrut(20));
            tmpPane.add(jLabel1);
            tmpPane.add(Box.createHorizontalStrut(20));
            tmpPane.add(jSpinner1);
            tmpPane.add(Box.createHorizontalStrut(20));
            tmpPane.add(jLabel2);
            tmpPane.add(Box.createHorizontalStrut(20));
            tmpPane.add(jSpinner2);
            tmpPane.add(Box.createHorizontalStrut(20));
        }
    }
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    private Timer t1, t2;
    private boolean up = true;

    
    public void actionPerformed(ActionEvent e) {
        Object aux = e.getSource();
        if (aux == t1) {
            if (up) {
                cGauge.setValue(cGauge.getValue() + 0.1);
            } else {
                cGauge.setValue(cGauge.getValue() - 0.1);
            }
            if ((cGauge.getValue()) > 99) {
                up = false;
            } else if ((cGauge.getValue()) < 1) {
                up = true;
            }
        } else if (aux == jCheckBox3) {
            cGauge.setAutoPositionLinearGauge(jCheckBox3.isSelected());
            jSpinner1.setEnabled(!jCheckBox3.isSelected());
            jSpinner2.setEnabled(!jCheckBox3.isSelected());
            jSpinner1.setValue(cGauge.getLinearGauge().getCustomBounds().getTop());
            jSpinner2.setValue(cGauge.getLinearGauge().getCustomBounds().getLeft());

        } else if (aux == jCheckBox2) {
            cGauge.getLinearGauge().setHorizontal(jCheckBox2.isSelected());
        } else if (aux == jCheckBox1) {
            if (jCheckBox1.isSelected()) {
                cGauge.setAutoValueLinearGauge(true);
                t2.stop();
            } else {
                t2.start();
                cGauge.setAutoValueLinearGauge(false);
            }
        } else if (aux == t2) {
            if (!cGauge.getAutoValueLinearGauge()) {
                Random rnd = new Random();
                cGauge.getLinearGauge().setValue(rnd.nextDouble() * 100);
            }
        }
    }

    
    public void stateChanged(ChangeEvent e) {
        Object aux = e.getSource();
        if (aux == jSpinner1) {
            Rectangle tmpR;
            tmpR = cGauge.getLinearGauge().getCustomBounds();
            tmpR.y = (Integer) jSpinner1.getValue();
            cGauge.getLinearGauge().setCustomBounds(tmpR);
        } else if (aux == jSpinner2) {
            Rectangle tmpR;
            tmpR = cGauge.getLinearGauge().getCustomBounds();
            tmpR.x = (Integer) jSpinner2.getValue();
            cGauge.getLinearGauge().setCustomBounds(tmpR);
        }
    }

    
    public void componentResized(ComponentEvent e) {
    }

    
    public void componentMoved(ComponentEvent e) {
    }

    
    public void componentShown(ComponentEvent e) {
    }

    
    public void componentHidden(ComponentEvent e) {
    }
}
