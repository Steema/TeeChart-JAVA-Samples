/*
 * VolumeDemo.java
 *
 * <p>Copyright: Copyright (c)  2005-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.style.volume;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.drawing.ChartPen;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.Volume;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class VolumeDemo extends ChartSample implements SelectionListener {

    private Volume volumeSeries;

    //TODO private ButtonPen editBorderButton;
    
	public VolumeDemo(Composite c) {
		super(c);
	}
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
        if (source == editButton) {
            //TODO ChartEditor.editSeries(series1);
        //} else if (source == editBorderButton) {
            //TODO volumeSeries.setColor(volumeSeries.getLinePen().getColor());
        }
	}
	
	protected void createContent() {
		super.createContent();
		//TODO editBorderButton = new ButtonPen(tmpPen, "Border...");
		editButton = addPushButton("Edit", "Edit Series", this);
	}

	protected void initContent() {
		super.initContent();
	}

	protected void initChart() {
		super.initChart();
        chart1.getHeader().setVisible(true);
        chart1.setText("Volume");
        chart1.getLegend().setVisible(false);
        chart1.getAspect().setView3D(false);
        
        volumeSeries = new com.steema.teechart.styles.Volume(chart1.getChart());
        volumeSeries.setColor(Color.GREEN);
        volumeSeries.fillSampleValues(100);

        ChartPen tmpPen = volumeSeries.getLinePen();
        tmpPen.setColor(Color.GREEN);
	}   	

    private Button editButton;		

}
