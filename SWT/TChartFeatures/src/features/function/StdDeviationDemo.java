/*
 * StdDeviationDemo.java
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
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.functions.StdDeviation;
import com.steema.teechart.styles.Line;
import com.steema.teechart.styles.VerticalAxis;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class StdDeviationDemo extends ChartSample implements SelectionListener {

    private Line sourceSeries;
    private StdDeviation deviationFunction;
    
	public StdDeviationDemo(Composite c) {
		super(c);
		styleList.addSelectionListener(this);
	}

	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
        if (source == styleList) {
            deviationFunction.setComplete(styleList.getSelectionIndex() != 0);
        } else if (source == randomButton) {
            sourceSeries.fillSampleValues(30);
        }
	}

	protected void createContent() {
		super.createContent();	
		addLabel(SWT.LEFT, "Style: ");
		styleList = addCombo(SWT.READ_ONLY | SWT.SINGLE | SWT.BORDER);        
        randomButton = addPushButton("New random values", "", this);   		
	}

	protected void initContent() {
		super.initContent();
		styleList.setItems(styleStrings);
		styleList.select(0);
	}

	protected void initChart() {
		super.initChart();
        chart1.getHeader().setVisible(true);
        chart1.setText("Standard Deviation Function");
        
        sourceSeries = new com.steema.teechart.styles.Line(chart1.getChart());
        sourceSeries.getMarks().setVisible(false);
        sourceSeries.getPointer().setVisible(false);
        sourceSeries.setTitle("Source");
        sourceSeries.setColor(Color.RED);
        sourceSeries.fillSampleValues(30);

        deviationFunction = new com.steema.teechart.functions.StdDeviation(chart1.getChart());
        deviationFunction.setPeriod(0);

        Line functionSeries = new com.steema.teechart.styles.Line(chart1.getChart());
        functionSeries.setDataSource(sourceSeries);
        functionSeries.setFunction(deviationFunction);
        functionSeries.setTitle("Std. Deviation");
        functionSeries.setColor(Color.GREEN);
        functionSeries.getMarks().setVisible(false);
        functionSeries.getPointer().setVisible(false);
        functionSeries.setVerticalAxis(VerticalAxis.RIGHT);   
	}   	

    private Button randomButton;
    private Combo styleList;

    private static final String[] styleStrings = { "Standard", "Complete" };	
}
