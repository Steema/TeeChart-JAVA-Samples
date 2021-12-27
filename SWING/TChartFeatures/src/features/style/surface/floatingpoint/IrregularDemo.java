/*
 * IrregularDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.surface.floatingpoint;

import com.steema.teechart.legend.Legend;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.PaletteStyle;
import com.steema.teechart.styles.Surface;
import com.steema.teechart.tools.Rotate;
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
public class IrregularDemo extends ChartSamplePanel
        implements ItemListener {

    private Surface series;

    /** Creates a new instance of IrregularDemo */
    public IrregularDemo() {
        super();
        irregularButton.addItemListener(this);
        view2DButton.addItemListener(this);
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == irregularButton) {
            series.setIrregularGrid(isSelected);
        } else if (source == view2DButton) {
            if (isSelected) {
                chart1.getAspect().setElevation(270);
                chart1.getAspect().setRotation(360);
            }  else {
                chart1.getAspect().setElevation(345);
                chart1.getAspect().setRotation(345);
            }
        }
    }

    protected void initChart(){
        super.initChart();
        Legend tmpLegend = chart1.getLegend();
        tmpLegend.setColorWidth(30);
        tmpLegend.getSymbol().setWidth(30);
        tmpLegend.getSymbol().setContinuous(true);
        tmpLegend.getSymbol().setDefaultPen(false);

        chart1.setClipPoints(false);
        chart1.getAspect().setChart3DPercent(85);
        chart1.getAspect().setOrthogonal(false);
        chart1.getAspect().setPerspective(55);
        chart1.getAspect().setZoom(60);
    }

    protected void initComponents() {
        super.initComponents();

        series = new com.steema.teechart.styles.Surface(chart1.getChart());
        series.getMarks().setVisible(false);
        series.setIrregularGrid(true);          // <---------- VERY IMPORTANT !!!
        series.getVertAxis().setMinMax(-2,2);   // axis scale for Y values
        series.setUseColorRange(false);
        series.setUsePalette(true);
        series.setPaletteStyle(PaletteStyle.STRONG);

        /* Arrays of X and Z values with sample points...
         * The values have floating point decimals and define
         * an irregular grid
         */

        double[] xval = {0.1,0.2,0.3,0.5,0.8,1.1,1.5,2.0,2.2,3.0};
        double[] zval = {0.5,0.6,0.7,0.75,0.8,1.1,1.5,2.0,2.2,5.6};

        // Now add all "Y" points...
        series.clear();

        // An irregular grid of 10 x 10 cells
        series.setNumXValues(10);
        series.setNumZValues(10);

        double y;
        for (int x=0; x<10; x++) {                          // = 10 rows
            for (int z=0; z <10; z++) {                     // = 10 columns
                y = Math.sin(z*Math.PI/10.0)*Math.cos(x*Math.PI/5.0);               // example Y value
                series.add(xval[x], y, zval[z]);
            }
        }

        Rotate tool = new com.steema.teechart.tools.Rotate(chart1.getChart());
        tool.getPen().setColor(Color.WHITE);

        irregularButton = new JCheckBox("Irregular surface");
        irregularButton.setSelected(series.getIrregularGrid());
        view2DButton = new JCheckBox("2D");
    }

    protected void initGUI() {
        super.initGUI();

        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(irregularButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(view2DButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JCheckBox irregularButton, view2DButton;
}
