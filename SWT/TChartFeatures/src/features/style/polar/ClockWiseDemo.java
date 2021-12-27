/*
 * ClockWiseDemo.java
 *
 * <p>Copyright: (c) 2005-2008 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.polar;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.drawing.ChartFont;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.DashStyle;
import com.steema.teechart.drawing.GradientDirection;
import com.steema.teechart.styles.PointerStyle;
import com.steema.teechart.styles.Polar;
import com.steema.teechart.styles.SeriesPointer;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class ClockWiseDemo extends ChartSample implements SelectionListener {

    private Polar series;
    
	public ClockWiseDemo(Composite c) {
		super(c);
	}

	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
        if (source instanceof Button) {
        	boolean isSelected = ((Button)source).getSelection();
            if (source == clockwiseButton) {
               series.setClockWiseLabels(isSelected);
            } 
        };
	}	
	
	protected void createContent() {
		super.createContent();	
		clockwiseButton = addCheckButton("Clockwise Labels", "", this);
	}

	protected void initContent() {
		super.initContent();
        clockwiseButton.setSelection(series.getClockWiseLabels());
	}

	protected void initChart() {
		super.initChart();
        chart1.getPanel().getGradient().setEndColor(Color.SILVER);
        chart1.getPanel().getGradient().setStartColor(Color.TEAL);
        chart1.getPanel().getGradient().setDirection(GradientDirection.HORIZONTAL);
        chart1.getPanel().getGradient().setVisible(true);
        chart1.getLegend().setColorWidth(30);
        chart1.getLegend().getSymbol().setWidth(30);
        chart1.getAxes().getBottom().setIncrement(30);
        chart1.getAspect().setView3D(false);
        
        series = new com.steema.teechart.styles.Polar(chart1.getChart());
        series.fillSampleValues(20);
        series.setCircleLabels(true);
        series.setClockWiseLabels(true);
        series.setCircled(true);

        ChartFont tmpFont = series.getCircleLabelsFont();
        tmpFont.setColor(Color.YELLOW);
        tmpFont.setSize(12);
        tmpFont.setItalic(true);

        series.getCirclePen().setColor(Color.BLUE);
        series.getCirclePen().setWidth(2);

        series.getPen().setColor(Color.RED);
        series.getPen().setWidth(2);
        series.getPen().setStyle(DashStyle.SOLID);

        series.getBrush().setColor(Color.WHITE);
        series.getBrush().setVisible(false);

        SeriesPointer tmpPointer = series.getPointer();
        tmpPointer.getBrush().setColor(Color.LIME);
        tmpPointer.setHorizSize(2);
        tmpPointer.setInflateMargins(true);
        tmpPointer.setStyle(PointerStyle.RECTANGLE);
        tmpPointer.setVertSize(2);
        tmpPointer.setVisible(true);        
	}  
	
	private Button clockwiseButton;	
}
