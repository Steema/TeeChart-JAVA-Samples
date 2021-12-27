/*
 * RotateDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.tool.rotate;

import com.steema.teechart.styles.Surface;
import com.steema.teechart.tools.Rotate;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class RotateDemo extends ChartSamplePanel
    implements ItemListener {

    private Rotate tool;

    /** Creates a new instance of RotateDemo */
    public RotateDemo() {
        super();
        activeButton.addItemListener(this);
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == activeButton) {
            tool.setActive(isSelected);
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getHeader().setVisible(false);
        chart1.getLegend().setVisible(false);
        chart1.getAxes().getDepth().setVisible(true);
        chart1.getAspect().setElevation(351);
        chart1.getAspect().setOrthogonal(false);
        chart1.getAspect().setPerspective(55);
        chart1.getAspect().setRotation(344);
        chart1.getAspect().setZoom(60);
        chart1.getAspect().setChart3DPercent(100);
    }

    protected void initComponents() {
        super.initComponents();
        Surface tmpSeries = new com.steema.teechart.styles.Surface(chart1.getChart());
        tmpSeries.getPen().setVisible(false);
        tmpSeries.getMarks().setVisible(false);
        tmpSeries.fillSampleValues(20);

        tool = new com.steema.teechart.tools.Rotate(chart1.getChart());

        activeButton = new JCheckBox("Active");
        activeButton.setSelected(true);
        rotateLabel = new JLabel();
    }

    protected void initGUI(){
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(activeButton);
            tmpPane.add(Box.createHorizontalStrut(30));
            tmpPane.add(rotateLabel);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JCheckBox activeButton;
    private JLabel rotateLabel;
}
