/*
 * BezierDemo.java
 *
 * <p>Copyright: (c) 2005-2008 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.style.bezier;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.Bezier;
import com.steema.teechart.styles.CustomStack;
import com.steema.teechart.styles.PointerStyle;
import com.steema.teechart.styles.SeriesPointer;

import features.ChartSample;
import features.utils.EnumStrings;

/**
 * @author tom
 *
 */
public class BezierDemo extends ChartSample implements ModifyListener, SelectionListener {

    private Bezier series;
	
	public BezierDemo(Composite c) {
		super(c);
		pointsSpinner.addModifyListener(this);
	}

	public void modifyText(ModifyEvent me) {
		Widget source = me.widget;
		if (source == pointsSpinner) {
            int points = pointsSpinner.getSelection();
            //TODO	
		}
	}	
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;	
        if (source instanceof Button) {        	
            boolean isSelected = ((Button)source).getSelection();
            if (source == view3DButton) {
                chart1.getAspect().setView3D(isSelected);
            } else if (source == editButton) {
                //TODO ChartEditor.editSeries(series);
            };
        }
	}	
	
    protected void createContent() {
    	super.createContent();
    	   	
    	addLabel(SWT.LEFT, "Style: ");    	
    	styleList = addCombo(SWT.DROP_DOWN | SWT.READ_ONLY);
    	
    	addLabel(SWT.LEFT, "Middle points: ");
    	pointsSpinner = new Spinner(getButtonPane(), SWT.READ_ONLY);    	
    	
    	editButton = addPushButton("Edit...", "Edit bezier series", this);
    	view3DButton = addCheckButton("3D", "", this);    	
    }
    
    protected void initContent() {
    	super.initContent();    	   	
    	pointsSpinner.setMaximum(200);
    	pointsSpinner.setMinimum(3);
    	pointsSpinner.setIncrement(5);
    	pointsSpinner.setSelection(32);    	
    	styleList.setItems(EnumStrings.BEZIER_STYLES);    	
    	styleList.select(0);
    	view3DButton.setSelection(chart1.getAspect().getView3D());
    	editButton.setEnabled(false);
    }
    
    protected void initChart() {
    	super.initChart();
        chart1.getHeader().setVisible(true);
        chart1.setText("Bezier curves");
        series = new Bezier(chart1.getChart());
        series.setStacked(CustomStack.NONE);
        series.getLinePen().setColor(Color.RED);
        series.fillSampleValues(30);

        SeriesPointer tmpPointer = series.getPointer();
        tmpPointer.setHorizSize(2);
        tmpPointer.setInflateMargins(true);
        tmpPointer.setStyle(PointerStyle.CROSS);
        tmpPointer.setVertSize(2);
        tmpPointer.setVisible(true);        
    }   	
	
    private Button editButton;
    private Button view3DButton;
    private Combo styleList;
    private Spinner pointsSpinner;	
}
