/*
 * VertSpacingDemo.java
 *
 * <p>Copyright: Copyright (c) 2004-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.legend;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.styles.Bar3D;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class VertSpacingDemo extends ChartSample implements ModifyListener {

	public VertSpacingDemo(Composite c) {
		super(c);
	}
	
	public void modifyText(ModifyEvent me) {
		Widget source = me.widget;
		if (source == spaceSpinner) {
			chart1.getLegend().setVertSpacing(spaceSpinner.getSelection());    		
		}
	}
	
	protected void createContent() {
		super.createContent();	
		addLabel(SWT.LEFT, "Vertical spacing: ");
        spaceSpinner = addSpinner(SWT.HORIZONTAL, 0, 30, 1, this);
	}

	protected void initContent() {
		super.initContent();
        spaceSpinner.setSelection(chart1.getLegend().getVertSpacing());
	}

	protected void initChart() {
		super.initChart();
        new Bar3D(chart1.getChart()).fillSampleValues(5);    
	}	
	
    private Spinner spaceSpinner;
}
