/*
 * GradientDemo.java
 *
 * <p>Copyright: (c) 2005-2008 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.style.polar;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.DashStyle;
import com.steema.teechart.drawing.GradientDirection;
import com.steema.teechart.styles.Polar;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class GradientDemo extends ChartSample implements SelectionListener {

    private Polar series;
    
	public GradientDemo(Composite c) {
		super(c);
	}

	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
        if (source instanceof Button) {
            if (source == gradientButton) {
            	//TODO
            } 
        };
	}	
	
	protected void createContent() {
		super.createContent();	
		gradientButton = addPushButton("Edit gradient...", "", this);
	}

	protected void initContent() {
		super.initContent();
        gradientButton.setEnabled(false);
	}

	protected void initChart() {
		super.initChart();
        chart1.getLegend().setVisible(false);
        chart1.getAspect().setView3D(false);
        chart1.getAxes().getBottom().setIncrement(10.0);
        
        series = new com.steema.teechart.styles.Polar(chart1.getChart());
        series.fillSampleValues(20);
        series.setCircled(true);
        series.getCircleGradient().setDirection(GradientDirection.RADIAL);
        series.getCircleGradient().setStartColor(Color.WHITE);
        series.getCircleGradient().setEndColor(Color.DARK_GRAY);
        series.getCircleGradient().setRadialX(100);
        series.getCircleGradient().setRadialY(-100);
        series.getCircleGradient().setVisible(true);
        series.getCirclePen().setColor(Color.NAVY);
        series.getCirclePen().setStyle(DashStyle.DOT);
        series.getCirclePen().setWidth(2);

        series.getBrush().setColor(Color.WHITE);
        series.getBrush().setVisible(false);
        series.getPen().setColor(Color.RED);      
	}  
	
	private Button gradientButton;	
}
