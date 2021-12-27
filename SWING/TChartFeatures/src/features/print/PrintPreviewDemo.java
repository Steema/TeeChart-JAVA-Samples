/*
 * PrintPreviewDemo.java
 *
 * <p>Copyright: Copyright (c) 2004-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.print;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.editors.PreviewPanelEditor;
import com.steema.teechart.printer.PreviewPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import features.ChartSamplePanel;
import java.awt.print.PageFormat;

/**
 *
 * @author tom
 */
public class PrintPreviewDemo extends ChartSamplePanel
    implements ActionListener {

    private PreviewPanel preview;

    /**
     * Creates a new instance of PrintPreviewDemo
     */
    public PrintPreviewDemo() {
        super();
        editButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == editButton) {
            PreviewPanelEditor.showModal(chart1.getChart(),preview);
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getPrinter().getMargins().setLeft(15);
        chart1.getPrinter().getMargins().setTop(30);
        chart1.getPrinter().getMargins().setRight(15);
        chart1.getPrinter().getMargins().setBottom(30);
    }

    protected void initComponents() {
        super.initComponents();

        preview = new PreviewPanel(chart1.getChart(), chart1.getPrinter());
        preview.setPaperColor(Color.LIGHT_YELLOW);
        preview.getPaperShadow().setColor(Color.BLACK);
        preview.getPaperShadow().setVertSize(4);
        preview.getPaperShadow().setHorizSize(4);
        preview.getPaperShadow().setTransparency(70);
        preview.setOrientation(PageFormat.LANDSCAPE);

        editButton = new JButton("Edit...");
    }

    private JSplitPane tmpSplitPane;

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(editButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
        tmpSplitPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                chart1,
                preview
                
                );
        tmpSplitPane.setDividerLocation(100);
        setSamplePane(tmpSplitPane);
    }

    private JButton editButton;
}
