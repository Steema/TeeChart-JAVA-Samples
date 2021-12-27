/*
 * SmithDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.smith;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.DashStyle;
import com.steema.teechart.editors.ChartEditor;
import com.steema.teechart.styles.PointerStyle;
import com.steema.teechart.styles.Smith;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class SmithDemo extends ChartSamplePanel
    implements ActionListener, ItemListener {

    private Smith series;

    /** Creates a new instance of SmithDemo */
    public SmithDemo() {
        super();
        circledButton.addItemListener(this);
        editButton.addActionListener(this);
        symbolField.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == editButton) {
            ChartEditor.editSeries(series);
        } else if (source == symbolField) {
            series.setImagSymbol(symbolField.getText());
        }
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == circledButton) {
            series.setCircled(isSelected);
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getAxes().getLeft().getGrid().setStyle(DashStyle.DOT);
        chart1.getAspect().setView3D(false);
    }

    protected void initComponents() {
        super.initComponents();

        series = new Smith(chart1.getChart());
        series.setCircled(true);
        series.setImagSymbol("i");
        series.fillSampleValues(20);

        series.getMarks().setVisible(false);
        series.setShowInLegend(false);
        series.getCCirclePen().setStyle(DashStyle.DOT);
        series.getCCirclePen().setColor(Color.BLUE);
        series.getCCirclePen().setWidth(2);
        series.setCLabels(true);
        series.setRLabels(true);
        series.getPointer().setStyle(PointerStyle.CIRCLE);


        editButton = new JButton("Edit...");
        circledButton = new JCheckBox("Circled");
        circledButton.setSelected(series.getCircled());
        symbolField = new JTextField(series.getImagSymbol());
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(editButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(circledButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            JLabel tmpLabel = new JLabel("Image Symbol:");
            tmpLabel.setDisplayedMnemonic('I');
            tmpLabel.setLabelFor(symbolField);
            tmpPane.add(tmpLabel);
            tmpPane.add(symbolField);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JButton editButton;
    private JCheckBox circledButton;
    private JTextField symbolField;
}
