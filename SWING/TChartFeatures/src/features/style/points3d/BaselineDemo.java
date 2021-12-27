/*
 * BaselineDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.points3d;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.editors.ButtonPen;
import com.steema.teechart.styles.PointerStyle;
import com.steema.teechart.styles.Points3D;
import java.awt.event.ActionEvent;
import javax.swing.Box;
import javax.swing.JPanel;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class BaselineDemo extends ChartSamplePanel {

    private Points3D series;

    /**
     * Creates a new instance of BaselineDemo
     */
    public BaselineDemo() {
        super();
    }

    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
    }

    protected void initChart() {
        super.initChart();
        chart1.getHeader().setText("Point3D BaseLine");
        chart1.getHeader().setVisible(true);
        chart1.getWalls().getBack().setTransparent(false);
        chart1.getLegend().setVisible(false);
        chart1.getAspect().setChart3DPercent(30);
    }

    protected void initSeries() {
        series = new com.steema.teechart.styles.Points3D(chart1.getChart());
        series.getMarks().setVisible(false);
        series.getBaseLine().setVisible(true);
        series.getBaseLine().setColor(Color.NAVY);
        series.getLinePen().setVisible(false);
        series.getPointer().setInflateMargins(true);
        series.getPointer().setStyle(PointerStyle.RECTANGLE);
        series.getPointer().setVisible(true);
        series.fillSampleValues();
    }

    protected void initComponents() {
        super.initComponents();
        initSeries();

        penButton = new ButtonPen(series.getBaseLine(), "Baseline...");
    }

    protected void initGUI() {
        super.initGUI();

        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(penButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private ButtonPen penButton;
}
