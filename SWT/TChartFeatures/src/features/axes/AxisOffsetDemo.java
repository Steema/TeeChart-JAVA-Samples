/*
 * AxisOffsetDemo.java
 *
 * <p>Copyright: (c) 2005-2008 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.axes;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.axis.Axis;
import com.steema.teechart.styles.FastLine;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class AxisOffsetDemo extends ChartSample implements ModifyListener, SelectionListener {

	public AxisOffsetDemo(Composite c) {
		super(c);
	}

	public void modifyText(ModifyEvent me) {
		Widget source = me.widget;
        if ((source == minSpinner) || (source == maxSpinner)) {
            Axis axis;
            switch (axisList.getSelectionIndex()) {
                case 1: {axis = chart1.getAxes().getBottom(); break; }
                default: {axis = chart1.getAxes().getLeft(); }
            }
            int offset = ((Spinner)source).getSelection();
            if (source == minSpinner) {
                axis.setMinimumOffset(offset);
            } else {
                axis.setMaximumOffset(offset);
            }
        }
	}	
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
        if (source == axisList) {
            Axis axis;
            switch (axisList.getSelectionIndex()) {
                case 1: {axis = chart1.getAxes().getBottom(); break; }
                default: {axis = chart1.getAxes().getLeft(); }
            }
            minSpinner.setSelection(axis.getMinimumOffset());
            maxSpinner.setSelection(axis.getMaximumOffset());
        }
	}
	
	protected void createContent() {
		super.createContent();
		addLabel(SWT.LEFT, "Axis: ");
		axisList = addCombo(SWT.SINGLE | SWT.READ_ONLY | SWT.BORDER, this);
		
		addLabel(SWT.LEFT, "Min offset: ");
		minSpinner = addSpinner(SWT.READ_ONLY | SWT.BORDER, -400, +400, 1, this);	
		
		addLabel(SWT.LEFT, "Max offset: ");		
		maxSpinner = addSpinner(SWT.READ_ONLY | SWT.BORDER, -400, +400, 1, this);		
	}

	protected void initContent() {
		super.initContent();
		axisList.setItems(new String[]{"Left", "Bottom"});
        axisList.select(0);
        minSpinner.setSelection(4);
        maxSpinner.setSelection(4);
	}

	protected void initChart() {
		super.initChart();
        chart1.getAspect().setView3D(false);
        chart1.getLegend().setVisible(false);
        Axis axis;
        axis = chart1.getAxes().getBottom();
        axis.setMaximumOffset(4);
        axis.setMinimumOffset(4);
        axis = chart1.getAxes().getLeft();
        axis.setMaximumOffset(4);
        axis.setMinimumOffset(4);
        
        FastLine series = new FastLine(chart1.getChart());
        series.fillSampleValues(20);
        series.getLinePen().setWidth(3);        
	}   				
	
    private Combo axisList;
    private Spinner minSpinner, maxSpinner;	
}
