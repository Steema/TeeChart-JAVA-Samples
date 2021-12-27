/*
 * ZPositionDemo.java
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
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Slider;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.axis.Axis;
import com.steema.teechart.styles.Line;
import com.steema.teechart.styles.VerticalAxis;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class ZPositionDemo extends ChartSample implements SelectionListener {

	public ZPositionDemo(Composite c) {
		super(c);
	}
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
        if (source == axisList) {
            Axis axis;
            switch (axisList.getSelectionIndex()) {
                case 1: {axis = chart1.getAxes().getRight(); break; }
                default: {axis = chart1.getAxes().getLeft(); }
            }
            int position = (int)axis.getZPosition();
            positionSlider.setSelection(position);
            positionLabel.setText(Integer.toString(position)+"%");
        } else if (source == positionSlider) {
            int value = ((Slider)source).getSelection();
            positionLabel.setText(Integer.toString(value)+"%");
            Axis axis = chart1.getAxes().getLeft();
            switch (axisList.getSelectionIndex()) {
                case 0: { axis = chart1.getAxes().getLeft(); break; }
                case 1: { axis = chart1.getAxes().getRight(); break; }
            }
            axis.setZPosition(value);
            axis.getGrid().setZPosition(value);
        }
	}
	
	protected void createContent() {
		super.createContent();
        axisList = addCombo(SWT.READ_ONLY | SWT.BORDER | SWT.SINGLE, this);		
		addLabel(SWT.LEFT, "Z position:: ");
        positionSlider = addSlider(SWT.HORIZONTAL,
                0,
                100,
                0,
                this);
        positionLabel = addLabel(SWT.LEFT, "0%");        	
	}

	protected void initContent() {
		super.initContent();
		axisList.setItems(new String[]{"Left", "Right"});
        axisList.select(0);		
        positionSlider.setSelection((int)chart1.getAxes().getLeft().getZPosition());
	}

	protected void initChart() {
		super.initChart();
        chart1.getHeader().setVisible(true);
        chart1.setText("Axes Z Position");
        chart1.getWalls().getLeft().setVisible(false);
        chart1.getAspect().setChart3DPercent(100);
        
        Line series = new Line(chart1.getChart());
        series.setVerticalAxis(VerticalAxis.BOTH);        
	}   				

    private Combo axisList;
    private Label positionLabel;
    private Slider positionSlider;	
}
