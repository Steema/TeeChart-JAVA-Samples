/*
 * ZoomScrollDemo.java
 *
 * <p>Copyright: Copyright (c) 2004-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.zoom;

import com.steema.teechart.styles.Line;
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
public class ZoomScrollDemo extends ChartSamplePanel
    implements ActionListener {

    /** Creates a new instance of ZoomScrollDemo */
    public ZoomScrollDemo() {
        super();
        scrollButton.addActionListener(this);
        zoomButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == scrollButton) {
            chart1.getAxes().getBottom().scroll(2, false);
        } else if (source == zoomButton) {
            chart1.getAspect().setZoom(110);  // 110 % = zoom in 10%
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getLegend().setVisible(false);
    }

    protected void initComponents() {
        super.initComponents();

        Line series = new Line(chart1.getChart());
        series.fillSampleValues(50);

        scrollButton = new JButton("Scroll !");
        zoomButton = new JButton("Zoom !");
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(zoomButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(scrollButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    JButton scrollButton, zoomButton;
}
