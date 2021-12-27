/*
 * LevelsDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.contour;

import com.steema.teechart.styles.Contour;
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
public class LevelsDemo extends ChartSamplePanel
        implements ItemListener {

    private Contour series;
    /** Creates a new instance of LevelsDemo */
    public LevelsDemo() {
        super();
        customButton.addItemListener(this);
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == customButton) {
            series.setAutomaticLevels(!isSelected);
            if (!series.getAutomaticLevels()) {
                setCustomLevels();
            }
        }
    }

    protected void initComponents() {
        super.initComponents();
        series = new com.steema.teechart.styles.Contour(chart1.getChart());
        series.fillSampleValues(20);
        setCustomLevels();

        customButton = new JCheckBox("Custom Levels");
        customButton.setSelected(true);
    }

    protected void initGUI() {
        super.initGUI();
        chart1.getAspect().setView3D(false);
        chart1.getWalls().getBottom().setTransparent(true);
        chart1.getAxes().getDepth().setVisible(true);
        chart1.getAxes().getLeft().setVisible(false);
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(customButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private void setCustomLevels() {
        series.setNumLevels(10);
        series.createAutoLevels();
        series.getLevels().getLevel(0).setUpToValue(-1);
        series.getLevels().getLevel(1).setUpToValue(-0.8);
        series.getLevels().getLevel(2).setUpToValue(-0.6);
        series.getLevels().getLevel(3).setUpToValue(-0.4);
        series.getLevels().getLevel(4).setUpToValue(-0.2);
        series.getLevels().getLevel(5).setUpToValue(0);
        series.getLevels().getLevel(6).setUpToValue(0.2);
        series.getLevels().getLevel(7).setUpToValue(0.4);
        series.getLevels().getLevel(8).setUpToValue(0.6);
        series.getLevels().getLevel(9).setUpToValue(0.8);

    }

    private JCheckBox customButton;
}
