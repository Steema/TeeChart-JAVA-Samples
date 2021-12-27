/*
 * TransparencyDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.histogram;

import com.steema.teechart.ImageMode;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.Histogram;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class TransparencyDemo extends ChartSamplePanel
    implements ChangeListener {

    private Histogram series1, series2;

    /** Creates a new instance of TransparencyDemo */
    public TransparencyDemo() {
        super();
        transparencySpinner.addChangeListener(this);
    }

    public void stateChanged(ChangeEvent e) {
        Object source = e.getSource();
        if (source == transparencySpinner) {
            int value = ((SpinnerNumberModel)transparencySpinner.getModel()).getNumber().intValue();
            series2.setTransparency(value);
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getPanel().getGradient().setStartColor(Color.SKY_BLUE);
        chart1.getPanel().getGradient().setEndColor(Color.BLUE);
        chart1.getPanel().getGradient().setVisible(true);
        chart1.getLegend().setVisible(false);
        chart1.getHeader().getFont().setColor(Color.WHITE);
        chart1.getHeader().getFont().setSize(21);
        chart1.setText("Histogram Series with Transparency");
        //TODO    BottomAxis.Grid.SmallDots = True
        chart1.getAxes().getLeft().getGrid().setColor(Color.WHITE);
        //TODO LeftAxis.Grid.SmallDots = True
        chart1.getAspect().setView3D(false);
    }

    protected void initComponents() {
        super.initComponents();

        series1 = new Histogram(chart1.getChart());
        series2 = new Histogram(chart1.getChart());
        series2.getBrush().setImageMode(ImageMode.TILE);
        series2.getBrush().setColor(Color.GRAY);
        series2.getBrush().loadImage(ChartSamplePanel.class.getResource(URL_BRUSHPATTERN));
        series2.setTransparency(60);
        series2.getBrush().setTransparency(60);

        for (int t=0; t < chart1.getSeriesCount(); t++) {
            chart1.getSeries(t).fillSampleValues(25);
        }

        transparencySpinner = new JSpinner(
                new SpinnerNumberModel(
                    series2.getTransparency(),
                    0,
                    100,
                    5)
        );
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            JLabel tmpLabel = new JLabel("Transparency %:");
            tmpLabel.setDisplayedMnemonic('T');
            tmpLabel.setLabelFor(transparencySpinner);
            tmpPane.add(tmpLabel);
            tmpPane.add(transparencySpinner);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JSpinner transparencySpinner;
    private final static String URL_BRUSHPATTERN = "images/zigzag.png";
}
