/*
 * GanttDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.gantt;
import com.steema.teechart.editors.ChartEditor;
import com.steema.teechart.legend.LegendSymbolSize;
import com.steema.teechart.styles.Gantt;
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
public class GanttDemo extends ChartSamplePanel
        implements ActionListener, ItemListener {

    private Gantt ganttSeries;

    /** Creates a new instance of GanttDemo */
    public GanttDemo() {
        super();
        editButton.addActionListener(this);
        view3DButton.addItemListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == editButton) {
            ChartEditor.editSeries(ganttSeries);
        }
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == view3DButton) {
            chart1.getAspect().setView3D(isSelected);
        };
    }

    protected void initChart() {
        super.initChart();
        chart1.getAspect().setView3D(false);
        chart1.getHeader().setVisible(true);
        chart1.setText("Gantt - Scheduling");
        chart1.getLegend().setColorWidth(10);
        chart1.getLegend().getSymbol().setWidth(10);
        chart1.getLegend().getSymbol().setWidthUnits(LegendSymbolSize.PIXELS);
    }

    protected void initComponents() {
        super.initComponents();

        ganttSeries = new com.steema.teechart.styles.Gantt(chart1.getChart());
        ganttSeries.fillSampleValues();

        editButton = new JButton("Edit...");
        view3DButton = new JCheckBox("3D");
        view3DButton.setSelected(chart1.getAspect().getView3D());
    }

    protected void initGUI() {
        super.initGUI();
        chart1.getAspect().setView3D(false);

        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(view3DButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(editButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JButton editButton;
    private JCheckBox view3DButton;
}
