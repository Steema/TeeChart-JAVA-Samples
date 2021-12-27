/*
 * MACDDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.function;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.functions.MACD;
import com.steema.teechart.styles.Candle;
import com.steema.teechart.styles.Line;
import com.steema.teechart.axis.Axis;

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
public class MACDDemo extends ChartSamplePanel 
    implements ItemListener, ChangeListener {

    private Candle candleS;
    private MACD sFunction;

    /**
     * Creates a new instance of CCIDemo
     */
    public MACDDemo() {
        super();
        accCheckBox.addItemListener(this);
        histogramCheckBox.addItemListener(this);
        MACDEXPCheckBox.addItemListener(this);
        p1Spinner.addChangeListener(this);
        p2Spinner.addChangeListener(this);
        p3Spinner.addChangeListener(this);
    }
    
    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);

        if (source == accCheckBox) {
            sFunction.getSeries().setActive(isSelected);
            sFunction.getMACDExp().setActive(isSelected);
            sFunction.getHistogram().setActive(isSelected);
            chart1.getLegend().setVisible(isSelected);
            /* re-position the axis */
            if (sFunction.getSeries().getActive()) {
               chart1.getAxes().getLeft().setEndPosition(60);
            } else {
                chart1.getAxes().getLeft().setEndPosition(100);
            }
        }
        else if (source == MACDEXPCheckBox) {
            sFunction.getMACDExp().setActive(isSelected);
        }
        else if (source == histogramCheckBox) {
            sFunction.getHistogram().setActive(isSelected);
        }
    }
    
    public void stateChanged(ChangeEvent e) {
        Object source = e.getSource();
        if (source instanceof JSpinner) {
            SpinnerModel tmpModel = ((JSpinner)source).getModel();
            if (tmpModel instanceof SpinnerNumberModel) {
                int period = ((SpinnerNumberModel)tmpModel).getNumber().intValue();
                if ((period > 0) && (period < 50)) {
                    if (source == p1Spinner) {
                        sFunction.setPeriod(period);
                    }
                    else if (source == p2Spinner) {
                        sFunction.setPeriod2(period);
                    }
                    else if (source == p3Spinner) {
                        sFunction.setPeriod3(period);
                    }
                }
            }
        }
    }
    
    
    protected void initComponents() {
        super.initComponents();

        candleS = new com.steema.teechart.styles.Candle(chart1.getChart());
        candleS.getMarks().setVisible(false);
        candleS.getPointer().setVisible(true);
        candleS.setTitle("Candle");
        candleS.getVertAxis().setStartPosition(0);
        candleS.getVertAxis().setEndPosition(40);
        candleS.getVertAxis().getTitle().setCaption("Candle");
        candleS.fillSampleValues(50);

        sFunction = new com.steema.teechart.functions.MACD(chart1.getChart());

        Axis tmpAxis;
        tmpAxis = chart1.getAxes().getCustom().getNew();
        tmpAxis.setHorizontal(false);
        tmpAxis.setOtherSide(false);
        tmpAxis.setStartPosition(60.0);
        tmpAxis.setEndPosition(100);
        tmpAxis.getTitle().setText("MACD");
        tmpAxis.getTitle().setAngle(90);
        
        Line functionSeries = new com.steema.teechart.styles.Line(chart1.getChart());
        functionSeries.setTitle("MACD");
        functionSeries.setColor(Color.GREEN);
        functionSeries.setCustomVertAxis(tmpAxis);
        functionSeries.setDataSource(candleS);
        functionSeries.setFunction(sFunction);
        
        accCheckBox = new JCheckBox("Show MACD");
        accCheckBox.setSelected(true);
        MACDEXPCheckBox = new JCheckBox("Show MACD Exp");
        MACDEXPCheckBox.setSelected(true);
        histogramCheckBox = new JCheckBox("Show histogram");
        histogramCheckBox.setSelected(true);
        
        SpinnerModel tmpModel;
        tmpModel = new SpinnerNumberModel(
                26,
                1,
                60,
                1);
        p1Spinner = new JSpinner(tmpModel);
        
        tmpModel = new SpinnerNumberModel(
                12,
                1,
                60,
                1);
        p2Spinner = new JSpinner(tmpModel);

        tmpModel = new SpinnerNumberModel(
                9,
                1,
                60,
                1);
        p3Spinner = new JSpinner(tmpModel);
    }

    protected void initGUI() {
        super.initGUI();
        chart1.getHeader().setVisible(true);
        chart1.setText("MACD Function Example");
        chart1.getAspect().setView3D(false);
        
        JLabel tmpLabel;
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(accCheckBox);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(MACDEXPCheckBox);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(histogramCheckBox);
            tmpPane.add(Box.createHorizontalStrut(10));

            tmpLabel = new JLabel("Period:");
            tmpLabel.setLabelFor(p1Spinner);
            tmpPane.add(tmpLabel);
            tmpPane.add(p1Spinner);
            tmpPane.add(Box.createHorizontalStrut(10));
            
            tmpLabel = new JLabel("Period 2:");
            tmpLabel.setLabelFor(p2Spinner);
            tmpPane.add(tmpLabel);
            tmpPane.add(p2Spinner);
            tmpPane.add(Box.createHorizontalStrut(10));
            
            tmpLabel = new JLabel("Period 3:");
            tmpLabel.setLabelFor(p3Spinner);
            tmpPane.add(tmpLabel);
            tmpPane.add(p3Spinner);
            tmpPane.add(Box.createHorizontalStrut(10));
            
            tmpPane.add(Box.createHorizontalGlue());
        }

    }
    
    private JCheckBox accCheckBox, MACDEXPCheckBox, histogramCheckBox;
    private JSpinner p1Spinner, p2Spinner, p3Spinner;

    
}