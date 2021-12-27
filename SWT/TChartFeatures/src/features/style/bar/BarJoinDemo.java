/*
 * BarJoinDemo.java
 *
 * <p>Copyright: (c) 2005-2008 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.bar;

import org.eclipse.swt.widgets.Composite;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.BarJoin;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class BarJoinDemo extends ChartSample {

    private BarJoin series;
    //TODO private ButtonPen penButton;
    
	public BarJoinDemo(Composite c) {
		super(c);
	}
	
    protected void createContent() {
    	super.createContent();
    	   	
        //TODO penButton = new ButtonPen(series.getJoinPen(), "Join...");  	
    }
    
    protected void initChart() {
    	super.initChart();
        chart1.getAspect().setView3D(false);
        series = new BarJoin(chart1.getChart());
        series.fillSampleValues(3);
        series.getJoinPen().setWidth(2);
        series.setColor(Color.RED);
    }   				
}
