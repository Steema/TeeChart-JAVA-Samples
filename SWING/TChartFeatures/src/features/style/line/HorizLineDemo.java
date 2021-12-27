/*
 * HorizLine.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.line;
import com.steema.teechart.styles.HorizLine;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import features.SamplePanel;

/**
 *
 * @author tom
 */
public class HorizLineDemo extends SamplePanel
    implements ItemListener {

    private HorizLine lineSeries;
    private JCheckBox stairsButton;

    /** Creates a new instance of HorizLine */
    public HorizLineDemo() {
        super();
        stairsButton.addItemListener(this);
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);

        if (source == stairsButton) {
            lineSeries.setStairs(isSelected);
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getAspect().setView3D(false);
        chart1.getLegend().setVisible(false);
    }

    protected void initComponents() {
        super.initComponents();
        lineSeries = new com.steema.teechart.styles.HorizLine(chart1.getChart());
        lineSeries.fillSampleValues(8);
        lineSeries.getPointer().setVisible(true);

        stairsButton = new JCheckBox("Stairs");
    }

    protected void initGUI() {
        super.initGUI();
        myCommander.setVisible(true);

        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(stairsButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }
}
