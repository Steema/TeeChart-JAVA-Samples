/*
 * DragPointDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.tool;

import com.steema.teechart.Cursor;
import com.steema.teechart.editors.ChartGridXValues;
import com.steema.teechart.events.ChangeEvent;
import com.steema.teechart.events.DragAdapter;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.editors.ChartEditor;
import com.steema.teechart.editors.ChartGrid;
import com.steema.teechart.styles.Line;
import com.steema.teechart.styles.SeriesPointer;
import com.steema.teechart.tools.DragPoint;
import com.steema.teechart.tools.DragPointStyle;

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
import java.awt.BorderLayout;

import features.ChartSamplePanel;
import features.utils.EnumStrings;

/**
 *
 * @author tom
 */
public class DragPointDemo extends ChartSamplePanel
    implements ActionListener, ItemListener {

    private DragPoint tool1;
    private Line series1;
    private ChartGrid chartGrid;

    /**
     * Creates a new instance of DragPointDemo
     */
    public DragPointDemo() {
        super();
        editButton.addActionListener(this);
        dragStyleList.addActionListener(this);
        activeButton.addItemListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

         if (source == dragStyleList) {
            DragPointStyle dragStyle = DragPointStyle.BOTH;
            switch (dragStyleList.getSelectedIndex()) {
                case 0: dragStyle = DragPointStyle.X; break;
                case 1: dragStyle = DragPointStyle.Y; break;
                case 2: dragStyle = DragPointStyle.BOTH; break;
            }
            tool1.setStyle(dragStyle);
        } else if (source == editButton) {
            ChartEditor.editTool(tool1);
        }
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);

        if (source == activeButton) {
            tool1.setActive(isSelected);
            // change the series cursor:
            if (tool1.getActive()) {
                series1.setCursor(Cursor.CROSS);
            } else {
                series1.setCursor(Cursor.DEFAULT);
            }
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getLegend().setVisible(false);
        chart1.getHeader().setVisible(true);
        chart1.setText( "Drag Point Tool" );
    }

    protected void initComponents() {
        super.initComponents();

        series1 = new Line(chart1.getChart());
        series1.fillSampleValues(20);
        series1.setCursor(Cursor.HAND);

        SeriesPointer tmpPointer = series1.getPointer();
        tmpPointer.getBrush().setColor(Color.BLUE);
        tmpPointer.setInflateMargins(true);
        tmpPointer.getPen().setColor(Color.LIME);
        tmpPointer.setVisible(true);

        tool1 = new DragPoint(series1);
        tool1.setStyle(DragPointStyle.BOTH);

        // set the event...
        tool1.addDragListener( new DragAdapter() {
            public void dragMoving(ChangeEvent e) {
                chartGrid.repaint();
            }
        });

        chartGrid = new ChartGrid(chart1.getChart());
        chartGrid.setShowFields(true);

        // tell the chartGrid to show both the X and Y
        chartGrid.setShowXValues(ChartGridXValues.YES);

        // do not show labels at chart chartGrid
        chartGrid.setShowLabels(false);

        editButton = new JButton("Edit...");
        dragStyleList = new JComboBox(EnumStrings.DRAGPOINT_STYLES);
        dragStyleList.setSelectedIndex(2);
        activeButton = new JCheckBox("Active");
        activeButton.setSelected(true);
    }

    protected void initGUI() {
        super.initGUI();
        JLabel tmpLabel;
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(editButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(activeButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpLabel = new JLabel("Style:");
            tmpLabel.setDisplayedMnemonic('S');
            tmpLabel.setLabelFor(dragStyleList);
            tmpPane.add(tmpLabel);
            tmpPane.add(dragStyleList);
            tmpPane.add(Box.createHorizontalGlue());
        }
        JPanel tmpNewSamplePane = new JPanel();
        {
            tmpNewSamplePane.setLayout(new BorderLayout());
            tmpNewSamplePane.add(chart1, BorderLayout.CENTER);
            tmpNewSamplePane.add(chartGrid, BorderLayout.LINE_END);
        }
        setSamplePane(tmpNewSamplePane);
    }

    private JButton editButton;
    private JComboBox dragStyleList;
    private JCheckBox activeButton;
}
