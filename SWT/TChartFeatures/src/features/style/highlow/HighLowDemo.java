/*
 * HighLowDemo.java
 *
 * <p>Copyright: (c) 2005-2008 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.style.highlow;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.HighLow;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class HighLowDemo extends ChartSample implements SelectionListener {

    //TODO private ButtonPen highButton, lowButton, linesButton;
    private HighLow series;
    
	public HighLowDemo(Composite c) {
		super(c);
	}
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;
        if (source == highFillButton) {
            //TODO DialogFactory.showModal(series.getHighBrush());
        } else if (source == lowFillButton) {
            //TODO DialogFactory.showModal(series.getLowBrush());
        }
	}	
	
    protected void createContent() {
    	super.createContent();    	   	
        highFillButton = addPushButton("High Fill...", "", this);
        lowFillButton = addPushButton("Low Fill...", "", this);	
    }
    
    protected void initContent() {
    	super.initContent();    	   	
    	highFillButton.setEnabled(false);
    	lowFillButton.setEnabled(false);
    }
    
    protected void initChart() {
    	super.initChart();
    	chart1.getLegend().setVisible(false);
        series = new HighLow(chart1.getChart());
        series.fillSampleValues(20);
        series.setColor(Color.RED);
        series.getHighBrush().setColor(Color.LIME);
        series.getHighPen().setColor(Color.BLUE);
        series.getHighPen().setWidth(2);
        series.getLowBrush().setColor(Color.WHITE);
        series.getPen().setColor(Color.BLUE);
        //TODO series.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));

        //highButton = new ButtonPen(series.getHighPen(), "High...");
        //lowButton = new ButtonPen(series.getLowPen(), "Low...");
        //linesButton = new ButtonPen(series.getPen(), "Lines...");
    }   			
	
    private Button highFillButton, lowFillButton;    
}
