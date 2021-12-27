/*
 * DepthDemo.java
 *
 * <p>Copyright: (c) 2005-2008 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.style;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.styles.Bar;
import com.steema.teechart.styles.Line;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class DepthDemo extends ChartSample implements ModifyListener, SelectionListener {

    private Line series1, series3;
    private Bar series2;
	
	public DepthDemo(Composite c) {
		super(c);
		depthSpinner.addModifyListener(this);
	}

	public void modifyText(ModifyEvent me) {
		Widget source = me.widget;
		if (source == depthSpinner) {
            int depth = depthSpinner.getSelection();
            autoDepthButton.setSelection(false);
            series1.setDepth(depth);
            series2.setDepth(depth);
            series3.setDepth(depth);
		}
	}	
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;	
        boolean isSelected = ((Button)source).getSelection();
        if (source == autoDepthButton) {
            int depth = -1;
            if (!isSelected) {
                depth = depthSpinner.getSelection();
            }
            series1.setDepth(depth);
            series2.setDepth(depth);
            series3.setDepth(depth);
        }
	}	
	
    protected void createContent() {
    	super.createContent();
    	   	
    	addLabel(SWT.LEFT, "Series depth: ");    	
    	depthSpinner = new Spinner(getButtonPane(), SWT.READ_ONLY | SWT.BORDER);    	
    	
    	autoDepthButton = addCheckButton("Automatic depth", "", this);    	
    }
    
    protected void initContent() {
    	super.initContent();    	   	
    	depthSpinner.setMaximum(Integer.MAX_VALUE);
    	depthSpinner.setMinimum(0);
    	depthSpinner.setIncrement(1);
    	depthSpinner.setSelection(5);    	
    	autoDepthButton.setSelection(chart1.getAspect().getView3D());
    }
    
    protected void initChart() {
    	super.initChart();
        chart1.getAspect().setOrthogonal(false);
        chart1.getAspect().setElevation(295);
        chart1.getAspect().setPerspective(45);
        chart1.getAspect().setChart3DPercent(25);    
        
        series1 = new Line(chart1.getChart());
        series1.fillSampleValues(10);

        series2 = new Bar(chart1.getChart());
        series2.fillSampleValues(6);

        series3 = new Line(chart1.getChart());
        series3.fillSampleValues(10);

        for (int t=0; t < chart1.getSeriesCount(); t++) {
            chart1.getSeries(t).setDepth(5);
        }        
    }   		
    
    private Button autoDepthButton;
    private Spinner depthSpinner;    
}
