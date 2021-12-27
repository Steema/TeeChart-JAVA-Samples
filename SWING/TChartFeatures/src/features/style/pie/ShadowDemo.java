/*
 * ShadowDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.pie;
import com.steema.teechart.drawing.Color;
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
public class ShadowDemo extends ChartSamplePanel
    implements ItemListener {

    private Pie pieSeries;
    private JCheckBox shadowButton;


    /**
     * Creates a new instance of ShadowDemo
     */
    public ShadowDemo() {
        super();
        shadowButton.addItemListener(this);
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);

        if (source == shadowButton) {
            pieSeries.getShadow().setVisible(isSelected);
        };
    }

    protected void initComponents() {
        super.initComponents();

        pieSeries = new com.steema.teechart.styles.Pie(chart1.getChart());
        pieSeries.getMarks().setVisible(true);
        pieSeries.getShadow().setVisible(true);
        pieSeries.getShadow().setHorizSize(30);
        pieSeries.getShadow().setVertSize(50);
        pieSeries.getShadow().setColor(Color.SILVER);
        pieSeries.fillSampleValues(9);

        shadowButton = new JCheckBox("Draw Pie Shadow");
        shadowButton.setSelected(pieSeries.getShadow().getVisible());
    }

    protected void initGUI() {
        super.initGUI();
        chart1.getHeader().setVisible(true);
        chart1.setText("Pie Shadow");
        chart1.getAspect().setElevation(315);
        chart1.getAspect().setOrthogonal(false);
        chart1.getAspect().setPerspective(0);
        chart1.getAspect().setRotation(360);

        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(shadowButton);
            tmpPane.add(Box.createHorizontalGlue());
        }

    }

}
