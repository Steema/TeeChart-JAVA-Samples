/*
 * ExtraLegendDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.tool;

import com.steema.teechart.editors.LegendEditor;
import com.steema.teechart.legend.Legend;
import com.steema.teechart.legend.LegendStyle;
import com.steema.teechart.styles.Line;
import com.steema.teechart.styles.Series;
import com.steema.teechart.tools.ExtraLegend;
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
public class ExtraLegendDemo extends ChartSamplePanel
    implements ActionListener, ItemListener {

    private ExtraLegend tool1;

    /**
     * Creates a new instance of ExtraLegendDemo
     */
    public ExtraLegendDemo() {
        super();
        editButton.addActionListener(this);
        alignButton.addActionListener(this);
        showLegendButton.addItemListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == alignButton) {
            Legend tmpLegend = tool1.getLegend();
            tmpLegend.setCustomPosition(true);
            tmpLegend.setLeft(chart1.getLegend().getLeft());
            tmpLegend.setTop(chart1.getLegend().getShapeBounds().getBottom()+10);
        } else if (source == editButton) {
            LegendEditor.editLegend(this, tool1.getLegend());
        }
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);

        if (source == showLegendButton) {
            tool1.setActive(isSelected);
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getLegend().setVisible(true);
        chart1.getLegend().setLegendStyle(LegendStyle.VALUES);
    }

    protected void initComponents() {
        super.initComponents();

        new Line(chart1.getChart());
        new Line(chart1.getChart());
        for (int i=0; i < chart1.getSeriesCount(); i++) {
            Series tmpSeries = chart1.getSeries(i);
            tmpSeries.fillSampleValues(5);
            tmpSeries.getMarks().setVisible(false);
            ((Line)tmpSeries).getPointer().setVisible(false);
        }

        tool1 = new ExtraLegend(chart1.getChart());
        tool1.setSeries(chart1.getSeries(1));

        // Cosmetic
        Legend tmpLegend = tool1.getLegend();
        tmpLegend.setLegendStyle(LegendStyle.VALUES);
        tmpLegend.getShadow().setTransparency(70);
        tmpLegend.setCustomPosition(true);
        tmpLegend.setLeft(50);
        tmpLegend.setTop(50);

        editButton = new JButton("Edit extra legend...");
        alignButton = new JButton("Align");
        showLegendButton = new JCheckBox("Show extra legend");
        showLegendButton.setSelected(true);
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(showLegendButton);
            tmpPane.add(Box.createHorizontalStrut(20));
            tmpPane.add(editButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(alignButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JButton alignButton, editButton;
    private JCheckBox showLegendButton;
}
