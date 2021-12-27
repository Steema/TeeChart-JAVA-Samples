/*
 * LinePointDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.pointer;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.DashStyle;
import com.steema.teechart.styles.LinePoint;
import com.steema.teechart.styles.MarksStyle;
import com.steema.teechart.tools.MarksTip;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class LinePointDemo extends ChartSample implements SelectionListener {

    //TODO private ButtonPen linesPen;
    private LinePoint series;
    
	public LinePointDemo(Composite c) {
		super(c);
	}

	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;
        if (source == editButton) {
            //TODO ChartEditor.editSeries(series);
        } else {
            boolean isSelected = ((Button)source).getSelection();
            if (source == view3DButton) {
                chart1.getAspect().setView3D(isSelected);
            }        	
        }
	}	
	
    protected void createContent() {
    	super.createContent();    	   	
        //TODO linesPen = new ButtonPen(series.getLinePen(), "Lines...");
    	editButton = addPushButton("Edit...", "", this);
        view3DButton = addCheckButton("3D", "", this);        
    }
    
    protected void initContent() {
    	super.initContent();    	   	
    	view3DButton.setSelection(chart1.getAspect().getView3D());  
    }
    
    protected void initChart() {
    	super.initChart();
        chart1.getLegend().setVisible(false);
        chart1.getHeader().setVisible(true);
        chart1.setText("Line Point Series");
        chart1.getAxes().getBottom().getGrid().setVisible(false);
        chart1.getAxes().getLeft().getGrid().setVisible(false);
        chart1.getAspect().setView3D(false);
        series = new LinePoint(chart1.getChart());
        series.fillSampleValues(10);
        series.getLinePen().setColor(Color.BLUE);
        series.getLinePen().setStyle(DashStyle.DASH);

        MarksTip tmpTool = new MarksTip(chart1.getChart());
        tmpTool.setSeries(series);
        tmpTool.setStyle(MarksStyle.LABELVALUE);
        tmpTool.setActive(true);        
    }   			
    
    private Button editButton;
    private Button view3DButton;    
}
