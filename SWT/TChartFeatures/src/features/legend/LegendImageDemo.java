/*
 * LegendImageDemo.java
 *
 * <p>Copyright: Copyright (c) 2004-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.legend;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.ImageMode;
import com.steema.teechart.styles.Series;
import com.steema.teechart.styles.WindRose;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class LegendImageDemo extends ChartSample implements SelectionListener {

	public LegendImageDemo(Composite c) {
		super(c);
	}

	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
        if (source == imageButton) {
        	boolean isSelected = ((Button)source).getSelection();
            if (isSelected) {
                chart1.getLegend().getBrush().loadImage(ChartSample.class.getResourceAsStream(URL_IMAGE));
            } else {
                chart1.getLegend().getBrush().clearImage();
            }
        }
	}
	
	protected void createContent() {
		super.createContent();	
		imageButton = addCheckButton("Legend image", "", this);  
	}

	protected void initContent() {
		super.initContent();
		imageButton.setSelection(chart1.getLegend().getBrush().getImage() != null);
	}

	protected void initChart() {
		super.initChart();
        chart1.getAspect().setView3D(false);
        chart1.getLegend().setTransparent(false);
        chart1.getLegend().getBrush().setImageMode(ImageMode.TILE);
        chart1.getLegend().getBrush().loadImage(ChartSample.class.getResourceAsStream(URL_IMAGE));

        Series series = new WindRose(chart1.getChart());
        series.fillSampleValues(8);        
	}   		

    private Button imageButton;
    private final static String URL_IMAGE = "images/pattern2.jpg";
}
