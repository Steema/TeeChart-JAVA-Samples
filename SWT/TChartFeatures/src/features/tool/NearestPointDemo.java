/*
 * NearestPointDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.tool;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.DashStyle;
import com.steema.teechart.styles.CustomStack;
import com.steema.teechart.styles.Points;
import com.steema.teechart.tools.NearestPoint;
import com.steema.teechart.tools.NearestPointStyle;

import features.ChartSample;
import features.utils.EnumStrings;

/**
 * @author tom
 *
 */
public class NearestPointDemo extends ChartSample implements SelectionListener {

    private NearestPoint tool;
    
	public NearestPointDemo(Composite c) {
		super(c);
		styleList.addSelectionListener(this);
	}

	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
        if (source == styleList) {
            switch (styleList.getSelectionIndex()) {
                case 0: tool.setStyle(NearestPointStyle.NONE); break;
                case 1: tool.setStyle(NearestPointStyle.CIRCLE); break;
                case 2: tool.setStyle(NearestPointStyle.RECTANGLE); break;
                case 3: tool.setStyle(NearestPointStyle.DIAMOND); break;
            }
        } else if (source == editButton) {
            //TODO ChartEditor.editTool(tool);
        } else {
            boolean isSelected = ((Button)source).getSelection();
            if (source == activeButton) {
                tool.setActive(isSelected);
            } else if (source == drawLineButton) {
                tool.setDrawLine(isSelected);
            }        	
        }
	}
	
	protected void createContent() {
		super.createContent();	 
		addLabel(SWT.LEFT, "Style: ");
        styleList = addCombo(SWT.BORDER | SWT.SINGLE | SWT.READ_ONLY);		
        activeButton = addCheckButton("Active", "", this);
        drawLineButton = addCheckButton("Draw line", "", this);
        editButton = addPushButton("Edit...", "", this);		
	}

	protected void initContent() {
		super.initContent();	
		styleList.setItems(EnumStrings.NEARESTPOINT_STYLES);
        styleList.select(1);		
		activeButton.setSelection(true);  
	    drawLineButton.setSelection(true);		
	}

	protected void initChart() {
		super.initChart();
        chart1.getHeader().setVisible(false);
        chart1.getAspect().setView3D(false);
        
        Points tmpSeries = new com.steema.teechart.styles.Points(chart1.getChart());
        tmpSeries.getMarks().setArrowLength(20);
        tmpSeries.getMarks().setVisible(true);
        tmpSeries.getPointer().setVisible(true);
        tmpSeries.setStacked(CustomStack.NONE);
        tmpSeries.fillSampleValues(6);

        tool = new com.steema.teechart.tools.NearestPoint(chart1.getChart());
        tool.setSeries(tmpSeries);
        tool.getBrush().setColor(Color.WHITE);
        tool.getBrush().setVisible(false);
        tool.getPen().setColor(Color.WHITE);
        tool.getPen().setStyle(DashStyle.DOT);
        tool.setStyle(NearestPointStyle.CIRCLE);
        tool.setDrawLine(true);        
	}   		
	
    private Button editButton;
    private Button activeButton, drawLineButton;
    private Combo styleList;	
}
