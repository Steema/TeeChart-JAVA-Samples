/*
 * SubHeaderFooterDemo.java
 *
 * <p>Copyright: Copyright (c) 2004-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.headfoot;

import com.steema.teechart.Footer;
import com.steema.teechart.Header;
import com.steema.teechart.drawing.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class SubHeaderFooterDemo extends ChartSamplePanel
    implements ItemListener {

    /**
     * Creates a new instance of SubHeaderFooterDemo
     */
    public SubHeaderFooterDemo() {
        super();
        showSubHeaderButton.addItemListener(this);
        showSubFooterButton.addItemListener(this);
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == showSubHeaderButton) {
            chart1.getSubHeader().setVisible(isSelected);
        } else if (source == showSubFooterButton) {
            chart1.getSubFooter().setVisible(isSelected);
        }
    }

    protected void initChart() {
        super.initChart();

        Header header;
        Footer footer;

        header = chart1.getSubHeader();
        header.setVisible(true);
        header.setText("Hello");
        header.getFont().setColor(Color.GREEN);
        header.getFont().setSize(12);
        header.getFont().setBold(true);
        header.getGradient().setVisible(true);
        header.setTransparent(false);

        footer = chart1.getSubFooter();
        footer.setVisible(true);
        footer.setText("World!");

        header = chart1.getHeader();
        header.setVisible(true);
        header.setText("TChart");

        footer = chart1.getFooter();
        footer.setVisible(true);
        footer.setText("This is the Chart Foot");
        footer.getFont().setColor(Color.YELLOW);
        footer.getFont().setSize(13);
        footer.getFont().setBold(true);
    }

    protected void initComponents() {
        super.initComponents();
        showSubHeaderButton = new JCheckBox("Show SubHeader");
        showSubHeaderButton.setSelected(chart1.getSubHeader().getVisible());
        showSubFooterButton = new JCheckBox("Show SubFooter");
        showSubFooterButton.setSelected(chart1.getSubFooter().getVisible());
    }

    protected void initGUI() {
        super.initGUI();
        JLabel tmpLabel;
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(showSubHeaderButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(showSubFooterButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JCheckBox showSubHeaderButton,  showSubFooterButton;
}
