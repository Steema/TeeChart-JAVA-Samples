/*
 * ColorEachLineDemo.java
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

import com.steema.teechart.styles.Line;
import com.steema.teechart.styles.PointerStyle;
import com.steema.teechart.styles.SeriesPointer;

import features.ChartSample;
import features.WidgetFactory;

/**
 * @author tom
 *
 */
public class ColorEachLineDemo extends ChartSample  implements SelectionListener {

    private Line lineSeries;
    
	public ColorEachLineDemo(Composite c) {
		super(c);
    	colorEachLineButton.addSelectionListener(this);
    	colorEachPointButton.addSelectionListener(this);		
	}

	public void widgetDefaultSelected(SelectionEvent se) { }

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;
		boolean isSelected = ((Button)source).getSelection();
		if (source == colorEachLineButton) {
			lineSeries.setColorEachLine(isSelected);
	    } else if (source == colorEachPointButton) {
	    	lineSeries.setColorEach(isSelected);
	    } 
        lineSeries.repaint();
        colorEachLineButton.setEnabled(colorEachPointButton.getSelection());		
	}
	
    protected void createContent() {
    	super.createContent();    

		colorEachLineButton = WidgetFactory.createCheckButton(
        		getButtonPane(),
        		"Color each line",
        		"",
        		this);
		colorEachPointButton = WidgetFactory.createCheckButton(
        		getButtonPane(),
        		"Color each point",
        		"",
        		this);    	
    }
    
    protected void initContent() {
    	super.initContent(); 	
    	colorEachPointButton.setSelection(true);
    }
    
    protected void initChart() {
    	super.initChart();
        chart1.getAspect().setView3D(false);  

        lineSeries = new com.steema.teechart.styles.Line(chart1.getChart());
        lineSeries.setColorEach(true);
        lineSeries.setColorEachLine(false);
        SeriesPointer tmpPointer = lineSeries.getPointer();
        tmpPointer.setInflateMargins(true);
        tmpPointer.setStyle(PointerStyle.RECTANGLE);
        tmpPointer.setVisible(true);
        lineSeries.fillSampleValues(20);        
    }

    private Button colorEachLineButton, colorEachPointButton;    
}
