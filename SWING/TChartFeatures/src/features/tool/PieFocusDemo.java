/*
 * PieFocusDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.tool;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.editors.ButtonPen;
import com.steema.teechart.editors.ChartEditor;
import com.steema.teechart.styles.Pie;
import com.steema.teechart.tools.PieTool;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import features.ChartSamplePanel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

/**
 *
 * @author tom
 */
public class PieFocusDemo extends ChartSamplePanel
    implements ActionListener {

    private PieTool tool;

    /** Creates a new instance of PieFocusDemo */
    public PieFocusDemo() {
        super();
        editButton.addActionListener(this);

        chart1.addMouseMotionListener( new MouseMotionAdapter() {
            public void mouseMoved(MouseEvent e) {
                int tmp;
                tmp=tool.getSlice();
                if (tmp==-1) {
                    pieFocusLabel.setText(" ");
                } else {
                    pieFocusLabel.setText(" " + chart1.getSeries(0).getLabels().getString(tmp));
                    pieFocusLabel.setForeground(chart1.getSeries(0).getValueColor(tmp));
                }
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == editButton) {
            ChartEditor.editTool(tool);
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getHeader().setVisible(false);
        chart1.getAspect().setElevation(315);
        chart1.getAspect().setOrthogonal(false);
        chart1.getAspect().setPerspective(0);
        chart1.getAspect().setRotation(360);
    }

    protected void initComponents() {
        super.initComponents();

        Pie pieSeries = new com.steema.teechart.styles.Pie(chart1.getChart());
        pieSeries.getMarks().getCallout().getBrush().setColor(Color.BLACK);
        pieSeries.getMarks().getCallout().setLength(8);
        pieSeries.getMarks().setVisible(true);
        pieSeries.getOtherSlice().getLegend().setVisible(false);
        pieSeries.fillSampleValues(8);

        tool = new com.steema.teechart.tools.PieTool(chart1.getChart());
        tool.getPen().setWidth(4);
        tool.setSeries(pieSeries);

        editButton = new JButton("Edit...");
        penButton = new ButtonPen(tool.getPen());
        pieFocusLabel = new JLabel(" ");
    }

    protected void initGUI() {
        super.initGUI();
        JLabel tmpLabel;
        JPanel tmpPane = getButtonPane();
        {
            tmpLabel = new JLabel("Slice:");
            tmpLabel.setDisplayedMnemonic('S');
            tmpPane.add(tmpLabel);
            tmpPane.add(Box.createHorizontalStrut(5));
            tmpPane.add(pieFocusLabel);
            tmpPane.add(Box.createHorizontalStrut(60));
            tmpPane.add(penButton);
            //tmpPane.add();
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(editButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private ButtonPen penButton;
    private JButton editButton;
    private JLabel pieFocusLabel;
}
