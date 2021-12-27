/*
 * SARDemo.java
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
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.BevelStyle;
import com.steema.teechart.axis.Axis;
import com.steema.teechart.drawing.ChartFont;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.DashStyle;
import com.steema.teechart.functions.SAR;
import com.steema.teechart.styles.Candle;
import com.steema.teechart.styles.Line;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class SARDemo extends ChartSample implements ModifyListener {

    private Candle series;
    private SAR function;

	public SARDemo(Composite c) {
		super(c);
		accFactor.addModifyListener(this);        
		maxStep.addModifyListener(this);		
	}

	public void modifyText(ModifyEvent me) {
		Widget source = me.widget;
        double tmpValue=-1;		
        try {
			tmpValue = Double.parseDouble(((Text)source).getText());
			if (source == accFactor) {
				 function.SetAF(tmpValue);	
			} else if (source == maxStep) {
				function.SetMS(tmpValue);
			}			
		} catch (NumberFormatException e) {
		}		
	}	


	protected void createContent() {
		super.createContent();
		addLabel(SWT.LEFT, "Acceleration factor: ");
		accFactor = addText(SWT.SINGLE | SWT.BORDER, Double.toString(0.02)); 

		addLabel(SWT.LEFT, "Maximum step: ");
		maxStep = addText(SWT.SINGLE | SWT.BORDER, Double.toString(0.2));
	}

	protected void initChart() {
		super.initChart();
        chart1.getAspect().setView3D(false);
        chart1.getAspect().setChart3DPercent(15);
        chart1.getPanel().setBevelOuter(BevelStyle.NONE);
        chart1.getPanel().setColor(Color.SILVER);
        chart1.getWalls().getBack().setColor(Color.WHITE);
        chart1.getWalls().getBack().setTransparent(false);
        chart1.getWalls().getLeft().setColor(Color.WHITE);
        chart1.getWalls().getRight().setColor(Color.WHITE);
        chart1.getLegend().getFont().setSize(12);
        chart1.getLegend().getFont().setName("Lucida Console");
        chart1.getLegend().getShadow().setColor(Color.GRAY);
        chart1.setText("SAR function");
        ChartFont font = chart1.getHeader().getFont();
        font.setColor(Color.BLACK);
        font.setSize(13);
        font.setName("Lucida Console");
        font.setBold(true);

        Axis axis;
        axis = chart1.getAxes().getBottom();
        axis.getGrid().setVisible(false);
        axis.getLabels().getFont().setName("Lucida Console");
        axis.getMinorTicks().setLength(3);
        axis.getMinorTicks().setColor(Color.BLACK);
        axis.getTicksInner().setLength(6);
        axis.getTicksInner().setColor(Color.BLACK);
        axis.getTicks().setLength(0);

        axis = chart1.getAxes().getLeft();
        axis.getGrid().setColor(Color.SILVER);
        axis.getGrid().setStyle(DashStyle.SOLID);
        axis.getLabels().getFont().setName("Lucida Console");
        axis.getMinorTicks().setLength(3);
        axis.getMinorTicks().setColor(Color.BLACK);
        axis.getTicksInner().setLength(6);
        axis.getTicksInner().setColor(Color.BLACK);
        axis.getTicks().setLength(0);
        
        series = new Candle(chart1.getChart());
        series.setTitle("Source");
        series.fillSampleValues(10);

        function = new com.steema.teechart.functions.SAR();
        function.setChart(chart1.getChart());
        function.setPeriod(0); //all points

        Line functionSeries = new Line(chart1.getChart());
        functionSeries.getMarks().setVisible(true);
        functionSeries.getMarks().setArrowLength(2);
        functionSeries.getMarks().setTransparent(true);
        functionSeries.setTitle("SAR");
        functionSeries.setDataSource(series);
        functionSeries.setFunction(function);        
	}   	

    private Text accFactor, maxStep;	
}
