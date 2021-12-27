/*
 * MarginPixelsDemo.java
 *
 * <p>Copyright: Copyright (c) 2004-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.panel;

import com.steema.teechart.PanelMarginUnits;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import features.ChartSamplePanel;
import features.utils.EnumStrings;

/**
 *
 * @author tom
 */
public class MarginPixelsDemo extends ChartSamplePanel
        implements ActionListener, ChangeListener {

    /**
     * Creates a new instance of MarginPixelsDemo
     */
    public MarginPixelsDemo() {
        super();
        marginUnitList.addActionListener(this);
        pixelSpinner.addChangeListener(this);
    }

    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if (source == marginUnitList) {
            switch (marginUnitList.getSelectedIndex()) {
                case 0: chart1.getPanel().setMarginUnits(PanelMarginUnits.PERCENT); break;
                case 1: chart1.getPanel().setMarginUnits(PanelMarginUnits.PIXELS); break;
            }
            syncUI();
        }
    }

    public void stateChanged(ChangeEvent e) {
        Object source = e.getSource();
        if (source == pixelSpinner) {
            int pixels = ((SpinnerNumberModel)pixelSpinner.getModel()).getNumber().intValue();
            chart1.getPanel().setMarginBottom(pixels);
            chart1.getPanel().setMarginTop(pixels);
            chart1.getPanel().setMarginLeft(pixels);
            chart1.getPanel().setMarginRight(pixels);
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getHeader().setText("Margin Units");
        chart1.getHeader().setVisible(true);
        chart1.getPanel().setMarginBottom(10);
        chart1.getPanel().setMarginTop(10);
        chart1.getPanel().setMarginLeft(10);
        chart1.getPanel().setMarginRight(10);
        chart1.getPanel().setMarginUnits(PanelMarginUnits.PIXELS);
    }

    protected void initComponents() {
        super.initComponents();
        pixelSpinner = new JSpinner(
                new SpinnerNumberModel(
                chart1.getPanel().getMarginLeft(),
                0,
                100,
                1
                )
                );

        marginUnitList = new JComboBox(EnumStrings.PANEL_MARGIN_UNITS);
        marginUnitList.setSelectedIndex(chart1.getPanel().getMarginUnits().getValue());

        unitLabel = new JLabel();
        syncUI();
    }

    protected void initGUI() {
        super.initGUI();
        JLabel tmpLabel;
        JPanel tmpPane = getButtonPane();
        {
            tmpLabel = new JLabel("Margin Units:");
            tmpLabel.setDisplayedMnemonic('M');
            tmpLabel.setLabelFor(marginUnitList);
            tmpPane.add(tmpLabel);
            tmpPane.add(marginUnitList);
            tmpPane.add(Box.createHorizontalStrut(20));
            tmpLabel = new JLabel("Value:");
            tmpLabel.setDisplayedMnemonic('V');
            tmpLabel.setLabelFor(pixelSpinner);
            tmpPane.add(tmpLabel);
            tmpPane.add(pixelSpinner);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(unitLabel);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private void syncUI() {
        switch (marginUnitList.getSelectedIndex()) {
            case 0: unitLabel.setText("%"); break;
            case 1: unitLabel.setText("percent"); break;
        }
    }

    private JComboBox marginUnitList;
    private JLabel unitLabel;
    private JSpinner pixelSpinner;
}
