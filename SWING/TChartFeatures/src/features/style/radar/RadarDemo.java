/*
 * RadarDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.radar;

import com.steema.teechart.ScrollMode;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.GradientDirection;
import com.steema.teechart.drawing.StringAlignment;
import com.steema.teechart.styles.Radar;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import features.SamplePanel;

/**
 *
 * @author tom
 */
public class RadarDemo extends SamplePanel
    implements ItemListener {

    private Radar series1, series2, series3;

    /** Creates a new instance of RadarDemo */
    public RadarDemo() {
        super();
        squaredButton.addItemListener(this);
        showLabelsButton.addItemListener(this);
        showLinesButton.addItemListener(this);
        showAxisButton.addItemListener(this);
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == squaredButton) {
            series1.setCircled(isSelected);
        } else if (source == showLabelsButton) {
            series1.setCircleLabels(isSelected);
        } else if (source == showLinesButton) {
            for (int t=0; t < chart1.getSeriesCount(); t++) {
                ((Radar)chart1.getSeries(t)).getPen().setVisible(isSelected);
            }
        } else if (source == showAxisButton) {
            chart1.getAxes().getRight().setVisible(isSelected);
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getPanning().setAllow(ScrollMode.NONE);
        chart1.getPanel().getGradient().setVisible(true);
        chart1.getPanel().getGradient().setDirection(GradientDirection.HORIZONTAL);
        chart1.getHeader().setVisible(true);
        chart1.getHeader().setAlignment(StringAlignment.NEAR);
        chart1.setText("Radar Series");
        chart1.getAxes().setVisible(false);
        chart1.getFrame().setVisible(false);
        chart1.getAspect().setView3D(false);
        chart1.getZoom().setAllow(false);
    }

    protected void initComponents() {
        super.initComponents();

        series1 = new com.steema.teechart.styles.Radar(chart1.getChart());
        series1.setCircleBackColor(Color.WHITE);
        series1.setCircleLabels(true);
        series1.getCircleLabelsFont().setItalic(true);
        series1.getCirclePen().setColor(Color.AQUA);

        series2 = new com.steema.teechart.styles.Radar(chart1.getChart());
        series3 = new com.steema.teechart.styles.Radar(chart1.getChart());

        for (int t=0; t < chart1.getSeriesCount(); t++) {
            chart1.getSeries(t).fillSampleValues(5);
            ((Radar)chart1.getSeries(t)).setCircled(true);
            ((Radar)chart1.getSeries(t)).getBrush().setVisible(false);
        }

        squaredButton = new JCheckBox("Squared");
        squaredButton.setSelected(true);
        showLabelsButton = new JCheckBox("Show Labels");
        showLabelsButton.setSelected(true);
        showLinesButton = new JCheckBox("Show Lines");
        showLinesButton.setSelected(true);
        showAxisButton = new JCheckBox("Show Axis");
        showAxisButton.setSelected(true);
    }

    protected void initGUI() {
        super.initGUI();
        myCommander.setVisible(true);
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(squaredButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(showLabelsButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(showLinesButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(showAxisButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JCheckBox squaredButton, showLabelsButton, showLinesButton, showAxisButton;
}
