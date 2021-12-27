/*
 * TransparencyDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.tool.colorband;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.Area;
import com.steema.teechart.tools.ColorBand;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class TransparencyDemo extends ChartSamplePanel
    implements ItemListener, ChangeListener {

    private ColorBand tool;

    /**
     * Creates a new instance of TransparencyDemo
     */
    public TransparencyDemo() {
        super();
        view3DButton.addItemListener(this);
        drawBehindButton.addItemListener(this);
        transparencySlider.addChangeListener(this);
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == drawBehindButton) {
            tool.setDrawBehind(isSelected);
//            chart1.repaint();
        } else if (source == view3DButton) {
            chart1.getAspect().setView3D(isSelected);
        }
    }

    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider)e.getSource();
        if (transparencySlider == source) {
            tool.setTransparency(transparencySlider.getValue());
        }
    }

    // @TODO Events

    protected void initChart() {
        super.initChart();
        chart1.getAspect().setView3D(false);
        chart1.getLegend().setVisible(false);
        chart1.getHeader().setVisible(true);
        chart1.setText("Color Band transparency");
        // remove the chart grid lines
        chart1.getAxes().getLeft().getGrid().setVisible(false);
        chart1.getAxes().getBottom().getGrid().setVisible(false);
    }

    protected void initComponents() {
        super.initComponents();
        Area areaSeries = new com.steema.teechart.styles.Area(chart1.getChart());
        areaSeries.fillSampleValues(20);

        tool = new com.steema.teechart.tools.ColorBand(chart1.getChart());
        tool.setAxis(chart1.getAxes().getLeft());
        tool.setTransparency(50);
        tool.setDrawBehind(false);
        // display the band vertically centered
        tool.setStart(areaSeries.getYValues().getMinimum()+30);
        tool.setEnd(areaSeries.getYValues().getMinimum()-30);
        tool.getBrush().setColor(Color.NAVY);

        transparencySlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
        drawBehindButton = new JCheckBox("Draw Behind");
        drawBehindButton.setSelected(false);
        view3DButton = new JCheckBox("3D");
        view3DButton.setSelected(false);
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(transparencySlider);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(drawBehindButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(view3DButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JCheckBox drawBehindButton, view3DButton;
    private JSlider transparencySlider;
}
