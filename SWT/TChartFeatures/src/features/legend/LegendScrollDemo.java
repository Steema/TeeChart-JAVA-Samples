/*
 * LegendScrollDemo.java
 *
 * <p>Copyright: Copyright (c) 2004-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.legend;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Slider;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.styles.Line;
import com.steema.teechart.tools.ColorLine;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class LegendScrollDemo extends ChartSample implements SelectionListener {

    private ColorLine lineTool;
    private Line series;
    
	public LegendScrollDemo(Composite c) {
		super(c);
        scrollSlider.addSelectionListener(this);		
	}

	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
		if (source == scrollSlider) {
			int scroll = scrollSlider.getSelection();
            chart1.getLegend().setFirstValue(scroll);
            scrollLabel.setText(String.valueOf(scroll));
            lineTool.setValue(scroll);
        }
	}
	
	protected void createContent() {
		super.createContent();	

        scrollSlider = addSlider(SWT.HORIZONTAL , 0, 1, 1);
        scrollLabel = addLabel(SWT.LEFT, String.valueOf(chart1.getLegend().getFirstValue()));        
	}

	protected void initContent() {
		super.initContent();
		scrollSlider.setMaximum(series.getCount()-1);
		scrollSlider.setSelection(chart1.getLegend().getFirstValue());
	}

	protected void initChart() {
		super.initChart();
        chart1.getAspect().setChart3DPercent(20);
        
        series = new Line(chart1.getChart());
        series.fillSampleValues(100);

        lineTool = new ColorLine(chart1.getAxes().getBottom());
        lineTool.getPen().setWidth(2);
        lineTool.setValue(chart1.getLegend().getFirstValue());        
	}   
	
    private Label scrollLabel;
    private Slider scrollSlider;	
}
