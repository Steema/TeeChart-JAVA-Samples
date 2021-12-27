/*
 * ExpLabelDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.axes;

import com.steema.teechart.styles.Line;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import features.ChartSamplePanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author tom
 */
public class ExpLabelDemo extends ChartSamplePanel
        implements ItemListener {

    /** Creates a new instance of ExpLabelDemo */
    public ExpLabelDemo() {
        super();
        ExpLabelButton.addItemListener(this);
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == ExpLabelButton) {
            chart1.getAxes().getLeft().getLabels().setExponent(isSelected);
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getAxes().getLeft().getLabels().setValueFormat("0.00E0");
        chart1.getAxes().getLeft().getLabels().setExponent(true);
    }

    protected void initComponents() {
        super.initComponents();
        Line series = new Line(chart1.getChart());
        series.add(      1);
        series.add(     10);
        series.add(    100);
        series.add(   1000);
        series.add(  10000);
        series.add( 100000);
        series.add(1000000);

        ExpLabelButton = new JCheckBox("Exponent super-script font");
        ExpLabelButton.setSelected(chart1.getAxes().getLeft().getLabels().getExponent());
        formatField = new JTextField("0.00E0");

        formatField.getDocument().addDocumentListener( new DocumentListener() {
            public void setValueFormat() {
                try {
                    chart1.getAxes().getLeft().getLabels().setValueFormat(formatField.getText());
                } catch (IllegalArgumentException e) {
                    //do nothing
                }
            }
            public void insertUpdate(DocumentEvent de) {
                setValueFormat();
            }
            public void removeUpdate(DocumentEvent de) {
                setValueFormat();
            }
            public void changedUpdate(DocumentEvent de) {}
        });
    }

    protected void initGUI() {
        super.initGUI();
        JLabel tmpLabel;
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(ExpLabelButton);
            tmpPane.add(Box.createHorizontalStrut(10));
                        tmpLabel = new JLabel("Format:");
            tmpLabel.setDisplayedMnemonic('F');
            tmpLabel.setLabelFor(formatField);
            tmpPane.add(tmpLabel);
            tmpPane.add(formatField);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JCheckBox ExpLabelButton;
    private JTextField formatField;
}
