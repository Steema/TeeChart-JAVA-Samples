/*
 * CalloutDemo.java
 *
 * <p>Copyright: (c) 2004-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.marks;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.editors.ChartEditor;
import com.steema.teechart.styles.Bar;
import com.steema.teechart.styles.PointerStyle;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import features.ChartSamplePanel;
import features.utils.EnumStrings;

/**
 *
 * @author tom
 */
public class CalloutDemo extends ChartSamplePanel
    implements ActionListener, ItemListener {

    private Bar series;

    /**
     * Creates a new instance of CalloutDemo
     */
    public CalloutDemo() {
        super();
        visibleButton.addItemListener(this);
        styleList.addActionListener(this);
        editButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if (source == styleList) {
            series.getMarks().getCallout().setStyle(PointerStyle.fromInt(styleList.getSelectedIndex()));
        } else if (source == editButton) {
            ChartEditor.editSeriesMarks(series);
        }
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == visibleButton) {
            series.getMarks().getCallout().setVisible(isSelected);
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getHeader().setText("Series Marks Callouts");
        chart1.getHeader().setVisible(true);
        chart1.getAspect().setChart3DPercent(20);
        chart1.getLegend().setVisible(false);
    }

    protected void initSeries() {
        series = new Bar(chart1.getChart());
        series.getMarks().setArrowLength(21);
        series.getMarks().getCallout().setStyle(PointerStyle.DOWNTRIANGLE);
        series.getMarks().getCallout().getBrush().setColor(Color.BLACK);
        series.getMarks().getCallout().setVisible(true);
        series.getMarks().getCallout().setDistance(13);
        series.getMarks().getCallout().setLength(21);
        series.getMarks().setVisible(true);
        series.fillSampleValues();
    }

    protected void initComponents() {
        super.initComponents();
        initSeries();

        visibleButton = new JCheckBox("Visible");
        visibleButton.setSelected(series.getMarks().getCallout().getVisible());

        styleList = new JComboBox(EnumStrings.POINTER_STYLES);
        styleList.setPreferredSize(new Dimension(100, 20));
        styleList.setSelectedIndex(series.getMarks().getCallout().getStyle().getValue());

        editButton = new JButton("Edit...");
    }

    protected void initGUI() {
        super.initGUI();
        JLabel tmpLabel;
        JPanel tmpPane = getButtonPane();
        {
            tmpLabel = new JLabel("Style:");
            tmpLabel.setDisplayedMnemonic('S');
            tmpLabel.setLabelFor(styleList);
            tmpPane.add(tmpLabel);
            tmpPane.add(styleList);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(editButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(visibleButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JCheckBox visibleButton;
    private JComboBox styleList;
    private JButton editButton;
}
