/*
 * DrawTextDemo.java
 *
 * <p>Copyright: (c) 2004-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.legend;

import com.steema.teechart.Rectangle;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.IGraphics3D;
import com.steema.teechart.drawing.StringAlignment;
import com.steema.teechart.events.ChartDrawEvent;
import com.steema.teechart.events.ChartPaintAdapter;
import com.steema.teechart.legend.Legend;
import com.steema.teechart.legend.LegendAdapter;
import com.steema.teechart.legend.LegendItemCoordinates;
import com.steema.teechart.legend.LegendStyle;
import com.steema.teechart.legend.LegendTextStyle;
import com.steema.teechart.styles.Bar;
import com.steema.teechart.styles.Series;
import com.steema.teechart.styles.StringList;
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
public class DrawTextDemo extends ChartSamplePanel
    implements ItemListener {

    /** Creates a new instance of MultipleDemo */
    public DrawTextDemo() {
        super();
        drawTextButton.addItemListener(this);
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == drawTextButton) {
            chart1.repaint();
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getLegend().setLegendStyle(LegendStyle.VALUES);
        chart1.getLegend().setTextStyle(LegendTextStyle.PLAIN);
        chart1.setLegendResolver( new LegendAdapter() {
            public Rectangle getBounds(Legend legend, Rectangle rectangle) {
                Rectangle r= new Rectangle(rectangle);
                if (drawTextButton.isSelected()) {
                    r.setLeft(r.getLeft()-40);
                    r.setBottom(r.getBottom()+30);
                }
                return r;
            }
            public LegendItemCoordinates getItemCoordinates(Legend legend, LegendItemCoordinates coordinates) {
                LegendItemCoordinates c = new LegendItemCoordinates(coordinates);
                if (drawTextButton.isSelected()) {
                    c.setX(c.getX()-40);
                    c.setXColor(c.getXColor()-40);
                }
                return c;
            };
        });

        chart1.addChartPaintListener( new ChartPaintAdapter() {
            public void chartPainted(ChartDrawEvent e) {
                if (drawTextButton.isSelected()) {
                    Rectangle r = chart1.getLegend().getShapeBounds();
                    IGraphics3D g = chart1.getGraphics3D();
                    g.horizontalLine(r.getLeft(), r.getRight(), r.getBottom()-30);
                    g.setTextAlign(StringAlignment.NEAR);
                    g.getFont().setSize(10);
                    g.getFont().setColor(Color.NAVY);
                    g.textOut(r.getLeft()+8, r.getBottom()-28, "Additional text");
                    g.textOut(r.getLeft()+8, r.getBottom()-16, "displayed here.");
                }
            };
        });
    }

    protected void initSeries() {
        Series tmpSeries = new Bar(chart1.getChart());
        tmpSeries.getMarks().setVisible(true);
        chart1.getSeries().fillSampleValues(6);
        String[] tmpLabels = new String[] {"one", "two", "three", "four", "five", "six"};
        tmpSeries.setLabels(new StringList(tmpLabels));
    }

    protected void initComponents() {
        super.initComponents();
        initSeries();
        chart1.getLegend().setSeries(chart1.getSeries(0));
        drawTextButton = new JCheckBox("Draw text on legend");
        drawTextButton.setSelected(true);
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(drawTextButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JCheckBox drawTextButton;
}
