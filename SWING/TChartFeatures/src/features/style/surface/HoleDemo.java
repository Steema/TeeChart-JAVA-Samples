/*
 * HoleDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.surface;

import com.steema.teechart.Aspect;
import com.steema.teechart.ImageMode;
import com.steema.teechart.Wall;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.misc.ImageUtils;
import com.steema.teechart.styles.Surface;
import com.steema.teechart.styles.ValueList;
import com.steema.teechart.tools.Rotate;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.Timer;
import features.ChartSamplePanel;
import features.SamplePanel;

/**
 *
 * @author tom
 */
public class HoleDemo extends SamplePanel
        implements ActionListener, ItemListener {

    private Surface series;

    /** Creates a new instance of HoleDemo */
    public HoleDemo() {
        super();
        angle=0;
        delta3D=1;
        normalButton.addActionListener(this);
        withHoleButton.addActionListener(this);
        animateButton.addItemListener(this);
        backWallButton.addItemListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == normalButton) {
            drawSeries(false);
        } else if (source == withHoleButton) {
            drawSeries(true);
        }
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
        } else if (source == backWallButton) {
            chart1.getWalls().getBack().setTransparent(!isSelected);
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getLegend().setVisible(false);
        chart1.getAxes().getLeft().setIncrement(0.1);
        chart1.getAxes().getBottom().setIncrement(1);
        chart1.getAxes().getBottom().getLabels().setSeparation(0);
        chart1.getAspect().setChart3DPercent(70);
        chart1.getAspect().setOrthogonal(false);
        chart1.getAspect().setPerspective(55);
        chart1.getAspect().setZoom(70);
        chart1.getPanel().setImage(ImageUtils.toImage(ImageUtils.getImage(ChartSamplePanel.class.getResource(URL_BACKIMAGE),chart1)));
        chart1.getPanel().setImageMode(ImageMode.TILE);
        Wall tmpWall;
        tmpWall = chart1.getWalls().getBack();
        tmpWall.setTransparent(true);
        tmpWall.getBrush().setColor(Color.WHITE);
        tmpWall.setSize(10);
        tmpWall = chart1.getWalls().getBottom();
        tmpWall.setColor(new Color(8454016));
        tmpWall.setSize(6);
        tmpWall = chart1.getWalls().getLeft();
        tmpWall.setColor(new Color(16777088));
        tmpWall.setSize(6);
    }

    protected void initComponents() {
        super.initComponents();

        series = new com.steema.teechart.styles.Surface(chart1.getChart());
        series.setNumXValues(10);
        series.setNumZValues(10);
        series.setEndColor(new Color(16744703));
        series.setMidColor(new Color(8453888));
        series.getSideBrush().setColor(Color.WHITE);
        series.getSideBrush().setStyle(null);
        drawSeries(true);

        Rotate tmpTool = new Rotate(chart1.getChart());
        tmpTool.getPen().setColor(Color.WHITE);

        normalButton = new JButton("Normal");
        withHoleButton = new JButton("With hole");
        animateButton = new JCheckBox("Animate");
        backWallButton = new JCheckBox("Back wall");

        //Create a timer.
        timer = new Timer(ONE_MILLISECOND, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                /* stop timer */
                timer.stop();

                /* add a new point to each series */
                double tmpX, tmpY;

                angle=angle+5;
                if (angle > 359) { angle=0; }
                Aspect tmpAspect = chart1.getAspect();
                tmpAspect.setChart3DPercent(tmpAspect.getChart3DPercent()+delta3D);
                if ((tmpAspect.getChart3DPercent()<5) || (tmpAspect.getChart3DPercent()>60)) {
                    delta3D = -delta3D;
                }

                ValueList tmpValues;
                tmpValues = series.getXValues();
                tmpX = Math.sin(angle*Math.PI/180.0);
                chart1.getAxes().getBottom().setMinMax(
                        tmpValues.getMinimum()-tmpX,
                        tmpValues.getMaximum()-tmpX
                        );
                tmpValues = series.getYValues();
                tmpY = Math.cos(angle*Math.PI/180.0)*
                        ((tmpValues.getMaximum()-tmpValues.getMinimum())/10.0);
                chart1.getAxes().getLeft().setMinMax(
                        tmpValues.getMinimum()-tmpY,
                        tmpValues.getMaximum()-tmpY
                        );

                /* re-enable timer again */
                timer.start();
            }
        });
    }

    protected void initGUI() {
        super.initGUI();
        myCommander.setVisible(true);
        JPanel tmpPane = getButtonPane();
        {   tmpPane.add(normalButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(withHoleButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(animateButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(backWallButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private void drawSeries(boolean withHole) {
        double tmpValue;
        series.clear();
        for (int x=1; x<11; x++) {
            for (int z=1; z<11; z++) {
                tmpValue=Math.cos(x/10.0) * Math.sin(z/10.0);
                /* apply hole at cells (5,5) to (6,6) */
                if ((withHole) && ((x==5) || (x==6)) && ((z==5) || (z==6))) {
                    series.add( x, tmpValue, z, "", Color.TRANSPARENT ); // <-- NULL cell
                } else {
                    series.add(x, tmpValue, z);
                }
            }
        }
    }

    private JButton normalButton, withHoleButton;
    private JCheckBox animateButton, backWallButton;

    private int angle, delta3D;
    private Timer timer;

    private final static int ONE_MILLISECOND = 1;
    private final static String URL_BACKIMAGE = "images/honeycomb.jpg";
}
