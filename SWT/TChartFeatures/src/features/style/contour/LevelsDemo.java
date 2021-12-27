/*
 * LevelsDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.style.contour;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.styles.Contour;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class LevelsDemo extends ChartSample implements SelectionListener {

    private Contour series;
    
	public LevelsDemo(Composite c) {
		super(c);
	}
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
        if (source instanceof Button) {
        	boolean isSelected = ((Button)source).getSelection();
            series.setAutomaticLevels(!isSelected);
            if (!series.getAutomaticLevels()) {
                setCustomLevels();
            }
        };
	}
	
	protected void createContent() {
		super.createContent();	  
        customButton = addCheckButton("Custom Levels", "", this);       
	}

	protected void initContent() {
		super.initContent();	
        customButton.setSelection(false);    	
	}

	protected void initChart() {
		super.initChart();
        chart1.getAspect().setView3D(false);
        chart1.getWalls().getBottom().setTransparent(true);
        chart1.getAxes().getDepth().setVisible(true);
        chart1.getAxes().getLeft().setVisible(false);
        series = new com.steema.teechart.styles.Contour(chart1.getChart());
        series.fillSampleValues(20);
        setCustomLevels();
        series.setAutomaticLevels(true);
	}   	
	
    private void setCustomLevels() {
        series.setNumLevels(10);
        series.createAutoLevels();
        series.getLevels().getLevel(0).setUpToValue(-1);
        series.getLevels().getLevel(1).setUpToValue(-0.8);
        series.getLevels().getLevel(2).setUpToValue(-0.6);
        series.getLevels().getLevel(3).setUpToValue(-0.4);
        series.getLevels().getLevel(4).setUpToValue(-0.2);
        series.getLevels().getLevel(5).setUpToValue(0);
        series.getLevels().getLevel(6).setUpToValue(0.2);
        series.getLevels().getLevel(7).setUpToValue(0.4);
        series.getLevels().getLevel(8).setUpToValue(0.6);
        series.getLevels().getLevel(9).setUpToValue(0.8);
    }	
	
    Button customButton;
}
