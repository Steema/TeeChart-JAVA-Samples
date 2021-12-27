/*
 * TrendDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.function.trend;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.functions.Function;
import com.steema.teechart.styles.Area;
import com.steema.teechart.styles.Line;
import com.steema.teechart.styles.MarksStyle;
import com.steema.teechart.styles.VerticalAxis;
import com.steema.teechart.tools.MarksTip;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;

import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class TrendDemo extends ChartSamplePanel
    implements ActionListener {

    private Area sourceSeries;
    private Function trendFunction;

    /**
     * Creates a new instance of TrendDemo
     */
    public TrendDemo() {
        super();
        randomButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == randomButton) {
            sourceSeries.fillSampleValues(20);
        }
    }

    protected void initComponents() {
        super.initComponents();

        sourceSeries = new com.steema.teechart.styles.Area(chart1.getChart());
        sourceSeries.getMarks().setVisible(false);
        sourceSeries.getPointer().setVisible(false);
        sourceSeries.setTitle("Source");
        sourceSeries.setColor(Color.RED);
        sourceSeries.fillSampleValues(20);

        trendFunction = new com.steema.teechart.functions.Trend(chart1.getChart());
        trendFunction.setPeriod(0);

        Line functionSeries = new com.steema.teechart.styles.Line(chart1.getChart());
        functionSeries.setDataSource(sourceSeries);
        functionSeries.setFunction(trendFunction);
        functionSeries.setTitle("Trend");
        functionSeries.setColor(Color.GREEN);
        functionSeries.getMarks().setVisible(false);
        functionSeries.getPointer().setVisible(false);
        functionSeries.setVerticalAxis(VerticalAxis.RIGHT);

        MarksTip tmpTool = new MarksTip(chart1.getChart());
        tmpTool.setSeries(sourceSeries);
        tmpTool.setStyle(MarksStyle.XY);

        randomButton = new JButton("Random!");
    }

    protected void initGUI() {
        super.initGUI();
        chart1.getHeader().setVisible(true);
        chart1.setText("Trend Function Example");

        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(randomButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JButton randomButton;
}
