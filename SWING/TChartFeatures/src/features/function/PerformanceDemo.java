/*
 * PerformanceDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.function;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.functions.Function;
import com.steema.teechart.styles.Bar;
import com.steema.teechart.styles.CustomStack;
import com.steema.teechart.styles.Line;
import com.steema.teechart.styles.PointerStyle;
import com.steema.teechart.styles.SeriesMarks;
import com.steema.teechart.styles.SeriesPointer;
import com.steema.teechart.styles.VerticalAxis;

import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class PerformanceDemo extends ChartSamplePanel {

    private Line lineSeries;
    private Bar barSeries;
    private Function performanceFunction;

    /**
     * Creates a new instance of PerformanceDemo
     */
    public PerformanceDemo() {
        super();
    }

    protected void initChart() {
        super.initChart();
        chart1.getHeader().setVisible(true);
        chart1.getLegend().setVisible(false);
        chart1.setText("Performance function");
        chart1.getAspect().setView3D(false);

        chart1.getAxes().getRight().setAutomatic(false);
        chart1.getAxes().getRight().setMaximum(100);
        chart1.getAxes().getRight().setMinimum(-100);
        chart1.getAxes().getRight().getGrid().setVisible(false);
    }

    protected void initComponents() {
        super.initComponents();

        SeriesMarks tmpMarks;
        SeriesPointer tmpPointer;

        barSeries = new com.steema.teechart.styles.Bar(chart1.getChart());
        barSeries.setTitle("Source");
        barSeries.setColor(Color.RED);
        tmpMarks = barSeries.getMarks();
        tmpMarks.setArrowLength(20);
        tmpMarks.setVisible(true);

        barSeries.fillSampleValues(10);

        lineSeries = new com.steema.teechart.styles.Line(chart1.getChart());
        lineSeries.setTitle("Performance");
        lineSeries.setColor(Color.BLUE);
        lineSeries.getLinePen().setWidth(2);
        lineSeries.setStacked(CustomStack.NONE);
        lineSeries.setVerticalAxis(VerticalAxis.RIGHT);

        tmpMarks = lineSeries.getMarks();
        tmpMarks.setVisible(false);

        tmpPointer = lineSeries.getPointer();
        tmpPointer.getBrush().setColor(Color.WHITE);
        tmpPointer.setInflateMargins(false);
        tmpPointer.setStyle(PointerStyle.RECTANGLE);
        tmpPointer.setVisible(true);

        performanceFunction = new com.steema.teechart.functions.Performance(chart1.getChart());
        performanceFunction.setPeriod(1);

        lineSeries.setDataSource(barSeries);
        lineSeries.setFunction(performanceFunction);
    }

    protected void initGUI() {
        super.initGUI();

        getButtonPane().setVisible(false);
    }
}
