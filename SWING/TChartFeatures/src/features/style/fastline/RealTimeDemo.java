/*
 * RealTimeDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.fastline;

import com.steema.teechart.styles.FastLine;
import com.steema.teechart.styles.Series;
import com.steema.teechart.styles.ValueListOrder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.NumberFormat;
import java.util.Random;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.Timer;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class RealTimeDemo extends ChartSamplePanel
        implements ActionListener, ItemListener {

    private FastLine lineSeries1, lineSeries2;

    /**
     * Creates a new instance of RealTimeDemo
     */
    public RealTimeDemo() {
        super();
        drawButton.addActionListener(this);
        allPointsButton.addItemListener(this);
        fastPenButton.addItemListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == drawButton) {
            if (stopped) {
                drawButton.setText("Stop");
                totalPointsField.setEnabled(false);
                scrollPointsField.setEnabled(false);

                /* Prepare variables */
                maxPoints = ((Integer)totalPointsField.getValue()).intValue();
                scrollPoints = ((Integer)scrollPointsField.getValue()).intValue();
                chart1.getAxes().getBottom().setMinMax(1,maxPoints);

                /* Clear */
                lineSeries1.clear();
                lineSeries2.clear();


                timer.start();
            } else {
                timer.stop();

                drawButton.setText("Start");
                totalPointsField.setEnabled(true);
                scrollPointsField.setEnabled(true);
            }
            stopped = !stopped;
        }
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == allPointsButton) {
            lineSeries1.setDrawAllPoints(isSelected);
            lineSeries2.setDrawAllPoints(isSelected);
        } else if (source == fastPenButton) {
            // When FastLine series have AutoRepaint = False ,
            // setting FastPen to True increases speed.
            /* TODO
            lineSeries1.setFastPen(isSelected);
            lineSeries2.setFastPen(isSelected);
             */
        }
    }

    protected void initComponents() {
        super.initComponents();

        lineSeries1 = new com.steema.teechart.styles.FastLine(chart1.getChart());
        lineSeries2 = new com.steema.teechart.styles.FastLine(chart1.getChart());

        /* Prepare chart for maximum speed: */
        chart1.setClipPoints(false);
        chart1.getHeader().setVisible(false);
        chart1.getLegend().setVisible(false);
        chart1.getAxes().getLeft().getAxisPen().setWidth(1);
        chart1.getAxes().getBottom().getAxisPen().setWidth(1);
        //chart1.getAxes().getBottom().setRoundFirstLabel(false);
        chart1.getAspect().setView3D(false);
        chart1.getAspect().setSmoothingMode(false);
        chart1.getAspect().setTextSmooth(false);

        stopped=true;
        maxPoints=10000;
        scrollPoints=5000;

        /* Prepare series.
         * Disable AutoRepaint and X Order

         * AutoRepaint=False means "real-time" drawing mode.
         * Points are displayed just after adding them,
         * without redrawing the whole chart.
         */
        lineSeries1.setAutoRepaint(false);
        lineSeries2.setAutoRepaint(false);

        /* Set Ordering to none, to increment speed when adding points */
        lineSeries1.getXValues().setOrder(ValueListOrder.NONE);
        lineSeries2.getXValues().setOrder(ValueListOrder.NONE);

        /* Initialize axis scales */
        chart1.getAxes().getLeft().setMinMax(0, 10000);
        chart1.getAxes().getBottom().setMinMax(0, maxPoints);

        /* Speed tips: */

        /* When using only a single thread, disable locking: */
        //TODO Chart1.Canvas.ReferenceCanvas.Pen.OwnerCriticalSection:=nil;
        //Series1.LinePen.OwnerCriticalSection:=nil;
        //Series2.LinePen.OwnerCriticalSection:=nil;

        /* For Windows NT, 2000 and XP only:
         * Speed realtime painting with solid pens of width 1. */
        //Series1.FastPen:=True;
        //Series2.FastPen:=True;

        /* Set axis calculations in "fast mode".
         * Note: For Windows Me and 98 might produce bad drawings when
         *       chart zoom is very big.
         */
        //Chart1.Axes.FastCalc:=True;

        drawButton = new JButton("Start");
        allPointsButton = new JCheckBox("Draw All Points");
        allPointsButton.setSelected(true);
        fastPenButton = new JCheckBox("Fast Pen");
        fastPenButton.setSelected(true);

        totalPointsField = new JFormattedTextField(NumberFormat.getIntegerInstance());
        totalPointsField.setValue(new Integer(maxPoints));
        totalPointsField.setColumns(10);

        scrollPointsField = new JFormattedTextField(NumberFormat.getIntegerInstance());
        scrollPointsField.setValue(new Integer(scrollPoints));
        scrollPointsField.setColumns(10);

        /* Create a timer. */
        timer = new Timer(ONE_MILLISECOND, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                /* stop timer */
                timer.stop();

                /* Add one more point */
                addRealTime(lineSeries1);
                addRealTime(lineSeries2);

                /* Delete and Scroll points to the left */
                if (lineSeries1.getCount() >  maxPoints-1) {
                    doScrollPoints();
                }

                chart1.repaint();
                /* re-enable timer again */
                timer.start();
            }
        });
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(drawButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(allPointsButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(totalPointsField);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(scrollPointsField);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(fastPenButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    /**
     * Adds a new random point to Series
     */
    private void addRealTime(Series series) {
        if (series.getCount()==0) {                      // First random point
            series.add(1,generator.nextInt(10000));
        }  else {                                        // Next random point
            series.add(
                    series.getXValues().getLast()+1,
                    series.getYValues().getLast()+generator.nextInt(10)-4.5
                    );
        }
    }

    /**
     * When the chart is filled with points, this procedure
     * deletes and scrolls points to the left.
     */
    private void doScrollPoints() {
        /* Delete multiple points with a single call.
         * Much faster than deleting points using a loop.
         */

        lineSeries1.delete(0, scrollPoints);
        lineSeries2.delete(0, scrollPoints);

        /* Scroll horizontal bottom axis */
        double tmp = lineSeries1.getXValues().getLast();
        chart1.getAxes().getBottom().setMinMax(
                tmp-maxPoints+scrollPoints,
                tmp+scrollPoints
                );

        /* Scroll vertical left axis */
        double tmpMin = Math.min(lineSeries1.getYValues().getMinimum(), lineSeries2.getYValues().getMinimum());
        double tmpMax = Math.max(lineSeries1.getYValues().getMaximum(), lineSeries2.getYValues().getMaximum());

        chart1.getAxes().getLeft().setMinMax(tmpMin-tmpMin/5,tmpMax+tmpMax/5);

        /* Do chart repaint after deleting and scrolling */
        chart1.repaint();
    }

    private Timer timer;
    private Random generator = new Random();
    private boolean stopped;        // are we in "loop" mode ?
    private int maxPoints;          // total points per Series
    private int scrollPoints;       // number of points to scroll when filled

    private JButton drawButton;
    private JCheckBox allPointsButton, fastPenButton;
    private JFormattedTextField totalPointsField, scrollPointsField;

    private final static int ONE_MILLISECOND = 1;
}
