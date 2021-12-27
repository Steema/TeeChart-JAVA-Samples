/*
 * SkipMarksDemo.java
 *
 * <p>Copyright: Copyright (c) 2004-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.marks;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.styles.Line;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class SkipMarksDemo extends ChartSample implements ModifyListener {

    private Line series;
    
	public SkipMarksDemo(Composite c) {
		super(c);
	}

	public void modifyText(ModifyEvent me) {
		Widget source = me.widget;
		if (source == skipSpinner) {
			  series.getMarks().setDrawEvery(skipSpinner.getSelection());    		
		}
	}	
	
	protected void createContent() {
		super.createContent();
		addLabel(SWT.LEFT, "Draw every: ");
		skipSpinner = addSpinner(SWT.READ_ONLY| SWT.BORDER, 1, 1, 1, this);				
	}

	protected void initContent() {
		super.initContent();
		
		skipSpinner.setMaximum(series.getCount());
		skipSpinner.setSelection(series.getMarks().getDrawEvery());
	}

	protected void initChart() {
		super.initChart();        
		chart1.getLegend().setVisible(false);
		
        series = new Line(chart1.getChart());
        series.getMarks().setDrawEvery(10);
        series.getMarks().setArrowLength(8);
        series.getMarks().setVisible(true);
        series.getPointer().setVisible(false);
        series.fillSampleValues(50);		
	}   		
	
    private Spinner skipSpinner;	
}
