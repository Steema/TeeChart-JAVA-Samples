/*
 * AxisLabelDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
/*
 * AxisLabelDemo.java
 *
 * <p>Copyright: (c) 2005-2008 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.tool;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.drawing.ChartFont;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.Bar;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class AxisLabelDemo extends ChartSample implements SelectionListener {

	public AxisLabelDemo(Composite c) {
		super(c);
	}
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		        
        if (source == activeButton) {
        	boolean isSelected = ((Button)source).getSelection();
            chart1.getTools().getTool(0).setActive(isSelected);
        }
	}	

	protected void createContent() {
		super.createContent();
		activeButton = addCheckButton("Active", "", this);        
	}
	
	protected void initContent() {
		super.initContent();
		activeButton.setSelection(true);       
	}	

	protected void initChart() {
		super.initChart();
        chart1.getLegend().setVisible(false);

        ChartFont tmpFont = chart1.getAxes().getLeft().getLabels().getFont();
        tmpFont.setColor(Color.NAVY);
        tmpFont.setSize(13);
        tmpFont.setBold(true);
        tmpFont.getShadow().setColor(Color.AQUA);
        tmpFont.getShadow().setHorizSize(1);
        tmpFont.getShadow().setVertSize(-1); 
        
        Bar tmpSeries = new com.steema.teechart.styles.Bar(chart1.getChart());
        tmpSeries.getMarks().setVisible(true);
        tmpSeries.getMarks().setColor(Color.RED);
        /* sample values for the series */
        tmpSeries.add(new int[]{8,800,150,1500,2000,1000,120000,30000});

        /* create the custom tool */
        new AxisLabel(chart1.getAxes().getLeft());        
	}   		
	
    private Button activeButton;	
}	

