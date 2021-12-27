/*
 * MultiScrollDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.axes;

import com.steema.teechart.axis.Axis;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.events.ChartEvent;
import com.steema.teechart.events.ChartMotionAdapter;
import com.steema.teechart.styles.Candle;
import com.steema.teechart.styles.FastLine;
import com.steema.teechart.styles.Series;
import com.steema.teechart.styles.VerticalAxis;
import com.steema.teechart.styles.Volume;
import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class MultiScrollDemo extends ChartSamplePanel {

    protected Axis blueAxis, greenAxis;

    /** Creates a new instance of MultiScrollDemo */
    public MultiScrollDemo() {
        super();
    }

    protected void initChart() {
        super.initChart();
        chart1.getLegend().setVisible(false);
        chart1.getAspect().setView3D(false);
        chart1.addChartMotionListener( new ChartMotionAdapter() {
            public void scrolled(ChartEvent e) {
                if (!redButton.isSelected()) {
                   double tmpMin = Math.min(
                                        chart1.getSeries(0).getMinYValue(),
                                        chart1.getSeries(1).getMinYValue()
                                   );
                   double tmpMax = Math.max(
                                        chart1.getSeries(0).getMaxYValue(),
                                        chart1.getSeries(1).getMaxYValue()
                                   );
                   chart1.getAxes().getLeft().setMinMax(tmpMin, tmpMax);
                }

                if (!greenButton.isSelected()) {
                    greenAxis.setMinMax(
                            chart1.getSeries(2).getMinYValue(),
                            chart1.getSeries(2).getMaxYValue());
                }

                if (!blueButton.isSelected()) {
                    blueAxis.setMinMax(
                            chart1.getSeries(3).getMinYValue(),
                            chart1.getSeries(3).getMaxYValue());
                }
            }
        });
    }

    protected void initAxes() {
        // change the Left axis properties
        Axis tmpAxis;
        tmpAxis = chart1.getAxes().getLeft();
        tmpAxis.getAxisPen().setColor(Color.RED);
        tmpAxis.setStartPosition(0);
        tmpAxis.setEndPosition(33);


        /* create custom axes. This can be done at design-time
         *         with the chart editor.
         */
        greenAxis = chart1.getAxes().getCustom().getNew();
        greenAxis.getAxisPen().setColor(Color.GREEN);
        greenAxis.setStartPosition(33);
        greenAxis.setEndPosition(66);

        blueAxis = chart1.getAxes().getCustom().getNew();
        blueAxis.getAxisPen().setColor(Color.BLUE);
        blueAxis.setStartPosition(66);
        blueAxis.setEndPosition(100);

    }

    protected void initComponents() {
        super.initComponents();
        initAxes();

        Series tmpSeries;
        tmpSeries = new FastLine(chart1.getChart());
        tmpSeries.setVerticalAxis(VerticalAxis.LEFT);
        tmpSeries = new FastLine(chart1.getChart());
        tmpSeries.setVerticalAxis(VerticalAxis.LEFT);
        tmpSeries = new Candle(chart1.getChart());
        tmpSeries.setCustomVertAxis(greenAxis);
        tmpSeries = new Volume(chart1.getChart());
        tmpSeries.setCustomVertAxis(blueAxis);
        chart1.getSeries().fillSampleValues(100);

        redButton = new JCheckBox("Red");
        blueButton = new JCheckBox("Blue");
        greenButton = new JCheckBox("Green");
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(new JLabel("Enable vertical scroll on axis:"));
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(redButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(blueButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(greenButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JCheckBox redButton, blueButton, greenButton;
}
