/*
 * TransparencyDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.walls;

import com.steema.teechart.Header;
import com.steema.teechart.TextShapeStyle;
import com.steema.teechart.Wall;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.GradientDirection;
import com.steema.teechart.misc.Utils;
import com.steema.teechart.editors.ChartEditor;
import com.steema.teechart.legend.Legend;
import com.steema.teechart.styles.PaletteStyle;
import com.steema.teechart.styles.Surface;
import com.steema.teechart.tools.Rotate;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class TransparencyDemo extends ChartSamplePanel
        implements ActionListener, ChangeListener {

    private Surface surface;
    /**
     * Creates a new instance of TransparencyDemo
     */
    public TransparencyDemo() {
        super();
        editButton.addActionListener(this);
        transparencySlider.addChangeListener(this);
    }

    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if (source == editButton) {
            ChartEditor.editSeries(surface);
        }
    }

    public void stateChanged(ChangeEvent ce) {
        JSlider source = (JSlider)ce.getSource();
        if (transparencySlider == source) {
            if (!source.getValueIsAdjusting()) {
                int value = transparencySlider.getValue();
                chart1.getWalls().getRight().setTransparency(value);
                chart1.getWalls().getLeft().setTransparency(value);
                chart1.getWalls().getBottom().setTransparency(value);
                chart1.getWalls().getBack().setTransparency(value);
                syncUI();
            }
        };
    }

    protected void initChart() {
        super.initChart();
        chart1.getAspect().setChart3DPercent(40);
        chart1.getAspect().setElevation(342);
        chart1.getAspect().setOrthogonal(false);
        chart1.getAspect().setPerspective(73);
        chart1.getAspect().setRotation(311);
        chart1.getAspect().setZoom(97);

        Wall wall;
        wall = chart1.getWalls().getBack();
        wall.setColor(Color.WHITE);
        wall.getGradient().setDirection(GradientDirection.RADIAL);
        wall.getGradient().setEndColor(Color.WHITE);
        wall.getGradient().setStartColor(Color.YELLOW);
        wall.setTransparent(false);
        wall = chart1.getWalls().getBottom();
        wall.setTransparency(40);
        wall = chart1.getWalls().getLeft();
        wall.setColor(Color.WHITE);
        wall.setTransparency(40);
        wall = chart1.getWalls().getRight();
        wall.setTransparency(40);
        wall.setVisible(true);

        chart1.getPanel().getGradient().setEndColor(Color.RED);
        chart1.getPanel().getGradient().setStartColor(Color.GREEN);
        chart1.getPanel().getGradient().setVisible(true);

        Legend legend = chart1.getLegend();
        legend.getShadow().setSize(6);
        legend.getShadow().setTransparency(70);
        legend.setShapeStyle(TextShapeStyle.ROUNDRECTANGLE);
        legend.setTransparency(30);

        Header header = chart1.getHeader();
        header.setColor(Color.SILVER);
        header.getFont().setColor(Color.WHITE);
        header.getFont().setSize(16);
        header.getFont().setBold(true);
        header.getFont().getShadow().setColor(Color.BLACK);
        header.getFont().getShadow().setSize(2);
        header.setText("Walls transparency");
        header.setVisible(true);
        header.setTransparent(false);

        new Rotate(chart1.getChart());
    }

    protected void initSeries() {
        surface = new Surface(chart1.getChart());
        surface.getMarks().setArrowLength(8);
        surface.getMarks().getCallout().getBrush().setColor(Color.BLACK);
        surface.getMarks().getCallout().setLength(8);
        surface.getMarks().setVisible(false);
        surface.setPaletteStyle(PaletteStyle.STRONG);
        surface.getSideBrush().setColor(Color.WHITE);
        surface.getSideBrush().setVisible(false);
        surface.setUseColorRange(false);
        surface.setUsePalette(true);
        for(double x=-10; x < 11; x++) {
            for(double z=-10; z < 11; z++) {
                surface.add(x, calcYValue(x,z), z);
            }
        }
    }

    protected void initComponents() {
        super.initComponents();
        initSeries();
        transparencySlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 40);
        transparentLabel = new JLabel();
        editButton = new JButton("Edit...");
        syncUI();
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(transparencySlider);
            tmpPane.add(transparentLabel);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(editButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }


    private static final double a=3.0;
    private static final double b=10.0;
    private static final double c=5.0;
    private static final double d=1.0;

    private double calcYValue(double x, double z) {
        x = x / 3.0;
        z = z / 3.0;
        double result =  a*Utils.sqr(d-x)*Math.exp(-(x*x)-Utils.sqr(z+d))
              -b*(x/c-(x*x*x)-(z*z*z*z*z))*Math.exp(-Utils.sqr(x)-Utils.sqr(z))
              -(d/a)*Math.exp(-Utils.sqr(x+d)-Utils.sqr(z));
        return result;
    }

    private void syncUI() {
        transparentLabel.setText(String.valueOf(transparencySlider.getValue())+"%");
    }

    private JButton editButton;
    private JLabel transparentLabel;
    private JSlider transparencySlider;
}
