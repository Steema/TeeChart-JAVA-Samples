/*
 * MapTransparencyDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.map;

import com.steema.teechart.styles.Map;
import com.steema.teechart.styles.PaletteStyle;
import javax.swing.Box;
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
public class MapTransparencyDemo extends ChartSamplePanel
    implements ChangeListener {

    private Map series;
    private JSlider transparencySlider;

    /** Creates a new instance of MapTransparencyDemo */
    public MapTransparencyDemo() {
        super();
        transparencySlider.addChangeListener(this);
    }
    
    public void stateChanged(ChangeEvent e) {
        Object source = e.getSource();

         if (source instanceof JSlider) {
            JSlider tmpSlider = (JSlider)e.getSource();
            int tmpValue = (int)tmpSlider.getValue();
            if (tmpSlider == transparencySlider) {
                series.setTransparency(tmpValue);
            } 
         }
    }

    protected void initChart() {
        super.initChart();
        chart1.setText("Map Series");
        chart1.getHeader().setVisible(true);
        chart1.getFrame().setVisible(false);
        chart1.getAspect().setView3D(false);
    }

    protected void initSeries() {
        series = new Map(chart1.getChart());
        series.fillSampleValues();

        /* Set the color palette "strong" */
        series.setPaletteStyle(PaletteStyle.STRONG);
        series.setUsePalette(true);
        series.setUseColorRange(false);
    }

    protected void initComponents() {
        super.initComponents();

        transparencySlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
        chart1.getAspect().setSmoothingMode(true);        
        initSeries();        
    }

    protected void initGUI() {
        super.initGUI();
        
        JLabel tmpLabel;
        
        JPanel tmpPane = getButtonPane();
        {
            tmpLabel = new JLabel("Transparency: ");
            tmpLabel.setDisplayedMnemonic('T');
            tmpLabel.setLabelFor(transparencySlider);
            tmpPane.add(tmpLabel);
            tmpPane.add(transparencySlider);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }
}
