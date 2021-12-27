/*
 * ChartPrintPreviewDemo.java
 *
 * <p>Copyright: (c) 2004-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.print;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.editors.PreviewDialog;
import com.steema.teechart.styles.Map;
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
public class ChartPrintPreviewDemo extends ChartSamplePanel
    implements ActionListener {

    /**
     * Creates a new instance of ChartPrintPreviewDemo
     */
    public ChartPrintPreviewDemo() {
        super();
        printPreviewButton.addActionListener(this);
        customButton.addActionListener(this);
        chartPreviewButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == printPreviewButton) {
            // WAY 1: using a single method...
            PreviewDialog.showModal(chart1.getChart());
        } else if (source == customButton) {
            // WAY 2, customizing the print preview dialog
            PreviewDialog d = new PreviewDialog(chart1.getChart());
            d.getPreviewPanel().setPaperColor(Color.SILVER);
            d.setModal(true);
            d.setVisible(true);
        } else if (source == chartPreviewButton) {
            // needed?
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getLegend().setVisible(false);
        chart1.getWalls().getBack().getPen().setVisible(false);
        chart1.getHeader().setText("Print Preview Example");
        chart1.getHeader().setVisible(true);
        chart1.getAxes().setVisible(false);
        chart1.setClipPoints(false);
        chart1.getAspect().setView3D(false);
    }

    protected void initComponents() {
        super.initComponents();

        Map series = new Map(chart1.getChart());
        series.getMarks().setVisible(true);
        series.fillSampleValues(15);

        printPreviewButton = new JButton("Print Preview...");
        customButton = new JButton("Custom...");
        chartPreviewButton = new JButton("Chart Preview component...");
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(printPreviewButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(customButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(chartPreviewButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JButton printPreviewButton, customButton, chartPreviewButton;
}
