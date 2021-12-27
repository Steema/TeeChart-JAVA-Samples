/*
 * XmlExportDemo.java
 *
 * <p>Copyright: Copyright (c) 2006-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.exports.data;

import com.steema.teechart.exports.Exports;
import com.steema.teechart.styles.Points;
import com.steema.teechart.misc.FileFilter;
import com.steema.teechart.editors.export.ExportPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

import features.ChartSamplePanel;
import features.utils.BrowserControl;
import features.utils.FileUtils;

/**
 *
 * @author tom
 */
public class XmlExportDemo extends ChartSamplePanel
    implements ActionListener {

    private Exports exporter;

    /** Creates a new instance of XmlExportDemo */
    public XmlExportDemo() {
        super();
        saveButton.addActionListener(this);
        exportDialogButton.addActionListener(this);
    }

   public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if (source == exportDialogButton) {
            ExportPanel.showModal(chart1.getChart());
        } else if (source == saveButton) {
            int tmpResult = fc.showSaveDialog(XmlExportDemo.this);
            if (tmpResult == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                String tmpName = file.getAbsolutePath();
                if (!FileUtils.isExtension(file, FileUtils.XML)) {
                    tmpName = FileUtils.replaceExtension(file, FileUtils.XML);
                }
                try {
                    chart1.getExport().getTemplate().toXML(tmpName);
                    showSavedFile(tmpName);
                } catch (FileNotFoundException e) {
                    System.out.println(e);
                }
            }
        }
    }

   protected void showSavedFile(String fileName) {
        BrowserControl.displayURL("file://"+fileName);
   }

    protected void initChart() {
        super.initChart();
        chart1.getAspect().setChart3DPercent(15);
    }

    protected void initComponents() {
        super.initComponents();

        for (int t=0; t<4; t++) {
            new Points(chart1.getChart());
        }
        chart1.getSeries().fillSampleValues(6);

        exporter = new Exports(chart1.getChart());

        exportDialogButton = new JButton("Show Export dialog...");
        saveButton = new JButton("Save to XML...");
        pointIndexButton = new JCheckBox("Point Index"); //TODO
        pointIndexButton.setSelected(true);
        fc = new JFileChooser();
        fc.setAcceptAllFileFilterUsed(false);
        fc.addChoosableFileFilter(new FileFilter("XML files", FileUtils.XML));
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(saveButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(pointIndexButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(exportDialogButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JButton exportDialogButton, saveButton;
    private JCheckBox pointIndexButton;
    private JFileChooser fc;
}
