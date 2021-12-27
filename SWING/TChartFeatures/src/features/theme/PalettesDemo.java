/*
 * PalettesDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.theme;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.Bar;
import com.steema.teechart.themes.Theme;
import com.steema.teechart.themes.ColorPalettes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class PalettesDemo extends ChartSamplePanel
        implements ActionListener {

    private Color[] redPalette;
    private Color[] bluePalette;
    private Color[] greenPalette;

    /**
     * Creates a new instance of PalettesDemo
     */
    public PalettesDemo() {
        super();
        customList.addActionListener(this);
        defaultList.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source instanceof JComboBox) {
            int index = ((JComboBox)source).getSelectedIndex();
            if (source == customList) {
                // Set a custom color palette
                switch (index) {
                    case 1: ColorPalettes.applyPalette(chart1.getChart(), redPalette); break;
                    case 2: ColorPalettes.applyPalette(chart1.getChart(), bluePalette); break;
                    case 3: ColorPalettes.applyPalette(chart1.getChart(), greenPalette); break;
                    default : ColorPalettes.applyPalette(chart1.getChart(), Theme.OperaPalette);
                }
            } else if (source == defaultList) {
                ColorPalettes.applyPalette(chart1.getChart(), index);
            }
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getHeader().setVisible(true);
        chart1.setText("TChart - Color Palettes");
    }

    protected void initComponents() {
        super.initComponents();
        Bar series = new Bar(chart1.getChart());
        series.fillSampleValues(15);
        series.setColorEach(true);  // <-- IMPORTANT, USE COLOR PALETTE

        defaultList = new JComboBox(ColorPalettes.PaletteNames);
        defaultList.setSelectedIndex(1);
        customList = new JComboBox(new String[] {"Default", "Red", "Blue", "Green"});
        redPalette = new Color[15];
        bluePalette = new Color[15];
        greenPalette = new Color[15];
        float tmp = (float)(128.0/15.0);
        for (int t=0; t<15; t++) {
            redPalette[t] = Color.fromArgb(128+Math.round(t*tmp),0,0);
            bluePalette[t] = Color.fromArgb(0,0,128+Math.round(t*tmp));
            greenPalette[t] = Color.fromArgb(0,128+Math.round(t*tmp),0);
        }
    }

    protected void initGUI() {
        super.initGUI();
        JLabel tmpLabel;
        JPanel tmpPane = getButtonPane();
        {
            tmpLabel = new JLabel("Custom Palettes:");
            tmpLabel.setDisplayedMnemonic('C');
            tmpLabel.setLabelFor(customList);
            tmpPane.add(tmpLabel);
            tmpPane.add(customList);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpLabel = new JLabel("Default Palettes:");
            tmpLabel.setDisplayedMnemonic('D');
            tmpLabel.setLabelFor(customList);
            tmpPane.add(tmpLabel);
            tmpPane.add(defaultList);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JComboBox customList, defaultList;
}
