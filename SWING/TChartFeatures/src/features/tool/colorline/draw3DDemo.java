/*
 * draw3DDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.tool.colorline;

import com.steema.teechart.styles.Points;
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
public class draw3DDemo extends ChartSamplePanel
    implements ItemListener {

    private ColorLine tool;

    /**
     * Creates a new instance of draw3DDemo
     */
    public draw3DDemo() {
        super();
        draw3DButton.addItemListener(this);
        drawBehindButton.addItemListener(this);
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);

        if (source == draw3DButton) {
            tool.setDraw3D(isSelected);
        } else if (source == drawBehindButton) {
            tool.setDrawBehind(isSelected);
        }
    }

    protected void initChart() {
        super.initChart();
    }

    protected void initComponents() {
        super.initComponents();

        Points tmpSeries = new Points(chart1.getChart());
        tmpSeries.setColorEach(true);
        tmpSeries.getMarks().setVisible(false);
        tmpSeries.fillSampleValues();

        double tmpValue =
                (tmpSeries.getYValues().getMaximum()
                + tmpSeries.getYValues().getMinimum()
                ) / 2;

        tool = new com.steema.teechart.tools.ColorLine(chart1.getChart());
        tool.setValue(tmpValue);
        tool.setAxis(chart1.getAxes().getLeft());
        tool.setDraw3D(true);

        draw3DButton = new JCheckBox("Draw 3D");
        draw3DButton.setSelected(true);
        drawBehindButton = new JCheckBox("Draw Behind");
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(draw3DButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(drawBehindButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JCheckBox draw3DButton, drawBehindButton;
}
