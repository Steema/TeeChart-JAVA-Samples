/*
 * VarianceDemo.java
 *
 * <p>Copyright: (c) 2005-2008 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.function;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.BevelStyle;
import com.steema.teechart.axis.Axis;
import com.steema.teechart.drawing.ChartFont;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.DashStyle;
import com.steema.teechart.functions.Variance;
import com.steema.teechart.styles.Line;
import com.steema.teechart.styles.Points;
import com.steema.teechart.styles.VerticalAxis;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class VarianceDemo extends ChartSample implements SelectionListener {

    private Points series;
    private Variance function;

	public VarianceDemo(Composite c) {
		super(c);
	}

	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;	
		if (source == randomButton) {
	        series.fillSampleValues();
		}; 	
	}	

	protected void createContent() {
		super.createContent();            	   	
		randomButton = addPushButton("New random values", "", this);	
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
        chart1.setText("Variance function");
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

        axis = chart1.getAxes().getRight();
        axis.getGrid().setColor(Color.SILVER);
        axis.getGrid().setStyle(DashStyle.SOLID);
        axis.getLabels().getFont().setName("Lucida Console");
        axis.getMinorTicks().setLength(3);
        axis.getMinorTicks().setColor(Color.BLACK);
        axis.getTicksInner().setLength(6);
        axis.getTicksInner().setColor(Color.BLACK);
        axis.getTicks().setLength(0);
		
        series = new Points(chart1.getChart());
        series.fillSampleValues();

        function = new com.steema.teechart.functions.Variance();
        function.setChart(chart1.getChart());
        function.setPeriod(0); //all points

        Line functionSeries = new Line(chart1.getChart());
        functionSeries.setTitle("Variance");
        functionSeries.setDataSource(series);
        functionSeries.setVerticalAxis(VerticalAxis.RIGHT);
        functionSeries.setFunction(function);
	} 

	private Button randomButton;    
}
