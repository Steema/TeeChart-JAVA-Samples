/*
 * LabelAlignmentDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.axes;

import com.steema.teechart.axis.Axis;
import com.steema.teechart.styles.HorizontalAxis;
import com.steema.teechart.styles.Line;
import com.steema.teechart.styles.SeriesPointer;
import com.steema.teechart.styles.VerticalAxis;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import features.ChartSamplePanel;
import javax.swing.JButton;
import javax.swing.JLabel;
/**
 *
 * @author tom
 */
public class PageDemo extends ChartSamplePanel
        implements ActionListener, ItemListener {

    private Axis xAxis, yAxis;

    /**
     * Creates a new instance of LabelAlignmentDemo
     */
    public PageDemo() {
        super();
        alternateButton.addItemListener(this);
        leftButton.addActionListener(this);
        rightButton.addActionListener(this);
        chart1.getPage().setMaxPointsPerPage(10);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == leftButton) 
           {
            chart1.getPage().next();
            chart1.setPage(chart1.getPage());
            chart1.getPage().setScaleLastPage(true);
            
           } 
        else if (source == rightButton)
           {
            chart1.getPage().previous();
            chart1.setPage(chart1.getPage());
            chart1.getPage().setScaleLastPage(true);
           }
        else if (alternateButton == source)
        {
          xAxis.setAutomatic(!xAxis.getAutomatic());
          yAxis.setAutomatic(!yAxis.getAutomatic());
        }
        //alternateButton.setSelected(xAxis.getLabels().getAlternate());

        higlightLabels();
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == alternateButton) {
            xAxis.getLabels().setAlternate(isSelected);
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
//        pointer.getGradient().setStartColor(Color.YELLOW);
//        pointer.getGradient().setEndColor(Color.RED);
//        pointer.getGradient().setVisible(true);
//        pointer.setInflateMargins(true);
        pointer.setVisible(true);

        alternateButton = new JCheckBox("AutoScale");
        alternateButton.setVisible(true);
        alternateButton.setSelected(false);
        rightButton = new JButton("<<");
        //leftButton.setSelected(true);
        rightButton.setVisible(true);
        yAxis = chart1.getAxes().getLeft();
        xAxis = chart1.getAxes().getBottom();
        leftButton = new JButton(">>");
        ButtonGroup group = new ButtonGroup();
        group.add(leftButton);
        group.add(rightButton);
        text1 = new JLabel("FirstValue Index");
        valueText = new JLabel(String.valueOf(xAxis.getEndPosition()));
        
        higlightLabels();
    }

    protected void initGUI() {
        super.initGUI();
        
        JPanel groupPane = new JPanel();
        {
//            groupPane.setBorder(BorderFactory.createTitledBorder("Axis autoscale"));
            groupPane.add(leftButton);
            groupPane.add(rightButton);
            groupPane.add(text1);
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

    private void higlightLabels() {
        valueText.setText(Double.toString(xAxis.getMinXValue()));
    }

    private JButton leftButton, rightButton;
    private JCheckBox alternateButton;
    private JLabel text1, valueText;
}
