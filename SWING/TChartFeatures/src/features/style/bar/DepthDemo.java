/*
 * DepthDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.bar;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.Bar;
import com.steema.teechart.styles.MultiBars;
import com.steema.teechart.tools.Annotation;
import com.steema.teechart.tools.Rotate;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class DepthDemo extends ChartSamplePanel
        implements ChangeListener {

    private Bar[] barSeries;
    private Annotation annotationTool;

    /**
     * Creates a new instance of DepthDemo
     */
    public DepthDemo() {
        super();
        depthSlider.addChangeListener(this);
        widthSlider.addChangeListener(this);
    }

    public void stateChanged(ChangeEvent e) {
        Object source = e.getSource();

         if (source instanceof JSlider) {
            JSlider tmpSlider = (JSlider)e.getSource();
            int tmpValue = (int)tmpSlider.getValue();
            if (tmpSlider == depthSlider) {
                for (int t=0; t < chart1.getSeriesCount(); t++) {
                    ((Bar)chart1.getSeries(t)).setDepthPercent(tmpValue);
                }
            } else  if (tmpSlider == widthSlider) {
                for (int t=0; t < chart1.getSeriesCount(); t++) {
                    ((Bar)chart1.getSeries(t)).setBarWidthPercent(tmpValue);
                }
            }
         }
    }

    protected void initChart() {
        super.initChart();
        chart1.getWalls().getBack().setColor(Color.RED);
        chart1.getWalls().getBack().setSize(7);
        chart1.getWalls().getBack().setTransparent(false);
        chart1.getWalls().getBottom().setSize(7);
        chart1.getWalls().getLeft().setColor(Color.PINK);
        chart1.getWalls().getLeft().setSize(7);
        chart1.getWalls().getRight().setSize(7);

        chart1.getAspect().setChart3DPercent(85);
        chart1.getAspect().setElevation(342);
        chart1.getAspect().setOrthogonal(false);
        chart1.getAspect().setPerspective(92);
        chart1.getAspect().setRotation(317);
        chart1.getAspect().setZoom(61);
    }

    protected void initComponents() {
        super.initComponents();
        barSeries = new Bar[5];
        for (int t=0; t<barSeries.length; t++) {
            barSeries[t] = new Bar(chart1.getChart());
            barSeries[t].setDepthPercent(40);
            barSeries[t].setBarWidthPercent(40);
            barSeries[t].getMarks().setVisible(false);
            barSeries[t].setMultiBar(MultiBars.NONE);
        }

        Rotate rotateTool = new Rotate(chart1.getChart());
        rotateTool.getPen().setColor(Color.WHITE);

        chart1.getSeries().fillSampleValues();

        depthSlider = new JSlider(JSlider.HORIZONTAL, 1, 100, 40);
        widthSlider = new JSlider(JSlider.HORIZONTAL, 1, 100, 40);
    }

    protected void initGUI() {
        super.initGUI();
        JLabel tmpLabel;
        JPanel tmpPane = getButtonPane();
        {
            tmpLabel = new JLabel("Depth:");
            tmpLabel.setDisplayedMnemonic('D');
            tmpLabel.setLabelFor(depthSlider);
            tmpPane.add(tmpLabel);
            tmpPane.add(depthSlider);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpLabel = new JLabel("Width:");
            tmpLabel.setDisplayedMnemonic('W');
            tmpLabel.setLabelFor(widthSlider);
            tmpPane.add(tmpLabel);
            tmpPane.add(widthSlider);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JSlider depthSlider, widthSlider;

}
