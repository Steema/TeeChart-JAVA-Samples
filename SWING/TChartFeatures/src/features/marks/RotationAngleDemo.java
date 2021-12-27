/*
 * RotationAngleDemo.java
 *
 * <p>Copyright: Copyright (c) 2004-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.marks;

import com.steema.teechart.styles.Area;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
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
public class RotationAngleDemo extends ChartSamplePanel
    implements ActionListener, ChangeListener {

    private Area series;

    /** Creates a new instance of RotationAngleDemo */
    public RotationAngleDemo() {
        super();
        angleSpinner.addChangeListener(this);
        resetButton.addActionListener(this);
        vertButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if (source == resetButton) {
            series.getMarks().setAngle(0);
        } else if (source == vertButton) {
            series.getMarks().setAngle(90);
        }
    }

    public void stateChanged(ChangeEvent e) {
        Object source = e.getSource();
        if (source == angleSpinner) {
            int angle = ((SpinnerNumberModel)((JSpinner)source).getModel()).getNumber().intValue();
            series.getMarks().setAngle(angle);
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getAspect().setElevation(31);
        chart1.getAspect().setPerspective(0);
        chart1.getAspect().setRotation(360);
    }

    protected void initComponents() {
        super.initComponents();

        series = new Area(chart1.getChart());
        series.getMarks().setAngle(45);
        series.getMarks().setArrowLength(37);
        series.getMarks().setVisible(true);
        series.getPointer().setVisible(false);
        series.fillSampleValues(5);

        vertButton = new JButton("Vertical");
        resetButton = new JButton("Reset");

        angleSpinner = new JSpinner(
            new SpinnerNumberModel(
                series.getMarks().getAngle(),
                0,
                360,
                5
            )
        );
        angleSpinner.setMaximumSize(new Dimension(50, 23));
    }

    protected void initGUI() {
        super.initGUI();
        JLabel tmpLabel;
        JPanel tmpPane = getButtonPane();
        {
            tmpLabel = new JLabel("Marks Angle:");
            tmpLabel.setDisplayedMnemonic('A');
            tmpLabel.setLabelFor(angleSpinner);
            tmpPane.add(tmpLabel);
            tmpPane.add(angleSpinner);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(resetButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(vertButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JButton resetButton, vertButton;
    private JSpinner angleSpinner;
}
