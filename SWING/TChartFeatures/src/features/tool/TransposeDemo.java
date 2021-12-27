/*
 * TransposeDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.tool;
import com.steema.teechart.styles.Surface;
import com.steema.teechart.tools.GridTranspose;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class TransposeDemo extends ChartSamplePanel
    implements ActionListener {

    private GridTranspose tool;

    /**
     * Creates a new instance of TransposeDemo
     */
    public TransposeDemo() {
        super();
        transposeButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == transposeButton) {
            tool.transpose();
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getHeader().setVisible(true);
        chart1.getLegend().setVisible(false);
        chart1.setText("3D Transpose Tool");

        chart1.getAspect().setChart3DPercent(65);
        chart1.getAxes().getDepth().setVisible(false);
        chart1.getAspect().setOrthoAngle(60);
    }

    protected void initComponents() {
        super.initComponents();

        tool = new GridTranspose(chart1.getChart());

        Surface surfaceSeries = new com.steema.teechart.styles.Surface(chart1.getChart());
        tool.setSeries(surfaceSeries);

        surfaceSeries.fillSampleValues(30);

        transposeButton = new JButton("Transpose!");
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(transposeButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JButton transposeButton;
}
