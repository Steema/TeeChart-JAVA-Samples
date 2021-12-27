/*
 * PieDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.pie;

import com.steema.teechart.editors.ChartEditor;
import com.steema.teechart.styles.Pie;
import com.steema.teechart.drawing.EdgeStyle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class PieDemo extends ChartSamplePanel
    implements ActionListener, ItemListener {

    private Pie pieSeries;
    private JButton editButton;
    private JCheckBox[] optionButtons;

    /**
     * Creates a new instance of PieDemo
     */
    public PieDemo() {
        super();
        editButton.addActionListener(this);
        for (int i=0; i < optionButtons.length; i++) {
            optionButtons[i].addItemListener(this);
        }
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == editButton) {
            ChartEditor.editSeries(pieSeries);
        }
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);

        if (source == optionButtons[0]) {
            chart1.getAspect().setView3D(isSelected);
        } else if (source == optionButtons[1]) {
            if (isSelected) {
                pieSeries.setExplodeBiggest(30);
            } else {
                pieSeries.setExplodeBiggest(0);
            }
        } else if (source == optionButtons[2]) {
            pieSeries.getMarks().setVisible(isSelected);
        } else if (source == optionButtons[3]) {
            if (isSelected) {
                pieSeries.getShadow().setHorizSize(20);
                pieSeries.getShadow().setVertSize(20);
            } else {
                pieSeries.getShadow().setHorizSize(0);
                pieSeries.getShadow().setVertSize(0);
            }
            pieSeries.repaint();
        } else if (source == optionButtons[4]) {
            if (isSelected) {
                pieSeries.setAngleSize(180);
                optionButtons[3].setSelected(false);
            } else {
                pieSeries.setAngleSize(360);
            }
        }
    }

    protected void initComponents() {
        super.initComponents();

        pieSeries = new com.steema.teechart.styles.Pie(chart1.getChart());
        pieSeries.getMarks().setVisible(true);
        pieSeries.getShadow().setVisible(true);
        pieSeries.getShadow().setHorizSize(20);
        pieSeries.getShadow().setVertSize(20);
        pieSeries.setRotationAngle(70);
        pieSeries.fillSampleValues(8);
        
//        pieSeries.add(5,"1st");
//        pieSeries.add(5,"2nd");
//        pieSeries.add(10,"3rd");
//        pieSeries.add(7,"4th");
        
        pieSeries.setBevelPercent(5);
        pieSeries.getPen().setVisible(false);
        pieSeries.setEdgeStyle(EdgeStyle.CURVED);

        editButton = new JButton("Edit...");
        optionButtons  = new JCheckBox[5];

        optionButtons[0] = new JCheckBox("3D");
        optionButtons[0].setSelected(true);
        optionButtons[1] = new JCheckBox("Exploded");
        optionButtons[2] = new JCheckBox("Marks");
        optionButtons[2].setSelected(pieSeries.getMarks().getVisible());
        optionButtons[3] = new JCheckBox("Shadow");
        optionButtons[3].setSelected(pieSeries.getShadow().getVisible());
        optionButtons[4] = new JCheckBox("Partial");
    }

    protected void initGUI() {
        super.initGUI();
        chart1.getHeader().setVisible(true);
        chart1.setText("Pie");
        chart1.getAspect().setElevation(315);
        chart1.getAspect().setOrthogonal(false);
        chart1.getAspect().setPerspective(0);
        chart1.getAspect().setRotation(360);

        JPanel tmpPane = getButtonPane();
        {
            for (int i=0; i < optionButtons.length; i++) {
                tmpPane.add(optionButtons[i]);
                tmpPane.add(Box.createHorizontalStrut(10));
            }
            tmpPane.add(editButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }
}
