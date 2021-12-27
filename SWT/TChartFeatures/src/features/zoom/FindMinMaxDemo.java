/*
 * FindMinMaxDemo.java
 *
 * <p>Copyright: (c) 2004-2008 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.zoom;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.Line;
import com.steema.teechart.styles.Series;
import com.steema.teechart.tools.ColorLine;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class FindMinMaxDemo extends ChartSample implements SelectionListener {

	public FindMinMaxDemo(Composite c) {
		super(c);
	}
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;
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

	protected void createContent() {
		super.createContent();    	       	
		findButton = addPushButton("Find min and max", "", this);
		resetButton = addPushButton("Reset", "", this);		
	}
	
	protected void initContent() {
		super.initContent();   
		resetButton.setEnabled(false);
	}

	protected void initChart() {
		super.initChart();
        chart1.getLegend().setVisible(false);
        chart1.getAspect().setChart3DPercent(20);
        
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

    Button findButton, resetButton;	
}
