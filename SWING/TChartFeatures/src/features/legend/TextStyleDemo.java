/*
 * TextStyleDemo.java
 *
 * <p>Copyright: Copyright (c) 2004-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.legend;

import com.steema.teechart.legend.LegendTextStyle;
import com.steema.teechart.styles.Pie;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class TextStyleDemo extends ChartSamplePanel
    implements ActionListener {

    /** Creates a new instance of TextStyleDemo */
    public TextStyleDemo() {
        super();
        styleList.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if (source == styleList) {
            switch (styleList.getSelectedIndex()) {
                case 0: chart1.getLegend().setTextStyle(LegendTextStyle.PLAIN); break;
                case 1: chart1.getLegend().setTextStyle(LegendTextStyle.LEFTVALUE); break;
                case 2: chart1.getLegend().setTextStyle(LegendTextStyle.RIGHTVALUE); break;
                case 3: chart1.getLegend().setTextStyle(LegendTextStyle.LEFTPERCENT); break;
                case 4: chart1.getLegend().setTextStyle(LegendTextStyle.RIGHTPERCENT); break;
                case 5: chart1.getLegend().setTextStyle(LegendTextStyle.XVALUE); break;
                case 6: chart1.getLegend().setTextStyle(LegendTextStyle.VALUE); break;
                case 7: chart1.getLegend().setTextStyle(LegendTextStyle.PERCENT); break;
                case 8: chart1.getLegend().setTextStyle(LegendTextStyle.XANDVALUE); break;
                case 9: chart1.getLegend().setTextStyle(LegendTextStyle.XANDPERCENT);
            }
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getLegend().setTextStyle(LegendTextStyle.PERCENT);
    }

    protected void initComponents() {
        super.initComponents();

        new Pie(chart1.getChart()).fillSampleValues(5);

        styleList = new JComboBox(
                new String[]{
                    "Plain",
                    "Left Value",
                    "Right Value",
                    "Left Percent",
                    "Right Percent",
                    "X Value",
                    "Value",
                    "Percent",
                    "X and Value",
                    "X and Percent"
                }
        );

        styleList.setPreferredSize(new Dimension(100, 20));
        styleList.setSelectedIndex(chart1.getLegend().getTextStyle().getValue());
    }

    protected void initGUI() {
        super.initGUI();
        JLabel tmpLabel;
        JPanel tmpPane = getButtonPane();
        {
            tmpLabel = new JLabel("Legend styles:");
            tmpLabel.setDisplayedMnemonic('s');
            tmpLabel.setLabelFor(styleList);
            tmpPane.add(tmpLabel);
            tmpPane.add(styleList);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JComboBox styleList;
}
