/*
 * SubtractDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.function;

import java.util.ArrayList;

import org.eclipse.swt.widgets.Composite;

import com.steema.teechart.TextShapeStyle;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.functions.Function;
import com.steema.teechart.styles.Bar;
import com.steema.teechart.styles.CustomStack;
import com.steema.teechart.styles.Line;
import com.steema.teechart.styles.PointerStyle;
import com.steema.teechart.styles.VerticalAxis;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class SubtractDemo extends ChartSample {

    private Line lineSeries;
    private Bar barSeries1, barSeries2;
    private Function subtractFunction;	

	public SubtractDemo(Composite c) {
		super(c);
	}
	
    protected void initContent() {
    	super.initContent();            	   	
        getButtonPane().setVisible(false);
    }
    
    protected void initChart() {
    	super.initChart();
        chart1.getHeader().setVisible(true);
        chart1.setText("Subtract function");
        barSeries1 = new com.steema.teechart.styles.Bar(chart1.getChart());
        barSeries1.setTitle("Source1");
        barSeries1.setColor(Color.RED);
        barSeries1.getMarks().setColor(Color.BLACK);
        barSeries1.getMarks().setBackColor(Color.BLACK);
        barSeries1.getMarks().getFont().setColor(Color.RED);
        barSeries1.getMarks().setArrowLength(20);
        barSeries1.add(new int[] {2,3,5,7,1,4});

        barSeries2 = new com.steema.teechart.styles.Bar(chart1.getChart());
        barSeries2.setTitle("Source2");
        barSeries2.setColor(Color.BLUE);
        barSeries2.getMarks().setColor(Color.BLACK);
        barSeries2.getMarks().setBackColor(Color.BLACK);
        barSeries2.getMarks().getFont().setColor(Color.RED);
        barSeries2.getMarks().setArrowLength(20);
        barSeries2.add(new int[] {1,5,9,3,8,2});

        lineSeries = new com.steema.teechart.styles.Line(chart1.getChart());
        lineSeries.setTitle("Subtract");
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

        subtractFunction = new com.steema.teechart.functions.Subtract();
        subtractFunction.setChart(chart1.getChart());
        subtractFunction.setPeriod(0); //all points

        ArrayList tmpArray = new ArrayList();
        tmpArray.add(barSeries1);
        tmpArray.add(barSeries2);
        lineSeries.setDataSource(tmpArray);
        lineSeries.setFunction(subtractFunction);        
    } 
}
