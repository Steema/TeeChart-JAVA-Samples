/*
 * RootMeanSqrDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.function;

import com.steema.teechart.functions.RootMeanSquare;
import com.steema.teechart.styles.CustomStack;
import com.steema.teechart.styles.Line;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class RootMeanSqrDemo extends ChartSamplePanel
    implements ItemListener {

    private Line sourceSeries, rmsSeries;
    private RootMeanSquare rmsFunction;

    /**
     * Creates a new instance of RootMeanSqrDemo
     */
    public RootMeanSqrDemo() {
        super();
        completeButton.addItemListener(this);
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == completeButton) {
            rmsFunction.setComplete(isSelected);
        };
        displayCalculation();
    }

    protected void initComponents() {
        super.initComponents();

        sourceSeries = new com.steema.teechart.styles.Line(chart1.getChart());
        sourceSeries.setTitle("Source");
        sourceSeries.getMarks().setVisible(false);
        sourceSeries.getPointer().setVisible(false);
        sourceSeries.setStacked(CustomStack.NONE);
        sourceSeries.fillSampleValues(20);

        rmsSeries = new com.steema.teechart.styles.Line(chart1.getChart());
        rmsSeries.setTitle("Root Mean Sq.");
        rmsSeries.getMarks().setVisible(false);
        rmsSeries.getPointer().setVisible(true);
        rmsSeries.setStacked(CustomStack.NONE);

        rmsFunction = new com.steema.teechart.functions.RootMeanSquare(chart1.getChart());
        rmsFunction.setComplete(false);

        rmsSeries.setDataSource(sourceSeries);
        rmsSeries.setFunction(rmsFunction);

        calcValueLabel = new JLabel("");
        completeButton = new JCheckBox("Complete");
        completeButton.setSelected(false);

        displayCalculation();
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(new JLabel("Root Mean Square value: "));
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(calcValueLabel);
            tmpPane.add(Box.createHorizontalStrut(20));
            tmpPane.add(completeButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private void displayCalculation() {
        calcValueLabel.setText(new Double(rmsSeries.getYValues().getValue(0)).toString());
    }

    private JCheckBox completeButton;
    private JLabel calcValueLabel;
}
