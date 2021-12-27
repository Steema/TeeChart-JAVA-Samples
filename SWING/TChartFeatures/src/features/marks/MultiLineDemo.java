/*
 * MultiLineDemo.java
 *
 * <p>Copyright: Copyright (c) 2004-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.marks;

import com.steema.teechart.styles.Bar;
import com.steema.teechart.styles.MarksStyle;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class MultiLineDemo extends ChartSamplePanel
    implements ItemListener {

    private Bar barSeries;

    /** Creates a new instance of MultiLineDemo */
    public MultiLineDemo() {
        super();
        multiLineButton.addItemListener(this);
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == multiLineButton) {
            barSeries.getMarks().setMultiLine(isSelected);
            if (isSelected) {
                barSeries.getLabels().setString(0, HELLO_MULTILINE);
            } else {
                barSeries.getLabels().setString(0, HELLO_SINGLELINE);
            }
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getPanel().setMarginBottom(10);
    }

    protected void initComponents() {
        super.initComponents();
        barSeries = new Bar(chart1.getChart());
        barSeries.add(123, HELLO_MULTILINE);
        barSeries.getMarks().setStyle(MarksStyle.LABELVALUE);
        barSeries.getMarks().setArrowLength(20);
        barSeries.getMarks().setMultiLine(true);

        multiLineButton = new JCheckBox("Marks Multiline");
        multiLineButton.setSelected(barSeries.getMarks().getMultiLine());
    }

    protected void initGUI() {
        super.initGUI();
        JLabel tmpLabel;
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(multiLineButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JCheckBox multiLineButton;

    private final static String HELLO_MULTILINE="Hello\nWorld";
    private final static String HELLO_SINGLELINE="Hello World";
}
