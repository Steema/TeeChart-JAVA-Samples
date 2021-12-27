/*
 * SmoothedDemo.java
 *
 * <p>Copyright: (c) 2005-2008 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.style.line;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.Custom;
import com.steema.teechart.styles.PointerStyle;
import com.steema.teechart.styles.SeriesPointer;

import features.ChartSample;

/**
*
* @author Yeray
*/
public class SmoothedDemo extends ChartSample implements SelectionListener {
	    
	/** Creates a new instance of SmoothedDemo */
	public SmoothedDemo(Composite c) {
		super(c);			
	}
	
	public void widgetDefaultSelected(SelectionEvent se) { }

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;
		if (source==clickableLineButton) {
			lineSeries.setSmoothed(clickableLineButton.getSelection());
		}
		else if (source==lineButton) {
            chart1.getAspect().setView3D(false);
            chart1.getSeries().remove(lineSeries);
            lineSeries.setChart((com.steema.teechart.Chart)null);
            chart1.getSeries().add(lineSeries = new com.steema.teechart.styles.Line());
            lineSeries.getPointer().setStyle(com.steema.teechart.styles.PointerStyle.CIRCLE);
            lineSeries.getPointer().setVisible(true);
            lineSeries.setColor(Color.fromArgb(128, 128, 128));
            lineSeries.fillSampleValues(8);
            lineSeries.setSmoothed(clickableLineButton.getSelection());
		}
		else if (source==areaButton) {
            chart1.getAspect().setView3D(true);
            chart1.getSeries().remove(lineSeries);
            lineSeries.setChart((com.steema.teechart.Chart)null);
            chart1.getSeries().add(lineSeries = new com.steema.teechart.styles.Area());
            lineSeries.setColor(Color.fromArgb(128, 128, 128));
            lineSeries.fillSampleValues(8);
            lineSeries.setSmoothed(clickableLineButton.getSelection());  
		}
	}	
		
    protected void createContent() {
    	super.createContent();
    	
        clickableLineButton = addCheckButton("Smoothed", "", this);
        lineButton = addRadioButton("Line", "", this);    	
        areaButton = addRadioButton("Area", "", this);		
    }
    
    protected void initContent() {
    	super.initContent();

    	lineButton.setSelection(true);	
    }
    
    protected void initChart() {
    	super.initChart();

    	chart1.getAspect().setView3D(false);
        chart1.getLegend().setVisible(false);
        lineSeries = new com.steema.teechart.styles.Line(chart1.getChart());

        SeriesPointer tmpPointer = lineSeries.getPointer();
        tmpPointer.setInflateMargins(true);
        tmpPointer.setStyle(PointerStyle.CIRCLE);
        tmpPointer.setVisible(true);
        lineSeries.setColor(Color.fromArgb(128, 128, 128));
        lineSeries.fillSampleValues(8);
    }
    
    private Custom lineSeries;
    private Button clickableLineButton, lineButton, areaButton;
}
