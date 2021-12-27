/*
 * DraggedDemo.java
 *
 * <p>Copyright: (c) 2005-2008 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.tool.drawline;

import java.text.DecimalFormat;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.events.ChangeEvent;
import com.steema.teechart.events.DragAdapter;
import com.steema.teechart.styles.FastLine;
import com.steema.teechart.tools.DrawLine;
import com.steema.teechart.tools.DrawLineItem;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class DraggedDemo extends ChartSample {

    private DrawLine tool;
    
	public DraggedDemo(Composite c) {
		super(c);
	}
	
	protected void createContent() {
		super.createContent();	

        df = new DecimalFormat("#.##");
        addLabel(SWT.LEFT, "Dragged line position:");
        linePositionLabel = addLabel(SWT.LEFT, Double.toString(0));     
	}

	protected void initContent() {
		super.initContent();        
	}

	protected void initChart() {
		super.initChart();
        chart1.getLegend().setVisible(false);
        
        FastLine tmpSeries = new com.steema.teechart.styles.FastLine(chart1.getChart());
        tmpSeries.fillSampleValues(20);

        tool = new com.steema.teechart.tools.DrawLine(chart1.getChart());
        tool.getPen().setColor(Color.BLUE);
        tool.addDragListener( new DragAdapter() {
            public void dragFinished(ChangeEvent e) {
                DrawLineItem tmpItem = tool.getSelected();
                StringBuffer sb = new StringBuffer("Start: (x:");
                sb.append(df.format(tmpItem.getStartPos().getX()));
                sb.append(" y: ");
                sb.append(df.format(tmpItem.getStartPos().getY()));
                sb.append(") End: (x: ");
                sb.append(df.format(tmpItem.getEndPos().getX()));
                sb.append(" y: ");
                sb.append(df.format(tmpItem.getEndPos().getY()));
                sb.append(")");
                linePositionLabel.setText(sb.toString());
            }
        });

        // create a new DrawLine
        DrawLineItem tmpLine = new DrawLineItem(tool);

        // set the "X" line positions (start and end position)
        tmpLine.getStartPos().x = 5.0;
        tmpLine.getEndPos().x = 15.0;

        // set the "Y" line positions (start and end position)
        double tmp = (tmpSeries.getYValues().getMaximum()-tmpSeries.getYValues().getMinimum()) / 5;
        tmpLine.getStartPos().y = tmpSeries.getYValues().getMaximum()-tmp;
        tmpLine.getEndPos().y = tmpSeries.getYValues().getMinimum()+tmp;       
	}   	
	
    private DecimalFormat df;
    private Label linePositionLabel;	

}
