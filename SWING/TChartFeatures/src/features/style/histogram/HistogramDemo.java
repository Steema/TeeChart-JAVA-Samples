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

/**
 *
 * @author tom
 */
public class HistogramDemo extends ChartSamplePanel {

    private ButtonPen lineButton, linesButton;

    /** Creates a new instance of HistogramDemo */
    public HistogramDemo() {
        super();
    }

    protected void initComponents() {
        super.initComponents();
        Histogram series = new Histogram(chart1.getChart());
        series.fillSampleValues(10);

        lineButton = new ButtonPen(series.getLinePen(), "Border...");
        linesButton = new ButtonPen(series.getLinesPen(), "Pen...");
    }

    protected void initGUI() {
        super.initGUI();
        chart1.getAspect().setView3D(false);
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(lineButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(linesButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }
}
