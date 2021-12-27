/*
 * ArrowDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.arrow;
import com.steema.teechart.Aspect;
import com.steema.teechart.Walls.BackWall;
import com.steema.teechart.Walls.BottomWall;
import com.steema.teechart.Walls.LeftWall;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.GradientDirection;
import com.steema.teechart.styles.Arrow;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Random;
import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.Timer;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class ArrowDemo extends ChartSamplePanel
        implements ItemListener {

    private Arrow arrowSeries;
    /** Creates a new instance of ArrowDemo */
    public ArrowDemo() {
        super();
        animateButton.addItemListener(this);
        colorEachButton.addItemListener(this);
        view3DButton.addItemListener(this);
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == animateButton) {
            if (isSelected) {
                timer.start();
            } else {
                timer.stop();
            }
        } else if (source == view3DButton) {
            chart1.getAspect().setView3D(isSelected);
        } else if (source == colorEachButton) {
            arrowSeries.setColorEach(isSelected);
        }
    }

    protected void initComponents() {
        super.initComponents();

        arrowSeries = new com.steema.teechart.styles.Arrow(chart1.getChart());
        arrowSeries.setArrowWidth(32);
        arrowSeries.setArrowHeight(32);
        arrowSeries.getXValues().setDateTime(false);
        arrowSeries.getYValues().setDateTime(false);
        arrowSeries.setColorEach(true);
        addRandomArrows();

        formatChart();

        colorEachButton = new JCheckBox("Color each");
        colorEachButton.setSelected(true);
        animateButton = new JCheckBox("Animate");
        animateButton.setSelected(false);
        view3DButton = new JCheckBox("3D");
        view3DButton.setSelected(true);

        //Create a timer.
        timer = new Timer(ONE_MILLISECOND, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                /* stop timer */
                timer.stop();

                /* add a new point to each series */
                double tmpX;

                Random generator = new Random();

                for (int t = 0; t < arrowSeries.getCount()-1; t++) {
                    arrowSeries.getStartXValues().setValue(
                            t,
                            arrowSeries.getStartXValues().getValue(t)+generator.nextDouble()*100-50.0
                            );
                    arrowSeries.getStartYValues().setValue(
                            t,
                            arrowSeries.getStartYValues().getValue(t)+generator.nextDouble()*100-50.0
                            );
                    arrowSeries.getEndXValues().setValue(
                            t,
                            arrowSeries.getEndXValues().getValue(t)+generator.nextDouble()*100-50.0
                            );
                    arrowSeries.getEndYValues().setValue(
                            t,
                            arrowSeries.getEndYValues().getValue(t)+generator.nextDouble()*100-50.0)
                            ;
                    arrowSeries.repaint();
                }

                /* re-enable timer again */
                timer.start();
            }
        });
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(colorEachButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(animateButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(view3DButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private void addRandomArrows() {
        arrowSeries.clear();
        Random generator = new Random();

        double x0, y0, x1, y1;
        for (int t=0; t < 40; t++) {
            x0 = generator.nextDouble()*1000;
            y0 = generator.nextDouble()*1000;

            x1 = generator.nextDouble()*300 - 150.0;
            if (x1<50) { x1 = 50; };
            x1 = x1 + x0;

            y1 = generator.nextDouble()*300 - 150.0;
            if (y1<50) { y1 = 50; };
            y1 = y1 + y0;
            arrowSeries.add(x0,y0,x1,y1, "");
        }
    }

    private void formatChart() {
        chart1.getLegend().setVisible(false);

        Aspect tmpAspect = chart1.getAspect();
        tmpAspect.setElevation(353);
        tmpAspect.setOrthogonal(false);
        tmpAspect.setPerspective(55);
        tmpAspect.setRotation(334);
        tmpAspect.setZoom(97);


        BackWall backWall = chart1.getWalls().getBack();
        backWall.getBrush().setColor(Color.WHITE);
        backWall.setColor(Color.LIME);
        backWall.setSize(20);
        backWall.setTransparent(true);
        backWall.getGradient().setDirection(GradientDirection.FORWARDDIAGONAL);
        backWall.getGradient().setEndColor(Color.LIME);
        backWall.getGradient().setVisible(true);

        BottomWall bottomWall = chart1.getWalls().getBottom();
        bottomWall.setColor(Color.GREEN);
        bottomWall.setSize(20);

        LeftWall leftWall = chart1.getWalls().getLeft();
        leftWall.setColor(Color.SKY_BLUE);
        leftWall.setSize(20);

    }

    private JCheckBox animateButton;
    private JCheckBox colorEachButton;
    private JCheckBox view3DButton;
    private Timer timer;

    private final static int ONE_MILLISECOND = 1;
}
