/*
 * BollingerDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.function;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.Candle;
import com.steema.teechart.styles.FastLine;
import com.steema.teechart.functions.Bollinger;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JLabel;


import features.ChartSamplePanel;

/**
 *
 * @author Marjan 
 */
public class BollingerDemo extends ChartSamplePanel
    implements ItemListener,ChangeListener {

    private Candle sourceSeries;
    private Bollinger function;
    private FastLine functionSeries;

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);

        if (source == expCheckBox) {
            function.setExponential(isSelected);
        }
    }
    
    public void stateChanged(ChangeEvent e) {
        Object source = e.getSource();
        if (source instanceof JSpinner) {
            SpinnerModel tmpModel = ((JSpinner)source).getModel();
            if (tmpModel instanceof SpinnerNumberModel) {
                int val = ((SpinnerNumberModel)tmpModel).getNumber().intValue();
                if ((val > 0) && (val < 51)) {
                    if (source == periodSpinner) {
                        function.setPeriod(val);
                    }
                    else if (source==devSpinner) {
                        function.setDeviation(val);
                    }
                }
            }
        }
    }
    
    
    
    /**
     * Creates a new instance of BollingerDemo
     */
    public BollingerDemo() {
        super();
        expCheckBox.addItemListener(this);
        periodSpinner.addChangeListener(this);
        devSpinner.addChangeListener(this);
    }

    protected void initChart() {
        super.initChart();
        chart1.getLegend().setVisible(true);
    }

    protected void initComponents() {
        super.initComponents();

        sourceSeries = new Candle(chart1.getChart());
        sourceSeries.setTitle("Source");
        sourceSeries.setColor(Color.RED);
        sourceSeries.fillSampleValues(100);

        function = new Bollinger(chart1.getChart());
        function.setDeviation(2.0);

        functionSeries = new FastLine(chart1.getChart());
        functionSeries.setTitle("Bollinger");
        functionSeries.setDataSource(sourceSeries);
        functionSeries.setFunction(function);

        SpinnerModel tmpModel;
        tmpModel = new SpinnerNumberModel(
                10,
                1,
                50,
                1);
        periodSpinner = new JSpinner(tmpModel);

        tmpModel = new SpinnerNumberModel(
                2,
                1,
                10,
                1);
        devSpinner = new JSpinner(tmpModel);
        
        expCheckBox = new JCheckBox("Exponential");
        expCheckBox.setSelected(true);
    }

    protected void initGUI() {
        super.initGUI();
        chart1.getHeader().setVisible(true);
        chart1.setText("Bollinger Bands");
        chart1.getAspect().setView3D(false);
        
        JLabel tmpLabel;
        JPanel tmpPane = getButtonPane();
        {
            tmpLabel = new JLabel("Period:");
            tmpLabel.setDisplayedMnemonic('P');
            tmpLabel.setLabelFor(periodSpinner);
            tmpPane.add(tmpLabel);
            tmpPane.add(periodSpinner);
            tmpPane.add(Box.createHorizontalStrut(10));

            tmpLabel = new JLabel("Deviation:");
            tmpLabel.setDisplayedMnemonic('D');
            tmpLabel.setLabelFor(devSpinner);
            tmpPane.add(tmpLabel);
            tmpPane.add(devSpinner);
            tmpPane.add(Box.createHorizontalStrut(10));
            
            tmpPane.add(expCheckBox);
            tmpPane.add(Box.createHorizontalGlue());

        }
    }

    private JCheckBox expCheckBox;
    private JSpinner devSpinner, periodSpinner;

}

