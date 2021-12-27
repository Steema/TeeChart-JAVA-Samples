/*
 * SidesDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.surface;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.HatchStyle;
import com.steema.teechart.styles.Surface;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class SidesDemo extends ChartSamplePanel
    implements ItemListener {

    private Surface series;

    /** Creates a new instance of SidesDemo */
    public SidesDemo() {
        super();
        sidesButton.addItemListener(this);
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == sidesButton) {
            if (isSelected) {
                series.getSideBrush().setStyle(HatchStyle.DIAGONALCROSS);
                series.getSideBrush().setColor(Color.BLUE);
                series.getSideBrush().setVisible(true);
            } else {
                series.getSideBrush().setVisible(false);
            }
        }
    }

    protected void initComponents() {
        super.initComponents();
        series = new com.steema.teechart.styles.Surface(chart1.getChart());
        series.fillSampleValues(12);
        series.getSideBrush().setStyle(HatchStyle.DIAGONALCROSS);
        series.getSideBrush().setColor(Color.BLUE);
        series.getSideBrush().setVisible(true);

        patternButton = new JButton("Pattern...");
        sidesButton = new JCheckBox("Sides visible");
        sidesButton.setSelected(true);
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(sidesButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(patternButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JButton patternButton;
    private JCheckBox sidesButton;
}
