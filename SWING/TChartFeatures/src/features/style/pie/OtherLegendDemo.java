/*
 * OtherLegendDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.pie;

import com.steema.teechart.events.ChartDrawEvent;
import com.steema.teechart.events.ChartPaintAdapter;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.DashStyle;
import com.steema.teechart.editors.DialogFactory;
import com.steema.teechart.legend.Legend;
import com.steema.teechart.styles.Pie;
import com.steema.teechart.styles.PieOtherStyle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class OtherLegendDemo extends ChartSamplePanel
        implements ActionListener, ItemListener {

    private Pie pieSeries;
    private JButton editButton;
    private JCheckBox otherLegendButton;

    /**
     * Creates a new instance of OtherLegendDemo
     */
    public OtherLegendDemo() {
        super();
        editButton.addActionListener(this);
        otherLegendButton.addItemListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == editButton) {
            DialogFactory.showModal(pieSeries.getOtherSlice().getLegend());
        }
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);

        if (source == otherLegendButton) {
            pieSeries.getOtherSlice().getLegend().setVisible(isSelected);
        };
    }

    protected void initChart() {
        super.initChart();
        chart1.getAspect().setElevation(315);
        chart1.getAspect().setOrthogonal(false);
        chart1.getAspect().setPerspective(0);
        chart1.getAspect().setRotation(360);
        chart1.addChartPaintListener( new ChartPaintAdapter() {
            public void chartPainted(ChartDrawEvent ae) {
                // cosmetic line from normal legend to "other" legend
                if (pieSeries.getOtherSlice().getLegend().getVisible()) {
                    chart1.getGraphics3D().getPen().setStyle(DashStyle.DOT);
                    chart1.getGraphics3D().getPen().setWidth(3);
                    chart1.getGraphics3D().getPen().setColor(Color.NAVY);
                    int tmpX = chart1.getLegend().getLeft();
                    chart1.getGraphics3D().moveTo(
                            tmpX,
                            chart1.getLegend().getShapeBounds().getBottom()-1
                    );
                    chart1.getGraphics3D().lineTo(
                            tmpX-10,
                            chart1.getLegend().getShapeBounds().getBottom()
                    );
                    chart1.getGraphics3D().lineTo(
                            tmpX-10,
                            pieSeries.getOtherSlice().getLegend().getTop()+4
                    );
                    chart1.getGraphics3D().lineTo(
                            pieSeries.getOtherSlice().getLegend().getRight(),
                            pieSeries.getOtherSlice().getLegend().getTop()+4
                    );
                }
            }
        });
    }

    protected void initSeries() {
        pieSeries = new com.steema.teechart.styles.Pie(chart1.getChart());
        pieSeries.getMarks().setVisible(true);

        // add data
        pieSeries.add(134, "Google");
        pieSeries.add( 65, "Yahoo");
        pieSeries.add( 23, "AltaVista");
        pieSeries.add( 12, "AllTheWeb");
        pieSeries.add(  9, "Terra");
        pieSeries.add(  6, "Lycos");
        pieSeries.add(  3, "Ask Jeeves");

        // prepare "Other" to group values below 10
        pieSeries.getOtherSlice().setStyle(PieOtherStyle.BELOWVALUE);
        pieSeries.getOtherSlice().setValue(10);

        // Display "Other" legend:
        Legend tmpLegend = pieSeries.getOtherSlice().getLegend();
        tmpLegend.setCustomPosition(true);
        tmpLegend.setLeft(150);
        tmpLegend.setTop(120);
        tmpLegend.setVisible(true);
    }

    protected void initComponents() {
        super.initComponents();
        initSeries();
        editButton = new JButton("Edit Legend...");
        otherLegendButton = new JCheckBox("Show 'Other' legend");
        otherLegendButton.setSelected(pieSeries.getOtherSlice().getLegend().getVisible());
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(otherLegendButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(editButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }


}
