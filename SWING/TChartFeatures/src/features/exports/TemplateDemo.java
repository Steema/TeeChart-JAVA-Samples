/*
 * TemplateDemo.java
 *
 * <p>Copyright: Copyright (c) 2006-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.exports;

import com.steema.teechart.TChart;
import com.steema.teechart.editors.ChartEditor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class TemplateDemo extends ChartSamplePanel
        implements ActionListener {

    private TChart copyChart;

    /**
     * Creates a new instance of TemplateDemo
     */
    public TemplateDemo() {
        super();
        editButton.addActionListener(this);
        runButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if (source == editButton) {
            ChartEditor.editChart(chart1.getChart());
            chart1.getSeries().fillSampleValues();

        } else if (source == runButton) {
            try {
                //( 1) Save the template into a Stream...
                ByteArrayOutputStream m = new ByteArrayOutputStream();
                chart1.getExport().getTemplate().toStream(m);
                //( 2) Load the template into other Chart...
                copyChart.setChart(copyChart.getImport().getTemplate().fromStream(
                        new ByteArrayInputStream(m.toByteArray())
                ));
                //( 3) repaint the Chart
                copyChart.repaint();
            } catch (IOException ex) {
                System.out.println(ex);
            } catch (ClassNotFoundException ex) {
                System.out.println(ex);
            }
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getHeader().setText("Template");
        chart1.getHeader().setVisible(true);
        copyChart = new TChart();
    }

    protected void initComponents() {
        super.initComponents();

        editButton = new JButton("Edit Template...");
        runButton = new JButton("Run the example");
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(editButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(runButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
        JSplitPane tmpSplitPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                chart1,
                copyChart
                
                );
        tmpSplitPane.setDividerLocation(100);
        setSamplePane(tmpSplitPane);
    }

    private JButton runButton, editButton;
}
