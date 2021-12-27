/*
 * AreaStackGroupDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.area;
import com.steema.teechart.styles.Area;
import com.steema.teechart.styles.CustomStack;
import com.steema.teechart.styles.MultiAreas;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import features.ChartSamplePanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;

/**
 *
 * @author narcis
 */
public class AreaStackGroupDemo extends ChartSamplePanel
        implements ItemListener, ChangeListener, ActionListener {

    private Area areaSeries1;
    private Area areaSeries2;
    private Area areaSeries3;
    private Area areaSeries4;
    private JCheckBox stackedButton;
    private JSpinner groupSpinner;
    private SpinnerNumberModel groupSpinnerModel;
    private JComboBox seriesList;

    /**
     * Creates a new instance of AreaStackGroup Demo
     */
    public AreaStackGroupDemo() {
        super();
        stackedButton.addItemListener(this);
        groupSpinner.addChangeListener(this);
        seriesList.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = null;
        
        if (e != null) {
            source = e.getSource();
        }

        if ((source == seriesList) || (source == null)) {            
            int group=((Area)chart1.getSeries(seriesList.getSelectedIndex())).getStackGroup();            
            groupSpinnerModel.setValue(Integer.valueOf(group));            
        }    
    }
        
    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == stackedButton) {
            for (int i=0; i<chart1.getSeriesCount(); i++) {
                ((Area)chart1.getSeries(i)).setMultiArea(isSelected ? MultiAreas.STACKED : MultiAreas.NONE);
            }
        }
    }

    public void stateChanged(ChangeEvent e) {
        Object source = e.getSource();

        if (source == groupSpinner) {
            int group = groupSpinnerModel.getNumber().intValue();
            ((Area)chart1.getSeries(seriesList.getSelectedIndex())).setStackGroup(group);
        }
    }

    protected void initComponents() {
        super.initComponents();

        areaSeries1 = new Area(chart1.getChart());
        areaSeries2 = new Area(chart1.getChart());
        areaSeries3 = new Area(chart1.getChart());
        areaSeries4 = new Area(chart1.getChart());
        
        for (int i=0; i < chart1.getSeriesCount(); i++) {
            Area tmpArea = (Area)chart1.getSeries(i);
            tmpArea.fillSampleValues(10);
            tmpArea.setMultiArea(MultiAreas.STACKED);
            tmpArea.setStacked(CustomStack.STACK);
            tmpArea.setTransparency(30);
        }
        
        areaSeries2.setStackGroup(1);
        areaSeries4.setStackGroup(1);

        groupSpinnerModel = new SpinnerNumberModel(
                0,
                0,
                chart1.getSeriesCount() - 1,
                1);
        groupSpinner = new JSpinner(groupSpinnerModel);

        stackedButton = new JCheckBox("Stacked");
        stackedButton.setSelected(true);
        
        seriesList = new JComboBox(chart1.getSeries().toArray());
        seriesList.setSelectedIndex(0);
        
        actionPerformed(null);
    }

    protected void initGUI() {
        super.initGUI();
        chart1.getLegend().setVisible(false);
        chart1.getAspect().setSmoothingMode(true);
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(stackedButton);
            tmpPane.add(Box.createHorizontalStrut(100));
            
            JLabel tmpLabel1;
            tmpLabel1 = new JLabel("Series:");
            tmpLabel1.setDisplayedMnemonic('S');
            tmpLabel1.setLabelFor(seriesList);
            tmpPane.add(tmpLabel1);
            tmpPane.add(seriesList);
            tmpPane.add(Box.createHorizontalStrut(20));
            
            JLabel tmpLabel2;
            tmpLabel2 = new JLabel("Group:");
            tmpLabel2.setDisplayedMnemonic('G');
            tmpLabel2.setLabelFor(groupSpinner);
            tmpPane.add(tmpLabel2);
            tmpPane.add(groupSpinner);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

}
