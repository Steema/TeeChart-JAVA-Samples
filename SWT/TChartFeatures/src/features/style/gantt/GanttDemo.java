/*
 * GanttDemo.java
 *
 * <p>Copyright: (c) 2005-2008 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.style.gantt;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.legend.LegendSymbolSize;
import com.steema.teechart.styles.Gantt;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class GanttDemo extends ChartSample implements SelectionListener {

    private Gantt ganttSeries;
    
	public GanttDemo(Composite c) {
		super(c);
	}
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
		if (source instanceof Button) {
	        if (source == view3DButton) {
				boolean isSelected = ((Button)source).getSelection();
	        	chart1.getAspect().setView3D(isSelected);
	        } else if (source == editButton) {
	        	//TODO ChartEditor.editSeries(ganttSeries);				
	        }
		}
	}
	
	protected void createContent() {
		super.createContent();
        view3DButton = addCheckButton("3D", "", this);
        editButton = addPushButton("Edit...", "Edit series", this);
	}

	protected void initContent() {
		super.initContent();
        view3DButton.setSelection(chart1.getAspect().getView3D());
	}

	protected void initChart() {
		super.initChart();
        chart1.getAspect().setView3D(false);
        chart1.getAspect().setView3D(false);
        chart1.getHeader().setVisible(true);
        chart1.setText("Gantt - Scheduling");
        chart1.getLegend().setColorWidth(10);
        chart1.getLegend().getSymbol().setWidth(10);
        chart1.getLegend().getSymbol().setWidthUnits(LegendSymbolSize.PIXELS);        
        ganttSeries = new com.steema.teechart.styles.Gantt(chart1.getChart());
        ganttSeries.fillSampleValues();        
	}   
	
    private Button view3DButton;
    private Button editButton;    

}
