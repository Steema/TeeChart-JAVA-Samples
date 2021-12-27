/*
 * PVODemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.function;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.editors.ChartEditor;
import com.steema.teechart.functions.Function;
import com.steema.teechart.styles.FastLine;
import com.steema.teechart.styles.MarksStyle;
import com.steema.teechart.styles.Volume;
import com.steema.teechart.tools.MarksTip;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;

import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class PVODemo extends ChartSamplePanel
    implements ActionListener {

    private Volume sourceSeries;
    private Function pvoFunction;
    private FastLine functionSeries;

    /**
     * Creates a new instance of PVODemo
     */
    public PVODemo() {
        super();
        editButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == editButton) {
            ChartEditor.editSeriesDatasource(functionSeries);
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getLegend().setVisible(false);
    }

    protected void initComponents() {
        super.initComponents();

        sourceSeries = new com.steema.teechart.styles.Volume(chart1.getChart());
        sourceSeries.getMarks().setVisible(false);
        sourceSeries.getPointer().setVisible(false);
        sourceSeries.setTitle("Source");
        sourceSeries.setColor(Color.RED);
        sourceSeries.fillSampleValues(100);

        pvoFunction = new com.steema.teechart.functions.PVO(chart1.getChart());
        pvoFunction.setPeriod(12);

        functionSeries = new com.steema.teechart.styles.FastLine(chart1.getChart());
        functionSeries.setDataSource(sourceSeries);
        functionSeries.setFunction(pvoFunction);

        functionSeries.setTitle("PVO");
        functionSeries.setColor(Color.YELLOW);
        functionSeries.getLinePen().setColor(Color.YELLOW);
        functionSeries.getLinePen().setWidth(2);
        functionSeries.getMarks().setVisible(false);

        MarksTip tmpTool = new MarksTip(chart1.getChart());
        tmpTool.setSeries(sourceSeries);
        tmpTool.setStyle(MarksStyle.XY);

        editButton = new JButton("Edit...");
    }

    protected void initGUI() {
        super.initGUI();
        chart1.getHeader().setVisible(true);
        chart1.setText("Percentage Volume Oscillator function");
        chart1.getAspect().setView3D(false);

        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(editButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JButton editButton;
}
