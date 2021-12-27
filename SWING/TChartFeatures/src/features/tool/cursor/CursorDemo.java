/*
 * CursorDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.tool.cursor;

import com.steema.teechart.axis.Axis;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.DashStyle;
import com.steema.teechart.editors.ChartEditor;
import com.steema.teechart.styles.FastLine;
import com.steema.teechart.tools.ColorLine;
import com.steema.teechart.tools.CursorTool;
import com.steema.teechart.tools.CursorTool.CursorEvent;
import com.steema.teechart.tools.CursorTool.CursorListener;
import com.steema.teechart.tools.CursorToolStyle;
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
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 *
 * @author tom
 */
public class CursorDemo extends ChartSamplePanel
    implements ActionListener, ItemListener {

    private CursorTool tool1, tool2;

    /**
     * Creates a new instance of CursorDemo
     */
    public CursorDemo() {
        super();
        editButton.addActionListener(this);
        activeButton.addItemListener(this);
        snapButton.addItemListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == editButton) {
            /* show the chart editor dialog */
            ChartEditor.editChart(chart1.getChart());
        }
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);

        if (source == activeButton) {
            /* enable / disable the first cursor */
            tool1.setActive(isSelected);
        } else if (source == snapButton) {
            /* set / unset cursor "Snap" (automatic moving of cursor to points) */
            tool1.setSnap(isSelected);
            /* change the cursor style... */
            tool1.setStyle(CursorToolStyle.VERTICAL);
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getHeader().setVisible(true);
        chart1.setText("Cursors example");
        chart1.getLegend().setVisible(false);
        chart1.getAspect().setView3D(false);
    }

    private final static NumberFormat formatter = new DecimalFormat("#.00");

    protected void initComponents() {
        super.initComponents();

        Axis customAxis = chart1.getAxes().getCustom().getNew();
        customAxis.setHorizontal(false);
        customAxis.setOtherSide(false);
        customAxis.setLabelsOnAxis(false);
        customAxis.setStartPosition(50.0);

        FastLine tmpSeries;
        tmpSeries = new com.steema.teechart.styles.FastLine(chart1.getChart());
        tmpSeries.setColor(Color.GREEN);
        tmpSeries.getLinePen().setColor(Color.GREEN);


        tmpSeries = new com.steema.teechart.styles.FastLine(chart1.getChart());
        tmpSeries.setCustomVertAxis(customAxis);
        tmpSeries.setColor(new Color(Color.ORANGE));
        tmpSeries.getLinePen().setColor(new Color(Color.ORANGE));

        for (int i=0; i < chart1.getSeriesCount(); i++) {
            chart1.getSeries(i).fillSampleValues(100);
            chart1.getSeries(i).getMarks().setVisible(false);
        }

        tool1 = new com.steema.teechart.tools.CursorTool(chart1.getChart());
        tool1.getPen().setColor(Color.RED);
        tool1.setSnap(true);
        tool1.setActive(true);
        tool1.getPen().setStyle(DashStyle.DOT);
        tool1.setSeries(chart1.getSeries(0));

        tool1.addCursorListener( new CursorListener() {
            public void cursorMoved(CursorEvent e) {
                topCursorLabel.setText(
                        formatter.format(e.getXValue())+
                        ';'+
                        formatter.format(e.getYValue()));
            }
        });


        tool2 = new com.steema.teechart.tools.CursorTool(chart1.getChart());
        tool2.getPen().setColor(Color.BLUE);
        tool2.setFollowMouse(true);
        tool2.setSeries(chart1.getSeries(1));

        tool2.addCursorListener( new CursorListener() {
            public void cursorMoved(CursorEvent e) {
                bottomCursorLabel.setText(
                        formatter.format(e.getXValue())+
                        ';'+
                        formatter.format(e.getYValue()));
            }
        });

        ColorLine tmpTool = new com.steema.teechart.tools.ColorLine(chart1.getChart());
        tmpTool.getPen().setColor(Color.TEAL);
        tmpTool.getPen().setWidth(2);
        tmpTool.setAxis(chart1.getAxes().getLeft());

        Axis tmpAxis = chart1.getAxes().getLeft();

        tmpAxis.setAutomaticMinimum(false);
        tmpAxis.setMinimum(40.0);
        tmpAxis.setEndPosition(50.0);

        editButton = new JButton("Edit...");
        activeButton = new JCheckBox("Active");
        activeButton.setSelected(true);
        snapButton = new JCheckBox("Snap");
        snapButton.setSelected(true);
        topCursorLabel = new JLabel();
        bottomCursorLabel = new JLabel();
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(topCursorLabel);
            tmpPane.add(Box.createHorizontalStrut(20));
            tmpPane.add(bottomCursorLabel);
            tmpPane.add(Box.createHorizontalStrut(20));
            tmpPane.add(snapButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(activeButton);
            tmpPane.add(Box.createHorizontalStrut(20));
            tmpPane.add(editButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JButton editButton;
    private JCheckBox activeButton, snapButton;
    private JLabel topCursorLabel, bottomCursorLabel;
}
