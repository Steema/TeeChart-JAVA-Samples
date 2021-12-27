/*
 * MomentumDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.function.momentum;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.functions.Momentum;
import com.steema.teechart.styles.Line;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class MomentumDemo extends ChartSamplePanel
    implements ChangeListener {

    private Line sourceSeries;
    private Momentum momentumFunction;

    /**
     * Creates a new instance of MomentumDemo
     */
    public MomentumDemo() {
        super();
        periodSpinner.addChangeListener(this);
    }

    public void stateChanged(ChangeEvent e) {
        Object source = e.getSource();
        if (source instanceof JSpinner) {
            SpinnerModel tmpModel = ((JSpinner)source).getModel();
            if (tmpModel instanceof SpinnerNumberModel) {
                int period = ((SpinnerNumberModel)tmpModel).getNumber().intValue();
                if ((period > 0) && (period < 101)) {
                    if (source == periodSpinner) {
                        momentumFunction.setPeriod(period);
                    }
                }
            }
        }
    }
    protected void initComponents() {
        super.initComponents();

        sourceSeries = new com.steema.teechart.styles.Line(chart1.getChart());
        sourceSeries.getMarks().setVisible(false);
        sourceSeries.getPointer().setVisible(true);
        sourceSeries.setTitle("Source");
        sourceSeries.setColor(Color.RED);
        sourceSeries.getLinePen().setColor(Color.RED);
        sourceSeries.fillSampleValues(30);

        momentumFunction = new com.steema.teechart.functions.Momentum(chart1.getChart());
        momentumFunction.setPeriod(10);


        Line functionSeries = new com.steema.teechart.styles.Line(chart1.getChart());
        functionSeries.setDataSource(sourceSeries);
        functionSeries.setFunction(momentumFunction);
        functionSeries.setTitle("Momentum");
        functionSeries.setColor(Color.GREEN);
        functionSeries.getLinePen().setColor(Color.GREEN);
        functionSeries.getMarks().setVisible(false);
        functionSeries.getPointer().setVisible(true);

        SpinnerModel tmpModel;
        tmpModel = new SpinnerNumberModel(
                10,
                1,
                100,
                1);
        periodSpinner = new JSpinner(tmpModel);
    }

    protected void initGUI() {
        super.initGUI();
        chart1.getHeader().setVisible(true);
        chart1.setText("Momentum Function Example");
        chart1.getAspect().setView3D(false);

        JLabel tmpLabel;
        JPanel tmpPane = getButtonPane();
        {
            tmpLabel = new JLabel("Period:");
            tmpLabel.setDisplayedMnemonic('P');
            tmpLabel.setLabelFor(periodSpinner);
            tmpPane.add(tmpLabel);
            tmpPane.add(periodSpinner);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JSpinner periodSpinner;
}
