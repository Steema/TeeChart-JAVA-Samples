/*
 * SideAllDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.legend;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.legend.LegendStyle;
import com.steema.teechart.styles.Bar;
import com.steema.teechart.styles.Line;
import com.steema.teechart.styles.Series;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class SeriesPropertyDemo extends ChartSample implements SelectionListener {

    private Series series1, series2;
	
	public SeriesPropertyDemo(Composite c) {
		super(c);
	}

	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
        if (source == barButton) {
            chart1.getLegend().setSeries(series1);
        } else if (source == lineButton) {
            chart1.getLegend().setSeries(series2);
		}
	}
	
	protected void createContent() {
		super.createContent();	

		addLabel(SWT.LEFT, "Use series: ");
        barButton = addRadioButton("Bar", "", this);
        lineButton = addRadioButton("Line", "", this);
	}

	protected void initContent() {
		super.initContent();
		barButton.setSelection(true);
	}

	protected void initChart() {
		super.initChart();
        chart1.getLegend().setColorWidth(30);
        chart1.getLegend().setLegendStyle(LegendStyle.VALUES);
        chart1.getLegend().getSymbol().setWidth(30);
        chart1.getAspect().setView3D(false);
        initSeries();
	}   	
	
    protected void initSeries() {
        series1 = new Bar(chart1.getChart());
        series1.getMarks().setArrowLength(20);
        series1.getMarks().setVisible(true);

        series2 = new Line(chart1.getChart());
        series2.getMarks().setArrowLength(8);
        series2.getMarks().setVisible(true);
        ((Line)series2).getPointer().setInflateMargins(false);
        ((Line)series2).getPointer().setVisible(true);

        series1.fillSampleValues(5);
        series2.fillSampleValues(8);
    }
    
    private Button barButton, lineButton;    
}
