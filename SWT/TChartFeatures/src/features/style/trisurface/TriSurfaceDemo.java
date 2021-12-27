/*
 * TriSurfaceDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.style.trisurface;

import org.eclipse.swt.widgets.Composite;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.DashStyle;
import com.steema.teechart.styles.TriSurface;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class TriSurfaceDemo extends ChartSample {

    private TriSurface series;
    
	public TriSurfaceDemo(Composite c) {
		super(c);
	}

    protected void createContent() {
    	super.createContent();
    	//TODO 
        //borderButton = new ButtonPen(series.getOutline(), "Border...");
        //penButton = new ButtonPen(series.getPen()); 
    }
    
    protected void initContent() {
    	super.initContent();
    	//TODO
    }
    
    protected void initChart() {
    	super.initChart();
        chart1.getAxes().getDepth().setVisible(true);

        chart1.getWalls().getBack().setSize(10);
        chart1.getWalls().getBottom().setSize(10);
        chart1.getWalls().getLeft().setSize(10);
        chart1.getLegend().setVisible(false);

        chart1.getAspect().setChart3DPercent(70);
        chart1.getAspect().setElevation(334);
        chart1.getAspect().setOrthogonal(false);
        chart1.getAspect().setPerspective(35);
        chart1.getAspect().setZoom(60);
        
        series = new TriSurface(chart1.getChart());

        series.fillSampleValues(30);

        series.getOutline().setColor(Color.RED);
        series.getOutline().setWidth(2);
        series.getOutline().setVisible(true);
        series.getBrush().setColor(Color.WHITE);
        series.getPen().setStyle(DashStyle.DOT);
        series.setEndColor(Color.LIME);   
    }   	
    
	//TODO private ButtonPen penButton, borderButton;	
}
