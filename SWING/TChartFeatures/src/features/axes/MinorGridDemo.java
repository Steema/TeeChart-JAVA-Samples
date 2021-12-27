/*
 * MinorGridDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.axes;

import com.steema.teechart.axis.Axis;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.DashStyle;
import com.steema.teechart.styles.Area;
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
public class MinorGridDemo extends ChartSamplePanel
    implements ItemListener {

    /** Creates a new instance of MinorGridDemo */
    public MinorGridDemo() {
        super();
        visibleButton.addItemListener(this);
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == visibleButton) {
            chart1.getAxes().getBottom().getMinorGrid().setVisible(isSelected);
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getAspect().setView3D(false);
        Axis axis = chart1.getAxes().getBottom();
        axis.getMinorGrid().setVisible(true);
        axis.setMinorTickCount(3);
        axis.getGrid().setStyle(DashStyle.SOLID);
        axis.getMinorGrid().setColor(Color.WHITE);
        axis.getMinorGrid().setStyle(DashStyle.DOT);
    }

    protected void initComponents() {
        super.initComponents();
        Area series = new Area(chart1.getChart());
        series.fillSampleValues(4);
        visibleButton = new JCheckBox("Minor Grid visible");
        visibleButton.setSelected(chart1.getAxes().getBottom().getMinorGrid().getVisible());
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(visibleButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JCheckBox visibleButton;
}
