/*
 * ColorGridDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.colorgrid;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.editors.ButtonPen;
import com.steema.teechart.editors.ChartEditor;
import com.steema.teechart.styles.ColorGrid;
import com.steema.teechart.styles.PaletteStyle;
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
public class ColorGridDemo extends ChartSamplePanel
        implements ActionListener, ChangeListener, ItemListener {

    private ColorGrid series;
    private ButtonPen gridPenButton;

    /** Creates a new instance of ColorGridDemo */
    public ColorGridDemo() {
        super();
        editButton.addActionListener(this);
        gridButton.addItemListener(this);
        centerButton.addItemListener(this);
        sizeSpinner.addChangeListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == editButton) {
            ChartEditor.editSeries(series);
            setGridControls();
        }
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == gridButton) {
            gridPenButton.setEnabled(gridButton.isSelected());
            series.getPen().setVisible(isSelected);
        } else if  (source == centerButton) {
            series.setCenteredPoints(isSelected);
        }
    }

    public void stateChanged(ChangeEvent e) {
        Object source = e.getSource();
        if (source == sizeSpinner) {
            int size = ((SpinnerNumberModel)sizeSpinner.getModel()).getNumber().intValue();
            series.fillSampleValues(size);
            if (size>30) {
                gridButton.setSelected(false);
                series.getPen().setVisible(false);
            }
        }
    }

    protected void initComponents() {
        super.initComponents();

        series = new ColorGrid(chart1.getChart());
        series.getPen().setColor(Color.WHITE);
        series.setUsePalette(true);
        series.setPaletteStyle(PaletteStyle.STRONG);
        series.setUseColorRange(false);
        series.fillSampleValues(20);

        editButton = new JButton("Edit...");
        gridPenButton = new ButtonPen(series.getPen(), "Grid...");

        gridButton = new JCheckBox("Grid");
        centerButton = new JCheckBox("Centered");
        centerButton.setSelected(series.getCenteredPoints());
        setGridControls();

        sizeSpinner = new JSpinner(
           new SpinnerNumberModel(
                20,
                1,
                5000,
                1)
           );
    }

    protected void initGUI() {
        super.initGUI();
        chart1.getAspect().setView3D(false);
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(gridButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(gridPenButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(editButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            JLabel tmpLabel = new JLabel("Size: ");
            tmpLabel.setDisplayedMnemonic('S');
            tmpLabel.setLabelFor(sizeSpinner);
            tmpPane.add(tmpLabel);
            tmpPane.add(sizeSpinner);
            tmpPane.add(sizeSpinner);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(centerButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private void setGridControls() {
        gridButton.setSelected(series.getPen().getVisible());
        gridPenButton.setEnabled(series.getPen().getVisible());
    }

    private JButton editButton;
    private JCheckBox gridButton, centerButton;
    private JSpinner sizeSpinner;
}
