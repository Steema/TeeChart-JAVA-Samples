/*
 * HidingDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.trisurface;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.misc.Utils;
import com.steema.teechart.styles.PaletteStyle;
import com.steema.teechart.styles.TriSurface;

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
public class HidingDemo extends ChartSamplePanel
    implements ItemListener {

    private TriSurface series;

    /** Creates a new instance of HidingDemo */
    public HidingDemo() {
        super();
        hideTrianglesButton.addItemListener(this);
        cacheTrianglesButton.addItemListener(this);
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == hideTrianglesButton) {
            series.setHideTriangles(isSelected);
        } else if (source == cacheTrianglesButton) {
            series.setCacheTriangles(isSelected);
            series.invalidate();
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getAxes().getDepth().setVisible(true);
        chart1.getLegend().setVisible(false);
        chart1.getWalls().getBottom().setTransparent(true);
        chart1.getWalls().getLeft().setTransparent(true);
        chart1.getPanel().getGradient().setEndColor(Color.GRAY);
        chart1.getPanel().getGradient().setStartColor(Color.WHITE);
        chart1.getPanel().getGradient().setVisible(true);

        chart1.getAspect().setChart3DPercent(75);
        chart1.getAspect().setElevation(339);
        chart1.getAspect().setOrthogonal(false);
        chart1.getAspect().setPerspective(72);
        chart1.getAspect().setRotation(319);
        chart1.getAspect().setZoom(70);
    }

    protected void initComponents() {
        super.initComponents();

        series = new TriSurface(chart1.getChart());
        series.setHideTriangles(true);
        series.setCacheTriangles(true);
        series.getPen().setVisible(false);
        series.setPaletteStyle(PaletteStyle.STRONG);
        series.setEndColor(Color.SKY_BLUE);
        series.setMidColor(Color.SILVER);

        fillSeries();

        hideTrianglesButton = new JCheckBox("Hide Triangles");
        hideTrianglesButton.setSelected(series.getHideTriangles());
        cacheTrianglesButton = new JCheckBox("Cache Triangles");
        cacheTrianglesButton.setSelected(series.getCacheTriangles());
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(hideTrianglesButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(cacheTrianglesButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private void fillSeries() {
        series.clear();
        double n = 0.5;
        int m = 10;
        for (int x=-m; x<=m; x++) {
            for (int z=-m; z<=m; z++) {
                series.add(
                    x,
                    4*Math.cos(3*Math.sqrt(Utils.sqr(x/3)+Utils.sqr(z/3)))*Math.exp(-n*(Math.sqrt(Utils.sqr(x/3)+Utils.sqr(z/3)))),
                    z
                );
            }
        }
    }

    private JCheckBox hideTrianglesButton, cacheTrianglesButton;
}
