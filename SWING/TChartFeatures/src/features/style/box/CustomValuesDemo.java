/*
 * CustomValuesDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.box;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.DashStyle;
import com.steema.teechart.events.ChartDrawEvent;
import com.steema.teechart.events.ChartPaintAdapter;
import com.steema.teechart.styles.Box;
import com.steema.teechart.styles.PointerStyle;
import com.steema.teechart.styles.SeriesPointer;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import features.ChartSamplePanel;
import java.text.DecimalFormat;

/**
 *
 * @author tom
 */
public class CustomValuesDemo extends ChartSamplePanel
    implements ItemListener {

    private Box series;

    /** Creates a new instance of CustomValuesDemo */
    public CustomValuesDemo() {
        super();
        customButton.addItemListener(this);
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == customButton) {
            series.setUseCustomValues(isSelected);
            if (series.getUseCustomValues()) {
                series.setMedian(15);
                series.setQuartile1(13);
                series.setQuartile3(17);
                series.setInnerFence1(12);
                series.setInnerFence3(18);
                series.setOuterFence1(10);
                series.setOuterFence3(20);
            }
            chart1.repaint();
            updateDisplay();
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getLegend().setVisible(false);
        chart1.getAspect().setView3D(false);
        chart1.addChartPaintListener(new ChartPaintAdapter() {
            public void chartPainted(ChartDrawEvent e) {
                updateDisplay();
            };
        });
    }

    protected void initComponents() {
        super.initComponents();
        series = new Box(chart1.getChart());
        series.clear();
        series.add(new double[] {12, 14, 18, 18.5, 18.6, 18.6, 19, 24});
        series.setUseCustomValues(false); // by default, use internal calculating algorithms
        SeriesPointer tmpPointer;
        tmpPointer = series.getPointer();
        tmpPointer.getBrush().setColor(Color.WHITE);
        tmpPointer.setDraw3D(false);
        tmpPointer.setHorizSize(15);
        tmpPointer.setVertSize(15);
        tmpPointer.setInflateMargins(true);
        tmpPointer.setStyle(PointerStyle.RECTANGLE);
        tmpPointer.setVisible(true);
        tmpPointer = series.getExtrOut();
        tmpPointer.setInflateMargins(true);
        tmpPointer.setStyle(PointerStyle.STAR);
        tmpPointer.setVisible(true);
        tmpPointer = series.getMildOut();
        tmpPointer.setInflateMargins(true);
        tmpPointer.setStyle(PointerStyle.CIRCLE);
        tmpPointer.setVisible(true);
        series.getMedianPen().setStyle(DashStyle.DASH);
        series.setWhiskerLength(1.5);

        customButton = new JCheckBox("Custom Values");
        medianLabel = new JLabel();
        innerLabel = new JLabel();
        outerLabel = new JLabel();

        df = new DecimalFormat("0.00");

        updateDisplay();
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(customButton);
            JPanel vPane = new JPanel();
            vPane.setLayout(new BoxLayout(vPane, BoxLayout.Y_AXIS));
            vPane.add(medianLabel);
            vPane.add(innerLabel);
            vPane.add(outerLabel);
            tmpPane.add(vPane);
            tmpPane.add(javax.swing.Box.createHorizontalGlue());
        }
    }


    private DecimalFormat df;

    private void updateDisplay() {
        medianLabel.setText(
                "Median = " + df.format(series.getMedian())
        );
        innerLabel.setText(
                "Inner Fences = ["
                + df.format(series.getInnerFence1())
                + " ; "
                + df.format(series.getInnerFence3())
                + "]"
        );
        outerLabel.setText(
                "Outer Fences = ["
                + df.format(series.getOuterFence1())
                + " ; "
                + df.format(series.getOuterFence3())
                + "]"
        );
    }

    private JCheckBox customButton;
    private JLabel medianLabel, innerLabel, outerLabel;
}
