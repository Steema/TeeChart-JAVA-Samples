/*
 * WirePaletteDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.surface;

import com.steema.teechart.styles.Surface;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class WirePaletteDemo extends ChartSamplePanel
        implements ActionListener, ItemListener {

    private Surface series;

    /** Creates a new instance of WirePaletteDemo */
    public WirePaletteDemo() {
        super();
        colorStyleList.addActionListener(this);
        wireFrameButton.addItemListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == colorStyleList) {
            switch (colorStyleList.getSelectedIndex()) {
                case 0: {
                    series.setUseColorRange(false);
                    series.setUsePalette(false);
                    break;
                }
                case 1: {
                    series.setUseColorRange(true);
                    series.setUsePalette(false);
                    break;
                }
                case 2: {
                    series.setUseColorRange(false);
                    series.setUsePalette(true);
                    break;
                }
            }
        }
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == wireFrameButton) {
            series.setWireFrame(isSelected);
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getWalls().getBottom().setTransparent(true);
        chart1.getWalls().getLeft().setTransparent(true);
    }

    protected void initComponents() {
        super.initComponents();

        series = new com.steema.teechart.styles.Surface(chart1.getChart());
        series.setWireFrame(true);
        series.setUsePalette(true);
        series.setUseColorRange(false);
        series.getPen().setWidth(1);
        series.fillSampleValues(10);

        colorStyleList = new JComboBox(new String[] {"Single", "Range", "Palette"} );
        colorStyleList.setSelectedIndex(2);
        wireFrameButton = new JCheckBox("Wire-frame");
        wireFrameButton.setSelected(true);
    }

    protected void initGUI() {
        super.initGUI();
        JLabel tmpLabel;
        JPanel tmpPane = getButtonPane();
        {
            tmpLabel = new JLabel("Wire-frame Color Style:");
            tmpLabel.setDisplayedMnemonic('W');
            tmpLabel.setLabelFor(colorStyleList);
            tmpPane.add(tmpLabel);
            tmpPane.add(colorStyleList);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(wireFrameButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    JCheckBox wireFrameButton;
    JComboBox colorStyleList;
}
