/*
 * TransparencyDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.surface;

import com.steema.teechart.Aspect;
import com.steema.teechart.legend.LegendSymbolPosition;
import com.steema.teechart.Wall;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.GradientDirection;
import com.steema.teechart.styles.Surface;
import com.steema.teechart.tools.Rotate;
import javax.swing.Box;
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
    implements ChangeListener {

    private Surface series;

    /** Creates a new instance of TransparencyDemo */
    public TransparencyDemo() {
        super();
        transparencySlider.addChangeListener(this);
    }

    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider)e.getSource();
        if (!source.getValueIsAdjusting()) {
            int tmp = (int)source.getValue();
            //series.setTransparency(tmp);
            transparencyLabel.setText(Integer.toString(tmp)+"%");
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getHeader().setVisible(true);
        chart1.setText("Surface and Transparency");
        Wall tmpWall;
        tmpWall = chart1.getWalls().getBack();
        tmpWall.setColor( new Color(33023));
        tmpWall.setTransparent(false);
        tmpWall = chart1.getWalls().getLeft();
        tmpWall.setTransparent(true);
        tmpWall.setTransparency(50);
        tmpWall = chart1.getWalls().getRight();
        tmpWall.setTransparent(true);
        tmpWall.setTransparency(50);
        tmpWall.setTransparent(false);

        chart1.getPanel().getGradient().setDirection(GradientDirection.HORIZONTAL);
        chart1.getPanel().getGradient().setStartColor(Color.WHITE);
        chart1.getPanel().getGradient().setEndColor(Color.SILVER);
        chart1.getPanel().getGradient().setVisible(true);

        chart1.getLegend().getSymbol().setContinuous(true);
        chart1.getLegend().getSymbol().setPosition(LegendSymbolPosition.RIGHT);

        chart1.getAxes().getDepth().setVisible(true);

        Aspect tmpAspect = chart1.getAspect();
        tmpAspect.setElevation(338);
        tmpAspect.setOrthogonal(false);
        tmpAspect.setPerspective(83);
        tmpAspect.setZoom(72);
        tmpAspect.setChart3DPercent(75);
        tmpAspect.setClipPoints(false);
    }

    protected void initComponents() {
        super.initComponents();
        series = new com.steema.teechart.styles.Surface(chart1.getChart());
        series.fillSampleValues();
        //series.setTransparency(50);

        Rotate tool = new com.steema.teechart.tools.Rotate(chart1.getChart());

        transparencySlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
        transparencyLabel = new JLabel("50%");
    }

    protected void initGUI() {
        super.initGUI();
        JLabel tmpLabel;
        JPanel tmpPane = getButtonPane();
        {
            tmpLabel = new JLabel("Transparency:");
            tmpLabel.setDisplayedMnemonic('T');
            tmpLabel.setLabelFor(transparencySlider);
            tmpPane.add(tmpLabel);
            tmpPane.add(transparencySlider);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(transparencyLabel);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JSlider transparencySlider;
    private JLabel transparencyLabel;
}
