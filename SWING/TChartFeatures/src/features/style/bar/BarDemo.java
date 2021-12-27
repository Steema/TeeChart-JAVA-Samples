/*
 * BarDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.bar;

import com.steema.teechart.editors.ChartEditor;
import com.steema.teechart.styles.Bar;
import com.steema.teechart.styles.BarStyle;
import com.steema.teechart.styles.MultiBars;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import features.ChartSamplePanel;
import features.utils.EnumStrings;

/**
 *
 * @author tom
 */
public class BarDemo extends ChartSamplePanel
        implements ActionListener {

    /**
     * Creates a new instance of BarDemo
     */
    public BarDemo() {
        super();
        editButton.addActionListener(this);
        layoutList.addActionListener(this);
        barStyleList.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        Bar tmpSeries = (Bar)chart1.getSeries(0);

        if (source == layoutList) {
            MultiBars layout = MultiBars.NONE;
            switch (layoutList.getSelectedIndex()) {
                case 0: layout = MultiBars.NONE; break;
                case 1: layout = MultiBars.SIDE; break;
                case 2: layout = MultiBars.STACKED; break;
                case 3: layout = MultiBars.STACKED100; break;
                case 4: layout = MultiBars.SIDEALL; break;
                case 5: layout = MultiBars.SELFSTACK; break;
            }

            boolean tmpVisible = (layout != MultiBars.STACKED)
            && (layout != MultiBars.STACKED100)
            && (layout != MultiBars.SELFSTACK);

            tmpSeries.setMultiBar(layout);
            for (int i=0; i < chart1.getSeriesCount(); i++) {
                chart1.getSeries(i).getMarks().setVisible(tmpVisible);
            }
        } else if (source == barStyleList) {
            BarStyle barStyle = BarStyle.RECTANGLE;
            switch (barStyleList.getSelectedIndex()) {
                case 0: barStyle = BarStyle.RECTANGLE; break;
                case 1: barStyle = BarStyle.PYRAMID; break;
                case 2: barStyle = BarStyle.INVPYRAMID; break;
                case 3: barStyle = BarStyle.CYLINDER; break;
                case 4: barStyle = BarStyle.ELLIPSE; break;
                case 5: barStyle = BarStyle.ARROW; break;
                case 6: barStyle = BarStyle.RECTGRADIENT; break;
                case 7: barStyle = BarStyle.CONE; break;
            }

            tmpSeries.setBarStyle(barStyle);
        } else if (source == editButton) {
            ChartEditor.editSeries(chart1.getSeries(0));
        }
    }

    protected void initComponents() {
        super.initComponents();

        Bar barSeries = null;
        for (int i=0; i < 3; i++) {
            barSeries = new Bar(chart1.getChart());
            barSeries.setBarStyle(BarStyle.RECTANGLE);
            barSeries.setMultiBar(MultiBars.NONE);
            barSeries.fillSampleValues(6);
        }

        editButton = new JButton("Edit...");

        layoutList = new JComboBox(EnumStrings.SERIES_LAYOUTS);
        layoutList.setSelectedIndex(0);

        barStyleList = new JComboBox(EnumStrings.BAR_STYLES);
        barStyleList.setSelectedIndex(0);

    }

    protected void initGUI() {
        super.initGUI();
        chart1.getHeader().setVisible(true);
        chart1.setText( "Bar" );

        JLabel tmpLabel;
        JPanel tmpPane = getButtonPane();
        {
            tmpLabel = new JLabel("Layout:");
            tmpLabel.setDisplayedMnemonic('L');
            tmpLabel.setLabelFor(layoutList);
            tmpPane.add(tmpLabel);
            tmpPane.add(layoutList);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpLabel = new JLabel("Style Bar 1:");
            tmpLabel.setDisplayedMnemonic('S');
            tmpLabel.setLabelFor(barStyleList);
            tmpPane.add(tmpLabel);
            tmpPane.add(barStyleList);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(editButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JButton editButton;
    private JComboBox barStyleList;
    private JComboBox layoutList;
}
