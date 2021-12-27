/*
 * FontColorDemo.java
 *
 * <p>Copyright: (c) 2004-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.legend;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.legend.Legend;
import com.steema.teechart.legend.LegendSymbolSize;
import com.steema.teechart.styles.Line;
import com.steema.teechart.styles.PointerStyle;
import com.steema.teechart.styles.Points;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class FontColorDemo extends ChartSamplePanel
    implements ItemListener {

    private Points[] points;
    private Line[] line;

    /** Creates a new instance of FontColorDemo */
    public FontColorDemo() {
        super();
        legendFontButton.addItemListener(this);
        multipleSeriesButton.addItemListener(this);
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == legendFontButton) {
            Legend legend = chart1.getLegend();
            legend.setFontSeriesColor(isSelected);
            if (isSelected) {
                legend.getFont().getShadow().setColor(Color.BLACK);
            } else {
                legend.getFont().getShadow().setColor(Color.WHITE);
            }
        } else if (source == multipleSeriesButton) {
            line[0].setActive(isSelected);
            line[1].setActive(isSelected);
            points[1].setActive(isSelected);
            points[0].setColorEach(!isSelected);
        }
    }

    protected void initChart() {
        super.initChart();
        Legend legend = chart1.getLegend();
        legend.setColorWidth(15);
        legend.getFont().setSize(16);
        legend.getFont().setBold(true);
        legend.getFont().setItalic(true);
        legend.getFont().getShadow().setColor(Color.BLACK);
        legend.getFont().getShadow().setHorizSize(1);
        legend.getFont().getShadow().setVertSize(1);
        legend.setFontSeriesColor(true);
        legend.getSymbol().setWidth(15);
        legend.getSymbol().setWidthUnits(LegendSymbolSize.PIXELS);
        legend.setTransparent(true);
        chart1.getAspect().setView3D(false);
    }

    protected void initSeries() {
        points = new Points[2];
        points[0] = new Points(chart1.getChart());
        points[0].setColorEach(true);
        points[0].getMarks().setVisible(false);
        points[0].getPointer().setHorizSize(10);
        points[0].getPointer().setVertSize(10);
        points[0].getPointer().setStyle(PointerStyle.DIAMOND);
        points[0].getPointer().setVisible(true);

        points[1] = new Points(chart1.getChart());
        points[1].getMarks().setVisible(false);
        points[1].getPointer().setVisible(true);
        points[1].setActive(false);

        line = new Line[2];
        for (int t=0; t < line.length; t++) {
            line[t] = new Line(chart1.getChart());
            line[t].getPointer().setVisible(false);
            line[t].setActive(false);
        }
        chart1.getSeries().fillSampleValues(10);
    }

    protected void initComponents() {
        super.initComponents();
        initSeries();

        legendFontButton = new JCheckBox("Legend Font Series Color");
        legendFontButton.setSelected(chart1.getLegend().getFontSeriesColor());
        multipleSeriesButton = new JCheckBox("Multiple Series");
        multipleSeriesButton.setSelected(!points[0].getColorEach());
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(legendFontButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(multipleSeriesButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JCheckBox legendFontButton, multipleSeriesButton;
}
