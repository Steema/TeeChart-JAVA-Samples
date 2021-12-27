/*
 * LabelFormatDemo.java
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
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.axis.AxisLabels;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.Bar;
import com.steema.teechart.styles.HorizontalAxis;
import com.steema.teechart.styles.VerticalAxis;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class LabelFormatDemo extends ChartSample implements SelectionListener {

	public LabelFormatDemo(Composite c) {
		super(c);
	}
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
        if (source == axisList) {
            transparentButton.setSelection(getSelectedAxisLabels().getTransparent());
        } else if (source == editButton) {
            //TODO DialogFactory.showModal(getSelectedAxisLabels());
        } else if (source instanceof Button) {
			boolean isSelected = ((Button)source).getSelection();
	        if (source == transparentButton) {
	            getSelectedAxisLabels().setTransparent(isSelected);
	        }		
		}
	}
	
	protected void createContent() {
		super.createContent();
		addLabel(SWT.LEFT, "Axis: ");		
        axisList = addCombo(SWT.READ_ONLY | SWT.BORDER | SWT.SINGLE, this);
        transparentButton = addCheckButton("Transparent", "", this);        
        editButton = addPushButton("Edit...", "", this);        
	}

	protected void initContent() {
		super.initContent();
        axisList.setItems(new String[]{"Left", "Top", "Right", "Bottom"});
        axisList.select(0);        
        transparentButton.setSelection(getSelectedAxisLabels().getTransparent());
	}

	protected void initChart() {
		super.initChart();
        Bar series = new Bar(chart1.getChart());
        series.fillSampleValues(5);
        series.setHorizontalAxis(HorizontalAxis.BOTH);
        series.setVerticalAxis(VerticalAxis.BOTH);
        series.getMarks().setArrowLength(20);
        series.getMarks().getCallout().getBrush().setColor(Color.BLACK);
        series.getMarks().getCallout().setLength(20);
        series.getMarks().setVisible(true);
        series.setColor(Color.PURPLE);
        series.setDark3D(false);
	}   			

    private AxisLabels getSelectedAxisLabels() {
            AxisLabels labels;
            switch (axisList.getSelectionIndex()) {
                case 1: {labels = chart1.getAxes().getTop().getLabels(); break; }
                case 2: {labels = chart1.getAxes().getRight().getLabels(); break; }
                case 3: {labels = chart1.getAxes().getBottom().getLabels(); break; }
                default: {labels = chart1.getAxes().getLeft().getLabels(); }
            }
            return labels;
    }

    private Button editButton;
    private Button transparentButton;
    private Combo axisList;		
}
