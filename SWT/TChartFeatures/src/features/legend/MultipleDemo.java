/*
 * MultipleDemo.java
 *
 * <p>Copyright: Copyright (c) 2004-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.legend;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.events.ChartDrawEvent;
import com.steema.teechart.events.ChartPaintAdapter;
import com.steema.teechart.legend.LegendStyle;
import com.steema.teechart.styles.Bar;
import com.steema.teechart.styles.Series;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class MultipleDemo extends ChartSample implements SelectionListener {

	public MultipleDemo(Composite c) {
		super(c);
        chart1.addChartPaintListener( new ChartPaintAdapter() {

            public void chartPainted(ChartDrawEvent pce) {
                if (show2ndLegendButton.getSelection()) {
                    if (chart1.getSeriesCount()>1) {
                        chart1.getLegend().setTop(125);
                        chart1.getLegend().setSeries(chart1.getSeries(1));
                        chart1.getLegend().paint();
                        chart1.getLegend().setCustomPosition(false);
                        chart1.getLegend().setSeries(chart1.getSeries(0));
                    }
                }
            };
        });		
	}

	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
		if (source instanceof Button) {
	        if (source == show2ndLegendButton) {
	            chart1.refreshControl();
	        }
		}
	}
	
	protected void createContent() {
		super.createContent();	

        show2ndLegendButton = addCheckButton("Show 2nd legend", "", this);
	}

	protected void initContent() {
		super.initContent();
        show2ndLegendButton.setSelection(true);
	}

	protected void initChart() {
		super.initChart();

        chart1.getLegend().setLegendStyle(LegendStyle.VALUES);
        
        Series tmpSeries;
        for (int t=0; t<2; t++) {
            tmpSeries = new Bar(chart1.getChart());
            tmpSeries.getMarks().getCallout().getBrush().setColor(Color.BLACK);
            tmpSeries.getMarks().setVisible(true);
        }
        chart1.getSeries().fillSampleValues(4);
        chart1.getLegend().setSeries(chart1.getSeries(0));        
	}

    private Button show2ndLegendButton;	
}
