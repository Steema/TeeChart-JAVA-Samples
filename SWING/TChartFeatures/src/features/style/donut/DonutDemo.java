/*
 * DonutDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.donut;

import com.steema.teechart.styles.Donut;
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
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class DonutDemo extends ChartSamplePanel
    implements ChangeListener, ItemListener {

    private Donut series;

    /** Creates a new instance of DonutDemo */
    public DonutDemo() {
        super();
        view3DButton.addItemListener(this);
        holeSpinner.addChangeListener(this);
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == view3DButton) {
            chart1.getAspect().setView3D(isSelected);
        }
    }

    public void stateChanged(ChangeEvent e) {
        Object source = e.getSource();
        if (source == holeSpinner) {
            int hole = ((SpinnerNumberModel)holeSpinner.getModel()).getNumber().intValue();
            series.setDonutPercent(hole);
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getAspect().setElevation(315);
        chart1.getAspect().setOrthogonal(false);
        chart1.getAspect().setPerspective(0);
        chart1.getAspect().setRotation(360);
    }

    protected void initComponents() {
        super.initComponents();

        series = new com.steema.teechart.styles.Donut(chart1.getChart());
        series.fillSampleValues(8);
        series.setDonutPercent(50);
        series.setCircled(true);

        view3DButton = new JCheckBox("3D");
        view3DButton.setSelected(true);
        holeSpinner = new JSpinner(
                new SpinnerNumberModel(
                    50,
                    0,
                    100,
                    5)
        );
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            JLabel tmpLabel = new JLabel("Hole %:");
            tmpLabel.setDisplayedMnemonic('H');
            tmpLabel.setLabelFor(holeSpinner);
            tmpPane.add(tmpLabel);
            tmpPane.add(holeSpinner);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(view3DButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JCheckBox view3DButton;
    private JSpinner holeSpinner;
}
