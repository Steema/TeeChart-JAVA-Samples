/*
 * ColorLineDemo.java
 *
 * <p>Copyright: (c) 2005-2008 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.tool.colorline;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.axis.Axis;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.Line;
import com.steema.teechart.styles.Points;
import com.steema.teechart.tools.ColorLine;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class ColorLineDemo extends ChartSample implements SelectionListener {

    //TODO private ButtonPen tool1ButtonPen, tool2ButtonPen;	
    private ColorLine tool1, tool2;
    
	public ColorLineDemo(Composite c) {
		super(c);
	}

	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
        if (source instanceof Button) {
        	boolean isSelected = ((Button)source).getSelection();
            if (source == showLinesButton) {
                tool1.setActive(isSelected);
                tool2.setActive(isSelected);
            } else if (source == view3DButton) {
                chart1.getAspect().setView3D(isSelected);
                view3DLinesButton.setEnabled(isSelected);
            } else if (source == view3DLinesButton) {
                tool1.setDraw3D(isSelected);
                tool2.setDraw3D(isSelected);
            } else if (source == drawBehindButton) {
                tool1.setDrawBehind(isSelected);
                tool2.setDrawBehind(isSelected);
            } else if (source == allowDragButton) {
                tool1.setAllowDrag(isSelected);
                tool2.setAllowDrag(isSelected);
            } else if (source == dragRepaintButton) {
                tool1.setDragRepaint(isSelected);
                tool2.setDragRepaint(isSelected);
            } else if (source == noLimitDragButton) {
                tool1.setNoLimitDrag(isSelected);
                tool2.setNoLimitDrag(isSelected);
            }
        };
	}
	
	protected void createContent() {
		super.createContent();	  
        showLinesButton = addCheckButton("Show Lines", "", this);
        view3DButton = addCheckButton("3D", "", this);
        view3DLinesButton = addCheckButton("3D Lines", "", this);
        drawBehindButton = addCheckButton("Draw Behind", "", this);
        allowDragButton = addCheckButton("Allow Drag", "", this);
        dragRepaintButton = addCheckButton("Drag Repaint", "", this);
        noLimitDragButton = addCheckButton("No Limit Drag", "", this);		
	}

	protected void initContent() {
		super.initContent();	
		view3DButton.setSelection(chart1.getAspect().getView3D());
        view3DLinesButton.setSelection(true);
        showLinesButton.setSelection(true);
        allowDragButton.setSelection(true);        
	}

	protected void initChart() {
		super.initChart();
        chart1.getHeader().setVisible(false);
        chart1.getAxes().getLeft().setAutomatic(false);
        chart1.getAxes().getLeft().setAutomaticMinimum(false);
        chart1.getAxes().getLeft().setEndPosition(80);
        
        tool1 = new com.steema.teechart.tools.ColorLine(chart1.getChart());
        tool1.getPen().setColor(Color.BLUE);
        tool1.getPen().setWidth(2);
        tool1.setAxis(chart1.getAxes().getLeft());

        tool2 = new com.steema.teechart.tools.ColorLine(chart1.getChart());
        tool2.getPen().setColor(Color.LIME);
        tool2.getPen().setWidth(2);
        tool2.setValue(10.0);
        tool2.setAxis(chart1.getAxes().getBottom());

        //TODO tool1ButtonPen = new ButtonPen( tool1.getPen() );
        //TODO tool2ButtonPen = new ButtonPen( tool2.getPen() );

        Axis tmpAxis = chart1.getAxes().getCustom().getNew();
        tmpAxis.setHorizontal(false);
        tmpAxis.setOtherSide(false);
        tmpAxis.setStartPosition(80.0);

        Line tmpLineSeries = new com.steema.teechart.styles.Line(chart1.getChart());
        tmpLineSeries.fillSampleValues(20);
        tmpLineSeries.getMarks().setVisible(false);
        tmpLineSeries.setCustomVertAxis(tmpAxis);

        Points tmpPointSeries = new com.steema.teechart.styles.Points(chart1.getChart());
        tmpPointSeries.fillSampleValues(20);
        tmpPointSeries.getMarks().setVisible(false);        
	}   		
	
    private Button showLinesButton, view3DButton, view3DLinesButton, drawBehindButton;
    private Button allowDragButton, dragRepaintButton, noLimitDragButton;
}
