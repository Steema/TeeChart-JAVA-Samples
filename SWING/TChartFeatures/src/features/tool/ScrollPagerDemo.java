package features.tool;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.StringAlignment;
import com.steema.teechart.editors.ChartEditor;
import com.steema.teechart.events.ChangeEvent;
import com.steema.teechart.events.ChangeListener;
import com.steema.teechart.styles.FastLine;
import com.steema.teechart.themes.ThemesList;
import com.steema.teechart.tools.Annotation;
import com.steema.teechart.tools.AnnotationPosition;
import com.steema.teechart.tools.NearestPoint;
import com.steema.teechart.tools.NearestPointDirection;
import com.steema.teechart.tools.ScrollPager;
import features.ChartSamplePanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Steema Software 2011
 */
public class ScrollPagerDemo extends ChartSamplePanel
        implements ActionListener, ItemListener, ChangeListener {

    ScrollPager tool1;
    FastLine tmpSeries;
    Annotation annotate;
    NearestPoint point;
    private JButton leftTopButton;

    public void actionPerformed(ActionEvent e) {
        ChartEditor.editTool(tool1, "ScrollPager Tool");
    }

    public void itemStateChanged(ItemEvent e) {

        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);

        if (source == leftTopButton) {
            tool1.setActive(isSelected);
        }
    }

    /**
     * Creates a new instance of AxisBreakDemo
     */
    public ScrollPagerDemo() {
        super();
        addComponentListener(new java.awt.event.ComponentAdapter() {

            public void componentResized(java.awt.event.ComponentEvent evt) {
                scrollPagerDemoResized(evt);
            }
        });
    }

    private void scrollPagerDemoResized(java.awt.event.ComponentEvent evt) {
        if (tool1.getSeries() == null) {
            tool1.setSeries(tmpSeries);
            ThemesList.applyTheme(tool1.getSubChartTChart().getChart(), 1);
        }
    }

    protected void initComponents() {
        super.initComponents();

        chart1.getChart().getAspect().setView3D(false);
        chart1.getChart().getLegend().setVisible(false);

        tmpSeries = new FastLine(chart1.getChart());
        tmpSeries.setColor(Color.GREEN_YELLOW);
        tmpSeries.fillSampleValues(1000);
        tmpSeries.getMarks().setVisible(false);

        tool1 = new ScrollPager(chart1.getChart());
            
        annotate = new Annotation(chart1.getChart());
        annotate.setPosition(AnnotationPosition.RIGHTTOP);
        annotate.setText("YValue:");
        annotate.getShape().getShadow().setVisible(false);
        annotate.getShape().getFont().setColor(chart1.getHeader().getColor());
        annotate.getShape().setColor(tool1.getPointerHighlightColor());
        annotate.getShape().getPen().setVisible(false);
        annotate.setTextAlign(StringAlignment.CENTER);
        
        point = new NearestPoint(chart1.getChart());
        point.getBrush().setVisible(true);
        point.getBrush().setColor(tool1.getPointerHighlightColor());
        point.setDrawLine(false);
        point.setSize(6);
        point.setDirection(NearestPointDirection.HORIZONTAL);
        point.setSeries(tmpSeries);
        point.addChangeListener(this);
        
        leftTopButton = new JButton("Edit...");
        leftTopButton.addActionListener(this);

        ThemesList.applyTheme(chart1.getChart(), 1);
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(leftTopButton);
        }
    }

    public void stateChanged(ChangeEvent e) {
        if(point.point > -1) {
            annotate.setText("YValue: " + tmpSeries.getYValues().getValue(point.point));
        }
    }
}
