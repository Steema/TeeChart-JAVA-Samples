/*
 * MinorGridDemo.java
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
import com.steema.teechart.drawing.DashStyle;
import com.steema.teechart.styles.Area;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class MinorGridDemo extends ChartSample implements SelectionListener {

	public MinorGridDemo(Composite c) {
		super(c);
	}
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
		if (source instanceof Button) {
			boolean isSelected = ((Button)source).getSelection();
	        if (source == visibleButton) {
	            chart1.getAxes().getBottom().getMinorGrid().setVisible(isSelected);
	        }			
		}
	}
	
	protected void createContent() {
		super.createContent();
        visibleButton = addCheckButton("Minor Grid visible", "", this);
	}

	protected void initContent() {
		super.initContent();
        visibleButton.setSelection(chart1.getAxes().getBottom().getMinorGrid().getVisible());
	}

	protected void initChart() {
		super.initChart();
        chart1.getAspect().setView3D(false);
        Axis axis = chart1.getAxes().getBottom();
        axis.getMinorGrid().setVisible(true);
        axis.setMinorTickCount(3);
        axis.getGrid().setStyle(DashStyle.SOLID);
        axis.getMinorGrid().setColor(Color.WHITE);
        axis.getMinorGrid().setStyle(DashStyle.DOT);

        Area series = new Area(chart1.getChart());
        series.fillSampleValues(4);        
	}   			
	
    private Button visibleButton;	
}
