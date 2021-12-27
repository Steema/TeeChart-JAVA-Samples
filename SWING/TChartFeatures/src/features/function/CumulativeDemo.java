/*
 * CumulativeDemo.java
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
import com.steema.teechart.styles.SeriesMarks;
import com.steema.teechart.styles.SeriesPointer;

import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class CumulativeDemo extends ChartSamplePanel {

    private Line lineSeries;
    private Bar barSeries;
    private Function cumulativeFunction;

    /**
     * Creates a new instance of CumulativeDemo
     */
    public CumulativeDemo() {
        super();
    }

    protected void initComponents() {
        super.initComponents();

        SeriesMarks tmpMarks;
        SeriesPointer tmpPointer;

        barSeries = new com.steema.teechart.styles.Bar(chart1.getChart());
        barSeries.setTitle("Source");
        barSeries.setColor(Color.RED);
        tmpMarks = barSeries.getMarks();
        tmpMarks.setColor(Color.BLACK);
        tmpMarks.setBackColor(Color.BLACK);
        tmpMarks.getFont().setColor(Color.MAROON);
        tmpMarks.setTransparent(true);
        tmpMarks.setArrowLength(20);
        barSeries.add(new int[] {7,5,6,8,2,1,9,3,4,1});


        lineSeries = new com.steema.teechart.styles.Line(chart1.getChart());
        lineSeries.setTitle("Cumulative");
        lineSeries.setColor(Color.GREEN);
        lineSeries.setStacked(CustomStack.NONE);

        tmpMarks = lineSeries.getMarks();
        tmpMarks.setArrowLength(8);
        tmpMarks.getShadow().setVisible(false);
        tmpMarks.setShapeStyle(TextShapeStyle.ROUNDRECTANGLE);
        tmpMarks.setVisible(true);

        tmpPointer = lineSeries.getPointer();
        tmpPointer.setColor(Color.YELLOW);
        tmpPointer.setInflateMargins(false);
        tmpPointer.setStyle(PointerStyle.RECTANGLE);
        tmpPointer.setVisible(true);

        cumulativeFunction = new com.steema.teechart.functions.Cumulative(chart1.getChart());
        cumulativeFunction.setPeriod(1);

        lineSeries.setDataSource(barSeries);
        lineSeries.setFunction(cumulativeFunction);
    }

    protected void initGUI() {
        super.initGUI();

        chart1.getHeader().setVisible(true);
        chart1.setText("Cumulative function");

        getButtonPane().setVisible(false);
    }
}
