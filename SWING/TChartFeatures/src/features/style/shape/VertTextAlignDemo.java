/*
 * VertTextAlignDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.shape;
import com.steema.teechart.Dimension;
import com.steema.teechart.ImageMode;
import com.steema.teechart.legend.LegendSymbolSize;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.DashStyle;
import com.steema.teechart.drawing.HatchStyle;
import com.steema.teechart.styles.Shape;
import com.steema.teechart.styles.ShapeStyle;
import com.steema.teechart.styles.ShapeTextVertAlign;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class VertTextAlignDemo extends ChartSamplePanel
    implements ActionListener {

    private Shape[] shape;

    /** Creates a new instance of VertTextAlignDemo */
    public VertTextAlignDemo() {
        super();
        topButton.addActionListener(this);
        centerButton.addActionListener(this);
        bottomButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        String tmp;

        ShapeTextVertAlign tmpAlign = ShapeTextVertAlign.TOP;
        tmp = TEXT_TOP;

        if (source == centerButton) {
            tmpAlign = ShapeTextVertAlign.CENTER;
            tmp = TEXT_CENTER;
        } else if (source == bottomButton) {
            tmpAlign = ShapeTextVertAlign.BOTTOM;
            tmp = TEXT_BOTTOM;
        }

        for (int t=0; t < chart1.getSeriesCount(); t++) {
            shape[t].setVertAlignment(tmpAlign);
            shape[t].setText( new String[] {tmp} );
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getLegend().setColorWidth(15);
        chart1.getLegend().getSymbol().setWidth(15);
        chart1.getLegend().getSymbol().setWidthUnits(LegendSymbolSize.PIXELS);
        chart1.getAxes().getBottom().setAutomatic(false);
        chart1.getAxes().getBottom().setMaximum(100);
        chart1.getAxes().getLeft().setAutomatic(false);
        chart1.getAxes().getLeft().setMaximum(115);
        chart1.getAspect().setView3D(false);
    }

    protected void initSeries() {
        Shape tmpShape;
        shape = new Shape[3];
        //shape1
        shape[0] = new Shape(chart1.getChart());
        tmpShape = shape[0];
        tmpShape.getMarks().setVisible(false);
        tmpShape.setColor(Color.WHITE);
        tmpShape.getBrush().setColor(Color.LIGHT_YELLOW);
        tmpShape.getBrush().setStyle(HatchStyle.DIAGONALCROSS);
        tmpShape.getBrush().loadImage(ChartSamplePanel.class.getResource(URL_IMAGE1));
        tmpShape.getBrush().setImageMode(ImageMode.TILE);
        tmpShape.getFont().getShadow().setSize(new Dimension(-4,-3));
        tmpShape.getFont().setBold(true);
        tmpShape.getFont().setItalic(true);
        tmpShape.setStyle(ShapeStyle.RECTANGLE);
        tmpShape.setVertAlignment(ShapeTextVertAlign.BOTTOM);
        tmpShape.setText(new String[]{TEXT_BOTTOM});
        tmpShape.getPen().setColor(Color.TEAL);
        tmpShape.getPen().setStyle(DashStyle.DOT);
        tmpShape.getPen().setWidth(2);
        tmpShape.setX0(3);
        tmpShape.setX1(44);
        tmpShape.setY0(10);
        tmpShape.setY1(96);

        //shape2
        shape[1] = new Shape(chart1.getChart());
        tmpShape = shape[1];
        tmpShape.getMarks().setVisible(false);
        tmpShape.setColor(Color.WHITE);
        tmpShape.getBrush().setColor(Color.LIGHT_YELLOW);
        tmpShape.getBrush().setStyle(HatchStyle.CROSS);
        tmpShape.getPen().setWidth(2);
        tmpShape.getPen().setColor(Color.BLUE);
        tmpShape.getFont().setColor(Color.BLUE);
        tmpShape.getFont().setSize(13);
        tmpShape.getFont().setBold(true);
        tmpShape.getFont().getShadow().setColor(Color.WHITE);
        tmpShape.getFont().getShadow().setHorizSize(1);
        tmpShape.getFont().getShadow().setVertSize(1);
        tmpShape.setVertAlignment(ShapeTextVertAlign.CENTER);
        tmpShape.setText(new String[]{TEXT_CENTER});
        tmpShape.setStyle(ShapeStyle.RECTANGLE);
        tmpShape.setX0(56);
        tmpShape.setX1(96);
        tmpShape.setY0(63.5);
        tmpShape.setY1(103);

        //shape3
        shape[2] = new Shape(chart1.getChart());
        tmpShape = shape[2];
        tmpShape.getMarks().setVisible(false);
        tmpShape.setColor(Color.WHITE);
        tmpShape.getBrush().setColor(Color.BLUE);
        tmpShape.getBrush().loadImage(ChartSamplePanel.class.getResource(URL_IMAGE2));
        tmpShape.getBrush().setImageMode(ImageMode.TILE);
        tmpShape.getPen().setWidth(2);
        tmpShape.getFont().setColor(Color.YELLOW);
        tmpShape.getFont().setSize(16);
        tmpShape.getFont().setBold(true);
        tmpShape.getFont().getShadow().setColor(Color.BLUE);
        tmpShape.getFont().getShadow().setSize(1);
        tmpShape.setStyle(ShapeStyle.RECTANGLE);
        tmpShape.setVertAlignment(ShapeTextVertAlign.TOP);
        tmpShape.setText(new String[] {TEXT_TOP});
        tmpShape.setX0(32);
        tmpShape.setX1(88);
        tmpShape.setY0(25);
        tmpShape.setY1(60);
    }

    protected void initComponents() {
        super.initComponents();
        initSeries();
        topButton = new JRadioButton("Top");
        centerButton = new JRadioButton("Center");
        bottomButton = new JRadioButton("Bottom");
        ButtonGroup group = new ButtonGroup();
        group.add(topButton);
        group.add(centerButton);
        group.add(bottomButton);
    }

    protected void initGUI() {
        super.initGUI();
        JLabel tmpLabel;
        JPanel tmpPane = getButtonPane();
        {
            tmpLabel = new JLabel("Vertical Align:");
            tmpLabel.setDisplayedMnemonic('V');
            tmpPane.add(tmpLabel);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(topButton);
            tmpPane.add(centerButton);
            tmpPane.add(bottomButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JRadioButton topButton, centerButton, bottomButton;
    private final static String TEXT_TOP = "Text at Top";
    private final static String TEXT_CENTER = "Text at Center";
    private final static String TEXT_BOTTOM = "Text at Bottom";
    private final static String URL_IMAGE1 = "images/horizLines.png";
    private final static String URL_IMAGE2 = "images/crossLines.png";
}
