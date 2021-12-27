/*
 * GradientDemo.java
 *
 * <p>Copyright: (c) 2005-2008 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.area;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Slider;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.GradientDirection;
import com.steema.teechart.styles.Area;
import com.steema.teechart.styles.CustomStack;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class GradientDemo extends ChartSample implements SelectionListener {

    private Area areaSeries;
    
	public GradientDemo(Composite c) {
		super(c);
		transparencySlider.addSelectionListener(this);
	}
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;
        if (source instanceof Button) {
        	boolean isSelected = ((Button)source).getSelection();
            if (source == showGradientEditorButton) {
            	//TODO
            } else if (source == gradientButton) {
            	areaSeries.getGradient().setVisible(isSelected);
            }                    	
        } else if (source instanceof Slider) {
            if (transparencySlider == source) {
                areaSeries.setTransparency(transparencySlider.getSelection());
                areaSeries.getGradient().setTransparency(transparencySlider.getSelection());
                areaSeries.repaint();
            }      
        }
	}		
	
	protected void createContent() {
		super.createContent();
        showGradientEditorButton = addPushButton("Edit gradient...", "", this);
        gradientButton = addCheckButton("Gradient Visible", "", this);
        transparencySlider = addSlider(SWT.HORIZONTAL, 0, 100, 0);		
 	}
	
	protected void initContent() {
		super.initContent();		  	
		gradientButton.setSelection(areaSeries.getGradient().getVisible());
	}
	
	protected void initChart() {
		super.initChart();
        chart1.getAspect().setView3D(false);	
        areaSeries = new Area(chart1.getChart());
        areaSeries.getMarks().setVisible(false);
        areaSeries.setColor(Color.RED);
        areaSeries.fillSampleValues(10);
        areaSeries.setTransparency(0);
        areaSeries.setStacked(CustomStack.NONE);

        com.steema.teechart.drawing.Gradient tmpGradient = areaSeries.getGradient();
        tmpGradient.setVisible(true);
        tmpGradient.setUseMiddle(true);
        tmpGradient.setDirection(GradientDirection.HORIZONTAL);
        tmpGradient.setStartColor(Color.RED);
        tmpGradient.setMiddleColor(Color.BLUE);
        tmpGradient.setEndColor(Color.GREEN);
        tmpGradient.setTransparency(0);
	}   			
	
    private Button showGradientEditorButton;
    private Button gradientButton;
    private Slider transparencySlider;	
}
