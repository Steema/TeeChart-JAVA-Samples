/*
 * CustomLabelsDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.axes;

import com.steema.teechart.axis.Axis;
import com.steema.teechart.axis.AxisLabelItem;
import com.steema.teechart.axis.AxisLabelsItems;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.editors.DialogFactory;
import com.steema.teechart.styles.Line;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import features.ChartSamplePanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author tom
 */
public class CustomLabelsDemo extends ChartSamplePanel
    implements ActionListener, ChangeListener, ItemListener {

    /** Creates a new instance of CustomLabelsDemo */
    public CustomLabelsDemo() {
        super();
        editButton.addActionListener(this);
        visibleButton.addItemListener(this);
        labelSpinner.addChangeListener(this);
        positionSpinner.addChangeListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == editButton) {
            int label = labelModel.getNumber().intValue();
            DialogFactory.showModal(
                chart1.getAxes().getLeft().getCustomLabels().getItem(label)
            );
        }
    }

    public void stateChanged(ChangeEvent ce) {
            JSpinner spinner = (JSpinner)ce.getSource();
            int value = ((SpinnerNumberModel)spinner.getModel()).getNumber().intValue();
            if (spinner == labelSpinner) {
               positionSpinner.getModel().setValue(new Double((chart1.getAxes().getLeft().getCustomLabels().getItem(value)).getValue()));
            } else if (spinner == positionSpinner) {
                chart1.getAxes().getLeft().getCustomLabels().getItem(
                    labelModel.getNumber().intValue()).setValue(value);
            }
        }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == visibleButton) {
            if (isSelected) {
                addCustomLabels();
            } else {
                chart1.getAxes().getLeft().getCustomLabels().clear();
            }
        }
    }

    protected void addCustomLabels() {
        Axis axis = chart1.getAxes().getLeft();
        AxisLabelsItems items = axis.getCustomLabels();
        //remove all custom labels
        items.clear();
        //add custom labels
        AxisLabelItem item;
        item = items.add(123.0, "Hello");
        item.getFont().setSize(16);
        item = items.add(466.0, "Good\nBye");
        item.setTransparent(false);
        items.add(300);
        item = items.add(-100);
        item.setTransparent(false);
        item.setTransparency(50);
        item.setColor(Color.BLUE);
    }

    protected void initChart() {
        super.initChart();
    }

    protected void initComponents() {
        super.initComponents();
        Line series = new Line(chart1.getChart());
        series.add(new int[]{200,0,123,300,260,-100,650,400});
        addCustomLabels();

        editButton = new JButton("Edit...");
        visibleButton = new JCheckBox("Custom labels");
        visibleButton.setSelected(chart1.getAxes().getLeft().getCustomLabels().size()>0);

        labelModel = new SpinnerNumberModel(
                0,
                0,
                chart1.getAxes().getLeft().getCustomLabels().size()-1,
                1
            );
        labelSpinner = new JSpinner(labelModel);

        positionSpinner = new JSpinner(
            new SpinnerNumberModel(
                0,
                -10000,
                +10000,
                chart1.getAxes().getLeft().getCustomLabels().getItem(
                    labelModel.getNumber().intValue()).getValue()
            )
        );

        syncDemoControls();
    }

    protected void initGUI() {
        super.initGUI();
        JLabel tmpLabel;
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(visibleButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpLabel = new JLabel("Label:");
            tmpLabel.setDisplayedMnemonic('L');
            tmpPane.add(tmpLabel);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(labelSpinner);
            tmpPane.add(positionSpinner);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(editButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private void syncDemoControls() {
        boolean customLabels = chart1.getAxes().getLeft().getCustomLabels().size() > 0;
        labelSpinner.setEnabled(customLabels);
        if (labelSpinner.isEnabled()) {
            labelModel.setMaximum(new Integer(chart1.getAxes().getLeft().getLabels().getItems().size()-1));
        }
        positionSpinner.setEnabled(customLabels);
        editButton.setEnabled(customLabels);
    }

    private SpinnerNumberModel labelModel;
    private JButton editButton;
    private JCheckBox visibleButton;
    private JSpinner labelSpinner, positionSpinner;
}
