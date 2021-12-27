/*
 * BollingerDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.function;

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
import com.steema.teechart.functions.Bollinger;
import com.steema.teechart.styles.Candle;
import com.steema.teechart.styles.FastLine;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class BollingerDemo extends ChartSample implements ModifyListener, SelectionListener {

    private Candle sourceSeries;
    private Bollinger function;
    private FastLine functionSeries;

	public BollingerDemo(Composite c) {
		super(c);
        periodSpinner.addModifyListener(this);
        devSpinner.addModifyListener(this);
	}

	public void modifyText(ModifyEvent me) {
		Widget source = me.widget;
        if (source instanceof Spinner) {
            int val = ((Spinner)source).getSelection();
            if ((val > 0) && (val < 51)) {
                if (source == periodSpinner) {
                    function.setPeriod(val);
                }
                else if (source==devSpinner) {
                    function.setDeviation(val);
                }
            }
        }
	}	
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
        if (source == exponentialButton) {
        	boolean isSelected = ((Button)source).getSelection();
            function.setExponential(isSelected);
        }
	}	

	protected void createContent() {
		super.createContent();	
		addLabel(SWT.LEFT, "Period: ");
        periodSpinner = addSpinner(SWT.BORDER, 1, 50, 1); 
        
		addLabel(SWT.LEFT, "Deviation: ");
		devSpinner = addSpinner(SWT.BORDER, 1, 10, 1); 
         
		exponentialButton = addCheckButton("Exponential", "", this);        
	}

	protected void initContent() {
		super.initContent();
		exponentialButton.setSelection(true);
		periodSpinner.setSelection(10);
		devSpinner.setSelection(2);
	}

	protected void initChart() {
		super.initChart();
        chart1.getHeader().setVisible(true);
        chart1.setText("Bollinger Bands");
        chart1.getAspect().setView3D(false);		
        chart1.getLegend().setVisible(true);
        
        sourceSeries = new Candle(chart1.getChart());
        sourceSeries.setTitle("Source");
        sourceSeries.setColor(Color.RED);
        sourceSeries.fillSampleValues(100);

        function = new Bollinger(chart1.getChart());
        function.setDeviation(2.0);

        functionSeries = new FastLine(chart1.getChart());
        functionSeries.setTitle("Bollinger");
        functionSeries.setDataSource(sourceSeries);
        functionSeries.setFunction(function);
	}   	
	
    private Button exponentialButton;
    private Spinner devSpinner, periodSpinner;	
}
