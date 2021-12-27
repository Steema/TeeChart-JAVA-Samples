/*
 * RadarDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.style.radar;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.ScrollMode;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.GradientDirection;
import com.steema.teechart.drawing.StringAlignment;
import com.steema.teechart.styles.Radar;

import features.CommandSample;

/**
 * @author tom
 *
 */
public class RadarDemo extends CommandSample implements SelectionListener {

    private Radar series1, series2, series3;
    
	public RadarDemo(Composite c) {
		super(c);
	}
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;	
		if (source instanceof Button) {
			boolean isSelected = ((Button)source).getSelection();
	        if (source == squaredButton) {
	            series1.setCircled(isSelected);
	        } else if (source == showLabelsButton) {
	            series1.setCircleLabels(isSelected);
	        } else if (source == showLinesButton) {
	            for (int t=0; t < chart1.getSeriesCount(); t++) {
	                ((Radar)chart1.getSeries(t)).getPen().setVisible(isSelected);
	            }
	        } else if (source == showAxisButton) {
	            chart1.getAxes().getRight().setVisible(isSelected);
	        }	
		}
	}
	
    protected void createContent() {
    	super.createContent();
    	
        squaredButton = addCheckButton("Squared", "", this);
        showLabelsButton = addCheckButton("Show Labels", "", this);
        showLinesButton = addCheckButton("Show Lines", "", this);
        showAxisButton = addCheckButton("Show Axis", "", this);
    }
    
    protected void initContent() {
    	super.initContent();
    	
    	squaredButton.setSelection(true);
    	showLabelsButton.setSelection(true);    	
    	showLinesButton.setSelection(true);
    	showAxisButton.setSelection(true);    	
    }
    
    protected void initChart() {
    	super.initChart();
        chart1.getPanning().setAllow(ScrollMode.NONE);
        chart1.getPanel().getGradient().setVisible(true);
        chart1.getPanel().getGradient().setDirection(GradientDirection.HORIZONTAL);
        chart1.getHeader().setVisible(true);
        chart1.getHeader().setAlignment(StringAlignment.NEAR);
        chart1.setText("Radar Series");
        chart1.getAxes().setVisible(false);
        chart1.getFrame().setVisible(false);
        chart1.getAspect().setView3D(false);
        chart1.getZoom().setAllow(false);    	
    	
        series1 = new com.steema.teechart.styles.Radar(chart1.getChart());
        series1.setCircleBackColor(Color.WHITE);
        series1.setCircleLabels(true);
        series1.getCircleLabelsFont().setItalic(true);
        series1.getCirclePen().setColor(Color.AQUA);

        series2 = new com.steema.teechart.styles.Radar(chart1.getChart());
        series3 = new com.steema.teechart.styles.Radar(chart1.getChart());

        chart1.getSeries().fillSampleValues(5);

        for (int t=0; t < chart1.getSeriesCount(); t++) {
            ((Radar)chart1.getSeries(t)).setCircled(true);
            ((Radar)chart1.getSeries(t)).getBrush().setVisible(false);
        }       
    }   
		
   	private Button squaredButton, showLabelsButton, showLinesButton, showAxisButton;
}
