/*
 * TransparencyDemo.java
 *
 * <p>Copyright: Copyright (c)
 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.area;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.Area;
import com.steema.teechart.styles.CustomStack;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class TransparencyDemo extends ChartSamplePanel
        implements ItemListener, ChangeListener {

    /**
     * Creates a new instance of TransparencyDemo
     */
    public TransparencyDemo() {
        super();
        transparencyButton.addItemListener(this);
        view3DButton.addItemListener(this);
        for (int i=0; i < transparencySliders.length; i++) {
            transparencySliders[i].addChangeListener(this);
        };
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == transparencyButton) {
            int transparency = 0;
            for(int i=0; i < chart1.getSeriesCount(); i++) {
                if (isSelected) {
                    ((Area)chart1.getSeries(i)).setTransparency(transparencySliders[i].getValue());
                } else {
                    ((Area)chart1.getSeries(i)).setTransparency(0);
                }
            }
            chart1.repaint();
        } else if (source == view3DButton) {
            chart1.getAspect().setView3D(isSelected);
        }
    }

    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider)e.getSource();
        for (int i=0; i < transparencySliders.length; i++) {
            if (transparencySliders[i] == source) {
                ((Area)chart1.getSeries(i)).setTransparency(transparencySliders[i].getValue());
                ((Area)chart1.getSeries(i)).repaint();
                break;
            }
        };
    }

    protected void initComponents() {
        super.initComponents();

        Area areaSeries = null;
        for (int i=0; i < 3; i++) {
            areaSeries = new com.steema.teechart.styles.Area(chart1.getChart());
            /* colors must be set before setting transparency */
            switch (i) {
                case 0: areaSeries.setColor(Color.RED); break;
                case 1: areaSeries.setColor(Color.GREEN); break;
                case 2: areaSeries.setColor(Color.NAVY); break;
            };
            areaSeries.setStacked(CustomStack.NONE);
            areaSeries.setStairs(false);
            areaSeries.setTransparency(56);
            areaSeries.fillSampleValues(20);

        }
        transparencySliders = new JSlider[3];
        for (int i=0; i < transparencySliders.length; i++) {
            transparencySliders[i] = new JSlider(JSlider.HORIZONTAL, 0, 100, 56);
        };
        transparencyButton = new JCheckBox("Transparency %:");
        transparencyButton.setSelected(true);
        view3DButton = new JCheckBox("3D");
        view3DButton.setSelected(false);
    }

    protected void initGUI() {
        super.initGUI();
        chart1.getAspect().setView3D(false);
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(view3DButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(transparencyButton);
            for (int i=0; i < transparencySliders.length; i++) {
                tmpPane.add(Box.createHorizontalStrut(10));
                tmpPane.add(transparencySliders[i]);
            }
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JSlider[] transparencySliders;
    private JCheckBox transparencyButton;
    private JCheckBox view3DButton;
}
