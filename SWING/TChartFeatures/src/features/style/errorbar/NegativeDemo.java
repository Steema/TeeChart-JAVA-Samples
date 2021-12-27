/*
 * NegativeDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.errorbar;

import com.steema.teechart.axis.Axis;
import com.steema.teechart.legend.Legend;
import com.steema.teechart.legend.LegendSymbolSize;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.DashStyle;
import com.steema.teechart.drawing.GradientDirection;
import com.steema.teechart.styles.BarStyle;
import com.steema.teechart.styles.ErrorBar;
import com.steema.teechart.tools.ColorLine;
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
public class NegativeDemo extends ChartSamplePanel
        implements ItemListener {

    private ErrorBar series;

    /** Creates a new instance of NegativeDemo */
    public NegativeDemo() {
        super();
        view3DButton.addItemListener(this);
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == view3DButton) {
            chart1.getAspect().setView3D(isSelected);
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getHeader().setVisible(true);
        chart1.setText("Error-Bar series with negative values.");
        chart1.getAspect().setView3D(false);
        chart1.getFrame().setVisible(false);
        chart1.getWalls().getBack().getPen().setVisible(false);
        chart1.getPanel().getGradient().setStartColor(Color.GRAY);
        chart1.getPanel().getGradient().setEndColor(Color.WHITE);
        chart1.getPanel().getGradient().setVisible(true);

        Legend tmpLegend = chart1.getLegend();
        tmpLegend.getSymbol().setWidth(10);
        tmpLegend.getSymbol().setWidthUnits(LegendSymbolSize.PIXELS);

        Axis tmpAxis;
        tmpAxis = chart1.getAxes().getBottom();
        tmpAxis.getAxisPen().setColor(Color.OLIVE);
        tmpAxis.getAxisPen().setWidth(1);
        tmpAxis.getGrid().setVisible(false);
        tmpAxis.getLabels().getFont().setColor(Color.SILVER);
        tmpAxis.getLabels().getFont().setBold(true);
        tmpAxis.getLabels().getShadow().setColor(Color.BLACK);
        tmpAxis.getLabels().getShadow().setHorizSize(1);
        tmpAxis.getLabels().getShadow().setVertSize(1);
        tmpAxis.getMinorTicks().setLength(3);
        tmpAxis.getMinorTicks().setColor(Color.BLACK);
        tmpAxis.getTicks().setColor(Color.AQUA);
        tmpAxis.getTicksInner().setColor(Color.BLACK);

        tmpAxis = chart1.getAxes().getLeft();
        tmpAxis.getGrid().setColor(Color.WHITE);
        tmpAxis.getGrid().setStyle(DashStyle.SOLID);
        tmpAxis.setGridCentered(true);
    }

    protected void initComponents() {
        super.initComponents();
        series = new ErrorBar(chart1.getChart());
        initSeries();
        ColorLine tool = new ColorLine(chart1.getChart());
        tool.getPen().setColor(Color.GRAY);
        tool.setAxis(chart1.getAxes().getLeft());
        view3DButton = new JCheckBox("View 3D");
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(view3DButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private void initSeries() {
        series.setBarStyle(BarStyle.RECTGRADIENT);
        series.setColor(Color.RED);
        series.getGradient().setDirection(GradientDirection.VERTICAL);
        series.getGradient().setUseMiddle(true);
        series.getGradient().setMiddleColor(Color.YELLOW);
        series.getGradient().setStartColor(Color.BLUE);
        series.getErrorPen().setColor(Color.BLUE);
        series.getErrorValues().setDateTime(false);
        series.clear();
        series.add(0,-123,23,"", Color.EMPTY);
        series.add(1,432,65,"", Color.EMPTY);
        series.add(2,-88,13,"", Color.EMPTY);
        series.add(3,222,44,"", Color.EMPTY);
        series.add(4,-321,49,"", Color.EMPTY);
    }

    private JCheckBox view3DButton;
}
