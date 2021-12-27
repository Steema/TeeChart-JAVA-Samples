/*
 * FloatDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.surface.floatingpoint;

import com.steema.teechart.Aspect;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.PaletteStyle;
import com.steema.teechart.styles.Surface;
import com.steema.teechart.tools.Rotate;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class FloatDemo extends ChartSamplePanel {

    /** Creates a new instance of FloatDemo */
    public FloatDemo() {
        super();
    }

    protected void initChart() {
        super.initChart();
        // visual properties...
        Aspect tmpAspect = chart1.getAspect();
        tmpAspect.setChart3DPercent(100);
        tmpAspect.setOrthogonal(false);
        tmpAspect.setPerspective(50);
        tmpAspect.setRotation(327);
        tmpAspect.setElevation(352);
        tmpAspect.setZoom(70);

        // adjust some axes properties...
        chart1.getAxes().getDepth().setVisible(true);
        chart1.getAxes().getDepth().getLabels().setValueFormat("0.#");
        chart1.getAxes().getDepth().setIncrement(0.2);
        chart1.getAxes().getBottom().getLabels().setValueFormat("0.#");
        chart1.getAxes().getBottom().setIncrement(0.1);
    }

    protected void initComponents() {
        super.initComponents();

        Surface series;
        series = new com.steema.teechart.styles.Surface(chart1.getChart());
        series.setIrregularGrid(true); // <-- IMPORTANT ! means X and Z are float

        for (int x=1; x<11; x++) {                          // = 10 rows
            for (int z=1; z <11; z++) {                     // = 10 columns
                series.add(x/10.0, Math.sqrt(x*z), z/5.0);
            }
        }

        //set palette colors to "10, strong" ...
        series.setUseColorRange(false);
        series.setUsePalette(true);
        series.setPaletteStyle(PaletteStyle.STRONG);
        series.setPaletteSteps(10);

        Rotate tool = new Rotate(chart1.getChart());
        tool.getPen().setColor(Color.WHITE);
    }
}
