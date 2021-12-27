/*
 * AxisTitleDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.axes;

import com.steema.teechart.axis.AxisTitle;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.Points;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class AxisTitleDemo extends ChartSamplePanel
        implements ActionListener, ItemListener {

    /** Creates a new instance of AxisTitleDemo */
    public AxisTitleDemo() {
        super();
        visibleButton.addItemListener(this);
        titleField.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == titleField) {
            chart1.getAxes().getLeft().getTitle().setCaption(titleField.getText());
        }
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == visibleButton) {
            chart1.getAxes().getLeft().getTitle().setVisible(isSelected);
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getAspect().setView3D(false);
        AxisTitle title = chart1.getAxes().getLeft().getTitle();
        title.setCaption("Temperature");
        title.getFont().setSize(15);
        title.getFont().setBold(true);
        title.getFont().getShadow().setColor(Color.WHITE);
        title.getFont().getShadow().setSize(1);
    }

    protected void initComponents() {
        super.initComponents();
        Points series = new Points(chart1.getChart());
        series.fillSampleValues(15);
        visibleButton = new JCheckBox("Visible Axis title");
        visibleButton.setSelected(chart1.getAxes().getLeft().getTitle().getVisible());
        titleField = new JTextField(chart1.getAxes().getLeft().getTitle().getCaption());
    }

    protected void initGUI() {
        super.initGUI();
        JLabel tmpLabel;
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(visibleButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpLabel = new JLabel("Text:");
            tmpLabel.setDisplayedMnemonic('T');
            tmpLabel.setLabelFor(titleField);
            tmpPane.add(titleField);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JCheckBox visibleButton;
    private JTextField titleField;
}
