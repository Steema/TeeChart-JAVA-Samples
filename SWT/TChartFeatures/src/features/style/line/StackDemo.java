package features.style.line;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.styles.CustomStack;
import com.steema.teechart.styles.Line;

import features.ChartSample;
import features.WidgetFactory;
import features.utils.EnumStrings;

/**
*
* @author tom
*/
public class StackDemo extends ChartSample implements SelectionListener {

	public StackDemo(Composite c) {
		super(c);
		
		stackList.addSelectionListener(this);		
	}
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {
		Widget source = se.widget;
        if (source == stackList) {
            CustomStack mode = CustomStack.NONE;
            switch (stackList.getSelectionIndex()) {
                case 0: mode = CustomStack.NONE; break;
                case 1: mode = CustomStack.OVERLAP; break;
                case 2: mode = CustomStack.STACK; break;
                case 3: mode = CustomStack.STACK100; break;
            }

            ((Line)chart1.getSeries(0)).setStacked(mode);
        }		
	}	
	
    protected void createContent() {
    	super.createContent();
    	   	
    	WidgetFactory.createLabel(getButtonPane(), SWT.LEFT, "Mode: ");
    	stackList = new Combo(getButtonPane(), SWT.DROP_DOWN | SWT.READ_ONLY);    	    	   
    }
    
    protected void initContent() {
    	super.initContent();
    	     	
    	stackList.setItems(EnumStrings.STACK_STYLES);
    	stackList.select(0);
    }
    
    protected void initChart() {
    	super.initChart();

        Line lineSeries = null;
        for (int i=0; i < 4; i++) {
            lineSeries = new Line(chart1.getChart());
            lineSeries.setStacked(CustomStack.NONE);
            lineSeries.fillSampleValues(10);
        }	    
    }   	
	
    private Combo stackList;	

}
