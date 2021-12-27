/*
 * LegendScrollDemo.java
 *
 * <p>Copyright: Copyright (c) 2004-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.legend;

import com.steema.teechart.styles.Line;
import com.steema.teechart.tools.ColorLine;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class LegendScrollDemo extends ChartSamplePanel
    implements ChangeListener {

    private ColorLine lineTool;

    /**
     * Creates a new instance of LegendScrollDemo
     */
    public LegendScrollDemo() {
        super();
        scrollSlider.addChangeListener(this);
    }

    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider)e.getSource();
        if (!source.getValueIsAdjusting()) {
            int scroll = (int)source.getValue();
            chart1.getLegend().setFirstValue(scroll);
            scrollLabel.setText(String.valueOf(scroll));
            lineTool.setValue(scroll);
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getAspect().setChart3DPercent(20);
    }

    protected void initComponents() {
        super.initComponents();

        Line series = new Line(chart1.getChart());
        series.fillSampleValues(100);

        lineTool = new ColorLine(chart1.getAxes().getBottom());
        lineTool.getPen().setWidth(2);
        lineTool.setValue(chart1.getLegend().getFirstValue());

        scrollLabel = new JLabel(String.valueOf(chart1.getLegend().getFirstValue()));
        scrollSlider = new JSlider(JSlider.HORIZONTAL, 0, series.getCount()-1, chart1.getLegend().getFirstValue());
    }

    protected void initGUI() {
        super.initGUI();
        JLabel tmpLabel;
        JPanel tmpPane = getButtonPane();
        {
            tmpLabel = new JLabel("First value:");
            tmpLabel.setDisplayedMnemonic('F');
            tmpLabel.setLabelFor(scrollSlider);
            tmpPane.add(scrollSlider);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(scrollLabel);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JLabel scrollLabel;
    private JSlider scrollSlider;
}
