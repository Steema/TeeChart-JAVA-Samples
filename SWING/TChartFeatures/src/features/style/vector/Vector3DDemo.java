/*
 * Vector3DDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.vector;

import com.steema.teechart.editors.ButtonPen;
import com.steema.teechart.styles.Vector3D;
import javax.swing.Box;
import javax.swing.JPanel;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class Vector3DDemo extends ChartSamplePanel {

    private Vector3D series;

    /** Creates a new instance of Vector3DDemo */
    public Vector3DDemo() {
        super();
    }

    protected void initChart() {
        super.initChart();
        chart1.getHeader().setVisible(true);
        chart1.setText("3D Vectors");
        chart1.getLegend().setVisible(false);
        chart1.getWalls().getBottom().setTransparent(true);
        chart1.getWalls().getLeft().setTransparent(true);
        chart1.getWalls().getRight().setTransparent(true);
        chart1.getWalls().getRight().setVisible(true);
        chart1.getAxes().getRight().setZPosition(0.01);

        chart1.getAspect().setChart3DPercent(55);
        chart1.getAspect().setOrthogonal(false);
        chart1.getAspect().setPerspective(92);
        chart1.getAspect().setRotation(358);
        chart1.getAspect().setZoom(84);
    }

    protected void initComponents() {
        super.initComponents();

        series = new Vector3D(chart1.getChart());
        series.setUsePalette(true);
        series.fillSampleValues();

        penButton = new ButtonPen(series.getPen());
    }

    protected void initGUI() {
        super.initGUI();

        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(penButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private ButtonPen penButton;
}
