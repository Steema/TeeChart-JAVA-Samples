/*
 * DragMarksDemo.java
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

import com.steema.teechart.TextShapeStyle;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.Line;
import com.steema.teechart.styles.Points;
import com.steema.teechart.styles.SeriesMarks;
import com.steema.teechart.tools.DragMarks;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class DragMarksDemo extends ChartSample implements SelectionListener {

    private DragMarks tool1;
    
	public DragMarksDemo(Composite c) {
		super(c);
	}
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
        if (source instanceof Button) {        	
        	boolean isSelected = ((Button)source).getSelection();
            if (source == activeButton) {
                tool1.setActive(isSelected);
            } else if (source == resetButton) {
                for (int i=0; i < chart1.getSeriesCount(); i++) {
                    chart1.getSeries(i).getMarks().resetPositions();
                }
            } else if (source == editButton) {
                //TODO DialogFactory.showModal(tool1);
            }
        };
	}
	
	protected void createContent() {
		super.createContent();	  
        editButton = addPushButton("Edit...", "Edit tool", this);
        resetButton = addPushButton("Reset", "", this);
        activeButton = addCheckButton("Active", "", this);       
	}

	protected void initContent() {
		super.initContent();	
        activeButton.setSelection(true);    	
	}

	protected void initChart() {
		super.initChart();
        chart1.getLegend().setVisible(false);
        
        tool1 = new DragMarks(chart1.getChart());

        Points series1 = new Points(chart1.getChart());
        series1.fillSampleValues(10);
        series1.getMarks().setVisible(true);
        series1.getMarks().getCallout().setLength(10);

        Line series2 = new Line(chart1.getChart());
        series2.fillSampleValues(6);
        series2.getPointer().setVisible(false);
        SeriesMarks tmpMarks = series2.getMarks();

        tmpMarks.setBackColor(Color.SILVER);
        tmpMarks.setColor(Color.SILVER);
        tmpMarks.getFont().setColor(Color.BLUE);
        tmpMarks.getFont().getShadow().setColor(Color.AQUA);
        tmpMarks.getFont().getShadow().setHorizSize(1);
        tmpMarks.getFont().getShadow().setVertSize(1);
        tmpMarks.setShapeStyle(TextShapeStyle.ROUNDRECTANGLE);
        tmpMarks.setVisible(true);        
	}   		
	
    private Button editButton, resetButton;
    private Button activeButton;
}
