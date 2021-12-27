/*
 * HistogramDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.style.histogram;

import com.steema.teechart.editors.ButtonPen;
import com.steema.teechart.styles.Histogram;
import javax.swing.Box;
import javax.swing.JPanel;
import features.ChartSamplePanel;
import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.omg.PortableInterceptor.USER_EXCEPTION;

/**
 *
 * @author tom
 */
public class HistogramYOriginDemo extends ChartSamplePanel implements ChangeListener {

    private JCheckBox useOrigin;
    private JLabel yOrigin;
    private JSpinner jValue;
    private Histogram series;

    /** Creates a new instance of HistogramDemo */
    public HistogramYOriginDemo() {
        super();
    }

    protected void initComponents() {
        super.initComponents();
        series = new Histogram(chart1.getChart());
        useOrigin = new JCheckBox();
        useOrigin.addChangeListener(this);
        useOrigin.setText("use YOrigin");
        yOrigin = new JLabel();
        yOrigin.setText("YOrigin: ");
        jValue = new JSpinner();
        jValue.addChangeListener(this);
        jValue.setModel(new javax.swing.SpinnerNumberModel(0, -10000, 10000, 1));
        useOrigin.setSelected(true);
        series.setUseYOrigin(true);
        series.setYOrigin(0);
        series.add(0, 10);
        series.add(1, 20);
        series.add(2, -30);
        series.add(3, -10);
        series.add(4, 10);
        series.add(5, 20);
        series.add(6, 30);
        series.add(7, 10);
    }

    protected void initGUI() {
        super.initGUI();
        chart1.getAspect().setView3D(false);
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(useOrigin);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(yOrigin);
            tmpPane.add(jValue);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    public void stateChanged(ChangeEvent e) {
        Object aux = e.getSource();
        if (aux == useOrigin) {
            if (useOrigin.isSelected()) {
                series.setUseYOrigin(true);
                jValue.setEnabled(true);
                series.setYOrigin((Integer) jValue.getValue());
            } else {
                jValue.setEnabled(false);
                series.setUseYOrigin(false);
            }
        } else if (aux == jValue)
        {
          series.setYOrigin((Integer) jValue.getValue());
        }
    }
}
