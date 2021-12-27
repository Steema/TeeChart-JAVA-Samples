/*
 * AxisOffsetDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.axes;

import com.steema.teechart.axis.Axis;
import com.steema.teechart.styles.FastLine;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JComboBox;
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
public class AxisOffsetDemo extends ChartSamplePanel
        implements ActionListener, ChangeListener {

    /** Creates a new instance of ZPositionDemo */
    public AxisOffsetDemo() {
        super();
        axisList.addActionListener(this);
        minSpinner.addChangeListener(this);
        maxSpinner.addChangeListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == axisList) {
            Axis axis;
            switch (axisList.getSelectedIndex()) {
                case 1: {axis = chart1.getAxes().getBottom(); break; }
                default: {axis = chart1.getAxes().getLeft(); }
            }
            minSpinner.setValue(new Integer(axis.getMinimumOffset()));
            maxSpinner.setValue(new Integer(axis.getMaximumOffset()));
        }
    }

    public void stateChanged(ChangeEvent e) {
        Object source = e.getSource();
        if ((source == minSpinner) || (source == maxSpinner)) {
            Axis axis;
            switch (axisList.getSelectedIndex()) {
                case 1: {axis = chart1.getAxes().getBottom(); break; }
                default: {axis = chart1.getAxes().getLeft(); }
            }
            if (source == minSpinner) {
                int offset = ((SpinnerNumberModel)minSpinner.getModel()).getNumber().intValue();
                axis.setMinimumOffset(offset);
            } else {
                int offset = ((SpinnerNumberModel)maxSpinner.getModel()).getNumber().intValue();
                axis.setMaximumOffset(offset);
            }
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getAspect().setView3D(false);
        chart1.getLegend().setVisible(false);
        Axis axis;
        axis = chart1.getAxes().getBottom();
        axis.setMaximumOffset(4);
        axis.setMinimumOffset(4);
        axis = chart1.getAxes().getLeft();
        axis.setMaximumOffset(4);
        axis.setMinimumOffset(4);
    }

    protected void initComponents() {
        super.initComponents();
        FastLine series = new FastLine(chart1.getChart());
        series.fillSampleValues(20);
        series.getLinePen().setWidth(3);

        minSpinner = new JSpinner(
            new SpinnerNumberModel(
                4,
                -400,
                +400,
                1
            )
        );

        maxSpinner = new JSpinner(
            new SpinnerNumberModel(
                4,
                -400,
                +400,
                1
            )
        );

        axisList = new JComboBox(new String[]{"Left", "Bottom"});
        axisList.setSelectedIndex(0);
    }

    protected void initGUI() {
        super.initGUI();
        JLabel tmpLabel;
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpLabel = new JLabel("Axis:");
            tmpLabel.setDisplayedMnemonic('A');
            tmpLabel.setLabelFor(axisList);
            tmpPane.add(tmpLabel);
            tmpPane.add(axisList);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpLabel = new JLabel("Min offset:");
            tmpLabel.setDisplayedMnemonic('i');
            tmpLabel.setLabelFor(minSpinner);
            tmpPane.add(tmpLabel);
            tmpPane.add(minSpinner);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpLabel = new JLabel("Max offset:");
            tmpLabel.setDisplayedMnemonic('a');
            tmpLabel.setLabelFor(maxSpinner);
            tmpPane.add(tmpLabel);
            tmpPane.add(maxSpinner);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JComboBox axisList;
    private JSpinner minSpinner, maxSpinner;
}
