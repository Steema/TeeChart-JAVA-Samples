/*
 * StdDeviationDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.function;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.functions.StdDeviation;
import com.steema.teechart.styles.Line;
import com.steema.teechart.styles.VerticalAxis;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class StdDeviationDemo extends ChartSamplePanel
    implements ActionListener {

    private Line sourceSeries;
    private StdDeviation deviationFunction;

    /**
     * Creates a new instance of StdDeviationDemo
     */
    public StdDeviationDemo() {
        super();
        styleList.addActionListener(this);
        randomButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == styleList) {
            deviationFunction.setComplete(styleList.getSelectedIndex() != 0);
        } else if (source == randomButton) {
            sourceSeries.fillSampleValues(30);
        }
    }

    protected void initComponents() {
        super.initComponents();

        sourceSeries = new com.steema.teechart.styles.Line(chart1.getChart());
        sourceSeries.getMarks().setVisible(false);
        sourceSeries.getPointer().setVisible(false);
        sourceSeries.setTitle("Source");
        sourceSeries.setColor(Color.RED);
        sourceSeries.fillSampleValues(30);

        deviationFunction = new com.steema.teechart.functions.StdDeviation(chart1.getChart());
        deviationFunction.setPeriod(0);

        Line functionSeries = new com.steema.teechart.styles.Line(chart1.getChart());
        functionSeries.setDataSource(sourceSeries);
        functionSeries.setFunction(deviationFunction);
        functionSeries.setTitle("Std. Deviation");
        functionSeries.setColor(Color.GREEN);
        functionSeries.getMarks().setVisible(false);
        functionSeries.getPointer().setVisible(false);
        functionSeries.setVerticalAxis(VerticalAxis.RIGHT);

        styleList = new JComboBox(styleStrings);
        styleList.setSelectedIndex(0);
        randomButton = new JButton("New random values");
    }

    protected void initGUI() {
        super.initGUI();
        chart1.getHeader().setVisible(true);
        chart1.setText("Standard Deviation Function");

        JLabel tmpLabel;
        JPanel tmpPane = getButtonPane();
        {
            tmpLabel = new JLabel("Style:");
            tmpLabel.setDisplayedMnemonic('S');
            tmpLabel.setLabelFor(styleList);
            tmpPane.add(tmpLabel);
            tmpPane.add(styleList);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(randomButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JButton randomButton;
    private JComboBox styleList;

    private static final String[] styleStrings = { "Standard", "Complete" };
}
