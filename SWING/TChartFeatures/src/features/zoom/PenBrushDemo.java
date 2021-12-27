/*
 * PenBrushDemo.java
 *
 * <p>Copyright: Copyright (c) 2004-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.zoom;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.editors.ButtonPen;
import com.steema.teechart.editors.DialogFactory;
import com.steema.teechart.styles.FastLine;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class PenBrushDemo extends ChartSamplePanel
    implements ActionListener{

    /** Creates a new instance of PenBrushDemo */
    public PenBrushDemo() {
        super();
        brushButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == brushButton) {
           DialogFactory.showModal(chart1.getZoom().getBrush());
        }
    }
    protected void initChart() {
        super.initChart();
        chart1.getZoom().getPen().setColor(Color.LIME);
        chart1.getZoom().getPen().setWidth(2);
        chart1.getZoom().getBrush().setSolid(true);
        chart1.getZoom().getBrush().setColor(Color.BLUE);
    }

    protected void initComponents() {
        super.initComponents();
        FastLine series = new FastLine(chart1.getChart());
        series.fillSampleValues(200);
        penButton = new ButtonPen(chart1.getZoom().getPen(), "Zoom Pen...");
        brushButton = new JButton("Zoom Brush...");
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(penButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(brushButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(new JLabel("<html><b>Drag to zoom...</b></html>"));
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private ButtonPen penButton;
    private JButton brushButton;
}
