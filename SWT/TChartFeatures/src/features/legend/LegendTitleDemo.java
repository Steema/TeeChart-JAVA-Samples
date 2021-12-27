/*
 * LegendTitleDemo.java
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
import com.steema.teechart.drawing.GradientDirection;
import com.steema.teechart.legend.Legend;
import com.steema.teechart.styles.Line;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class LegendTitleDemo extends ChartSample implements SelectionListener {

	public LegendTitleDemo(Composite c) {
		super(c);
	}

	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;
        if (source == legendButton) {
            //TODO ChartEditor.editLegend(chart1.getChart());
        } else if (source instanceof Button) {
			boolean isSelected = ((Button)source).getSelection();
	        if (source == showTitleButton) {
	            chart1.getLegend().getTitle().setVisible(isSelected);
	        }
		}
	}
	
	protected void createContent() {
		super.createContent();	
        showTitleButton = addCheckButton("Show legend title", "", this);
        legendButton = addPushButton("Legend title properties...", "", this);
	}

	protected void initContent() {
		super.initContent();
        showTitleButton.setSelection(chart1.getLegend().getTitle().getVisible());
        legendButton.setEnabled(false);
	}

	protected void initChart() {
		super.initChart();
        Legend legend = chart1.getLegend();
        legend.getGradient().setDirection(GradientDirection.HORIZONTAL);
        legend.getGradient().setStartColor(Color.GRAY);
        legend.getGradient().setMiddleColor(Color.WHITE);
        legend.getGradient().setEndColor(Color.GRAY);
        legend.getGradient().setVisible(true);
        legend.getTitle().setText("Values");
        legend.getTitle().setTransparent(false);
        legend.getTitle().getFont().setColor(Color.BLUE); 
        
        Line tmpSeries = new Line(chart1.getChart());
        tmpSeries.getPointer().setVisible(false);

        tmpSeries.fillSampleValues();        
	}   	
	
	private Button legendButton;
    private Button showTitleButton;	
}
