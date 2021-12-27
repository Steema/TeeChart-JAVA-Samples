/*
 * Vector3DDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.style.vector;

import org.eclipse.swt.widgets.Composite;

import com.steema.teechart.styles.Vector3D;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class Vector3DDemo extends ChartSample {

    private Vector3D series;
    
	public Vector3DDemo(Composite c) {
		super(c);
	}
	
    protected void createContent() {
    	super.createContent();
    	//TODO
        //penButton = new ButtonPen(series.getPen()); 
    }
    
    protected void initContent() {
    	super.initContent();
    	//TODO
    }
    
    protected void initChart() {
    	super.initChart();
        chart1.getHeader().setVisible(true);
        chart1.setText("3D Vectors");
        chart1.getLegend().setVisible(false);
        chart1.getWalls().getBottom().setTransparent(true);
        chart1.getWalls().getLeft().setTransparent(true);
        chart1.getWalls().getRight().setTransparent(true);
        chart1.getWalls().getRight().setVisible(true);
        chart1.getAxes().getRight().setZPosition(0.01);

        chart1.getAspect().setChart3DPercent(55);
        chart1.getAspect().setOrthogonal(false);
        chart1.getAspect().setPerspective(92);
        chart1.getAspect().setRotation(358);
        chart1.getAspect().setZoom(84);
        

        series = new Vector3D(chart1.getChart());
        series.setUsePalette(true);
        series.fillSampleValues();
    }   	
    
	//TODO private ButtonPen penButton;	
}
