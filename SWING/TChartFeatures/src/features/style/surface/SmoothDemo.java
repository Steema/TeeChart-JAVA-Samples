/*
 * SmoothDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.surface;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.Surface;
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
public class SmoothDemo extends ChartSamplePanel
    implements ItemListener {

    private Surface series;

    /** Creates a new instance of SmoothDemo */
    public SmoothDemo() {
        super();
        smoothButton.addItemListener(this);
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == smoothButton) {
            series.setSmoothPalette(isSelected);
        }
    }

    protected void initComponents() {
        super.initComponents();
        series = new com.steema.teechart.styles.Surface(chart1.getChart());
        series.fillSampleValues(10);
        series.setSmoothPalette(true);
        series.getSideBrush().setVisible(false);
        series.getSideBrush().setColor(Color.WHITE);
        series.setStartColor(new Color(4210816));

        smoothButton = new JCheckBox("Smooth Palette");
        smoothButton.setSelected(true);
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(smoothButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JCheckBox smoothButton;
}
