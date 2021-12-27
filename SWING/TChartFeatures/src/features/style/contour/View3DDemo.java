/*
 * View3DDemo.java
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
public class View3DDemo extends ChartSamplePanel
    implements ItemListener {

    /** Creates a new instance of View3DDemo */
    public View3DDemo() {
        super();
        view3DButton.addItemListener(this);
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == view3DButton) {
            chart1.getAspect().setView3D(isSelected);
        }
    }

    protected void initComponents() {
        super.initComponents();
        Contour series = new com.steema.teechart.styles.Contour(chart1.getChart());
        series.fillSampleValues(20);
        series.setYPosition(0.19);

        view3DButton = new JCheckBox("View 3D");
    }

    protected void initGUI() {
        super.initGUI();
        chart1.getAspect().setView3D(false);
        chart1.getAspect().setChart3DPercent(75);
        chart1.getAspect().setElevation(310);
        chart1.getAspect().setOrthogonal(false);
        chart1.getAspect().setRotation(354);
        chart1.getAspect().setZoom(70);
        chart1.getAxes().getDepth().setVisible(true);
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(view3DButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JCheckBox view3DButton;
}
