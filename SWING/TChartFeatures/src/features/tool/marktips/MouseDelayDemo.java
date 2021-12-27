/*
 * MouseDelayDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.tool.marktips;

import com.steema.teechart.styles.Bar;
import com.steema.teechart.tools.MarksTip;
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

/**
 *
 * @author tom
 */
public class MouseDelayDemo extends ChartSamplePanel
    implements ActionListener, ChangeListener {

    private MarksTip tool;
    private Bar series1, series2, series3;

    /** Creates a new instance of MouseDelayDemo */
    public MouseDelayDemo() {
        super();
        mouseDelaySpinner.addChangeListener(this);
        seriesList.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == seriesList) {
            switch (seriesList.getSelectedIndex()) {
                case 0: tool.setSeries(null); break;
                case 1: tool.setSeries(series1); break;
                case 2: tool.setSeries(series2); break;
                case 3: tool.setSeries(series3); break;
            }
        }
    }

    public void stateChanged(ChangeEvent e) {
        Object source = e.getSource();
        if (source == mouseDelaySpinner) {
            int pause = mouseDelaySpinnerModel.getNumber().intValue();
            tool.setMouseDelay(pause);
        };
    }

    protected void initChart() {
        super.initChart();
        chart1.getAspect().setView3D(false);
        chart1.getHeader().setVisible(false);
    }

    protected void initComponents() {
        super.initComponents();

        tool = new com.steema.teechart.tools.MarksTip(chart1.getChart());

        series1 = new com.steema.teechart.styles.Bar(chart1.getChart());
        series1.getMarks().setVisible(false);
        series1.fillSampleValues(4);

        series2 = new com.steema.teechart.styles.Bar(chart1.getChart());
        series2.getMarks().setVisible(false);
        series2.fillSampleValues(4);

        series3 = new com.steema.teechart.styles.Bar(chart1.getChart());
        series3.getMarks().setVisible(false);
        series3.fillSampleValues(4);

        seriesList = new JComboBox(new String[] {"(all)", "Series1", "Series2", "Series3"});
        mouseDelaySpinnerModel = new SpinnerNumberModel(
                tool.getMouseDelay(),
                0,
                10000,
                100);
        mouseDelaySpinner = new JSpinner(mouseDelaySpinnerModel);
    }

    protected void initGUI() {
        super.initGUI();
        JLabel tmpLabel;
        JPanel tmpPane = getButtonPane();
        {
            tmpLabel = new JLabel("Mouse delay:");
            tmpLabel.setDisplayedMnemonic('M');
            tmpLabel.setLabelFor(mouseDelaySpinner);
            tmpPane.add(tmpLabel);
            tmpPane.add(mouseDelaySpinner);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpLabel = new JLabel("Series:");
            tmpLabel.setDisplayedMnemonic('S');
            tmpLabel.setLabelFor(seriesList);
            tmpPane.add(tmpLabel);
            tmpPane.add(seriesList);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JComboBox seriesList;
    private JSpinner mouseDelaySpinner;
    private SpinnerNumberModel mouseDelaySpinnerModel;
}
