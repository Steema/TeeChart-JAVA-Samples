/*
 * ADXDem.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.function;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.functions.ADX;
import com.steema.teechart.styles.Candle;
import com.steema.teechart.styles.Line;
import com.steema.teechart.styles.VerticalAxis;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class ADXDemo extends ChartSample implements ModifyListener {

	private Candle sourceSeries;
	private ADX adxFunction;

	public ADXDemo(Composite c) {
		super(c);
		periodSpinner.addModifyListener(this);
	}

	public void modifyText(ModifyEvent me) {
		Widget source = me.widget;
		if (source == periodSpinner) {
			int period = ((Spinner)source).getSelection();
			if ((period > 0) && (period < 101)) {
				adxFunction.setPeriod(period);				
			}               	
		}
	}		

	protected void createContent() {
		super.createContent();	
		addLabel(SWT.LEFT, "Period: ");
        periodSpinner = addSpinner(SWT.BORDER, 1, 100, 1); 		
	}

	protected void initContent() {
		super.initContent();
		periodSpinner.setSelection(10);
	}

	protected void initChart() {
		super.initChart();
        chart1.getHeader().setVisible(true);
        chart1.setText("ADX Function Example");
        chart1.getAspect().setView3D(false);
        
        sourceSeries = new com.steema.teechart.styles.Candle(chart1.getChart());
        sourceSeries.getMarks().setVisible(false);
        sourceSeries.getPointer().setVisible(true);
        sourceSeries.setTitle("Source");
        sourceSeries.setColor(Color.RED);
        sourceSeries.getLinePen().setColor(Color.RED);
        sourceSeries.getVertAxis().setStartPosition(0);
        sourceSeries.getVertAxis().setEndPosition(70);
        sourceSeries.fillSampleValues(100);

        adxFunction = new com.steema.teechart.functions.ADX(chart1.getChart());
        adxFunction.setPeriod(10);


        Line functionSeries = new com.steema.teechart.styles.Line(chart1.getChart());
        functionSeries.setTitle("ADX");
        functionSeries.setColor(Color.GREEN);
        functionSeries.getLinePen().setColor(Color.GREEN);
        functionSeries.getMarks().setVisible(false);
        functionSeries.getPointer().setVisible(true);
        functionSeries.setVerticalAxis(VerticalAxis.RIGHT);
        functionSeries.getVertAxis().setStartPosition(70);
        functionSeries.getVertAxis().setEndPosition(100);
        functionSeries.setDataSource(sourceSeries);
        functionSeries.setFunction(adxFunction);
	}   	

	private Spinner periodSpinner;
}
