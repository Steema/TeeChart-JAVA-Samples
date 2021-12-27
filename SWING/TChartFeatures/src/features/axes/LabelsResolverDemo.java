/*
 * LabelsResolverDemo.java
 *
 * <p>Copyright: Copyright (c) 2006-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.axes;

import com.steema.teechart.editors.ChartEditor;
import com.steema.teechart.events.ScrollModEventArgs;
import com.steema.teechart.styles.Bar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import features.ChartSamplePanel;
import com.steema.teechart.styles.ISeries;
import com.steema.teechart.axis.AxisLabelResolver;
import com.steema.teechart.axis.Axis;
import com.steema.teechart.axis.NextAxisLabelValue;

/**
 *
 * @author pep
 */

public class LabelsResolverDemo extends ChartSamplePanel implements ActionListener {

    /**
     * Creates a new instance of LabelsResolverDemo
     */
    public LabelsResolverDemo() {
        super();
        editButton.addActionListener(this);

        // Customizing axis labels
        chart1.setAxisLabelResolver(new AxisLabelResolver() {
            public String getLabel(Axis axis, ISeries s, int valueIndex, String labelText) {
                if (axis==chart1.getAxes().getBottom())
                    labelText+= " x";
                return labelText;
            }

            public NextAxisLabelValue getNextLabel(Axis axis, int labelIndex, NextAxisLabelValue nextLabel) {
                return nextLabel;
            }

            public void scrollModHandler(Axis a, ScrollModEventArgs e) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == editButton) {
            ChartEditor.editChart(chart1.getChart());
        }
    }

    protected void initChart() {
        super.initChart();
    }

    protected void initComponents() {
        super.initComponents();
        Bar series = new Bar(chart1.getChart());

        chart1.getAspect().setView3D(false);
        series.fillSampleValues();

        editButton = new JButton("Edit Chart...");
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(editButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JButton editButton;
}
