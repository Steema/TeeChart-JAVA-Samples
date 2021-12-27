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
import com.steema.teechart.styles.FastLine;
import com.steema.teechart.styles.VerticalAxis;
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
public class MultiRuntimeDemo extends ChartSamplePanel
        implements ItemListener {

    private FastLine series1, series2, series3;

    /** Creates a new instance of MinorGridDemo */
    public MultiRuntimeDemo() {
        super();
        singleButton.addItemListener(this);
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == singleButton) {
            if (isSelected) {
                series2.setVerticalAxis(VerticalAxis.LEFT);
                series3.setVerticalAxis(VerticalAxis.LEFT);
                chart1.getAxes().getLeft().setEndPosition(100);
            } else {
                series2.setCustomVertAxis(0);
                series3.setCustomVertAxis(1);
                chart1.getAxes().getLeft().setEndPosition(30);
            }
        }
    }

    protected void initAxes() {
        chart1.getAxes().getCustom().clear();
        Axis axis;
        axis = chart1.getAxes().getCustom().getNew();
        axis.setStartPosition(30);
        axis.setEndPosition(60);
        axis.getAxisPen().setColor(series2.getColor());
        series2.setCustomVertAxis(axis);

        axis = chart1.getAxes().getCustom().getNew();
        axis.setStartPosition(60);
        axis.setEndPosition(100);
        axis.getAxisPen().setColor(series3.getColor());
        series3.setCustomVertAxis(axis);
    }

    protected void initChart() {
        super.initChart();
        chart1.getAspect().setView3D(false);
        chart1.getAxes().getLeft().setEndPosition(30);
    }

    protected void initComponents() {
        super.initComponents();
        series1 = new FastLine(chart1.getChart());
        series2 = new FastLine(chart1.getChart());
        series3 = new FastLine(chart1.getChart());

        chart1.getSeries().fillSampleValues(1000);
        initAxes();

        singleButton = new JCheckBox("Single axis");
        singleButton.setSelected(chart1.getAxes().getBottom().getMinorGrid().getVisible());
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(singleButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JCheckBox singleButton;
}
