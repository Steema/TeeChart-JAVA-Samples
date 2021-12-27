/*
 * OutLine.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.line;
import com.steema.teechart.drawing.ChartPen;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.editors.ButtonPen;
import com.steema.teechart.styles.Line;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JPanel;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class OutlineDemo extends ChartSamplePanel
    implements ActionListener {

    private Line lineSeries;

    private ButtonPen editLineButton;
    private ButtonPen editOutlineButton;

    /** Creates a new instance of OutLine */
    public OutlineDemo() {
        super();
        //editLineButton.addActionListener(this);
        //editOutlineButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source instanceof ButtonPen) {
            ((ButtonPen)source).editPen();
        }
    }

    protected void initComponents() {
        super.initComponents();
        chart1.getHeader().setVisible(true);
        chart1.setText("Line Series with outline");
        chart1.getLegend().setVisible(false);

        lineSeries = new com.steema.teechart.styles.Line(chart1.getChart());
        lineSeries.getMarks().setVisible(false);
        lineSeries.fillSampleValues(25);
        ChartPen tmpPen;
        tmpPen = lineSeries.getLinePen();
        tmpPen.setColor(Color.RED);
        tmpPen.setWidth(2);
        tmpPen = lineSeries.getOutLine();
        tmpPen.setColor(Color.YELLOW);
        tmpPen.setVisible(true);

        editLineButton = new ButtonPen(lineSeries.getLinePen(), "Line...");
        editOutlineButton = new ButtonPen(lineSeries.getOutLine(), "Outline...");
    }

    protected void initGUI(){
        super.initGUI();

        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(editLineButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(editOutlineButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }
}
