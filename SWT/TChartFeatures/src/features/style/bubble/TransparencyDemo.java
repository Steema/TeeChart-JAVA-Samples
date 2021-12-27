/*
 * TransparencyDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.style.bubble;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Slider;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.drawing.GradientDirection;
import com.steema.teechart.styles.Bubble;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class TransparencyDemo extends ChartSample implements SelectionListener {

    private Bubble bubbleSeries;
    
	public TransparencyDemo(Composite c) {
		super(c);
		transparencySlider.addSelectionListener(this);
	}

	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;
        if (source instanceof Slider) {
            if (transparencySlider == source) {
                transparencyLabel.setText(String.valueOf(transparencySlider.getSelection()+"%"));
                bubbleSeries.getPointer().setTransparency(transparencySlider.getSelection());
                bubbleSeries.repaint();
            }        	
        }
	}		
	
	protected void createContent() {
		super.createContent();
        transparencySlider = addSlider(SWT.HORIZONTAL, 0, 100, 50);
        transparencyLabel = addLabel(SWT.LEFT, String.valueOf(transparencySlider.getSelection()+"%"));
 	}
	
	protected void initContent() {
		super.initContent();	
	}
	
	protected void initChart() {
		super.initChart();
        chart1.getAspect().setView3D(false);	
        bubbleSeries = new com.steema.teechart.styles.Bubble(chart1.getChart());
        // transparency:
        //bubbleSeries.getPointer().getGradient().setTransparency(50);
        bubbleSeries.getPointer().setTransparency(50);

        // cosmetic gradient:
        bubbleSeries.getPointer().getGradient().setVisible(true);
        bubbleSeries.getPointer().getGradient().setDirection(GradientDirection.RADIAL);


        bubbleSeries.fillSampleValues();

        // Now, let's adjust horizontal axis to fit all bubbles...
        int tmp;
        tmp = bubbleSeries.getVertAxis().calcSizeValue(bubbleSeries.getRadiusValues().getFirst());
        bubbleSeries.getHorizAxis().setMinimumOffset(tmp);
        tmp = bubbleSeries.getVertAxis().calcSizeValue(bubbleSeries.getRadiusValues().getLast());
        bubbleSeries.getHorizAxis().setMaximumOffset(tmp);
	}   		

    private Label transparencyLabel;	
    private Slider transparencySlider; 	
}
