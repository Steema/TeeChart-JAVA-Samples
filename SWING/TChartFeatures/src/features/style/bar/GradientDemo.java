/*
 * GradientDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.bar;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.GradientDirection;
import com.steema.teechart.styles.Bar;
import com.steema.teechart.styles.BarStyle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import features.ChartSamplePanel;
import com.steema.teechart.editors.GradientEditor;

/**
 *
 * @author tom
 */
public class GradientDemo extends ChartSamplePanel
        implements ActionListener {

    private Bar barSeries;
    private JButton showGradientEditorButton;

    /**
     * Creates a new instance of GradientDemo
     */
    public GradientDemo() {
        super();
        showGradientEditorButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source  == showGradientEditorButton) {
            GradientEditor.edit(this, barSeries.getGradient(),true,true);
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getAspect().setView3D(false);
    }

    protected void initSeries() {
        barSeries = new Bar(chart1.getChart());
        barSeries.getMarks().setVisible(true);
        barSeries.fillSampleValues(6);
        barSeries.setColor(Color.RED);
        barSeries.setBarStyle(BarStyle.RECTGRADIENT);
        barSeries.getGradient().setDirection(GradientDirection.VERTICAL);
        barSeries.getGradient().setStartColor(Color.GREEN);
        barSeries.getGradient().setUseMiddle(false);
        barSeries.getGradient().setMiddleColor(Color.YELLOW);
    }

    protected void initComponents() {
        super.initComponents();
        initSeries();

        showGradientEditorButton = new JButton("Edit gradient...");
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(showGradientEditorButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }
}
