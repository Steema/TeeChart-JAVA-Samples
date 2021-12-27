/*
 * SmoothingDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.function;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.functions.Smoothing;
import com.steema.teechart.styles.Line;
import com.steema.teechart.styles.PointerStyle;
import com.steema.teechart.styles.SeriesPointer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
public class SmoothingDemo extends ChartSamplePanel
    implements ActionListener, ChangeListener, ItemListener {

    private Line sourceSeries;
    private Smoothing smoothingFunction;

    /**
     * Creates a new instance of SmoothingDemo
     */
    public SmoothingDemo() {
        super();
        viewPointsButton.addItemListener(this);
        viewSourceButton.addItemListener(this);
        interpolateButton.addItemListener(this);
        factorSpinner.addChangeListener(this);
        randomButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == randomButton) {
            sourceSeries.fillSampleValues();
        }
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == viewPointsButton) {
            sourceSeries.getPointer().setVisible(isSelected);
        } else if (source == viewSourceButton) {
            sourceSeries.setVisible(isSelected);
        } else if (source == interpolateButton) {
            smoothingFunction.setInterpolate(isSelected);
        }
    }

    public void stateChanged(ChangeEvent e) {
        Object source = e.getSource();
        if (source instanceof JSpinner) {
            SpinnerModel tmpModel = ((JSpinner)source).getModel();
            if (tmpModel instanceof SpinnerNumberModel) {
                int factor = ((SpinnerNumberModel)tmpModel).getNumber().intValue();
                if ((factor > 0) && (factor < 101)) {
                    if (source == factorSpinner) {
                        smoothingFunction.setFactor(factor);
                    }
                }
            }
        }
    }

    protected void initComponents() {
        super.initComponents();

        sourceSeries = new com.steema.teechart.styles.Line(chart1.getChart());
        sourceSeries.getMarks().setVisible(false);
        sourceSeries.setTitle("Source");
        sourceSeries.setColor(Color.RED);
        sourceSeries.getLinePen().setColor(Color.RED);
        sourceSeries.getLinePen().setWidth(2);
        sourceSeries.fillSampleValues(10);

        SeriesPointer tmpPointer = sourceSeries.getPointer();
        tmpPointer.setInflateMargins(true);
        tmpPointer.getBrush().setColor(Color.YELLOW);
        tmpPointer.getPen().setColor(Color.OLIVE);
        tmpPointer.setStyle(PointerStyle.TRIANGLE);
        tmpPointer.setVisible(true);


        smoothingFunction = new com.steema.teechart.functions.Smoothing(chart1.getChart());
        smoothingFunction.setPeriod(1);
        smoothingFunction.setInterpolate(false);
        smoothingFunction.setFactor(4);

        Line functionSeries = new com.steema.teechart.styles.Line(chart1.getChart());
        functionSeries.setDataSource(sourceSeries);
        functionSeries.setFunction(smoothingFunction);
        functionSeries.setTitle("Smooth");
        functionSeries.setColor(Color.GREEN);
        functionSeries.getMarks().setVisible(false);
        functionSeries.getPointer().setVisible(false);

        viewPointsButton = new JCheckBox("View points");
        viewPointsButton.setSelected(true);
        viewSourceButton = new JCheckBox("View source");
        viewSourceButton.setSelected(true);
        interpolateButton = new JCheckBox("Interpolate");
        interpolateButton.setSelected(false);

        SpinnerModel tmpModel;
        tmpModel = new SpinnerNumberModel(
                4,
                1,
                100,
                1);
        factorSpinner = new JSpinner(tmpModel);

        randomButton = new JButton("Random!");
    }

    protected void initGUI() {
        super.initGUI();
        chart1.getHeader().setVisible(true);
        chart1.setText("Smoothing using Splines");
        chart1.getAspect().setView3D(false);

        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(viewPointsButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(viewSourceButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(interpolateButton);
            tmpPane.add(Box.createHorizontalStrut(20));
            tmpPane.add(new JLabel("Factor:"));
            tmpPane.add(factorSpinner);
            tmpPane.add(Box.createHorizontalStrut(20));
            tmpPane.add(randomButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JButton randomButton;
    private JCheckBox viewPointsButton, viewSourceButton, interpolateButton;
    private JSpinner factorSpinner;
}
