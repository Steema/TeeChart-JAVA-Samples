/*
 * OriginDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.volume;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.ValueList;
import com.steema.teechart.styles.Volume;
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
public class OriginDemo extends ChartSamplePanel
        implements ItemListener, ChangeListener {

    private Volume volumeSeries;

    /**
     * Creates a new instance of OriginDemo
     */
    public OriginDemo() {
        super();
        originButton.addItemListener(this);
        originSpinner.addChangeListener(this);
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == originButton) {
            volumeSeries.setUseOrigin(isSelected);
        }
    }

    public void stateChanged(ChangeEvent e) {
        Object source = e.getSource();
        if (source == originSpinner) {
            refreshOrigin();
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getHeader().setVisible(true);
        chart1.setText("Volume Series -- Using an Y origin value");
        chart1.getLegend().setVisible(false);
        chart1.getAspect().setView3D(false);
    }

    protected void initComponents() {
        super.initComponents();
        volumeSeries = new Volume(chart1.getChart());
        volumeSeries.getMarks().setVisible(false);
        volumeSeries.setColor(Color.RED);
        volumeSeries.fillSampleValues(50);
        volumeSeries.setUseOrigin(true);

        ValueList tmpValues = volumeSeries.getYValues();

        long tmpMiddle = Math.round((tmpValues.getMaximum() + tmpValues.getMinimum()) / 2.0);

        originSpinnerModel = new SpinnerNumberModel(
                tmpMiddle,
                Integer.MIN_VALUE,
                Integer.MAX_VALUE,
                1);
        originSpinner = new JSpinner(originSpinnerModel);

        originButton = new JCheckBox("Use Origin");
        originButton.setSelected(volumeSeries.getUseOrigin());

        refreshOrigin();
    }

    protected void initGUI() {
        super.initGUI();
        chart1.getLegend().setVisible(false);
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(originButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            JLabel tmpLabel = new JLabel("Use Y Origin:");
            tmpLabel.setDisplayedMnemonic('U');
            tmpLabel.setLabelFor(originSpinner);
            tmpPane.add(tmpLabel);
            tmpPane.add(originSpinner);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private void refreshOrigin() {
        int origin = originSpinnerModel.getNumber().intValue();
        volumeSeries.setOrigin(origin);
        volumeSeries.repaint();
    }

    private JCheckBox originButton;
    private JSpinner originSpinner;
    private SpinnerNumberModel originSpinnerModel;
}
