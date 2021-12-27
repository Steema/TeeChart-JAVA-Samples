/*
 * CLVDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.function;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.axis.Axis;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.functions.CLV;
import com.steema.teechart.styles.Candle;
import com.steema.teechart.styles.Line;
import com.steema.teechart.styles.Volume;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class CLVDemo extends ChartSample implements SelectionListener {

	private Candle candleSeries;
	private Volume volumeSeries;
	private CLV function;

	public CLVDemo(Composite c) {
		super(c);
	}

	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;

		boolean isSelected = ((Button)source).getSelection();
		if (source == accumulateButton) {
			function.setAccumulate(isSelected);
		}
		else if (source == multiplyButton)
		{
			if (isSelected) function.setVolume(volumeSeries);
			else function.setVolume(null);
		}

	}	

	protected void createContent() {
		super.createContent();	
		accumulateButton = addCheckButton("Accumulate", "", this);
		multiplyButton = addCheckButton("Multiplay", "", this);		
	}

	protected void initContent() {
		super.initContent();
		accumulateButton.setSelection(true);
		multiplyButton.setSelection(true);		
	}

	protected void initChart() {
		super.initChart();
        chart1.getHeader().setVisible(true);
        chart1.setText("CLV Function Example");
        chart1.getAspect().setView3D(false);
        
        candleSeries = new com.steema.teechart.styles.Candle(chart1.getChart());
        candleSeries.getMarks().setVisible(false);
        candleSeries.getPointer().setVisible(true);
        candleSeries.setTitle("Candle");
        candleSeries.getVertAxis().setStartPosition(0);
        candleSeries.getVertAxis().setEndPosition(40);
        candleSeries.getVertAxis().getTitle().setCaption("Candle");
        candleSeries.fillSampleValues(50);
        
        Axis tmpAxis;
        tmpAxis = chart1.getAxes().getCustom().getNew();
        tmpAxis.setHorizontal(false);
        tmpAxis.setOtherSide(false);
        tmpAxis.setStartPosition(50.0);
        tmpAxis.setEndPosition(70);
        tmpAxis.getTitle().setText("Volume");
        tmpAxis.getTitle().setAngle(90);
        volumeSeries = new com.steema.teechart.styles.Volume(chart1.getChart());
        volumeSeries.getPointer().setVisible(true);
        volumeSeries.setTitle("Volume");
        volumeSeries.setDataSource(candleSeries);
        volumeSeries.setCustomVertAxis(tmpAxis);

        function = new com.steema.teechart.functions.CLV(chart1.getChart());
        function.setAccumulate(true);
        function.setVolume(volumeSeries);

        Line functionSeries = new com.steema.teechart.styles.Line(chart1.getChart());
        functionSeries.setDataSource(candleSeries);
        functionSeries.setFunction(function);
        functionSeries.setTitle("CLV");
        functionSeries.setColor(Color.GREEN);
        tmpAxis = chart1.getAxes().getCustom().getNew();
        tmpAxis.getTitle().setText("CLV");
        tmpAxis.getTitle().setAngle(90);
        tmpAxis.setHorizontal(false);
        tmpAxis.setOtherSide(false);
        tmpAxis.setStartPosition(75.0);
        tmpAxis.setEndPosition(100);
        functionSeries.setCustomVertAxis(tmpAxis);
	}   	

    private Button accumulateButton, multiplyButton;	
}
