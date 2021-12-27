/*
 * ShadowDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.panel;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.editors.DialogFactory;
import com.steema.teechart.styles.Surface;
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
public class ShadowDemo extends ChartSamplePanel
        implements ActionListener, ItemListener {

    private Surface series;

    /**
     * Creates a new instance of ShadowDemo
     */
    public ShadowDemo() {
        super();
        editButton.addActionListener(this);
        shadowButton.addItemListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == editButton) {
            DialogFactory.showModal(chart1.getPanel().getShadow());
        }
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == shadowButton) {
            chart1.getPanel().getShadow().setVisible(isSelected);
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getHeader().setVisible(true);
        chart1.getHeader().setText("Chart Panel Shadow");
        /* set panel shadow properties */
        chart1.getPanel().getShadow().setSize(6);
        chart1.getPanel().getShadow().setColor(Color.DARK_GRAY);
        chart1.getPanel().getShadow().setVisible(true);
    }

    protected void initComponents() {
        super.initComponents();
        editButton = new JButton("Edit...");
        shadowButton = new JCheckBox("Show Shadow");
        shadowButton.setSelected(chart1.getPanel().getShadow().getVisible());
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {   tmpPane.add(shadowButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(editButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JButton editButton;
    private JCheckBox shadowButton;
}
