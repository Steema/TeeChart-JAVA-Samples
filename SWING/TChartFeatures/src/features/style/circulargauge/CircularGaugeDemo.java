/*
 * CircularGaugeDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.style.circulargauge;

import com.steema.teechart.IBaseChart;
import com.steema.teechart.drawing.ChartBrush;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.editors.ButtonPen;
import com.steema.teechart.editors.ChartEditor;
import com.steema.teechart.editors.series.CircularGaugeEditor;
import com.steema.teechart.events.ChartDrawEvent;
import com.steema.teechart.events.SeriesPaintListener;
import com.steema.teechart.misc.Utils;
import com.steema.teechart.styles.CircularGauge;
import com.steema.teechart.styles.ValueListOrder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import features.ChartSamplePanel;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.SpinnerNumberModel;

import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author tom
 */
public class CircularGaugeDemo extends ChartSamplePanel
        implements ActionListener, ChangeListener, WindowListener {

    private CircularGauge series;
    private JButton dialog;
    private Action openAction;

    /** Creates a new instance of WaterfallDemo */
    public CircularGaugeDemo() {
        super();

    }

    protected void initComponents() {

        super.initComponents();

        t = new Timer(100, this);

        t.addActionListener(this);
        t.start();
        series = new CircularGauge((IBaseChart)(chart1.getChart()));
        series.getFrame().getOuterBand().setColor(Color.fromArgb(153, 153, 153));
        series.getFrame().getMiddleBand().getGradient().setVisible(true);
        series.getFrame().getMiddleBand().getGradient().setStartColor(Color.fromArgb(80, 80, 80));
        series.getFrame().getMiddleBand().getGradient().setEndColor(Color.WHITE);
        series.getFrame().getInnerBand().setColor(Color.fromArgb(213, 213, 213));
        series.getFaceBrush().setForegroundColor(com.steema.teechart.drawing.Color.BLUE);
        series.fillSampleValues();
        series.setCircled(true);
        series.setColorEach(false);
        series.setMinimum(0);
        series.setMaximum(100);
        series.setGreenLineStartValue(0);
        series.setGreenLineEndValue(70);
        series.setRedLineStartValue(80);
        series.setRedLineEndValue(100);
        series.setShowInLegend(false);
        series.setTotalAngle(300);
        series.setValue(0);
        series.getXValues().setDataMember("Angle");
        series.getXValues().setOrder(ValueListOrder.ASCENDING);
        series.getYValues().setDataMember("Y");
        jOuter = new javax.swing.JSpinner();
        jCheckBox1 = new javax.swing.JCheckBox();
        openAction = new OpenAction("Circular Gauge Editor");
        dialog = new JButton(openAction);
        window.addWindowListener(this);
        jLabel1 = new javax.swing.JLabel();
        jMiddle = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jInner = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        jWidth = new javax.swing.JSpinner();

        jOuter.setModel(new javax.swing.SpinnerNumberModel(series.getFrame().getFrameElementPercents()[2], 0, 100, 1));
        jOuter.setToolTipText("");
        jOuter.setMaximumSize(new java.awt.Dimension(100, 100));

        jCheckBox1.setMnemonic('A');
        jCheckBox1.setSelected(true);
        jCheckBox1.setText("Animate");
        jLabel1.setText("Outer %:");
        jCheckBox1.addActionListener(this);
        jMiddle.setModel(new javax.swing.SpinnerNumberModel(series.getFrame().getFrameElementPercents()[1], 0, 100, 1));
        jMiddle.setToolTipText("");

        jMiddle.setMaximumSize(new java.awt.Dimension(100, 100));

        jLabel2.setText("Middle %:");

        jLabel3.setText("Inner %: ");

        jInner.setModel(new javax.swing.SpinnerNumberModel(series.getFrame().getFrameElementPercents()[0], 0, 100, 1));
        jInner.setToolTipText("");
        jInner.setMaximumSize(new java.awt.Dimension(100, 100));
        jInner.addChangeListener(this);
        jMiddle.addChangeListener(this);
        jOuter.addChangeListener(this);
        jLabel4.setText("Frame  Width %: ");

        jWidth.setModel(new javax.swing.SpinnerNumberModel(30, 0, 100, 1));
        jWidth.setToolTipText("");
        jWidth.setMaximumSize(new java.awt.Dimension(100, 100));

        jWidth.setValue(series.getFrame().getWidth());
        jWidth.addChangeListener(this);
        jOuter.addChangeListener(this);
        jMiddle.addChangeListener(this);
        jInner.addChangeListener(this);
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(jCheckBox1);
            tmpPane.add(Box.createHorizontalStrut(20));
            tmpPane.add(dialog);
            tmpPane.add(Box.createHorizontalStrut(20));
            tmpPane.add(jLabel4);
            tmpPane.add(Box.createHorizontalStrut(0));
            tmpPane.add(jWidth);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(jLabel1);
            tmpPane.add(Box.createHorizontalStrut(0));
            tmpPane.add(jOuter);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(jLabel2);
            tmpPane.add(Box.createHorizontalStrut(0));
            tmpPane.add(jMiddle);
             tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(jLabel3);
            tmpPane.add(Box.createHorizontalStrut(0));
            tmpPane.add(jInner);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }
    private JButton editButton;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JSpinner jOuter;
    private javax.swing.JSpinner jMiddle;
    private javax.swing.JSpinner jInner;
    private javax.swing.JSpinner jWidth;
    private static JFrame window = new JFrame();
    private Boolean b = false;
    private Timer t;
    private double value2 = 0.0;
    private boolean up = true;

    public void actionPerformed(ActionEvent e) {
        Object aux = e.getSource();
        if (aux == jCheckBox1) {
            if (jCheckBox1.isSelected()) {
                t.start();
            } else {
                t.stop();
            }
        } else if (aux == t) {

            if (up) {
                value2 += 1;

            } else {
                value2 -= 1;
            }
            if (value2 > 99) {
                up = false;
            } else if (value2 < 1) {
                up = true;
            }
            series.setValue(value2);
        }

    }
    boolean isChanging = false;
    public void stateChanged(ChangeEvent e) {
       Object aux = e.getSource();
       if (!isChanging)
      {
        isChanging = true;
       
         if (aux == jOuter)
          series.getFrame().getFrameElementPercents()[2] = ((Double) jOuter.getValue());
        else if (aux == jMiddle)
           series.getFrame().getFrameElementPercents()[1] = ((Double) jMiddle.getValue());
        else if (aux == jInner)
             series.getFrame().getFrameElementPercents()[0] = ((Double) jInner.getValue());

        double appOuter = series.getFrame().getFrameElementPercents()[2];
        double appMiddle = series.getFrame().getFrameElementPercents()[1];
        double appInner = series.getFrame().getFrameElementPercents()[0];


        double[] values = Utils.rationaliseValues(((Double)jInner.getValue()),((Double) jMiddle.getValue()), ((Double)jOuter.getValue()));
        
        jInner.setValue(values[0]);
        jMiddle.setValue(values[1]);
        jOuter.setValue(values[2]);

        jOuter.setValue(CircularGauge.truncate((Double)jOuter.getValue()));
        jMiddle.setValue(CircularGauge.truncate((Double)jMiddle.getValue()));
        jInner.setValue(CircularGauge.truncate((Double)jInner.getValue()));
        series.getChart().getParent().refreshControl();
        isChanging = false;
      }

      if (series.getChart().getParent() != null)
        series.getChart().getParent().refreshControl();
    }

    final public class OpenAction extends AbstractAction {

        public OpenAction(String text) {
            super(text);
        }

        public void actionPerformed(ActionEvent e) {
            Object obj = e.getSource();
            if (obj == dialog) {
                if (!b) {
                    CircularGaugeEditor dialog = new CircularGaugeEditor(series);
                    window.add(dialog);
                    window.setVisible(true);
                    window.setSize(347, 250);
                    window.setBounds(500, 400, 480, 250);
                    b = true;
                }
            }
        }
    }

    public void windowOpened(WindowEvent e) {
    }

    public void windowClosing(WindowEvent e) {
        Object obj = e.getSource();
        if (obj == window) {
            b = false;
        }
    }

    public void windowClosed(WindowEvent e) {
        Object obj = e.getSource();
        if (obj == window) {
            b = false;
        }
    }

    public void windowIconified(WindowEvent e) {
    }

    public void windowDeiconified(WindowEvent e) {
    }

    public void windowActivated(WindowEvent e) {
    }

    public void windowDeactivated(WindowEvent e) {
    }


}

