/*
 * ExportDialogDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.exports;

import com.steema.teechart.editors.export.ExportPanel;
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
public class ExportDialogDemo extends ChartSamplePanel
    implements ActionListener {

    /** Creates a new instance of ExportDialogDemo */
    public ExportDialogDemo() {
        super();
        exportDialogButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == exportDialogButton) {
            ExportPanel.showModal(chart1.getChart());
        }
    }

    protected void initChart() {
        super.initChart();
    }

    protected void initComponents() {
        super.initComponents();
        Line series = new Line(chart1.getChart());
        series.getMarks().setVisible(false);
        series.getPointer().setVisible(false);
        series.fillSampleValues(20);
        exportDialogButton = new JButton("Show Export dialog...");
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(exportDialogButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JButton exportDialogButton;
}
