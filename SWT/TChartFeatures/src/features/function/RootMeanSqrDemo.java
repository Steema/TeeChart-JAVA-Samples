/*
 * RootMeanSqrDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.function;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.functions.RootMeanSquare;
import com.steema.teechart.styles.CustomStack;
import com.steema.teechart.styles.Line;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class RootMeanSqrDemo extends ChartSample implements SelectionListener {

    private Line sourceSeries, rmsSeries;
    private RootMeanSquare rmsFunction;
    
	public RootMeanSqrDemo(Composite c) {
		super(c);
	}

	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
        if (source == completeButton) {
        	boolean isSelected = ((Button)source).getSelection();
            rmsFunction.setComplete(isSelected);
            displayCalculation();
        }
	}

	protected void createContent() {
		super.createContent();	
		addLabel(SWT.LEFT, "Root Mean Square value: ");
        calcValueLabel = addLabel(SWT.LEFT, "");
        completeButton = addCheckButton("Complete", "", this);
	}

	protected void initContent() {
		super.initContent();
        completeButton.setSelection(false);
        displayCalculation();	 
	}

	protected void initChart() {
		super.initChart();
        sourceSeries = new com.steema.teechart.styles.Line(chart1.getChart());
        sourceSeries.setTitle("Source");
        sourceSeries.getMarks().setVisible(false);
        sourceSeries.getPointer().setVisible(false);
        sourceSeries.setStacked(CustomStack.NONE);
        sourceSeries.fillSampleValues(20);

        rmsSeries = new com.steema.teechart.styles.Line(chart1.getChart());
        rmsSeries.setTitle("Root Mean Sq.");
        rmsSeries.getMarks().setVisible(false);
        rmsSeries.getPointer().setVisible(true);
        rmsSeries.setStacked(CustomStack.NONE);

        rmsFunction = new com.steema.teechart.functions.RootMeanSquare(chart1.getChart());
        rmsFunction.setComplete(false);

        rmsSeries.setDataSource(sourceSeries);
        rmsSeries.setFunction(rmsFunction); 
	}	

    private void displayCalculation() {
        calcValueLabel.setText(new Double(rmsSeries.getYValues().getValue(0)).toString());
    }

    private Button completeButton;
    private Label calcValueLabel;	
}
