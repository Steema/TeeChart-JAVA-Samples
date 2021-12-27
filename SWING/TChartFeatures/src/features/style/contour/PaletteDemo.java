/*
 * PaletteDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.contour;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.Contour;
import com.steema.teechart.styles.PaletteStyle;
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
public class PaletteDemo extends ChartSamplePanel
    implements ActionListener {

    private Contour series;

    /** Creates a new instance of PaletteDemo */
    public PaletteDemo() {
        super();
        colorList.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == colorList) {
            switch (colorList.getSelectedIndex()) {
                case 0: {                                       //single color
                    series.setUseColorRange(false);
                    series.setUsePalette(false);
                    series.setColor(Color.YELLOW);
                    break;
                }
                case 1: {                                  //gradient 2 colors
                    series.setUseColorRange(true);
                    series.setUsePalette(false);
                    series.setStartColor(Color.BLUE);
                    series.setEndColor(Color.RED);
                    series.setMidColor(Color.TRANSPARENT);
                    break;
                }
                case 2: {                                  //gradient 3 colors
                    series.setUseColorRange(true);
                    series.setUsePalette(false);
                    series.setStartColor(Color.BLUE);
                    series.setEndColor(Color.RED);
                    series.setMidColor(Color.YELLOW);
                    break;
                }
                case 3: {                                 // palette "pale"
                    series.setUseColorRange(false);
                    series.setUsePalette(true);
                    series.setPaletteStyle(PaletteStyle.PALE);
                    break;
                }
                case 4: {                                 // palette  "strong"
                    series.setUseColorRange(false);
                    series.setUsePalette(true);
                    series.setPaletteStyle(PaletteStyle.STRONG);
                    break;
                }
                case 5: {                            // palette  "gray levels"
                    series.setUseColorRange(false);
                    series.setUsePalette(true);
                    series.setPaletteStyle(PaletteStyle.GRAYSCALE);
                    break;
                }
                case 6: {                            // palette  "inverted gray levels"
                    series.setUseColorRange(false);
                    series.setUsePalette(true);
                    series.setPaletteStyle(PaletteStyle.INVERTED_GRAYSCALE);
                    break;
                }
                case 7: {                            // palette  "rainbow"
                    series.setUseColorRange(false);
                    series.setUsePalette(true);
                    series.setPaletteStyle(PaletteStyle.RAINBOW);
                    break;
                }
            }
        }
    }

    protected void initComponents() {
        super.initComponents();
        series = new com.steema.teechart.styles.Contour(chart1.getChart());
        series.fillSampleValues(25);
        series.getMarks().setVisible(true);
        series.setYPosition(0.19);
        series.setStartColor(Color.BLUE);
        series.setEndColor(Color.YELLOW);
        series.setMidColor(Color.RED);
        series.setPaletteSteps(34);

        colorList = new JComboBox(new String[] {
            "Single color",
            "Gradient 2 colors",
            "Gradient 3 colors",
            "Palette Pale",
            "Palette Strong",
            "Palette Grayscale",
            "Inverted Gray",
            "Rainbow"}
        );
        colorList.setSelectedIndex(2);
    }

    protected void initGUI() {
        super.initGUI();
        chart1.getAspect().setView3D(false);
        chart1.getAxes().getDepth().setVisible(true);
        chart1.getLegend().getSymbol().setContinuous(true);
        chart1.getLegend().setTransparent(true);
        JLabel tmpLabel;
        JPanel tmpPane = getButtonPane();
        {
            tmpLabel = new JLabel("Colors:");
            tmpLabel.setDisplayedMnemonic('C');
            tmpLabel.setLabelFor(colorList);
            tmpPane.add(tmpLabel);
            tmpPane.add(colorList);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JComboBox colorList;
}
