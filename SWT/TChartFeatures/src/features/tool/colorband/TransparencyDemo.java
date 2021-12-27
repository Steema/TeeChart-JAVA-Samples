/*
 * TransparencyDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.tool.colorband;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Slider;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.Area;
import com.steema.teechart.tools.ColorBand;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class TransparencyDemo extends ChartSample implements SelectionListener {

	private ColorBand tool;
	
	public TransparencyDemo(Composite c) {
		super(c);
		transparencySlider.addSelectionListener(this);
	}
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;
		if (source == transparencySlider) {
            tool.setTransparency(transparencySlider.getSelection());			
		} else {
	    	boolean isSelected =  ((Button)source).getSelection();
	    	if (source == drawBehindButton) {
	    		tool.setDrawBehind(isSelected);
	    	} else if (source == view3DButton) {
	    		chart1.getAspect().setView3D(isSelected);
	    	}
		}
	}
	
	protected void createContent() {
		super.createContent();	
        transparencySlider = addSlider(SWT.HORIZONTAL, 0, 100, 50);
        drawBehindButton = addCheckButton("Draw Behind", "", this);
        view3DButton = addCheckButton("3D", "", this); 
	}

	protected void initContent() {
		super.initContent();
		view3DButton.setSelection(chart1.getAspect().getView3D());
		drawBehindButton.setSelection(false);
	}

	protected void initChart() {
		super.initChart();
        super.initChart();
        chart1.getAspect().setView3D(false);
        chart1.getLegend().setVisible(false);
        chart1.getHeader().setVisible(true);
        chart1.setText("Color Band transparency");
        // remove the chart grid lines
        chart1.getAxes().getLeft().getGrid().setVisible(false);
        chart1.getAxes().getBottom().getGrid().setVisible(false);
        
        Area areaSeries = new com.steema.teechart.styles.Area(chart1.getChart());
        areaSeries.fillSampleValues(20);

        tool = new com.steema.teechart.tools.ColorBand(chart1.getChart());
        tool.setAxis(chart1.getAxes().getLeft());
        tool.setTransparency(50);
        tool.setDrawBehind(false);
        // display the band vertically centered
        tool.setStart(areaSeries.getYValues().getMinimum()+30);
        tool.setEnd(areaSeries.getYValues().getMinimum()-30);
        tool.getBrush().setColor(Color.NAVY);        
	}   	
		
    private Button drawBehindButton, view3DButton;
    private Slider transparencySlider;
}
