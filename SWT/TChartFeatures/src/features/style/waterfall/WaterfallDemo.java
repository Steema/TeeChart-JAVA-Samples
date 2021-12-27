/*
 * WaterfallDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.style.waterfall;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.styles.Waterfall;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class WaterfallDemo extends ChartSample implements SelectionListener {

	private Waterfall series;
	//TODO private ButtonPen borderButton, penButton;

	public WaterfallDemo(Composite c) {
		super(c);
	}

	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;
		if (source == editButton) {
			//TODO ChartEditor.editSeries(series);
		}
	}	

	protected void createContent() {
		super.createContent();

		editButton = addPushButton("Edit...", "", this);

		/* TODO
        borderButton = new ButtonPen(series.getPen(), "Border...");
        penButton = new ButtonPen(series.getWaterLines(), "Lines...");
		 */  	
	}

	protected void initChart() {
		super.initChart();
		series = new Waterfall(chart1.getChart());
		series.fillSampleValues(20);
		series.setTimesZOrder(2);
	}   		

	private Button editButton;

}
