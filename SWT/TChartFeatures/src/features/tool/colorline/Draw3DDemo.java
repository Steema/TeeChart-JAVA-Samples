/*
 * Draw3DDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.tool.colorline;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.styles.Points;
import com.steema.teechart.tools.ColorLine;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class Draw3DDemo extends ChartSample implements SelectionListener {

    private ColorLine tool;

	public Draw3DDemo(Composite c) {
		super(c);
	}

	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
        if (source instanceof Button) {
        	boolean isSelected = ((Button)source).getSelection();
            if (source == draw3DButton) {
                tool.setDraw3D(isSelected);
            } else if (source == drawBehindButton) {
                tool.setDrawBehind(isSelected);
            }
        };
	}
	
	protected void createContent() {
		super.createContent();	  
		draw3DButton = addCheckButton("View 3D", "", this);       
		drawBehindButton = addCheckButton("Draw Behind", "", this);		
	}

	protected void initContent() {
		super.initContent();	
		draw3DButton.setSelection(chart1.getAspect().getView3D());    	
	}

	protected void initChart() {
		super.initChart();
        Points tmpSeries = new Points(chart1.getChart());
        tmpSeries.setColorEach(true);
        tmpSeries.getMarks().setVisible(false);
        tmpSeries.fillSampleValues();

        double tmpValue =
                (tmpSeries.getYValues().getMaximum()
                + tmpSeries.getYValues().getMinimum()
                ) / 2;

        tool = new com.steema.teechart.tools.ColorLine(chart1.getChart());
        tool.setValue(tmpValue);
        tool.setAxis(chart1.getAxes().getLeft());
        tool.setDraw3D(true);
	}   		
	
    Button draw3DButton, drawBehindButton;
}	
