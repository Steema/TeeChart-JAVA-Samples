/*
 * StairsDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.style.area;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.styles.Area;
import com.steema.teechart.styles.MultiAreas;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class StairsDemo extends ChartSample implements SelectionListener {

    private Area areaSeries1, areaSeries2;
	
	public StairsDemo(Composite c) {
		super(c);	
	}

	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
		if (source instanceof Button) {
			boolean isSelected = ((Button)source).getSelection();
	        if (source == stairsButton) {
	            areaSeries1.setStairs(isSelected);
	            areaSeries2.setStairs(isSelected);
	        }				
		}
	}
	
	protected void createContent() {
		super.createContent();
        stairsButton = addCheckButton("Stairs", "", this);				
	}

	protected void initContent() {
		super.initContent();
        stairsButton.setSelection(true);
	}

	protected void initChart() {
		super.initChart();
        chart1.getAspect().setView3D(false);
        chart1.getLegend().setVisible(false);		
        areaSeries1 = new Area(chart1.getChart());
        areaSeries2 = new Area(chart1.getChart());

        for (int i=0; i < chart1.getSeriesCount(); i++) {
            Area tmpSeries = (Area)chart1.getSeries(i);
            tmpSeries.getMarks().setVisible(false);
            tmpSeries.fillSampleValues(10);

            tmpSeries.setStairs(true);
            tmpSeries.setMultiArea(MultiAreas.STACKED);
        }
	}   
	
    private Button stairsButton;	
}
