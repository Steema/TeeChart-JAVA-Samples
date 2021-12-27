/*
 * MapTransparencyDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.style.map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Slider;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.styles.Map;
import com.steema.teechart.styles.PaletteStyle;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class MapTransparencyDemo extends ChartSample implements SelectionListener {

    private Map series;	
	
	public MapTransparencyDemo(Composite c) {
		super(c);
		transparencySlider.addSelectionListener(this);
	}

	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {
		Widget source = se.widget;		
        if (source instanceof Slider) {
            Slider tmpSlider = (Slider)source;
            int tmpValue = tmpSlider.getSelection();
            if (tmpSlider == transparencySlider) {
                series.setTransparency(tmpValue);
            } 
         }
	}
		
    protected void createContent() {
    	super.createContent();    	   	
    	
    	addLabel(SWT.LEFT, "Transparency: ");
    	transparencySlider = addSlider(SWT.HORIZONTAL, 0, 100, 0);
    }
    
    protected void initContent() {
    	super.initContent();    
              
    }
    
    protected void initChart() {
    	super.initChart();
        chart1.setText("Map Series");
        chart1.getHeader().setVisible(true);
        chart1.getFrame().setVisible(false);
        chart1.getAspect().setView3D(false);    	
    	chart1.getAspect().setSmoothingMode(true);                
        initSeries();
    }   	
    
    protected void initSeries() {
        series = new Map(chart1.getChart());
        series.fillSampleValues();

        /* Set the color palette "strong" */
        series.setPaletteStyle(PaletteStyle.STRONG);
        series.setUsePalette(true);
        series.setUseColorRange(false);
    };    

    private Slider transparencySlider;    
	
}
