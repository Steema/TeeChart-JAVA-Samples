/*
 * CustomPositionDemo.java
 *
 * <p>Copyright: (c) 2004-2008 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.headfoot;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Slider;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.Dimension;
import com.steema.teechart.TextShapeStyle;
import com.steema.teechart.drawing.ChartFont;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.Pie;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class CustomPositionDemo extends ChartSample implements SelectionListener {

	public CustomPositionDemo(Composite c) {
		super(c);
        leftSlider.addSelectionListener(this);
        topSlider.addSelectionListener(this);		
	}

	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;
		if (source instanceof Button) {
			boolean isSelected = ((Button)source).getSelection();
			if (source == customPosButton) {
				chart1.getHeader().setCustomPosition(isSelected);
				chart1.getHeader().setLeft(leftSlider.getSelection());
				chart1.getHeader().setTop(topSlider.getSelection());
			}        	        
		} else if (source instanceof Slider) {                            
			int pos = ((Slider)source).getSelection();
			if (source == leftSlider) {
				chart1.getHeader().setLeft(pos);
			} else if (source == topSlider) {
				chart1.getHeader().setTop(pos);
			}        
		}
	}		
	
	protected void createContent() {
		super.createContent();
        customPosButton = addCheckButton("Custom position", "", this);
        addLabel(SWT.LEFT, "Left: ");
        leftSlider = addSlider(SWT.HORIZONTAL, 0, 0, 0);
        addLabel(SWT.LEFT, "Top: ");
        topSlider = addSlider(SWT.HORIZONTAL, 0, 0, 0);        
 	}
	
	protected void initContent() {
		super.initContent();
		customPosButton.setSelection(chart1.getHeader().getCustomPosition());
	}
	
	protected void initChart() {
		super.initChart();
        chart1.getAspect().setView3D(false);
        chart1.getHeader().setVisible(true);
        chart1.getHeader().setText("This is the Title");
        chart1.getHeader().setTransparent(false);
        chart1.getHeader().setCustomPosition(true);
        chart1.getHeader().setLeft(123);
        chart1.getHeader().setTop(9);
        chart1.getHeader().setShapeStyle(TextShapeStyle.ROUNDRECTANGLE);
        ChartFont font = chart1.getHeader().getFont();
        font.setBold(true);
        font.setColor(Color.BLACK);
        font.getShadow().setSize(new Dimension(4,2));
        font.getShadow().setColor(Color.DARK_GRAY);
        
        Pie tmpSeries = new Pie(chart1.getChart());
        tmpSeries.fillSampleValues(7);        
	}	

    private Button customPosButton;
    private Slider leftSlider, topSlider;
}
