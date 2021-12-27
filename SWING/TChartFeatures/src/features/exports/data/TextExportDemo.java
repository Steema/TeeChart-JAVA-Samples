/*
 * TextExportDemo.java
 *
 * <p>Copyright: Copyright (c) 2004-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.exports.data;

import com.steema.teechart.misc.FileFilter;
import com.steema.teechart.editors.export.ExportPanel;
import com.steema.teechart.legend.LegendTextStyle;
import com.steema.teechart.styles.Line;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import java.io.IOException;

import features.ChartSamplePanel;
import features.utils.FileUtils;

/**
 *
 * @author tom
 */
public class TextExportDemo extends ChartSamplePanel
    implements ActionListener {

    /** Creates a new instance of TextExportDemo */
    public TextExportDemo() {
        super();
        exportDialogButton.addActionListener(this);
        saveButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if (source == exportDialogButton) {
            ExportPanel.showModal(chart1.getChart());
        } else
        if (source == saveButton) {
            int tmpResult = fc.showSaveDialog(TextExportDemo.this);

            if (tmpResult == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                String tmpName = file.getAbsolutePath();

                if (!FileUtils.isExtension(file, FileUtils.TXT)) {
                    tmpName = FileUtils.replaceExtension(file, FileUtils.TXT);
                }

                try {
                    chart1.getExport().getData().getText().save(tmpName);
                } catch (IOException ex) {
                    ex.printStackTrace();
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

        Line series;
        for (int t=0; t < 3; t++) {
            series = new Line(chart1.getChart());
            series.getMarks().setVisible(false);
            series.getPointer().setVisible(false);
        }
        chart1.getSeries().fillSampleValues(10);

        saveButton = new JButton("Save to Text...");
        exportDialogButton = new JButton("Show export dialog...");
        fc = new JFileChooser();
        fc.setAcceptAllFileFilterUsed(false);
        fc.addChoosableFileFilter(new FileFilter("Text files", FileUtils.TXT));
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
