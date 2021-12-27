/*
 * LabelAlternateDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.axes;

import com.steema.teechart.axis.Axis;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.HorizontalAxis;
import com.steema.teechart.styles.Line;
import com.steema.teechart.styles.SeriesPointer;
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
public class LabelAlternateDemo extends ChartSamplePanel
        implements ActionListener, ItemListener {

    private Axis selectedAxis;

    /**
     * Creates a new instance of LabelAlignmentDemo
     */
    public LabelAlternateDemo() {
        super();
        alternateButton.addItemListener(this);
        leftButton.addActionListener(this);
        rightButton.addActionListener(this);
        topButton.addActionListener(this);
        bottomButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == leftButton) {
            selectedAxis = chart1.getAxes().getLeft();
        } else if (source == rightButton) {
            selectedAxis = chart1.getAxes().getRight();
        } else if (source == topButton) {
            selectedAxis = chart1.getAxes().getTop();
        } else if (source == bottomButton) {
            selectedAxis = chart1.getAxes().getBottom();
        }

        alternateButton.setSelected(selectedAxis.getLabels().getAlternate());

        higlightLabels();
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == alternateButton) {
            selectedAxis.getLabels().setAlternate(isSelected);
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getAspect().setView3D(false);
        /* Set axes alternate labels */
        for (int t=0; t < chart1.getAxes().getCount(); t++) {
            chart1.getAxes().getAxis(t).getLabels().setAlternate(true);
        }
    }

    protected void initComponents() {
        super.initComponents();

        Line series = new Line(chart1.getChart());
        /* Sample values */
        series.fillSampleValues();
        series.setHorizontalAxis(HorizontalAxis.BOTH);
        series.setVerticalAxis(VerticalAxis.BOTH);

        series.getMarks().setVisible(false);

        SeriesPointer pointer= series.getPointer();
        pointer.getGradient().setStartColor(Color.YELLOW);
        pointer.getGradient().setEndColor(Color.RED);
        pointer.getGradient().setVisible(true);
        pointer.setInflateMargins(true);
        pointer.setVisible(true);

        alternateButton = new JCheckBox("Alternate labels");
        alternateButton.setSelected(true);
        leftButton = new JRadioButton("Left");
        leftButton.setSelected(true);
        selectedAxis = chart1.getAxes().getLeft();
        rightButton = new JRadioButton("Right");
        topButton = new JRadioButton("Top");
        bottomButton = new JRadioButton("Bottom");
        ButtonGroup group = new ButtonGroup();
        group.add(leftButton);
        group.add(rightButton);
        group.add(topButton);
        group.add(bottomButton);

        higlightLabels();
    }

    protected void initGUI() {
        super.initGUI();
        JPanel groupPane = new JPanel();
        {
            groupPane.setBorder(BorderFactory.createTitledBorder("Axis"));
            groupPane.add(leftButton);
            groupPane.add(rightButton);
            groupPane.add(topButton);
            groupPane.add(bottomButton);
        }
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(groupPane);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(alternateButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private void higlightLabels() {
        for (int t=0; t < chart1.getAxes().getCount(); t++) {
            chart1.getAxes().getAxis(t).getLabels().getFont().setBold(
                    chart1.getAxes().getAxis(t).equals(selectedAxis)
                    );
        }
    }

    private JRadioButton leftButton, rightButton, topButton, bottomButton;
    private JCheckBox alternateButton;
}
