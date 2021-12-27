/*
 * OBVDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.function;

import org.eclipse.swt.widgets.Composite;

import com.steema.teechart.axis.Axis;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.functions.OBV;
import com.steema.teechart.styles.Candle;
import com.steema.teechart.styles.Line;
import com.steema.teechart.styles.Volume;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class OBVDemo extends ChartSample {

	private Candle candleSeries;
	private Volume volumeSeries;
	private OBV function;

	public OBVDemo(Composite c) {
		super(c);
	}

	protected void createContent() {
		super.createContent();
	}

	protected void initContent() {
		super.initContent();
		getButtonPane().setVisible(false);
	}

	protected void initChart() {
		super.initChart();
		chart1.getHeader().setVisible(true);
		chart1.setText("OBV Function Example");
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

		function = new com.steema.teechart.functions.OBV(chart1.getChart());
		function.setVolume(volumeSeries);

		Line functionSeries = new com.steema.teechart.styles.Line(chart1.getChart());
		functionSeries.setDataSource(candleSeries);
		functionSeries.setFunction(function);
		functionSeries.setTitle("OBV");
		functionSeries.setColor(Color.GREEN);
		tmpAxis = chart1.getAxes().getCustom().getNew();
		tmpAxis.getTitle().setText("OBV");
		tmpAxis.getTitle().setAngle(90);
		tmpAxis.setHorizontal(false);
		tmpAxis.setOtherSide(false);
		tmpAxis.setStartPosition(75.0);
		tmpAxis.setEndPosition(100);
		functionSeries.setCustomVertAxis(tmpAxis);    
	}   			
}
