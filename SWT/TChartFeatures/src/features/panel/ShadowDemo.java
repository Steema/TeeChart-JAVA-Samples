/*
 * ShadowDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.panel;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.drawing.Color;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class ShadowDemo extends ChartSample implements SelectionListener {

	public ShadowDemo(Composite c) {
		super(c);
	}

	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
        if (source == editButton) {
            //TODO DialogFactory.showModal(chart1.getPanel().getShadow());
        }		
		if (source instanceof Button) {
			boolean isSelected = ((Button)source).getSelection();
	        if (source == shadowButton) {
	            chart1.getPanel().getShadow().setVisible(isSelected);
	        }
		}
	}
	
	protected void createContent() {
		super.createContent();	
        shadowButton = addCheckButton("Show Shadow", "", this);
        editButton = addPushButton("Edit...", "", this);        
	}

	protected void initContent() {
		super.initContent();
        shadowButton.setSelection(chart1.getPanel().getShadow().getVisible());	}

	protected void initChart() {
		super.initChart();
        chart1.getHeader().setVisible(true);
        chart1.getHeader().setText("Chart Panel Shadow");
        /* set panel shadow properties */
        chart1.getPanel().getShadow().setSize(6);
        chart1.getPanel().getShadow().setColor(Color.DARK_GRAY);
        chart1.getPanel().getShadow().setVisible(true);  
	}   
	
    private Button editButton;
    private Button shadowButton;
}
