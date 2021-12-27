/*
 * BaselineDemo.java
 *
 * <p>Copyright: (c) 2005-2008 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.style.points3d;

import org.eclipse.swt.widgets.Composite;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.PointerStyle;
import com.steema.teechart.styles.Points3D;

import features.ChartSample;


/**
 * @author tom
 *
 */
public class BaselineDemo extends ChartSample {

    private Points3D series;
    
	public BaselineDemo(Composite c) {
		super(c);
	}
	
    protected void createContent() {
    	super.createContent();
    	   	
        //TODO penButton = new ButtonPen(series.getBaseLine(), "Baseline...");
    }
    
    protected void initContent() {
    	super.initContent();
    		
    }
    
    protected void initChart() {
    	super.initChart();
        chart1.getHeader().setText("Point3D BaseLine");
        chart1.getHeader().setVisible(true);
        chart1.getWalls().getBack().setTransparent(false);
        chart1.getLegend().setVisible(false);
        chart1.getAspect().setChart3DPercent(30);
        
        series = new com.steema.teechart.styles.Points3D(chart1.getChart());
        series.getMarks().setVisible(false);
        series.getBaseLine().setVisible(true);
        series.getBaseLine().setColor(Color.NAVY);
        series.getLinePen().setVisible(false);
        series.getPointer().setInflateMargins(true);
        series.getPointer().setStyle(PointerStyle.RECTANGLE);
        series.getPointer().setVisible(true);
        series.fillSampleValues();   
    }   
    
    //TODO private ButtonPen penButton;    	
}
