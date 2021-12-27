/*
 * AxisArrowDemo.java
 *
 * <p>Copyright: (c) 2005-2008 by Steema Software SL.
 All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.tool;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.Line;
import com.steema.teechart.tools.AxisArrow;
import com.steema.teechart.tools.AxisArrowPosition;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class AxisArrowDemo extends ChartSample implements SelectionListener {

    private AxisArrow tool1, tool2, tool3;
    
	public AxisArrowDemo(Composite c) {
		super(c);
	}

	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;				
	    if (source == editButton) {
	    	//TODO ChartEditor.editTool(tool1);
	    } else {
	    	boolean isSelected =  ((Button)source).getSelection();
	        if (source == leftTopButton) {
	            tool1.setActive(isSelected);
	        } else if (source == bottomButton) {
	            tool2.setActive(isSelected);
	        }
	    }
	}
	
	protected void createContent() {
		super.createContent();	

        leftTopButton = addCheckButton("Active Left Top", "", this);
        bottomButton = addCheckButton("Active Botto", "", this);
        editButton = addPushButton("Edit...", "", this);        
        Label clickedLabel = addLabel(SWT.LEFT, "");        
	}

	protected void initContent() {
		super.initContent();
		editButton.setEnabled(false);
        leftTopButton.setSelection(true);
        bottomButton.setSelection(true);        
	}

	protected void initChart() {
		super.initChart();
        chart1.getLegend().setVisible(false);
        chart1.getAspect().setView3D(false);

        Line tmpSeries = new com.steema.teechart.styles.Line(chart1.getChart());
        tmpSeries.fillSampleValues(10);
        tmpSeries.getPointer().setVisible(true);
        tmpSeries.getMarks().setVisible(false);

        tool1 = new AxisArrow(chart1.getAxes().getLeft());
        tool1.setPosition(AxisArrowPosition.START);


        tool2 = new AxisArrow(chart1.getAxes().getBottom());
        tool2.getBrush().setColor(Color.GREEN_YELLOW);
        tool2.getPen().setColor(Color.WHITE);
        tool2.setPosition(AxisArrowPosition.BOTH);

        tool3 = new AxisArrow(chart1.getAxes().getLeft());
        tool3.setPosition(AxisArrowPosition.END);
        tool3.getBrush().setColor(Color.WHITE);
        tool3.getPen().setColor(Color.BLUE);
        tool3.setLength(36);        
	}   	
	
    private Button leftTopButton, bottomButton;
    private Button editButton;
}
