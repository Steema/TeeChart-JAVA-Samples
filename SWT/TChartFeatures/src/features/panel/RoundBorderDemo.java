/*
 * MarginPixelsDemo.java
 *
 * <p>Copyright: Copyright (c) 2004-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.panel;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.BevelStyle;
import com.steema.teechart.PanelMarginUnits;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.GradientDirection;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class RoundBorderDemo extends ChartSample implements ModifyListener, SelectionListener {
	
    //TODO private ButtonPen penButton;	

	public RoundBorderDemo(Composite c) {
		super(c);
		roundingSpinner.addModifyListener(this);
	}
	
	public void modifyText(ModifyEvent me) {
		Widget source = me.widget;
        if (source == roundingSpinner) {
            int value = roundingSpinner.getSelection();
            chart1.getPanel().setBorderRound(value);
            roundedButton.setSelection(value == 0);
        }
	}	
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
		if (source instanceof Button) {
			boolean isSelected = ((Button)source).getSelection();
	        if (source == roundedButton) {
	            if (isSelected) {
	                chart1.getPanel().setBorderRound(0);
	                roundingSpinner.setSelection(0);
	            }
	        }
		}
	}
	
	protected void createContent() {
		super.createContent();	
		addLabel(SWT.LEFT, "Rounding amount:");
		roundingSpinner = addSpinner(SWT.READ_ONLY | SWT.BORDER, 0, 1000, 5);
		roundedButton = addCheckButton("No rounding", "", this);		
        //TODO penButton = new ButtonPen();		
	}

	protected void initContent() {
		super.initContent();
        //TODO penButton.setPen(chart1.getPanel().getBorderPen());        
		roundedButton.setSelection(chart1.getPanel().getBorderRound()==0);
		roundingSpinner.setSelection(chart1.getPanel().getBorderRound());
	}

	protected void initChart() {
		super.initChart();
        chart1.getHeader().setText("Round Borders");
        chart1.getHeader().getFont().setSize(19);
        chart1.getHeader().getFont().setBold(true);
        chart1.getHeader().setVisible(true);
        chart1.getPanel().getShadow().setColor(Color.BLACK);
        chart1.getPanel().setBevelOuter(BevelStyle.NONE);
        chart1.getPanel().getGradient().setDirection(GradientDirection.RADIAL);
        chart1.getPanel().getGradient().setEndColor(Color.TEAL);
        chart1.getPanel().getGradient().setVisible(true);
        chart1.getPanel().setMarginRight(10);
        chart1.getPanel().setMarginUnits(PanelMarginUnits.PIXELS);

        chart1.getPanel().getBorderPen().setColor(Color.NAVY);
        chart1.getPanel().getBorderPen().setWidth(5);
        chart1.getPanel().getBorderPen().setVisible(true);
        chart1.getPanel().setBorderRound(30);
	}   
	
    private Button roundedButton;
    private Spinner roundingSpinner;   	
}
