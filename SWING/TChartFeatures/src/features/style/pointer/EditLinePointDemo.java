/*
 * EditLinePointDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.pointer;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.editors.ChartEditor;
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
public class EditLinePointDemo extends ChartSamplePanel
    implements ActionListener {

    private MyPoints series;

    /**
     * Creates a new instance of EditLinePointDemo
     */
    public EditLinePointDemo() {
        super();
        editButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == editButton) {
            ChartEditor.editSeries(series);
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.setText("LinePoint Series");
        chart1.getHeader().setVisible(true);
        chart1.getAxes().setVisible(false);
    }

    protected void initComponents() {
        super.initComponents();
        series = new MyPoints(chart1.getChart());
        series.getLinesPen().setColor(Color.LIME);
        series.fillSampleValues(6);
        editButton = new JButton("Edit MyPoints...");
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(editButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JButton editButton;
}
