/*
 * ChartListBoxDemo.java
 *
 * <p>Copyright: (c) 2004-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.components.chartlistbox;

import com.steema.teechart.editors.ChartListBox;
import com.steema.teechart.styles.Bar;
import com.steema.teechart.styles.FastLine;
import com.steema.teechart.styles.Line;
import com.steema.teechart.styles.Points;
import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import features.SamplePanel;

/**
 *
 * @author tom
 */
public class ChartListBoxDemo extends SamplePanel
        implements ItemListener {

    protected ChartListBox chartListBox;

    /** Creates a new instance of ChartListBoxDemo */
    public ChartListBoxDemo() {
        super();
        showIconButton.addItemListener(this);
        showActiveButton.addItemListener(this);
        showColorButton.addItemListener(this);
        showTitleButton.addItemListener(this);
    }

    public void itemStateChanged(ItemEvent ie) {
        Object source = ie.getItemSelectable();
        boolean isSelected = (ie.getStateChange() == ItemEvent.SELECTED);
        if (source == showIconButton) {
            chartListBox.setShowSeriesIcon(isSelected);
        } else if (source == showActiveButton) {
            chartListBox.setShowActiveCheck(isSelected);
        } else if (source == showColorButton) {
            chartListBox.setShowSeriesColor(isSelected);
        } else if (source == showTitleButton) {
            chartListBox.setShowSeriesTitle(isSelected);
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getAspect().setChart3DPercent(60);
        chart1.getAspect().setElevation(346);
        chart1.getAspect().setOrthogonal(false);
        chart1.getAspect().setPerspective(35);
        chart1.getAspect().setRotation(329);
        chart1.getAspect().setZoom(72);
    }

    protected void initComponents() {
        super.initComponents();

        chartListBox = new ChartListBox(chart1.getChart());

        new Line(chart1.getChart());
        new Bar(chart1.getChart());
        new Points(chart1.getChart());
        new FastLine(chart1.getChart());
        new Line(chart1.getChart());
        new Line(chart1.getChart());

        chart1.getSeries(0).fillSampleValues(20);
        chart1.getSeries(1).fillSampleValues(6);
        chart1.getSeries(2).fillSampleValues(10);
        chart1.getSeries(3).fillSampleValues(20);
        chart1.getSeries(4).fillSampleValues(20);
        chart1.getSeries(5).fillSampleValues(20);

        showActiveButton = new JCheckBox("Show Active CheckBox");
        showActiveButton.setSelected(chartListBox.getShowSeriesIcon());
        showColorButton = new JCheckBox("Show Series Color");
        showColorButton.setSelected(chartListBox.getShowActiveCheck());
        showIconButton = new JCheckBox("Show Series Icon");
        showIconButton.setSelected(chartListBox.getShowSeriesColor());
        showTitleButton = new JCheckBox("Show Series Title");
        showTitleButton.setSelected(chartListBox.getShowSeriesTitle());
    }

    protected void initGUI() {
        super.initGUI();

        JPanel buttonPane = getButtonPane();
        {
            buttonPane.add(showIconButton);
            buttonPane.add(Box.createHorizontalStrut(10));
            buttonPane.add(showActiveButton);
            buttonPane.add(Box.createHorizontalStrut(10));
            buttonPane.add(showColorButton);
            buttonPane.add(Box.createHorizontalStrut(10));
            buttonPane.add(showTitleButton);
            buttonPane.add(Box.createHorizontalGlue());
        }

        JSplitPane tmpSplitPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                new JScrollPane(chartListBox),
                chart1
                
                );
        tmpSplitPane.setDividerLocation(100);
        tmpSplitPane.setBorder(null);

        JPanel tmpPane = new JPanel();
        tmpPane.setLayout( new BorderLayout() );
        tmpPane.add(myCommander, BorderLayout.PAGE_START);
        tmpPane.add(tmpSplitPane, BorderLayout.CENTER);

        setSamplePane(tmpPane);
    }

    private JCheckBox showIconButton, showActiveButton, showColorButton, showTitleButton;
}
