/*
 * WorldDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2011 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.world;

import com.steema.teechart.styles.World;
import com.steema.teechart.styles.WorldMapType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import features.ChartSamplePanel;

/**
 *
 * @author chris
 */
public class WorldDemo extends ChartSamplePanel
    implements ActionListener {

    private World series;

    /** Creates a new instance of WorldDemo */
    public WorldDemo() {
        super();
        colorList.addActionListener(this);
        showAxes.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == colorList) {
            try {
                series.setMap(WorldMapType.fromInt(colorList.getSelectedIndex()));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(WorldDemo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(WorldDemo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(WorldDemo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(WorldDemo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(source == showAxes) {
            chart1.getAxes().setVisible(showAxes.isSelected());
        }
    }

    protected void initComponents() {
        super.initComponents();
        chart1.getAspect().setView3D(false);
        chart1.getLegend().setVisible(false);
        series = new com.steema.teechart.styles.World(chart1.getChart());
        series.setUseColorRange(false);
        series.setUsePalette(false);
        series.getShadow().setVisible(false);
        chart1.getAxes().setVisible(false);

        colorList = new JComboBox(new String[] {
            "World",
            "Africa",
            "Asia",
            "Australia",
            "CentralAmerica",
            "Europe",
            "Europe (EU 15)",
            "Europe (EU 27)",
            "Spain",
            "Middle East",
            "North America",
            "South America",
            "USA",
            "USA with Hawaii and Alaska"}
        );
        
        colorList.setSelectedIndex(5);
        
        showAxes = new JCheckBox("Show Axes"); 
    }

    protected void initGUI() {
        super.initGUI();
        JLabel tmpLabel;
        JPanel tmpPane = getButtonPane();
        {
            tmpLabel = new JLabel("Map:");
            tmpLabel.setDisplayedMnemonic('C');
            tmpLabel.setLabelFor(colorList);
            tmpPane.add(tmpLabel);
            tmpPane.add(colorList);
            tmpPane.add(Box.createHorizontalGlue());
            tmpPane.add(showAxes);  
        }
    }

    private JComboBox colorList;
    private JCheckBox showAxes;
}
