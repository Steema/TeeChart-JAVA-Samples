/*
 * DivisionDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.function.momentum;

import com.steema.teechart.axis.Axis;
import com.steema.teechart.legend.LegendAlignment;
import com.steema.teechart.legend.LegendStyle;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.functions.MomentumDivision;
import com.steema.teechart.styles.Line;
import com.steema.teechart.tools.ColorLine;
import com.steema.teechart.tools.ColorLineStyle;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.Box;
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
public class DivisionDemo extends ChartSamplePanel
    implements ChangeListener, ItemListener {

    private Line sourceSeries, functionSeries;
    private MomentumDivision momentumFunction;
    private ColorLine colorLineTool;
    private Axis customAxis;

    /**
     * Creates a new instance of DivisionDemo
     */
    public DivisionDemo() {
        super();
        periodSpinner.addChangeListener(this);
        showMomentumButton.addItemListener(this);
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);

        if (source == showMomentumButton) {
            /* show / hide the momentum series */
            functionSeries.setActive(isSelected);

            /* re-position the axis */
            if (functionSeries.getActive()) {
                chart1.getAxes().getLeft().setEndPosition(80);
            } else {
                chart1.getAxes().getLeft().setEndPosition(100);
            }

            /* show / hide the custom right axis */
            chart1.getAxes().getCustom().getAxis(0).setVisible(functionSeries.getActive());

            /* show / hide the blue color line */
            colorLineTool.setActive(functionSeries.getActive());
        };
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

        /* create custom axis */
        customAxis = chart1.getAxes().getCustom().getNew();
        customAxis.setHorizontal(false);
        customAxis.setOtherSide(true);
        customAxis.setStartPosition(80.0);
        customAxis.getTitle().setAngle(90);
        customAxis.getTitle().setText("Mom. Div.");
        customAxis.getTitle().setVisible(true);
        chart1.getAxes().getLeft().setEndPosition(80);

        sourceSeries = new com.steema.teechart.styles.Line(chart1.getChart());
        sourceSeries.getMarks().setVisible(false);
        sourceSeries.getPointer().setVisible(false);
        sourceSeries.setTitle("Source");
        sourceSeries.setColor(Color.RED);
        sourceSeries.getLinePen().setColor(Color.RED);
        sourceSeries.fillSampleValues(50);

        momentumFunction = new com.steema.teechart.functions.MomentumDivision(chart1.getChart());
        /*{ function is = 100 * Value / (Previous 10th value) */
        momentumFunction.setPeriod(10);

        functionSeries = new com.steema.teechart.styles.Line(chart1.getChart());
        functionSeries.setTitle("Momentum Div.");
        functionSeries.setColor(Color.GREEN);
        functionSeries.getLinePen().setColor(Color.GREEN);
        functionSeries.getMarks().setVisible(false);
        functionSeries.getPointer().setVisible(false);
        functionSeries.setCustomVertAxis(customAxis);
        functionSeries.setDataSource(sourceSeries);
        functionSeries.setFunction(momentumFunction);

        colorLineTool = new ColorLine(chart1.getChart());
        colorLineTool.getPen().setColor(Color.BLUE);
        colorLineTool.setStyle(ColorLineStyle.MINIMUM);
        colorLineTool.setValue(480.0);
        colorLineTool.setAxis(sourceSeries.getVertAxis());

        showMomentumButton = new JCheckBox("Show Momentum");
        showMomentumButton.setSelected(true);

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
        chart1.getHeader().setVisible(false);
        chart1.getAspect().setView3D(false);
        chart1.getLegend().setAlignment(LegendAlignment.BOTTOM);
        chart1.getLegend().setLegendStyle(LegendStyle.SERIES);
        chart1.getPanel().setMarginRight(15.0);

        JLabel tmpLabel;
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(showMomentumButton);
            tmpPane.add(Box.createHorizontalStrut(20));
            tmpLabel = new JLabel("Period:");
            tmpLabel.setDisplayedMnemonic('P');
            tmpLabel.setLabelFor(periodSpinner);
            tmpPane.add(tmpLabel);
            tmpPane.add(periodSpinner);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JCheckBox showMomentumButton;
    private JSpinner periodSpinner;
}
