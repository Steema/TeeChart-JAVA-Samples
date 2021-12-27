
package features.toolbar;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.styles.Pie;

import features.CommandSample;

/**
 * @author tom
 *
 */
public class CommanderDemo extends CommandSample implements SelectionListener {

	public CommanderDemo(Composite c) {
		super(c);
	}

	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;
        if (source == verticalButton) {
        	boolean isSelected = ((Button)source).getSelection();
            setCommanderOrientation(isSelected);
        }
	}	
	
    protected void createContent() {
    	super.createContent();    	       	
    	verticalButton = addCheckButton("Vertical", "", this);
    }
    
    protected void initContent() {
    	super.initContent();   
        getButtonPane().setVisible(false);
    }
    
    protected void initChart() {
    	super.initChart();
        chart1.getAspect().setElevation(315);
        chart1.getAspect().setOrthogonal(false);
        chart1.getAspect().setPerspective(0);
        chart1.getAspect().setRotation(360);
        Pie tmpSeries = new Pie(chart1.getChart());
        tmpSeries.fillSampleValues(8);        
    }   			
    
    private Button verticalButton;    
}
