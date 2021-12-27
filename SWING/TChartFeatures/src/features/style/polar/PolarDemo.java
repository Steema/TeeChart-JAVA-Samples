/*
 * PolarDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.polar;

import com.steema.teechart.axis.Axis;
import com.steema.teechart.Cursor;
import com.steema.teechart.Header;
import com.steema.teechart.events.ChartPaintAdapter;
import com.steema.teechart.events.ChartDrawEvent;
import com.steema.teechart.legend.Legend;
import com.steema.teechart.legend.LegendAlignment;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.DashStyle;
import com.steema.teechart.drawing.HatchStyle;
import com.steema.teechart.drawing.StringAlignment;
import com.steema.teechart.events.SeriesMouseAdapter;
import com.steema.teechart.events.SeriesMouseEvent;
import com.steema.teechart.styles.PointerStyle;
import com.steema.teechart.styles.Polar;
import com.steema.teechart.styles.SeriesPointer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.Timer;
import features.SamplePanel;
import java.text.NumberFormat;
import javax.swing.JOptionPane;

/**
 *
 * @author tom
 */
public class PolarDemo extends SamplePanel
        implements ItemListener {

    private Polar polarSeries1, polarSeries2;

    /** Creates a new instance of PolarDemo */
    public PolarDemo() {
        super();
        animateButton.addItemListener(this);
        circledButton.addItemListener(this);
        transparentButton.addItemListener(this);
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == animateButton) {
            if (isSelected) {
                timer.start();
            } else {
                timer.stop();
            }
        } else if ( source == circledButton) {
            polarSeries1.setCircled(isSelected);
        } else if ( source == transparentButton) {
            if (isSelected) {
                polarSeries1.getBrush().setVisible(false);
                polarSeries2.getBrush().setVisible(false);
            } else {
                polarSeries1.getBrush().setStyle(HatchStyle.DIAGONALCROSS);
                polarSeries2.getBrush().setSolid(true);
                polarSeries1.getBrush().setVisible(true);
                polarSeries2.getBrush().setVisible(true);
            }
            polarSeries1.invalidate();
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getLegend().setVisible(false);
        Header tmpHeader = chart1.getHeader();
        tmpHeader.setVisible(true);
        tmpHeader.setAlignment(StringAlignment.NEAR);
        tmpHeader.setColor(Color.NAVY);
        tmpHeader.getBrush().setColor(Color.NAVY);
        tmpHeader.getFont().setSize(19);
        tmpHeader.getFont().setBold(true);
        tmpHeader.getFont().setItalic(true);
        tmpHeader.getFont().setColor(Color.NAVY);

        chart1.setText("Wind Direction and Speed");
        chart1.getAspect().setView3D(false);
        chart1.getZoom().setAllow(false);
        chart1.getFrame().setVisible(false);
        chart1.getAxes().setVisible(false);

        chart1.getPanel().getGradient().setVisible(false);
        chart1.getPanel().setColor(Color.WHITE);

        Legend tmpLegend = chart1.getLegend();
        tmpLegend.setVisible(true);
        tmpLegend.setAlignment(LegendAlignment.BOTTOM);
        tmpLegend.setColor(Color.YELLOW);
        tmpLegend.setInverted(true);
        tmpLegend.getShadow().setSize(4);
        tmpLegend.getSymbol().setWidth(37);

        //Events
        chart1.addChartPaintListener( new ChartPaintAdapter() {
            public void chartPainted( ChartDrawEvent pce ) {
                chart1.getGraphics3D().getBrush().setVisible(false);           //<-- IMPORTANT (Try without) !!!
                chart1.getGraphics3D().getPen().setVisible(true);
                chart1.getGraphics3D().getPen().setWidth(2);
                //Draw a blue ring passing over the 3rd PolarSeries1 Point
                chart1.getGraphics3D().getPen().setColor(Color.BLUE);
                polarSeries1.drawRing(polarSeries1.getYValues().getValue(2), polarSeries1.getEndZ());
                //Draw a green ring passing over the 6th PolarSeries2 Point
                chart1.getGraphics3D().getPen().setColor(Color.GREEN);
                polarSeries2.drawRing(polarSeries2.getYValues().getValue(5), polarSeries2.getEndZ());
            }
        });
    }

    protected void initSeries() {
        SeriesPointer tmpPointer;
        polarSeries1 = new com.steema.teechart.styles.Polar(chart1.getChart());
        polarSeries1.fillSampleValues(20);
        polarSeries1.setTitle("California");
        polarSeries1.setCircled(true);
        polarSeries1.setCursor(Cursor.HAND);
        polarSeries1.setAngleIncrement(30);
        polarSeries1.getBrush().setColor(Color.BLUE);
        polarSeries1.getBrush().setStyle(HatchStyle.DIAGONALCROSS);
        polarSeries1.getBrush().setVisible(true);
        polarSeries1.setCircleBackColor(Color.LIGHT_YELLOW);
        polarSeries1.setCircleLabels(true);
        polarSeries1.getCircleLabelsFont().setColor(Color.PURPLE);
        polarSeries1.getCircleLabelsFont().setBold(true);
        polarSeries1.getCircleLabelsFont().setItalic(true);
        polarSeries1.setCircleLabelsRotated(true);
        polarSeries1.getCirclePen().setColor(Color.BLUE);
        polarSeries1.getCirclePen().setStyle(DashStyle.DOT);
        polarSeries1.getCirclePen().setWidth(2);
        polarSeries1.setClockWiseLabels(true);

        tmpPointer = polarSeries1.getPointer();
        tmpPointer.setHorizSize(6);
        tmpPointer.setInflateMargins(true);
        tmpPointer.getPen().setVisible(false);
        tmpPointer.setStyle(PointerStyle.RECTANGLE);
        tmpPointer.setVertSize(6);
        tmpPointer.setVisible(true);

        polarSeries2 = new com.steema.teechart.styles.Polar(chart1.getChart());
        polarSeries2.fillSampleValues(25);
        polarSeries2.setCursor(Cursor.HAND);
        polarSeries2.setTitle("Catalonia");
        polarSeries2.setCircled(true);
        polarSeries2.setColorEach(true);
        polarSeries2.setAngleIncrement(30);
        polarSeries2.getBrush().setColor(Color.BLUE);
        polarSeries2.getBrush().setVisible(true);
        polarSeries2.getCirclePen().setColor(Color.BLUE);

        tmpPointer = polarSeries2.getPointer();
        tmpPointer.setHorizSize(5);
        tmpPointer.setInflateMargins(true);
        tmpPointer.getPen().setVisible(false);
        tmpPointer.setStyle(PointerStyle.DIAMOND);
        tmpPointer.setVertSize(5);
        tmpPointer.setVisible(true);

        //Events
        class PolarMouseListener extends SeriesMouseAdapter {
            NumberFormat nf = NumberFormat.getInstance();

            public void seriesClicked(SeriesMouseEvent e) {
                Polar series = (Polar)e.getSeries();
                double angle = series.getXValues().getValue(e.getValueIndex());
                double radius = series.getYValues().getValue(e.getValueIndex());
                StringBuffer sb = new StringBuffer("You clicked on series: ");
                sb.append(series.toString()).append("\n");
                sb.append("Angle: ").append(nf.format(angle)).append("\n");
                sb.append("Radius: ").append(nf.format(radius));
                JOptionPane.showMessageDialog(null, sb.toString() );
            };
        }
        polarSeries1.addSeriesMouseListener( new PolarMouseListener() );
        polarSeries2.addSeriesMouseListener( new PolarMouseListener() );
    }

    protected void initComponents() {
        super.initComponents();
        initSeries();

        animateButton = new JCheckBox("Animate!");
        circledButton = new JCheckBox("Circled");
        circledButton.setSelected(polarSeries1.getCircled());
        transparentButton = new JCheckBox("Transparent");

        //Create a timer.
        timer = new Timer(ONE_MILLISECOND, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                /* stop timer */
                timer.stop();

                /* Move Points !!! */
                polarSeries1.rotate(5);
                polarSeries2.rotate(355);

                /*  Change Grid Lines and Horizontal Axis Labels */
                Axis tmpAxis;
                tmpAxis = chart1.getAxes().getBottom();
                if ((tmpAxis.getIncrement() == 0) || (tmpAxis.getIncrement() >= 90)) {
                    tmpAxis.setIncrement(1);
                } else {
                    tmpAxis.setIncrement(tmpAxis.getIncrement() + 2);
                }

                /*  Change Grid RINGS and Vertical Axis Labels */
                tmpAxis = chart1.getAxes().getLeft();
                if ((tmpAxis.getIncrement() == 0) ||
                        (tmpAxis.getIncrement() >= ((tmpAxis.getMaximum()-tmpAxis.getMinimum())/2.0))) {
                    tmpAxis.setIncrement((tmpAxis.getMaximum()-tmpAxis.getMinimum())/20.0);
                } else {
                    tmpAxis.setIncrement(tmpAxis.getIncrement() * 2.0);
                }


                /* re-enable timer again */
                timer.start();
            }
        });
    }

    protected void initGUI() {
        super.initGUI();
        myCommander.setVisible(true);
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(animateButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(circledButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(transparentButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JCheckBox animateButton, circledButton, transparentButton;

    private Timer timer;
    private final static int ONE_MILLISECOND = 1;
}
