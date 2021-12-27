/*
 * BehindAxesDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.axes;

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
public class BehindAxesDemo extends ChartSamplePanel
    implements ItemListener {

    /** Creates a new instance of BehindAxesDemo */
    public BehindAxesDemo() {
        super();
        behindButton.addItemListener(this);
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == behindButton) {
            chart1.getAxes().setDrawBehind(isSelected);
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getAspect().setView3D(false);
        chart1.getAxes().setDrawBehind(false);
        chart1.getAxes().getLeft().getGrid().setColor(Color.WHITE);
        chart1.getAxes().getBottom().getGrid().setColor(Color.BLUE);
    }

    protected void initComponents() {
        super.initComponents();
        Bar series = new Bar(chart1.getChart());
        series.getMarks().setVisible(false);
        series.fillSampleValues(6);
        behindButton = new JCheckBox("Axis behind");
        behindButton.setSelected(chart1.getAxes().getDrawBehind());
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(behindButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JCheckBox behindButton;
}
