/*
 * PageNavigatorDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.components;

import com.steema.teechart.Page;
import com.steema.teechart.editors.AbstractPageNavigator;
import com.steema.teechart.editors.ButtonPageNavigator;
import com.steema.teechart.events.ChangeEvent;
import com.steema.teechart.events.ChangeListener;
import com.steema.teechart.styles.FastLine;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class PageNavigatorDemo extends ChartSamplePanel
    implements ChangeListener {

    private AbstractPageNavigator navigator;

    /**
     * Creates a new instance of PageNavigatorDemo
     */
    public PageNavigatorDemo() {
        super();
    }

    public void stateChanged(ChangeEvent ce) {
        Object source = ce.getSource();
        if (source == chart1.getPage()) {
            updatePageLabel();
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getPage().setMaxPointsPerPage(50);
        chart1.getPage().addChangeListener(this);
    }

    protected void initComponents() {
        super.initComponents();
        navigator = new ButtonPageNavigator(chart1.getChart());

        FastLine series = new FastLine(chart1.getChart());
        series.getMarks().setVisible(false);
        series.fillSampleValues(1000);

        pageLabel = new JLabel();
        updatePageLabel();
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(pageLabel);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(navigator);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    protected void updatePageLabel() {
        Page p = chart1.getPage();
        pageLabel.setText(String.valueOf(p.getCurrent())+ " of "+String.valueOf(p.getCount()));
    }

    private JLabel pageLabel;
}
