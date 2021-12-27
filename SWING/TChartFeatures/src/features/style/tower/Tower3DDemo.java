/*
 * Tower3DDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.tower;

import com.steema.teechart.editors.ChartEditor;
import com.steema.teechart.styles.PaletteStyle;
import com.steema.teechart.styles.Tower;
import com.steema.teechart.styles.TowerStyle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Random;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import features.ChartSamplePanel;
import features.utils.EnumStrings;

/**
 *
 * @author tom
 */
public class Tower3DDemo extends ChartSamplePanel
    implements ActionListener, ItemListener {

    private Tower series;

    /** Creates a new instance of Tower3DDemo */
    public Tower3DDemo() {
        super();
        editButton.addActionListener(this);
        styleList.addActionListener(this);
        useOriginButton.addItemListener(this);
    }

     public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == editButton) {
            ChartEditor.editSeries(series);
        } else if (source == styleList) {
            switch (styleList.getSelectedIndex()) {
                case 0: series.setTowerStyle(TowerStyle.CUBE); break;
                case 1: series.setTowerStyle(TowerStyle.RECTANGLE); break;
                case 2: series.setTowerStyle(TowerStyle.COVER); break;
                case 3: series.setTowerStyle(TowerStyle.CYLINDER); break;
                case 4: series.setTowerStyle(TowerStyle.ARROW); break;
                case 5: series.setTowerStyle(TowerStyle.CONE); break;
            }
        }
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == useOriginButton) {
            series.setUseOrigin(isSelected);
        }
    }

    protected void initComponents() {
        super.initComponents();

        series = new Tower(chart1.getChart());
        series.setOrigin(500.0);
        series.setUseOrigin(true);
        series.setPaletteStyle(PaletteStyle.STRONG);
        series.setUseColorRange(false);
        series.setUsePalette(true);
        series.clear();
        Random generator = new Random();
        for (int x=1; x <= 10; x++) {
            for (int z=1; z <= 10; z++) {
                series.add(x, generator.nextInt(1000)-100, z);
            }
        }

        editButton = new JButton("Edit...");
        useOriginButton = new JCheckBox("Use origin");
        useOriginButton.setSelected(series.getUseOrigin());

        styleList = new JComboBox(EnumStrings.TOWER_STYLES);
        styleList.setSelectedIndex(0);
    }

    protected void initGUI() {
        super.initGUI();
        chart1.getHeader().setVisible(true);
        chart1.setText("Tower 3D Series");
        chart1.getLegend().setVisible(false);
        chart1.getAspect().setChart3DPercent(55);
        chart1.getAxes().getDepth().setVisible(true);
        chart1.getAspect().setPerspective(100);

        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(editButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            JLabel tmpLabel = new JLabel("Style:");
            tmpLabel.setDisplayedMnemonic('S');
            tmpLabel.setLabelFor(styleList);
            tmpPane.add(tmpLabel);
            tmpPane.add(styleList);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(useOriginButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JButton editButton;
    private JCheckBox useOriginButton;
    private JComboBox styleList;
}
