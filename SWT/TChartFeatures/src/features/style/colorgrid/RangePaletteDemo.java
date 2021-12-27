/*
 * ColorGridDemo.java
 *
 * <p>Copyright: (c) 2005-2008 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.style.colorgrid;

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
import com.steema.teechart.styles.ColorGrid;
import com.steema.teechart.styles.PaletteStyle;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class RangePaletteDemo extends ChartSample implements ModifyListener, SelectionListener {

    private ColorGrid series;
    //TODO private ButtonColor startColorButton, middleColorButton, endColorButton;
    
	public RangePaletteDemo(Composite c) {
		super(c);
	}
	
	public void modifyText(ModifyEvent me) {
		Widget source = me.widget;
		if (source == stepsSpinner) {
            int steps = stepsSpinner.getSelection();
            series.setPaletteSteps(steps);
            series.repaint();
		}
	}	
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;
		//TODO
		/*
        if (source == startColorButton) {
            series.setStartColor(new Color(startColorButton.getColor()));
        } else if (source == middleColorButton) {
            series.setMidColor(new Color(middleColorButton.getColor()));
        } else if (source == endColorButton) {
            series.setEndColor(new Color(endColorButton.getColor()));
        } else */ {
        	boolean isSelected = ((Button)source).getSelection();
            if (source == noStepsButton) {
                stepsSpinner.setEnabled(!isSelected);
                if (isSelected) {
                    series.setUsePalette(false);
                    series.setUseColorRange(true);
                } else {
                    series.setUsePalette(true);
                    series.setUseColorRange(false);
                }
            }        	
        }
	}	
	
    protected void createContent() {
    	super.createContent();
    	   	
    	addLabel(SWT.LEFT, "Steps: ");    	
    	stepsSpinner = new Spinner(getButtonPane(), SWT.READ_ONLY | SWT.BORDER);    	
    	
        noStepsButton = addCheckButton("No Steps", "", this);    	
    }
    
    protected void initContent() {
    	super.initContent();    	   	
        noStepsButton.setSelection(true);    	
    	stepsSpinner.setMaximum(250);
    	stepsSpinner.setMinimum(1);
    	stepsSpinner.setIncrement(1);
    	stepsSpinner.setSelection(series.getPaletteSteps());    	
    	stepsSpinner.setEnabled(!noStepsButton.getSelection());    	
    }
    
    protected void initChart() {
    	super.initChart();
        chart1.getAspect().setView3D(false);   
        
        series = new ColorGrid(chart1.getChart());
        series.fillSampleValues(100);
        series.setPaletteStyle(PaletteStyle.STRONG);
        series.setPaletteSteps(10);
        series.setMidColor(Color.TEAL);
        series.getPen().setVisible(false);
        
        //TODO
        /* set-up initial values for controls */
        /*
        startColorButton = new ButtonColor(series.getStartColor());
        middleColorButton = new ButtonColor(series.getMidColor());
        endColorButton = new ButtonColor(series.getEndColor());
        */
    }   			

    private Button noStepsButton;
    private Spinner stepsSpinner;    
}
