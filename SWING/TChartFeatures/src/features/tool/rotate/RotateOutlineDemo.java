/*
 * RotateDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.tool.rotate;
import com.steema.teechart.editors.ButtonPen;
import com.steema.teechart.styles.HorizArea;
import com.steema.teechart.tools.Rotate;
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
public class RotateOutlineDemo extends ChartSamplePanel
    implements ItemListener {

    private Rotate tool;

    /** Creates a new instance of RotateDemo */
    public RotateOutlineDemo() {
        super();
        drawOutlineButton.addItemListener(this);
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == drawOutlineButton) {
            tool.getPen().setVisible(isSelected);
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getHeader().setVisible(true);
        chart1.setText("Rotate tool outline");
        chart1.getLegend().setVisible(false);
        chart1.getWalls().getBack().setTransparent(false);
        chart1.getWalls().getRight().setVisible(true);
        chart1.getAspect().setOrthogonal(false);
        chart1.getAspect().setPerspective(89);
        chart1.getAspect().setRotation(344);
        chart1.getAspect().setZoom(78);
        chart1.getAspect().setChart3DPercent(95);
    }

    protected void initComponents() {
        super.initComponents();
        HorizArea tmpSeries = new com.steema.teechart.styles.HorizArea(chart1.getChart());
        tmpSeries.getPointer().setVisible(false);
        tmpSeries.getMarks().setVisible(false);
        tmpSeries.fillSampleValues(9);

        tool = new com.steema.teechart.tools.Rotate(chart1.getChart());
        tool.getPen().setVisible(true); // Enable Rotate tool "Outline"

        drawOutlineButton = new JCheckBox("Draw outline");
        drawOutlineButton.setSelected(true);
        penOutlineButton = new ButtonPen(tool.getPen(), "Outline...");
    }

    protected void initGUI(){
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(penOutlineButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(drawOutlineButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JCheckBox drawOutlineButton;
    private ButtonPen penOutlineButton;
}
