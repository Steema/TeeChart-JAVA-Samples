/*
 * MultipleDemo.java
 *
 * <p>Copyright: Copyright (c) 2004-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.legend;

import com.steema.teechart.events.ChartPaintAdapter;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.legend.LegendStyle;
import com.steema.teechart.styles.Bar;
import com.steema.teechart.styles.Series;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import features.ChartSamplePanel;
import com.steema.teechart.events.ChartDrawEvent;

/**
 *
 * @author tom
 */
public class MultipleDemo extends ChartSamplePanel
        implements ItemListener {

    /** Creates a new instance of MultipleDemo */
    public MultipleDemo() {
        super();
        show2ndLegendButton.addItemListener(this);
        chart1.addChartPaintListener( new ChartPaintAdapter() {

            public void chartPainted(ChartDrawEvent pce) {
                if (show2ndLegendButton.isSelected()) {
                    if (chart1.getSeriesCount()>1) {
                        chart1.getLegend().setTop(125);
                        chart1.getLegend().setSeries(chart1.getSeries(1));
                        chart1.getLegend().paint();
                        chart1.getLegend().setCustomPosition(false);
                        chart1.getLegend().setSeries(chart1.getSeries(0));
                    }
                }
            };
        });
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == show2ndLegendButton) {
            chart1.repaint();
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getLegend().setLegendStyle(LegendStyle.VALUES);
    }

    protected void initSeries() {
        Series tmpSeries;
        for (int t=0; t<2; t++) {
            tmpSeries = new Bar(chart1.getChart());
            tmpSeries.getMarks().getCallout().getBrush().setColor(Color.BLACK);
            tmpSeries.getMarks().setVisible(true);
        }
        chart1.getSeries().fillSampleValues(4);
        chart1.getLegend().setSeries(chart1.getSeries(0));
    }

    protected void initComponents() {
        super.initComponents();
        initSeries();

        show2ndLegendButton = new JCheckBox("Show 2nd legend");
        show2ndLegendButton.setSelected(true);
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(show2ndLegendButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JCheckBox show2ndLegendButton;
}
