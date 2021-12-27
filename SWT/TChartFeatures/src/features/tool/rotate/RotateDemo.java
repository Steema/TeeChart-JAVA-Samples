/*
 * RotateDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.tool.rotate;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.styles.Surface;
import com.steema.teechart.tools.Rotate;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class RotateDemo extends ChartSample implements SelectionListener {

	private Rotate tool;

	public RotateDemo(Composite c) {
		super(c);
	}

	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
        if (source instanceof Button) {
        	boolean isSelected = ((Button)source).getSelection();
            if (source == activeButton) {
                tool.setActive(isSelected);
            }
        };
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
        chart1.getHeader().setVisible(false);
        chart1.getLegend().setVisible(false);
        chart1.getAxes().getDepth().setVisible(true);
        chart1.getAspect().setElevation(351);
        chart1.getAspect().setOrthogonal(false);
        chart1.getAspect().setPerspective(55);
        chart1.getAspect().setRotation(344);
        chart1.getAspect().setZoom(60);
        chart1.getAspect().setChart3DPercent(100);
        
        Surface tmpSeries = new com.steema.teechart.styles.Surface(chart1.getChart());
        tmpSeries.getPen().setVisible(false);
        tmpSeries.getMarks().setVisible(false);
        tmpSeries.fillSampleValues(20);

        tool = new com.steema.teechart.tools.Rotate(chart1.getChart());        
	}   		
	
    Button activeButton;	
}
