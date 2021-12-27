/*
 * RightWallDemo.java
 *
 * <p>Copyright: Copyright (c) 2004-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.walls;

import com.steema.teechart.Wall;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.Bar;
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
public class RightWallDemo extends ChartSamplePanel
    implements ItemListener {

    /**
     * Creates a new instance of RightWallDemo
     */
    public RightWallDemo() {
        super();
        showRightWallButton.addItemListener(this);
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == showRightWallButton) {
            chart1.getWalls().getRight().setVisible(isSelected);
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getAspect().setChart3DPercent(100);
        chart1.getAspect().setElevation(360);
        chart1.getAspect().setOrthogonal(false);
        chart1.getAspect().setPerspective(35);
        chart1.getAspect().setRotation(360);

        Wall wall;
        wall = chart1.getWalls().getBack();
        wall.setSize(10);
        wall.setTransparent(false);
        wall = chart1.getWalls().getBottom();
        wall.setSize(10);
        wall = chart1.getWalls().getLeft();
        wall.setSize(10);
        wall = chart1.getWalls().getRight();
        wall.setColor(Color.PINK);
        wall.setSize(4);
        wall.setVisible(true);
    }

    protected void initSeries() {
        Bar series = new Bar(chart1.getChart());
        series.fillSampleValues(6);
    }

    protected void initComponents() {
        super.initComponents();
        initSeries();
        showRightWallButton = new JCheckBox("Show Right wall");
        showRightWallButton.setSelected(chart1.getWalls().getRight().getVisible());
    }

    protected void initGUI() {
        super.initGUI();
        JLabel tmpLabel;
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(showRightWallButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JCheckBox showRightWallButton,  showSubFooterButton;
}
