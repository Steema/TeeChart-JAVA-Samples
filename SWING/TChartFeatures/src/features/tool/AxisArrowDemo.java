/*
 * AxisArrowDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL.
 All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.tool;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.editors.ChartEditor;
import com.steema.teechart.styles.Line;
import com.steema.teechart.tools.AxisArrow;
import com.steema.teechart.tools.AxisArrowPosition;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class AxisArrowDemo extends ChartSamplePanel
    implements ActionListener, ItemListener {

    private AxisArrow tool1, tool2, tool3;

    /**
     * Creates a new instance of AxisArrowDemo
     */
    public AxisArrowDemo() {
        super();
        editButton.addActionListener(this);
        leftTopButton.addItemListener(this);
        bottomButton.addItemListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == editButton) {
            ChartEditor.editTool(tool1);
        }
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);

        if (source == leftTopButton) {
            tool1.setActive(isSelected);
        } else if (source == bottomButton) {
            tool2.setActive(isSelected);
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getLegend().setVisible(false);
        chart1.getAspect().setView3D(false);
    }

    protected void initComponents() {
        super.initComponents();
        Line tmpSeries = new com.steema.teechart.styles.Line(chart1.getChart());
        tmpSeries.fillSampleValues(10);
        tmpSeries.getPointer().setVisible(true);
        tmpSeries.getMarks().setVisible(false);

        tool1 = new AxisArrow(chart1.getAxes().getLeft());
        tool1.setPosition(AxisArrowPosition.START);


        tool2 = new AxisArrow(chart1.getAxes().getBottom());
        tool2.getBrush().setColor(Color.GREEN_YELLOW);
        tool2.getPen().setColor(Color.WHITE);
        tool2.setPosition(AxisArrowPosition.BOTH);

        tool3 = new AxisArrow(chart1.getAxes().getLeft());
        tool3.setPosition(AxisArrowPosition.END);
        tool3.getBrush().setColor(Color.WHITE);
        tool3.getPen().setColor(Color.BLUE);
        tool3.setLength(36);

        editButton = new JButton("Edit");
        leftTopButton = new JCheckBox("Active Left Top");
        leftTopButton.setSelected(true);
        bottomButton = new JCheckBox("Active Bottom");
        bottomButton.setSelected(true);
        clickedLabel = new JLabel("");
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(leftTopButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(bottomButton);
            tmpPane.add(Box.createHorizontalStrut(20));
            tmpPane.add(editButton);
            tmpPane.add(Box.createHorizontalStrut(20));
            tmpPane.add(clickedLabel);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JCheckBox leftTopButton, bottomButton;
    private JButton editButton;
    private JLabel clickedLabel;
}
