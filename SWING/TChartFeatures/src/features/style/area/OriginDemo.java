/*
 * OriginDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.area;
import com.steema.teechart.styles.Area;
import com.steema.teechart.styles.CustomStack;
import com.steema.teechart.styles.ValueList;
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

    private Area areaSeries;
    private JCheckBox originButton;
    private JSpinner originSpinner;
    private SpinnerNumberModel originSpinnerModel;


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
            areaSeries.setUseOrigin(isSelected);
        }
    }

    public void stateChanged(ChangeEvent e) {
        Object source = e.getSource();

        if (source == originSpinner) {
            refreshOrigin();
        }
    }

    protected void initComponents() {
        super.initComponents();

        areaSeries = new Area(chart1.getChart());
        areaSeries.getMarks().setVisible(false);
        areaSeries.fillSampleValues(20);
        areaSeries.setStacked(CustomStack.NONE);
        areaSeries.setUseOrigin(false);

        ValueList tmpValues = areaSeries.getYValues();

        long tmpMiddle = Math.round((tmpValues.getMaximum() + tmpValues.getMinimum()) / 2.0);

        originSpinnerModel = new SpinnerNumberModel(
                tmpMiddle,
                Integer.MIN_VALUE,
                Integer.MAX_VALUE,
                1);
        originSpinner = new JSpinner(originSpinnerModel);

        originButton = new JCheckBox("Use Origin");
        originButton.setSelected(false);

        refreshOrigin();
    }

    protected void initGUI() {
        super.initGUI();
        chart1.getAspect().setView3D(false);
        chart1.getLegend().setVisible(false);
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(originButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            JLabel tmpLabel;
            tmpLabel = new JLabel("Origin:");
            tmpLabel.setDisplayedMnemonic('O');
            tmpLabel.setLabelFor(originSpinner);
            tmpPane.add(tmpLabel);
            tmpPane.add(originSpinner);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private void refreshOrigin() {
        int origin = originSpinnerModel.getNumber().intValue();
        areaSeries.setOrigin(origin);
        areaSeries.repaint();
    }

}
