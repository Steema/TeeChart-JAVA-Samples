/*
 * MarksDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.colorgrid;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.editors.ChartEditor;
import com.steema.teechart.styles.ColorGrid;
import com.steema.teechart.styles.MarksStyle;
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
public class MarksDemo extends ChartSamplePanel
    implements ActionListener, ItemListener {

    private ColorGrid series;

    /** Creates a new instance of MarksDemo */
    public MarksDemo() {
        super();
        editButton.addActionListener(this);
        viewButton.addItemListener(this);
        centeredButton.addItemListener(this);
    }

     public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == editButton) {
            ChartEditor.editSeriesMarks(series);
            viewButton.setSelected(series.getMarks().getVisible());
        };
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == viewButton) {
            series.getMarks().setVisible(isSelected);
        } else if (source == centeredButton) {
            series.setCenteredPoints(isSelected);
        }
    }

    protected void initComponents() {
        super.initComponents();

        series = new ColorGrid(chart1.getChart());
        series.fillSampleValues(5);
        series.setCenteredPoints(true);
        series.getMarks().setStyle(MarksStyle.VALUE);
        series.getMarks().setVisible(true);
        series.getMarks().getShadow().setTransparency(70);
        series.getMarks().getShadow().setColor(Color.BLACK);
        series.getMarks().getShadow().setSize(3);

        editButton = new JButton("Edit Marks...");
        viewButton = new JCheckBox("View Marks");
        viewButton.setSelected(series.getMarks().getVisible());
        centeredButton = new JCheckBox("Centered Mode");
        centeredButton.setSelected(series.getCenteredPoints());
    }

    protected void initGUI() {
        super.initGUI();
        chart1.getAspect().setView3D(false);
        chart1.getHeader().setVisible(true);
        chart1.setText("ColorGrid Marks");
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(viewButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(editButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(centeredButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JButton editButton;
    private JCheckBox viewButton, centeredButton;
}
