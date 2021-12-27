/*
 * PieFocusDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.tool;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.Pie;
import com.steema.teechart.tools.PieTool;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class PieFocusDemo extends ChartSample implements SelectionListener {

    private PieTool tool;
    //TODO private ButtonPen penButton;    
    
	public PieFocusDemo(Composite c) {
		super(c);
		
		/* TODO
        chart1.addMouseMotionListener( new MouseMotionAdapter() {
            public void mouseMoved(MouseEvent e) {
                int tmp;
                tmp=tool.getSlice();
                if (tmp==-1) {
                    pieFocusLabel.setText(" ");
                } else {
                    pieFocusLabel.setText(" " + chart1.getSeries(0).getLabels().getString(tmp));
                    pieFocusLabel.setForeground(chart1.getSeries(0).getValueColor(tmp));
                }
            }
        });		
        */
	}

	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
        if (source == editButton) {
            //TODO ChartEditor.editTool(tool);
        }
	}
	
	protected void createContent() {
		super.createContent();	  
        pieFocusLabel = addLabel(SWT.LEFT, "Slice: ");	        
        pieFocusLabel = addLabel(SWT.LEFT, " ");
        editButton = addPushButton("Edit...", "", this);
        //TODO penButton = new ButtonPen(tool.getPen());
        
	}

	protected void initContent() {
		super.initContent();	
	}

	protected void initChart() {
		super.initChart();
        chart1.getHeader().setVisible(false);
        chart1.getAspect().setElevation(315);
        chart1.getAspect().setOrthogonal(false);
        chart1.getAspect().setPerspective(0);
        chart1.getAspect().setRotation(360);

        Pie pieSeries = new com.steema.teechart.styles.Pie(chart1.getChart());
        pieSeries.getMarks().getCallout().getBrush().setColor(Color.BLACK);
        pieSeries.getMarks().getCallout().setLength(8);
        pieSeries.getMarks().setVisible(true);
        pieSeries.getOtherSlice().getLegend().setVisible(false);
        pieSeries.fillSampleValues(8);

        tool = new com.steema.teechart.tools.PieTool(chart1.getChart());
        tool.getPen().setWidth(4);
        tool.setSeries(pieSeries);        
	}   		
	
    private Button editButton;
    private Label pieFocusLabel;
}
