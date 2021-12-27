/*
 * ZoomScrollDemo.java
 *
 * <p>Copyright: Copyright (c) 2004-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.zoom;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.styles.Line;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class ZoomScrollDemo extends ChartSample implements SelectionListener {

	public ZoomScrollDemo(Composite c) {
		super(c);
	}

	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;
        if (source == scrollButton) {
            chart1.getAxes().getBottom().scroll(2, false);
        } else if (source == zoomButton) {
            chart1.getAspect().setZoom(110);  // 110 % = zoom in 10%
        }
	}	

	protected void createContent() {
		super.createContent();    	       	
		scrollButton = addPushButton("Scroll !", "", this);
		zoomButton = addPushButton("Zoom !", "", this);		
	}

	protected void initChart() {
		super.initChart();
        chart1.getLegend().setVisible(false);
        Line series = new Line(chart1.getChart());
        series.fillSampleValues(50);        
	}   			
	
    Button scrollButton, zoomButton;	
}
