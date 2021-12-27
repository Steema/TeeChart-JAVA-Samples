/*
 * ImageDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.bar;

import com.steema.teechart.ImageMode;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.Bar;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class ImageDemo extends ChartSamplePanel
    implements ItemListener {

    private Bar series1, series2;

    /** Creates a new instance of ImageDemo */
    public ImageDemo() {
        super();
        usePatternButton.addItemListener(this);
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == usePatternButton) {
            if (isSelected) {
                series1.getBrush().loadImage(ChartSamplePanel.class.getResource(URL_IMAGE1));
                series2.getBrush().loadImage(ChartSamplePanel.class.getResource(URL_IMAGE2));
            } else {
                series1.getBrush().clearImage();
                series2.getBrush().clearImage();
            }
            chart1.repaint();
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getAspect().setView3D(false);
    }

    protected void initComponents() {
        super.initComponents();

        series1 = new Bar(chart1.getChart());
        series1.setColor(Color.RED);
        series2 = new Bar(chart1.getChart());
        series2.setColor(Color.GREEN);

        for (int i=0; i < chart1.getSeriesCount(); i++) {
            chart1.getSeries(i).fillSampleValues(3);
            chart1.getSeries(i).getMarks().setVisible(true);
            ((Bar)chart1.getSeries(i)).getBrush().setImageMode(ImageMode.TILE);
        }

        series1.getBrush().loadImage(ChartSamplePanel.class.getResource(URL_IMAGE1));
        series2.getBrush().loadImage(ChartSamplePanel.class.getResource(URL_IMAGE2));

        usePatternButton = new JCheckBox("Use Pattern");
        usePatternButton.setSelected(true);
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(usePatternButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JCheckBox usePatternButton;
    private final static String URL_IMAGE1 = "images/barimage1.jpg";
    private final static String URL_IMAGE2 = "images/barimage2.jpg";
}
