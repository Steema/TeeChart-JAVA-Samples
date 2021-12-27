/*
 * ClipboardDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.exports.clipboard;

import com.steema.teechart.TextShapeStyle;
import com.steema.teechart.Wall;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.GradientDirection;
import com.steema.teechart.styles.FastLine;
import com.steema.teechart.styles.Line;
import com.steema.teechart.styles.PointerStyle;
import com.steema.teechart.styles.Points;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import features.ChartSamplePanel;
import features.utils.WaitToolkit;

/**
 *
 * @author tom
 */
public class ClipboardDemo extends ChartSamplePanel
    implements ActionListener {

    /**
     * Creates a new instance of ClipboardDemo
     */
    public ClipboardDemo() {
        super();
        copyButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == copyButton) {
            WaitToolkit.startWait(this.getRootPane());
            chart1.getChart().getExport().getImage().copyToClipboard();
            WaitToolkit.stopWait(this.getRootPane());
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getHeader().setText("This is a chart !");
        chart1.getHeader().setVisible(true);
        Wall tmpWall;
        tmpWall = chart1.getWalls().getBack();
        tmpWall.getGradient().setEndColor(Color.SILVER);
        tmpWall.getGradient().setMiddleColor(Color.GRAY);
        tmpWall.getGradient().setVisible(true);
        tmpWall.setSize(10);
        tmpWall.setTransparent(false);
        chart1.getWalls().getBottom().setSize(10);
        chart1.getWalls().getLeft().setSize(10);
        chart1.getWalls().getRight().setSize(10);
        chart1.getWalls().getRight().setVisible(true);
        chart1.getPanel().getGradient().setMiddleColor(Color.RED);
        chart1.getPanel().getGradient().setStartColor(Color.GOLD);
        chart1.getPanel().getGradient().setVisible(true);
        chart1.getPanel().setMarginRight(5);
        chart1.getLegend().getGradient().setStartColor(Color.LIME);
        chart1.getLegend().getGradient().setDirection(GradientDirection.HORIZONTAL);
        chart1.getLegend().getGradient().setVisible(true);
        chart1.getHeader().getFont().setColor(Color.NAVY);
        chart1.getHeader().getFont().setSize(16);
        chart1.getHeader().getFont().setBold(true);
        chart1.getHeader().getGradient().setDirection(GradientDirection.HORIZONTAL);
        chart1.getHeader().getGradient().setEndColor(Color.GRAY);
        chart1.getHeader().getGradient().setVisible(true);
        chart1.getHeader().setShapeStyle(TextShapeStyle.ROUNDRECTANGLE);
        chart1.getAspect().setElevation(360);
        chart1.getAspect().setOrthogonal(false);
        chart1.getAspect().setPerspective(72);
        chart1.getAspect().setRotation(360);
        chart1.getAspect().setZoom(88);
    }

    protected void initSeries() {
        Points tmpPoints = new Points(chart1.getChart());
        tmpPoints.getMarks().setVisible(false);
        tmpPoints.getPointer().setInflateMargins(true);
        tmpPoints.getPointer().setStyle(PointerStyle.RECTANGLE);
        tmpPoints.setVisible(true);

        Line tmpLine = new Line(chart1.getChart());
        tmpLine.getMarks().setVisible(false);
        tmpLine.getPointer().setVisible(false);

        FastLine tmpFastLine = new FastLine(chart1.getChart());
        tmpFastLine.getMarks().setVisible(false);
        tmpFastLine.getLinePen().setColor(Color.YELLOW);
        chart1.getSeries().fillSampleValues(10);
    }

    protected void initComponents() {
        super.initComponents();
        initSeries();
        copyButton = new JButton("Copy to Clipboard !");
    }

    protected void initGUI() {
        super.initGUI();
        JLabel tmpLabel;
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(copyButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JButton copyButton;
}
