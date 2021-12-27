/*
 * CustomDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.function;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.editors.ChartEditor;
import com.steema.teechart.functions.Custom;
import com.steema.teechart.styles.CustomStack;
import com.steema.teechart.styles.Line;
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
public class CustomDemo extends ChartSamplePanel
        implements ActionListener {

    private Line lineSeries ;
    private Custom customFunction;

    /**
     * Creates a new instance of CustomDemo
     */
    public CustomDemo() {
        super();
        editButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == editButton) {
            ChartEditor.editSeriesDatasource(lineSeries);
        }
    }

    protected void initComponents() {
        super.initComponents();

        lineSeries = new com.steema.teechart.styles.Line(chart1.getChart());

        lineSeries.setTitle("y = Sin( x / 10)");
        lineSeries.setColor(Color.BLUE);
        lineSeries.setStacked(CustomStack.NONE);
        lineSeries.getMarks().setVisible(false);
        lineSeries.getPointer().setVisible(false);
        lineSeries.getLinePen().setWidth(2);
        lineSeries.getXValues().setDateTime(false);
        lineSeries.getYValues().setDateTime(false);

        customFunction = new com.steema.teechart.functions.Custom(chart1.getChart());
        customFunction.setPeriod(1);
        customFunction.setNumPoints(100);

        customFunction.setYCalculator( new Custom.YCalculator() {
            public double calculate(Custom series, double x) {
                return Math.sin(x/10)*100;
            };
        });

        lineSeries.setFunction(customFunction);
        lineSeries.setStairs(false);

        editButton = new JButton("Edit...");
    }

    protected void initGUI() {
        super.initGUI();

        chart1.getHeader().setVisible(true);
        chart1.setText("y = Sin(x / 10)");
        chart1.getAspect().setView3D(false);

        chart1.setClipPoints(false);
        chart1.getAxes().getLeft().setMaximumOffset(3);
        chart1.getAxes().getLeft().setMinimumOffset(3);

        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(editButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JButton editButton;
}
