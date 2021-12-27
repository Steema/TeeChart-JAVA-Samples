/*
 * OriginDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.style.volume;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.ValueList;
import com.steema.teechart.styles.Volume;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class OriginDemo extends ChartSample implements ModifyListener, SelectionListener {

    private Volume volumeSeries;
    
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
	        	volumeSeries.setUseOrigin(isSelected);
	        }				
		}
	}
	
	protected void createContent() {
		super.createContent();
        originButton = addCheckButton("Use Y Origin: ", "", this);
		addLabel(SWT.LEFT, "Origin: ");
		originSpinner = new Spinner(getButtonPane(), SWT.READ_ONLY | SWT.BORDER);				
	}

	protected void initContent() {
		super.initContent();
        ValueList tmpValues = volumeSeries.getYValues();
        int tmpMiddle = (int)Math.round((tmpValues.getMaximum() + tmpValues.getMinimum()) / 2.0);	
		originSpinner.setMaximum(Integer.MAX_VALUE);
		originSpinner.setMinimum(Integer.MIN_VALUE);
		originSpinner.setIncrement(1);
		originSpinner.setSelection(tmpMiddle);
        originButton.setSelection(volumeSeries.getUseOrigin());

        refreshOrigin();
	}

	protected void initChart() {
		super.initChart();
        chart1.getHeader().setVisible(true);
        chart1.setText("Volume Series -- Using an Y origin value");
        chart1.getLegend().setVisible(false);
        chart1.getAspect().setView3D(false);
        volumeSeries = new Volume(chart1.getChart());
        volumeSeries.getMarks().setVisible(false);
        volumeSeries.setColor(Color.RED);
        volumeSeries.fillSampleValues(50);
        volumeSeries.setUseOrigin(true);
	}   			
	
    private void refreshOrigin() {
        int origin = originSpinner.getSelection();
        volumeSeries.setOrigin(origin);
    }
    
    private Button originButton;
    private Spinner originSpinner;		
}
