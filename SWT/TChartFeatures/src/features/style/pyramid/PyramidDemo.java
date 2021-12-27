/*
 * TernaryDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.style.pyramid;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.styles.Pyramid;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class PyramidDemo extends ChartSample implements ModifyListener, SelectionListener {

    private Pyramid series;
    
	public PyramidDemo(Composite c) {
		super(c);
		sizeSpinner.addModifyListener(this);
	}

	public void modifyText(ModifyEvent me) {
		Widget source = me.widget;
		if (source == sizeSpinner) {
            int size = sizeSpinner.getSelection();
            series.setSizePercent(size);	
		}
	}	
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;	
        if (source instanceof Button) {        	
            boolean isSelected = ((Button)source).getSelection();
            if (source == editButton) {
                //TODO ChartEditor.editSeries(series);
            } else if (source == colorEachButton) {
                series.setColorEach(isSelected);
            } else if (source == invertedButton) {
                chart1.getAxes().getLeft().setInverted(isSelected);
            } else if (source == nullPointsButton) {
                if (isSelected) {
                    series.setNull(2);
                    series.setNull(5);
                } else {
                    series.getColors().setColor(2, series.getColor());
                    series.getColors().setColor(5, series.getColor());
                }
                series.repaint();
            }
        }
	}	
	
    protected void createContent() {
    	super.createContent();
    	   	
    	colorEachButton = addCheckButton("Color each", "", this);    	
    	invertedButton = addCheckButton("Inverted", "", this);
    	
    	addLabel(SWT.LEFT, "Size %: ");
    	sizeSpinner = new Spinner(getButtonPane(), SWT.READ_ONLY);    	
    	
    	nullPointsButton = addCheckButton("With null points", "", this);
    	editButton = addPushButton("Edit...", "Edit pyramid series", this);
    }
    
    protected void initContent() {
    	super.initContent();
    	
    	sizeSpinner.setMaximum(100);
    	sizeSpinner.setMinimum(0);
    	sizeSpinner.setIncrement(5);
    	sizeSpinner.setSelection(series.getSizePercent());
    	
        colorEachButton.setSelection(series.getColorEach()); 
        invertedButton.setSelection(chart1.getAxes().getLeft().getInverted());        
    	nullPointsButton.setSelection(false); 
    	
    	editButton.setEnabled(false);
    }
    
    protected void initChart() {
    	super.initChart();
        series = new Pyramid(chart1.getChart());
        series.setSizePercent(50);
        series.fillSampleValues(10);     
    }   	
	
    private Button editButton;
    private Button colorEachButton, invertedButton, nullPointsButton;
    private Spinner sizeSpinner;
}
