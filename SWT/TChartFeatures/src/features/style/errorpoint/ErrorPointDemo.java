/*
 * ErrorPointDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.style.errorpoint;

import org.eclipse.swt.widgets.Composite;

import com.steema.teechart.styles.ErrorPoint;
import com.steema.teechart.themes.BlackIsBackTheme;
import com.steema.teechart.themes.Theme;

import features.ChartSample;

/**
 * @author yeray
 *
 */
public class ErrorPointDemo extends ChartSample {

	private ErrorPoint series;
	
	public ErrorPointDemo(Composite c) {
		super(c);

	}
   
    protected void initContent() {
    	super.initContent();
    	
    }
    
    protected void initChart() {
    	super.initChart();

        chart1.getAspect().setView3D(false);
        series = new ErrorPoint(chart1.getChart());
        series.fillSampleValues(10);
        series.setColorEach(true);
        Theme theme = new BlackIsBackTheme(chart1.getChart());
        theme.apply();
    }   	
	

}
