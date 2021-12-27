/*
 * LabelsInsideDemo.java
 *
 * <p>Copyright: Copyright (c) 2004-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.polar;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.ImageMode;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.Polar;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class LabelsInsideDemo extends ChartSample implements SelectionListener {

	private Polar series;
	   
	public LabelsInsideDemo(Composite c) {
		super(c);
	}

	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
        if (source instanceof Button) {
        	boolean isSelected = ((Button)source).getSelection();
            if (source == insideButton) {
            	series.setCircleLabelsInside(isSelected);
            } 
        };
	}	
	
	protected void createContent() {
		super.createContent();	

		insideButton = addCheckButton("Circle Labels Inside", "", this);
	}

	protected void initContent() {
		super.initContent();
        insideButton.setSelection(series.getCircleLabelsInside());
	}

	protected void initChart() {
		super.initChart();
        chart1.getPanel().getGradient().setEndColor(Color.OLIVE);
        chart1.getPanel().getGradient().setStartColor(Color.WHITE);
        chart1.getPanel().getGradient().setVisible(true);
        chart1.getLegend().setVisible(false);
        chart1.getAxes().setVisible(false);
        chart1.getAxes().getBottom().getGrid().setVisible(false);
        chart1.getAxes().getLeft().getGrid().setVisible(false);
        chart1.getAspect().setView3D(false);
        
        series = new com.steema.teechart.styles.Polar(chart1.getChart());
        series.fillSampleValues(10);
        series.setCircleLabels(true);
        series.setCircleLabelsInside(true);
        series.setClockWiseLabels(true);
        series.setCircled(true);
        series.setCircleBackColor(Color.EMPTY);
        series.getCircleLabelsFont().setColor(Color.NAVY);
        series.getCircleLabelsFont().setSize(13);
        series.getCircleLabelsFont().setBold(true);
        series.getCircleLabelsFont().setItalic(true);
        series.setTransparency(35);
        series.getBrush().setColor(Color.WHITE);
        series.getBrush().setSolid(true);
        series.getBrush().loadImage(ChartSample.class.getResourceAsStream(URL_BRUSHPATTERN));
        series.getBrush().setImageMode(ImageMode.TILE);       
	}  
	
	private Button insideButton;
    private final static String URL_BRUSHPATTERN = "images/zigzag.png";	
}
