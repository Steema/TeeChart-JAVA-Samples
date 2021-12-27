/*
 * DraggedDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.tool.drawline;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.events.ChangeEvent;
import com.steema.teechart.events.DragAdapter;
import com.steema.teechart.styles.FastLine;
import com.steema.teechart.tools.DrawLine;
import com.steema.teechart.tools.DrawLineItem;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import features.ChartSamplePanel;
import java.text.DecimalFormat;


/**
 *
 * @author tom
 */
public class DraggedDemo extends ChartSamplePanel {

    private DrawLine tool;

    /**
     * Creates a new instance of DraggedDemo
     */
    public DraggedDemo() {
        super();
    }

    protected void initChart() {
        super.initChart();
        chart1.getLegend().setVisible(false);
    }

    protected void initComponents() {
        super.initComponents();

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

        df = new DecimalFormat("#.##");
        linePositionLabel = new JLabel("0,0");
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(new JLabel("Dragged line position:"));
            tmpPane.add(Box.createHorizontalStrut(20));
            tmpPane.add(linePositionLabel);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private DecimalFormat df;
    private JLabel linePositionLabel;
}
