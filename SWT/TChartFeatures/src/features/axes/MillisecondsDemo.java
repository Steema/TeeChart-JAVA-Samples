/*
 * MillisecondsDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.axes;

import org.eclipse.swt.widgets.Composite;

import com.steema.teechart.DateTime;
import com.steema.teechart.DateTimeStep;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.PointerStyle;
import com.steema.teechart.styles.Points;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class MillisecondsDemo extends ChartSample {

	public MillisecondsDemo(Composite c) {
		super(c);
	}
	
	protected void initContent() {
		super.initContent();
        getButtonPane().setVisible(false);
	}

	protected void initChart() {
		super.initChart();
        chart1.getAspect().setView3D(false);
        chart1.getLegend().setVisible(false);
        chart1.getPanel().setMarginRight(10.0);
        chart1.getAxes().getBottom().setIncrement(DateTimeStep.ONEMILLISECOND);
        chart1.getAxes().getBottom().getLabels().setDateTimeFormat("ss.SSS");
        chart1.getAxes().getBottom().getLabels().setRoundFirstLabel(false);
        
        Points series = new Points(chart1.getChart());
        series.getMarks().setVisible(false);
        series.getPointer().getBrush().setColor(Color.TEAL);
        series.getPointer().setHorizSize(7);
        series.getPointer().setVertSize(7);
        series.getPointer().setInflateMargins(true);
        series.getPointer().getPen().setColor(Color.WHITE);
        series.getPointer().setStyle(PointerStyle.TRIANGLE);
        series.getPointer().setVisible(true);
        series.getXValues().setDateTime(true);


        series.add( new DateTime(0), 100);
        series.add( new DateTime(1), 200);
        series.add( new DateTime(2), 100);
        series.add( new DateTime(3), 200);        
	}   
}
