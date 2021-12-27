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
import com.steema.teechart.styles.FastLine;
import com.steema.teechart.styles.VerticalAxis;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class MultiRuntimeDemo extends ChartSample implements SelectionListener {

    private FastLine series2, series3;

	public MultiRuntimeDemo(Composite c) {
		super(c);
	}
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
		if (source instanceof Button) {
			boolean isSelected = ((Button)source).getSelection();
	        if (source == singleButton) {
	            if (isSelected) {
	                series2.setVerticalAxis(VerticalAxis.LEFT);
	                series3.setVerticalAxis(VerticalAxis.LEFT);
	                chart1.getAxes().getLeft().setEndPosition(100);
	            } else {
	                series2.setCustomVertAxis(0);
	                series3.setCustomVertAxis(1);
	                chart1.getAxes().getLeft().setEndPosition(30);
	            }
	        }			
		}
	}
	
	protected void createContent() {
		super.createContent();
        singleButton = addCheckButton("Single axis", "", this);		
	}

	protected void initContent() {
		super.initContent();
        singleButton.setSelection(chart1.getAxes().getBottom().getMinorGrid().getVisible());	}

	protected void initChart() {
		super.initChart();
        chart1.getAspect().setView3D(false);
        chart1.getAxes().getLeft().setEndPosition(30);
        
        new FastLine(chart1.getChart());
        series2 = new FastLine(chart1.getChart());
        series3 = new FastLine(chart1.getChart());

        chart1.getSeries().fillSampleValues(1000);
        
        chart1.getAxes().getCustom().clear();      
        Axis axis;
        axis = chart1.getAxes().getCustom().getNew();
        axis.setStartPosition(30);
        axis.setEndPosition(60);
        axis.getAxisPen().setColor(series2.getColor());
        series2.setCustomVertAxis(axis);

        axis = chart1.getAxes().getCustom().getNew();
        axis.setStartPosition(60);
        axis.setEndPosition(100);
        axis.getAxisPen().setColor(series3.getColor());
        series3.setCustomVertAxis(axis);
	}   			
	
    private Button singleButton;
}
