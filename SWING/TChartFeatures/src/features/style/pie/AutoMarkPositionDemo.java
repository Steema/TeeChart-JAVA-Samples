/*
 * AutoMarkPositionDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.pie;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.Pie;
import com.steema.teechart.styles.SeriesMarks;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class AutoMarkPositionDemo extends ChartSamplePanel
    implements ItemListener {

    private Pie pieSeries;

    /**
     * Creates a new instance of AutoMarkPositionDemo
     */
    public AutoMarkPositionDemo() {
        super();
        markButton.addItemListener(this);
        view3DButton.addItemListener(this);
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);

        if (source == markButton) {
            pieSeries.setAutoMarkPosition(isSelected);
        } else if (source == view3DButton) {
            chart1.getAspect().setView3D(isSelected);
        };
    }

    protected void initChart() {
        super.initChart();
        chart1.getAspect().setElevation(315);
        chart1.getAspect().setOrthogonal(false);
        chart1.getAspect().setPerspective(0);
        chart1.getAspect().setRotation(360);
    }

    protected void initComponents() {
        super.initComponents();

        pieSeries = new com.steema.teechart.styles.Pie(chart1.getChart());

        SeriesMarks tmpMarks = pieSeries.getMarks();
        tmpMarks.setArrowLength(19);
        tmpMarks.getCallout().getBrush().setColor(Color.ORANGE);
        tmpMarks.getCallout().setHorizSize(2);
        tmpMarks.getCallout().getPen().setVisible(false);
        tmpMarks.getCallout().setVertSize(2);
        tmpMarks.getCallout().setVisible(true);
        tmpMarks.getCallout().setDistance(8);
        tmpMarks.getCallout().setLength(19);
        tmpMarks.setVisible(true);

        pieSeries.setAutoMarkPosition(true);
        pieSeries.fillSampleValues(16);

        markButton = new JCheckBox("Auto Mark position");
        markButton.setSelected(pieSeries.getAutoMarkPosition());
        view3DButton = new JCheckBox("3D");
        view3DButton.setSelected(chart1.getAspect().getView3D());
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(markButton);
            tmpPane.add(Box.createHorizontalStrut(20));
            tmpPane.add(view3DButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JCheckBox markButton, view3DButton;
}
