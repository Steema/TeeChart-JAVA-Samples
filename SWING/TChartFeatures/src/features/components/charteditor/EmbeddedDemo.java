/*
 * EmbeddedDemo.java
 *
 * <p>Copyright: (c) 2004-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.components.charteditor;

import com.steema.teechart.TextShapeStyle;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.editors.ChartEditorPanel;
import com.steema.teechart.legend.LegendAlignment;
import com.steema.teechart.styles.HorizLine;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class EmbeddedDemo extends ChartSamplePanel {

    /** Creates a new instance of EmbeddedDemo */
    public EmbeddedDemo() {
        super();
    }

    protected void initChart() {
        super.initChart();
        chart1.getFooter().getFont().setColor(Color.SILVER);
        chart1.getFooter().getFont().setBold(true);
        chart1.getFooter().getShadow().setColor(Color.GRAY);
        chart1.getFooter().getShadow().setSize(2);
        chart1.getFooter().setShapeStyle(TextShapeStyle.ROUNDRECTANGLE);
        chart1.getFooter().setText("Click me");
        chart1.getFooter().setTransparent(false);
        chart1.getHeader().setText("Title");

        chart1.getLegend().setAlignment(LegendAlignment.BOTTOM);
        chart1.getAspect().setChart3DPercent(20);
        chart1.getPage().setMaxPointsPerPage(10);
    }

    protected void initComponents() {
        super.initComponents();
        HorizLine tmpSeries = new HorizLine(chart1.getChart());
        tmpSeries.fillSampleValues(10);
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpEditor = new ChartEditorPanel(chart1.getChart());
        JSplitPane tmpSplitPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                tmpEditor,
                chart1
                
                );
        tmpSplitPane.setDividerLocation(500);
        setSamplePane(tmpSplitPane);
    }
}
