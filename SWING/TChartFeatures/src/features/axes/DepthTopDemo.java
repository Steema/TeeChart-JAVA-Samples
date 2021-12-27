/*
 * DepthTopDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.axes;

import com.steema.teechart.axis.Axis;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.Tower;
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
public class DepthTopDemo extends ChartSamplePanel
    implements ItemListener {

    /** Creates a new instance of DepthTopDemo */
    public DepthTopDemo() {
        super();
        depthButton.addItemListener(this);
        depthTopButton.addItemListener(this);
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == depthButton) {
            chart1.getAxes().getDepth().setVisible(isSelected);
        } else if (source == depthTopButton) {
            chart1.getAxes().getDepthTop().setVisible(isSelected);
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getLegend().setVisible(false);

        Axis axis = chart1.getAxes().getDepthTop();
        axis.getLabels().getFont().setColor(Color.WHITE_SMOKE);
        axis.getLabels().getFont().setSize(13);
        axis.getLabels().getFont().getShadow().setColor(Color.WHITE);
        axis.getLabels().getFont().getShadow().setHorizSize(1);
        axis.getLabels().getFont().getShadow().setVertSize(1);
        axis.setVisible(true);
    }

    protected void initComponents() {
        super.initComponents();
        Tower series = new Tower(chart1.getChart());
        series.fillSampleValues();

        depthTopButton = new JCheckBox("View DepthTop Axis");
        depthTopButton.setSelected(chart1.getAxes().getDepthTop().getVisible());
        depthButton = new JCheckBox("View Depth Axis");
        depthButton.setSelected(chart1.getAxes().getDepth().getVisible());
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(depthTopButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(depthButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JCheckBox depthButton, depthTopButton;
}
