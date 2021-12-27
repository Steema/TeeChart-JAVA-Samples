/*
 * MovAveDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.function;

import com.steema.teechart.axis.Axis;
import com.steema.teechart.Header;
import com.steema.teechart.legend.Legend;
import com.steema.teechart.legend.LegendAlignment;
import com.steema.teechart.TextShapeStyle;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.functions.CurveFitting;
import com.steema.teechart.functions.ExpAverage;
import com.steema.teechart.functions.MovingAverage;
import com.steema.teechart.styles.Line;

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
public class MovAveDemo extends ChartSamplePanel
        implements ItemListener, ChangeListener {

    private Line priceSeries, expAveSeries, movingAve1Series, movingAve2Series;
    private ExpAverage expAveFunction;

    /**
     * Creates a new instance of MovAveDemo
     */
    public MovAveDemo() {
        super();
        animateButton.addItemListener(this);
        funButton.addItemListener(this);
        greenPeriodSpinner.addChangeListener(this);
        yellowPeriodSpinner.addChangeListener(this);
        blueWeightSpinner.addChangeListener(this);
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
        } else if (source == funButton) {
            /* switch between 2D and 3D and start animating... */
            chart1.getAspect().setView3D(isSelected);
            chart1.getAxes().setVisible(!isSelected);
            chart1.getWalls().setView3D(!isSelected);
            chart1.getAspect().setChart3DPercent(20);
            chart1.setClipPoints(!isSelected);
            chart1.getFrame().setVisible(!isSelected);
            chart1.getHeader().setVisible(!isSelected);
            chart1.getFooter().setVisible(!isSelected);
            chart1.getPanel().getGradient().setVisible(isSelected);
            if (isSelected) {
                chart1.getLegend().setAlignment(LegendAlignment.BOTTOM);
            } else {
                chart1.getLegend().setAlignment(LegendAlignment.LEFT);
            }

            if (isSelected) { animateButton.setSelected(true); };
        }
    }

    public void stateChanged(ChangeEvent e) {
        Object source = e.getSource();
        if (source instanceof JSpinner) {
            SpinnerModel tmpModel = ((JSpinner)source).getModel();
            if (tmpModel instanceof SpinnerNumberModel) {
                int tmpValue = ((SpinnerNumberModel)tmpModel).getNumber().intValue();
                if ((tmpValue > 0) && (tmpValue < 101)) {
                    if (source == greenPeriodSpinner) {
                        movingAve1Series.getFunction().setPeriod(tmpValue);
                    } else if (source == yellowPeriodSpinner) {
                        movingAve2Series.getFunction().setPeriod(tmpValue);
                    } else if (source == blueWeightSpinner) {
                        try {
                            expAveFunction.setWeight( tmpValue / 100.0 );
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                    }
                }
            }
        }
    }

    protected void initComponents() {
        super.initComponents();

        initSeries();

        //Create a timer.
        timer = new Timer(ONE_MILLISECOND, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                /* stop timer */
                timer.stop();

                Random generator = new Random();

                /* delete first point */
                priceSeries.delete(0);

                /* @TODO recalcOptions! */
                movingAve1Series.delete(0);
                movingAve2Series.delete(0);

                /* add a new random point */
                priceSeries.add(
                        priceSeries.getXValues().getLast()+1,
                        priceSeries.getYValues().getLast()+(generator.nextInt(MAX_CHART_SAMPLES)-(MAX_CHART_SAMPLES / 2)),
                        "",
                        priceSeries.getColor()
                        );

                /* Recalculate Averages */
                priceSeries.refreshSeries();

                /* re-enable timer again */
                timer.start();
            }
        });

        animateButton = new JCheckBox("Animate");
        funButton = new JCheckBox("Fun!");

        SpinnerModel tmpModel;
        tmpModel = new SpinnerNumberModel(
                20,
                0,
                100,
                5);
        greenPeriodSpinner = new JSpinner(tmpModel);
        tmpModel = new SpinnerNumberModel(
                40,
                0,
                100,
                5);
        yellowPeriodSpinner = new JSpinner(tmpModel);
        tmpModel = new SpinnerNumberModel(
                20,
                0,
                100,
                5);
        blueWeightSpinner = new JSpinner(tmpModel);
    }

    protected void initGUI() {
        super.initGUI();

        chart1.getHeader().setVisible(true);
        chart1.setText("Moving Averages");

        Legend tmpLegend = chart1.getLegend();
        tmpLegend.setAlignment(LegendAlignment.LEFT);
        tmpLegend.getGradient().setVisible(true);
        tmpLegend.setShapeStyle(TextShapeStyle.ROUNDRECTANGLE);

        Header tmpHeader = chart1.getHeader();
        tmpHeader.getFont().setColor(Color.WHITE);
        tmpHeader.getFont().setSize(13);

        Axis tmpAxis;
        tmpAxis = chart1.getAxes().getBottom();
        tmpAxis.getLabels().setDateTimeFormat("dd-MMM");
        tmpAxis.setIncrement(3.0);
        tmpAxis.getLabels().setAngle(90);
        tmpAxis.getLabels().getFont().setColor(Color.GRAY);
        tmpAxis.getTitle().setCaption("Stock Market Date");
        tmpAxis.getTitle().getFont().setColor(Color.GREEN);
        tmpAxis.getTitle().getFont().setSize(12);
        tmpAxis.getTitle().getFont().setItalic(true);

        tmpAxis = chart1.getAxes().getLeft();
        tmpAxis.getLabels().getFont().setColor(Color.WHITE);
        tmpAxis.getLabels().getFont().setSize(12);
        tmpAxis.getTitle().setCaption("Stock Price");
        tmpAxis.getTitle().getFont().setColor(Color.NAVY);
        tmpAxis.getTitle().getFont().setSize(12);
        tmpAxis.getTitle().getFont().setBold(true);

        chart1.getAspect().setView3D(false);
        chart1.getZoom().setAnimated(true);

        JLabel tmpLabel;
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(animateButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(new JLabel("Green Period:"));
            tmpPane.add(greenPeriodSpinner);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(new JLabel("Yellow Period:"));
            tmpPane.add(yellowPeriodSpinner);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpLabel = new JLabel("Blue Weight %:");
            tmpLabel.setDisplayedMnemonic('B');
            tmpLabel.setLabelFor(blueWeightSpinner);
            tmpPane.add(tmpLabel);
            tmpPane.add(blueWeightSpinner);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(funButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private void initSeries() {
        priceSeries = new com.steema.teechart.styles.Line(chart1.getChart());
        priceSeries.setTitle("Price");
        priceSeries.setColor(Color.RED);
        priceSeries.fillSampleValues(200);

        Line curveSeries = new com.steema.teechart.styles.Line(chart1.getChart());
        curveSeries.setTitle("Curve1");
        curveSeries.setColor(Color.AQUA);
        curveSeries.setDataSource(priceSeries);
        curveSeries.setFunction(new CurveFitting(chart1.getChart()));
        curveSeries.getFunction().setPeriod(1);

        movingAve1Series = new com.steema.teechart.styles.Line(chart1.getChart());
        movingAve1Series.setTitle("MovAve1");
        movingAve1Series.setColor(Color.GREEN);
        movingAve1Series.setDataSource(priceSeries);
        movingAve1Series.setFunction(new MovingAverage(chart1.getChart()));
        movingAve1Series.getFunction().setPeriod(20);

        movingAve2Series = new com.steema.teechart.styles.Line(chart1.getChart());
        movingAve2Series.setTitle("MovAve2");
        movingAve2Series.setColor(Color.YELLOW);
        movingAve2Series.setDataSource(priceSeries);
        movingAve2Series.setFunction(new MovingAverage(chart1.getChart()));
        movingAve2Series.getFunction().setPeriod(40);

        expAveFunction = new ExpAverage(chart1.getChart());
        try {
            expAveFunction.setWeight(0.2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        expAveSeries = new com.steema.teechart.styles.Line(chart1.getChart());
        expAveSeries.setTitle("ExpAve1");
        expAveSeries.setColor(Color.BLUE);
        expAveSeries.setDataSource(priceSeries);
        expAveSeries.setFunction(expAveFunction);

        for (int i=0; i < chart1.getSeriesCount(); i++) {

            Line tmpSeries = (Line)(chart1.getSeries(i));
            tmpSeries.getMarks().setVisible(false);
            tmpSeries.getPointer().setVisible(false);
            tmpSeries.getXValues().setDateTime(true);
            tmpSeries.getYValues().setDateTime(false);

        }
    }

    private JCheckBox animateButton, funButton;
    private JSpinner greenPeriodSpinner, yellowPeriodSpinner, blueWeightSpinner;
    private Timer timer;

    private final static int ONE_MILLISECOND = 1;
    private final static int MAX_CHART_SAMPLES = 1000;
}
