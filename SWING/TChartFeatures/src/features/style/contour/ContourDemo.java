/*
 * ContourDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.contour;

import com.steema.teechart.Aspect;
import com.steema.teechart.legend.Legend;
import com.steema.teechart.legend.LegendAlignment;
import com.steema.teechart.legend.LegendStyle;
import com.steema.teechart.Wall;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.Contour;
import com.steema.teechart.styles.Surface;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import features.ChartSamplePanel;
import java.awt.BorderLayout;

/**
 *
 * @author tom
 */
public class ContourDemo extends ChartSamplePanel
    implements ChangeListener, ItemListener {

    private Contour contourSeries;
    private Surface surfaceSeries;

    /** Creates a new instance of ContourDemo */
    public ContourDemo() {
        super();
        view2DButton.addItemListener(this);
        showWallsButton.addItemListener(this);
        colorEachButton.addItemListener(this);
        posLevelButton.addItemListener(this);
        surfaceButton.addItemListener(this);
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == view2DButton) {
            chart1.getAspect().setView3D(!isSelected);
        } else if (source == showWallsButton) {
            chart1.getWalls().setVisible(isSelected);
        } else if (source == colorEachButton) {
            contourSeries.setColorEach(isSelected);
        } else if (source == posLevelButton) {
            contourSeries.setYPositionLevel(isSelected);
        } else if (source == surfaceButton) {
            surfaceSeries.setActive(isSelected);
        }
    }

    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider)e.getSource();
        if (!source.getValueIsAdjusting()) {
            int tmp = (int)source.getValue();
            // We don't want each Level to have automatic Y position
            contourSeries.setYPositionLevel(false);
            // Force the Level Y position
            contourSeries.setYPosition(1000-tmp);
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getAspect().setChart3DPercent(100);
        chart1.getAxes().getDepth().setVisible(true);

        Wall tmpWall;
        tmpWall = chart1.getWalls().getBack();
        tmpWall.getBrush().setColor(Color.WHITE);
        tmpWall.setSize(10);
        tmpWall.setColor(new Color(8454143));
        tmpWall = chart1.getWalls().getBottom();
        tmpWall.setSize(10);
        tmpWall.setColor(new Color(16777088));
        tmpWall = chart1.getWalls().getLeft();
        tmpWall.setSize(10);

        Legend tmpLegend = chart1.getLegend();
        tmpLegend.setAlignment(LegendAlignment.LEFT);
        //Legend.ColorWidth = 30
        tmpLegend.setLegendStyle(LegendStyle.VALUES);
        tmpLegend.getSymbol().setWidth(30);

        chart1.getPanel().setMarginLeft(5);
        chart1.getPanel().setMarginTop(5);

        Aspect tmpAspect = chart1.getAspect();
        tmpAspect.setElevation(342);
        tmpAspect.setOrthogonal(false);
        tmpAspect.setPerspective(55);
        tmpAspect.setRotation(317);
        tmpAspect.setZoom(72);
    }

    // auxilary functions for populating series
    private double ToAngle(double val)
    {
        return (val+10.0)*Math.PI*0.1;
    }
                
    private double fun(int x, int z)
    {
        return 500*(Math.sin(ToAngle(x))+Math.pow(Math.cos(z),2.0));
    }
        
    protected void initComponents() {
        super.initComponents();

        // First we add XYZ points to the Contour series...
        contourSeries = new com.steema.teechart.styles.Contour(chart1.getChart());
        surfaceSeries = new com.steema.teechart.styles.Surface(chart1.getChart());
        surfaceSeries.setVisible(false);
        
        contourSeries.setIrregularGrid(false);
        for (int x=-10;x<10;x++)
	{
            for (int z=-10;z<10;z++)
            {
                contourSeries.add(x,fun(x,z),z);
            }
	}

        /* 
        * surfaceSeries1 displays the same data, 
        * so let's connect it to contour series
	*/
	surfaceSeries.setDataSource(contourSeries);

        // Then we specify how many "contour levels" we want 
	contourSeries.setNumLevels(10);
	// initially, YPosition in the middle
	contourSeries.setYPosition(0.5*(contourSeries.getYValues().getMaximum() + contourSeries.getYValues().getMinimum()));

        //ScrollBar1.Position:=1000-Round(ContourSeries.YPosition);
        view2DButton = new JCheckBox("2D");
        showWallsButton = new JCheckBox("Show Walls");
        showWallsButton.setSelected(true);
        colorEachButton = new JCheckBox("Color Each Level");
        colorEachButton.setSelected(true);
        posLevelButton = new JCheckBox("Levels at Y");
        surfaceButton = new JCheckBox("Surface");

        levelSlider = new JSlider(JSlider.VERTICAL, 0, 1000, 50);
        levelSlider.setValue((int)(1000-Math.round(contourSeries.getYPosition())));
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(view2DButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(showWallsButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(colorEachButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(posLevelButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(surfaceButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
        JPanel tmpChartPane = new JPanel();
        tmpChartPane.add(chart1, BorderLayout.CENTER);
        tmpChartPane.add(levelSlider, BorderLayout.EAST);
        setSamplePane(tmpChartPane);
    }

    private JCheckBox view2DButton, showWallsButton, colorEachButton, posLevelButton, surfaceButton;
    private JSlider levelSlider;
}
