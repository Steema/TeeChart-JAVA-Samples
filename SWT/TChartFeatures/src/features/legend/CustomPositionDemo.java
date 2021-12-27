/*
 * CustomPositionDemo.java
 *
 * <p>Copyright: (c) 2004-2008 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.legend;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Slider;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.styles.Pie;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class CustomPositionDemo extends ChartSample implements SelectionListener {

	public CustomPositionDemo(Composite c) {
		super(c);
        leftSlider.addSelectionListener(this);
        topSlider.addSelectionListener(this);		
	}

	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;
		if (source instanceof Button) {
			boolean isSelected = ((Button)source).getSelection();
	        if (source == customPosButton) {
	            chart1.getLegend().setCustomPosition(isSelected);
	        }     	        
		} else if (source instanceof Slider) {                            
			int pos = ((Slider)source).getSelection();
            if (source == leftSlider) {
                chart1.getLegend().setLeft(pos);
            } else if (source == topSlider) {
                chart1.getLegend().setTop(pos);
            }    
		}
	}		
	
	protected void createContent() {
		super.createContent();
        customPosButton = addCheckButton("Custom position", "", this);
        addLabel(SWT.LEFT, "Left: ");
        leftSlider = addSlider(SWT.HORIZONTAL, 0, 0, 0);
        addLabel(SWT.LEFT, "Top: ");
        topSlider = addSlider(SWT.HORIZONTAL, 0, 0, 0);        
 	}
	
	protected void initContent() {
		super.initContent();
        customPosButton.setSelection(chart1.getLegend().getCustomPosition());		
	}
	
	protected void initChart() {
		super.initChart();
        chart1.getAspect().setView3D(false);
        chart1.getLegend().setCustomPosition(true);
        chart1.getLegend().setLeft(36);
        chart1.getLegend().setTop(20);
        
        Pie tmpSeries = new Pie(chart1.getChart());
        tmpSeries.fillSampleValues(7);        
	}	

    private Button customPosButton;
    private Slider leftSlider, topSlider;
}
