/*
 * HistogramFunctionDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.function;

import com.steema.teechart.TChart;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.functions.HistogramFunction;
import com.steema.teechart.styles.Histogram;
import com.steema.teechart.styles.Line;

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
import javax.swing.JSplitPane;

/**
 *
 * @author narcis
 */
public class HistogramFunctionDemo extends ChartSamplePanel
    implements ActionListener, ChangeListener, ItemListener {

    private Line sourceSeries;
    private HistogramFunction histogramFunction;

    /**
     * Creates a new instance of SmoothingDemo
     */
    public HistogramFunctionDemo() {
        super();
        cumulativeButton.addItemListener(this);
        binsSpinner.addChangeListener(this);
        randomButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == randomButton) {
            sourceSeries.fillSampleValues(500);
            histogramFunction.recalculate();
        }
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == cumulativeButton) {
            histogramFunction.setCumulative(isSelected);
            if (histogramFunction.getCumulative()) {
                funcChart.setText("Cumulative Histogram"); 
            }
            else {
                funcChart.setText("Histogram");
            }
            histogramFunction.recalculate();
        } 
    }

    public void stateChanged(ChangeEvent e) {
        Object source = e.getSource();
        if (source instanceof JSpinner) {
            SpinnerModel tmpModel = ((JSpinner)source).getModel();
            if (tmpModel instanceof SpinnerNumberModel) {
                int bins = ((SpinnerNumberModel)tmpModel).getNumber().intValue();
                if ((bins > 0) && (bins < 101)) {
                    if (source == binsSpinner) {
                        histogramFunction.setNumBins(bins);
                        histogramFunction.recalculate();
                    }
                }
            }
        }
    }

    protected void initComponents() {
        super.initComponents();
        
        funcChart = new TChart();

        sourceSeries = new com.steema.teechart.styles.Line(chart1.getChart());
        sourceSeries.getMarks().setVisible(false);
        sourceSeries.setTitle("Source");
        sourceSeries.setColor(Color.RED);
        sourceSeries.getLinePen().setColor(Color.RED);
        sourceSeries.fillSampleValues(500);

        histogramFunction = new com.steema.teechart.functions.HistogramFunction(
                funcChart.getChart());

        Histogram functionSeries = new com.steema.teechart.styles.Histogram(
                funcChart.getChart());        
        functionSeries.setFunction(histogramFunction);
        functionSeries.setDataSource(sourceSeries);
        functionSeries.setTitle("Histogram");
        functionSeries.getLinePen().setVisible(false);
        functionSeries.getMarks().setVisible(false);

        cumulativeButton = new JCheckBox("Cumulative histogram");
        cumulativeButton.setSelected(false);

        SpinnerModel tmpModel;
        tmpModel = new SpinnerNumberModel(
                20,
                0,
                100,
                1);
        binsSpinner = new JSpinner(tmpModel);

        randomButton = new JButton("Recreate Data");
    }

    protected void initGUI() {
        super.initGUI();
        chart1.getHeader().setVisible(true);
        chart1.setText("Data");
        chart1.getAspect().setView3D(false);
        chart1.getLegend().setVisible(false);
        
        funcChart.getHeader().setVisible(true);
        funcChart.setText("Histogram");
        funcChart.getAspect().setView3D(false);
        funcChart.getLegend().setVisible(false);
        
        funcChart.getAxes().getLeft().getAxisPen().setWidth(1);
        funcChart.getAxes().getBottom().getAxisPen().setWidth(1);
        funcChart.getAxes().getLeft().setAutomatic(true);
        funcChart.getAxes().getBottom().setAutomatic(true);
        funcChart.getHeader().getFont().setColor(
               new com.steema.teechart.drawing.Color(226,226,226));

        JPanel tmpPane = getButtonPane();
        {            
            tmpPane.add(new JLabel("Number of bins:"));
            tmpPane.add(binsSpinner);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(cumulativeButton);
            tmpPane.add(Box.createHorizontalStrut(20));
            tmpPane.add(randomButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
        
        JSplitPane tmpSplitPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                chart1,
                funcChart      
                );
        tmpSplitPane.setDividerLocation(400);
        setSamplePane(tmpSplitPane);
    }

    private JButton randomButton;
    private JCheckBox cumulativeButton;
    private JSpinner binsSpinner;
    private TChart funcChart;
}
