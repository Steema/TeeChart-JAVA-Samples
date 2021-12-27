/*
 * SemiDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.pie;

import com.steema.teechart.styles.MarksStyle;
import com.steema.teechart.styles.Pie;
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
public class SemiDemo extends ChartSamplePanel
        implements ItemListener {

    private Pie pieSeries;
    private JCheckBox semiButton;
    private JCheckBox verticalButton;

    /**
     * Creates a new instance of SemiDemo
     */
    public SemiDemo() {
        super();
        semiButton.addItemListener(this);
        verticalButton.addItemListener(this);
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);

        if (source == semiButton) {
            if (isSelected) {
                pieSeries.setAngleSize(180);
            } else {
                pieSeries.setAngleSize(360);
            }
        } else if (source == verticalButton) {
            if (isSelected) {
                pieSeries.setRotationAngle(90);
            } else {
                pieSeries.setRotationAngle(0);
            }

        };
    }

    protected void initComponents() {
        super.initComponents();

        pieSeries = new com.steema.teechart.styles.Pie(chart1.getChart());
        pieSeries.getMarks().setVisible(true);
        pieSeries.getMarks().setStyle(MarksStyle.LABELPERCENT);
        pieSeries.fillSampleValues(5);
        pieSeries.setAngleSize(180);
        pieSeries.setRotationAngle(90);

        semiButton = new JCheckBox("Semi-pie");
        semiButton.setSelected(true);
        verticalButton = new JCheckBox("Vertical");
        verticalButton.setSelected(true);
    }

    protected void initGUI() {
        super.initGUI();
        chart1.getAspect().setView3D(false);
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(semiButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(verticalButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

}
