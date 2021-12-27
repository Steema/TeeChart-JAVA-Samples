/*
 * LinePointDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.pointer;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.DashStyle;
import com.steema.teechart.editors.ButtonPen;
import com.steema.teechart.editors.ChartEditor;
import com.steema.teechart.styles.MarksStyle;
import com.steema.teechart.tools.MarksTip;
import com.steema.teechart.styles.LinePoint;
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
public class LinePointDemo extends ChartSamplePanel
    implements ActionListener, ItemListener {

    private ButtonPen linesPen;
    private LinePoint series;

    /**
     * Creates a new instance of LinePointDemo
     */
    public LinePointDemo() {
        super();
        editButton.addActionListener(this);
        view3DButton.addItemListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == editButton) {
            ChartEditor.editSeries(series);
        }
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == view3DButton) {
            chart1.getAspect().setView3D(isSelected);
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getLegend().setVisible(false);
        chart1.getHeader().setVisible(true);
        chart1.setText("Line Point Series");
        chart1.getAxes().getBottom().getGrid().setVisible(false);
        chart1.getAxes().getLeft().getGrid().setVisible(false);
        chart1.getAspect().setView3D(false);
    }

    protected void initComponents() {
        super.initComponents();

        series = new LinePoint(chart1.getChart());
        series.fillSampleValues(10);
        series.getLinePen().setColor(Color.BLUE);
        series.getLinePen().setStyle(DashStyle.DASH);

        MarksTip tmpTool = new MarksTip(chart1.getChart());
        tmpTool.setSeries(series);
        tmpTool.setStyle(MarksStyle.LABELVALUE);
        tmpTool.setActive(true);

        linesPen = new ButtonPen(series.getLinePen(), "Lines...");
        editButton = new JButton("Edit...");
        view3DButton = new JCheckBox("3D");
        view3DButton.setSelected(chart1.getAspect().getView3D());
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(linesPen);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(editButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(view3DButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JButton editButton;
    private JCheckBox view3DButton;
}
