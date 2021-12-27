/*
 * PreviewDialogDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.print;

import com.steema.teechart.editors.PreviewDialog;
import com.steema.teechart.styles.Bar;
import com.steema.teechart.styles.BarStyle;

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
public class PreviewDialogDemo extends ChartSamplePanel
    implements ActionListener {

    /**
     * Creates a new instance of PreviewDialogDemo
     */
    public PreviewDialogDemo() {
        super();
        previewButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == previewButton) {
            PreviewDialog d = new PreviewDialog(chart1.getChart());
            d.setModal(true);
            d.setVisible(true);
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getAspect().setChart3DPercent(20);
        chart1.getPrinter().getMargins().setLeft(15);
        chart1.getPrinter().getMargins().setTop(30);
        chart1.getPrinter().getMargins().setRight(15);
        chart1.getPrinter().getMargins().setBottom(30);
    }

    protected void initComponents() {
        super.initComponents();
        Bar series = new Bar(chart1.getChart());
        series.setColorEach(true);
        series.getMarks().setArrowLength(20);
        series.getMarks().setVisible(true);
        series.setBarStyle(BarStyle.CYLINDER);
        series.fillSampleValues(6);
        previewButton = new JButton("Print Preview...");
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(previewButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JButton previewButton;
}
