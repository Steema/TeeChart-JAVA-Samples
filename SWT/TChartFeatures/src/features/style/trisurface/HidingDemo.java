/*
 * HidingDemo.java
 *
 * <p>Copyright: (c) 2005-2008 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.style.trisurface;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.misc.MathUtils;
import com.steema.teechart.styles.PaletteStyle;
import com.steema.teechart.styles.TriSurface;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class HidingDemo extends ChartSample implements SelectionListener {
	
    private TriSurface series;
	
	public HidingDemo(Composite c) {
		super(c);
	}
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;	
        if (source instanceof Button) {        	
            boolean isSelected = ((Button)source).getSelection();
            if (source == hideTrianglesButton) {
                series.setHideTriangles(isSelected);
            } else if (source == cacheTrianglesButton) {
                series.setCacheTriangles(isSelected);
                series.invalidate();
            }
        }
	}		
	
    protected void createContent() {
    	super.createContent();
    	   	
    	hideTrianglesButton = addCheckButton("Hide Triangles", "", this);    	
    	cacheTrianglesButton = addCheckButton("Cache Triangles", "", this);    
    }
    
    protected void initContent() {
    	super.initContent();
    	
        hideTrianglesButton.setSelection(series.getHideTriangles());
        cacheTrianglesButton.setSelection(series.getCacheTriangles());    }
    
    protected void initChart() {
    	super.initChart();
        chart1.getAxes().getDepth().setVisible(true);
        chart1.getLegend().setVisible(false);
        chart1.getWalls().getBottom().setTransparent(true);
        chart1.getWalls().getLeft().setTransparent(true);
        chart1.getPanel().getGradient().setEndColor(Color.GRAY);
        chart1.getPanel().getGradient().setStartColor(Color.WHITE);
        chart1.getPanel().getGradient().setVisible(true);

        chart1.getAspect().setChart3DPercent(75);
        chart1.getAspect().setElevation(339);
        chart1.getAspect().setOrthogonal(false);
        chart1.getAspect().setPerspective(72);
        chart1.getAspect().setRotation(319);
        chart1.getAspect().setZoom(70);
        
        series = new TriSurface(chart1.getChart());
        series.setHideTriangles(true);
        series.setCacheTriangles(true);
        series.getPen().setVisible(false);
        series.setPaletteStyle(PaletteStyle.STRONG);
        series.setEndColor(Color.SKY_BLUE);
        series.setMidColor(Color.SILVER);
        
        fillSeries();        
    }   	

    private void fillSeries() {
        series.clear();
        double n = 0.5;
        int m = 10;
        for (int x=-m; x<=m; x++) {
            for (int z=-m; z<=m; z++) {
                series.add(
                    x,
                    4*Math.cos(3*Math.sqrt(MathUtils.sqr(x/3)+MathUtils.sqr(z/3)))*Math.exp(-n*(Math.sqrt(MathUtils.sqr(x/3)+MathUtils.sqr(z/3)))),
                    z
                );
            }
        }
    }
    
    private Button hideTrianglesButton, cacheTrianglesButton;   
}
