/*
 * TrendDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.function.trend;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.functions.Function;
import com.steema.teechart.styles.Area;
import com.steema.teechart.styles.Line;
import com.steema.teechart.styles.MarksStyle;
import com.steema.teechart.styles.VerticalAxis;
import com.steema.teechart.tools.MarksTip;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class TrendDemo extends ChartSample implements SelectionListener {

    private Area sourceSeries;
    private Function trendFunction;
    
	public TrendDemo(Composite c) {
		super(c);
	}

	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
        if (source == randomButton) {
            sourceSeries.fillSampleValues(20);
        }
	}

	protected void createContent() {
		super.createContent();	
        randomButton = addPushButton("Random!", "", this);
	}
	
	protected void initChart() {
		super.initChart();
        chart1.getHeader().setVisible(true);
        chart1.setText("Trend Function Example");
        
        sourceSeries = new com.steema.teechart.styles.Area(chart1.getChart());
        sourceSeries.getMarks().setVisible(false);
        sourceSeries.getPointer().setVisible(false);
        sourceSeries.setTitle("Source");
        sourceSeries.setColor(Color.RED);
        sourceSeries.fillSampleValues(20);

        trendFunction = new com.steema.teechart.functions.Trend(chart1.getChart());
        trendFunction.setPeriod(0);

        Line functionSeries = new com.steema.teechart.styles.Line(chart1.getChart());
        functionSeries.setDataSource(sourceSeries);
        functionSeries.setFunction(trendFunction);
        functionSeries.setTitle("Trend");
        functionSeries.setColor(Color.GREEN);
        functionSeries.getMarks().setVisible(false);
        functionSeries.getPointer().setVisible(false);
        functionSeries.setVerticalAxis(VerticalAxis.RIGHT);

        MarksTip tmpTool = new MarksTip(chart1.getChart());
        tmpTool.setSeries(sourceSeries);
        tmpTool.setStyle(MarksStyle.XY);     
	}   	

    private Button randomButton;	
}
