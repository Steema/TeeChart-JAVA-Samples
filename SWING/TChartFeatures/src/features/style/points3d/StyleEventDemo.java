/*
 * StyleEventDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.points3d;

import com.steema.teechart.Wall;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.PointerStyle;
import com.steema.teechart.styles.Points3D;
import com.steema.teechart.styles.SeriesPointer;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import features.SamplePanel;
import com.steema.teechart.styles.PointerStyleResolver;
import com.steema.teechart.styles.ISeries;

/**
 *
 * @author tom
 */
public class StyleEventDemo extends SamplePanel
    implements ItemListener {

    private Points3D pseries;

    /** Creates a new instance of StyleEventDemo */
    public StyleEventDemo() {
        super();
        useEventButton.addItemListener(this);

        pseries.setPointerStyleResolver(new PointerStyleResolver() {
            public PointerStyle getStyle(ISeries series, int valueIndex, PointerStyle style) {

                  if (useEventButton.isSelected()) {
                      switch (valueIndex % 3) {
                          case 0:
                          {
                              pseries.getColors().setColor(valueIndex,Color.RED);
                              return PointerStyle.CIRCLE;
                          }
                          case 1:
                          {
                              pseries.getColors().setColor(valueIndex,Color.BLUE);
                              return PointerStyle.TRIANGLE;
                          }
                          case 2:
                          {
                              pseries.getColors().setColor(valueIndex,Color.YELLOW);
                              return PointerStyle.RECTANGLE;
                          }
                          default :
                          {
                              pseries.getColors().setColor(valueIndex,
                                      Color.BLUE);
                              return PointerStyle.RECTANGLE;
                          }
                      }
                  } else {
                      pseries.getColors().setColor(valueIndex,
                              Color.BISQUE);
                      return PointerStyle.RECTANGLE;
                  }
            }
        });
    }


    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == useEventButton) {
            pseries.repaint();
        }
    }

    protected void initChart() {
        super.initChart();
        Wall tmpWall;
        tmpWall = chart1.getWalls().getBack();
        tmpWall.setColor(Color.SKY_BLUE);
        tmpWall.setSize(10);
        chart1.getWalls().getBottom().setSize(10);
        chart1.getWalls().getLeft().setSize(10);
        chart1.getLegend().setVisible(false);
        chart1.getAxes().getDepth().setVisible(true);
        chart1.getAspect().setChart3DPercent(100);
        chart1.getAspect().setOrthogonal(false);
        chart1.getAspect().setPerspective(75);
        chart1.getAspect().setZoom(70);
        chart1.getAspect().setElevation(355);
        chart1.getAspect().setRotation(339);
    }

    protected void initComponents() {
        super.initComponents();
        pseries = new com.steema.teechart.styles.Points3D(chart1.getChart());
        pseries.getLinePen().setVisible(false);
        SeriesPointer tmpPointer = pseries.getPointer();
        tmpPointer.setHorizSize(12);
        tmpPointer.setVertSize(12);
        tmpPointer.setInflateMargins(true);
        tmpPointer.setStyle(PointerStyle.RECTANGLE);
        tmpPointer.setVisible(true);
        pseries.fillSampleValues(20);
        pseries.setColor(Color.BISQUE);

        useEventButton = new JCheckBox("Use event");
    }

    protected void initGUI() {
        super.initGUI();
        myCommander.setVisible(true);
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(useEventButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JCheckBox useEventButton;
}
