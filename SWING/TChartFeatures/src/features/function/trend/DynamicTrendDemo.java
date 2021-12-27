/*
 * DynamicTrendDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.function.trend;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.GradientDirection;
import com.steema.teechart.functions.CurveFitting;
import com.steema.teechart.styles.Area;
import com.steema.teechart.styles.FastLine;
import com.steema.teechart.styles.ISeries;
import com.steema.teechart.styles.Line;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
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
public class DynamicTrendDemo extends ChartSamplePanel
        implements ChangeListener, ItemListener {

    private FastLine dataSeries;
    private Area areaSeries;
    private Line lineSeries;
    private CurveFitting fitting1Function, fitting2Function;

    /**
     * Creates a new instance of DynamicTrendDemo
     */
    public DynamicTrendDemo() {
        super();
        animateButton.addItemListener(this);
        posAreaSlider.addChangeListener(this);
        posLineSlider.addChangeListener(this);
        sizeAreaSlider.addChangeListener(this);
        sizeLineSlider.addChangeListener(this);
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
            if (source == posAreaSlider) {
                moveFunction(fitting1Function, tmp);
            } else if (source == posLineSlider) {
                moveFunction(fitting2Function, tmp);
            } else if (source == sizeAreaSlider) {
                resizeFunction(fitting1Function, tmp);
            } else if (source == sizeLineSlider) {
                resizeFunction(fitting2Function, tmp);
            }
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getWalls().getBack().getBrush().setColor(Color.WHITE);
        chart1.getWalls().getBack().setSize(20);
        chart1.getWalls().getBottom().setSize(20);
        chart1.getWalls().getLeft().setSize(20);
        chart1.getPanel().getGradient().setDirection(GradientDirection.FORWARDDIAGONAL);
        chart1.getPanel().getGradient().setEndColor(Color.GRAY);
        chart1.getPanel().getGradient().setVisible(true);
        chart1.getLegend().setColor(new Color(88454016));
        chart1.getLegend().setColorWidth(35);
        chart1.getLegend().getSymbol().setWidth(35);
        chart1.getAxes().getLeft().getLabels().getFont().setBold(true);
        chart1.getAspect().setChart3DPercent(35);
        chart1.getAspect().setElevation(347);
        chart1.getAspect().setOrthogonal(false);
        chart1.getAspect().setPerspective(55);
        chart1.getAspect().setRotation(348);
        chart1.getAspect().setZoom(90);
    }

    protected void initComponents() {
        super.initComponents();

        /* Chart Components */
        dataSeries = new com.steema.teechart.styles.FastLine(chart1.getChart());
        dataSeries.fillSampleValues(280);

        areaSeries = new com.steema.teechart.styles.Area(chart1.getChart());
        areaSeries.setDataSource(dataSeries);
        areaSeries.setColor(Color.BLUE);

        lineSeries = new com.steema.teechart.styles.Line(chart1.getChart());
        lineSeries.setDataSource(dataSeries);
        lineSeries.setColor(Color.YELLOW);

        for (int i=0; i < chart1.getSeriesCount(); i++) {
            chart1.getSeries(i).getMarks().setVisible(false);
        }

        fitting1Function = new com.steema.teechart.functions.CurveFitting(chart1.getChart());
        fitting1Function.setPeriod(1);
        fitting1Function.setPolyDegree(2);

        /* set function first and last points */
        fitting1Function.setFirstPoint(10);
        fitting1Function.setLastPoint(40);
        fitting1Function.setFirstCalcPoint(10);
        fitting1Function.setLastCalcPoint(40);

        areaSeries.setFunction(fitting1Function);

        fitting2Function = new com.steema.teechart.functions.CurveFitting(chart1.getChart());
        fitting2Function.setPeriod(1);

        /* set function first and last points */
        fitting2Function.setFirstPoint(40);
        fitting2Function.setLastPoint(70);
        fitting2Function.setFirstCalcPoint(40);
        fitting2Function.setLastCalcPoint(70);

        lineSeries.setFunction(fitting2Function);

        /* General GUI Components */
        animateButton = new JCheckBox("Animate");
        animateButton.setSelected(false);

        //Create a timer.
        timer = new Timer(ONE_MILLISECOND, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                /* stop timer */
                timer.stop();

                if (posAreaSlider.getValue() < posAreaSlider.getMaximum()-1) {
                    posAreaSlider.setValue(posAreaSlider.getValue()+1);
                } else {
                    posAreaSlider.setValue(posAreaSlider.getMinimum());
                }

                if (posLineSlider.getValue() < posLineSlider.getMaximum()-1) {
                    posLineSlider.setValue(posLineSlider.getValue()+1);
                } else {
                    posLineSlider.setValue(posLineSlider.getMinimum());
                }

                /* re-enable timer again */
                timer.start();
            }
        });

        posAreaSlider = new JSlider(JSlider.HORIZONTAL, 1, dataSeries.getCount(), 1);
        posLineSlider = new JSlider(JSlider.HORIZONTAL, 1, dataSeries.getCount(), 1);

        sizeAreaSlider = new JSlider(JSlider.HORIZONTAL, 3, 100, 30);
        sizeLineSlider = new JSlider(JSlider.HORIZONTAL, 3, 100, 30);
    }

    protected void initGUI() {
        super.initGUI();

        JLabel tmpLabel;
        JPanel groupPane;
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(animateButton);
            tmpPane.add(Box.createHorizontalStrut(10));

            groupPane = new JPanel();
            groupPane.setBorder(BorderFactory.createTitledBorder("Blue Area Trend"));
            groupPane.setLayout(new BoxLayout(groupPane, BoxLayout.PAGE_AXIS));
            groupPane.add(new JLabel("Position:"));
            groupPane.add(posAreaSlider);
            groupPane.add(new JLabel("Size:"));
            groupPane.add(sizeAreaSlider);

            tmpPane.add(groupPane);
            tmpPane.add(Box.createHorizontalStrut(10));

            groupPane = new JPanel();
            groupPane.setBorder(BorderFactory.createTitledBorder("Yellow Curve Fitting"));
            groupPane.setLayout(new BoxLayout(groupPane, BoxLayout.PAGE_AXIS));
            groupPane.add(new JLabel("Position:"));
            groupPane.add(posLineSlider);
            groupPane.add(new JLabel("Size:"));
            groupPane.add(sizeLineSlider);

            tmpPane.add(groupPane);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private void moveFunction(CurveFitting function,  int pos) {
        int tmp = function.getLastPoint() - function.getFirstPoint();
        ISeries parentSource = (ISeries)(function. getSeries().getDataSource());
        if ((pos+tmp) < parentSource.getCount()) {
            function.setFirstPoint(pos);
            function.setLastPoint(function.getFirstPoint()+tmp);
            function.setFirstCalcPoint(function.getFirstPoint());
            function.setLastCalcPoint(function.getLastPoint());
        }
    }

    private void resizeFunction(CurveFitting function, int pos) {
        ISeries parentSource = (ISeries)(function. getSeries().getDataSource());
        if ((function.getFirstPoint()+pos) < parentSource.getCount()) {
            function.setLastPoint(function.getFirstPoint()+pos);
            function.setFirstCalcPoint(function.getFirstPoint());
            function.setLastCalcPoint(function.getLastPoint());
            posAreaSlider.setMaximum(dataSeries.getCount()-pos);
        }
    }

    private JCheckBox animateButton;
    private JSlider posAreaSlider, sizeAreaSlider;
    private JSlider posLineSlider, sizeLineSlider;

    private Timer timer;

    private final static int ONE_MILLISECOND = 1;
    private final static int MAX_CHART_SAMPLES = 200;

}
