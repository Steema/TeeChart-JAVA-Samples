/*
 * SeriesBandDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.tool;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.editors.ChartEditor;
import com.steema.teechart.styles.Line;
import com.steema.teechart.tools.SeriesBand;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import features.ChartSamplePanel;

/**
 *
 * @author narcis
 */
public class SeriesBandDemo extends ChartSamplePanel
    implements ActionListener {

    private SeriesBand seriesBandTool1;

    /**
     * Creates a new instance of SeriesBandDemo
     */
    public SeriesBandDemo() {
        super();
        editButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == editButton) {
            ChartEditor.editTool(seriesBandTool1);
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getAspect().setView3D(false);
        chart1.getHeader().setVisible(true);
        chart1.setText("SeriesBand Tool Example");
        chart1.getLegend().setVisible(false);
    }

    protected void initComponents() {
        super.initComponents();

        Line line1 = new Line(chart1.getChart());
        Line line2 = new Line(chart1.getChart());
        line1.fillSampleValues();

        for (int i=0; i<line1.getCount(); i++) {
            line2.add(line1.getXValues().getValue(i),
                    line1.getYValues().getValue(i) / 2.0);
        }

        seriesBandTool1 = new SeriesBand(chart1.getChart());
        seriesBandTool1.setSeries(line1);
        seriesBandTool1.setSeries2(line2);
        
        line1.getLinePen().setWidth(3);
        line2.getLinePen().setWidth(3);
        
        seriesBandTool1.getGradient().setVisible(true);
        seriesBandTool1.getGradient().setStartColor(Color.SILVER);        

        editButton = new JButton("Edit...");
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
