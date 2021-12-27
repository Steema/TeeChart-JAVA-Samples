/*
 * RotationAngleDemo.java
 *
 * <p>Copyright: Copyright (c) 2004-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.marks;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.styles.Area;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class RotationAngleDemo extends ChartSample implements ModifyListener, SelectionListener {

    private Area series;	

	public RotationAngleDemo(Composite c) {
		super(c);
	}
	
	public void modifyText(ModifyEvent me) {
		Widget source = me.widget;
		if (source == angleSpinner) {
            int angle = angleSpinner.getSelection();
            series.getMarks().setAngle(angle);  		
		}
	}	
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;	
        if (source == resetButton) {
            series.getMarks().setAngle(0);
        } else if (source == vertButton) {
            series.getMarks().setAngle(90);
        }
	}
	
	protected void createContent() {
		super.createContent();
		addLabel(SWT.LEFT, "Marks angle: ");
        angleSpinner = addSpinner(SWT.READ_ONLY | SWT.BORDER, 0, 360, 5, this);		
		vertButton = addCheckButton("Vertical", "", this);
        resetButton = addCheckButton("Reset", "", this);	
	}

	protected void initContent() {
		super.initContent();
        angleSpinner.setSelection((int)series.getMarks().getAngle());
	}

	protected void initChart() {
		super.initChart();
        chart1.getAspect().setElevation(31);
        chart1.getAspect().setPerspective(0);
        chart1.getAspect().setRotation(360);
        
        series = new Area(chart1.getChart());
        series.getMarks().setAngle(45);
        series.getMarks().setArrowLength(37);
        series.getMarks().setVisible(true);
        series.getPointer().setVisible(false);
        series.fillSampleValues(5);        
	}   			

    private Button resetButton, vertButton;
    private Spinner angleSpinner;	
}
