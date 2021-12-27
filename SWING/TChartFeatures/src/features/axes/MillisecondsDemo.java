/*
 * MillisecondsDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.axes;

import com.steema.teechart.DateTime;
import com.steema.teechart.DateTimeStep;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.PointerStyle;
import com.steema.teechart.styles.Points;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class MillisecondsDemo extends ChartSamplePanel {

    /** Creates a new instance of MillisecondsDemo */
    public MillisecondsDemo() {
        super();
    }

    protected void initChart() {
        super.initChart();
        chart1.getAspect().setView3D(false);
        chart1.getLegend().setVisible(false);
        chart1.getPanel().setMarginRight(10.0);
        chart1.getAxes().getBottom().setIncrement(DateTimeStep.ONEMILLISECOND);
        chart1.getAxes().getBottom().getLabels().setDateTimeFormat("ss.SSS");
        chart1.getAxes().getBottom().getLabels().setRoundFirstLabel(false);
    }

    protected void initComponents() {
        super.initComponents();
        Points series = new Points(chart1.getChart());
        series.getMarks().setVisible(false);
        series.getPointer().getBrush().setColor(Color.TEAL);
        series.getPointer().setHorizSize(7);
        series.getPointer().setVertSize(7);
        series.getPointer().setInflateMargins(true);
        series.getPointer().getPen().setColor(Color.WHITE);
        series.getPointer().setStyle(PointerStyle.TRIANGLE);
        series.getPointer().setVisible(true);
        series.getXValues().setDateTime(true);


        series.add( new DateTime(0), 100);
        series.add( new DateTime(1), 200);
        series.add( new DateTime(2), 100);
        series.add( new DateTime(3), 200);

    }

    protected void initGUI() {
        super.initGUI();
        hideButtonPane();
    }
}
