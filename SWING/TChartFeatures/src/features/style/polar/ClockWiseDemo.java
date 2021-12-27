/*
 * ClockWiseDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.polar;

import com.steema.teechart.drawing.ChartFont;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.DashStyle;
import com.steema.teechart.drawing.GradientDirection;
import com.steema.teechart.styles.PointerStyle;
import com.steema.teechart.styles.Polar;
import com.steema.teechart.styles.SeriesPointer;
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
public class ClockWiseDemo extends ChartSamplePanel
    implements ItemListener {

    private Polar series;

    /** Creates a new instance of ClockWiseDemo */
    public ClockWiseDemo() {
        super();
        clockwiseButton.addItemListener(this);
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == clockwiseButton) {
            series.setClockWiseLabels(isSelected);
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getPanel().getGradient().setEndColor(Color.SILVER);
        chart1.getPanel().getGradient().setStartColor(Color.TEAL);
        chart1.getPanel().getGradient().setDirection(GradientDirection.HORIZONTAL);
        chart1.getPanel().getGradient().setVisible(true);
        chart1.getLegend().setColorWidth(30);
        chart1.getLegend().getSymbol().setWidth(30);
        chart1.getAxes().getBottom().setIncrement(30);
        chart1.getAspect().setView3D(false);
    }

    protected void initComponents() {
        super.initComponents();
        series = new com.steema.teechart.styles.Polar(chart1.getChart());
        series.fillSampleValues(20);
        series.setCircleLabels(true);
        series.setClockWiseLabels(true);
        series.setCircled(true);

        ChartFont tmpFont = series.getCircleLabelsFont();
        tmpFont.setColor(Color.YELLOW);
        tmpFont.setSize(12);
        tmpFont.setItalic(true);

        series.getCirclePen().setColor(Color.BLUE);
        series.getCirclePen().setWidth(2);

        series.getPen().setColor(Color.RED);
        series.getPen().setWidth(2);
        series.getPen().setStyle(DashStyle.SOLID);

        series.getBrush().setColor(Color.WHITE);
        series.getBrush().setVisible(false);

        SeriesPointer tmpPointer = series.getPointer();
        tmpPointer.getBrush().setColor(Color.LIME);
        tmpPointer.setHorizSize(2);
        tmpPointer.setInflateMargins(true);
        tmpPointer.setStyle(PointerStyle.RECTANGLE);
        tmpPointer.setVertSize(2);
        tmpPointer.setVisible(true);

        clockwiseButton = new JCheckBox("Clockwise Labels");
        clockwiseButton.setSelected(series.getClockWiseLabels());
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(clockwiseButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JCheckBox clockwiseButton;
}
