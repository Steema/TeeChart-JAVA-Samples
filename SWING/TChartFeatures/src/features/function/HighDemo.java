/*
 * HighDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.function;

import com.steema.teechart.TextShapeStyle;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.functions.Function;
import com.steema.teechart.styles.Bar;
import com.steema.teechart.styles.CustomStack;
import com.steema.teechart.styles.Line;
import com.steema.teechart.styles.PointerStyle;

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
public class HighDemo extends ChartSamplePanel
    implements ItemListener {

    private Line lineSeries ;
    private Bar barSeries;
    private Function highFunction;

    /**
     * Creates a new instance of HighDemo
     */
    public HighDemo() {
        super();
        every2PointButton.addItemListener(this);
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);

        if (source == every2PointButton) {
            if (isSelected) {
                highFunction.setPeriod(2);
            } else {
                highFunction.setPeriod(0);
            }
        };
    }

    protected void initComponents() {
        super.initComponents();

        barSeries = new com.steema.teechart.styles.Bar(chart1.getChart());
        barSeries.setTitle("Source");
        barSeries.setColor(Color.RED);
        barSeries.getMarks().setColor(Color.BLACK);
        barSeries.getMarks().setBackColor(Color.BLACK);
        barSeries.getMarks().getFont().setColor(Color.RED);
        barSeries.getMarks().setArrowLength(20);
        barSeries.fillSampleValues(6);

        lineSeries = new com.steema.teechart.styles.Line(chart1.getChart());
        lineSeries.setTitle("High");
        lineSeries.setColor(Color.GREEN);
        lineSeries.setStacked(CustomStack.NONE);
        lineSeries.getMarks().setArrowLength(8);
        lineSeries.getMarks().getShadow().setVisible(false);
        lineSeries.getMarks().setShapeStyle(TextShapeStyle.ROUNDRECTANGLE);
        lineSeries.getMarks().setVisible(true);
        lineSeries.getPointer().setColor(Color.OLIVE);
        lineSeries.getPointer().setInflateMargins(false);
        lineSeries.getPointer().setStyle(PointerStyle.RECTANGLE);
        lineSeries.getPointer().setVisible(true);

        highFunction = new com.steema.teechart.functions.High();
        highFunction.setChart(chart1.getChart());
        highFunction.setPeriod(0); //all points

        lineSeries.setDataSource(barSeries);
        lineSeries.setFunction(highFunction);

        chart1.getHeader().setVisible(true);
        chart1.setText("High function");

        every2PointButton = new JCheckBox("By every 2 points");
        every2PointButton.setSelected(false);
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(every2PointButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JCheckBox every2PointButton;
}
