/*
 * GradientDemo.java
 *
 * <p>Copyright: (c) 2005-2008 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.style.bar;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.GradientDirection;
import com.steema.teechart.styles.Bar;
import com.steema.teechart.styles.BarStyle;

import features.ChartSample;
import features.WidgetFactory;

/**
 * @author tom
 *
 */
public class GradientDemo extends ChartSample implements SelectionListener {

    private Bar barSeries;
	
	public GradientDemo(Composite c) {
		super(c);
		showGradientEditorButton.addSelectionListener(this);
	}

	public void widgetDefaultSelected(SelectionEvent se) { }

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;
		if (source instanceof Button) {
			//TODO GradientEditor.edit(this, barSeries.getGradient(),true,true);			
		}
	}
	
    protected void createContent() {
    	super.createContent();    

    	showGradientEditorButton = WidgetFactory.createPushButton(
            		getButtonPane(), 
            		"Edit gradient...", 
            		"", 
            		this);
    }
    
    protected void initContent() {
    	super.initContent();
    	showGradientEditorButton.setEnabled(false);
    }    
    
    protected void initChart() {
    	super.initChart();
        chart1.getAspect().setView3D(false);
        barSeries = new Bar(chart1.getChart());
        barSeries.getMarks().setVisible(true);
        barSeries.fillSampleValues(6);
        barSeries.setColor(Color.RED);
        barSeries.setBarStyle(BarStyle.RECTGRADIENT);
        barSeries.getGradient().setDirection(GradientDirection.VERTICAL);
        barSeries.getGradient().setStartColor(Color.GREEN);
        barSeries.getGradient().setUseMiddle(false);
        barSeries.getGradient().setMiddleColor(Color.YELLOW);        
    }	

    private Button showGradientEditorButton;
}
