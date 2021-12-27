/*
 * SideAllDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.bar;
import com.steema.teechart.styles.Bar;
import com.steema.teechart.styles.MultiBars;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class SideAllDemo extends ChartSamplePanel
        implements ActionListener {

    private Bar bar1Series, bar2Series;

    /**
     * Creates a new instance of SideAllDemo
     */
    public SideAllDemo() {
        super();
        sideAllButton.addActionListener(this);
        sideToSideButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == sideAllButton) {
            bar1Series.setMultiBar(MultiBars.SIDEALL);
        } else if (source == sideToSideButton) {
            bar1Series.setMultiBar(MultiBars.SIDE);
        }
    }

    protected void initComponents() {
        super.initComponents();
        bar1Series = new com.steema.teechart.styles.Bar(chart1.getChart());
        bar2Series = new com.steema.teechart.styles.Bar(chart1.getChart());
        bar1Series.fillSampleValues(5);
        bar2Series.fillSampleValues(5);
        bar1Series.setMultiBar(MultiBars.SIDEALL);
        sideAllButton = new JRadioButton("Side All");
        sideAllButton.setSelected(true);
        sideToSideButton = new JRadioButton("Side to Side");
        ButtonGroup group = new ButtonGroup();
        group.add(sideAllButton);
        group.add(sideToSideButton);
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(sideAllButton);
            tmpPane.add(sideToSideButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JRadioButton sideAllButton, sideToSideButton;
}
