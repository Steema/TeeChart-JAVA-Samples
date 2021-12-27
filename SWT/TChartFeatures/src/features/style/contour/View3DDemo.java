/*
 * View3DDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.style.contour;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.styles.Contour;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class View3DDemo extends ChartSample implements SelectionListener {

	public View3DDemo(Composite c) {
		super(c);
	}
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
        if (source instanceof Button) {
        	boolean isSelected = ((Button)source).getSelection();
            if (source == view3DButton) {
                chart1.getAspect().setView3D(isSelected);
            }
        };
	}
	
	protected void createContent() {
		super.createContent();	  
		view3DButton = addCheckButton("View 3D", "", this);       
	}

	protected void initContent() {
		super.initContent();	
		view3DButton.setSelection(chart1.getAspect().getView3D());    	
	}

	protected void initChart() {
		super.initChart();
        chart1.getAspect().setView3D(false);
        chart1.getAspect().setChart3DPercent(75);
        chart1.getAspect().setElevation(310);
        chart1.getAspect().setOrthogonal(false);
        chart1.getAspect().setRotation(354);
        chart1.getAspect().setZoom(70);
        chart1.getAxes().getDepth().setVisible(true);		
        Contour series = new com.steema.teechart.styles.Contour(chart1.getChart());
        series.fillSampleValues(20);
        series.setYPosition(0.19);
	}   		
	
    Button view3DButton;
}
