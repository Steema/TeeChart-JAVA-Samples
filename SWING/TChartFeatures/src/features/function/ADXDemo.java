/*
 * ADXDem.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.function;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.functions.ADX;
import com.steema.teechart.styles.Candle;
import com.steema.teechart.styles.Line;
import com.steema.teechart.styles.VerticalAxis;

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
public class ADXDemo extends ChartSamplePanel
    implements ChangeListener {

    private Candle sourceSeries;
    private ADX adxFunction;

    /**
     * Creates a new instance of MomentumDemo
     */
    public ADXDemo() {
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
                        adxFunction.setPeriod(period);
                    }
                }
            }
        }
    }
    protected void initComponents() {
        super.initComponents();

        sourceSeries = new com.steema.teechart.styles.Candle(chart1.getChart());
        sourceSeries.getMarks().setVisible(false);
        sourceSeries.getPointer().setVisible(true);
        sourceSeries.setTitle("Source");
        sourceSeries.setColor(Color.RED);
        sourceSeries.getLinePen().setColor(Color.RED);
        sourceSeries.getVertAxis().setStartPosition(0);
        sourceSeries.getVertAxis().setEndPosition(70);
        sourceSeries.fillSampleValues(100);

        adxFunction = new com.steema.teechart.functions.ADX(chart1.getChart());
        adxFunction.setPeriod(10);


        Line functionSeries = new com.steema.teechart.styles.Line(chart1.getChart());
        functionSeries.setTitle("ADX");
        functionSeries.setColor(Color.GREEN);
        functionSeries.getLinePen().setColor(Color.GREEN);
        functionSeries.getMarks().setVisible(false);
        functionSeries.getPointer().setVisible(true);
        functionSeries.setVerticalAxis(VerticalAxis.RIGHT);
        functionSeries.getVertAxis().setStartPosition(70);
        functionSeries.getVertAxis().setEndPosition(100);
        functionSeries.setDataSource(sourceSeries);
        functionSeries.setFunction(adxFunction);

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
        chart1.setText("ADX Function Example");
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

