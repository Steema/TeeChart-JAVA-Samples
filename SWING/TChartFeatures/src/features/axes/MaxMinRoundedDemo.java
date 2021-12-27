/*
 * MaxMinRoundedDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.axes;

import com.steema.teechart.axis.Axis;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import features.ChartSamplePanel;
import com.steema.teechart.styles.*;
import javax.swing.JCheckBox;
import javax.swing.JLabel;

/**
 *
 * @author stark
 */
public class MaxMinRoundedDemo extends ChartSamplePanel
        implements ItemListener {
    
    /**
     * Creates a new instance of MaxMinRoundedDemo
     */
    public MaxMinRoundedDemo() {
        super();
        chooseAxis.addItemListener(this);
        maxButton.addItemListener(this);
        minButton.addItemListener(this);
    }    
    
    protected void initChart() {
        super.initChart();
        chart1.getAspect().setView3D(false);        
        chart1.getAxes().getTop().setVisible(false);
        chart1.getAxes().getRight().setVisible(false);
        chart1.getAxes().getLeft().setMaxRound(true);
        chart1.getAxes().getLeft().setMinRound(true);        
        chart1.getAxes().getLeft().setIncrement(50);
        chart1.getAxes().getBottom().setIncrement(10);
    }
    
    // 
    protected void initComponents() {
        super.initComponents();
        
        FastLine fastLine = new FastLine(chart1.getChart());
        /* Sample values */
        fastLine.fillSampleValues(20);
        fastLine.setHorizontalAxis(HorizontalAxis.BOTH);
        fastLine.setVerticalAxis(VerticalAxis.BOTH);
        fastLine.getMarks().setVisible(true);
        
        minButton = new JCheckBox("Min rounded");
        maxButton = new JCheckBox("Max rounded");
        
        chooseAxis = new JComboBox();
        chooseAxis.setVisible(true);
        chooseAxis.addItem("Vertical");
        chooseAxis.addItem("Horizontal");
        chooseAxis.setSelectedIndex(0);
        
	maxButton.setSelected(getSelectedAxis().getMaxRound());        
	minButton.setSelected(getSelectedAxis().getMinRound());        
    }
    
    //
    protected void initGUI() {
        super.initGUI();        
        JPanel tmpPane = getButtonPane();
        {            
            tmpPane.add(new JLabel("Axis"));
            tmpPane.add(chooseAxis);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(minButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(maxButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }       
    
    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean  isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == maxButton) {
            getSelectedAxis().setMaxRound(isSelected);
        } else if (source == minButton) {
            getSelectedAxis().setMinRound(isSelected);
        } if (source == chooseAxis) {
            maxButton.setSelected(getSelectedAxis().getMaxRound());        
            minButton.setSelected(getSelectedAxis().getMinRound());            
        }
    }
    
    public Axis getSelectedAxis() {
        if (chooseAxis.getSelectedIndex() == 0) {
            return chart1.getAxes().getLeft();
        }
        else {
            return chart1.getAxes().getBottom();
        }
    }
    
    private JComboBox chooseAxis;
    private JCheckBox minButton, maxButton;
}
