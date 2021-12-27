/*
 * CCIDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.function;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.functions.CCI;
import com.steema.teechart.styles.Candle;
import com.steema.teechart.styles.Line;
import com.steema.teechart.styles.VerticalAxis;

import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JFormattedTextField;
import java.text.NumberFormat;

import features.ChartSamplePanel;

/**
 *
 * @author Marjan
 */
public class CCIDemo extends ChartSamplePanel
    implements ChangeListener, ItemListener, PropertyChangeListener {

    private Candle sourceSeries;
    private CCI sFunction;

    /**
     * Creates a new instance of CCIDemo
     */
    public CCIDemo() {
        super();
        periodSpinner.addChangeListener(this);
        activeCheckBox.addItemListener(this);
        constantField.addPropertyChangeListener("value", this);
    }

    public void stateChanged(ChangeEvent e) {
        Object source = e.getSource();
        if (source instanceof JSpinner) {
            SpinnerModel tmpModel = ((JSpinner)source).getModel();
            if (tmpModel instanceof SpinnerNumberModel) {
                int period = ((SpinnerNumberModel)tmpModel).getNumber().intValue();
                if ((period > 0) && (period < 101)) {
                    if (source == periodSpinner) {
                        sFunction.setPeriod(period);
                    }
                }
            }
        }
    }
    
    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);

        if (source == activeCheckBox) {
                sFunction.getSeries().setActive(isSelected);
                chart1.getLegend().setVisible(isSelected);
                /* re-position the axis */
                if (sFunction.getSeries().getActive()) {
                    chart1.getAxes().getLeft().setEndPosition(70);
                } else {
                    chart1.getAxes().getLeft().setEndPosition(100);
                }
        };
    }
    
    public void propertyChange(PropertyChangeEvent e) {
        Object source = e.getSource();
        double tmpValue= 0.015;
        if (source == constantField) {
            tmpValue = ((Number)constantField.getValue()).doubleValue();
            sFunction.setConstant(tmpValue);
        }
    }
    
    
    protected void initComponents() {
        super.initComponents();

        sourceSeries = new com.steema.teechart.styles.Candle(chart1.getChart());
        sourceSeries.getMarks().setVisible(false);
        sourceSeries.getPointer().setVisible(true);
        sourceSeries.setTitle("Source");
        sourceSeries.getVertAxis().setStartPosition(0);
        sourceSeries.getVertAxis().setEndPosition(70);
        sourceSeries.getVertAxis().getTitle().setCaption("Candle");
        sourceSeries.fillSampleValues(60);

        sFunction = new com.steema.teechart.functions.CCI(chart1.getChart());
        sFunction.setPeriod(10);

        Line functionSeries = new com.steema.teechart.styles.Line(chart1.getChart());
        functionSeries.setTitle("CCI");
        functionSeries.setColor(Color.GREEN);
        functionSeries.getLinePen().setColor(Color.GREEN);
        functionSeries.setVerticalAxis(VerticalAxis.RIGHT);
        functionSeries.getVertAxis().setStartPosition(70);
        functionSeries.getVertAxis().setEndPosition(100);
        functionSeries.getVertAxis().getTitle().setCaption("CCI");
        functionSeries.setDataSource(sourceSeries);
        functionSeries.setFunction(sFunction);

        SpinnerModel tmpModel;
        tmpModel = new SpinnerNumberModel(
                10,
                1,
                100,
                1);
        periodSpinner = new JSpinner(tmpModel);
        activeCheckBox = new JCheckBox();
        activeCheckBox.setSelected(true);
        
        NumberFormat limitFormat = NumberFormat.getNumberInstance();;       
        constantField = new JFormattedTextField(limitFormat);
        constantField.setValue(new Double(0.015));
    }

    protected void initGUI() {
        super.initGUI();
        chart1.getHeader().setVisible(true);
        chart1.setText("CCI Function Example");
        chart1.getAspect().setView3D(false);

        JLabel tmpLabel;
        JPanel tmpPane = getButtonPane();
        {
            
            tmpLabel = new JLabel("Show CCI:");
            tmpLabel.setDisplayedMnemonic('S');
            tmpLabel.setLabelFor(activeCheckBox);
            tmpPane.add(tmpLabel);
            tmpPane.add(activeCheckBox);
            tmpPane.add(Box.createHorizontalStrut(10));
            
            tmpLabel = new JLabel("Period:");
            tmpLabel.setDisplayedMnemonic('P');
            tmpLabel.setLabelFor(periodSpinner);
            tmpPane.add(tmpLabel);
            tmpPane.add(periodSpinner);
            tmpPane.add(Box.createHorizontalStrut(20));

            tmpLabel = new JLabel("Constant:");
            tmpLabel.setDisplayedMnemonic('C');
            tmpLabel.setLabelFor(constantField);
            tmpPane.add(tmpLabel);
            tmpPane.add(constantField);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JSpinner periodSpinner;
    private JCheckBox activeCheckBox;
    private JFormattedTextField  constantField;
}


