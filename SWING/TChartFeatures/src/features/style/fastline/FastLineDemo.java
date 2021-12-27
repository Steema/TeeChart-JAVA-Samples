/*
 * FastLineDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.fastline;

import com.steema.teechart.axis.Axis;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.DashStyle;
import com.steema.teechart.styles.FastLine;
import com.steema.teechart.styles.HorizontalAxis;
import com.steema.teechart.styles.VerticalAxis;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Random;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class FastLineDemo extends ChartSamplePanel
        implements ActionListener, ItemListener {

    private FastLine lineSeries1, lineSeries2;

    /**
     * Creates a new instance of FastLineDemo
     */
    public FastLineDemo() {
        super();
        speedButton.addActionListener(this);
        for (int i=0; i < optionButtons.length; i++) {
            optionButtons[i].addItemListener(this);
        }
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == speedButton) {

            chart1.getZoom().setAnimated(false);

            long startTime = System.currentTimeMillis();

            chart1.getZoom().zoomPercent(95);
            for (int t=0; t < 30; t++) {
                chart1.getZoom().zoomPercent(95); // 5% zoom in
                chart1.paintImmediately(chart1.getBounds());
            }

            for (int t=0; t < 30; t++) {
                chart1.getZoom().zoomPercent(105); // 5% zoom out
                chart1.paintImmediately(chart1.getBounds());
            }

            long endTime = System.currentTimeMillis();
            chart1.getZoom().setAnimated(true);
            chart1.getZoom().undo();

            JOptionPane.showMessageDialog(null, "Time to plot 2000 points\n61 times:\n"+String.valueOf(endTime-startTime)+" milliseconds." );
        }
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);

        if (source == optionButtons[0]) {
            //@TODO chart1.setBufferedDisplay(isSelected);
        } else if (source == optionButtons[1]) {
            chart1.getAxes().setVisible(isSelected);
        } else if (source == optionButtons[2]) {
            chart1.setClipPoints(isSelected);
        }
    }

    protected void initSeries() {
        Random generator = new Random();
        int tmpRandom;

        lineSeries1 = new com.steema.teechart.styles.FastLine(chart1.getChart());
        lineSeries2 = new com.steema.teechart.styles.FastLine(chart1.getChart());
        for (int t=0; t < 1000; t++) {
            if ( t != 500) {
                tmpRandom = generator.nextInt(Math.abs(500-t))-(Math.abs(500-t) / 2);
                lineSeries1.add(1000-t+tmpRandom);
                lineSeries2.add(t+tmpRandom);
            }
        }

        lineSeries1.getLinePen().setStyle(DashStyle.DOT);
    }

    protected void initComponents() {
        super.initComponents();
        initSeries();

        speedButton = new JButton("Test Speed");
        optionButtons  = new JCheckBox[3];

        optionButtons[0] = new JCheckBox("Buffered");
        optionButtons[1] = new JCheckBox("Draw Axes");
        optionButtons[2] = new JCheckBox("Clip Points");

        for (int i=0; i < optionButtons.length; i++) {
            optionButtons[i].setSelected(true);
        }
    }

    protected void initGUI() {
        super.initGUI();

        // Avoid flickering when repainting the chart (default)
        //@TODO chart1.setBufferedDisplay(true);

        chart1.getAxes().setVisible(true);
        chart1.setClipPoints(true);

        // hide things for better speed
        chart1.getAspect().setView3D(false);
        chart1.getLegend().setVisible(false);
        chart1.getFooter().setVisible(false);
        chart1.getHeader().setVisible(false);

/*
  { some speed improvement if... }
  TeeDefaultCapacity:=1000;

  // Set axis calculations in "fast mode".
  // Note: For Windows Me and 98 might produce bad drawings when
  //       chart zoom is very big.
  Chart1.Axes.FastCalc:=True;
 */

        chart1.getZoom().setAnimated(true);
        chart1.getZoom().setAnimatedSteps(15);

        Axis tmpAxis;
        tmpAxis = chart1.getAxes().getBottom();
        tmpAxis.getGrid().setColor(Color.BLUE);
        tmpAxis.getLabels().getFont().setColor(Color.FUCHSIA);
        tmpAxis.getTicks().setColor(Color.LIME);
        tmpAxis = chart1.getAxes().getLeft();
        tmpAxis.getGrid().setColor(Color.BLUE);
        tmpAxis.getLabels().getFont().setColor(Color.NAVY);
        tmpAxis.getTicks().setColor(Color.RED);
        tmpAxis = chart1.getAxes().getRight();
        tmpAxis.getGrid().setVisible(false);
        tmpAxis.getLabels().getFont().setColor(Color.RED);
        tmpAxis.getTicks().setColor(Color.BLUE);
        tmpAxis = chart1.getAxes().getTop();
        tmpAxis.getGrid().setVisible(false);
        tmpAxis.getLabels().getFont().setColor(Color.GREEN);
        tmpAxis.getTicks().setColor(Color.YELLOW);

        lineSeries1.setVerticalAxis(VerticalAxis.RIGHT);
        lineSeries1.setHorizontalAxis(HorizontalAxis.TOP);

        JPanel tmpPane = getButtonPane();
        {
            for (int i=0; i < optionButtons.length; i++) {
                tmpPane.add(optionButtons[i]);
                tmpPane.add(Box.createHorizontalStrut(10));
            }
            tmpPane.add(speedButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JButton speedButton;
    private JCheckBox[] optionButtons;
}
