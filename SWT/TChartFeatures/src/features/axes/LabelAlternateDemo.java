/*
 * LabelAlternateDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.axes;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.axis.Axis;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.HorizontalAxis;
import com.steema.teechart.styles.Line;
import com.steema.teechart.styles.SeriesPointer;
import com.steema.teechart.styles.VerticalAxis;

import features.ChartSample;
import features.WidgetFactory;

/**
 * @author tom
 *
 */
public class LabelAlternateDemo extends ChartSample implements SelectionListener {

    private Axis selectedAxis;
    
	public LabelAlternateDemo(Composite c) {
		super(c);
	}
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
        if (source == leftButton) {
            selectedAxis = chart1.getAxes().getLeft();
        } else if (source == rightButton) {
            selectedAxis = chart1.getAxes().getRight();
        } else if (source == topButton) {
            selectedAxis = chart1.getAxes().getTop();
        } else if (source == bottomButton) {
            selectedAxis = chart1.getAxes().getBottom();
        } else {
            boolean isSelected = ((Button)source).getSelection();
            if (source == alternateButton) {
                selectedAxis.getLabels().setAlternate(isSelected);
            }        	
        }

        alternateButton.setSelection(selectedAxis.getLabels().getAlternate());

        higlightLabels();
	}
	
	protected void createContent() {
		super.createContent();
		Group group = new Group(getButtonPane(), SWT.NONE);
		group.setText(" Axis ");
		group.setLayout(new RowLayout());
		
        leftButton = WidgetFactory.createRadioButton(group, "Left", "", this);              
        rightButton = WidgetFactory.createRadioButton(group, "Right", "", this);
        topButton = WidgetFactory.createRadioButton(group, "Top", "", this);
        bottomButton = WidgetFactory.createRadioButton(group, "Bottom", "", this);
        alternateButton = addCheckButton("Alternate labels", "", this);
	}
	

	protected void initContent() {
		super.initContent();
		higlightLabels();
		alternateButton.setSelection(true);
		leftButton.setSelection(true);
		selectedAxis = chart1.getAxes().getLeft();
	}

	protected void initChart() {
		super.initChart();
        chart1.getAspect().setView3D(false);
        /* Set axes alternate labels */
        for (int t=0; t < chart1.getAxes().getCount(); t++) {
            chart1.getAxes().getAxis(t).getLabels().setAlternate(true);
        }
        Line series = new Line(chart1.getChart());
        /* Sample values */
        series.fillSampleValues();
        series.setHorizontalAxis(HorizontalAxis.BOTH);
        series.setVerticalAxis(VerticalAxis.BOTH);

        series.getMarks().setVisible(false);

        SeriesPointer pointer= series.getPointer();
        pointer.getGradient().setStartColor(Color.YELLOW);
        pointer.getGradient().setEndColor(Color.RED);
        pointer.getGradient().setVisible(true);
        pointer.setInflateMargins(true);
        pointer.setVisible(true);        
	}   			
		
    private void higlightLabels() {
        for (int t=0; t < chart1.getAxes().getCount(); t++) {
            chart1.getAxes().getAxis(t).getLabels().getFont().setBold(
                    chart1.getAxes().getAxis(t).equals(selectedAxis)
                    );
        }
    }

    private Button leftButton, rightButton, topButton, bottomButton;
    private Button alternateButton;	
}
