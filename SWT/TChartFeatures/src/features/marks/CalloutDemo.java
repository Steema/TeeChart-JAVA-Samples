/*
 * CalloutDemo.java
 *
 * <p>Copyright: (c) 2004-2008 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.marks;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.Bar;
import com.steema.teechart.styles.PointerStyle;

import features.ChartSample;
import features.utils.EnumStrings;

/**
 * @author tom
 *
 */
public class CalloutDemo extends ChartSample implements SelectionListener {

    private Bar series;
	
	public CalloutDemo(Composite c) {
		super(c);
	}

	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
        if (source == styleList) {
            series.getMarks().getCallout().setStyle(PointerStyle.fromInt(styleList.getSelectionIndex()));
        } else if (source == editButton) {
            //TODO ChartEditor.editSeriesMarks(series);
        } else if (source == visibleButton) {
            series.getMarks().getCallout().setVisible(visibleButton.getSelection());
        }
	}
	
	protected void createContent() {
		super.createContent();
		addLabel(SWT.LEFT, "Style: ");
		styleList = addCombo(SWT.BORDER | SWT.READ_ONLY | SWT.SINGLE, this);
	    editButton = addPushButton("Edit...", "", this);
	    visibleButton = addCheckButton("Visible", "", this);	    
	}

	protected void initContent() {
		super.initContent();
		visibleButton.setSelection(series.getMarks().getCallout().getVisible());
		styleList.setItems(EnumStrings.POINTER_STYLES);
		styleList.select(series.getMarks().getCallout().getStyle().getValue());
	    editButton.setEnabled(false);
	}

	protected void initChart() {
		super.initChart();
        chart1.getHeader().setText("Series Marks Callouts");
        chart1.getHeader().setVisible(true);
        chart1.getAspect().setChart3DPercent(20);
        chart1.getLegend().setVisible(false);
        
        series = new Bar(chart1.getChart());
        series.getMarks().setArrowLength(21);
        series.getMarks().getCallout().setStyle(PointerStyle.DOWNTRIANGLE);
        series.getMarks().getCallout().getBrush().setColor(Color.BLACK);
        series.getMarks().getCallout().setVisible(true);
        series.getMarks().getCallout().setDistance(13);
        series.getMarks().getCallout().setLength(21);
        series.getMarks().setVisible(true);
        series.fillSampleValues();        
	}
	
    private Button visibleButton;
    private Combo styleList;
    private Button editButton;	
}
