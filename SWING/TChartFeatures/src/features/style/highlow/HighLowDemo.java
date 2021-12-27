/*
 * HighLowDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.highlow;

import com.steema.teechart.Cursor;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.editors.ButtonPen;
import com.steema.teechart.editors.DialogFactory;
import com.steema.teechart.styles.HighLow;

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
public class HighLowDemo extends ChartSamplePanel
    implements ActionListener {

    private ButtonPen highButton, lowButton, linesButton;
    private HighLow series;

    /** Creates a new instance of HighLowDemo */
    public HighLowDemo() {
        super();
        highFillButton.addActionListener(this);
        lowFillButton.addActionListener(this);
    }

     public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == highFillButton) {
            DialogFactory.showModal(series.getHighBrush());
        } else if (source == lowFillButton) {
            DialogFactory.showModal(series.getLowBrush());
        }
    }

    protected void initComponents() {
        super.initComponents();
        series = new HighLow(chart1.getChart());
        series.fillSampleValues(20);
        series.setColor(Color.RED);
        series.getHighBrush().setColor(Color.LIME);
        series.getHighPen().setColor(Color.BLUE);
        series.getHighPen().setWidth(2);
        series.getLowBrush().setColor(Color.WHITE);
        series.getPen().setColor(Color.BLUE);
        series.setCursor(Cursor.CROSS);

        highFillButton = new JButton("High Fill...");
        lowFillButton = new JButton("Low Fill...");
        highButton = new ButtonPen(series.getHighPen(), "High...");
        lowButton = new ButtonPen(series.getLowPen(), "Low...");
        linesButton = new ButtonPen(series.getPen(), "Lines...");
    }

    protected void initGUI() {
        super.initGUI();
        chart1.getLegend().setVisible(false);
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(highButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(highFillButton);
            tmpPane.add(Box.createHorizontalStrut(20));
            tmpPane.add(lowButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(lowFillButton);
            tmpPane.add(Box.createHorizontalStrut(20));
            tmpPane.add(linesButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JButton highFillButton, lowFillButton;
}
