/*
 * GradientDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by
 Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.polar;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.DashStyle;
import com.steema.teechart.drawing.GradientDirection;
import com.steema.teechart.editors.DialogFactory;
import com.steema.teechart.styles.Polar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class GradientDemo extends ChartSamplePanel
    implements ActionListener {

    private Polar series;

    /** Creates a new instance of GradientDemo */
    public GradientDemo() {
        super();
        gradientButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == gradientButton) {
            DialogFactory.showModal(series.getCircleGradient());
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getLegend().setVisible(false);
        chart1.getAspect().setView3D(false);
        chart1.getAxes().getBottom().setIncrement(10.0);
    }

    protected void initComponents() {
        super.initComponents();
        series = new com.steema.teechart.styles.Polar(chart1.getChart());
        series.fillSampleValues(20);
        series.setCircled(true);
        series.getCircleGradient().setDirection(GradientDirection.RADIAL);
        series.getCircleGradient().setStartColor(Color.WHITE);
        series.getCircleGradient().setEndColor(Color.DARK_GRAY);
        series.getCircleGradient().setRadialX(100);
        series.getCircleGradient().setRadialY(-100);
        series.getCircleGradient().setVisible(true);
        series.getCirclePen().setColor(Color.NAVY);
        series.getCirclePen().setStyle(DashStyle.DOT);
        series.getCirclePen().setWidth(2);

        series.getBrush().setColor(Color.WHITE);
        series.getBrush().setVisible(false);
        series.getPen().setColor(Color.RED);

        gradientButton = new JButton("Edit gradient...");
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(gradientButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JButton gradientButton;
}
