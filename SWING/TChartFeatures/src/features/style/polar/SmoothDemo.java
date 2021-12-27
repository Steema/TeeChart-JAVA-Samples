/*
 * SmoothDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.polar;

import com.steema.teechart.Header;
import com.steema.teechart.ImageMode;
import com.steema.teechart.legend.Legend;
import com.steema.teechart.legend.LegendAlignment;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.functions.Smoothing;
import com.steema.teechart.legend.LegendStyle;
import com.steema.teechart.legend.LegendSymbolSize;
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
public class SmoothDemo extends ChartSamplePanel
        implements ItemListener {

    private Polar polarSeries, smoothSeries;
    private Smoothing smoothingFunction;

    /** Creates a new instance of SmoothDemo */
    public SmoothDemo() {
        super();
        originalButton.addItemListener(this);
        smoothButton.addItemListener(this);
        interpolateButton.addItemListener(this);
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == originalButton) {
            polarSeries.setVisible(isSelected);
        } else if ( source == smoothButton) {
            smoothSeries.setVisible(isSelected);
        } else if ( source == interpolateButton) {
            smoothingFunction.setInterpolate(isSelected);
            //polarSeries.invalidate();
        }
    }

    protected void initChart() {
        super.initChart();
        Header tmpHeader = chart1.getHeader();
        tmpHeader.setVisible(true);
        tmpHeader.setText("Smooth Polar Chart");
        chart1.getAspect().setView3D(false);

        chart1.getAxes().getBottom().setIncrement(10.0);

        Legend tmpLegend = chart1.getLegend();
        tmpLegend.setAlignment(LegendAlignment.BOTTOM);
        tmpLegend.setColorWidth(25);
        tmpLegend.setLegendStyle(LegendStyle.SERIES);
        tmpLegend.getSymbol().setWidth(25);
        tmpLegend.getSymbol().setWidthUnits(LegendSymbolSize.PIXELS);
        tmpLegend.setTopLeftPos(0);
    }

    protected void initSeries() {
        polarSeries = new com.steema.teechart.styles.Polar(chart1.getChart());
        polarSeries.getMarks().setVisible(false);
        polarSeries.setTitle("Original");
        polarSeries.setAngleIncrement(10.0);
        polarSeries.getAngleValues().setOrder(ValueListOrder.ASCENDING);
        polarSeries.getBrush().setColor(Color.WHITE);
        polarSeries.getBrush().setVisible(false);
        polarSeries.getPen().setColor(Color.RED);
        polarSeries.getPen().setWidth(2);
        polarSeries.getPointer().setHorizSize(2);
        polarSeries.getPointer().setVertSize(2);
        polarSeries.getPointer().setInflateMargins(true);
        polarSeries.getPointer().getPen().setColor(Color.MAROON);
        polarSeries.getPointer().setStyle(PointerStyle.RECTANGLE);
        polarSeries.getPointer().setVisible(true);

        polarSeries.add(14.4, 132);
        polarSeries.add(28.8, 446);
        polarSeries.add(43.2, 145);
        polarSeries.add(57.6, 549);
        polarSeries.add(72, 709);
        polarSeries.add(86.4, 452);
        polarSeries.add(100.8, 368);
        polarSeries.add(115.2, 551);
        polarSeries.add(144, 146);
        polarSeries.add(158.4, 378);
        polarSeries.add(172.8, 294);
        polarSeries.add(187.2, 978);
        polarSeries.add(201.6, 719);
        polarSeries.add(216, 261);
        polarSeries.add(230.4, 631);
        polarSeries.add(244.8, 432);
        polarSeries.add(259.2, 605);
        polarSeries.add(273.6, 655);
        polarSeries.add(288, 831);
        polarSeries.add(302.4, 614);
        polarSeries.add(316.8, 664);
        polarSeries.add(331.2, 842);
        polarSeries.add(345.6, 974);
        polarSeries.add(360, 59);

        smoothingFunction = new com.steema.teechart.functions.Smoothing(chart1.getChart());
        smoothingFunction.setPeriod(1);
        smoothingFunction.setFactor(7);

        smoothSeries = new com.steema.teechart.styles.Polar(chart1.getChart());
        smoothSeries.setDataSource(polarSeries);
        smoothSeries.setFunction(smoothingFunction);
        smoothSeries.setTitle("Smooth");
        smoothSeries.getMarks().setVisible(false);
        smoothSeries.setColor(Color.AQUA);
        smoothSeries.setAngleIncrement(10.0);
        smoothSeries.getAngleValues().setOrder(ValueListOrder.ASCENDING);
        smoothSeries.getBrush().setColor(Color.WHITE);
        smoothSeries.getBrush().loadImage(ChartSamplePanel.class.getResource(URL_BRUSH_IMAGE));
        smoothSeries.getBrush().setImageMode(ImageMode.TILE);
        smoothSeries.getCircleGradient().setEndColor(Color.GRAY);
        smoothSeries.getCircleGradient().setMiddleColor(Color.WHITE);
        smoothSeries.getCircleGradient().setStartColor(Color.SILVER);
        smoothSeries.getCirclePen().setWidth(3);
        smoothSeries.getPen().setColor(Color.GREEN);
        smoothSeries.getPen().setWidth(2);
        smoothSeries.getPointer().setVisible(false);
        smoothSeries.getRadiusValues().setOrder(ValueListOrder.NONE);

        chart1.getSeries().exchange(0,1); //change series draw order
    }

    protected void initComponents() {
        super.initComponents();
        initSeries();

        originalButton = new JCheckBox("Show Original Polar");
        originalButton.setSelected(polarSeries.getVisible());
        smoothButton = new JCheckBox("Show Smooth Polar");
        smoothButton.setSelected(smoothSeries.getVisible());
        interpolateButton = new JCheckBox("Interpolate");
        interpolateButton.setSelected(smoothingFunction.getInterpolate());
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(originalButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(smoothButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(interpolateButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JCheckBox originalButton, smoothButton, interpolateButton;
    private final static String URL_BRUSH_IMAGE = "images/cloud.png";
}
