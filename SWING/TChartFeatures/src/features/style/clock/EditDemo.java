/*
 * EditDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.clock;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.editors.ChartEditor;
import com.steema.teechart.styles.Clock;
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
public class EditDemo extends ChartSamplePanel
    implements ActionListener {

    private Clock clock;

    /** Creates a new instance of EditDemo */
    public EditDemo() {
        super();
        editButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == editButton) {
            ChartEditor.editSeries(clock);
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.setText("Clock Series");
        chart1.getHeader().setVisible(true);
        chart1.getAspect().setView3D(false);
        chart1.getAxes().getBottom().setIncrement(30.0);
        chart1.getAxes().setVisible(false);
    }

    protected void initComponents() {
        super.initComponents();
        clock = new Clock(chart1.getChart());
        clock.setColor(Color.RED);
        clock.setShowInLegend(false);
        clock.getBrush().setColor(Color.WHITE);
        clock.getBrush().setColor(Color.RED);
        clock.getPenHours().setColor(Color.BLACK);
        clock.getPenMinutes().setColor(Color.BLACK);
        clock.getPenSeconds().setColor(Color.BLACK);
        editButton = new JButton("Edit Clock...");
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
