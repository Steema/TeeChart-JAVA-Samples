/*
 * MultiScrollDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.axes;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.axis.Axis;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.events.ChartEvent;
import com.steema.teechart.events.ChartMotionAdapter;
import com.steema.teechart.styles.Candle;
import com.steema.teechart.styles.FastLine;
import com.steema.teechart.styles.Series;
import com.steema.teechart.styles.VerticalAxis;
import com.steema.teechart.styles.Volume;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class MultiScrollDemo extends ChartSample implements SelectionListener {

    protected Axis blueAxis, greenAxis;
    
	public MultiScrollDemo(Composite c) {
		super(c);
	}
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
		if (source instanceof Button) {
			boolean isSelected = ((Button)source).getSelection();			
		}
	}
	
	protected void createContent() {
		super.createContent();
        redButton = addCheckButton("Red", "", this);
        blueButton = addCheckButton("Blue", "", this);
        greenButton = addCheckButton("Green", "", this);				
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

	protected void initChart() {
		super.initChart();
        chart1.getLegend().setVisible(false);
        chart1.getAspect().setView3D(false);
        chart1.addChartMotionListener( new ChartMotionAdapter() {
            public void scrolled(ChartEvent e) {
                if (!redButton.getSelection()) {
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

                if (!greenButton.getSelection()) {
                    greenAxis.setMinMax(
                            chart1.getSeries(2).getMinYValue(),
                            chart1.getSeries(2).getMaxYValue());
                }

                if (!blueButton.getSelection()) {
                    blueAxis.setMinMax(
                            chart1.getSeries(3).getMinYValue(),
                            chart1.getSeries(3).getMaxYValue());
                }
            }
        });
        
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
	}   		
	
    private Button redButton, blueButton, greenButton;	
}
