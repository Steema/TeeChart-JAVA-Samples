/*
 * SelfStackDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.bar;
import com.steema.teechart.styles.Bar;
import com.steema.teechart.styles.MarksStyle;
import com.steema.teechart.styles.MultiBars;
import com.steema.teechart.styles.ValueListOrder;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class SelfStackDemo extends ChartSamplePanel
        implements ItemListener {

    private Bar barSeries;
    private JCheckBox selfStackButton;

    /**
     * Creates a new instance of SelfStackDemo
     */
    public SelfStackDemo() {
        super();
        selfStackButton.addItemListener(this);
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        if (source == selfStackButton) {
            boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
            if (isSelected) {
                barSeries.setMultiBar(MultiBars.SELFSTACK);
            } else {
                barSeries.setMultiBar(MultiBars.NONE);
            }
            barSeries.getMarks().setVisible(!isSelected);
        }
    }

    protected void initComponents() {
        super.initComponents();
        chart1.getHeader().setVisible(true);
        chart1.getHeader().setText("'Self Stacked' bar series");
        //chart1.getAxes().getBottom().getLabels().setStyle(AxisLabelStyle.Text);
        //chart1.getAxes().getBottom().setVisible(true);

        barSeries = new Bar(chart1.getChart());
        barSeries.getMarks().setVisible(false);
        barSeries.getMarks().setStyle(MarksStyle.VALUE);
        barSeries.setMultiBar(MultiBars.SELFSTACK);
        barSeries.setColorEach(true);
        barSeries.getXValues().setOrder(ValueListOrder.ASCENDING);
        barSeries.add(100, "Cars");
        barSeries.add(300, "Phones");
        barSeries.add(200, "Lamps");

        selfStackButton = new JCheckBox("Self stacked");
        selfStackButton.setSelected(true);
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(selfStackButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }
}
