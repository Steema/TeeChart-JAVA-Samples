/*
 * Points3DDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.points3d;
import com.steema.teechart.Aspect;
import com.steema.teechart.Wall;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.DashStyle;
import com.steema.teechart.styles.Points3D;
import com.steema.teechart.tools.Rotate;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.Timer;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class Points3DDemo extends ChartSamplePanel
    implements ItemListener {

    private Points3D series;

    /**
     * Creates a new instance of Points3DDemo
     */
    public Points3DDemo() {
        super();
        linesButton.addItemListener(this);
        wallsButton.addItemListener(this);
        axesButton.addItemListener(this);
        pointsButton.addItemListener(this);
        colorEachButton.addItemListener(this);
        animateButton.addItemListener(this);
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == linesButton) {
            series.getLinePen().setVisible(isSelected);
        } else if (source == wallsButton) {
            chart1.getWalls().setVisible(isSelected);
        } else if (source == axesButton) {
            chart1.getAxes().setVisible(isSelected);
        } else if (source == pointsButton) {
            series.getPointer().setVisible(isSelected);
        } else if (source == colorEachButton) {
            series.setColorEach(isSelected);
        } else if (source == animateButton) {
            if (isSelected) {
                timer.start();
            } else {
                timer.stop();
            }
        }
    }

    protected void initChart() {
        super.initChart();
        Wall tmpWall;
        tmpWall = chart1.getWalls().getBack();
        tmpWall.getBrush().setColor(Color.WHITE);
        tmpWall.setColor(Color.WHITE);
        tmpWall.setSize(10);
        tmpWall = chart1.getWalls().getBottom();
        tmpWall.getBrush().setColor(Color.WHITE);
        tmpWall.setColor(Color.SILVER);
        tmpWall.setSize(10);
        tmpWall = chart1.getWalls().getLeft();
        tmpWall.getBrush().setColor(Color.WHITE);
        tmpWall.setSize(10);

        chart1.getLegend().setVisible(false);

        chart1.getPanel().setMarginBottom(9);
        chart1.getPanel().setMarginTop(9);

        chart1.getAxes().getDepth().setVisible(true);
        chart1.getAspect().setChart3DPercent(100);
        chart1.getAspect().setOrthogonal(false);
        chart1.getAspect().setPerspective(45);
        chart1.getAspect().setZoom(81);
    }

    protected void initComponents() {
        super.initComponents();

        series = new com.steema.teechart.styles.Points3D(chart1.getChart());
        series.setColorEach(true);
        series.getLinePen().setStyle(DashStyle.DOT);
        series.getPointer().setVisible(true);
        series.getBaseLine().setVisible(true);
        series.setDepthSize(3.0);
        series.fillSampleValues(100);

        Rotate tmpTool = new Rotate(chart1.getChart());
        tmpTool.getPen().setColor(Color.WHITE);

        linesButton = new JCheckBox("Lines");
        linesButton.setSelected(series.getLinePen().getVisible());
        wallsButton = new JCheckBox("3D Walls");
        wallsButton.setSelected(chart1.getWalls().getVisible());
        axesButton = new JCheckBox("Axes");
        axesButton.setSelected(chart1.getAxes().getVisible());
        pointsButton = new JCheckBox("Points");
        pointsButton.setSelected(series.getPointer().getVisible());
        colorEachButton = new JCheckBox("Color Each");
        colorEachButton.setSelected(series.getColorEach());
        animateButton = new JCheckBox("Animate");

        /* Create a timer. */
        timer = new Timer(ONE_MILLISECOND, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                /* stop timer */
                timer.stop();

                Aspect tmpAspect = chart1.getAspect();
                tmpAspect.setRotation((tmpAspect.getRotation()+rotateDelta) % 360);
                tmpAspect.setElevation(tmpAspect.getElevation()+elevateDelta);
                if ((tmpAspect.getElevation() < 280) || (tmpAspect.getElevation() > 350)) {
                    elevateDelta = -elevateDelta;
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
            tmpPane.add(linesButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(wallsButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(axesButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(pointsButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(colorEachButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(animateButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JCheckBox linesButton, wallsButton, axesButton, pointsButton, colorEachButton, animateButton;
    private Timer timer;
    private int rotateDelta = -5;
    private int elevateDelta = -4;
    private final static int ONE_MILLISECOND = 1;
}
