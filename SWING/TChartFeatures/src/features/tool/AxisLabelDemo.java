/*
 * AxisLabelDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.tool;

import com.steema.teechart.drawing.ChartFont;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.Bar;
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
public class AxisLabelDemo extends ChartSamplePanel
    implements ItemListener {

    /**
     * Creates a new instance of AxisLabelDemo
     */
    public AxisLabelDemo() {
        super();
        activeButton.addItemListener(this);
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);

        if (source == activeButton) {
            chart1.getTools().getTool(0).setActive(isSelected);
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getLegend().setVisible(false);

        ChartFont tmpFont = chart1.getAxes().getLeft().getLabels().getFont();
        tmpFont.setColor(Color.NAVY);
        tmpFont.setSize(13);
        tmpFont.setBold(true);
        tmpFont.getShadow().setColor(Color.AQUA);
        tmpFont.getShadow().setHorizSize(1);
        tmpFont.getShadow().setVertSize(-1);
    }

    protected void initComponents() {
        super.initComponents();
        Bar tmpSeries = new com.steema.teechart.styles.Bar(chart1.getChart());
        tmpSeries.getMarks().setVisible(true);
        tmpSeries.getMarks().setColor(Color.RED);
        /* sample values for the series */
        tmpSeries.add(new int[]{8,800,150,1500,2000,1000,120000,30000});

        /* create the custom tool */
        AxisLabel tmpTool = new AxisLabel(chart1.getAxes().getLeft());

        activeButton = new JCheckBox("Active");
        activeButton.setSelected(true);
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(activeButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JCheckBox activeButton;
}
