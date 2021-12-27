/*
 * TernaryDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.style.ternary;

import org.eclipse.swt.widgets.Composite;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.Ternary.TernaryMarkStyle;

import com.steema.teechart.styles.PointerStyle;
import com.steema.teechart.styles.Ternary;
import com.steema.teechart.styles.Ternary.TernaryLegendStyle;
import com.steema.teechart.styles.Ternary.TernaryStyle;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class TernaryDemo extends ChartSample {

    private Ternary series;
    
	public TernaryDemo(Composite c) {
		super(c);

	}
   
    protected void initContent() {
    	super.initContent();
    	
        chart1.getHeader().setText("Ternary Series Example");
        
    }
    
    protected void initChart() {
    	super.initChart();
        series = new Ternary(chart1.getChart());
        series.fillSampleValues(10);
        series.setMarkStyle(TernaryMarkStyle.LongLabelled);
        series.setTernaryStyle(TernaryStyle.Bubble);
        series.getPointer().setTransparency(20);
        series.setTernaryLegendStyle(TernaryLegendStyle.BubbleWeight);
        chart1.getLegend().getTitle().setText("Radius" + ", " + "Weights");
        series.getMarks().setMultiLine(true);
        series.getPointer().setStyle(PointerStyle.CIRCLE);
        series.setEndColor(Color.GREEN); //for use when range palette active (ColorEach false)
        chart1.getLegend().getTitle().setVisible(true);
    }   	
	

}
