/*
 * WaterfallDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.waterfall;

import com.steema.teechart.editors.ButtonPen;
import com.steema.teechart.editors.ChartEditor;
import com.steema.teechart.styles.Waterfall;
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
public class WaterfallDemo extends ChartSamplePanel
    implements ActionListener {

    private Waterfall series;
    private ButtonPen borderButton, penButton;

    /** Creates a new instance of WaterfallDemo */
    public WaterfallDemo() {
        super();
    }

    public void actionPerformed(ActionEvent e) {
       Object source = e.getSource();
       if (source == editButton) {
           ChartEditor.editSeries(series);
       }
    }

    protected void initComponents() {
        super.initComponents();

        series = new Waterfall(chart1.getChart());
        series.fillSampleValues(20);
        series.setTimesZOrder(2);

        editButton = new JButton("Edit...");

        borderButton = new ButtonPen(series.getPen(), "Border...");
        penButton = new ButtonPen(series.getWaterLines(), "Lines...");
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(borderButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(penButton);
            tmpPane.add(Box.createHorizontalStrut(20));
            tmpPane.add(editButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JButton editButton;
}
