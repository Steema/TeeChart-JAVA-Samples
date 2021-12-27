/*
 * FindMinMaxDemo.java
 *
 * <p>Copyright: (c) 2004-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.zoom;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.Line;
import com.steema.teechart.styles.Series;
import com.steema.teechart.tools.ColorLine;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class FindMinMaxDemo extends ChartSamplePanel
    implements ActionListener {

    /** Creates a new instance of ZoomScrollDemo */
    public FindMinMaxDemo() {
        super();
        findButton.addActionListener(this);
        resetButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == findButton) {
            /* Show the Series points between 20 and 50 only */
            chart1.getAxes().getBottom().setMinMax(20,50);
            /* Now find the min and max for this portion of points */
            double[] pos = seriesMinMax(chart1.getSeries().getSeries(0), 20, 50);
            /* For example, set the vertical axis to fit */
            chart1.getAxes().getLeft().setMinMax(pos[0],pos[1]);
            findButton.setEnabled(false);
            resetButton.setEnabled(true);
        } else if (source == resetButton) {
            chart1.getAxes().getLeft().setAutomatic(true);
            chart1.getAxes().getBottom().setAutomatic(true);
            findButton.setEnabled(true);
            resetButton.setEnabled(false);
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getLegend().setVisible(false);
        chart1.getAspect().setChart3DPercent(20);
    }

    protected void initComponents() {
        super.initComponents();

        Line series = new Line(chart1.getChart());
        series.fillSampleValues(100);
        double[] pos = seriesMinMax(series, 20, 50);

        ColorLine line;
        line = new ColorLine(chart1.getAxes().getBottom());
        line.getPen().setColor(Color.BLUE);
        line.setValue(20);

        line = new ColorLine(chart1.getAxes().getBottom());
        line.getPen().setColor(Color.BLUE);
        line.setValue(50);

        line = new ColorLine(chart1.getAxes().getLeft());
        line.getPen().setColor(Color.LIME);
        line.setValue(pos[0]);

        line = new ColorLine(chart1.getAxes().getLeft());
        line.getPen().setColor(Color.LIME);
        line.setValue(pos[1]);

        findButton = new JButton("Find min and max");
        resetButton = new JButton("Reset");
        resetButton.setEnabled(false);
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(findButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(resetButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    /**
     * Returns the min and max of a portion of Series points (from "first" to "last" point)
     * @param   series  the series on which to find the min and max
     * @param   first   the first point from which to calculate the min and max
     * @param   last    the last point till which to calculate the min and max
     */
    private double[] seriesMinMax(Series series, int first, int last) {
        double[] result = new double[2];
        double min = series.getYValues().getValue(first);
        double max = series.getYValues().getValue(first);
        for (int t=first+1; t<last; t++ ) {
            if (series.getYValues().getValue(t) < min) {
                min = series.getYValues().getValue(t);
            } else if (series.getYValues().getValue(t) > max) {
                max = series.getYValues().getValue(t);
            }
        }
        result[0] = min;
        result[1] = max;
        return result;
    }


    JButton findButton, resetButton;
}
