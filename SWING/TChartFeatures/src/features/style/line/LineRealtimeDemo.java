/*
 * LineRealtimeDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.line;

import com.steema.teechart.TChart;
import com.steema.teechart.legend.LegendStyle;
import com.steema.teechart.styles.Line;
import com.steema.teechart.themes.ColorPalettes;
import com.steema.teechart.themes.Theme;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import features.ChartSamplePanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.Timer;

/**
 *
 * @author narcis
 */
public class LineRealtimeDemo extends ChartSamplePanel
    implements ItemListener {

    private Line lineSeries1;
    private Line lineSeries2;
    private Line lineSeries3;
    private Line lineSeries4;

    private JCheckBox animateButton;
    private JCheckBox threeDButton;
    private JCheckBox legendButton;
    
    private Timer timer1;       
    
    private final static int ONE_MILLISECOND = 1;

    /**
     * Creates a new instance of RealtimeDemo
     */
    public LineRealtimeDemo() {
        super();
        animateButton.addItemListener(this);
        threeDButton.addItemListener(this);
        legendButton.addItemListener(this);
    }    

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = e.getStateChange() == ItemEvent.SELECTED;
        
        if (source == animateButton) {
            if (isSelected){
                timer1.start();
            }
            else {
                timer1.stop();
            }                
        }
        
        if (source == threeDButton) {
            chart1.getAspect().setView3D(isSelected);
        }
        
        if (source == legendButton) {
            chart1.getLegend().setVisible(isSelected);
        }
    }

    protected void initComponents() {
        super.initComponents();

        ColorPalettes.applyPalette(chart1.getChart(), Theme.DefaultPalette);
        
        lineSeries1 = new com.steema.teechart.styles.Line(chart1.getChart());
        lineSeries2 = new com.steema.teechart.styles.Line(chart1.getChart());
        lineSeries3 = new com.steema.teechart.styles.Line(chart1.getChart());
        lineSeries4 = new com.steema.teechart.styles.Line(chart1.getChart());        
                
        lineSeries1.fillSampleValues(1000);
        lineSeries2.fillSampleValues(1000);
        lineSeries3.fillSampleValues(1000);
        lineSeries4.fillSampleValues(1000);
        
        chart1.getAspect().setSmoothingMode(false);
        chart1.getAspect().setTextSmooth(false);
        
        chart1.getAxes().getBottom().setIncrement(5);
        
        chart1.getHeader().setVisible(false);
        
        chart1.getLegend().setLegendStyle(LegendStyle.LASTVALUES);
        chart1.getLegend().setVisible(false);
        
        animateButton = new JCheckBox("Animate");
        animateButton.setSelected(true);
        
        threeDButton = new JCheckBox("3D");
        threeDButton.setSelected(true);
        
        legendButton = new JCheckBox("Legend");        
        
        /* Create a timer. */
        timer1 = new Timer(ONE_MILLISECOND, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                AnimateSeries(chart1);
            }
        });
        
        timer1.start();
    }
    
    private void AnimateSeries(TChart chart){
        Random rnd = new Random();
        double newX, newY;
        
        chart.setAutoRepaint(false);
        
        for (int i=0; i<chart.getSeriesCount(); i++) {
            // show only 50 points - delete the rest
            com.steema.teechart.styles.Series s = chart.getSeries(i);
            
            while(s.getCount() > 50) {
                s.delete(0);
            }
                
            newX = s.getXValues().getLast() + 1;
            newY = s.getYValues().getLast() + 1000*rnd.nextInt(2) - 500;

            if (Math.abs(newY)>1.0e+4){
                newY = 0.0;
            }
            
            s.add(newX, newY);
        }
        
        chart.setAutoRepaint(false);        
        chart.refreshControl();
    }

    protected void initGUI() {
        super.initGUI();

        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(animateButton);            
            tmpPane.add(Box.createHorizontalStrut(20));
            tmpPane.add(threeDButton);
            tmpPane.add(Box.createHorizontalStrut(20));
            tmpPane.add(legendButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }
}
