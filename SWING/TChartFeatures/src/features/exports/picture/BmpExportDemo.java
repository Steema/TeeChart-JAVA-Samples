/*
 * BmpExportDemo.java
 *
 * <p>Copyright: (c) 2004-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.exports.picture;

import com.steema.teechart.misc.FileFilter;
import com.steema.teechart.editors.export.ExportPanel;
import com.steema.teechart.legend.LegendTextStyle;
import com.steema.teechart.styles.Area;
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
public class BmpExportDemo extends ChartSamplePanel implements ActionListener {

    /** Creates a new instance of BmpExportDemo */
    public BmpExportDemo() {
        super();
        exportDialogButton.addActionListener(this);
        saveButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();

        if (source == exportDialogButton) {
            ExportPanel.showModal(chart1.getChart());
        } else {
            if (source == saveButton) {
                int tmpResult = fc.showSaveDialog(BmpExportDemo.this);

                if (tmpResult == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    String tmpName = file.getAbsolutePath();

                    if (!FileUtils.isExtension(file, FileUtils.BMP)) {
                        tmpName = FileUtils.replaceExtension(file,
                                FileUtils.BMP);
                    }

                    try {
                        chart1.getExport().getImage().getBMP().save(tmpName);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        System.err.println(ex.toString());
                    }
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

        Area series = new Area(chart1.getChart());
        series.setColorEach(true);
        series.getMarks().setVisible(false);
        series.getPointer().setVisible(false);
        series.fillSampleValues(8);

        saveButton = new JButton("Save to BMP...");
        exportDialogButton = new JButton("Show export dialog...");
        fc = new JFileChooser();
        fc.setAcceptAllFileFilterUsed(false);
        fc.addChoosableFileFilter(new FileFilter("BMP files", FileUtils.BMP));
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
