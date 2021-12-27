/*
 * SelfStackDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights
 * Reserved.</p>
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
import com.steema.teechart.styles.MarksStyle;
import com.steema.teechart.styles.MultiBars;
import com.steema.teechart.styles.ValueListOrder;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class SelfStackDemo extends ChartSample implements SelectionListener {

    private Bar barSeries;
    
	public SelfStackDemo(Composite c) {
		super(c);
		selfStackButton.addSelectionListener(this);
	}
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;
        if (source == selfStackButton) {       	
        	boolean isSelected = ((Button)source).getSelection();
            if (isSelected) {
                barSeries.setMultiBar(MultiBars.SELFSTACK);
            } else {
                barSeries.setMultiBar(MultiBars.NONE);
            }
            barSeries.getMarks().setVisible(!isSelected);
        }
	}		
	
    protected void createContent() {
    	super.createContent();    	   	
    	selfStackButton = addCheckButton("Self stacked", "", this);    	
    }
    
    protected void initContent() {
    	super.initContent();
    	selfStackButton.setSelection(true);
    }
    
    protected void initChart() {
    	super.initChart();
        chart1.getHeader().setVisible(true);
        chart1.getHeader().setText("'Self Stacked' bar series");

        barSeries = new Bar(chart1.getChart());
        barSeries.getMarks().setVisible(false);
        barSeries.getMarks().setStyle(MarksStyle.VALUE);
        barSeries.setMultiBar(MultiBars.SELFSTACK);
        barSeries.setColorEach(true);
        barSeries.getXValues().setOrder(ValueListOrder.ASCENDING);
        barSeries.add(100, "Cars");
        barSeries.add(300, "Phones");
        barSeries.add(200, "Lamps");       
    }   		

    private Button selfStackButton;
}
