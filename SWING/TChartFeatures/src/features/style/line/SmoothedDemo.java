/*
 * SmoothedDemo.java
 *
 * <p>Copyright: (c) 2005-2011 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.line;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.Custom;
import com.steema.teechart.styles.Line;
import com.steema.teechart.styles.PointerStyle;
import com.steema.teechart.styles.PointerStyles;
import com.steema.teechart.styles.SeriesPointer;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import features.ChartSamplePanel;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

/**
 *
 * @author chris
 */
public class SmoothedDemo extends ChartSamplePanel
    implements ItemListener {

    private Custom lineSeries;

    private JCheckBox clickableLineButton;
    private JRadioButton lineButton, areaButton;

    /**
     * Creates a new instance of SmoothedDemo
     */
    public SmoothedDemo() {
        super();
        clickableLineButton.addItemListener(this);
        lineButton.addItemListener(this);
        areaButton.addItemListener(this);
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = e.getStateChange() == ItemEvent.SELECTED;
        if (source == clickableLineButton) {
            lineSeries.setSmoothed(isSelected);
        }
        else if(source == lineButton && lineButton.isSelected()) {          
             chart1.getAspect().setView3D(false);
             chart1.getSeries().remove(lineSeries);
             lineSeries.setChart((com.steema.teechart.Chart)null);
             chart1.getSeries().add(lineSeries = new com.steema.teechart.styles.Line());
             lineSeries.getPointer().setStyle(com.steema.teechart.styles.PointerStyle.CIRCLE);
             lineSeries.getPointer().setVisible(true);
             lineSeries.setColor(Color.fromArgb(128, 128, 128));
             lineSeries.fillSampleValues(8);
             lineSeries.setSmoothed(clickableLineButton.isSelected());
        }
        else if(source == areaButton && areaButton.isSelected()) {
             chart1.getAspect().setView3D(true);
             chart1.getSeries().remove(lineSeries);
             lineSeries.setChart((com.steema.teechart.Chart)null);
             chart1.getSeries().add(lineSeries = new com.steema.teechart.styles.Area());
             lineSeries.setColor(Color.fromArgb(128, 128, 128));
             lineSeries.fillSampleValues(8);
             lineSeries.setSmoothed(clickableLineButton.isSelected());    
        }
    }

    protected void initComponents() {
        super.initComponents();

        this.chart1.getLegend().setVisible(false);
        lineSeries = new com.steema.teechart.styles.Line(chart1.getChart());

        SeriesPointer tmpPointer = lineSeries.getPointer();
        tmpPointer.setInflateMargins(true);
        tmpPointer.setStyle(PointerStyle.CIRCLE);
        tmpPointer.setVisible(true);
        lineSeries.setColor(Color.fromArgb(128, 128, 128));
        lineSeries.fillSampleValues(8);
        clickableLineButton = new JCheckBox("Smoothed");
        lineButton = new JRadioButton("Line");
        areaButton = new JRadioButton("Area");
        ButtonGroup group = new ButtonGroup();
        group.add(lineButton);
        group.add(areaButton);
        lineButton.setSelected(true);
    }

    protected void initGUI() {
        super.initGUI();
        chart1.getAspect().setView3D(false);

        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(clickableLineButton);
            tmpPane.add(Box.createHorizontalStrut(20));
            tmpPane.add(lineButton);
            tmpPane.add(Box.createHorizontalStrut(20));
            tmpPane.add(areaButton);
            tmpPane.add(Box.createHorizontalStrut(20));
        }
    }
}
