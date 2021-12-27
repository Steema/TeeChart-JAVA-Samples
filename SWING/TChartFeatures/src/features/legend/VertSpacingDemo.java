/*
 * VertSpacingDemo.java
 *
 * <p>Copyright: Copyright (c) 2004-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.legend;

import com.steema.teechart.styles.Bar3D;
import javax.swing.Box;
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
public class VertSpacingDemo extends ChartSamplePanel
    implements ChangeListener {

    /** Creates a new instance of VertSpacingDemo */
    public VertSpacingDemo() {
        super();
        spaceSpinner.addChangeListener(this);
    }

    public void stateChanged(ChangeEvent e) {
        Object source = e.getSource();
        if (source == spaceSpinner) {
            int space = ((SpinnerNumberModel)spaceSpinner.getModel()).getNumber().intValue();
            chart1.getLegend().setVertSpacing(space);
        }
    }

    protected void initChart() {
        super.initChart();
    }

    protected void initComponents() {
        super.initComponents();

        new Bar3D(chart1.getChart()).fillSampleValues(5);

        spaceSpinner = new JSpinner(
            new SpinnerNumberModel(
                chart1.getLegend().getVertSpacing(),
                0,
                30,
                1
            )
        );
    }

    protected void initGUI() {
        super.initGUI();
        JLabel tmpLabel;
        JPanel tmpPane = getButtonPane();
        {
            tmpLabel = new JLabel("Vertical spacing: ");
            tmpLabel.setDisplayedMnemonic('V');
            tmpLabel.setLabelFor(spaceSpinner);
            tmpPane.add(tmpLabel);
            tmpPane.add(spaceSpinner);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JSpinner spaceSpinner;
}
