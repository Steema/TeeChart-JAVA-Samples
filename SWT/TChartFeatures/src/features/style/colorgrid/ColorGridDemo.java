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
public class ColorGridDemo extends ChartSample implements ModifyListener, SelectionListener {

    private ColorGrid series;
    //TODO private ButtonPen gridPenButton;
    
	public ColorGridDemo(Composite c) {
		super(c);
		sizeSpinner.addModifyListener(this);
	}
	
	public void modifyText(ModifyEvent me) {
		Widget source = me.widget;
		if (source == sizeSpinner) {
            int size = sizeSpinner.getSelection();
            series.fillSampleValues(size);
            if (size>30) {
                //TODO gridButton.setSelected(false);
                series.getPen().setVisible(false);
            }
		}
	}	
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;
        if (source == editButton) {
            //TODO ChartEditor.editSeries(series);
            setGridControls();
        } else {		
        	boolean isSelected = ((Button)source).getSelection();
            if (source == gridButton) {
                //TODO gridPenButton.setEnabled(gridButton.isSelected());
                series.getPen().setVisible(isSelected);
            } else if  (source == centerButton) {
                series.setCenteredPoints(isSelected);
            }        	
        }
	}	
	
    protected void createContent() {
    	super.createContent();
    	   	
    	gridButton = addCheckButton("Grid", "", this);    	
    	addLabel(SWT.LEFT, "Size: ");    	
    	sizeSpinner = new Spinner(getButtonPane(), SWT.READ_ONLY | SWT.BORDER);    	
    	
    	centerButton = addCheckButton("Centered", "", this);    	
    }
    
    protected void initContent() {
    	super.initContent();    	   	
    	sizeSpinner.setMaximum(5000);
    	sizeSpinner.setMinimum(1);
    	sizeSpinner.setIncrement(1);
    	sizeSpinner.setSelection(20);    	
        centerButton.setSelection(series.getCenteredPoints());
        setGridControls();        
    }
    
    protected void initChart() {
    	super.initChart();
        chart1.getAspect().setView3D(false);    	
        series = new ColorGrid(chart1.getChart());
        series.getPen().setColor(Color.WHITE);
        series.setUsePalette(true);
        series.setPaletteStyle(PaletteStyle.STRONG);
        series.setUseColorRange(false);
        series.fillSampleValues(20);
        /* TODO
        editButton = new JButton("Edit...");
        gridPenButton = new ButtonPen(series.getPen(), "Grid...");
         */
    }   			

    private void setGridControls() {
        /* TODO 
        gridButton.setSelected(series.getPen().getVisible());
        gridPenButton.setEnabled(series.getPen().getVisible());
        */
    }
    
    private Button editButton;
    private Button gridButton, centerButton;
    private Spinner sizeSpinner;    
}
