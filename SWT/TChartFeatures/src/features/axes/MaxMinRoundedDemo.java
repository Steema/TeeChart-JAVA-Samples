/*
 * MaxMinRoundedDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.axes;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.axis.Axis;
import com.steema.teechart.styles.FastLine;
import com.steema.teechart.styles.HorizontalAxis;
import com.steema.teechart.styles.VerticalAxis;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class MaxMinRoundedDemo extends ChartSample implements SelectionListener {

	public MaxMinRoundedDemo(Composite c) {
		super(c);
	}

	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
		if (source == chooseAxis){
			maxButton.setSelection(getSelectedAxis().getMaxRound());        
			minButton.setSelection(getSelectedAxis().getMinRound());
		} else {
	        boolean  isSelected = ((Button)source).getSelection();
	        if (maxButton == source) {
	            getSelectedAxis().setMaxRound(isSelected);
	        } else if (minButton == source) {
	            getSelectedAxis().setMinRound(isSelected);
	        }			
		}
	}

	protected void createContent() {
		super.createContent();
		addLabel(SWT.LEFT, "Axis: ");
		chooseAxis = addCombo(SWT.SINGLE | SWT.READ_ONLY | SWT.BORDER, this);
		minButton = addCheckButton("Min rounded", "", this);	
		maxButton = addCheckButton("Max rounded", "", this);		
	}

	protected void initContent() {
		super.initContent();
		getButtonPane().setVisible(false);
		chooseAxis.add("Vertical");
		chooseAxis.add("Horizontal"); 		
		chooseAxis.select(0);
		maxButton.setSelection(getSelectedAxis().getMaxRound());        
		minButton.setSelection(getSelectedAxis().getMinRound());		
	}

	protected void initChart() {
		super.initChart();
        chart1.getAspect().setView3D(false);        
        chart1.getAxes().getTop().setVisible(false);
        chart1.getAxes().getRight().setVisible(false);
        chart1.getAxes().getLeft().setMaxRound(true);
        chart1.getAxes().getLeft().setMinRound(true);        
        chart1.getAxes().getLeft().setIncrement(50);
        chart1.getAxes().getBottom().setIncrement(10);
        
        FastLine fastLine = new FastLine(chart1.getChart());
        fastLine.fillSampleValues(20);
        fastLine.setHorizontalAxis(HorizontalAxis.BOTH);
        fastLine.setVerticalAxis(VerticalAxis.BOTH);
        fastLine.getMarks().setVisible(true);        
	}   		

	private Axis getSelectedAxis() {
		if (chooseAxis.getSelectionIndex() == 0) {
			return chart1.getAxes().getLeft();
		} 
		else {
			return chart1.getAxes().getBottom();
		}
	}
	
	private Combo chooseAxis;
	private Button minButton, maxButton; 	
}
