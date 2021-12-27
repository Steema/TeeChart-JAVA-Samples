/*
 * RuntimeDemo.java
 *
 * <p>Copyright: Copyright (c) 2004-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.components.charteditor;

import com.steema.teechart.editors.ChartEditor;
import com.steema.teechart.styles.Line;
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
public class RuntimeDemo extends ChartSamplePanel
    implements ActionListener {

    /** Creates a new instance of RuntimeDemo */
    public RuntimeDemo() {
        super();
        editButton.addActionListener(this);
    }

   public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if (source == editButton) {
            ChartEditor.editChart(chart1.getChart(), "of ChartEditor component");
        }
    }

    protected void initChart() {
        super.initChart();
    }

    protected void initComponents() {
        super.initComponents();

        Line tmpSeries;
        tmpSeries =  new com.steema.teechart.styles.Line(chart1.getChart());
        {
            tmpSeries.fillSampleValues(10);
        }
        editButton = new JButton("Edit...");
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(editButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JButton editButton;
}
