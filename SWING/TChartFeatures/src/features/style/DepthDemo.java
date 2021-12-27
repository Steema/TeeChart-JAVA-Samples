/*
 * DepthDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style;

import com.steema.teechart.styles.Bar;
import com.steema.teechart.styles.Line;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import features.SamplePanel;

/**
 *
 * @author tom
 */
public class DepthDemo extends SamplePanel
    implements ChangeListener, ItemListener {

    private Line series1, series3;
    private Bar series2;

    /** Creates a new instance of DepthDemo */
    public DepthDemo() {
        super();
        autoDepthButton.addItemListener(this);
        depthSpinner.addChangeListener(this);
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == autoDepthButton) {
            int depth = -1;
            if (!isSelected) {
                depth = ((SpinnerNumberModel)depthSpinner.getModel()).getNumber().intValue();
            }
            series1.setDepth(depth);
            series2.setDepth(depth);
            series3.setDepth(depth);
        }
    }

    public void stateChanged(ChangeEvent e) {
        Object source = e.getSource();
        if (source == depthSpinner) {
            autoDepthButton.setSelected(false);
            int depth = ((SpinnerNumberModel)depthSpinner.getModel()).getNumber().intValue();
            series1.setDepth(depth);
            series2.setDepth(depth);
            series3.setDepth(depth);
        }
    }

    protected void initComponents() {
        super.initComponents();

        series1 = new Line(chart1.getChart());
        series1.fillSampleValues(10);

        series2 = new Bar(chart1.getChart());
        series2.fillSampleValues(6);

        series3 = new Line(chart1.getChart());
        series3.fillSampleValues(10);

        for (int t=0; t < chart1.getSeriesCount(); t++) {
            chart1.getSeries(t).setDepth(5);
        }

        autoDepthButton = new JCheckBox("Automatic Depth");
        depthSpinner = new JSpinner(
            new SpinnerNumberModel(
                5,
                0,
                Integer.MAX_VALUE,
                1
            )
        );
    }

    protected void initGUI() {
        super.initGUI();
        chart1.getAspect().setOrthogonal(false);
        chart1.getAspect().setElevation(295);
        chart1.getAspect().setPerspective(45);
        chart1.getAspect().setChart3DPercent(25);

        JPanel tmpPane = getButtonPane();
        {
            JLabel tmpLabel = new JLabel("Series Depth:");
            tmpLabel.setDisplayedMnemonic('D');
            tmpLabel.setLabelFor(depthSpinner);
            tmpPane.add(tmpLabel);
            tmpPane.add(depthSpinner);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(autoDepthButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JCheckBox autoDepthButton;
    private JSpinner depthSpinner;
}
