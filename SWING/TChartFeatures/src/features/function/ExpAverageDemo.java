/*
 * ExpAverageDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.function;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.functions.ExpAverage;
import com.steema.teechart.styles.CustomStack;
import com.steema.teechart.styles.Line;
import com.steema.teechart.styles.SeriesPointer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class ExpAverageDemo extends ChartSamplePanel
        implements ChangeListener, ItemListener {

    private Line sourceSeries, expAvgSeries;
    private ExpAverage expAvgFunction;

    /**
     * Creates a new instance of ExpAverageDemo
     */
    public ExpAverageDemo() {
        super();
        animateButton.addItemListener(this);
        weightSlider.addChangeListener(this);
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
        JSlider source = (JSlider)e.getSource();
        if (!source.getValueIsAdjusting()) {
            int tmp = (int)source.getValue();
            if (source == weightSlider) {
                setFunctionWeight(tmp / 100.0);
            }
        }
    }

    protected void initComponents() {
        super.initComponents();

        /* Chart Components */
        sourceSeries = new com.steema.teechart.styles.Line(chart1.getChart());
        sourceSeries.setTitle("Source");
        sourceSeries.setColor(Color.RED);
        sourceSeries.getPointer().setVisible(false);
        sourceSeries.fillSampleValues(50);

        expAvgSeries = new com.steema.teechart.styles.Line(chart1.getChart());
        expAvgSeries.setTitle("Exp. Average");
        expAvgSeries.setColor(Color.GREEN);
        expAvgSeries.getMarks().setVisible(false);
        expAvgSeries.getPointer().setVisible(false);
        SeriesPointer tmpPointer = expAvgSeries.getPointer();
        {
            tmpPointer.getBrush().setColor(Color.BLUE);
            tmpPointer.setHorizSize(2);
            tmpPointer.setVertSize(2);
            tmpPointer.setInflateMargins(true);
            tmpPointer.setVisible(true);
        }

        for (int i=0; i < chart1.getSeriesCount(); i++) {
            ((Line)chart1.getSeries(i)).getMarks().setVisible(false);
            ((Line)chart1.getSeries(i)).setStacked(CustomStack.NONE);
        }

        expAvgSeries.setDataSource(sourceSeries);

        expAvgFunction = new com.steema.teechart.functions.ExpAverage();
        expAvgFunction.setChart(chart1.getChart());
        expAvgFunction.setPeriod(1);

        expAvgSeries.setFunction(expAvgFunction);

        /* General GUI Components */
        animateButton = new JCheckBox("Animate");
        animateButton.setSelected(false);

        weightSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 20);

        weightLabel = new JLabel();

        setFunctionWeight(0.20);

        //Create a timer.
        timer = new Timer(ONE_MILLISECOND, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                /* stop timer */
                timer.stop();

                int pos = weightSlider.getValue()+delta;
                weightSlider.setValue(pos);
                if ((pos < 2) || (pos > 98)) {
                    delta = -delta;
                }

                /* re-enable timer again */
                timer.start();
            }
        });
    }

    protected void initGUI() {
        super.initGUI();

        chart1.getAspect().setView3D(false);
        chart1.getHeader().setVisible(true);
        chart1.setText("Exponential Average Function");

        JLabel tmpLabel;
        JPanel tmpPane = getButtonPane();
        {
            tmpLabel = new JLabel("Exponential Weight:");
            tmpLabel.setDisplayedMnemonic('E');
            tmpLabel.setLabelFor(weightSlider);
            tmpPane.add(tmpLabel);
            tmpPane.add(weightSlider);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpLabel.setLabelFor(weightLabel);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(animateButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private void setFunctionWeight(double newWeight) {
        try {
            expAvgFunction.setWeight(newWeight);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        weightLabel.setText(Double.toString(expAvgFunction.getWeight()));
    }

    private int delta = 2;

    private JCheckBox animateButton;
    private JSlider weightSlider;
    private JLabel weightLabel;
    private Timer timer;

    private final static int ONE_MILLISECOND = 1;
    private final static int MAX_CHART_SAMPLES = 200;

}
