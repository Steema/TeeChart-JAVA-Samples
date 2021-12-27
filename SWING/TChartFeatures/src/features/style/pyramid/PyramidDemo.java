/*
 * PyramidDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.pyramid;

import com.steema.teechart.editors.ChartEditor;
import com.steema.teechart.styles.Pyramid;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Box;
import javax.swing.JButton;
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
public class PyramidDemo extends ChartSamplePanel
        implements ActionListener, ChangeListener, ItemListener {

    private Pyramid series;

    /** Creates a new instance of PyramidDemo */
    public PyramidDemo() {
        super();
        editButton.addActionListener(this);
        colorEachButton.addItemListener(this);
        invertedButton.addItemListener(this);
        nullPointsButton.addItemListener(this);
        sizeSpinner.addChangeListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == editButton) {
            ChartEditor.editSeries(series);
        };
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == colorEachButton) {
            series.setColorEach(isSelected);
        } else if (source == invertedButton) {
            chart1.getAxes().getLeft().setInverted(isSelected);
        } else if (source == nullPointsButton) {
            if (isSelected) {
                series.setNull(2);
                series.setNull(5);
            } else {
                series.getColors().setColor(2, series.getColor());
                series.getColors().setColor(5, series.getColor());
            }
            series.repaint();
        }
    }

    public void stateChanged(ChangeEvent e) {
        Object source = e.getSource();
        if (source == sizeSpinner) {
            int size = ((SpinnerNumberModel)sizeSpinner.getModel()).getNumber().intValue();
            series.setSizePercent(size);
        }
    }

    protected void initComponents() {
        super.initComponents();

        series = new Pyramid(chart1.getChart());
        series.setSizePercent(50);
        series.fillSampleValues(10);

        editButton = new JButton("Edit...");
        colorEachButton = new JCheckBox("Color Each");
        colorEachButton.setSelected(series.getColorEach());
        invertedButton = new JCheckBox("Inverted");
        invertedButton.setSelected(chart1.getAxes().getLeft().getInverted());
        nullPointsButton = new JCheckBox("With null points");
        nullPointsButton.setSelected(false);
        sizeSpinner = new JSpinner(
                new SpinnerNumberModel(
                    series.getSizePercent(),
                    0,
                    100,
                    5)
        );
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(colorEachButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(invertedButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            JLabel tmpLabel = new JLabel("Size %:");
            tmpLabel.setDisplayedMnemonic('S');
            tmpLabel.setLabelFor(sizeSpinner);
            tmpPane.add(tmpLabel);
            tmpPane.add(sizeSpinner);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(nullPointsButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(editButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JButton editButton;
    private JCheckBox colorEachButton, invertedButton, nullPointsButton;
    private JSpinner sizeSpinner;
}
