/*
 * SemiDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.style.pie;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.styles.MarksStyle;
import com.steema.teechart.styles.Pie;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class SemiDemo extends ChartSample implements SelectionListener {

    private Pie pieSeries;

    public SemiDemo(Composite c) {
		super(c);
	}
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
		if (source instanceof Button) {
			boolean isSelected = ((Button)source).getSelection();	        	
	        if (source == semiButton) {
	            if (isSelected) {
	                pieSeries.setAngleSize(180);
	            } else {
	                pieSeries.setAngleSize(360);
	            }
	        } else if (source == verticalButton) {
	            if (isSelected) {
	                pieSeries.setRotationAngle(90);
	            } else {
	                pieSeries.setRotationAngle(0);
	            }
	        }
		}
	}

	protected void createContent() {
		super.createContent();
		semiButton = addCheckButton("Semi-Pie", "", this);       
		verticalButton = addCheckButton("Vertical", "", this);		
	}

	protected void initContent() {
		super.initContent();  
		semiButton.setSelection(true);
		verticalButton.setSelection(true);		
	}

	protected void initChart() {
		super.initChart();
		chart1.getAspect().setView3D(false);
		
		pieSeries = new com.steema.teechart.styles.Pie(chart1.getChart());
        pieSeries.getMarks().setVisible(true);
        pieSeries.getMarks().setStyle(MarksStyle.LABELPERCENT);
        pieSeries.fillSampleValues(5);
        pieSeries.setAngleSize(180);
        pieSeries.setRotationAngle(90);	
	}   	

    private Button semiButton;
    private Button verticalButton;	
}
