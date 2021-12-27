/*
 * ZonesDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.polar;

import com.steema.teechart.Header;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.DashStyle;
import com.steema.teechart.events.ChartDrawEvent;
import com.steema.teechart.events.SeriesPaintAdapter;
import com.steema.teechart.styles.PointerStyle;
import com.steema.teechart.styles.Polar;
import com.steema.teechart.styles.ValueListOrder;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class ZonesDemo extends ChartSamplePanel
        implements ItemListener {

    private Polar polarSeries;

    /** Creates a new instance of ZonesDemo */
    public ZonesDemo() {
        super();
        drawZonesButton.addItemListener(this);
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == drawZonesButton) {
            chart1.repaint();
        }
    }

    protected void drawPolarZones() {
        if (polarSeries.getCircleXCenter()!=0) {
            // Hide pen
            chart1.getGraphics3D().getPen().setVisible(false);

            chart1.getGraphics3D().getBrush().getGradient().setVisible(false);

            // Draw three zones (green, yellow and red)
            //chart1.getPanel().setColor(Color.EMPTY);

            chart1.getGraphics3D().getBrush().setColor(Color.GREEN);
            polarSeries.drawZone(0, 100, chart1.getAspect().getWidth3D());

            chart1.getGraphics3D().getBrush().setColor(Color.YELLOW);
            polarSeries.drawZone(100, 300, chart1.getAspect().getWidth3D());

            chart1.getGraphics3D().getBrush().setColor(Color.RED);
            polarSeries.drawZone(300, 700, chart1.getAspect().getWidth3D());

            // Prepare Pen

            chart1.getGraphics3D().getPen().setColor(Color.WHITE);
            chart1.getGraphics3D().getPen().setWidth(2);
            chart1.getGraphics3D().getPen().setStyle(DashStyle.SOLID);
            chart1.getGraphics3D().getPen().setVisible(true);

            // Draw "ring" at 300

            polarSeries.drawRing(300, chart1.getAspect().getWidth3D());
        }
    }

    protected void initChart() {
        super.initChart();
        Header tmpHeader = chart1.getHeader();
        tmpHeader.setVisible(true);
        tmpHeader.setText("Polar background Zones.");
        chart1.getAspect().setView3D(false);

        chart1.getLegend().setVisible(false);
    }

    protected void initSeries() {
        polarSeries = new com.steema.teechart.styles.Polar(chart1.getChart());
        polarSeries.getMarks().setVisible(false);
        polarSeries.setAngleIncrement(10.0);
        polarSeries.setColor(Color.BLUE);
        polarSeries.setCircled(true);
        polarSeries.getAngleValues().setName("Angle");
        polarSeries.getAngleValues().setOrder(ValueListOrder.ASCENDING);
        polarSeries.getBrush().setColor(Color.WHITE);
        polarSeries.getBrush().setVisible(false);
        polarSeries.getPen().setColor(Color.BLUE);
        polarSeries.getPen().setWidth(2);
        polarSeries.getPointer().setInflateMargins(true);
        polarSeries.getPointer().setStyle(PointerStyle.CIRCLE);
        polarSeries.getPointer().setVisible(true);

        polarSeries.clear();
        polarSeries.add(30,100);
        polarSeries.add(45,150);
        polarSeries.add(60,-90);
        polarSeries.add(80,300);
        polarSeries.add(130,400);
        polarSeries.add(170,250);
        polarSeries.add(230,100);
        polarSeries.add(260,120);
        polarSeries.add(330,700);

        //events
        polarSeries.addSeriesPaintListener( new SeriesPaintAdapter() {
            public void seriesPainting(ChartDrawEvent e) {
                if (drawZonesButton.isSelected()) {
                    drawPolarZones();
                }
            };
        });
    }

    protected void initComponents() {
        super.initComponents();

        initSeries();

        drawZonesButton = new JCheckBox("Draw Zones");
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(drawZonesButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JCheckBox drawZonesButton;
}
