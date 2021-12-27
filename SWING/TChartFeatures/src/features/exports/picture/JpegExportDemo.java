/*
 * JpegExportDemo.java
 *
 * <p>Copyright: Copyright (c) 2004-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.exports.picture;

import com.steema.teechart.misc.FileFilter;
import com.steema.teechart.editors.export.ExportPanel;
import com.steema.teechart.legend.LegendTextStyle;
import com.steema.teechart.styles.Arrow;
import com.steema.teechart.styles.PointerStyle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import features.ChartSamplePanel;
import features.utils.FileUtils;
import java.io.IOException;

/**
 *
 * @author tom
 */
public class JpegExportDemo extends ChartSamplePanel implements ActionListener {

    /** Creates a new instance of JpegExportDemo */
    public JpegExportDemo() {
        super();
        exportDialogButton.addActionListener(this);
        saveButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();

        if (source == exportDialogButton) {
            ExportPanel.showModal(chart1.getChart());

        } else if (source == saveButton) {

            int tmpResult = fc.showSaveDialog(JpegExportDemo.this);

            if (tmpResult == JFileChooser.APPROVE_OPTION) {

                File file = fc.getSelectedFile();

                String tmpName = file.getAbsolutePath();
                if (!FileUtils.isExtension(file, FileUtils.JPG)) {
                    tmpName = FileUtils.replaceExtension(file, FileUtils.JPG);
                }

                try {
                    chart1.getExport().getImage().getJPEG().save(tmpName);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    System.err.println(ex.toString());
                }
            }
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getAspect().setChart3DPercent(10);
        chart1.getLegend().setTextStyle(LegendTextStyle.VALUE);
    }

    protected void initComponents() {
        super.initComponents();

        Arrow series = new Arrow(chart1.getChart());
        series.setColorEach(true);
        series.getMarks().setVisible(false);
        series.getPointer().setHorizSize(14);
        series.getPointer().setVertSize(14);
        series.getPointer().setStyle(PointerStyle.RECTANGLE);
        series.getPointer().setInflateMargins(false);
        series.getPointer().setVisible(true);
        series.getXValues().setDateTime(true);
        series.fillSampleValues(8);

        saveButton = new JButton("Save to JPEG...");
        exportDialogButton = new JButton("Show export dialog...");
        fc = new JFileChooser();
        fc.setAcceptAllFileFilterUsed(false);
        fc.addChoosableFileFilter(new FileFilter("JPG files", FileUtils.JPG));
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(saveButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(exportDialogButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JButton saveButton, exportDialogButton;
    private JFileChooser fc;
}
