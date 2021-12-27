/*
 * PVODemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.function;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.functions.Function;
import com.steema.teechart.styles.FastLine;
import com.steema.teechart.styles.MarksStyle;
import com.steema.teechart.styles.Volume;
import com.steema.teechart.tools.MarksTip;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class PVODemo extends ChartSample implements SelectionListener {

    private Volume sourceSeries;
    private Function pvoFunction;
    private FastLine functionSeries;
    
	public PVODemo(Composite c) {
		super(c);
	}
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
        if (source == editButton) {
            //TODO ChartEditor.editSeriesDatasource(functionSeries);
        }
	}	

	protected void createContent() {
		super.createContent();
		editButton = addPushButton("Edit...", "", this);        
	}

	protected void initContent() {
		super.initContent();
        editButton.setEnabled(false);
    }

	protected void initChart() {
		super.initChart();
        chart1.getHeader().setVisible(true);
        chart1.setText("Percentage Volume Oscillator function");
        chart1.getAspect().setView3D(false);
        
        sourceSeries = new com.steema.teechart.styles.Volume(chart1.getChart());
        sourceSeries.getMarks().setVisible(false);
        sourceSeries.getPointer().setVisible(false);
        sourceSeries.setTitle("Source");
        sourceSeries.setColor(Color.RED);
        sourceSeries.fillSampleValues(100);

        pvoFunction = new com.steema.teechart.functions.PVO(chart1.getChart());
        pvoFunction.setPeriod(12);

        functionSeries = new com.steema.teechart.styles.FastLine(chart1.getChart());
        functionSeries.setDataSource(sourceSeries);
        functionSeries.setFunction(pvoFunction);

        functionSeries.setTitle("PVO");
        functionSeries.setColor(Color.YELLOW);
        functionSeries.getLinePen().setColor(Color.YELLOW);
        functionSeries.getLinePen().setWidth(2);
        functionSeries.getMarks().setVisible(false);

        MarksTip tmpTool = new MarksTip(chart1.getChart());
        tmpTool.setSeries(sourceSeries);
        tmpTool.setStyle(MarksStyle.XY);      
	}   		
	
    private Button editButton;	
}
