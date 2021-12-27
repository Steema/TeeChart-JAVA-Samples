/*
 * TriSurfaceDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.trisurface;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.DashStyle;
import com.steema.teechart.editors.ButtonPen;
import com.steema.teechart.styles.TriSurface;
import javax.swing.Box;
import javax.swing.JPanel;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class TriSurfaceDemo extends ChartSamplePanel {

    private TriSurface series;

    /** Creates a new instance of TriSurfaceDemo */
    public TriSurfaceDemo() {
        super();
    }

    protected void initChart() {
        super.initChart();
        chart1.getAxes().getDepth().setVisible(true);

        chart1.getWalls().getBack().setSize(10);
        chart1.getWalls().getBottom().setSize(10);
        chart1.getWalls().getLeft().setSize(10);
        chart1.getLegend().setVisible(false);

        chart1.getAspect().setChart3DPercent(70);
        chart1.getAspect().setElevation(334);
        chart1.getAspect().setOrthogonal(false);
        chart1.getAspect().setPerspective(35);
        chart1.getAspect().setZoom(60);
    }

    protected void initComponents() {
        super.initComponents();


        series = new TriSurface(chart1.getChart());

        series.fillSampleValues(30);

        series.getOutline().setColor(Color.RED);
        series.getOutline().setWidth(2);
        series.getOutline().setVisible(true);
        series.getBrush().setColor(Color.WHITE);
        series.getPen().setStyle(DashStyle.DOT);
        series.setEndColor(Color.LIME);

        borderButton = new ButtonPen(series.getOutline(), "Border...");
        penButton = new ButtonPen(series.getPen());
    }

    protected void initGUI() {
        super.initGUI();

        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(borderButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(penButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private ButtonPen penButton, borderButton;
}
