/*
 * DonutDemo.java
 *
 * <p>Copyright: (c) 2005-2008 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.donut;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.styles.Donut;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class DonutDemo extends ChartSample implements ModifyListener, SelectionListener {

    private Donut series;

	public DonutDemo(Composite c) {
		super(c);
		holeSpinner.addModifyListener(this);
	}
	
	public void modifyText(ModifyEvent me) {
		Widget source = me.widget;
		if (source == holeSpinner) {
            int hole = holeSpinner.getSelection();
            series.setDonutPercent(hole);  		
		}
	}	
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;	
        if (source instanceof Button) {
            boolean isSelected = ((Button)source).getSelection();
            if (source == view3DButton) {
                chart1.getAspect().setView3D(isSelected);
            }
        }
	}	
	
    protected void createContent() {
    	super.createContent();
    	   	
    	addLabel(SWT.LEFT, "Hole %: ");

    	holeSpinner = new Spinner(getButtonPane(), SWT.READ_ONLY);    	
    	
    	view3DButton = addCheckButton("3D", "", this);
    }
    
    protected void initContent() {
    	super.initContent();
    	
    	holeSpinner.setMaximum(100);
    	holeSpinner.setMinimum(0);
    	holeSpinner.setIncrement(5);
    	holeSpinner.setSelection(50);
    	view3DButton.setSelection(true);    	
    }
    
    protected void initChart() {
    	super.initChart();
        chart1.getAspect().setElevation(315);
        chart1.getAspect().setOrthogonal(false);
        chart1.getAspect().setPerspective(0);
        chart1.getAspect().setRotation(360);
        
        series = new com.steema.teechart.styles.Donut(chart1.getChart());
        series.fillSampleValues(8);
        series.setDonutPercent(50);
        series.setCircled(true);        
    }   	
	
    private Button view3DButton;
    private Spinner holeSpinner;
}
