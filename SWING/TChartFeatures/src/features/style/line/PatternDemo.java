/*
 * PatternDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.line;

import com.steema.teechart.misc.FileFilter;
import com.steema.teechart.styles.Line;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

import features.ChartSamplePanel;
import features.utils.FileUtils;

/**
 *
 * @author tom
 */
public class PatternDemo extends ChartSamplePanel
        implements ActionListener {

    /** Creates a new instance of PatternDemo */
    public PatternDemo() {
        super();
        for (int i=0; i<3; i++) {
            patternButton[i].addActionListener(this);
        }
    }

    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();

        if (source instanceof JButton) {

            prepareFileChooser();

            int tmpResult = fc.showSaveDialog(PatternDemo.this);

            if (tmpResult == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                String tmpName = file.getAbsolutePath();

                for (int i=0; i<3; i++) {
                    if (source == patternButton[i]) {
                        ((Line)chart1.getSeries(i)).getBrush().loadImage(tmpName);
                        patternButton[i].setIcon(new ImageIcon(tmpName));
                        break;
                    }
                }
            }
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getLegend().getSymbol().setWidth(30);
        chart1.getLegend().setColorWidth(30);
    }

    protected void initSeries() {
        Line lineSeries;
        for (int i=0; i<3; i++) {
            lineSeries = new com.steema.teechart.styles.Line(chart1.getChart());
            lineSeries.setStairs(false);
            lineSeries.getMarks().setVisible(false);
            lineSeries.getPointer().setVisible(false);
            lineSeries.getBrush().loadImage(ChartSamplePanel.class.getResource(URL_PATTERN[i]));
        }
    }

    protected void initComponents() {
        super.initComponents();
        initSeries();
        chart1.getSeries().fillSampleValues(8);

        patternButton = new JButton[3];
        for (int i=0; i<3; i++) {
            patternButton[i] = new JButton(new ImageIcon(ChartSamplePanel.class.getResource(URL_PATTERN[i])));
            patternButton[i].setMaximumSize(new Dimension(100,100));
        }
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            for (int i=0; i<3; i++) {
                tmpPane.add(patternButton[i]);
                tmpPane.add(Box.createHorizontalStrut(10));
            }
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private final static String[] URL_PATTERN = new String[]{
        "images/pattern1.jpg",
        "images/pattern2.jpg",
        "images/pattern3.jpg"
    };

    private JButton patternButton[];
    private JFileChooser fc;

    private void prepareFileChooser() {
        if (fc==null) {
            fc = new JFileChooser();
            fc.setAcceptAllFileFilterUsed(false);
            fc.addChoosableFileFilter(new FileFilter("GIF files", FileUtils.GIF));
            fc.addChoosableFileFilter(new FileFilter("JPG files", FileUtils.JPG));
            fc.addChoosableFileFilter(new FileFilter("PNG files", FileUtils.PNG));
        }
    }

}
