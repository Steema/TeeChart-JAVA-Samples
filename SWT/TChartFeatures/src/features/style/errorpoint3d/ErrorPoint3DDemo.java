/*
 * ErrorPoint3DDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.style.errorpoint3d;

import org.eclipse.swt.widgets.Composite;

import com.steema.teechart.styles.ErrorPoint3D;
import com.steema.teechart.themes.Theme;

import features.ChartSample;

/**
 * @author yeray
 *
 */
public class ErrorPoint3DDemo extends ChartSample {

    private ErrorPoint3D series;
    
	public ErrorPoint3DDemo(Composite c) {
		super(c);

	}
   
    protected void initContent() {
    	super.initContent();
   
    }
    
    protected void initChart() {
    	super.initChart();

        chart1.getAspect().setOrthogonal(false);
        chart1.getAspect().setChart3DPercent(70);
        chart1.getAspect().setZoom(80);
        chart1.getLegend().setVisible(false);
        chart1.getAxes().getDepth().setVisible(true);
        chart1.getAxes().getDepth().setIncrement(2);
        
        series = new ErrorPoint3D(chart1.getChart());
        series.fillSampleValues(10);
        series.setColorEach(true);
        Theme theme = new com.steema.teechart.themes.BlackIsBackTheme(chart1.getChart());
        theme.apply();
    }   	
	

}
