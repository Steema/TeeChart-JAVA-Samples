/*
 * SymbolsBorderDemo.java
 *
 * <p>Copyright: Copyright (c) 2004-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.legend;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.editors.ButtonPen;
import com.steema.teechart.legend.Legend;
import com.steema.teechart.styles.Bar;
import com.steema.teechart.styles.Series;

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
public class SymbolsBorderDemo extends ChartSamplePanel
    implements ItemListener {

    private ButtonPen penButton;

    /** Creates a new instance of SymbolsBorderDemo */
    public SymbolsBorderDemo() {
        super();
        useSeriesBorderButton.addItemListener(this);
        squaredSymbolsButton.addItemListener(this);
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == useSeriesBorderButton) {
            chart1.getLegend().getSymbol().setDefaultPen(isSelected);
            penButton.setEnabled(!isSelected);
        } else if (source == squaredSymbolsButton) {
            chart1.getLegend().getSymbol().setSquared(isSelected);
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getHeader().setText("Custom Legend Symbol Border");
        chart1.getHeader().setVisible(true);
        Legend legend = chart1.getLegend();
        legend.getFont().setSize(19);
        legend.getGradient().setVisible(true);
        // Do not use series border to display legend symbols:
        legend.getSymbol().setDefaultPen(false);
        // Customize border:
        legend.getSymbol().getPen().setColor(Color.RED);
        legend.getSymbol().getPen().setWidth(2);
    }

    protected void initSeries() {
        Series tmpSeries = new Bar(chart1.getChart());
        tmpSeries.getMarks().setArrowLength(20);
        tmpSeries.getMarks().getCallout().getBrush().setColor(Color.BLACK);
        tmpSeries.getMarks().getCallout().setLength(20);
        tmpSeries.getMarks().setVisible(true);
        chart1.getSeries().fillSampleValues(10);
    }

    protected void initComponents() {
        super.initComponents();
        initSeries();
        penButton = new ButtonPen(chart1.getLegend().getSymbol().getPen(), "Edit Border...");

        useSeriesBorderButton = new JCheckBox("Use series border");
        useSeriesBorderButton.setSelected(chart1.getLegend().getSymbol().getDefaultPen());
        squaredSymbolsButton = new JCheckBox("Squared symbols");
        squaredSymbolsButton.setSelected(chart1.getLegend().getSymbol().getSquared());
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(useSeriesBorderButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(penButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(squaredSymbolsButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JCheckBox useSeriesBorderButton, squaredSymbolsButton;
}
