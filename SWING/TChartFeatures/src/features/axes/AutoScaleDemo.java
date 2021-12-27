/*
 * AutoScaleDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.axes;

import com.steema.teechart.axis.Axis;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.Line;
import com.steema.teechart.styles.SeriesPointer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import features.ChartSamplePanel;
import com.steema.teechart.styles.*;
import javax.swing.JButton;
import javax.swing.JLabel;
/**
 *
 * @author alexander stark 
 */
public class AutoScaleDemo extends ChartSamplePanel
        implements ActionListener, ItemListener {

    private Axis xAxis;

    /**
     * Creates a new instance of LabelAlignmentDemo
     */
    public AutoScaleDemo() {
        super();
        alternateButton.addItemListener(this);
        alternateButton.addActionListener(this);
        leftButton.addActionListener(this);
        rightButton.addActionListener(this);
        chart1.getPage().setMaxPointsPerPage(10);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == leftButton) 
           {
            chart1.getPage().next();
          } 
        else if (source == rightButton)
           {
            chart1.getPage().previous();           
           }
        leftButton.setEnabled(!(chart1.getPage().getCount() == chart1.getPage().getCurrent()));
        rightButton.setEnabled(!(1 == chart1.getPage().getCurrent()));
        finishingTheRest();
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean  isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == alternateButton) {
            chart1.getPage().setAutoScale(isSelected);
        }
    }

    
    protected void initChart() {
        super.initChart();
        chart1.getAspect().setView3D(false);
        /* Set axes alternate labels */
        for (int t=0; t < chart1.getAxes().getCount(); t++) {
            chart1.getAxes().getAxis(t).getLabels().setAlternate(true);
        }
    }

    
    protected void initComponents() {
        super.initComponents();

        Line series = new Line(chart1.getChart());
        /* Sample values */
        series.fillSampleValues();
        series.setHorizontalAxis(HorizontalAxis.BOTH);
        series.setVerticalAxis(VerticalAxis.BOTH);

        series.getMarks().setVisible(false);

        SeriesPointer pointer= series.getPointer();
        pointer.getGradient().setStartColor(Color.YELLOW);
        pointer.getGradient().setEndColor(Color.RED);
        pointer.getGradient().setVisible(true);
        pointer.setInflateMargins(true);
        pointer.setVisible(true);

        
        alternateButton = new JCheckBox("AutoScale");
        alternateButton.setVisible(true);
        alternateButton.setSelected(true);
        chart1.getPage().setAutoScale(true);
        rightButton = new JButton("<<");
        //leftButton.setSelected(true);
        rightButton.setVisible(true);
        xAxis = chart1.getAxes().getBottom();
        leftButton = new JButton(">>");
        ButtonGroup group = new ButtonGroup();
        group.add(leftButton);
        group.add(rightButton);        
        valueText = new JLabel(String.valueOf(xAxis.getEndPosition()));
        
        leftButton.setEnabled(true);
        rightButton.setEnabled(false);
        
        finishingTheRest();
    }

    
    protected void initGUI() {
        super.initGUI();
        
        JPanel groupPane = new JPanel();
        {
//            groupPane.setBorder(BorderFactory.createTitledBorder("Axis autoscale"));
            groupPane.add(leftButton);
            groupPane.add(rightButton);
            groupPane.add(new JLabel("FirstValue Index"));
            groupPane.add(valueText);
            groupPane.add(alternateButton);
        }
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(groupPane);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(rightButton);
            tmpPane.add(leftButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private void finishingTheRest() {
        valueText.setText(Double.toString(xAxis.getMinXValue()));

    }

    private JButton leftButton, rightButton;
    private JCheckBox alternateButton;
    private JLabel valueText;
}
