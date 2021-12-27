/*
 * GradientDemo.java
 *
 * <p>Copyright: (c) 2005-2008 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.style.bubble;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.GradientDirection;
import com.steema.teechart.styles.Bubble;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class GradientDemo extends ChartSample implements SelectionListener {

    private Bubble bubbleSeries;

	public GradientDemo(Composite c) {
		super(c);
	}

	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
        if (source == adjustMarginsButton) {
            int tmp;
            tmp = bubbleSeries.getVertAxis().calcSizeValue(bubbleSeries.getRadiusValues().getFirst());
            bubbleSeries.getHorizAxis().setMinimumOffset(tmp);
            tmp = bubbleSeries.getVertAxis().calcSizeValue(bubbleSeries.getRadiusValues().getLast());
            bubbleSeries.getHorizAxis().setMaximumOffset(tmp);
        } else if (source == editGradientButton) {
            //TODO DialogFactory.showModal(bubbleSeries.getPointer().getGradient());
        } else {
            boolean isSelected = ((Button)source).getSelection();
            if (source == useGradientButton) {
                bubbleSeries.getPointer().getGradient().setVisible(isSelected);
            }      	
        }
	}

	protected void createContent() {
		super.createContent();		
		useGradientButton = addCheckButton("Gradient", "", this);	
		editGradientButton = addPushButton("Edit Gradient...", "", this);
		adjustMarginsButton = addPushButton("Adjust margins", "", this);		
	}

	protected void initContent() {
		super.initContent();
        useGradientButton.setSelection(true);
	}

	protected void initChart() {
		super.initChart();
        chart1.getAspect().setView3D(false);
        bubbleSeries = new com.steema.teechart.styles.Bubble(chart1.getChart());
        bubbleSeries.fillSampleValues();
        bubbleSeries.setColorEach(true);
        bubbleSeries.getPointer().getGradient().setVisible(true);
        bubbleSeries.getPointer().getGradient().setDirection(GradientDirection.HORIZONTAL);
        bubbleSeries.getColors().setColor(4, Color.AQUA);
    }

    private Button useGradientButton;    
    private Button editGradientButton, adjustMarginsButton;
}
