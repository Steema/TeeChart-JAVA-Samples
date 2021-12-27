/*
 * ToolbarDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.toolbar;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.styles.Bar3D;
import com.steema.teechart.styles.BarStyle;

import features.CommandSample;

/**
 * @author tom
 *
 */
public class ToolbarDemo extends CommandSample implements SelectionListener {

	public ToolbarDemo(Composite c) {
		super(c);
	}

	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;
        boolean isSelected = ((Button)source).getSelection();
        if (source == verticalButton) {
            setCommanderOrientation(isSelected);
        } else if (source == hintsButton) {
        	commander.setHints(isSelected);
        } else if (source == textButton) {
        	commander.setLabelValues(isSelected);
        } else if (source == enableButton) {
        	commander.setEnabled(isSelected);
        }
	}	
	
    protected void createContent() {
    	super.createContent();    	       	
        verticalButton = addCheckButton("Vertical", "", this);
        hintsButton = addCheckButton("Hints", "", this);
        textButton = addCheckButton("Text", "", this);
        enableButton = addCheckButton("Enable", "", this);        
    }
    
    protected void initContent() {
    	super.initContent();   

        hintsButton.setSelection(true);
        enableButton.setSelection(true);
        textButton.setSelection(false);
        verticalButton.setSelection(false);
    }
    
    protected void initChart() {
    	super.initChart();
        Bar3D tmpSeries = new Bar3D(chart1.getChart());
        tmpSeries.setBarStyle(BarStyle.PYRAMID);
        tmpSeries.setColorEach(true);
        tmpSeries.fillSampleValues(5);
    }
    
    private Button verticalButton, hintsButton, textButton, enableButton;    
}
