/*
 * LabelsInsideDemo.java
 *
 * <p>Copyright: Copyright (c) 2004-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.polar;

import com.steema.teechart.ImageMode;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.Polar;
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
public class LabelsInsideDemo extends ChartSamplePanel
    implements ItemListener {

    private Polar series;

    /** Creates a new instance of LabelsInsideDemo */
    public LabelsInsideDemo() {
        super();
        insideButton.addItemListener(this);
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == insideButton) {
            series.setCircleLabelsInside(isSelected);
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getPanel().getGradient().setEndColor(Color.OLIVE);
        chart1.getPanel().getGradient().setStartColor(Color.WHITE);
        chart1.getPanel().getGradient().setVisible(true);
        chart1.getLegend().setVisible(false);
        chart1.getAxes().setVisible(false);
        chart1.getAxes().getBottom().getGrid().setVisible(false);
        chart1.getAxes().getLeft().getGrid().setVisible(false);
        chart1.getAspect().setView3D(false);
    }

    protected void initComponents() {
        super.initComponents();
        series = new com.steema.teechart.styles.Polar(chart1.getChart());
        series.fillSampleValues(10);
        series.setCircleLabels(true);
        series.setCircleLabelsInside(true);
        series.setClockWiseLabels(true);
        series.setCircled(true);
        series.setCircleBackColor(Color.EMPTY);
        series.getCircleLabelsFont().setColor(Color.NAVY);
        series.getCircleLabelsFont().setSize(13);
        series.getCircleLabelsFont().setBold(true);
        series.getCircleLabelsFont().setItalic(true);
        series.setTransparency(35);
        series.getBrush().setColor(Color.WHITE);
        series.getBrush().setSolid(true);
        series.getBrush().loadImage(ChartSamplePanel.class.getResource(URL_BRUSHPATTERN));
        series.getBrush().setImageMode(ImageMode.TILE);

        insideButton = new JCheckBox("Circle Labels Inside");
        insideButton.setSelected(series.getCircleLabelsInside());
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(insideButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JCheckBox insideButton;
    private final static String URL_BRUSHPATTERN = "images/zigzag.png";
}
