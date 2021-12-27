/*
 * BarJoinDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.bar;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.editors.ButtonPen;
import com.steema.teechart.styles.BarJoin;
import javax.swing.Box;
import javax.swing.JPanel;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class BarJoinDemo extends ChartSamplePanel {

    private BarJoin series;
    private ButtonPen penButton;

    /** Creates a new instance of BarJoinDemo */
    public BarJoinDemo() {
        super();
    }

    protected void initChart() {
        super.initChart();
        chart1.getAspect().setView3D(false);
    }

    protected void initComponents() {
        super.initComponents();
        series = new BarJoin(chart1.getChart());
        series.fillSampleValues(3);
        series.getJoinPen().setWidth(2);
        series.setColor(Color.RED);
        penButton = new ButtonPen(series.getJoinPen(), "Join...");
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(penButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }
}
