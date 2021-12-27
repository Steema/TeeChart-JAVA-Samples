/*
 * DrawAllDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.fastline;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.FastLine;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class DrawAllDemo extends ChartSamplePanel
    implements ActionListener {

    private FastLine lineSeries;

    /**
     * Creates a new instance of DrawAllDemo
     */
    public DrawAllDemo() {
        super();
        drawAllPoints.addActionListener(this);
        drawNonRepeated.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == drawAllPoints) {
            lineSeries.setDrawAllPoints(true);
        } else if (source == drawNonRepeated) {
            lineSeries.setDrawAllPoints(false);
        }
    }

    protected void initComponents() {
        super.initComponents();

        double[] MyX = new double[NUM_POINTS];
        double[] MyY = new double[NUM_POINTS];

        Random generator = new Random();
        double tmpRandom = 10000 * generator.nextDouble();

        for (int t=0; t < NUM_POINTS; t++) {
            tmpRandom += (100 * generator.nextDouble())-50;
            MyX[t] = t;
            MyY[t] = tmpRandom;
        }


        lineSeries = new com.steema.teechart.styles.FastLine(chart1.getChart());
        lineSeries.getXValues().count = NUM_POINTS;
        lineSeries.getXValues().value = MyX;
        lineSeries.getYValues().count = NUM_POINTS;
        lineSeries.getYValues().value = MyY;

        // tell lineSeries to draw non-repeated points only ( much faster ! )
        lineSeries.setDrawAllPoints(false);

        drawAllPoints = new JRadioButton("All points (one million)");
        drawNonRepeated = new JRadioButton("Non-repeated X only");
        drawNonRepeated.setSelected(true);

        ButtonGroup group = new ButtonGroup();
        group.add(drawAllPoints);
        group.add(drawNonRepeated);
    }

    protected void initGUI() {
        super.initGUI();

        // Set axis calculations in "fast mode".
        // Note: For Windows Me and 98 might produce bad drawings when
        //       chart zoom is very big.
        //TODO chart1.getAxes().setFastCalc(true);
        chart1.getAspect().setView3D(false);
        chart1.getAspect().setSmoothingMode(false);
        chart1.getAspect().setTextSmooth(false);        
        chart1.getLegend().setVisible(false);
        chart1.getHeader().setVisible(true);
        chart1.setText("One million points !\nDisplaying non-repeated X position only.");

        chart1.getZoom().setAnimated(true);
        chart1.getZoom().setAnimatedSteps(3);
        chart1.getZoom().getBrush().setColor(Color.BLUE);
        chart1.getZoom().getBrush().setSolid(true);
        chart1.getZoom().getPen().setColor(Color.RED);
        chart1.getZoom().getPen().setWidth(3);

        JLabel tmpLabel;
        JPanel tmpPane = getButtonPane();
        {
            tmpLabel = new JLabel("Draw:");
            tmpLabel.setDisplayedMnemonic('D');
            tmpPane.add(tmpLabel);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(drawAllPoints);
            tmpPane.add(drawNonRepeated);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JRadioButton drawAllPoints, drawNonRepeated;

    private final static int NUM_POINTS = 1000000;  // one million !
}
