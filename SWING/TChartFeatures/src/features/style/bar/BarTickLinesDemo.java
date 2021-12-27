/*
 * BarTickLinesDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.bar;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.editors.ButtonPen;
import com.steema.teechart.styles.Bar;
import javax.swing.Box;
import javax.swing.JPanel;
import features.ChartSamplePanel;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JCheckBox;

/**
 *
 * @author narcis
 */
public class BarTickLinesDemo extends ChartSamplePanel
        implements ItemListener {

    private Bar barSeries;
    private ButtonPen showTickLineEditorButton;
    private JCheckBox tickLinesButton;

    /**
     * Creates a new instance of GradientDemo
     */
    public BarTickLinesDemo() {
        super();
        tickLinesButton.addItemListener(this);
    }
    
    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = e.getStateChange() == ItemEvent.SELECTED;
        
        if (source == tickLinesButton) {
            barSeries.getTickLines().setVisible(isSelected);
            showTickLineEditorButton.invalidate();
        }
    }

    protected void initChart() {
        super.initChart();
        
        chart1.getHeader().setVisible(true);
        chart1.getHeader().setText("Bar Series with lines at each grid tick position.");
        chart1.getHeader().getFont().setBold(true);
        chart1.getHeader().getFont().setColor(Color.NAVY);
        
        chart1.getPanel().getGradient().setVisible(true);
        chart1.getPanel().getGradient().setUseMiddle(true);
        chart1.getPanel().getGradient().setStartColor(Color.fromArgb(254, 255, 192, 128));
        chart1.getPanel().getGradient().setMiddleColor(Color.fromArgb(254, 255, 255, 128));
        chart1.getPanel().getGradient().setEndColor(Color.WHITE);
        
        chart1.getWalls().getBack().setTransparent(false);
    }

    protected void initSeries() {
        barSeries = new Bar(chart1.getChart());
        barSeries.setColor(Color.fromArgb(254, 0, 255, 255));
        barSeries.getPen().setColor(Color.fromArgb(254, 0, 153, 153));
        barSeries.getTickLines().setColor(Color.fromArgb(254, 0, 0, 255));
        barSeries.getTickLines().setVisible(true);
        barSeries.fillSampleValues(6);        
    }

    protected void initComponents() {
        super.initComponents();        

        showTickLineEditorButton = new ButtonPen("Tick lines...");
        tickLinesButton = new JCheckBox("Show tick lines");
        tickLinesButton.setSelected(true);
        
        initSeries();
        
        showTickLineEditorButton.setPen(barSeries.getTickLines());
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(showTickLineEditorButton);
            tmpPane.add(Box.createHorizontalStrut(20));
            tmpPane.add(tickLinesButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }
}
