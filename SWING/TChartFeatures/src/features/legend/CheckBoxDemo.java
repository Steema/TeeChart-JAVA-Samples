/*
 * CheckBoxDemo.java
 *
 * <p>Copyright: (c) 2004-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.legend;

import com.steema.teechart.styles.Area;
import com.steema.teechart.styles.Bar;
import com.steema.teechart.styles.Bubble;
import com.steema.teechart.styles.ErrorBar;
import com.steema.teechart.styles.FastLine;
import com.steema.teechart.styles.HighLow;
import com.steema.teechart.styles.Histogram;
import com.steema.teechart.styles.Line;
import com.steema.teechart.styles.Points;
import com.steema.teechart.styles.Volume;
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
public class CheckBoxDemo extends ChartSamplePanel
    implements ItemListener {

    /** Creates a new instance of CheckBoxDemo */
    public CheckBoxDemo() {
        super();
        showCheckBoxButton.addItemListener(this);
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == showCheckBoxButton) {
            chart1.getLegend().setCheckBoxes(isSelected);
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getAspect().setChart3DPercent(10);
        chart1.getAspect().setElevation(352);
        chart1.getAspect().setOrthogonal(false);
        chart1.getAspect().setPerspective(55);
        chart1.getAspect().setRotation(342);
        chart1.getLegend().setCheckBoxes(true);
    }

    protected void initComponents() {
        super.initComponents();

        new Histogram(chart1.getChart());
        new Bar(chart1.getChart());
        new Area(chart1.getChart());
        new Points(chart1.getChart());
        new FastLine(chart1.getChart());
        new Bubble(chart1.getChart());
        new Volume(chart1.getChart());
        new Line(chart1.getChart());
        new ErrorBar(chart1.getChart());
        new HighLow(chart1.getChart());

        chart1.getSeries().fillSampleValues();

        showCheckBoxButton = new JCheckBox("Legend Checkboxes");
        showCheckBoxButton.setSelected(chart1.getLegend().getCheckBoxes());
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(showCheckBoxButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JCheckBox showCheckBoxButton;
}
