/*
 * DepthTopDemo.java
 *
 * <p>Copyright: (c) 2005-2008 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.axes;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.axis.Axis;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.Tower;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class DepthTopDemo extends ChartSample implements SelectionListener {

	public DepthTopDemo(Composite c) {
		super(c);
	}
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
		if (source instanceof Button) {
			boolean isSelected = ((Button)source).getSelection();
	        if (source == depthButton) {
	            chart1.getAxes().getDepth().setVisible(isSelected);
	        } else if (source == depthTopButton) {
	            chart1.getAxes().getDepthTop().setVisible(isSelected);
	        }			
		}
	}
	
	protected void createContent() {
		super.createContent();
        depthTopButton =addCheckButton("View DepthTop Axis", "", this);
        depthButton = addCheckButton("View Depth Axis", "", this);        			
	}

	protected void initContent() {
		super.initContent();
        depthTopButton.setSelection(chart1.getAxes().getDepthTop().getVisible());
        depthButton.setSelection(chart1.getAxes().getDepth().getVisible());        
    }

	protected void initChart() {
		super.initChart();
        chart1.getLegend().setVisible(false);

        Axis axis = chart1.getAxes().getDepthTop();
        axis.getLabels().getFont().setColor(Color.WHITE_SMOKE);
        axis.getLabels().getFont().setSize(13);
        axis.getLabels().getFont().getShadow().setColor(Color.WHITE);
        axis.getLabels().getFont().getShadow().setHorizSize(1);
        axis.getLabels().getFont().getShadow().setVertSize(1);
        axis.setVisible(true);
        
        Tower series = new Tower(chart1.getChart());
        series.fillSampleValues();        
	}   				

    private Button depthButton, depthTopButton;
}
