/*
 * ChartSamplePanel.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features;

import com.steema.teechart.Commander;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * A sample panel with a TChart 'chart1' & 'myCommander' instance
 * @author tom
 */
public class SamplePanel extends ChartSamplePanel {
    protected Commander myCommander;

    /** Creates a new instance of ChartSamplePanel */
    public SamplePanel() {
        super();
    }

    public void setCommanderOrientation(boolean vertical) {
        if (vertical) {
            myCommander.setOrientation(SwingConstants.VERTICAL);
            samplePane.add(myCommander, BorderLayout.LINE_START);
        } else {
            myCommander.setOrientation(SwingConstants.HORIZONTAL);
            samplePane.add(myCommander, BorderLayout.PAGE_START);
        }
        samplePane.validate();
    }

    protected void initCommander() {
        myCommander = new Commander(chart1.getChart());
        myCommander.setVisible(true);
    }

    protected void initComponents() {
        super.initComponents();
        initCommander();
    }

    protected void initGUI() {
        super.initGUI();
        samplePane = new JPanel();
        samplePane.setLayout(new BorderLayout());
        samplePane.add(myCommander, BorderLayout.PAGE_START);
        samplePane.add(chart1, BorderLayout.CENTER);
        setSamplePane(samplePane);
    }

    private JPanel samplePane;

    public void setLabelText(boolean show) {
        myCommander.setLabelValues(show);
    }
}
