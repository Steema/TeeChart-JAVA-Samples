/*
 * ZPositionDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.axes;

import com.steema.teechart.axis.Axis;
import com.steema.teechart.styles.Line;
import com.steema.teechart.styles.VerticalAxis;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JComboBox;
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
public class ZPositionDemo extends ChartSamplePanel
        implements ActionListener, ChangeListener {

    /** Creates a new instance of ZPositionDemo */
    public ZPositionDemo() {
        super();
        axisList.addActionListener(this);
        positionSlider.addChangeListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == axisList) {
            Axis axis;
            switch (axisList.getSelectedIndex()) {
                case 1: {axis = chart1.getAxes().getRight(); break; }
                default: {axis = chart1.getAxes().getLeft(); }
            }
            int position = (int)axis.getZPosition();
            positionSlider.setValue(position);
            positionLabel.setText(Integer.toString(position)+"%");
        }
    }

    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider)e.getSource();
        if (!source.getValueIsAdjusting()) {
            int value = (int)source.getValue();
            positionLabel.setText(Integer.toString(value)+"%");
            Axis axis = chart1.getAxes().getLeft();
            switch (axisList.getSelectedIndex()) {
                case 0: { axis = chart1.getAxes().getLeft(); break; }
                case 1: { axis = chart1.getAxes().getRight(); break; }
            }
            axis.setZPosition(value);
            axis.getGrid().setZPosition(value);
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getHeader().setVisible(true);
        chart1.setText("Axes Z Position");
        chart1.getWalls().getLeft().setVisible(false);
        chart1.getAspect().setChart3DPercent(100);
    }

    protected void initComponents() {
        super.initComponents();
        Line series = new Line(chart1.getChart());
        series.setVerticalAxis(VerticalAxis.BOTH);

        positionLabel = new JLabel("0%");
        positionSlider = new JSlider(
                JSlider.HORIZONTAL,
                0,
                100,
                (int)chart1.getAxes().getLeft().getZPosition());
        axisList = new JComboBox(new String[]{"Left", "Right"});
        axisList.setSelectedIndex(0);
    }

    protected void initGUI() {
        super.initGUI();
        JLabel tmpLabel;
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(axisList);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpLabel = new JLabel("Z position:");
            tmpLabel.setDisplayedMnemonic('Z');
            tmpLabel.setLabelFor(positionSlider);
            tmpPane.add(tmpLabel);
            tmpPane.add(positionSlider);
            tmpPane.add(positionLabel);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JComboBox axisList;
    private JLabel positionLabel;
    private JSlider positionSlider;
}
