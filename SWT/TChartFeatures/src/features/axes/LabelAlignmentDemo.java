/*
 * LabelAlignmentDemo.java
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
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.axis.AxisLabelAlign;
import com.steema.teechart.styles.HorizBar;
import com.steema.teechart.styles.MarksStyle;
import com.steema.teechart.styles.VerticalAxis;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class LabelAlignmentDemo extends ChartSample implements SelectionListener {

    private HorizBar series;
    
	public LabelAlignmentDemo(Composite c) {
		super(c);
	}
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;
        if (source == oppositeButton) {
            setLabelAlignment();
        } else {
	        if (source == leftButton) {
	            series.setVerticalAxis(VerticalAxis.LEFT);
	        } else if (source == rightButton) {
	            series.setVerticalAxis(VerticalAxis.RIGHT);
	        }
	        setLabelAlignment();
	    }
	}
	
	protected void createContent() {
		super.createContent();
        oppositeButton = addCheckButton("Opposite Label Aligment", "", this);
        
        addLabel(SWT.LEFT, "Axis: ");
        leftButton = addRadioButton("Left", "", this);        
        rightButton = addRadioButton("Right", "", this);
	}

	protected void initContent() {
		super.initContent();
		oppositeButton.setSelection(true);
		leftButton.setSelection(true);
	}

	protected void initChart() {
		super.initChart();
        /* Set axes labels to "opposite" alignment */
        chart1.getAxes().getLeft().getLabels().setAlign(AxisLabelAlign.OPPOSITE);
        chart1.getAxes().getRight().getLabels().setAlign(AxisLabelAlign.OPPOSITE);
        
        series = new HorizBar(chart1.getChart());
        /* Sample values */
        series.add( 278, "Africa");
        series.add( 123, "America");
        series.add( 321, "Asia");
        series.add( 432, "Australia");
        series.add(  89, "Europe");
        series.add( 300, "Moon");
        series.setColorEach(true);
        series.getMarks().setArrowLength(20);
        series.getMarks().setStyle(MarksStyle.PERCENT);
        series.getMarks().setVisible(true);
        
	}  
	
    private void setLabelAlignment() {
        if (oppositeButton.getSelection()) {
            series.getVertAxis().getLabels().setAlign(AxisLabelAlign.OPPOSITE);
        } else {
            series.getVertAxis().getLabels().setAlign(AxisLabelAlign.DEFAULT);
        }
    }

    private Button leftButton, rightButton;
    private Button oppositeButton;
}
