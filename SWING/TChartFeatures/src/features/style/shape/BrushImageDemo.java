/*
 * BrushImageDemo.java
 *
 * <p>Copyright: (c) 2005-2007
 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.shape;

import com.steema.teechart.ImageMode;
import com.steema.teechart.legend.LegendSymbolSize;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.Shape;
import com.steema.teechart.styles.ShapeStyle;
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
public class BrushImageDemo extends ChartSamplePanel
        implements ItemListener {

    private Shape[] shape;

    /**
     * Creates a new instance of BrushImageDemo
     */
    public BrushImageDemo() {
        super();
        transparentButton.addItemListener(this);
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == transparentButton) {
            shape[0].setTransparent(isSelected);
            shape[1].setTransparent(isSelected);
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getLegend().setColorWidth(15);
        chart1.getLegend().getFont().setSize(16);
        chart1.getLegend().getFont().setBold(true);
        chart1.getLegend().getSymbol().setWidth(15);
        chart1.getLegend().getSymbol().setWidthUnits(LegendSymbolSize.PIXELS);
    }

    protected void initSeries() {
        Shape tmpShape;
        shape = new Shape[2];

        //shape1
        shape[0] = new Shape(chart1.getChart());
        tmpShape = shape[0];
        tmpShape.getMarks().setVisible(false);
        tmpShape.setColor(Color.WHITE);
        tmpShape.getBrush().setColor(Color.WHITE);
        tmpShape.getBrush().setImageMode(ImageMode.TILE);
        tmpShape.getBrush().loadImage(ChartSamplePanel.class.getResource(URL_IMAGE1));

        tmpShape.setStyle(ShapeStyle.CIRCLE);
        tmpShape.getPen().setColor(Color.RED);
        tmpShape.getPen().setWidth(2);
        tmpShape.setX1(50);
        tmpShape.setY1(50);

        //shape2
        shape[1] = new Shape(chart1.getChart());
        tmpShape = shape[1];
        tmpShape.getMarks().setVisible(false);
        tmpShape.setColor(Color.WHITE);
        tmpShape.getBrush().setColor(Color.WHITE);
        tmpShape.getBrush().setImageMode(ImageMode.TILE);
        tmpShape.getBrush().loadImage(ChartSamplePanel.class.getResource(URL_IMAGE2));
        tmpShape.setStyle(ShapeStyle.RECTANGLE);
        tmpShape.getPen().setColor(Color.BLUE);
        tmpShape.getPen().setWidth(2);
        tmpShape.setX0(62.5);
        tmpShape.setX1(87.5);
        tmpShape.setY0(17.5);
        tmpShape.setY1(126);
    }

    protected void initComponents() {
        super.initComponents();
        initSeries();
        transparentButton = new JCheckBox("Transparent Shapes");
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(transparentButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JCheckBox transparentButton;
    private final static String URL_IMAGE1 = "images/shapeimage1.png";
    private final static String URL_IMAGE2 = "images/shapeimage2.png";
}
