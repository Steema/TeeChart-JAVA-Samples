/*
 * LegendImageDemo.java
 *
 * <p>Copyright: Copyright (c) 2004-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.legend;

import com.steema.teechart.ImageMode;
import com.steema.teechart.styles.Series;
import com.steema.teechart.styles.WindRose;
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
public class LegendImageDemo extends ChartSamplePanel
    implements ItemListener {

    /** Creates a new instance of LegendImageDemo */
    public LegendImageDemo() {
        super();
        imageButton.addItemListener(this);
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == imageButton) {
            if (isSelected) {
                chart1.getLegend().getBrush().loadImage(URL_IMAGE);
            } else {
                chart1.getLegend().getBrush().clearImage();
            }
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getAspect().setView3D(false);
        chart1.getLegend().setTransparent(false);
        chart1.getLegend().getBrush().setImageMode(ImageMode.TILE);
        chart1.getLegend().getBrush().loadImage(ChartSamplePanel.class.getResource(URL_IMAGE));
    }

    protected void initComponents() {
        super.initComponents();

        Series series = new WindRose(chart1.getChart());
        series.fillSampleValues(8);

        imageButton = new JCheckBox("Legend Image");
        imageButton.setSelected(chart1.getLegend().getBrush().getImage() != null);
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(imageButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JCheckBox imageButton;
    private final static String URL_IMAGE = "images/pattern2.jpg";
}
