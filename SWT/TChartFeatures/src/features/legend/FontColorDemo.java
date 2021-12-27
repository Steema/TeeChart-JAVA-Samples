/*
 * FontColorDemo.java
 *
 * <p>Copyright: (c) 2004-2008 by Steema Software SL. All Rights Reserved.</p>
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
import com.steema.teechart.legend.LegendSymbolSize;
import com.steema.teechart.styles.Line;
import com.steema.teechart.styles.PointerStyle;
import com.steema.teechart.styles.Points;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class FontColorDemo extends ChartSample implements SelectionListener {

    private Points[] points;
    private Line[] line;
    
	public FontColorDemo(Composite c) {
		super(c);
	}

	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
		if (source instanceof Button) {
			boolean isSelected = ((Button)source).getSelection();
	        if (source == legendFontButton) {
	            Legend legend = chart1.getLegend();
	            legend.setFontSeriesColor(isSelected);
	            if (isSelected) {
	                legend.getFont().getShadow().setColor(Color.BLACK);
	            } else {
	                legend.getFont().getShadow().setColor(Color.WHITE);
	            }
	        } else if (source == multipleSeriesButton) {
	            line[0].setActive(isSelected);
	            line[1].setActive(isSelected);
	            points[1].setActive(isSelected);
	            points[0].setColorEach(!isSelected);
	        }
		}
	}
	
	protected void createContent() {
		super.createContent();	
		legendFontButton = addCheckButton("Legend Font Series Color", "", this);
		multipleSeriesButton = addCheckButton("Multiple Series", "", this);
	}

	protected void initContent() {
		super.initContent();
        legendFontButton.setSelection(chart1.getLegend().getFontSeriesColor());
        multipleSeriesButton.setSelection(!points[0].getColorEach());
	}

	protected void initChart() {
		super.initChart();
        Legend legend = chart1.getLegend();
        legend.setColorWidth(15);
        legend.getFont().setSize(16);
        legend.getFont().setBold(true);
        legend.getFont().setItalic(true);
        legend.getFont().getShadow().setColor(Color.BLACK);
        legend.getFont().getShadow().setHorizSize(1);
        legend.getFont().getShadow().setVertSize(1);
        legend.setFontSeriesColor(true);
        legend.getSymbol().setWidth(15);
        legend.getSymbol().setWidthUnits(LegendSymbolSize.PIXELS);
        legend.setTransparent(true);
        chart1.getAspect().setView3D(false);
        initSeries();
	}   	
	
    protected void initSeries() {
        points = new Points[2];
        points[0] = new Points(chart1.getChart());
        points[0].setColorEach(true);
        points[0].getMarks().setVisible(false);
        points[0].getPointer().setHorizSize(10);
        points[0].getPointer().setVertSize(10);
        points[0].getPointer().setStyle(PointerStyle.DIAMOND);
        points[0].getPointer().setVisible(true);

        points[1] = new Points(chart1.getChart());
        points[1].getMarks().setVisible(false);
        points[1].getPointer().setVisible(true);
        points[1].setActive(false);

        line = new Line[2];
        for (int t=0; t < line.length; t++) {
            line[t] = new Line(chart1.getChart());
            line[t].getPointer().setVisible(false);
            line[t].setActive(false);
        }
        chart1.getSeries().fillSampleValues(10);
    }	
    
    private Button legendFontButton, multipleSeriesButton;	
}
