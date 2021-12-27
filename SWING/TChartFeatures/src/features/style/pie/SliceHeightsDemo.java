/*
 * SliceHeightsDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.pie;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.Donut;
import com.steema.teechart.themes.ColorPalettes;
import com.steema.teechart.themes.Theme;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import features.ChartSamplePanel;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author narcis
 */
public class SliceHeightsDemo extends ChartSamplePanel
    implements ItemListener, ChangeListener {

    private Donut donutSeries;
    private JCheckBox heightButton;
    private JSlider rotateSlider;


    /**
     * Creates a new instance of SliceHeightsDemo
     */
    public SliceHeightsDemo() {
        super();        
        heightButton.addItemListener(this);
        heightButton.setSelected(true);
        rotateSlider.addChangeListener(this);
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);

        if (source == heightButton) {
            for(int t=0; t < donutSeries.getCount(); ++t) {
                if (isSelected) {
                    donutSeries.SliceHeight().setSlice(t, (t+1)*100 / donutSeries.getCount()); // vary height
                }
                else {
                    donutSeries.SliceHeight().setSlice(t, 100);  // total height for all slices
                }
            }
        }
    }
    
    public void stateChanged(ChangeEvent e) {
        Object source = e.getSource();

         if (source instanceof JSlider) {
            JSlider tmpSlider = (JSlider)e.getSource();
            int tmpValue = (int)tmpSlider.getValue();
            if (tmpSlider == rotateSlider) {
                donutSeries.setRotationAngle(tmpValue);
            } 
         }
    }

    protected void initComponents() {
        super.initComponents();
        
        chart1.getHeader().setVisible(true);
        chart1.getHeader().getFont().setBold(true);
        chart1.getHeader().getFont().setColor(Color.BLACK);
        chart1.setText("TeeChart");
        
        chart1.getAspect().setElevation(315);
        chart1.getAspect().setOrthogonal(false);
        chart1.getAspect().setPerspective(0);
        chart1.getAspect().setRotation(360);
        chart1.getAspect().setChart3DPercent(55);
        
        chart1.getLegend().setVisible(false);
        
        chart1.getPanel().getGradient().setVisible(true);
        chart1.getPanel().getGradient().setStartColor(Color.WHITE);
        chart1.getPanel().getGradient().setUseMiddle(false);
        chart1.getPanel().getGradient().setEndColor(Color.YELLOW);

        donutSeries = new com.steema.teechart.styles.Donut(chart1.getChart());
        donutSeries.getMarks().setVisible(true);
        donutSeries.getMarks().getArrow().setColor(Color.WHITE);
        donutSeries.fillSampleValues(9);
        ColorPalettes.applyPalette(chart1.getChart(), Theme.PastelsPalette);        

        heightButton = new JCheckBox("Vary Slice Heights");        
        
        rotateSlider = new JSlider(JSlider.HORIZONTAL, 0, 360, 0);
    }

    protected void initGUI() {
        super.initGUI();
        
        JLabel tmpLabel;
        
        JPanel tmpPane = getButtonPane();
        {            
            tmpPane.add(heightButton);
            tmpPane.add(Box.createHorizontalStrut(20));            
            
            tmpLabel = new JLabel("Rotate: ");
            tmpLabel.setDisplayedMnemonic('R');
            tmpLabel.setLabelFor(rotateSlider);
            tmpPane.add(tmpLabel);
            tmpPane.add(rotateSlider);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

}
