/*
 * BehindAxesDemo.java
 *
 * <p>Copyright: (c) 2005-2008 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.axes;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.Bar;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class BehindAxesDemo extends ChartSample implements SelectionListener {

	public BehindAxesDemo(Composite c) {
		super(c);
	}
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
		if (source instanceof Button) {
			boolean isSelected = ((Button)source).getSelection();
	        if (source == behindButton) {
	            chart1.getAxes().setDrawBehind(isSelected);
	        }			
		}
	}
	
	protected void createContent() {
		super.createContent();
		behindButton = addCheckButton("Axis behind", "", this);				
	}

	protected void initContent() {
		super.initContent();
		behindButton.setSelection(chart1.getAxes().getDrawBehind());
	}

	protected void initChart() {
		super.initChart();
        chart1.getAspect().setView3D(false);
        chart1.getAxes().setDrawBehind(false);
        chart1.getAxes().getLeft().getGrid().setColor(Color.WHITE);
        chart1.getAxes().getBottom().getGrid().setColor(Color.BLUE);
        
        Bar series = new Bar(chart1.getChart());
        series.getMarks().setVisible(false);
        series.fillSampleValues(6);        
	}   				
	
    private Button behindButton;	
}
