/*
 * MultiClipboardDemo.java
 *
 * <p>Copyright: Copyright (c) 2004-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.exports.clipboard;

import com.steema.teechart.Rectangle;
import com.steema.teechart.TChart;
import com.steema.teechart.Wall;
import com.steema.teechart.axis.Axis;
import com.steema.teechart.drawing.ChartFont;
import com.steema.teechart.drawing.Gradient;
import com.steema.teechart.legend.Legend;
import com.steema.teechart.styles.Area;
import com.steema.teechart.styles.Bar;
import com.steema.teechart.styles.Line;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import features.ChartSamplePanel;
import features.utils.WaitToolkit;

/**
 *
 * @author tom
 */
public class MultiClipboardDemo extends ChartSamplePanel
        implements ActionListener {

    private TChart chart1, chart2, chart3;

    /**
     * Creates a new instance of MultiClipboardDemo
     */
    public MultiClipboardDemo() {
        super();
        copyButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if (source == copyButton) {
            WaitToolkit.startWait(this.getRootPane());
            // Create a new BITMAP which contains the THREE charts.
            int tmpW = 500;
            int tmpH = 300;
            BufferedImage bi = new BufferedImage(tmpW, tmpH, BufferedImage.TYPE_INT_RGB);
            Graphics g = bi.getGraphics();
            chart1.setDoubleBuffered(false);
            chart1.paint(g, new Rectangle((tmpW/2)+1,0,tmpW/2-1,125));
            chart1.setDoubleBuffered(true);
            chart2.setDoubleBuffered(false);
            chart2.paint(g, new Rectangle(0,0,tmpW/2,125));
            chart2.setDoubleBuffered(true);
            chart3.setDoubleBuffered(false);
            chart3.paint(g, new Rectangle(0,126,tmpW-1,tmpH/2-1));
            chart3.setDoubleBuffered(true);
            // Copy the image to clipboard...
            ImageSelection imgSel = new ImageSelection(bi);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(imgSel, null);
            WaitToolkit.stopWait(this.getRootPane());
        }
    }

    protected void initChart() {
        Wall tmpWall;
        Legend tmpLegend;
        ChartFont tmpFont;
        Axis tmpAxis;
        Gradient tmpGradient;

        chart1 = new TChart();
        chart1.getHeader().setText("Bars...");
        chart1.getHeader().setVisible(true);

        chart2 = new TChart();
        chart2.getHeader().setText("Lines...");
        chart2.getHeader().setVisible(true);

        chart3 = new TChart();
        chart3.getHeader().setVisible(false);
    }

    protected void initSeries() {
        Bar tmpBar = new Bar(chart1.getChart());
        {
            tmpBar.setColorEach(true);
            tmpBar.getMarks().setArrowLength(20);
            tmpBar.getMarks().setVisible(true);
        }
        Line tmpLine = new Line(chart2.getChart());
        {
            tmpLine.getMarks().setVisible(false);
            tmpLine.getPointer().setVisible(false);
        }
        Area tmpArea = new Area(chart3.getChart());
        {
            tmpArea.getMarks().setVisible(false);
            tmpArea.getPointer().setVisible(false);
        }
        chart1.getSeries().fillSampleValues(6);
        chart2.getSeries().fillSampleValues(6);
        chart3.getSeries().fillSampleValues(26);
    }

    protected void initComponents() {
        super.initComponents();
        initSeries();
        copyButton = new JButton("Copy ALL to ClipBoard !");
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(copyButton);
            tmpPane.add(Box.createHorizontalGlue());
        }

        JPanel tmpCharts = new JPanel();
        {
            tmpCharts.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.weightx = 0.25;
            c.weighty = 0.75;
            c.gridx = 0;
            c.gridy = 0;
            c.fill = GridBagConstraints.BOTH;
            tmpCharts.add(chart2, c);
            c.weightx = 0.75;
            c.weighty = 0.75;
            c.gridx = 1;
            c.gridy = 0;
            c.fill = GridBagConstraints.BOTH;
            tmpCharts.add(chart1, c);
            c.weightx = 1;
            c.weighty = 0.25;
            c.gridwidth = 2;
            c.gridx = 0;
            c.gridy = 1;
            c.fill = GridBagConstraints.BOTH;
            tmpCharts.add(chart3, c);
        }
        setSamplePane(tmpCharts);
    }

    private JButton copyButton;

    public static class ImageSelection implements Transferable {
        private Image image;

        public ImageSelection(Image image) {
            this.image = image;
        }

        public DataFlavor[] getTransferDataFlavors() {
            return new DataFlavor[]{DataFlavor.imageFlavor};
        }

        public boolean isDataFlavorSupported(DataFlavor flavor) {
            return DataFlavor.imageFlavor.equals(flavor);
        }

        public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
            if (!DataFlavor.imageFlavor.equals(flavor)) {
                throw new UnsupportedFlavorException(flavor);
            }
            return image;
        }
    }

}
