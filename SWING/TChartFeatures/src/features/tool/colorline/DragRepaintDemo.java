/*
 * DragRepaintDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.tool.colorline;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.DashStyle;
import com.steema.teechart.styles.Bar;
import com.steema.teechart.tools.ColorLine;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class DragRepaintDemo extends ChartSamplePanel
        implements ItemListener {

    /**
     * Creates a new instance of DragRepaintDemo
     */
    public DragRepaintDemo() {
        super();
        repaintButton.addItemListener(this);
        view3DButton.addItemListener(this);
        drawBehindButton.addItemListener(this);
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);

        if (source == repaintButton) {
            for (int t=0; t < chart1.getTools().size(); t++) {
                if (chart1.getTools().getTool(t) instanceof ColorLine) {
                    ((ColorLine)chart1.getTools().getTool(t)).setDragRepaint(isSelected);
                }
            }
        } else if (source == view3DButton) {
            chart1.getAspect().setView3D(isSelected);
        } else if (source == drawBehindButton) {
            for (int t=0; t < chart1.getTools().size(); t++) {
                if (chart1.getTools().getTool(t) instanceof ColorLine) {
                    ((ColorLine)chart1.getTools().getTool(t)).setDrawBehind(isSelected);
                }
            }
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.setText("3 Color-Line tools, drag them !");
        chart1.getAspect().setView3D(false);
    }

    protected void initComponents() {
        super.initComponents();

        Bar tmpSeries = new com.steema.teechart.styles.Bar(chart1.getChart());
        tmpSeries.add(new int[] {40,220,140,512,256,310,60,100,600});

        ColorLine tmpTool;

        //tool1
        tmpTool = new com.steema.teechart.tools.ColorLine(chart1.getChart());
        tmpTool.getPen().setColor(Color.YELLOW);
        tmpTool.getPen().setWidth(2);
        tmpTool.setValue(100.0);
        tmpTool.setAxis(chart1.getAxes().getLeft());

        //tool2
        tmpTool = new com.steema.teechart.tools.ColorLine(chart1.getChart());
        tmpTool.getPen().setStyle(DashStyle.DOT);
        tmpTool.getPen().setWidth(3);
        tmpTool.setValue(300.0);
        tmpTool.setAxis(chart1.getAxes().getLeft());

        //tool3
        tmpTool = new com.steema.teechart.tools.ColorLine(chart1.getChart());
        tmpTool.getPen().setColor(Color.LIME);
        tmpTool.setValue(500.0);
        tmpTool.setAxis(chart1.getAxes().getLeft());

        repaintButton = new JCheckBox("Repaint all when dragging");
        view3DButton = new JCheckBox("3D");
        drawBehindButton = new JCheckBox("Draw Behind");
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(repaintButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(view3DButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(drawBehindButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JCheckBox drawBehindButton, view3DButton, repaintButton;
}
