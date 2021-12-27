/*
 * LegendWidthsDemo.java
 *
 * <p>Copyright: Copyright (c) 2004-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.legend;

import com.steema.teechart.styles.Candle;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class LegendWidthsDemo extends ChartSamplePanel
    implements ChangeListener, ItemListener {

    /** Creates a new instance of LegendWidthsDemo */
    public LegendWidthsDemo() {
        super();
        autoWidthsButton.addItemListener(this);
        width1Spinner.addChangeListener(this);
        width2Spinner.addChangeListener(this);
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == autoWidthsButton) {
            chart1.getLegend().setColumnWidthAuto(isSelected);
            chart1.repaint();
            syncUI();
        }
    }

    public void stateChanged(ChangeEvent e) {
        Object source = e.getSource();
        if ((source == width1Spinner) || (source == width2Spinner)) {
            int width = ((SpinnerNumberModel)((JSpinner)source).getModel()).getNumber().intValue();
            if (source == width1Spinner) {
                chart1.getLegend().setColumnWidth(0, width);
            } else {
                chart1.getLegend().setColumnWidth(1, width);
            }
        }
    }

    protected void initChart() {
        super.initChart();
    }

    protected void initComponents() {
        super.initComponents();

        Candle series = new Candle(chart1.getChart());
        series.fillSampleValues(20);

        autoWidthsButton = new JCheckBox("Auto widths");
        autoWidthsButton.setSelected(chart1.getLegend().getColumnWidthAuto());

        width1Spinner = new JSpinner(
            new SpinnerNumberModel(
                chart1.getLegend().getColumnWidth(0),
                0,
                100,
                1
            )
        );

        width2Spinner = new JSpinner(
            new SpinnerNumberModel(
                chart1.getLegend().getColumnWidth(1),
                0,
                100,
                1
            )
        );

        syncUI();
    }

    protected void initGUI() {
        super.initGUI();
        JLabel tmpLabel;
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(autoWidthsButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpLabel = new JLabel("Column 1 width:");
            tmpLabel.setDisplayedMnemonic('1');
            tmpLabel.setLabelFor(width1Spinner);
            tmpPane.add(tmpLabel);
            tmpPane.add(width1Spinner);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpLabel = new JLabel("Column 2 width:");
            tmpLabel.setDisplayedMnemonic('2');
            tmpLabel.setLabelFor(width2Spinner);
            tmpPane.add(tmpLabel);
            tmpPane.add(width2Spinner);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    protected void syncUI() {
        width1Spinner.setEnabled(!autoWidthsButton.isSelected());
        width2Spinner.setEnabled(!autoWidthsButton.isSelected());
    }

    private JCheckBox autoWidthsButton;
    private JSpinner width1Spinner, width2Spinner;
}
