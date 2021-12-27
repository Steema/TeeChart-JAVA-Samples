/*
 * OriginDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.style.area;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.styles.Area;
import com.steema.teechart.styles.CustomStack;
import com.steema.teechart.styles.ValueList;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class OriginDemo extends ChartSample implements ModifyListener, SelectionListener {

    private Area areaSeries;

    public OriginDemo(Composite c) {
		super(c);
        originSpinner.addModifyListener(this);		
	}

	public void modifyText(ModifyEvent me) {
		Widget source = me.widget;
		if (source == originSpinner) {
			refreshOrigin();    		
		}
	}	
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
		if (source instanceof Button) {
			boolean isSelected = ((Button)source).getSelection();
	        if (source == originButton) {
	            areaSeries.setUseOrigin(isSelected);
	        }				
		}
	}
	
	protected void createContent() {
		super.createContent();
        originButton = addCheckButton("Use Origin: ", "", this);
		addLabel(SWT.LEFT, "Origin: ");
		originSpinner = new Spinner(getButtonPane(), SWT.READ_ONLY);				
	}

	protected void initContent() {
		super.initContent();
        ValueList tmpValues = areaSeries.getYValues();
        int tmpMiddle = (int)Math.round((tmpValues.getMaximum() + tmpValues.getMinimum()) / 2.0);	
		originSpinner.setMaximum(Integer.MAX_VALUE);
		originSpinner.setMinimum(Integer.MIN_VALUE);
		originSpinner.setIncrement(1);
		originSpinner.setSelection(tmpMiddle);
        originButton.setSelection(areaSeries.getUseOrigin());

        refreshOrigin();
	}

	protected void initChart() {
		super.initChart();
        chart1.getAspect().setView3D(false);
        chart1.getLegend().setVisible(false);
        areaSeries = new Area(chart1.getChart());
        areaSeries.getMarks().setVisible(false);
        areaSeries.fillSampleValues(20);
        areaSeries.setStacked(CustomStack.NONE);
        areaSeries.setUseOrigin(false);
	}   			
	
    private void refreshOrigin() {
        int origin = originSpinner.getSelection();
        areaSeries.setOrigin(origin);
    }
    
    private Button originButton;
    private Spinner originSpinner;			
}
