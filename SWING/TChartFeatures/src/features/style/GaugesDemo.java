/*
 * GaugesDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.editors.ChartEditor;
import com.steema.teechart.styles.Gauges;
import com.steema.teechart.styles.PointerStyle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class GaugesDemo extends ChartSamplePanel
    implements ActionListener, ChangeListener {

    private Gauges series;

    /**
     * Creates a new instance of GaugesDemo
     */
    public GaugesDemo() {
        super();
        editButton.addActionListener(this);
        valueSlider.addChangeListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == editButton) {
            ChartEditor.editSeries(series);
        }
    }

    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider)e.getSource();
        if (!source.getValueIsAdjusting()) {
            int value = (int)source.getValue();
            series.setValue(value);
            valueLabel.setText(Double.toString(series.getValue()));
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getAspect().setView3D(false);
        chart1.getWalls().getBack().getPen().setVisible(false);
        chart1.getHeader().setVisible(true);
        chart1.setText("Volts");
        chart1.getAxes().getBottom().getMinorTicks().setLength(6);
        chart1.getAxes().getBottom().getTicks().setLength(14);
        chart1.getFrame().setVisible(false);
        chart1.getAxes().getLeft().setIncrement(10);
        chart1.getAxes().getLeft().getMinorTicks().setLength(6);
        chart1.getAxes().getLeft().getTicks().setLength(14);
        chart1.getAxes().getLeft().getAxisPen().setColor(Color.BLACK);
    }

    protected void initComponents() {
        super.initComponents();

        series = new Gauges(chart1.getChart());
        series.setMinimum(0);
        series.setMaximum(100);
        series.setValue(6);
        series.getCenter().getBrush().setColor(Color.BLACK);
        series.getCenter().getGradient().setEndColor(Color.BLACK);
        series.getCenter().getGradient().setVisible(true);
        series.getCenter().setInflateMargins(true);
        series.getCenter().setStyle(PointerStyle.CIRCLE);
        series.getCenter().setHorizSize(6);
        series.getCenter().setVertSize(6);
        series.getCenter().setVisible(true);
        series.setTotalAngle(90);

        series.getEndPoint().setVisible(false);

        editButton = new JButton("Edit...");
        valueLabel = new JLabel(Double.toString(series.getValue()));
        valueSlider = new JSlider(JSlider.HORIZONTAL, 1, 100, 6);
    }

    protected void initGUI() {
        super.initGUI();
        JLabel tmpLabel;
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(editButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpLabel = new JLabel("Value:");
            tmpLabel.setDisplayedMnemonic('V');
            tmpLabel.setLabelFor(valueSlider);
            tmpPane.add(tmpLabel);
            tmpPane.add(valueSlider);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(valueLabel);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JButton editButton;
    private JLabel valueLabel;
    private JSlider valueSlider;
}
