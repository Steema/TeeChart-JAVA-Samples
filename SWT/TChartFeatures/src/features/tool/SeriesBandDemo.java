/*
 * SeriesBandDemo.java
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

import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.Line;
import com.steema.teechart.tools.SeriesBand;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class SeriesBandDemo extends ChartSample implements SelectionListener {

    private SeriesBand tool;
   
	public SeriesBandDemo(Composite c) {
		super(c);
	}
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
		if (source == editButton) {
			//TODO ChartEditor.editTool(seriesBandTool1);
		}
	}
	
	protected void createContent() {
		super.createContent();	  
		editButton = addPushButton("Edit..", "Edit tool", this);       	
	}

	protected void initContent() {
		super.initContent();	
	}

	protected void initChart() {
		super.initChart();
        chart1.getAspect().setView3D(false);
        chart1.getHeader().setVisible(true);
        chart1.setText("SeriesBand Tool Example");
        chart1.getLegend().setVisible(false);
        
        Line line1 = new Line(chart1.getChart());
        Line line2 = new Line(chart1.getChart());
        line1.fillSampleValues();

        for (int i=0; i<line1.getCount(); i++) {
            line2.add(line1.getXValues().getValue(i),
                    line1.getYValues().getValue(i) / 2.0);
        }

        tool = new SeriesBand(chart1.getChart());
        tool.setSeries(line1);
        tool.setSeries2(line2);
        
        line1.getLinePen().setWidth(3);
        line2.getLinePen().setWidth(3);
        
        tool.getGradient().setVisible(true);
        tool.getGradient().setStartColor(Color.SILVER);         
	}   		
	
    Button editButton;	

}
