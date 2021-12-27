/*
 * MovAveWeightedDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.function;

import com.steema.teechart.legend.Legend;
import com.steema.teechart.legend.LegendAlignment;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.functions.MovingAverage;
import com.steema.teechart.styles.Line;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class MovAveWeightedDemo extends ChartSamplePanel
    implements ItemListener {

    private Line sourceSeries;
    private MovingAverage movAveFunction;

    /**
     * Creates a new instance of MovAveWeightedDemo
     */
    public MovAveWeightedDemo() {
        super();
        weightedButton.addItemListener(this);
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);

        if (source == weightedButton) {
            movAveFunction.setWeighted(isSelected);
        };
    }

    protected void initComponents() {
        super.initComponents();

        sourceSeries = new com.steema.teechart.styles.Line(chart1.getChart());;
        sourceSeries.setTitle("Source");
        sourceSeries.getMarks().setVisible(false);
        sourceSeries.getPointer().setVisible(false);
        sourceSeries.setColor(Color.RED);
        sourceSeries.fillSampleValues(100);

        Line tmpSeries = new com.steema.teechart.styles.Line(chart1.getChart());;
        tmpSeries.setTitle("Moving Average");
        tmpSeries.setColor(Color.BLUE);
        tmpSeries.getMarks().setVisible(false);
        tmpSeries.getPointer().setVisible(false);

        movAveFunction = new com.steema.teechart.functions.MovingAverage(chart1.getChart());
        movAveFunction.setPeriod(10);
        movAveFunction.setWeighted(true);

        tmpSeries.setDataSource(sourceSeries);
        tmpSeries.setFunction(movAveFunction);

        weightedButton = new JCheckBox("Weighted");
        weightedButton.setSelected(true);
    }

    protected void initGUI() {
        super.initGUI();

        chart1.getAspect().setView3D(false);

        chart1.getHeader().setVisible(true);
        chart1.setText("Weighted Moving Average Function");

        Legend tmpLegend = chart1.getLegend();
        tmpLegend.setAlignment(LegendAlignment.BOTTOM);

        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(weightedButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JCheckBox weightedButton;
}
