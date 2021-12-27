/*
 * FastLineSeries.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.fastline;
import com.steema.teechart.styles.FastLine;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Random;
import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class NullsDemo extends ChartSamplePanel
        implements ItemListener {

    private FastLine lineSeries;

    /** Creates a new instance of FastLineSeries */
    public NullsDemo() {
        super();
        for (int i=0; i < optionButtons.length; i++) {
            optionButtons[i].addItemListener(this);
        }
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);

        if (source == optionButtons[0]) {
            lineSeries.setStairs(isSelected);
            optionButtons[1].setEnabled(lineSeries.getStairs());
        } else if (source == optionButtons[1]) {
            lineSeries.setInvertedStairs(isSelected);
        } else if (source == optionButtons[2]) {
            lineSeries.setIgnoreNulls(isSelected);
        }
    }

    protected void initComponents() {
        super.initComponents();

        Random generator = new Random();
        int tmpRandom;

        lineSeries = new com.steema.teechart.styles.FastLine(chart1.getChart());
        lineSeries.fillSampleValues(50);

        /* set some null points: */
        lineSeries.setNull(14);
        lineSeries.setNull(20);
        lineSeries.setNull(31);
        lineSeries.setNull(39);

        /* allow nulls:
         * (this property is false by default, for speed reasons)
         */
        lineSeries.setIgnoreNulls(false);

        /* Draw in "stairs" mode: */
        lineSeries.setStairs(true);

        optionButtons  = new JCheckBox[3];
        optionButtons[0] = new JCheckBox("Stairs mode");
        optionButtons[1] = new JCheckBox("Inverted stairs");
        optionButtons[2] = new JCheckBox("Ignore Nulls");

        optionButtons[0].setSelected(true);
    }

    protected void initGUI() {
        super.initGUI();

        chart1.getAspect().setView3D(false);
        chart1.getLegend().setVisible(false);
        chart1.getFooter().setVisible(false);
        chart1.getHeader().setVisible(true);
        chart1.setText("Fast-Line series with null points and stairs display.");

        JPanel tmpPane = getButtonPane();
        {
            for (int i=0; i < optionButtons.length; i++) {
                tmpPane.add(optionButtons[i]);
                tmpPane.add(Box.createHorizontalStrut(10));
            }
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JCheckBox[] optionButtons;
}
