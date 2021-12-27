/*
 * HistogramDemo.java
 *
 * <p>Copyright: (c) 2005-2008 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.style.histogram;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.styles.Histogram;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class HistogramDemo extends ChartSample {

    //TODO private ButtonPen lineButton, linesButton;

	public HistogramDemo(Composite c) {
		super(c);
	}
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;
	}	
	
    protected void createContent() {
    	super.createContent();
    	
    	/* TODO
        lineButton = new ButtonPen(series.getLinePen(), "Border...");
        linesButton = new ButtonPen(series.getLinesPen(), "Pen...");
        */   	
    }
    
    protected void initChart() {
    	super.initChart();
        chart1.getAspect().setView3D(false);
        Histogram series = new Histogram(chart1.getChart());
        series.fillSampleValues(10);           
    }   			
}
