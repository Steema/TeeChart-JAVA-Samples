/*
 * AreaSeries.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.area;
import com.steema.teechart.editors.ChartEditor;
import com.steema.teechart.styles.CustomStack;
import com.steema.teechart.styles.HorizArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
public class HorizAreaDemo extends ChartSamplePanel
        implements ItemListener, ActionListener {

    private JButton editButton;
    private JComboBox areaStyleList;
    private JCheckBox stairsButton;
    private JCheckBox view3DButton;

    /** Creates a new instance of AreaSeries */
    public HorizAreaDemo() {
        super();
        stairsButton.addItemListener(this);
        view3DButton.addItemListener(this);
        editButton.addActionListener(this);
        areaStyleList.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == areaStyleList) {
            CustomStack stackType = CustomStack.NONE;
            switch (areaStyleList.getSelectedIndex()) {
                case 0: stackType = CustomStack.NONE; break;
                case 1: stackType = CustomStack.STACK; break;
                case 2: stackType = CustomStack.STACK100; break;
            }
            setAreaSeriesStyle(stackType);
        } else if (source == editButton) {
            ChartEditor.editSeries(chart1.getSeries(0));
        }
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);

        if (source == stairsButton) {
            for(int i=0; i < chart1.getSeriesCount(); i++) {
                ((HorizArea)chart1.getSeries(i)).setStairs(isSelected);
            };
        } else if (source == view3DButton) {
            chart1.getAspect().setView3D(isSelected);
        }
    }

    protected void initComponents() {
        super.initComponents();

        HorizArea areaSeries = null;
        for (int i=0; i < 3; i++) {
            areaSeries = new com.steema.teechart.styles.HorizArea(chart1.getChart());
            areaSeries.setStairs(false);
            areaSeries.getPointer().setVisible(false);
            areaSeries.fillSampleValues(6);
        }

        editButton = new JButton("Edit...");

        stairsButton = new JCheckBox("Stairs");
        stairsButton.setSelected(false);
        view3DButton = new JCheckBox("3D");
        view3DButton.setSelected(true);

        areaStyleList = new JComboBox(EnumStrings.AREA_STYLES);
        areaStyleList.setSelectedIndex(0);
        setAreaSeriesStyle(CustomStack.NONE);
    }

    protected void initGUI() {
        super.initGUI();

        JPanel tmpPane = getButtonPane();
        {
            JLabel tmpLabel;
            tmpLabel = new JLabel("Layout:");
            tmpLabel.setDisplayedMnemonic('L');
            tmpLabel.setLabelFor(areaStyleList);
            tmpPane.add(tmpLabel);
            tmpPane.add(areaStyleList);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(stairsButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(view3DButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(editButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private void setAreaSeriesStyle(CustomStack stackType) {
        for(int i=0; i < chart1.getSeriesCount(); i++) {
            ((HorizArea)chart1.getSeries(i)).setStacked(stackType);
            ((HorizArea)chart1.getSeries(i)).getMarks().setVisible( stackType == CustomStack.NONE );
        };
    }
}
