/*
 * StairsDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.area;
import com.steema.teechart.styles.Area;
import com.steema.teechart.styles.MultiAreas;
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
public class StairsDemo extends ChartSamplePanel
    implements ItemListener {

    private Area areaSeries1, areaSeries2;
    private JCheckBox stairsButton;

    /**
     * Creates a new instance of StairsDemo
     */
    public StairsDemo() {
        super();
        stairsButton.addItemListener(this);
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == stairsButton) {
            areaSeries1.setStairs(isSelected);
            areaSeries2.setStairs(isSelected);
        }
    }

    protected void initComponents() {
        super.initComponents();

        areaSeries1 = new Area(chart1.getChart());
        areaSeries2 = new Area(chart1.getChart());

        for (int i=0; i < chart1.getSeriesCount(); i++) {
            Area tmpSeries = (Area)chart1.getSeries(i);
            tmpSeries.getMarks().setVisible(false);
            tmpSeries.fillSampleValues(10);

            tmpSeries.setStairs(true);
            tmpSeries.setMultiArea(MultiAreas.STACKED);
        }

        stairsButton = new JCheckBox("Stairs");
        stairsButton.setSelected(true);
    }

    protected void initGUI() {
        super.initGUI();
        chart1.getAspect().setView3D(false);
        chart1.getLegend().setVisible(false);
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(stairsButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

}
