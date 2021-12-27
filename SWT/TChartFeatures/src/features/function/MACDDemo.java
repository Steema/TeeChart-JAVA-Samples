/*
 * MACDDemo.java
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

import com.steema.teechart.axis.Axis;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.functions.MACD;
import com.steema.teechart.styles.Candle;
import com.steema.teechart.styles.Line;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class MACDDemo extends ChartSample implements ModifyListener, SelectionListener {

    private Candle candleSeries;
    private MACD function;
    
	public MACDDemo(Composite c) {
		super(c);
		p1Spinner.addModifyListener(this);
		p2Spinner.addModifyListener(this);
		p3Spinner.addModifyListener(this);		
	}

	public void modifyText(ModifyEvent me) {
		Widget source = me.widget;
        if (source instanceof Spinner) {
            int period = ((Spinner)source).getSelection();
            if ((period > 0) && (period < 50)) {
                if (source == p1Spinner) {
                    function.setPeriod(period);
                }
                else if (source == p2Spinner) {
                	function.setPeriod2(period);
                }
                else if (source == p3Spinner) {
                	function.setPeriod3(period);
                }
            }
        }
	}	
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
		boolean isSelected = ((Button)source).getSelection();
        if (source == activeButton) {
        	function.getSeries().setActive(isSelected);
        	function.getMACDExp().setActive(isSelected);
            function.getHistogram().setActive(isSelected);
            chart1.getLegend().setVisible(isSelected);
            /* re-position the axis */
            if (function.getSeries().getActive()) {
               chart1.getAxes().getLeft().setEndPosition(60);
            } else {
                chart1.getAxes().getLeft().setEndPosition(100);
            }
        }
        else if (source == expButton) {
        	function.getMACDExp().setActive(isSelected);
        }
        else if (source == histogramButton) {
        	function.getHistogram().setActive(isSelected);
        }        
	}	

	protected void createContent() {
		super.createContent();	
		activeButton = addCheckButton("Show MACD", "", this);        
        expButton = addCheckButton("Show MACD Exp", "", this);        
        histogramButton = addCheckButton("Show histogram", "", this);
                
        addLabel(SWT.LEFT, "Period 1: ");
        p1Spinner = addSpinner(SWT.BORDER, 1, 60, 1); 
        
        addLabel(SWT.LEFT, "Period 2: ");
        p2Spinner = addSpinner(SWT.BORDER, 1, 60, 1); 
         
		addLabel(SWT.LEFT, "Period 3: ");
		p3Spinner = addSpinner(SWT.BORDER, 1, 60, 1);        
	}

	protected void initContent() {
		super.initContent();
		activeButton.setSelection(true);
		expButton.setSelection(true);
		histogramButton.setSelection(true);
		p1Spinner.setSelection(26);
		p2Spinner.setSelection(12);
		p3Spinner.setSelection(9);
	}

	protected void initChart() {
		super.initChart();
        chart1.getHeader().setVisible(true);
        chart1.setText("MACD Function Example");
        chart1.getAspect().setView3D(false);
        
        candleSeries = new com.steema.teechart.styles.Candle(chart1.getChart());
        candleSeries.getMarks().setVisible(false);
        candleSeries.getPointer().setVisible(true);
        candleSeries.setTitle("Candle");
        candleSeries.getVertAxis().setStartPosition(0);
        candleSeries.getVertAxis().setEndPosition(40);
        candleSeries.getVertAxis().getTitle().setCaption("Candle");
        candleSeries.fillSampleValues(50);

        function = new com.steema.teechart.functions.MACD(chart1.getChart());

        Axis tmpAxis;
        tmpAxis = chart1.getAxes().getCustom().getNew();
        tmpAxis.setHorizontal(false);
        tmpAxis.setOtherSide(false);
        tmpAxis.setStartPosition(60.0);
        tmpAxis.setEndPosition(100);
        tmpAxis.getTitle().setText("MACD");
        tmpAxis.getTitle().setAngle(90);
        
        Line functionSeries = new com.steema.teechart.styles.Line(chart1.getChart());
        functionSeries.setTitle("MACD");
        functionSeries.setColor(Color.GREEN);
        functionSeries.setCustomVertAxis(tmpAxis);
        functionSeries.setDataSource(candleSeries);
        functionSeries.setFunction(function);        
	}   	
		
    private Button activeButton, expButton, histogramButton;
    private Spinner p1Spinner, p2Spinner, p3Spinner;	
}
