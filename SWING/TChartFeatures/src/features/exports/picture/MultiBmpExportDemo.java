/*
 * MultiBmpExportDemo.java
 *
 * <p>Copyright: Copyright (c) 2004-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.exports.picture;

import com.steema.teechart.Rectangle;
import com.steema.teechart.TChart;
import com.steema.teechart.Wall;
import com.steema.teechart.axis.Axis;
import com.steema.teechart.drawing.ChartFont;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.Gradient;
import com.steema.teechart.drawing.GradientDirection;
import com.steema.teechart.misc.FileFilter;
import com.steema.teechart.legend.Legend;
import com.steema.teechart.legend.LegendAlignment;
import com.steema.teechart.legend.LegendStyle;
import com.steema.teechart.styles.Area;
import com.steema.teechart.styles.Bar;
import com.steema.teechart.styles.Line;
import com.steema.teechart.styles.MarksStyle;
import com.steema.teechart.styles.MultiBars;
import com.steema.teechart.styles.PointerStyle;
import com.steema.teechart.styles.Points;
import com.steema.teechart.styles.VerticalAxis;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import features.ChartSamplePanel;
import features.utils.EnumStrings;
import features.utils.FileUtils;

/**
 *
 * @author tom
 */
public class MultiBmpExportDemo extends ChartSamplePanel
        implements ActionListener {

    private TChart chart1, chart2, chart3, chart4;

    /**
     * Creates a new instance of MultiBmpExportDemo
     */
    public MultiBmpExportDemo() {
        super();
        saveButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
         if (source == saveButton) {
            /* This code creates and stores a new BITMAP file
               which contains the FOUR charts.
               It asks previously the end user for the BMP filename
               where to save the bitmap.
             */
            int tmpResult = fc.showSaveDialog(MultiBmpExportDemo.this);
            if (tmpResult == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                String tmpName = file.getAbsolutePath();
                if (!FileUtils.isExtension(file, FileUtils.BMP)) {
                    tmpName = FileUtils.replaceExtension(file, FileUtils.BMP);
                    file = new File(tmpName);
                }
                int tmpW = chart1.getWidth();
                int tmpH = chart1.getHeight();
                BufferedImage bi = new BufferedImage(tmpW*2, tmpH*2, BufferedImage.TYPE_INT_RGB);
                Graphics g = bi.getGraphics();
                chart1.setDoubleBuffered(false);
                chart1.paint(g, new Rectangle(0,0,tmpW,tmpH));
                chart1.setDoubleBuffered(true);
                chart2.setDoubleBuffered(false);
                chart2.paint(g, new Rectangle(0,tmpH+1,tmpW,tmpH));
                chart2.setDoubleBuffered(true);
                chart4.setDoubleBuffered(false);
                chart4.paint(g, new Rectangle(tmpW+1,0,tmpW,tmpH));
                chart4.setDoubleBuffered(true);
                chart3.setDoubleBuffered(false);
                chart3.paint(g, new Rectangle(tmpW+1,tmpH+1,tmpW,tmpH));
                chart3.setDoubleBuffered(true);
                try {
                    ImageIO.write(bi, "BMP", file);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    protected void initChart() {
        Wall tmpWall;
        Legend tmpLegend;
        ChartFont tmpFont;
        Axis tmpAxis;
        Gradient tmpGradient;

        chart1 = new TChart();
        chart1.getFooter().setVisible(false);
        chart1.getHeader().setText("Line");
        chart1.getAspect().setOrthogonal(false);
        chart1.getAspect().setChart3DPercent(60);
        chart1.getAspect().setPerspective(35);
        chart1.getAspect().setZoom(88);
        chart1.getPanel().setColor(Color.GREEN_YELLOW);
        chart1.getAxes().getRight().setVisible(false);
        chart1.getAxes().getTop().setVisible(false);
        tmpWall = chart1.getWalls().getBack();
        {
            tmpWall.getBrush().setColor(Color.WHITE);
            tmpWall.getBrush().setVisible(false);
        }
        tmpWall = chart1.getWalls().getBottom();
        {
            tmpWall.setColor(Color.AQUA);
            tmpWall.setSize(6);
        }
        tmpWall = chart1.getWalls().getLeft();
        {
            tmpWall.setColor(Color.AQUA);
            tmpWall.setSize(5);
        }
        tmpLegend = chart1.getLegend();
        {
            tmpLegend.setAlignment(LegendAlignment.BOTTOM);
            tmpLegend.setColor(Color.BLACK);
            tmpLegend.setColorWidth(16);
            tmpLegend.getShadow().setColor(Color.GRAY);
            tmpLegend.getSymbol().setWidth(16);
            tmpFont = tmpLegend.getFont();
            {
                tmpFont.setColor(Color.WHITE);
                tmpFont.setItalic(true);
                tmpFont.setSize(12);
            }
        }
        tmpFont = chart1.getHeader().getFont();
        {
            tmpFont.setColor(Color.GREEN);
            tmpFont.setSize(16);
            tmpFont.setBold(true);
            tmpFont.setItalic(true);
        }
        tmpAxis = chart1.getAxes().getBottom();
        {
            tmpAxis.getAxisPen().setWidth(1);
            tmpFont = tmpAxis.getLabels().getFont();
            {
                tmpFont.setColor(Color.BLUE);
                tmpFont.setSize(12);
            }
            tmpFont = tmpAxis.getTitle().getFont();
            {
                tmpFont.setColor(Color.GREEN);
                tmpFont.setSize(16);
                tmpFont.setItalic(true);
            }
        }
        tmpAxis = chart1.getAxes().getLeft();
        {
            tmpAxis.getAxisPen().setWidth(1);
            tmpFont = tmpAxis.getLabels().getFont();
            {
                tmpFont.setColor(Color.PURPLE);
                tmpFont.setSize(13);
            }
            tmpFont = tmpAxis.getTitle().getFont();
            {
                tmpFont.setColor(Color.NAVY);
                tmpFont.setSize(15);
                tmpFont.setBold(true);
            }
        }

        chart2 = new TChart();
        chart2.getFooter().setVisible(false);
        chart2.getHeader().setText("Bar");
        chart2.getLegend().setVisible(false);
        chart2.getAspect().setOrthogonal(false);
        chart2.getAspect().setChart3DPercent(80);
        chart2.getAspect().setElevation(347);
        chart2.getAspect().setRotation(352);
        chart2.getAspect().setZoom(97);
        chart2.getWalls().getBottom().setSize(3);
        chart2.getWalls().getLeft().setSize(3);
        tmpWall = chart2.getWalls().getBack();
        {
            tmpWall.getBrush().setColor(Color.WHITE);
            tmpWall.setColor(Color.YELLOW);
            tmpWall.setSize(4);
        }
        tmpFont = chart2.getHeader().getFont();
        {
            tmpFont.setSize(16);
            tmpFont.setBold(true);
            tmpFont.setItalic(true);
        }
        tmpAxis = chart2.getAxes().getBottom();
        {
            tmpAxis.getAxisPen().setWidth(1);
            tmpAxis.getLabels().setDateTimeFormat("MM/yy");
            tmpAxis.getLabels().setSeparation(20);
            tmpFont = tmpAxis.getLabels().getFont();
            {
                tmpFont.setColor(Color.BLUE);
                tmpFont.setSize(16);
                tmpFont.setItalic(true);
            }
        }
        tmpAxis = chart2.getAxes().getLeft();
        {
            tmpAxis.getAxisPen().setWidth(1);
            tmpFont = tmpAxis.getLabels().getFont();
            {
                tmpFont.setColor(Color.BLUE);
                tmpFont.setSize(13);
                tmpFont.setBold(true);
            }
            tmpAxis.getTitle().setText("Sales");
            tmpFont = tmpAxis.getTitle().getFont();
            {
                tmpFont.setColor(Color.GRAY);
                tmpFont.setSize(15);
                tmpFont.setBold(true);
            }
        }
        tmpAxis = chart2.getAxes().getTop();
        {
            tmpAxis.getLabels().setAngle(90);
            tmpAxis.getLabels().getFont().setColor(Color.RED);
        }

        chart3 = new TChart();
        chart3.getFooter().setVisible(false);
        chart3.getHeader().setText("Area");
        chart3.getPanel().setColor(Color.WHITE);
        chart3.getAspect().setOrthogonal(false);
        chart3.getAspect().setChart3DPercent(80);
        chart3.getAspect().setElevation(349);
        chart3.getAspect().setZoom(93);
        chart3.getWalls().getBottom().setSize(4);
        chart3.getWalls().getBack().setSize(4);
        chart3.getWalls().getBack().setColor(Color.WHITE);
        chart3.getWalls().getLeft().setSize(4);
        chart3.getWalls().getLeft().setColor(Color.SILVER);
        tmpLegend = chart3.getLegend();
        {
            tmpLegend.setAlignment(LegendAlignment.BOTTOM);
            tmpLegend.setColor(Color.GRAY);
            tmpLegend.setColorWidth(35);
            tmpLegend.getShadow().setSize(4);
            tmpLegend.getSymbol().setWidth(35);
            tmpLegend.setTopLeftPos(20);
            tmpFont = tmpLegend.getFont();
            {
                tmpFont.setColor(Color.WHITE);
                tmpFont.setItalic(true);
                tmpFont.setSize(12);
            }
        }
        tmpFont = chart3.getHeader().getFont();
        {
            tmpFont.setColor(Color.RED);
            tmpFont.setItalic(true);
            tmpFont.setBold(true);
            tmpFont.setSize(16);
        }
        tmpAxis = chart3.getAxes().getBottom();
        {
            tmpAxis.getLabels().setDateTimeFormat("d/MM");
            tmpAxis.getLabels().setAngle(90);
            tmpFont = tmpAxis.getLabels().getFont();
            {
                tmpFont.setColor(Color.TEAL);
                tmpFont.setSize(12);
            }
            tmpFont = tmpAxis.getTitle().getFont();
            {
                tmpFont.setColor(Color.GREEN);
                tmpFont.setSize(16);
                tmpFont.setItalic(true);
            }
        }
        tmpAxis = chart3.getAxes().getLeft();
        {
            tmpAxis.getTitle().setText("Green Area");
            tmpFont = tmpAxis.getTitle().getFont();
            {
                tmpFont.setColor(Color.GREEN);
                tmpFont.setSize(15);
                tmpFont.setItalic(true);
                tmpFont.setBold(true);
            }
        }
        tmpAxis = chart3.getAxes().getRight();
        {
            tmpAxis.getTitle().setText("Red Area");
            tmpAxis.getGrid().setVisible(false);
            tmpFont = tmpAxis.getLabels().getFont();
            {
                tmpFont.setColor(Color.BLUE);
                tmpFont.setSize(13);
                tmpFont.setItalic(true);
            }
            tmpFont = tmpAxis.getTitle().getFont();
            {
                tmpFont.setColor(Color.RED);
                tmpFont.setSize(15);
                tmpFont.setItalic(true);
                tmpFont.setBold(true);
            }
        }

        chart4 = new TChart();
        chart4.getFooter().setText("Last values");
        chart4.getFooter().getFont().setColor(Color.YELLOW);
        chart4.getHeader().setText("Point");
        chart4.getAspect().setOrthogonal(false);
        chart4.getAspect().setChart3DPercent(50);
        chart4.getAspect().setElevation(357);
        chart4.getAspect().setPerspective(35);
        chart4.getAspect().setRotation(327);
        chart4.getPanel().setColor(Color.LIGHT_YELLOW);
        chart4.getAxes().getLeft().setVisible(false);
        chart4.getAxes().getRight().setVisible(false);
        chart4.getAxes().getTop().setVisible(false);
        tmpWall = chart4.getWalls().getBack();
        {
            tmpWall.getBrush().setColor(Color.WHITE);
            tmpWall.setColor(Color.YELLOW);
            tmpWall.getPen().setColor(Color.BLUE);
            tmpWall.setSize(3);
        }
        tmpWall = chart4.getWalls().getLeft();
        {
            tmpWall.getPen().setColor(Color.BLUE);
            tmpWall.setSize(3);
        }
        tmpLegend = chart4.getLegend();
        {
            tmpLegend.setAlignment(LegendAlignment.BOTTOM);
            tmpLegend.setColorWidth(50);
            tmpLegend.getFont().setColor(Color.PURPLE);
            tmpLegend.setLegendStyle(LegendStyle.LASTVALUES);
            tmpLegend.getSymbol().setWidth(50);
            tmpLegend.setTopLeftPos(2);
            tmpLegend.setVertMargin(10);
        }
        tmpFont = chart4.getHeader().getFont();
        {
            tmpFont.setColor(Color.PURPLE);
            tmpFont.setSize(16);
            tmpFont.setBold(true);
            tmpFont.setItalic(true);
        }
        tmpAxis = chart4.getAxes().getBottom();
        {
            tmpAxis.setMinorTickCount(4);
            tmpAxis.getTicks().setLength(3);
            tmpAxis.getTicks().setColor(Color.WHITE);
            tmpAxis.getAxisPen().setColor(Color.BLUE);
            tmpAxis.getAxisPen().setWidth(1);
            tmpAxis.getGrid().setColor(Color.BLUE);
            tmpAxis.getLabels().setDateTimeFormat("d/MM/yy");
            tmpAxis.getLabels().setSeparation(0);
            tmpFont = tmpAxis.getLabels().getFont();
            {
                tmpFont.setColor(Color.GREEN);
                tmpFont.setSize(12);
            }
            tmpFont = tmpAxis.getTitle().getFont();
            {
                tmpFont.setColor(Color.GREEN);
                tmpFont.setSize(16);
                tmpFont.setItalic(true);
            }
        }
    }

    protected void initSeries() {
        Line tmpLine;
        tmpLine = new Line(chart1.getChart());
        {
            tmpLine.getMarks().setVisible(false);
            tmpLine.getPointer().setVisible(false);
            tmpLine.getXValues().setDateTime(true);
            tmpLine.setTitle("Winter");
            tmpLine.fillSampleValues(20);
        }
        tmpLine = new Line(chart1.getChart());
        {
            tmpLine.getMarks().setVisible(false);
            tmpLine.getPointer().setVisible(false);
            tmpLine.getXValues().setDateTime(true);
            tmpLine.setTitle("Summer");
            tmpLine.fillSampleValues(20);
        }

        Random generator = new Random();
        Bar tmpBar;
        tmpBar = new Bar(chart2.getChart());
        {
            tmpBar.getPen().setVisible(false);
            tmpBar.getMarks().setArrowLength(20);
            tmpBar.getMarks().setColor(Color.AQUA);
            tmpBar.getMarks().getFont().setSize(8);
            tmpBar.getMarks().getFont().setColor(Color.BLACK);
            tmpBar.getMarks().getFont().setName("MS Serif");
            tmpBar.getMarks().setStyle(MarksStyle.PERCENT);
            tmpBar.getMarks().setVisible(true);
            tmpBar.getGradient().setDirection(GradientDirection.VERTICAL);
            tmpBar.setMultiBar(MultiBars.NONE);
            tmpBar.setColorEach(true);

            for(int t=0; t<12; t++) {
                tmpBar.add(generator.nextInt(1000), EnumStrings.MONTH_SHORTNAMES[t]);
            }
        }

        Area tmpArea;
        tmpArea = new Area(chart3.getChart());
        {
            tmpArea.getMarks().setVisible(false);
            tmpArea.getPointer().setVisible(false);
            tmpArea.getXValues().setDateTime(true);
            tmpArea.setTitle("South");
            tmpArea.fillSampleValues(20);
        }
        tmpArea = new Area(chart3.getChart());
        {
            tmpArea.getMarks().setVisible(false);
            tmpArea.getPointer().setVisible(false);
            tmpArea.setVerticalAxis(VerticalAxis.RIGHT);
            tmpArea.setTitle("North");
            tmpArea.getXValues().setDateTime(true);
            tmpArea.fillSampleValues(20);
        }

        Points tmpPoint;
        tmpPoint = new Points(chart4.getChart());
        {
            tmpPoint.setTitle("Speaking");
            tmpPoint.getMarks().setVisible(false);
            tmpPoint.setVerticalAxis(VerticalAxis.RIGHT);
            tmpPoint.getPointer().setInflateMargins(true);
            tmpPoint.getPointer().setStyle(PointerStyle.RECTANGLE);
            tmpPoint.getXValues().setDateTime(true);
            tmpPoint.fillSampleValues(20);
        }
        tmpPoint = new Points(chart4.getChart());
        {
            tmpPoint.setTitle("Writing");
            tmpPoint.getMarks().setVisible(false);
            tmpPoint.getPointer().setInflateMargins(true);
            tmpPoint.getPointer().setStyle(PointerStyle.CIRCLE);
            tmpPoint.getXValues().setDateTime(true);
            tmpPoint.fillSampleValues(20);
        }
        tmpPoint = new Points(chart4.getChart());
        {
            tmpPoint.setTitle("Reading");
            tmpPoint.getMarks().setVisible(false);
            tmpPoint.getPointer().setInflateMargins(true);
            tmpPoint.getPointer().setHorizSize(5);
            tmpPoint.getPointer().setVertSize(5);
            tmpPoint.getPointer().setStyle(PointerStyle.TRIANGLE);
            tmpPoint.getXValues().setDateTime(true);
            tmpPoint.fillSampleValues(20);
        }
    }

    protected void initComponents() {
        super.initComponents();
        initSeries();
        saveButton = new JButton("Save the four charts to a BITMAP...");
        fc = new JFileChooser();
        fc.setAcceptAllFileFilterUsed(false);
        fc.addChoosableFileFilter(new FileFilter("BMP files", FileUtils.BMP));
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(saveButton);
            tmpPane.add(Box.createHorizontalGlue());
        }

        JPanel tmpCharts = new JPanel();
        {
            tmpCharts.setLayout(new GridLayout(2,2));
            tmpCharts.add(chart1);
            tmpCharts.add(chart4);
            tmpCharts.add(chart2);
            tmpCharts.add(chart3);
        }
        setSamplePane(tmpCharts);
    }

    private JButton saveButton;
    private JFileChooser fc;
}
