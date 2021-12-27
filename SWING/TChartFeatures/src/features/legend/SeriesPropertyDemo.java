/*
 * SideAllDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.legend;
import com.steema.teechart.legend.LegendStyle;
import com.steema.teechart.styles.Bar;
import com.steema.teechart.styles.Line;
import com.steema.teechart.styles.Series;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class SeriesPropertyDemo extends ChartSamplePanel
        implements ActionListener {

    private Series series1, series2;

    /**
     * Creates a new instance of SeriesPropertyDemo
     */
    public SeriesPropertyDemo() {
        super();
        barButton.addActionListener(this);
        lineButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == barButton) {
            chart1.getLegend().setSeries(series1);
        } else if (source == lineButton) {
            chart1.getLegend().setSeries(series2);
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getLegend().setColorWidth(30);
        chart1.getLegend().setLegendStyle(LegendStyle.VALUES);
        chart1.getLegend().getSymbol().setWidth(30);
        chart1.getAspect().setView3D(false);
    }

    protected void initSeries() {
        series1 = new Bar(chart1.getChart());
        series1.getMarks().setArrowLength(20);
        series1.getMarks().setVisible(true);

        series2 = new Line(chart1.getChart());
        series2.getMarks().setArrowLength(8);
        series2.getMarks().setVisible(true);
        ((Line)series2).getPointer().setInflateMargins(false);
        ((Line)series2).getPointer().setVisible(true);

        series1.fillSampleValues(5);
        series2.fillSampleValues(8);
    }

    protected void initComponents() {
        super.initComponents();
        initSeries();

        barButton = new JRadioButton("Bar");
        barButton.setSelected(true);
        lineButton = new JRadioButton("Line");
        ButtonGroup group = new ButtonGroup();
        group.add(barButton);
        group.add(lineButton);
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(new JLabel("Use series: "));
            tmpPane.add(barButton);
            tmpPane.add(lineButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JRadioButton barButton, lineButton;
}
