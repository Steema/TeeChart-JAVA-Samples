/*
 * BorderShadowDemo.java
 *
 * <p>Copyright: (c) 2004-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.marks;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.editors.ButtonColor;
import com.steema.teechart.styles.Bar;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class BorderShadowDemo extends ChartSamplePanel
    implements ActionListener, ChangeListener, ItemListener {

    private Bar barSeries;
    private ButtonColor colorButton;

    /** Creates a new instance of BorderShadowDemo */
    public BorderShadowDemo() {
        super();
        shadowButton.addItemListener(this);
        sizeSpinner.addChangeListener(this);
        colorButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if (source == colorButton) {
            barSeries.getMarks().getShadow().setColor(new Color(colorButton.getColor()));
        }
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == shadowButton) {
            barSeries.getMarks().getShadow().setVisible(isSelected);
        }
    }

    public void stateChanged(ChangeEvent e) {
        Object source = e.getSource();
        if (source == sizeSpinner) {
            int size = ((SpinnerNumberModel)((JSpinner)source).getModel()).getNumber().intValue();
            barSeries.getMarks().getShadow().setSize(size);
        }
    }

    protected void initComponents() {
        super.initComponents();

        barSeries = new Bar(chart1.getChart());
        barSeries.getMarks().setArrowLength(20);
        barSeries.getMarks().getShadow().setSize(3);
        barSeries.getMarks().getShadow().setVisible(true);
        barSeries.getMarks().setVisible(true);
        barSeries.fillSampleValues(6);

        colorButton = new ButtonColor("Color", barSeries.getMarks().getShadow().getColor());

        shadowButton = new JCheckBox("Marks Shadow");
        shadowButton.setSelected(barSeries.getMarks().getShadow().getVisible());

        sizeSpinner = new JSpinner(
            new SpinnerNumberModel(
                barSeries.getMarks().getShadow().getVertSize(),
                0,
                20,
                1
            )
        );
        sizeSpinner.setMaximumSize(new Dimension(25, 23));
    }

    protected void initGUI() {
        super.initGUI();
        JLabel tmpLabel;
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(shadowButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(colorButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpLabel = new JLabel("Size:");
            tmpLabel.setDisplayedMnemonic('S');
            tmpLabel.setLabelFor(sizeSpinner);
            tmpPane.add(tmpLabel);
            tmpPane.add(sizeSpinner);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JCheckBox shadowButton;
    private JComboBox positionList;
    private JSpinner sizeSpinner;
}
