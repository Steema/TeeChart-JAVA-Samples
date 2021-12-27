/*
 * RangePaletteDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.colorgrid;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.editors.ButtonColor;
import com.steema.teechart.styles.ColorGrid;
import com.steema.teechart.styles.PaletteStyle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class RangePaletteDemo extends ChartSamplePanel
    implements ActionListener, ItemListener, ChangeListener {

    private ColorGrid series;
    private ButtonColor startColorButton, middleColorButton, endColorButton;

    /** Creates a new instance of RangePaletteDemo */
    public RangePaletteDemo() {
        super();
        noStepsButton.addItemListener(this);
        stepsSpinner.addChangeListener(this);
        startColorButton.addActionListener(this);
        middleColorButton.addActionListener(this);
        endColorButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == startColorButton) {
            series.setStartColor(new Color(startColorButton.getColor()));
        } else if (source == middleColorButton) {
            series.setMidColor(new Color(middleColorButton.getColor()));
        } else if (source == endColorButton) {
            series.setEndColor(new Color(endColorButton.getColor()));
        }
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == noStepsButton) {
            stepsSpinner.setEnabled(!isSelected);
            if (isSelected) {
                series.setUsePalette(false);
                series.setUseColorRange(true);
            } else {
                series.setUsePalette(true);
                series.setUseColorRange(false);
            }
        }
    }

    public void stateChanged(ChangeEvent e) {
        Object source = e.getSource();
        if (source == stepsSpinner) {
            int steps = ((SpinnerNumberModel)stepsSpinner.getModel()).getNumber().intValue();
            series.setPaletteSteps(steps);
            series.repaint();
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getAspect().setView3D(false);
    }

    protected void initComponents() {
        super.initComponents();

        series = new ColorGrid(chart1.getChart());
        series.fillSampleValues(100);
        series.setPaletteStyle(PaletteStyle.STRONG);
        series.setPaletteSteps(10);
        series.setMidColor(Color.TEAL);
        series.getPen().setVisible(false);
        /* set-up initial values for controls */
        startColorButton = new ButtonColor(series.getStartColor());
        middleColorButton = new ButtonColor(series.getMidColor());
        endColorButton = new ButtonColor(series.getEndColor());

        noStepsButton = new JCheckBox("No Steps");
        noStepsButton.setSelected(true);
        stepsSpinner = new JSpinner(
                new SpinnerNumberModel(
                    series.getPaletteSteps(),
                    1,
                    250,
                    1)
        );
        stepsSpinner.setEnabled(!noStepsButton.isSelected());
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(startColorButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(middleColorButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(endColorButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            JLabel tmpLabel = new JLabel("Steps: ");
            tmpLabel.setDisplayedMnemonic('S');
            tmpLabel.setLabelFor(stepsSpinner);
            tmpPane.add(tmpLabel);
            tmpPane.add(stepsSpinner);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(noStepsButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JCheckBox noStepsButton;
    private JSpinner stepsSpinner;
}
