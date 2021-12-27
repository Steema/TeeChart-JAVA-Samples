/*
 * NegativeStackedDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.bar;

import com.steema.teechart.styles.Bar;
import com.steema.teechart.styles.MultiBars;
import com.steema.teechart.tools.ColorLine;
import features.SamplePanel;

/**
 *
 * @author tom
 */
public class NegativeStackedDemo extends SamplePanel {

    /**
     * Creates a new instance of NegativeStackedDemo
     */
    public NegativeStackedDemo() {
        super();
    }

    protected void initChart() {
        super.initChart();
        chart1.getHeader().setVisible(true);
        chart1.getHeader().setText("Negative and Positive Stacked Bars");
    }

    protected void initComponents() {
        super.initComponents();

        ColorLine tmpTool = new ColorLine(chart1.getChart());
        tmpTool.setDraw3D(true);
        tmpTool.setAxis(chart1.getAxes().getLeft());

        Bar[] barSeries = new Bar[3];
        for (int i=0; i < barSeries.length; i++) {
            barSeries[i] = new com.steema.teechart.styles.Bar(chart1.getChart());
            barSeries[i].setMultiBar(MultiBars.STACKED);
            barSeries[i].getMarks().setVisible(false);
        }
        barSeries[0].add(new int[] {23, -56, 42, 9, -8});
        barSeries[1].add(new int[] {32, -16, 9, 39, -28});
        barSeries[2].add(new int[] {12, -21, 14, 22, -13});
    }

    protected void initGUI() {
        super.initGUI();
        getButtonPane().setVisible(false);
        myCommander.setVisible(true);
    }
}
