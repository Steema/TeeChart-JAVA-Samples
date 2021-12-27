/*
 * CurveFittingCoefDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.function;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.functions.CurveFitting;
import com.steema.teechart.styles.FastLine;
import com.steema.teechart.styles.HorizontalAxis;
import com.steema.teechart.styles.SeriesMarks;
import com.steema.teechart.styles.SeriesPointer;
import com.steema.teechart.styles.Shape;
import com.steema.teechart.styles.ShapeXYStyle;
import com.steema.teechart.styles.VerticalAxis;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;

import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class CurveFittingCoefDemo extends ChartSamplePanel {

    private FastLine curve1Series, curve2Series;
    private Shape shapeSeries;
    private CurveFitting fittingFunction;

    /**
     * Creates a new instance of CurveFittingCoefDemo
     */
    public CurveFittingCoefDemo() {
        super();

        chart1.addMouseMotionListener( new MouseMotionAdapter() {
            public void mouseMoved(MouseEvent e) {
                int x = e.getX();
                // convert from "X" pixels to axis...
                double tmpX = chart1.getAxes().getBottom().calcPosPoint(x);

                // calculate the "Y" value for a given "X"
                double tmpY = fittingFunction.getCurveYValue(curve1Series, tmpX);

                // convert from "Y" axis to pixels...
                int y = chart1.getAxes().getLeft().calcPosValue(tmpY);
                                
                // set the "circle shape" bounds...
                shapeSeries.setX0(x-10);
                shapeSeries.setX1(x+10);
                shapeSeries.setY0(y-10);
                shapeSeries.setY1(y+10);
                shapeSeries.setActive(true);                
            }
        });
    }

    protected void initComponents() {
        super.initComponents();

        SeriesMarks tmpMarks;
        SeriesPointer tmpPointer;

        chart1.getAxes().getBottom().setMinMax(98, 99);
        
        curve1Series = new com.steema.teechart.styles.FastLine(chart1.getChart());
        curve1Series.setTitle("Source");
        curve1Series.setColor(Color.RED);
        curve1Series.fillSampleValues(100);

        curve2Series = new com.steema.teechart.styles.FastLine(chart1.getChart());
        curve2Series.setTitle("Curve");
        curve2Series.setColor(Color.GREEN);


        for (int i=0; i < chart1.getSeriesCount(); i++) {
            chart1.getSeries(i).getMarks().setVisible(false);
            ((FastLine)chart1.getSeries(i)).setStairs(false);
        }

        fittingFunction = new com.steema.teechart.functions.CurveFitting(chart1.getChart());
        fittingFunction.setPeriod(1);

        curve2Series.setDataSource(curve1Series);
        curve2Series.setFunction(fittingFunction);

        shapeSeries = new Shape(chart1.getChart());        
        shapeSeries.getMarks().setVisible(false);
        shapeSeries.setHorizontalAxis(HorizontalAxis.TOP);
        shapeSeries.setVerticalAxis(VerticalAxis.RIGHT);
        shapeSeries.getVertAxis().setVisible(false);
        shapeSeries.getHorizAxis().setVisible(false);
        shapeSeries.setColor(Color.WHITE);
        shapeSeries.getBrush().setColor(Color.WHITE);
        shapeSeries.setXYStyle(ShapeXYStyle.PIXELS);
        shapeSeries.setTitle("Shape");
        shapeSeries.setShowInLegend(false);    
        shapeSeries.setActive(false);

        formatter = new DecimalFormat("0.0##");
        expressionLabel = new JLabel();

        showFunction();
    }

    protected void initGUI() {
        super.initGUI();

        chart1.getHeader().setVisible(true);
        chart1.setText("Cumulative function");
        chart1.getAspect().setView3D(false);

        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(new JLabel("Function:"));
            tmpPane.add(expressionLabel);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private void showFunction() {

        StringBuffer sb = new StringBuffer().append("y=");

        for (int t=1; t <= fittingFunction.getPolyDegree(); t++) {
            sb.append(" ");

            double tmpValue = fittingFunction.coefficient(t);
            if (tmpValue > 0) { sb.append("+"); };
            sb.append(formatter.format(tmpValue));
            sb.append("*x");

            if (t>1) { sb.append("^").append(t); };
        };

        /* show the expression */
        expressionLabel.setText(sb.toString());
    }

    private NumberFormat formatter;
    private JLabel expressionLabel;
}
