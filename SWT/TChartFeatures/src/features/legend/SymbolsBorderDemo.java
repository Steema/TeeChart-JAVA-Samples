/*
 * SymbolsBorderDemo.java
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
import com.steema.teechart.legend.Legend;
import com.steema.teechart.styles.Bar;
import com.steema.teechart.styles.Series;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class SymbolsBorderDemo extends ChartSample implements SelectionListener{

    //TODO private ButtonPen penButton;
    
	public SymbolsBorderDemo(Composite c) {
		super(c);
	}

	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
		if (source instanceof Button) {
			boolean isSelected = ((Button)source).getSelection();
	        if (source == useSeriesBorderButton) {
	            chart1.getLegend().getSymbol().setDefaultPen(isSelected);
	            //TODO penButton.setEnabled(!isSelected);
	        } else if (source == squaredSymbolsButton) {
	            chart1.getLegend().getSymbol().setSquared(isSelected);
	        }
		}
	}
	
	protected void createContent() {
		super.createContent();	

		useSeriesBorderButton = addCheckButton("Use series border", "", this);
        //TODO penButton = new ButtonPen(chart1.getLegend().getSymbol().getPen(), "Edit Border...");
		squaredSymbolsButton = addCheckButton("Squared symbols", "", this);
	}

	protected void initContent() {
		super.initContent();
		useSeriesBorderButton.setSelection(chart1.getLegend().getSymbol().getDefaultPen());
		squaredSymbolsButton.setSelection(chart1.getLegend().getSymbol().getSquared());
	}

	protected void initChart() {
		super.initChart();
        chart1.getHeader().setText("Custom Legend Symbol Border");
        chart1.getHeader().setVisible(true);
        Legend legend = chart1.getLegend();
        legend.getFont().setSize(19);
        legend.getGradient().setVisible(true);
        // Do not use series border to display legend symbols:
        legend.getSymbol().setDefaultPen(false);
        // Customize border:
        legend.getSymbol().getPen().setColor(Color.RED);
        legend.getSymbol().getPen().setWidth(2);       
        
        Series tmpSeries = new Bar(chart1.getChart());
        tmpSeries.getMarks().setArrowLength(20);
        tmpSeries.getMarks().getCallout().getBrush().setColor(Color.BLACK);
        tmpSeries.getMarks().getCallout().setLength(20);
        tmpSeries.getMarks().setVisible(true);
        chart1.getSeries().fillSampleValues(10);        
	}   
	
    private Button useSeriesBorderButton, squaredSymbolsButton;	
}
