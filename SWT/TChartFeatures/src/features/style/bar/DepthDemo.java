/*
 * DepthDemo.java
 *
 * <p>Copyright: (c) 2005-2008 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 * 
 */

package features.style.bar;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Slider;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.Bar;
import com.steema.teechart.styles.MultiBars;
import com.steema.teechart.tools.Rotate;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class DepthDemo extends ChartSample implements SelectionListener {

    private Bar[] barSeries;
    
	public DepthDemo(Composite c) {
		super(c);
		depthSlider.addSelectionListener(this);
		widthSlider.addSelectionListener(this);		
	}
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;

        if (source instanceof Slider) {
           Slider tmpSlider = (Slider)source;
           int tmpValue = tmpSlider.getSelection();
           if (tmpSlider == depthSlider) {
               for (int t=0; t < chart1.getSeriesCount(); t++) {
                   ((Bar)chart1.getSeries(t)).setDepthPercent(tmpValue);
               }
           } else  if (tmpSlider == widthSlider) {
               for (int t=0; t < chart1.getSeriesCount(); t++) {
                   ((Bar)chart1.getSeries(t)).setBarWidthPercent(tmpValue);
               }
           }
        }
	}	
	
    protected void createContent() {
    	super.createContent();
    	   	
        depthSlider = addSlider(SWT.HORIZONTAL, 1, 100, 40);
        widthSlider = addSlider(SWT.HORIZONTAL, 1, 100, 40);    	
    }
    
    protected void initChart() {
    	super.initChart();
        chart1.getWalls().getBack().setColor(Color.RED);
        chart1.getWalls().getBack().setSize(7);
        chart1.getWalls().getBack().setTransparent(false);
        chart1.getWalls().getBottom().setSize(7);
        chart1.getWalls().getLeft().setColor(Color.PINK);
        chart1.getWalls().getLeft().setSize(7);
        chart1.getWalls().getRight().setSize(7);

        chart1.getAspect().setChart3DPercent(85);
        chart1.getAspect().setElevation(342);
        chart1.getAspect().setOrthogonal(false);
        chart1.getAspect().setPerspective(92);
        chart1.getAspect().setRotation(317);
        chart1.getAspect().setZoom(61);
        
        barSeries = new Bar[5];
        for (int t=0; t<barSeries.length; t++) {
            barSeries[t] = new Bar(chart1.getChart());
            barSeries[t].setDepthPercent(40);
            barSeries[t].setBarWidthPercent(40);
            barSeries[t].getMarks().setVisible(false);
            barSeries[t].setMultiBar(MultiBars.NONE);
        }

        Rotate rotateTool = new Rotate(chart1.getChart());
        rotateTool.getPen().setColor(Color.WHITE);

        chart1.getSeries().fillSampleValues();        
    }   	

    private Slider depthSlider, widthSlider;    
}
