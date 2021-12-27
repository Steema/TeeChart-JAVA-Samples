/*
 * ExpLabelDemo.java
 *
 * <p>Copyright: (c) 2005-2008 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.axes;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.styles.Line;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class ExpLabelDemo extends ChartSample implements ModifyListener, SelectionListener {

	public ExpLabelDemo(Composite c) {
		super(c);
	}

	public void modifyText(ModifyEvent me) {
		Widget source = me.widget;
	}	
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
		if (source instanceof Button) {
			boolean isSelected = ((Button)source).getSelection();
	        if (source == ExpLabelButton) {
	            chart1.getAxes().getLeft().getLabels().setExponent(isSelected);
	        }			
		}
	}
	
	protected void createContent() {
		super.createContent();
		ExpLabelButton = addCheckButton("Exponent super-script font: ", "", this);
		addLabel(SWT.LEFT, "Format: ");
		addText(SWT.BORDER, "", this);
		
		/* TODO
        formatField.getDocument().addDocumentListener( new DocumentListener() {
            public void setValueFormat() {
                try {
                    chart1.getAxes().getLeft().getLabels().setValueFormat(formatField.getText());
                } catch (IllegalArgumentException e) {
                    //do nothing
                }
            }
            public void insertUpdate(DocumentEvent de) {
                setValueFormat();
            }
            public void removeUpdate(DocumentEvent de) {
                setValueFormat();
            }
            public void changedUpdate(DocumentEvent de) {}
        });*/
	}

	protected void initContent() {
		super.initContent();
        ExpLabelButton.setSelection(chart1.getAxes().getLeft().getLabels().getExponent());
	}

	protected void initChart() {
		super.initChart();
        chart1.getAxes().getLeft().getLabels().setValueFormat("0.00E0");
        chart1.getAxes().getLeft().getLabels().setExponent(true);

        Line series = new Line(chart1.getChart());
        series.add(      1);
        series.add(     10);
        series.add(    100);
        series.add(   1000);
        series.add(  10000);
        series.add( 100000);
        series.add(1000000);        
	}   			
	
    private Button ExpLabelButton;
    //private Text formatField;    
}
