/*
 * StackGroupDemo.java
 *
 * <p>Copyright: (c) 2005-2008 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.style.bar;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.legend.Legend;
import com.steema.teechart.styles.Bar;
import com.steema.teechart.styles.MultiBars;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class StackGroupDemo extends ChartSample implements ModifyListener {

	public StackGroupDemo(Composite c) {
		super(c);
		stackSpinner.addModifyListener(this);
	}

	public void modifyText(ModifyEvent me) {
		Widget source = me.widget;
		if (source == stackSpinner) {
			setStackGroup(stackSpinner.getSelection());	        		
		}
	}

	protected void createContent() {
		super.createContent();
    	addLabel(SWT.LEFT, "First stack has: ");
		stackSpinner = new Spinner(getButtonPane(), SWT.READ_ONLY);		
    	addLabel(SWT.LEFT, " series");		
	}

	protected void initContent() {
		super.initContent();
		stackSpinner.setMaximum(chart1.getSeriesCount());
		stackSpinner.setMinimum(1);
		stackSpinner.setIncrement(1);
		stackSpinner.setSelection(3);
		setStackGroup(3);
	}

	protected void initChart() {
		super.initChart();
		chart1.getAspect().setView3D(false);
        Legend tmpLegend = chart1.getLegend();
        tmpLegend.setCheckBoxes(true);
        tmpLegend.getFont().setBold(true);
        tmpLegend.setFontSeriesColor(true);
        tmpLegend.getGradient().setEndColor(Color.SILVER);
        tmpLegend.getGradient().setMiddleColor(Color.SILVER);
        tmpLegend.getGradient().setStartColor(Color.WHITE);
        tmpLegend.getGradient().setVisible(true);
        
        Bar barSeries = null;
        for (int i=0; i < 5; i++) {
            barSeries = new com.steema.teechart.styles.Bar(chart1.getChart());
            barSeries.setMultiBar(MultiBars.STACKED);
            barSeries.getMarks().setVisible(false);
            barSeries.fillSampleValues(6);
        }
	}   		

    private void setStackGroup(int series) {
        for(int i=0; i < series; i++) {
            ((Bar)chart1.getSeries(i)).setStackGroup(0);
        };
        for(int i=series; i < chart1.getSeriesCount(); i++) {
            ((Bar)chart1.getSeries(i)).setStackGroup(1);
        };
    }	

	private Spinner stackSpinner;	
}
