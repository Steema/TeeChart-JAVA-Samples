/*
 * ConeDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.bar;
import com.steema.teechart.events.ChartPaintAdapter;
import com.steema.teechart.events.ChartDrawEvent;
import com.steema.teechart.styles.Bar;
import com.steema.teechart.styles.BarStyle;
import com.steema.teechart.styles.MarksStyle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class ConeDemo extends ChartSamplePanel
        implements ActionListener, ChangeListener {

    private Bar barSeries;

    /**
     * Creates a new instance of ConeDemo
     */
    public ConeDemo() {
        super();
        percentSlider.addChangeListener(this);
        barStyleList.addActionListener(this);
    }

public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == barStyleList) {
            switch (barStyleList.getSelectedIndex()) {
                case 0: barSeries.setBarStyle(BarStyle.CONE); break;
                case 1: barSeries.setBarStyle(BarStyle.PYRAMID); break;
            }
            percentSlider.setEnabled(barSeries.getBarStyle()==BarStyle.CONE);
        }
    }

    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider)e.getSource();
        if (!source.getValueIsAdjusting()) {
            int percent = (int)source.getValue();
            percentLabel.setText(String.valueOf(percent)+"%");
            barSeries.setConePercent(percent);
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getPanel().setMarginTop(15);
        chart1.addChartPaintListener( new ChartPaintAdapter() {
            public void chartPainted(ChartDrawEvent pce) {
                chart1.getGraphics3D().cone(
                        true,
                        30,30,80,50,0,20,
                        true,
                        percentSlider.getValue()
                );
            };
        });
    }

    protected void initSeries() {
        barSeries = new Bar(chart1.getChart());
        barSeries.setBarStyle(BarStyle.CONE);
        barSeries.getMarks().setVisible(true);
        barSeries.getMarks().setStyle(MarksStyle.VALUE);
        barSeries.fillSampleValues(5);
        barSeries.setConePercent(30);
    }

    protected void initComponents() {
        super.initComponents();
        initSeries();
        percentSlider = new JSlider(JSlider.HORIZONTAL, 1, 100, barSeries.getConePercent());
        percentLabel = new JLabel(String.valueOf(percentSlider.getValue())+"%");
        barStyleList = new JComboBox(BAR_STYLES);
        barStyleList.setSelectedIndex(0);
    }

    protected void initGUI(){
        super.initGUI();
        JLabel tmpLabel;
        JPanel tmpPane = getButtonPane();
        {
            tmpLabel = new JLabel("Cone Percent:");
            tmpLabel.setDisplayedMnemonic('P');
            tmpLabel.setLabelFor(percentSlider);
            tmpPane.add(tmpLabel);
            tmpPane.add(percentSlider);
            tmpPane.add(percentLabel);
            tmpPane.add(Box.createHorizontalStrut(20));
            tmpPane.add(barStyleList);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JLabel percentLabel;
    private JSlider percentSlider;
    private JComboBox barStyleList;

    private static final String[] BAR_STYLES = { "Cone", "Pyramid" };
}
