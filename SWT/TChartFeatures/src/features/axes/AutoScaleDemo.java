/*
 * AutoScaleDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.axes;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.axis.Axis;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.HorizontalAxis;
import com.steema.teechart.styles.Line;
import com.steema.teechart.styles.SeriesPointer;
import com.steema.teechart.styles.VerticalAxis;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class AutoScaleDemo extends ChartSample implements SelectionListener {

	private Axis xAxis;

	public AutoScaleDemo(Composite c) {
		super(c);
	}

	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;
		if (source == leftButton) {
			chart1.getPage().next();
		} else if (source == rightButton) {
			chart1.getPage().previous();           
		} else if (source instanceof Button) {
			boolean isSelected = ((Button)source).getSelection();
	        if (source == alternateButton) {
	            chart1.getPage().setAutoScale(isSelected);
	        }		
		}
		leftButton.setEnabled(!(chart1.getPage().getCount() == chart1.getPage().getCurrent()));
		rightButton.setEnabled(!(1 == chart1.getPage().getCurrent()));
		finishingTheRest();				
	}

	protected void createContent() {
		super.createContent();
        addLabel(SWT.LEFT, "FirstValue Index");
        valueText = addLabel(SWT.LEFT, String.valueOf(xAxis.getEndPosition()));

        rightButton = addPushButton("<<", "", this);
        leftButton = addPushButton(">>", "", this);
        alternateButton = addCheckButton("AutoScale", "", this);
	}

	protected void initContent() {
		super.initContent();
        alternateButton.setVisible(true);
        alternateButton.setSelection(true);
        leftButton.setEnabled(true);
        rightButton.setVisible(true);
        rightButton.setEnabled(false);			
        finishingTheRest();
	}

	protected void initChart() {
		super.initChart();
	    xAxis = chart1.getAxes().getBottom();		
		chart1.getPage().setAutoScale(true);
		chart1.getPage().setMaxPointsPerPage(10);
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
	
    private void finishingTheRest() {
        valueText.setText(Double.toString(xAxis.getMinXValue()));
    }

    private Button leftButton, rightButton;
    private Button alternateButton;
    private Label valueText;	
}
