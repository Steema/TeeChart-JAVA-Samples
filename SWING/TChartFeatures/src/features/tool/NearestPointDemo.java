/*
 * NearestPointDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.tool;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.DashStyle;
import com.steema.teechart.editors.ChartEditor;
import com.steema.teechart.styles.CustomStack;
import com.steema.teechart.styles.Points;
import com.steema.teechart.tools.NearestPoint;
import com.steema.teechart.tools.NearestPointStyle;
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
public class NearestPointDemo extends ChartSamplePanel
    implements ActionListener, ItemListener {

    private NearestPoint tool;

    /** Creates a new instance of NearestPointDemo */
    public NearestPointDemo() {
        super();
        editButton.addActionListener(this);
        activeButton.addItemListener(this);
        drawLineButton.addItemListener(this);
        styleList.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == styleList) {
            switch (styleList.getSelectedIndex()) {
                case 0: tool.setStyle(NearestPointStyle.NONE); break;
                case 1: tool.setStyle(NearestPointStyle.CIRCLE); break;
                case 2: tool.setStyle(NearestPointStyle.RECTANGLE); break;
                case 3: tool.setStyle(NearestPointStyle.DIAMOND); break;
            }
        } else if (source == editButton) {
            ChartEditor.editTool(tool);
        }
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == activeButton) {
            tool.setActive(isSelected);
        } else if (source == drawLineButton) {
            tool.setDrawLine(isSelected);
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getHeader().setVisible(false);
        chart1.getAspect().setView3D(false);
    }

    protected void initComponents() {
        super.initComponents();

        Points tmpSeries = new com.steema.teechart.styles.Points(chart1.getChart());
        tmpSeries.getMarks().setArrowLength(20);
        tmpSeries.getMarks().setVisible(true);
        tmpSeries.getPointer().setVisible(true);
        tmpSeries.setStacked(CustomStack.NONE);
        tmpSeries.fillSampleValues(6);

        tool = new com.steema.teechart.tools.NearestPoint(chart1.getChart());
        tool.setSeries(tmpSeries);
        tool.getBrush().setColor(Color.WHITE);
        tool.getBrush().setVisible(false);
        tool.getPen().setColor(Color.WHITE);
        tool.getPen().setStyle(DashStyle.DOT);
        tool.setStyle(NearestPointStyle.CIRCLE);
        tool.setDrawLine(true);

        editButton = new JButton("Edit...");
        activeButton = new JCheckBox("Active");
        activeButton.setSelected(true);
        drawLineButton = new JCheckBox("Draw line");
        drawLineButton.setSelected(true);
        styleList = new JComboBox(EnumStrings.NEARESTPOINT_STYLES);
        styleList.setSelectedIndex(1);
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
            tmpPane.add(activeButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(drawLineButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(editButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JButton editButton;
    private JCheckBox activeButton, drawLineButton;
    private JComboBox styleList;
}
