/*
 * HideCellsDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2011 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.surface;

import com.steema.teechart.styles.Surface;
import com.steema.teechart.tools.Rotate;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import features.ChartSamplePanel;

/**
 *
 * @author chris
 */
public class HideCellsDemo extends ChartSamplePanel
    implements ItemListener {

    private Surface series;

    /** Creates a new instance of HideCellsDemo */
    public HideCellsDemo() {
        super();
        smoothButton.addItemListener(this);
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == smoothButton) {
            series.setHideCells(isSelected);
        }
    }
    
    private Rotate rotate;

    protected void initComponents() {
        super.initComponents();
        series = new com.steema.teechart.styles.Surface(chart1.getChart());
        series.fillSampleValues(10);
        chart1.getAspect().setChart3DPercent(60);
        chart1.getAspect().setElevation(349);
        chart1.getAspect().setOrthogonal(false);
        chart1.getAspect().setPerspective(123);
        chart1.getAspect().setRotation(350);
        chart1.getAspect().setZoom(80);
        chart1.getHeader().setText("");
        series.setHideCells(true);
        
        chart1.getTools().add(rotate = new Rotate());
        rotate.setChart(chart1.getChart());

        smoothButton = new JCheckBox("Hide Cells");
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
