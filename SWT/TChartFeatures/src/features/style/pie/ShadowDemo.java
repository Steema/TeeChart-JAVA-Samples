/*
 * ShadowDemo.java
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

import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.Pie;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class ShadowDemo extends ChartSample implements SelectionListener {

	private Pie pieSeries;

	public ShadowDemo(Composite c) {
		super(c);
	}

	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
		if (source instanceof Button) {
			boolean isSelected = ((Button)source).getSelection();	        	
			if (source == shadowButton) {
				pieSeries.getShadow().setVisible(isSelected);
			}
		}
	}

	protected void createContent() {
		super.createContent();
		shadowButton = addCheckButton("Draw Pie shadow", "", this);       
	}

	protected void initContent() {
		super.initContent();  
		shadowButton.setSelection(pieSeries.getShadow().getVisible());
	}

	protected void initChart() {
		super.initChart();
		chart1.getHeader().setVisible(true);
		chart1.setText("Pie Shadow");
		chart1.getAspect().setElevation(315);
		chart1.getAspect().setOrthogonal(false);
		chart1.getAspect().setPerspective(0);
		chart1.getAspect().setRotation(360);
		
        pieSeries = new com.steema.teechart.styles.Pie(chart1.getChart());
        pieSeries.getMarks().setVisible(true);
        pieSeries.getShadow().setVisible(true);
        pieSeries.getShadow().setHorizSize(30);
        pieSeries.getShadow().setVertSize(50);
        pieSeries.getShadow().setColor(Color.SILVER);
        pieSeries.fillSampleValues(9);		
	}   

	private Button shadowButton;	

}
