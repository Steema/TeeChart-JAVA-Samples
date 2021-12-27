/*
 * ThemesDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.theme;

import com.steema.teechart.styles.Bar;
import com.steema.teechart.themes.DefaultTheme;
import com.steema.teechart.themes.ExcelTheme;
import com.steema.teechart.themes.ThemesList;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class ThemesDemo extends ChartSamplePanel
    implements ActionListener, ListSelectionListener {

    /** Creates a new instance of ThemesDemo */
    public ThemesDemo() {
        super();
        editButton.addActionListener(this);
        codeButton.addActionListener(this);
        themeList.addListSelectionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == editButton) {
            com.steema.teechart.editors.ThemesEditor.showModal(chart1.getChart());
        } else if (source == codeButton) {
            ThemesList.applyTheme(chart1.getChart(), new ExcelTheme(chart1.getChart()));
        }
    }

    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {
            int index = themeList.getSelectedIndex();
            if (index == -1) {
                ThemesList.applyTheme(chart1.getChart(), new DefaultTheme(chart1.getChart()));
            } else {
                ThemesList.applyTheme(chart1.getChart(), index);
            }
        }
    }

    protected void initChart() {
        super.initChart();
    }

    protected void initComponents() {
        super.initComponents();

        Bar series = new Bar(chart1.getChart());
        series.fillSampleValues();
        series.setColorEach(true);

        DefaultListModel listModel = new DefaultListModel();
        for (int t=0; t < ThemesList.size(); t++) {
            listModel.addElement(ThemesList.getThemeDescription(t));
        }
        themeList = new JList(listModel);
        themeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        themeList.setSelectedIndex(0);
        themeList.setFixedCellWidth(100);
        JScrollPane listScrollPane = new JScrollPane(themeList);
        getBasePane().add(listScrollPane, BorderLayout.WEST);
        editButton = new JButton("Theme Editor...");
        codeButton = new JButton("Apply by code");
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(new JLabel("Select Theme:"));
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(editButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(codeButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JButton editButton, codeButton;
    private JList themeList;
}
