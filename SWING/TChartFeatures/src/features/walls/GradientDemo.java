/*
 * GradientDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.walls;

import com.steema.teechart.Aspect;
import com.steema.teechart.ImageMode;
import com.steema.teechart.Wall;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.editors.DialogFactory;
import com.steema.teechart.styles.Surface;
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

/**
 *
 * @author tom
 */
public class GradientDemo extends ChartSamplePanel
        implements ActionListener, ItemListener {

    private Surface series;

    /**
     * Creates a new instance of GradientDemo
     */
    public GradientDemo() {
        super();
        editButton.addActionListener(this);
        animateButton.addItemListener(this);
        backWallButton.addItemListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == editButton) {
            DialogFactory.showModal(chart1.getWalls().getBack().getGradient());
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
            chart1.getWalls().getBack().getGradient().setVisible(isSelected);
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getHeader().setVisible(true);
        chart1.getHeader().setText("Back wall and gradient");
        chart1.getAspect().setChart3DPercent(80);
        chart1.getAspect().setOrthogonal(false);
        chart1.getAspect().setPerspective(75);
        chart1.getPanel().getGradient().setEndColor(Color.SILVER);
        chart1.getPanel().getGradient().setVisible(true);

        Wall tmpWall;
        tmpWall = chart1.getWalls().getBack();
        tmpWall.setTransparent(false);
        tmpWall.getGradient().setEndColor(Color.GREEN);
        tmpWall.getGradient().setMiddleColor(Color.ORANGE);
        tmpWall.getGradient().setStartColor(Color.SKY_BLUE);
        tmpWall.getGradient().setUseMiddle(true);
        tmpWall.getGradient().setVisible(true);
        tmpWall.getBrush().setColor(Color.WHITE);
        tmpWall.setSize(10);
        tmpWall = chart1.getWalls().getBottom();
        tmpWall.getBrush().loadImage(ChartSamplePanel.class.getResource(URL_BRUSHIMAGE));
        tmpWall.getBrush().setImageMode(ImageMode.TILE);
        tmpWall.setSize(10);
        chart1.getWalls().getLeft().setSize(10);
        chart1.getWalls().getRight().setSize(10);
    }

    protected void initComponents() {
        super.initComponents();

        new Rotate(chart1.getChart());  //add rotate tool

        editButton = new JButton("Edit...");
        animateButton = new JCheckBox("Animate!");
        animateButton.setSelected(false);
        backWallButton = new JCheckBox("Show gradient");
        backWallButton.setSelected(chart1.getWalls().getBack().getGradient().getVisible());

        //Create a timer.
        timer = new Timer(ONE_MILLISECOND, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                /* stop timer */
                timer.stop();

                Aspect aspect = chart1.getAspect();
                aspect.setRotation(aspect.getRotation()+deltaR);
                if ((aspect.getRotation() > 358) || (aspect.getRotation() < 272)) {
                    deltaR=-deltaR;
                }

                aspect.setElevation(aspect.getElevation()+deltaE);

                if ((aspect.getElevation() > 358) || (aspect.getElevation() < 272)) {
                    deltaE=-deltaE;
                }

                /* re-enable timer again */
                timer.start();
            }
        });
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {   tmpPane.add(backWallButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(editButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(animateButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JButton editButton;
    private JCheckBox animateButton, backWallButton;

    private Timer timer;
    private int deltaR = 1;
    private int deltaE = 1;
    private final static String URL_BRUSHIMAGE = "images/zigzag.png";
    private final static int ONE_MILLISECOND = 1;
}
