/*
 * LegendTitleDemo.java
 *
 * <p>Copyright: Copyright (c) 2004-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.legend;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.GradientDirection;
import com.steema.teechart.editors.ChartEditor;
import com.steema.teechart.legend.Legend;
import com.steema.teechart.styles.Line;
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
public class LegendTitleDemo extends ChartSamplePanel
        implements ActionListener, ItemListener {

    /** Creates a new instance of LegendTitleDemo */
    public LegendTitleDemo() {
        super();
        legendButton.addActionListener(this);
        showTitleButton.addItemListener(this);
    }

    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if (source == legendButton) {
            ChartEditor.editLegend(chart1.getChart());
        }
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == showTitleButton) {
            chart1.getLegend().getTitle().setVisible(isSelected);
            chart1.repaint();
        }
    }

    protected void initChart() {
        super.initChart();
        Legend legend = chart1.getLegend();
        legend.getGradient().setDirection(GradientDirection.HORIZONTAL);
        legend.getGradient().setStartColor(Color.GRAY);
        legend.getGradient().setMiddleColor(Color.WHITE);
        legend.getGradient().setEndColor(Color.GRAY);
        legend.getGradient().setVisible(true);
        legend.getTitle().setText("Values");
        legend.getTitle().setTransparent(false);
        legend.getTitle().getFont().setColor(Color.BLUE);
    }

    protected void initComponents() {
        super.initComponents();
        Line tmpSeries = new Line(chart1.getChart());
        tmpSeries.getPointer().setVisible(false);

        tmpSeries.fillSampleValues();

        legendButton = new JButton("Legend title properties...");
        showTitleButton = new JCheckBox("Show legend title");
        showTitleButton.setSelected(chart1.getLegend().getTitle().getVisible());
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(showTitleButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(legendButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JButton legendButton;
    private JCheckBox showTitleButton;
}
