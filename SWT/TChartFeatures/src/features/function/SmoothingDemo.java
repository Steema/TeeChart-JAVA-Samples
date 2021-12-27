/*
 * SmoothingDemo.java
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
import com.steema.teechart.functions.Smoothing;
import com.steema.teechart.styles.Line;
import com.steema.teechart.styles.PointerStyle;
import com.steema.teechart.styles.SeriesPointer;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class SmoothingDemo extends ChartSample implements ModifyListener, SelectionListener {

    private Line sourceSeries;
    private Smoothing smoothingFunction;
    
	public SmoothingDemo(Composite c) {
		super(c);
		factorSpinner.addModifyListener(this);
	}
	
	public void modifyText(ModifyEvent me) {
		Widget source = me.widget;
		if (source == factorSpinner) {
            int factor = ((Spinner)source).getSelection();
            if ((factor > 0) && (factor < 101)) {
                if (source == factorSpinner) {
                    smoothingFunction.setFactor(factor);
                }
            }   		
		}
	}	
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
        if (source == randomButton) {
            sourceSeries.fillSampleValues();
        } else {
            boolean isSelected = ((Button)source).getSelection();
            if (source == viewPointsButton) {
                sourceSeries.getPointer().setVisible(isSelected);
            } else if (source == viewSourceButton) {
                sourceSeries.setVisible(isSelected);
            } else if (source == interpolateButton) {
                smoothingFunction.setInterpolate(isSelected);
            }        	
        }
	}

	protected void createContent() {
		super.createContent();	
        viewPointsButton = addCheckButton("View points", "", this);
        viewSourceButton = addCheckButton("View source", "", this);
        interpolateButton = addCheckButton("Interpolate", "", this);

        factorSpinner = addSpinner(SWT.BORDER, 1, 100, 1);

        randomButton = addPushButton("Random!", "", this);
	}

	protected void initContent() {
		super.initContent();
        viewPointsButton.setSelection(true);
        viewSourceButton.setSelection(true);
        interpolateButton.setSelection(false);        
        factorSpinner.setSelection(4);
	}

	protected void initChart() {
		super.initChart();
        chart1.getHeader().setVisible(true);
        chart1.setText("Smoothing using Splines");
        chart1.getAspect().setView3D(false);
        
        sourceSeries = new com.steema.teechart.styles.Line(chart1.getChart());
        sourceSeries.getMarks().setVisible(false);
        sourceSeries.setTitle("Source");
        sourceSeries.setColor(Color.RED);
        sourceSeries.getLinePen().setColor(Color.RED);
        sourceSeries.getLinePen().setWidth(2);
        sourceSeries.fillSampleValues(10);

        SeriesPointer tmpPointer = sourceSeries.getPointer();
        tmpPointer.setInflateMargins(true);
        tmpPointer.getBrush().setColor(Color.YELLOW);
        tmpPointer.getPen().setColor(Color.OLIVE);
        tmpPointer.setStyle(PointerStyle.TRIANGLE);
        tmpPointer.setVisible(true);


        smoothingFunction = new com.steema.teechart.functions.Smoothing(chart1.getChart());
        smoothingFunction.setPeriod(1);
        smoothingFunction.setInterpolate(false);
        smoothingFunction.setFactor(4);

        Line functionSeries = new com.steema.teechart.styles.Line(chart1.getChart());
        functionSeries.setDataSource(sourceSeries);
        functionSeries.setFunction(smoothingFunction);
        functionSeries.setTitle("Smooth");
        functionSeries.setColor(Color.GREEN);
        functionSeries.getMarks().setVisible(false);
        functionSeries.getPointer().setVisible(false);
	}   	

    private Button randomButton;
    private Button viewPointsButton, viewSourceButton, interpolateButton;
    private Spinner factorSpinner;
}
