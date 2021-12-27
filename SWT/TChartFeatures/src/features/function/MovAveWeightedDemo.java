/*
 * MovAveWeightedDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.function;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.functions.MovingAverage;
import com.steema.teechart.legend.Legend;
import com.steema.teechart.legend.LegendAlignment;
import com.steema.teechart.styles.Line;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class MovAveWeightedDemo extends ChartSample implements SelectionListener {

    private Line sourceSeries;
    private MovingAverage movAveFunction;

	public MovAveWeightedDemo(Composite c) {
		super(c);
	}
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;
		boolean isSelected = ((Button)source).getSelection();
        if (source == weightedButton) {
            movAveFunction.setWeighted(isSelected);
        };
	}	

	protected void createContent() {
		super.createContent();	
        weightedButton = addCheckButton("Weighted", "", this);	
	}

	protected void initContent() {
		super.initContent();
		weightedButton.setSelection(true);		
	}

	protected void initChart() {
		super.initChart();
        chart1.getAspect().setView3D(false);

        chart1.getHeader().setVisible(true);
        chart1.setText("Weighted Moving Average Function");

        Legend tmpLegend = chart1.getLegend();
        tmpLegend.setAlignment(LegendAlignment.BOTTOM);
        
        sourceSeries = new com.steema.teechart.styles.Line(chart1.getChart());;
        sourceSeries.setTitle("Source");
        sourceSeries.getMarks().setVisible(false);
        sourceSeries.getPointer().setVisible(false);
        sourceSeries.setColor(Color.RED);
        sourceSeries.fillSampleValues(100);

        Line tmpSeries = new com.steema.teechart.styles.Line(chart1.getChart());;
        tmpSeries.setTitle("Moving Average");
        tmpSeries.setColor(Color.BLUE);
        tmpSeries.getMarks().setVisible(false);
        tmpSeries.getPointer().setVisible(false);

        movAveFunction = new com.steema.teechart.functions.MovingAverage(chart1.getChart());
        movAveFunction.setPeriod(10);
        movAveFunction.setWeighted(true);

        tmpSeries.setDataSource(sourceSeries);
        tmpSeries.setFunction(movAveFunction);
	}   	

    private Button weightedButton;
}
