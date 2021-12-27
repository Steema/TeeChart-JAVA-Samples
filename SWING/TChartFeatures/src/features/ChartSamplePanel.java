/*
 * ChartSamplePanel.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features;

import com.steema.teechart.TChart;
import com.steema.teechart.drawing.Gradient;
import com.steema.teechart.drawing.GradientDirection;
import com.steema.teechart.themes.ColorPalettes;
import com.steema.teechart.editors.ChartEditor;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * A sample panel with a TChart 'chart1' instance
 *
 * @author tom
 */
public class ChartSamplePanel extends BasicSamplePanel {

    protected TChart chart1;

    /** Creates a new instance of ChartSamplePanel */
    public ChartSamplePanel() {
        super();
    }

    protected void initChart() {
        chart1 = new TChart();

        chart1.getFooter().setVisible(false);
        chart1.getHeader().setVisible(false);
        chart1.getAspect().setView3D(true);

        Gradient g=chart1.getPanel().getGradient();

        g.setDirection(GradientDirection.VERTICAL);

        // Smooth gray-like colors that adapt correctly to all demos:
        g.setEndColor(new com.steema.teechart.drawing.Color(109,109,109));
        g.setMiddleColor(new com.steema.teechart.drawing.Color(149,202,255));
        g.setStartColor(new com.steema.teechart.drawing.Color(0,115,230));

        g.setVisible(true);

        chart1.getAxes().getLeft().getAxisPen().setWidth(1);
        chart1.getAxes().getBottom().getAxisPen().setWidth(1);

        chart1.getHeader().getFont().setColor(new com.steema.teechart.drawing.Color(226,226,226));

        ColorPalettes.applyPalette(chart1.getChart(),1); // Excel color palette
    }

    protected void initComponents() {
        super.initComponents();
        initChart();

        // Trick: Show the Chart editor modal dialog when double-clicking the "description" text area
        sampleDescription.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount()==2) {
                    ChartEditor.editChart(chart1.getChart());
                }
            }

        });
    }

    protected void initGUI() {
        super.initGUI();
        setSamplePane(chart1);
    }
}
