/*
 * LabelAlignmentDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.axes;

import com.steema.teechart.axis.AxisLabelAlign;
import com.steema.teechart.styles.HorizBar;
import com.steema.teechart.styles.MarksStyle;
import com.steema.teechart.styles.VerticalAxis;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class LabelAlignmentDemo extends ChartSamplePanel
        implements ActionListener, ItemListener {

    private HorizBar series;

    /**
     * Creates a new instance of LabelAlignmentDemo
     */
    public LabelAlignmentDemo() {
        super();
        oppositeButton.addItemListener(this);
        leftButton.addActionListener(this);
        rightButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == leftButton) {
            series.setVerticalAxis(VerticalAxis.LEFT);
        } else if (source == rightButton) {
            series.setVerticalAxis(VerticalAxis.RIGHT);
        }
        setLabelAlignment();
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == oppositeButton) {
            setLabelAlignment();
        }
    }

    protected void initChart() {
        super.initChart();
        /* Set axes labels to "opposite" alignment */
        chart1.getAxes().getLeft().getLabels().setAlign(AxisLabelAlign.OPPOSITE);
        chart1.getAxes().getRight().getLabels().setAlign(AxisLabelAlign.OPPOSITE);
    }

    protected void initComponents() {
        super.initComponents();

        series = new HorizBar(chart1.getChart());
        /* Sample values */
        series.add( 278, "Africa");
        series.add( 123, "America");
        series.add( 321, "Asia");
        series.add( 432, "Australia");
        series.add(  89, "Europe");
        series.add( 300, "Moon");
        series.setColorEach(true);
        series.getMarks().setArrowLength(20);
        series.getMarks().setStyle(MarksStyle.PERCENT);
        series.getMarks().setVisible(true);

        oppositeButton = new JCheckBox("Opposite Label Aligment");
        oppositeButton.setSelected(true);
        leftButton = new JRadioButton("Left");
        leftButton.setSelected(true);
        rightButton = new JRadioButton("Right");
        ButtonGroup group = new ButtonGroup();
        group.add(leftButton);
        group.add(rightButton);
    }

    protected void initGUI() {
        super.initGUI();
        JPanel groupPane = new JPanel();
        {
            groupPane.setBorder(BorderFactory.createTitledBorder("Axis"));
            groupPane.add(leftButton);
            groupPane.add(rightButton);
        }
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(oppositeButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(groupPane);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private void setLabelAlignment() {
        if (oppositeButton.isSelected()) {
            series.getVertAxis().getLabels().setAlign(AxisLabelAlign.OPPOSITE);
        } else {
            series.getVertAxis().getLabels().setAlign(AxisLabelAlign.DEFAULT);
        }
    }

    private JRadioButton leftButton, rightButton;
    private JCheckBox oppositeButton;
}
