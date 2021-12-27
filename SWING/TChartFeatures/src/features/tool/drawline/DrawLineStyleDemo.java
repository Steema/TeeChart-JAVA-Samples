/*
 * DrawLineDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.tool.drawline;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.Line;
import com.steema.teechart.tools.DrawLine;
import com.steema.teechart.tools.DrawLineStyle;

import features.ChartSamplePanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 *
 * @author tom
 */
public class DrawLineStyleDemo extends ChartSamplePanel implements ActionListener
         {

    private JLabel label2;
    private JComboBox jStyles;
    private JLabel label1;
    private DrawLine tool;

    /**
     * Creates a new instance of DrawLineDemo
     */
    public DrawLineStyleDemo() {
        super();
      
    }
    protected void initChart() {
        super.initChart();
        chart1.getAspect().setView3D(false);
    }

    protected void initComponents() {
        super.initComponents();

        Line tmpLine = new com.steema.teechart.styles.Line(chart1.getChart());
        tmpLine.fillSampleValues(20);

        tool = new com.steema.teechart.tools.DrawLine(chart1.getChart());
        tool.getPen().setColor(Color.BLUE);
        label1 = new JLabel("New Line Style: ");
        jStyles = new JComboBox(new String[]{ "Ellipse","HorizParallel","Line","Rectangle","VertParallel" });
        jStyles.setSelectedIndex(2);
        jStyles.addActionListener(this);
        label2 = new JLabel("(Click and drag left mouse button)");
        tool.getPen().setColor(Color.BLUE);
        tool.getPen().setWidth(2);


    }

    protected void initGUI() {
        super.initGUI();
         JPanel tmpPane = getButtonPane();
        {
         tmpPane.add(label1);
         tmpPane.add(Box.createHorizontalStrut(10));
         tmpPane.add(jStyles);
         tmpPane.add(Box.createHorizontalStrut(40));
         tmpPane.add(label2);
         tmpPane.add(Box.createHorizontalGlue());
         }
    }

    public void actionPerformed(ActionEvent e)
    {

        switch(jStyles.getSelectedIndex())
        {
            case 0:
                tool.setDrawLineStyle(DrawLineStyle.ELLIPSE);
                break;
            case 1:
                tool.setDrawLineStyle(DrawLineStyle.HORIZPARALLEL);
                break;
            case 2:
                tool.setDrawLineStyle(DrawLineStyle.LINE);
                break;
            case 3:
                tool.setDrawLineStyle(DrawLineStyle.RECTANGLE);
                break;
            case 4:
                tool.setDrawLineStyle(DrawLineStyle.VERTPARALLEL);
                break;
        }
    }




}
