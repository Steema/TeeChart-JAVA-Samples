/*
 * OpaqueZonesDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.axes;
import com.steema.teechart.Rectangle;
import com.steema.teechart.axis.Axis;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.events.ChartDrawEvent;
import com.steema.teechart.events.SeriesPaintAdapter;
import com.steema.teechart.styles.Line;
import com.steema.teechart.styles.Series;
import com.steema.teechart.tools.ColorLine;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import features.ChartSamplePanel;
import java.util.Iterator;

/**
 *
 * @author tom
 */
public class OpaqueZonesDemo extends ChartSamplePanel
    implements ItemListener, ChangeListener {

    private Line series1, series2, series3, series4;

    /** Creates a new instance of OpaqueZonesDemo */
    public OpaqueZonesDemo() {
        super();
        opaqueButton.addItemListener(this);
        axesScrollBar.addChangeListener(this);
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == opaqueButton) {
            chart1.repaint();
        }
    }

    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider)e.getSource();
        if (!source.getValueIsAdjusting()) {
            int value = (int)source.getValue();
            doScroll(series1, value);
            doScroll(series2, value);
            doScroll(series3, value);
            doScroll(series4, value);
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getAxes().getLeft().setEndPosition(25.0);
        chart1.getAspect().setView3D(false);
        chart1.getLegend().setVisible(false);
    }

    protected void initAxes() {
       Axis tmpAxis;

       tmpAxis = chart1.getAxes().getCustom().getNew();
       tmpAxis.getAxisPen().setColor(Color.LIME);
       tmpAxis.setHorizontal(false);
       tmpAxis.setOtherSide(false);
       tmpAxis.setStartPosition(25.0);
       tmpAxis.setEndPosition(50.0);

       tmpAxis = chart1.getAxes().getCustom().getNew();
       tmpAxis.getAxisPen().setColor(Color.BLUE);
       tmpAxis.setHorizontal(false);
       tmpAxis.setOtherSide(false);
       tmpAxis.setStartPosition(50.0);
       tmpAxis.setEndPosition(75.0);

       tmpAxis = chart1.getAxes().getCustom().getNew();
       tmpAxis.getAxisPen().setColor(Color.RED);
       tmpAxis.getGrid().setVisible(false);
       tmpAxis.setHorizontal(false);
       tmpAxis.setOtherSide(false);
       tmpAxis.setStartPosition(75.0);
    }

    protected void initComponents() {
        super.initComponents();
        initAxes();

        series1 = new Line(chart1.getChart());
        series2 = new Line(chart1.getChart());
        series2.setCustomVertAxis(0);
        series3 = new Line(chart1.getChart());
        series3.setCustomVertAxis(1);
        series4 = new Line(chart1.getChart());
        series4.setCustomVertAxis(2);

        Iterator it = chart1.getChart().series();
        while (it.hasNext()) {
            Series s = (Series) it.next();
            s.addSeriesPaintListener( new SeriesPaintAdapter() {

                protected Rectangle seriesRectangle(Series s) {
                    Rectangle tmp = new Rectangle();
                    tmp.setLeft((int)s.getHorizAxis().getStartPosition());
                    tmp.setRight((int)s.getHorizAxis().getEndPosition());
                    tmp.setTop((int)s.getVertAxis().getStartPosition());
                    tmp.setBottom((int)s.getVertAxis().getEndPosition());
                    return tmp;
                }

                public void seriesPainting(ChartDrawEvent e) {
                    // make opaque
                    if (opaqueButton.isSelected()) {
                        chart1.getGraphics3D().clipRectangle(
                                seriesRectangle((Series)e.getSource())
                        );
                    }
                };

                public void seriesPainted(ChartDrawEvent e) {
                    if (opaqueButton.isSelected()) {
                        chart1.getGraphics3D().unClip();
                    }
                };
            });
        }

        chart1.getSeries().fillSampleValues(100);

        ColorLine tmpTool;
        tmpTool = new ColorLine(chart1.getAxes().getCustom().getAxis(0));
        tmpTool.setValue(74.0);

        tmpTool = new ColorLine(chart1.getAxes().getCustom().getAxis(1));

        tmpTool = new ColorLine(chart1.getAxes().getCustom().getAxis(2));
        tmpTool.setValue(3.0);


        opaqueButton = new JCheckBox("Opaque zones");
        opaqueButton.setSelected(true);

        axesScrollBar = new JSlider(JSlider.HORIZONTAL, 1, 100, 50);
    }

    protected void initGUI() {
        super.initGUI();
        JLabel tmpLabel;
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(opaqueButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpLabel = new JLabel("Scroll: ");
            tmpLabel.setDisplayedMnemonic('S');
            tmpLabel.setLabelFor(axesScrollBar);
            tmpPane.add(tmpLabel);
            tmpPane.add(axesScrollBar);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private void doScroll(Series s, int value) {
        double tmp = (value-50)*(s.getYValues().getMaximum()-s.getYValues().getMinimum())/100;
        s.getVertAxis().setMinMax(s.getMaxYValue()+tmp, s.getMinYValue()+tmp);
    }

    private JCheckBox opaqueButton;
    private JSlider axesScrollBar;
}
