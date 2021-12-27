/*
 * CustomLabelsDemo.java
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
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.axis.Axis;
import com.steema.teechart.axis.AxisLabelItem;
import com.steema.teechart.axis.AxisLabelsItems;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.Line;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class CustomLabelsDemo extends ChartSample implements ModifyListener, SelectionListener {

	public CustomLabelsDemo(Composite c) {
		super(c);
	}

	public void modifyText(ModifyEvent me) {
		Widget source = me.widget;
        int value = ((Spinner)source).getSelection();
        if (source == labelSpinner) {
        	positionSpinner.setSelection((int)(chart1.getAxes().getLeft().getCustomLabels().getItem(value)).getValue());
        } else if (source == positionSpinner) {
            chart1.getAxes().getLeft().getCustomLabels().getItem(value).setValue(value);
        }
	}	
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;	
        if (source == editButton) {
            int label = labelSpinner.getSelection();
            /* TODO DialogFactory.showModal(
                chart1.getAxes().getLeft().getCustomLabels().getItem(label)
            );*/
        } else if (source instanceof Button) {
			boolean isSelected = ((Button)source).getSelection();
	        if (source == visibleButton) {
	            if (isSelected) {
	                addCustomLabels();
	            } else {
	                chart1.getAxes().getLeft().getCustomLabels().clear();
	            }
	        }			
		}
	}
	
	protected void createContent() {
		super.createContent();
		visibleButton = addCheckButton("Custom labels", "", this);
		addLabel(SWT.LEFT, "Label: ");
		labelSpinner = addSpinner(SWT.READ_ONLY | SWT.BORDER, 
                -10000,
                +10000,
                (int)chart1.getAxes().getLeft().getCustomLabels().getItem(labelSpinner.getSelection()).getValue(), 
                this);		
		positionSpinner = addSpinner(SWT.READ_ONLY | SWT.BORDER,
	              0,
	              chart1.getAxes().getLeft().getCustomLabels().size()-1,
	              1,
	              this);
		editButton = addCheckButton("Edit...", "", this);		
	}

	protected void initContent() {
		super.initContent();
		// TODO labelSpinner.setSelection(0);
		positionSpinner.setSelection(0);
		syncDemoControls();
	}

    protected void addCustomLabels() {
        Axis axis = chart1.getAxes().getLeft();
        AxisLabelsItems items = axis.getCustomLabels();
        //remove all custom labels
        items.clear();
        //add custom labels
        AxisLabelItem item;
        item = items.add(123.0, "Hello");
        item.getFont().setSize(16);
        item = items.add(466.0, "Good\nBye");
        item.setTransparent(false);
        items.add(300);
        item = items.add(-100);
        item.setTransparent(false);
        item.setTransparency(50);
        item.setColor(Color.BLUE);
    }
    
	protected void initChart() {
		super.initChart();

        Line series = new Line(chart1.getChart());
        series.add(new int[]{200,0,123,300,260,-100,650,400});
        addCustomLabels();        
	}   			
	
    private void syncDemoControls() {
        boolean customLabels = chart1.getAxes().getLeft().getCustomLabels().size() > 0;
        labelSpinner.setEnabled(customLabels);
        if (labelSpinner.isEnabled()) {
        	labelSpinner.setMaximum((int)chart1.getAxes().getLeft().getLabels().getItems().size()-1);
        }
        positionSpinner.setEnabled(customLabels);
        editButton.setEnabled(customLabels);
    }
    
    private Button editButton;
    private Button visibleButton;
    private Spinner labelSpinner, positionSpinner;	
}
