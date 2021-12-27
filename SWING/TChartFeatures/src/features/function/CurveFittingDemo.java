/*
 * CurveFittingDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.function;

import com.steema.teechart.legend.LegendAlignment;
import com.steema.teechart.functions.CurveFitting;
import com.steema.teechart.styles.FastLine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Random;

import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class CurveFittingDemo extends ChartSamplePanel
        implements ChangeListener, ItemListener {

    private FastLine dataSeries, curve1Series, curve2Series;
    private CurveFitting fitting1Function, fitting2Function;

    /**
     * Creates a new instance of CurveFittingDemo
     */
    public CurveFittingDemo() {
        super();
        animateButton.addItemListener(this);
        degree1Spinner.addChangeListener(this);
        degree2Spinner.addChangeListener(this);
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);

        if (source == animateButton) {
            if (isSelected) {
                timer.start();
            } else {
                timer.stop();
            }
        };
    }

    public void stateChanged(ChangeEvent e) {
        Object source = e.getSource();
        if (source instanceof JSpinner) {
            SpinnerModel tmpModel = ((JSpinner)source).getModel();
            if (tmpModel instanceof SpinnerNumberModel) {
                int degree = ((SpinnerNumberModel)tmpModel).getNumber().intValue();
                if (degree > 0) {
                    if (source == degree1Spinner) {
                        fitting1Function.setPolyDegree(degree);
                    } else if (source == degree2Spinner) {
                        fitting2Function.setPolyDegree(degree);
                    }
                }
            }
        }
    }

    protected void initComponents() {
        super.initComponents();

        /* Chart Components */
        dataSeries = new com.steema.teechart.styles.FastLine(chart1.getChart());
        dataSeries.fillSampleValues(200);
        dataSeries.setTitle("data");

        curve1Series = new com.steema.teechart.styles.FastLine(chart1.getChart());
        curve2Series = new com.steema.teechart.styles.FastLine(chart1.getChart());

        for (int i=0; i < chart1.getSeriesCount(); i++) {
            chart1.getSeries(i).getMarks().setVisible(false);
            ((FastLine)chart1.getSeries(i)).setStairs(false);
        }

        fitting1Function = new com.steema.teechart.functions.CurveFitting(chart1.getChart());
        fitting1Function.setPeriod(1);
        fitting1Function.setPolyDegree(5);

        curve1Series.setDataSource(dataSeries);
        curve1Series.setFunction(fitting1Function);
        curve1Series.setTitle("Curve1");

        fitting2Function = new com.steema.teechart.functions.CurveFitting();
        fitting2Function.setChart(chart1.getChart());
        fitting2Function.setPeriod(1);
        fitting2Function.setPolyDegree(3);

        curve2Series.setDataSource(dataSeries);
        curve2Series.setFunction(fitting2Function);
        curve2Series.setTitle("Curve2");

        /* General GUI Components */
        animateButton = new JCheckBox("Animate");
        animateButton.setSelected(false);

        SpinnerModel tmpModel;
        tmpModel = new SpinnerNumberModel(
                5,
                2,
                20,
                1);
        degree1Spinner = new JSpinner(tmpModel);
        tmpModel = new SpinnerNumberModel(
                3,
                2,
                20,
                1);
        degree2Spinner = new JSpinner(tmpModel);

        //Create a timer.
        timer = new Timer(ONE_MILLISECOND, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                /* stop timer */
                timer.stop();

                Random generator = new Random();

                /* delete first point */
                dataSeries.delete(0);

                /* add a new random point */
                dataSeries.add(
                        dataSeries.getXValues().getLast()+1,
                        dataSeries.getYValues().getLast()+(generator.nextInt(MAX_CHART_SAMPLES)-(MAX_CHART_SAMPLES / 2)),
                        "",
                        dataSeries.getColor()
                        );

                curve1Series.checkDataSource();  // <-- fill again the points
                curve2Series.checkDataSource();  // <-- fill again the points

                /* re-enable timer again */
                timer.start();
            }
        });
    }

    protected void initGUI() {
        super.initGUI();

        chart1.getAspect().setView3D(false);
        chart1.getHeader().setVisible(true);
        chart1.setText("Curve Fitting");
        chart1.getLegend().setAlignment(LegendAlignment.BOTTOM);

        JLabel tmpLabel;
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(animateButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpLabel = new JLabel("Poly. degree 1:");
            tmpLabel.setDisplayedMnemonic('1');
            tmpLabel.setLabelFor(degree1Spinner);
            tmpPane.add(tmpLabel);
            tmpPane.add(degree1Spinner);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpLabel = new JLabel("2:");
            tmpLabel.setDisplayedMnemonic('2');
            tmpLabel.setLabelFor(degree2Spinner);
            tmpPane.add(tmpLabel);
            tmpPane.add(degree2Spinner);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JCheckBox animateButton;
    private JSpinner degree1Spinner, degree2Spinner;
    private Timer timer;

    private final static int ONE_MILLISECOND = 1;
    private final static int MAX_CHART_SAMPLES = 1000;

}
