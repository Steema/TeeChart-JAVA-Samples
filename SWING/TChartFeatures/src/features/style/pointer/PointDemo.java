/*
 * PointDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.pointer;
import com.steema.teechart.Aspect;
import com.steema.teechart.BevelStyle;
import com.steema.teechart.legend.Legend;
import com.steema.teechart.legend.LegendAlignment;
import com.steema.teechart.legend.LegendSymbolSize;
import com.steema.teechart.TextShapeStyle;
import com.steema.teechart.Wall;
import com.steema.teechart.axis.Axis;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.GradientDirection;
import com.steema.teechart.drawing.StringAlignment;
import com.steema.teechart.styles.HorizontalAxis;
import com.steema.teechart.styles.PointerStyle;
import com.steema.teechart.styles.Points;
import com.steema.teechart.styles.Series;
import com.steema.teechart.styles.VerticalAxis;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Random;
import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.Timer;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class PointDemo extends ChartSamplePanel
        implements ItemListener {

    /** Creates a new instance of AreaSeries */
    public PointDemo() {
        super();
        animateButton.addItemListener(this);
        view3DButton.addItemListener(this);
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
        } else if (source == view3DButton) {
            chart1.getAspect().setView3D(isSelected);
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getHeader().setVisible(true);
        chart1.getHeader().setColor(Color.SILVER);
        chart1.getHeader().setAlignment(StringAlignment.NEAR);
        chart1.getHeader().getFont().setColor(Color.TEAL);
        chart1.getHeader().getFont().setSize(16);
        chart1.getHeader().getFont().setBold(true);
        chart1.setText("Point Series");

        chart1.getPanel().setBevelInner(BevelStyle.RAISED);
        chart1.getPanel().getGradient().setDirection(GradientDirection.HORIZONTAL);
        chart1.getPanel().getGradient().setEndColor(Color.WHITE);
        chart1.getPanel().getGradient().setStartColor(Color.GRAY);
        chart1.getPanel().getGradient().setVisible(true);

        Aspect tmpAspect = chart1.getAspect();
        tmpAspect.setElevation(360);
        tmpAspect.setOrthogonal(false);
        tmpAspect.setPerspective(35);
        tmpAspect.setRotation(312);
        tmpAspect.setChart3DPercent(25);

        Legend tmpLegend = chart1.getLegend();
        tmpLegend.setAlignment(LegendAlignment.BOTTOM);
        tmpLegend.setBevelWidth(1);
        tmpLegend.setColor(Color.GRAY);
        tmpLegend.getFont().setColor(Color.LIME);
        tmpLegend.getFont().setSize(12);
        tmpLegend.getPen().setColor(Color.RED);
        tmpLegend.getShadow().setColor(Color.SILVER);
        tmpLegend.getShadow().setSize(4);
        tmpLegend.setShapeStyle(TextShapeStyle.ROUNDRECTANGLE);
        tmpLegend.getSymbol().setWidth(10);
        tmpLegend.getSymbol().setWidthUnits(LegendSymbolSize.PIXELS);
        tmpLegend.setTopLeftPos(0);

        Wall tmpWall;
        tmpWall = chart1.getWalls().getBack();
        tmpWall.getBrush().setColor(Color.WHITE);
        tmpWall.setSize(9);
        tmpWall.setTransparent(false);

        tmpWall = chart1.getWalls().getBottom();
        tmpWall.getBrush().setColor(Color.SILVER);
        tmpWall.setSize(6);
        tmpWall = chart1.getWalls().getLeft();
        tmpWall.getBrush().setColor(Color.SILVER);
        tmpWall.setSize(6);

        Axis tmpAxis;
        tmpAxis = chart1.getAxes().getBottom();
        tmpAxis.getGrid().setColor(Color.SILVER);
        tmpAxis.getGrid().setCentered(true);
        tmpAxis.getMinorTicks().setLength(0);
        tmpAxis.getTicksInner().setLength(7);
        tmpAxis.getTicksInner().setColor(Color.WHITE);

        tmpAxis = chart1.getAxes().getLeft();
        tmpAxis.getLabels().getFont().setColor(Color.OLIVE);
        tmpAxis.getLabels().getFont().setBold(true);
        tmpAxis.getTicksInner().setLength(6);
        tmpAxis.getTicksInner().setColor(Color.LIME);

        tmpAxis = chart1.getAxes().getRight();
        tmpAxis.getLabels().setValueFormat(" #,##0.00");
        tmpAxis.getGrid().setVisible(false);
        tmpAxis.getLabels().getFont().setColor(Color.NAVY);
        tmpAxis.getLabels().getFont().setSize(12);
        tmpAxis.getLabels().getFont().setItalic(true);

        tmpAxis = chart1.getAxes().getTop();
        tmpAxis.getGrid().setVisible(false);
        tmpAxis.getLabels().getFont().setColor(Color.BLUE);
        tmpAxis.getLabels().getFont().setSize(12);
    }

    protected void initSeries() {
        Points pointSeries = null;
        for (int i=0; i < 6; i++) {
            pointSeries = new com.steema.teechart.styles.Points(chart1.getChart());
            pointSeries.fillSampleValues(20);
            switch (i) {
                case 0: {
                    pointSeries.setTitle("Cars");
                    pointSeries.getPointer().setStyle(PointerStyle.RECTANGLE);
                    break;

                }
                case 1: {
                    pointSeries.setTitle("Bikes");
                    pointSeries.getPointer().setStyle(PointerStyle.CIRCLE);
                    break;
                }
                case 2: {
                    pointSeries.setTitle("Trucks");
                    pointSeries.getPointer().setStyle(PointerStyle.TRIANGLE);
                    break;
                }
                case 3: {
                    pointSeries.setTitle("Boats");
                    pointSeries.getPointer().setStyle(PointerStyle.DOWNTRIANGLE);
                    pointSeries.setVerticalAxis(VerticalAxis.RIGHT);
                    pointSeries.setHorizontalAxis(HorizontalAxis.TOP);
                    break;
                }
                case 4: {
                    pointSeries.setTitle("Planes");
                    pointSeries.getPointer().setStyle(PointerStyle.DIAGCROSS);
                    break;
                }
                case 5: {
                    pointSeries.setTitle("Rockets");
                    pointSeries.getPointer().setStyle(PointerStyle.DIAMOND);
                    pointSeries.setVerticalAxis(VerticalAxis.RIGHT);
                    break;
                }
            }
        }
    }

    protected void initComponents() {
        super.initComponents();
        initSeries();

        animateButton = new JCheckBox("Animate");
        animateButton.setSelected(false);
        view3DButton = new JCheckBox("3D");
        view3DButton.setSelected(true);

        //Create a timer.
        timer = new Timer(ONE_MILLISECOND, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                /* stop timer */
                timer.stop();

                /* add a new point to each series */
                double tmpX;

                Series tmpSeries;
                Random generator = new Random();
                for (int t=0; t < chart1.getSeriesCount(); t++) {
                    tmpSeries = chart1.getSeries(t);
                    tmpX = tmpSeries.getXValues().getValue(1)
                            - tmpSeries.getXValues().getValue(0);

                    tmpSeries.delete(0);

                    tmpSeries.add(
                        tmpSeries.getXValues().getLast()+tmpX,
                        tmpSeries.getYValues().getLast()+generator.nextInt(100)-50);
                }

                /* re-enable timer again */
                timer.start();
            }
        });
    }

    protected void initGUI() {
        super.initGUI();

        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(view3DButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(animateButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JCheckBox animateButton;
    private JCheckBox view3DButton;
    private Timer timer;

    private final static int ONE_MILLISECOND = 1;
}
