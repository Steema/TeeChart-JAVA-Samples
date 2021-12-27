/*
 * StackDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.line;

import com.steema.teechart.styles.CustomStack;
import com.steema.teechart.styles.Line;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import features.ChartSamplePanel;
import features.utils.EnumStrings;

/**
 *
 * @author tom
 */
public class StackDemo extends ChartSamplePanel
        implements ActionListener {

    private JComboBox stackList;

    /**
     * Creates a new instance of StackDemo
     */
    public StackDemo() {
        super();
        stackList.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == stackList) {
            CustomStack mode = CustomStack.NONE;
            switch (stackList.getSelectedIndex()) {
                case 0: mode = CustomStack.NONE; break;
                case 1: mode = CustomStack.OVERLAP; break;
                case 2: mode = CustomStack.STACK; break;
                case 3: mode = CustomStack.STACK100; break;
            }

            ((Line)chart1.getSeries(0)).setStacked(mode);
        }
    }

    protected void initComponents() {
        super.initComponents();
        Line lineSeries = null;
        for (int i=0; i < 4; i++) {
            lineSeries = new Line(chart1.getChart());
            lineSeries.setStacked(CustomStack.NONE);
            lineSeries.fillSampleValues(10);
        }

        stackList = new JComboBox(EnumStrings.STACK_STYLES);
        stackList.setSelectedIndex(0);
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            JLabel tmpLabel;
            tmpLabel = new JLabel("Mode:");
            tmpLabel.setDisplayedMnemonic('M');
            tmpLabel.setLabelFor(stackList);
            tmpPane.add(tmpLabel);
            tmpPane.add(stackList);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }
}
