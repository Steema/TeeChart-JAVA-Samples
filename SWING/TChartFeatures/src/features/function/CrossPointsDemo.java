/*
 * CrossPointsDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.function;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.functions.CrossPoints;
import com.steema.teechart.styles.CustomStack;
import com.steema.teechart.styles.Line;
import com.steema.teechart.styles.PointerStyle;
import com.steema.teechart.styles.SeriesPointer;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class CrossPointsDemo extends ChartSamplePanel
    implements ItemListener {

    private Line lineSeries;

    /**
     * Creates a new instance of CrossPointsDemo
     */
    public CrossPointsDemo() {
        super();
        viewCrossPointsButton.addItemListener(this);
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);

        if (source == viewCrossPointsButton) {
            lineSeries.setVisible(isSelected);
        };
    }

    protected void initComponents() {
        super.initComponents();

        Line tmpSeries;
        for (int i=0; i < 3; i++) {
            tmpSeries = new com.steema.teechart.styles.Line(chart1.getChart());
            tmpSeries.setTitle("Series"+(i+1));
            tmpSeries.setStacked(CustomStack.NONE);
            tmpSeries.getMarks().setVisible(false);

            SeriesPointer tmpPointer = tmpSeries.getPointer();
            tmpPointer.setHorizSize(2);
            tmpPointer.setVertSize(2);
            tmpPointer.setInflateMargins(true);
            tmpPointer.setStyle(PointerStyle.RECTANGLE);
            tmpPointer.setVisible(true);
        }

        chart1.getSeries(0).fillSampleValues(25);
        chart1.getSeries(1).fillSampleValues(25);

        lineSeries = (Line)chart1.getSeries(2);
        lineSeries.setColor(Color.YELLOW);

        CrossPoints tmpFunction = new com.steema.teechart.functions.CrossPoints(chart1.getChart());
        tmpFunction.setPeriod(0); //all points

        ArrayList tmpArray = new ArrayList();
        tmpArray.add(chart1.getSeries(0));
        tmpArray.add(chart1.getSeries(1));
        lineSeries.setDataSource(tmpArray);
        lineSeries.setFunction(tmpFunction);

        chart1.getAspect().setView3D(false);

        viewCrossPointsButton = new JCheckBox("View CrossPoints line");
        viewCrossPointsButton.setSelected(true);
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(viewCrossPointsButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JCheckBox viewCrossPointsButton;
}
