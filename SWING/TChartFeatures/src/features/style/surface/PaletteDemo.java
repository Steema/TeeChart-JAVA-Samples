/*
 * PaletteDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.surface;

import com.steema.teechart.Aspect;
import com.steema.teechart.legend.LegendSymbolPosition;
import com.steema.teechart.Wall;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.PaletteStyle;
import com.steema.teechart.styles.Surface;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import features.ChartSamplePanel;
import features.utils.EnumStrings;

/**
 *
 * @author tom
 */
public class PaletteDemo extends ChartSamplePanel
    implements ActionListener {

    private Surface series;

    /** Creates a new instance of PaletteDemo */
    public PaletteDemo() {
        super();
        paletteStyleList.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == paletteStyleList) {
            switch (paletteStyleList.getSelectedIndex()) {
                case 0: series.setPaletteStyle(PaletteStyle.PALE); break;
                case 1: series.setPaletteStyle(PaletteStyle.STRONG); break;
                case 2: series.setPaletteStyle(PaletteStyle.GRAYSCALE); break;
                case 3: series.setPaletteStyle(PaletteStyle.INVERTED_GRAYSCALE); break;
                case 4: series.setPaletteStyle(PaletteStyle.RAINBOW); break;
            }
        }
    }

    protected void initChart() {
        super.initChart();
        Wall tmpWall = chart1.getWalls().getBack();
        tmpWall.getGradient().setEndColor(Color.GRAY);
        tmpWall.getGradient().setVisible(true);
        tmpWall.setTransparent(false);

        chart1.getLegend().getSymbol().setContinuous(true);
        chart1.getLegend().getSymbol().setPosition(LegendSymbolPosition.RIGHT);

        chart1.getAxes().getDepth().setVisible(true);

        Aspect tmpAspect = chart1.getAspect();
        tmpAspect.setElevation(305);
        tmpAspect.setOrthogonal(false);
        tmpAspect.setPerspective(63);
        tmpAspect.setRotation(325);
        tmpAspect.setZoom(45);
        tmpAspect.setZoomText(false);
        tmpAspect.setChart3DPercent(100);
        tmpAspect.setClipPoints(false);
    }

    protected void initComponents() {
        super.initComponents();
        series = new com.steema.teechart.styles.Surface(chart1.getChart());
        series.setPaletteStyle(PaletteStyle.STRONG);
        series.setUsePalette(true);
        series.getPen().setVisible(false);
        series.setPaletteSteps(15);
        series.setUseColorRange(false);
        series.fillSampleValues(30);

        paletteStyleList = new JComboBox(EnumStrings.PALETTE_STYLES);
        paletteStyleList.setSelectedIndex(1);
    }

    protected void initGUI() {
        super.initGUI();
        JLabel tmpLabel;
        JPanel tmpPane = getButtonPane();
        {
            tmpLabel = new JLabel("Palette style:");
            tmpLabel.setDisplayedMnemonic('P');
            tmpLabel.setLabelFor(paletteStyleList);
            tmpPane.add(tmpLabel);
            tmpPane.add(paletteStyleList);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JComboBox paletteStyleList;
}
