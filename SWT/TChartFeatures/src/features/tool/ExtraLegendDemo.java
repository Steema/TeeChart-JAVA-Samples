/*
 * ExtraLegendDemo.java
 *
 * <p>Copyright: (c) 2005-2008 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.tool;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.legend.Legend;
import com.steema.teechart.legend.LegendStyle;
import com.steema.teechart.styles.Line;
import com.steema.teechart.styles.Series;
import com.steema.teechart.tools.ExtraLegend;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class ExtraLegendDemo extends ChartSample implements SelectionListener {

    private ExtraLegend tool1;
    
	public ExtraLegendDemo(Composite c) {
		super(c);
	}

	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
        if (source instanceof Button) {        	
        	boolean isSelected = ((Button)source).getSelection();
            if (source == alignButton) {
                Legend tmpLegend = tool1.getLegend();
                tmpLegend.setCustomPosition(true);
                tmpLegend.setLeft(chart1.getLegend().getLeft());
                tmpLegend.setTop(chart1.getLegend().getShapeBounds().getBottom()+10);
            } else if (source == editButton) {
                //TODO LegendEditor.editLegend(this, tool1.getLegend());
            } else if (source == showLegendButton) {
                tool1.setActive(isSelected);
            }
        };
	}
	
	protected void createContent() {
		super.createContent();	  	
        showLegendButton = addCheckButton("Show extra legend", "", this);		
        editButton = addPushButton("Edit...", "Edit extra legend", this);
        alignButton = addPushButton("Align", "", this);       
	}

	protected void initContent() {
		super.initContent();	
		showLegendButton.setSelection(true);    	
	}

	protected void initChart() {
		super.initChart();
        chart1.getLegend().setVisible(true);
        chart1.getLegend().setLegendStyle(LegendStyle.VALUES);
        
        new Line(chart1.getChart());
        new Line(chart1.getChart());
        for (int i=0; i < chart1.getSeriesCount(); i++) {
            Series tmpSeries = chart1.getSeries(i);
            tmpSeries.fillSampleValues(5);
            tmpSeries.getMarks().setVisible(false);
            ((Line)tmpSeries).getPointer().setVisible(false);
        }

        tool1 = new ExtraLegend(chart1.getChart());
        tool1.setSeries(chart1.getSeries(1));

        // Cosmetic
        Legend tmpLegend = tool1.getLegend();
        tmpLegend.setLegendStyle(LegendStyle.VALUES);
        tmpLegend.getShadow().setTransparency(70);
        tmpLegend.setCustomPosition(true);
        tmpLegend.setLeft(50);
        tmpLegend.setTop(50);        
	}   		
	
    private Button alignButton, editButton;
    private Button showLegendButton;	
}
