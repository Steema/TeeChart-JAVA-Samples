/*
 * CursorProgDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.tool.cursor;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.PointerStyle;
import com.steema.teechart.styles.Points;
import com.steema.teechart.styles.SeriesPointer;
import com.steema.teechart.styles.ValueList;
import com.steema.teechart.tools.CursorTool;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import features.ChartSamplePanel;

//@todo check when color is correct

/**
 *
 * @author tom
 */
public class CursorProgDemo extends ChartSamplePanel
        implements ActionListener {

    private CursorTool tool1;
    private Points pointSeries;

    /**
     * Creates a new instance of CursorProgDemo
     */
    public CursorProgDemo() {
        super();
        leftButton.addActionListener(this);
        rightButton.addActionListener(this);
        topButton.addActionListener(this);
        bottomButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == leftButton) {
            tool1.setXValue( tool1.getXValue() - 1);
        } else if (source == rightButton) {
            tool1.setXValue( tool1.getXValue() + 1);
        } else if (source == topButton) {
            tool1.setYValue( tool1.getYValue() - getSomeValue());
        } else if (source == bottomButton) {
            tool1.setYValue( tool1.getYValue() + getSomeValue());
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getLegend().setVisible(false);
        chart1.getAspect().setView3D(false);
    }

    protected void initComponents() {
        super.initComponents();

        pointSeries = new com.steema.teechart.styles.Points(chart1.getChart());
        pointSeries.fillSampleValues(20);

        SeriesPointer tmpPointer = pointSeries.getPointer();
        tmpPointer.getBrush().setColor(Color.YELLOW);
        tmpPointer.setHorizSize(2);
        tmpPointer.setVertSize(2);
        tmpPointer.setInflateMargins(true);
        tmpPointer.setStyle(PointerStyle.RECTANGLE);

        tool1 = new CursorTool(chart1.getChart());
        tool1.setSeries(pointSeries);
        tool1.getPen().setColor(Color.GREEN);
        tool1.getPen().setWidth(3);

        leftButton = new JButton("< Left");
        rightButton = new JButton("> Right");
        topButton = new JButton("^ Top");
        bottomButton = new JButton("v Bottom");
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(leftButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(rightButton);
            tmpPane.add(Box.createHorizontalStrut(30));
            tmpPane.add(topButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(bottomButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private double getSomeValue() {
        ValueList tmpList = pointSeries.getYValues();
        return (tmpList.getMaximum() - tmpList.getMinimum()) / 10.0;
    }

    private JButton leftButton, rightButton, topButton, bottomButton;
}
