/*
 * ImageDemo.java
 *
 * <p>Copyright: (c) 2005-2008 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.style.bar;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.ImageMode;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.Bar;

import features.ChartSample;
import features.WidgetFactory;

/**
 * @author tom
 *
 */
public class ImageDemo extends ChartSample implements SelectionListener {
	
    private Bar series1, series2;	

	public ImageDemo(Composite c) {
		super(c);
		usePatternButton.addSelectionListener(this);
	}
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;
        if (source == usePatternButton) {       	
            if (((Button)source).getSelection()) {
                series1.getBrush().loadImage(ChartSample.class.getResourceAsStream(URL_IMAGE1));
                series2.getBrush().loadImage(ChartSample.class.getResourceAsStream(URL_IMAGE2));
            } else {
                series1.getBrush().clearImage();
                series2.getBrush().clearImage();
            }
            //needed? chart1.redraw();
        }
	}		
	
    protected void createContent() {
    	super.createContent();    	   	
    	usePatternButton = WidgetFactory.createCheckButton(getButtonPane(), "Use Pattern", "", this);    	
    }
    
    protected void initContent() {
    	super.initContent();
    	usePatternButton.setSelection(true);
    }
    
    protected void initChart() {
    	super.initChart();
        chart1.getAspect().setView3D(false);
        
        series1 = new Bar(chart1.getChart());
        series1.setColor(Color.RED);
        series2 = new Bar(chart1.getChart());
        series2.setColor(Color.GREEN);

        for (int i=0; i < chart1.getSeriesCount(); i++) {
        	chart1.getSeries(i).fillSampleValues(3);
        	chart1.getSeries(i).getMarks().setVisible(true);
            ((Bar)chart1.getSeries(i)).getBrush().setImageMode(ImageMode.TILE);
        }

        series1.getBrush().loadImage(ChartSample.class.getResourceAsStream(URL_IMAGE1)); //TODO
        series2.getBrush().loadImage(ChartSample.class.getResourceAsStream(URL_IMAGE2)); //TODO       
    }   		

    private Button usePatternButton;
    private final static String URL_IMAGE1 = "images/barimage1.jpg";
    private final static String URL_IMAGE2 = "images/barimage2.jpg";	
}
