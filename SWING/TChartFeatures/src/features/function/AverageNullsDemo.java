/*
 * AverageNullsDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.function;

import com.steema.teechart.TextShapeStyle;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.functions.Average;
import com.steema.teechart.styles.Bar;
import com.steema.teechart.styles.CustomStack;
import com.steema.teechart.styles.Line;
import com.steema.teechart.styles.MarksStyle;
import com.steema.teechart.styles.PointerStyle;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class AverageNullsDemo extends ChartSamplePanel
        implements ItemListener {

    private Line lineSeries ;
    private Bar barSeries;
    private Average avgFunction;

    /**
     * Creates a new instance of AverageNullsDemo
     */
    public AverageNullsDemo() {
        super();
        includeNullsButton.addItemListener(this);
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);

        if (source == includeNullsButton) {
            avgFunction.setIncludeNulls(isSelected);
            setLabelAverage();
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
        barSeries.getMarks().setStyle(MarksStyle.VALUE);
        barSeries.getXValues().setDateTime(false);

        /* Add some points and one null point... */
        barSeries.clear();
        barSeries.add( 10 , "One", Color.RED);
        barSeries.add( 20 , "Two", Color.RED);
        barSeries.add("Three");
        barSeries.add( 40 , "Four", Color.RED);
        barSeries.add( 50 , "Five", Color.RED);

        lineSeries = new com.steema.teechart.styles.Line(chart1.getChart());
        lineSeries.setTitle("Average");
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
        lineSeries.getXValues().setDateTime(false);


        avgFunction = new com.steema.teechart.functions.Average();
        avgFunction.setChart(chart1.getChart());
        avgFunction.setPeriod(0); //all points
        avgFunction.setIncludeNulls(false);

        lineSeries.setDataSource(barSeries);
        lineSeries.setFunction(avgFunction);

        chart1.getHeader().setVisible(true);
        chart1.setText("Average function and NULL points");


        includeNullsButton = new JCheckBox("Include NULLS");
        includeNullsButton.setSelected(false);

        avgLabel = new JLabel("");
        setLabelAverage();
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(includeNullsButton);
            tmpPane.add(Box.createHorizontalStrut(20));
            tmpPane.add(new JLabel("Average is:"));
            tmpPane.add(Box.createHorizontalStrut(5));
            tmpPane.add(avgLabel);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private void setLabelAverage() {
        /* calculate the sum and number of points... */
        double tmp=0;
        int tmpCount=0;
        for (int t=0; t < barSeries.getCount(); t++) {
            /* consider or not null points... */
            if (avgFunction.getIncludeNulls() || (!barSeries.isNull(t)) ) {
                tmp = tmp + barSeries.getYValues().getValue(t);
                tmpCount++;
            }
        }
        avgLabel.setText( tmp + "/" + tmpCount + "=" + (tmp/tmpCount) );
    }

    private JCheckBox includeNullsButton;
    private JLabel avgLabel;
}
