/*
 * SideAllDemo.java
 *
 * <p>Copyright: (c) 2005-2008 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.style.bar;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.styles.Bar;
import com.steema.teechart.styles.MultiBars;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class SideAllDemo extends ChartSample implements SelectionListener {

    private Bar bar1Series, bar2Series;
	
	public SideAllDemo(Composite c) {
		super(c);
        sideAllButton.addSelectionListener(this);
        sideToSideButton.addSelectionListener(this);		
	}
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;
        if (source == sideAllButton) {
            bar1Series.setMultiBar(MultiBars.SIDEALL);
        } else if (source == sideToSideButton) {
            bar1Series.setMultiBar(MultiBars.SIDE);
        }
	}	
	
    protected void createContent() {
    	super.createContent();
    	   	
    	sideAllButton = addRadioButton("Side All", "", this);    	
    	sideToSideButton = addRadioButton("Side to Side", "", this);
    }
    
    protected void initContent() {
    	super.initContent();
    	sideAllButton.setSelection(true);
    }
    
    protected void initChart() {
    	super.initChart();

        bar1Series = new com.steema.teechart.styles.Bar(chart1.getChart());
        bar2Series = new com.steema.teechart.styles.Bar(chart1.getChart());
        bar1Series.fillSampleValues(5);
        bar2Series.fillSampleValues(5);
        bar1Series.setMultiBar(MultiBars.SIDEALL);
    }   		

    private Button sideAllButton, sideToSideButton;	
}
