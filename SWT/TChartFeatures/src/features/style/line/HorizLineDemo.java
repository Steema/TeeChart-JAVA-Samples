/*
 * HorizLine.java
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

import com.steema.teechart.styles.HorizLine;

import features.CommandSample;

/**
 * @author tom
 *
 */
public class HorizLineDemo extends CommandSample implements SelectionListener {

    private HorizLine lineSeries;
    
	public HorizLineDemo(Composite c) {
		super(c);
	}
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
		if (source instanceof Button) {
			boolean isSelected = ((Button)source).getSelection();	        	
	        if (source == stairsButton) {
	            lineSeries.setStairs(isSelected);
	        }
		}
	}

	protected void createContent() {
		super.createContent();  
		stairsButton = addCheckButton("Stairs", "", this);		
	}

	protected void initContent() {
		super.initContent();  
		stairsButton.setSelection(lineSeries.getStairs());		
	}

	protected void initChart() {
		super.initChart();
        chart1.getAspect().setView3D(false);
        chart1.getLegend().setVisible(false);
        lineSeries = new com.steema.teechart.styles.HorizLine(chart1.getChart());
        lineSeries.fillSampleValues(8);
        lineSeries.getPointer().setVisible(true);        
	}   	

    private Button stairsButton;		

}
