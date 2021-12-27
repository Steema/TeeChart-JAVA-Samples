/*
 * CountDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.function;

import com.steema.teechart.TextShapeStyle;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.functions.Function;
import com.steema.teechart.styles.Bar;
import com.steema.teechart.styles.CustomStack;
import com.steema.teechart.styles.Line;
import com.steema.teechart.styles.PointerStyle;
import com.steema.teechart.styles.VerticalAxis;

import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class CountDemo extends ChartSamplePanel {

    private Line lineSeries ;
    private Bar barSeries;
    private Function countFunction;

    /**
     * Creates a new instance of CountDemo
     */
    public CountDemo() {
        super();
    }

    protected void initComponents() {
        super.initComponents();

        barSeries = new com.steema.teechart.styles.Bar(chart1.getChart());
        barSeries.setTitle("Source");
        barSeries.setColor(Color.RED);
        barSeries.getMarks().setColor(Color.BLACK);
        barSeries.getMarks().setBackColor(Color.BLACK);
        barSeries.getMarks().getFont().setColor(Color.RED);
        barSeries.getMarks().setArrowLength(20);
        barSeries.fillSampleValues(6);

        lineSeries = new com.steema.teechart.styles.Line(chart1.getChart());
        lineSeries.setTitle("Count");
        lineSeries.setColor(Color.GREEN);
        lineSeries.setStacked(CustomStack.NONE);
        lineSeries.setVerticalAxis(VerticalAxis.RIGHT);
        lineSeries.getMarks().setArrowLength(8);
        lineSeries.getMarks().getShadow().setVisible(false);
        lineSeries.getMarks().setShapeStyle(TextShapeStyle.ROUNDRECTANGLE);
        lineSeries.getMarks().setVisible(true);
        lineSeries.getPointer().setColor(Color.OLIVE);
        lineSeries.getPointer().setInflateMargins(false);
        lineSeries.getPointer().setStyle(PointerStyle.RECTANGLE);
        lineSeries.getPointer().setVisible(true);

        countFunction = new com.steema.teechart.functions.Count();
        countFunction.setChart(chart1.getChart());
        countFunction.setPeriod(0); //all points

        lineSeries.setDataSource(barSeries);
        lineSeries.setFunction(countFunction);

        chart1.getHeader().setVisible(true);
        chart1.setText("Count function");
    }

    protected void initGUI() {
        super.initGUI();
        getButtonPane().setVisible(false);
    }

}
