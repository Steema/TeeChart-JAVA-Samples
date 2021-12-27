/*
 * ModeDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.function;

import com.steema.teechart.styles.FastLine;
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
public class ModeDemo extends ChartSamplePanel
    implements ItemListener {

    private FastLine series;
    //private Mode modeFunction;

    /** Creates a new instance of ModeDemo */
    public ModeDemo() {
        super();
        includeNullsButton.addItemListener(this);
        ignoreNullsButton.addItemListener(this);
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == includeNullsButton) {
            //TODO modeFunction.setIncludeNulls(isSelected);
        } else if (source == ignoreNullsButton) {
            series.setIgnoreNulls(isSelected);
        }
    }

    protected void initChart() {
        super.initChart();
    }

    protected void initComponents() {
        super.initComponents();
        series = new FastLine(chart1.getChart());
        series.fillSampleValues();
        // Set some null values for this example...
        series.setNull(11);
        series.setNull(14);
        series.setNull(3);

        //TODO
        //modeFunction = new com.steema.teechart.functions.Mode();
        //modeFunction.setChart(chart1.getChart());
        //modeFunction.setPeriod(0); //all points
        //modeFunction.setIncludeNulls(false);

        Line functionSeries = new Line(chart1.getChart());
        functionSeries.setTitle("Mode");
        functionSeries.setDataSource(series);
        //lineSeries.setFunction(modeFunction);

        includeNullsButton = new JCheckBox("Include Null values");
        ignoreNullsButton = new JCheckBox("Hide Null values");
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(includeNullsButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(ignoreNullsButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JCheckBox includeNullsButton, ignoreNullsButton;
}
