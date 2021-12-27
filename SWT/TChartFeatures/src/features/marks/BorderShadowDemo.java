/*
 * BorderShadowDemo.java
 *
 * <p>Copyright: (c) 2004-2008 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.marks;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.Dimension;
import com.steema.teechart.styles.Bar;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class BorderShadowDemo extends ChartSample implements ModifyListener, SelectionListener {

    private Bar barSeries;
    //TODO private ButtonColor colorButton;
    
	public BorderShadowDemo(Composite c) {
		super(c);
	}

	public void modifyText(ModifyEvent me) {
		Widget source = me.widget;
		if (source == sizeSpinner) {
			int size = ((Spinner)source).getSelection();
            barSeries.getMarks().getShadow().setSize(new Dimension(size,size));    		
		}
	}	
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;	
        /* TODO if (source == colorButton) {
            barSeries.getMarks().getShadow().setColor(new Color(colorButton.getColor()));
        } else*/ if (source instanceof Button) {
			boolean isSelected = ((Button)source).getSelection();
	        if (source == shadowButton) {
	            barSeries.getMarks().getShadow().setVisible(isSelected);
	        }
		}
	}
	
	protected void createContent() {
		super.createContent();	

		shadowButton = addCheckButton("Marks Shadow", "", this);
        //TODO colorButton = new ButtonColor("Color", barSeries.getMarks().getShadow().getColor());
		addLabel(SWT.LEFT, "Size: ");
        sizeSpinner = addSpinner(SWT.READ_ONLY | SWT.BORDER, 0, 20, 1, this);
	}

	protected void initContent() {
		super.initContent();
		shadowButton.setSelection(barSeries.getMarks().getShadow().getVisible());
		sizeSpinner.setSelection(barSeries.getMarks().getShadow().getVertSize());
	}

	protected void initChart() {
		super.initChart();
        barSeries = new Bar(chart1.getChart());
        barSeries.getMarks().setArrowLength(20);
        barSeries.getMarks().getShadow().setSize(3);
        barSeries.getMarks().getShadow().setVisible(true);
        barSeries.getMarks().setVisible(true);
        barSeries.fillSampleValues(6);     
	}   
	
    private Button shadowButton;
    private Spinner sizeSpinner;	
}
