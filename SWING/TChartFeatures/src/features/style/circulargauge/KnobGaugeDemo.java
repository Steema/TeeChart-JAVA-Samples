/*
 * CircularGaugeDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.style.circulargauge;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.editors.series.KnobGaugeEditor;
import com.steema.teechart.styles.KnobGauge;
import com.steema.teechart.styles.ValueListOrder;
import com.steema.teechart.themes.ThemesList;
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

import javax.swing.Timer;

/**
 *
 * @author tom
 */
public class KnobGaugeDemo extends ChartSamplePanel
        implements ActionListener , WindowListener{

    private KnobGauge series;
        private JButton dialog;
    private Action openAction;


    /** Creates a new instance of WaterfallDemo */
    public KnobGaugeDemo() {
        super();

    }

    protected void initComponents() {

        super.initComponents();

        t = new Timer(100, this);

        t.addActionListener(this);
        t.start();
        //series = new KnobGauge((IBaseChart)(chart1.getChart()));
        series = new KnobGauge(chart1.getChart());
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
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox1.setMnemonic('A');
        jCheckBox1.setSelected(true);
        jCheckBox1.setText("Animate");
        jCheckBox1.addActionListener(this);
        openAction = new OpenAction("Knob Gauge Editor");
        dialog = new JButton(openAction);
        window.addWindowListener(this);
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(jCheckBox1);
            tmpPane.add(Box.createHorizontalStrut(150));
            tmpPane.add(dialog);
            tmpPane.add(Box.createHorizontalGlue());

        }
    }
    private JButton editButton;
    private javax.swing.JCheckBox jCheckBox1;
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


    final public class OpenAction extends AbstractAction {

        public OpenAction(String text) {
            super(text);
        }

        public void actionPerformed(ActionEvent e) {
            Object obj = e.getSource();
            if (obj == dialog) {
                if (!b) {
                    KnobGaugeEditor dialog = new KnobGaugeEditor(series);
                    window.add(dialog);
                    window.setVisible(true);
                    window.setSize(347, 217);
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

