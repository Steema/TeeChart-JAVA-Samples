/*
 * StyleDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.tool.drawline;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.events.ChangeEvent;
import com.steema.teechart.tools.DrawLine;
import com.steema.teechart.tools.DrawLineItem;
import com.steema.teechart.tools.DrawLineStyle;

import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import features.ChartSamplePanel;
import features.utils.EnumStrings;


/**
 *
 * @author tom
 */
public class StyleDemo extends ChartSamplePanel {

    private DrawLine tool;

    /**
     * Creates a new instance of StyleDemo
     */
    public StyleDemo() {
        super();
    }

    protected void initChart() {
        super.initChart();
        chart1.getAspect().setView3D(false);
        chart1.getHeader().setText("DrawLine style example");
        chart1.getLegend().setVisible(false);
        chart1.getAxes().getLeft().setMinMax(0,100);
        chart1.getAxes().getBottom().setMinMax(0,100);
    }

    protected void initComponents() {
        super.initComponents();

        tool = new com.steema.teechart.tools.DrawLine(chart1.getChart());
        tool.getPen().setColor(Color.BLUE);
        tool.getPen().setWidth(3);
        tool.addDrawLineListener( tool.new DrawLineAdapter() {
            public void lineNew(ChangeEvent e) {
                // Change line style for new added lines:
                DrawLineItem tmpItem = tool.getLines().getLast();
                switch (lineStyleList.getSelectedIndex()) {
                    case 0: tmpItem.setDrawLineStyle(DrawLineStyle.LINE); break;
                    case 1: tmpItem.setDrawLineStyle(DrawLineStyle.HORIZPARALLEL); break;
                    case 2: tmpItem.setDrawLineStyle(DrawLineStyle.VERTPARALLEL); break;
                }
                chart1.repaint();
            }
        });

        lineStyleList = new JComboBox(EnumStrings.DRAWLINE_STYLES);
        lineStyleList.setSelectedIndex(1);
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            JLabel tmpLabel = new JLabel("New line style:");
            tmpLabel.setDisplayedMnemonic('s');
            tmpLabel.setLabelFor(lineStyleList);
            tmpPane.add(tmpLabel);
            tmpPane.add(lineStyleList);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(new JLabel("(click and drag left mouse button)"));
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JComboBox lineStyleList;
}
