/*
 * SkipMarksDemo.java
 *
 * <p>Copyright: Copyright (c) 2004-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.marks;

import com.steema.teechart.styles.Line;
import java.awt.Dimension;
import javax.swing.Box;
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
public class SkipMarksDemo extends ChartSamplePanel
    implements ChangeListener {

    private Line series;

    /**
     * Creates a new instance of SkipMarksDemo
     */
    public SkipMarksDemo() {
        super();
        skipSpinner.addChangeListener(this);
    }

    public void stateChanged(ChangeEvent e) {
        Object source = e.getSource();
        if (source == skipSpinner) {
            int value = ((SpinnerNumberModel)((JSpinner)source).getModel()).getNumber().intValue();
            series.getMarks().setDrawEvery(value);
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getLegend().setVisible(false);
    }

    protected void initComponents() {
        super.initComponents();

        series = new Line(chart1.getChart());
        series.getMarks().setDrawEvery(10);
        series.getMarks().setArrowLength(8);
        series.getMarks().setVisible(true);
        series.getPointer().setVisible(false);
        series.fillSampleValues(50);

        skipSpinner = new JSpinner(
            new SpinnerNumberModel(
                series.getMarks().getDrawEvery(),
                1,
                series.getCount(),
                1
            )
        );
        skipSpinner.setMaximumSize(new Dimension(50, 23));
    }

    protected void initGUI() {
        super.initGUI();
        JLabel tmpLabel;
        JPanel tmpPane = getButtonPane();
        {
            tmpLabel = new JLabel("Draw Every:");
            tmpLabel.setDisplayedMnemonic('E');
            tmpLabel.setLabelFor(skipSpinner);
            tmpPane.add(tmpLabel);
            tmpPane.add(skipSpinner);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JSpinner skipSpinner;
}
