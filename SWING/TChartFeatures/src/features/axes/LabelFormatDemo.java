/*
 * LabelFormatDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.axes;

import com.steema.teechart.axis.AxisLabels;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.editors.DialogFactory;
import com.steema.teechart.styles.Bar;
import com.steema.teechart.styles.HorizontalAxis;
import com.steema.teechart.styles.VerticalAxis;
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

/**
 *
 * @author tom
 */
public class LabelFormatDemo extends ChartSamplePanel
    implements ActionListener, ItemListener {

    /**
     * Creates a new instance of LabelFormatDemo
     */
    public LabelFormatDemo() {
        super();
        axisList.addActionListener(this);
        editButton.addActionListener(this);
        transparentButton.addItemListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == axisList) {
            transparentButton.setSelected(getSelectedAxisLabels().getTransparent());
        } else if (source == editButton) {
            DialogFactory.showModal(getSelectedAxisLabels());
        }
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == transparentButton) {
            getSelectedAxisLabels().setTransparent(isSelected);
        }
    }

    protected void initComponents() {
        super.initComponents();
        Bar series = new Bar(chart1.getChart());
        series.fillSampleValues(5);
        series.setHorizontalAxis(HorizontalAxis.BOTH);
        series.setVerticalAxis(VerticalAxis.BOTH);
        series.getMarks().setArrowLength(20);
        series.getMarks().getCallout().getBrush().setColor(Color.BLACK);
        series.getMarks().getCallout().setLength(20);
        series.getMarks().setVisible(true);
        series.setColor(Color.PURPLE);
        series.setDark3D(false);

        editButton = new JButton("Edit...");
        axisList = new JComboBox(new String[]{"Left", "Top", "Right", "Bottom"});
        axisList.setSelectedIndex(0);
        transparentButton = new JCheckBox("Transparent");
        transparentButton.setSelected(getSelectedAxisLabels().getTransparent());
    }

    protected void initGUI() {
        super.initGUI();
        JLabel tmpLabel;
        JPanel tmpPane = getButtonPane();
        {
            tmpLabel = new JLabel("Axis:");
            tmpLabel.setDisplayedMnemonic('A');
            tmpLabel.setLabelFor(axisList);
            tmpPane.add(tmpLabel);
            tmpPane.add(axisList);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(transparentButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(editButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private AxisLabels getSelectedAxisLabels() {
            AxisLabels labels;
            switch (axisList.getSelectedIndex()) {
                case 1: {labels = chart1.getAxes().getTop().getLabels(); break; }
                case 2: {labels = chart1.getAxes().getRight().getLabels(); break; }
                case 3: {labels = chart1.getAxes().getBottom().getLabels(); break; }
                default: {labels = chart1.getAxes().getLeft().getLabels(); }
            }
            return labels;
    }

    private JButton editButton;
    private JCheckBox transparentButton;
    private JComboBox axisList;
}
