/*
 * TransposeDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.tool;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.styles.Surface;
import com.steema.teechart.tools.GridTranspose;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class TransposeDemo extends ChartSample implements SelectionListener {

    private GridTranspose tool;
    
	public TransposeDemo(Composite c) {
		super(c);
	}
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
        if (source == transposeButton) {
            tool.transpose();
        }
	}	

	protected void createContent() {
		super.createContent();
		transposeButton = addPushButton("Transpose!", "", this);        
	}

	protected void initChart() {
		super.initChart();
        chart1.getHeader().setVisible(true);
        chart1.getLegend().setVisible(false);
        chart1.setText("3D Transpose Tool");

        chart1.getAspect().setChart3DPercent(65);
        chart1.getAxes().getDepth().setVisible(false);
        chart1.getAspect().setOrthoAngle(60);
        
        tool = new GridTranspose(chart1.getChart());

        Surface surfaceSeries = new com.steema.teechart.styles.Surface(chart1.getChart());
        tool.setSeries(surfaceSeries);

        surfaceSeries.fillSampleValues(30);    
	}   		
	
    private Button transposeButton;	
}	

