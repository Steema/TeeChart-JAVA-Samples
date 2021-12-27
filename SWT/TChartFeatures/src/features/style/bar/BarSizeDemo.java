/*
 * BarSizeDemo.java
 *
 * <p>Copyright: (c) 2005-2008 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.style.bar;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Slider;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.TextShapePosition;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.GradientDirection;
import com.steema.teechart.tools.Annotation;
import com.steema.teechart.styles.*;

import features.ChartSample;
import features.WidgetFactory;

/**
 * @author tom
 *
 */
public class BarSizeDemo extends ChartSample implements SelectionListener {

    private Bar barSeries;
    private Annotation annotationTool;
    
	public BarSizeDemo(Composite c) {
		super(c);
        barSpinner.addSelectionListener(this);
        positionSlider.addSelectionListener(this);
        sizeSlider.addSelectionListener(this);		
	}
	
	public void widgetDefaultSelected(SelectionEvent se) { }

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;
        if (source == barSpinner) {
            refreshButtonPane();
        } else if (source instanceof Slider) {
            Slider tmpSlider = (Slider)source;
            double tmpValue = tmpSlider.getSelection() / 100.0;
            int tmpBar = barSpinner.getSelection();
            if (tmpSlider == positionSlider) {
                positionLabel.setText(String.valueOf(tmpValue));
                barSeries.getXValues().setValue(tmpBar , tmpValue);
                refreshAnnotation();
            } else  if (tmpSlider == sizeSlider) {
                sizeLabel.setText(String.valueOf(tmpValue));
                barSeries.getYValues().setValue(tmpBar , tmpValue);
            }
            barSeries.repaint();
        }
	}
	
    protected void createContent() {
    	super.createContent();    

    	WidgetFactory.createLabel(getButtonPane(), SWT.LEFT, "Bar: ");
    	barSpinner = new Spinner(getButtonPane(), SWT.READ_ONLY);
    	WidgetFactory.createLabel(getButtonPane(), SWT.LEFT, "Position: ");
        positionSlider = new Slider(getButtonPane(), SWT.HORIZONTAL);
        positionLabel = WidgetFactory.createLabel(getButtonPane(), SWT.LEFT, "");
        WidgetFactory.createLabel(getButtonPane(), SWT.LEFT, "Size: ");
        sizeSlider = WidgetFactory.createSlider(getButtonPane(), SWT.HORIZONTAL, 0, 300, 40);
        sizeLabel = WidgetFactory.createLabel(getButtonPane(), SWT.LEFT, "");
    }
    
    protected void initContent() {
    	super.initContent();

    	barSpinner.setMinimum(0);
    	barSpinner.setMaximum(barSeries.getXValues().count-1);
    	barSpinner.setSelection(3);
    	positionSlider.setMinimum(-barSeries.getCount()*100);
    	positionSlider.setMaximum(barSeries.getCount()*100);        
    }    
    
    protected void initChart() {
    	super.initChart();
        chart1.getHeader().setVisible(true);
        chart1.setText("Bar Size Series example.");

        barSeries = new Bar(chart1.getChart());
        barSeries.getMarks().setVisible(false);
        barSeries.fillSampleValues(6);

        barSeries.getXValues().setValue(3,2.8);
        barSeries.getColors().setColor(1, Color.BLUE);
        barSeries.getColors().setColor(4, Color.YELLOW);

        barSeries.getYValues().setValue(0, 0.4);
        barSeries.getYValues().setValue(2, 0.1);
        barSeries.getYValues().setValue(4, 1.1);

        annotationTool = new Annotation(chart1.getChart());
        annotationTool.getCallout().setColor(Color.BLACK);
        annotationTool.getCallout().getArrow().setVisible(false);

        TextShapePosition tmpShape = annotationTool.getShape();
        tmpShape.setCustomPosition(true);
        tmpShape.getGradient().setDirection(GradientDirection.VERTICAL);
        tmpShape.getGradient().setEndColor(Color.SILVER);
        tmpShape.getGradient().setVisible(true);
        tmpShape.setLeft(6);
        tmpShape.getShadow().setColor(Color.GRAY);
        tmpShape.getShadow().setHorizSize(1);
        tmpShape.getShadow().setVertSize(1);
        tmpShape.setTop(8);       
        refreshButtonPane();
    }	
    
    private void refreshAnnotation() {
        int selBar = barSpinner.getSelection();

        annotationTool.setText(String.valueOf(selBar));
        annotationTool.getShape().setTop( chart1.getHeight() - 28);  //TODO Not visible under X-Axis!
        annotationTool.getShape().setLeft( barSeries.calcXPosValue( barSeries.getXValues().value[selBar])-8);
    }

    private void refreshButtonPane() {
        double tmpValue;
        int selBar = barSpinner.getSelection();
        tmpValue = barSeries.getXValues().getValue(selBar);
        positionSlider.setSelection( (int)Math.round( tmpValue * 100));
        positionLabel.setText(String.valueOf(tmpValue));

        tmpValue = barSeries.getYValues().getValue(selBar);
        sizeSlider.setSelection( (int)Math.round( tmpValue * 100));
        sizeLabel.setText(String.valueOf(tmpValue));

        refreshAnnotation();
    }    

    private Label positionLabel, sizeLabel;
    private Slider positionSlider, sizeSlider;
    private Spinner barSpinner;    	
}
