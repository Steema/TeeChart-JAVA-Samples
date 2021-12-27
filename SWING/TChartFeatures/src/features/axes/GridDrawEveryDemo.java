/*
 * GridDrawEveryDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.axes;

import com.steema.teechart.axis.Axis;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.SeriesPointer;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import features.ChartSamplePanel;
import com.steema.teechart.styles.*;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author stark
 */
public class GridDrawEveryDemo extends ChartSamplePanel
        implements ItemListener, ChangeListener {
    
    /**
     * Creates a new instance of LabelAlignmentDemo
     */
    public GridDrawEveryDemo() {
        super();
        chooseAxis.addItemListener(this);
        drawEverySpinner.addChangeListener(this);
    }
    
    //   
    protected void initChart() {
        super.initChart();
        chart1.getAspect().setView3D(false);
        /* Set axes alternate labels */
        for (int t=0; t < chart1.getAxes().getCount(); t++) {
            chart1.getAxes().getAxis(t).getLabels().setAlternate(true);
        }
    }
    
    // 
    protected void initComponents() {
        super.initComponents();
        
        Bubble series = new Bubble(chart1.getChart());
        /* Sample values */
        series.fillSampleValues(6);
        series.setHorizontalAxis(HorizontalAxis.BOTTOM);
        series.setVerticalAxis(VerticalAxis.LEFT);
        
        series.getMarks().setVisible(false);
        
        SeriesPointer pointer= series.getPointer();
        pointer.getGradient().setStartColor(Color.YELLOW);
        pointer.getGradient().setEndColor(Color.RED);
        pointer.getGradient().setVisible(true);
        pointer.setInflateMargins(true);
        pointer.setVisible(true);
        
        chart1.getAxes().getLeft().setIncrement(200);
        
        chooseAxis = new JComboBox();
        chooseAxis.setVisible(true);
        chooseAxis.addItem("Vertical");
        chooseAxis.addItem("Horizontal");
        chooseAxis.setSelectedIndex(0);
        
        SpinnerNumberModel drawEverySpinnerModel = new SpinnerNumberModel(
                getSelectedAxis().getGrid().getDrawEvery(),
                1,
                Integer.MAX_VALUE,
                1);
        drawEverySpinner = new JSpinner(drawEverySpinnerModel);
    }
    
    //
    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(new JLabel("Axis: "));
            tmpPane.add(chooseAxis);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(new JLabel("Draw every: "));
            tmpPane.add(drawEverySpinner);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }
    
    private Axis getSelectedAxis() {
        if (chooseAxis.getSelectedIndex() == 0) {
            return chart1.getAxes().getLeft();
        } else {
            return chart1.getAxes().getBottom();
        }
    }
    
    public void itemStateChanged(ItemEvent e) {
        Object source = e.getSource();
        if (source == chooseAxis){
            drawEverySpinner.setValue(Integer.valueOf(getSelectedAxis().getGrid().getDrawEvery()));
        }
    }
    
    public void stateChanged(ChangeEvent e) {
        Object source = e.getSource();
        if (source == drawEverySpinner) {
            int i = ((Integer)drawEverySpinner.getValue()).intValue();
            getSelectedAxis().getGrid().setDrawEvery(i);
        }
    }
    
    private JComboBox chooseAxis;
    private JSpinner drawEverySpinner;
}
