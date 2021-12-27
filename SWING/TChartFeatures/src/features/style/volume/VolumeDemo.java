/*
 * VolumeDemo.java
 *
 * <p>Copyright: Copyright (c)  2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.volume;
import com.steema.teechart.drawing.ChartPen;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.editors.ButtonPen;
import com.steema.teechart.editors.ChartEditor;
import com.steema.teechart.styles.Volume;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JPanel;
import features.ChartSamplePanel;
import javax.swing.JButton;

/**
 *
 * @author tom
 */
public class VolumeDemo extends ChartSamplePanel
    implements ActionListener {

    private Volume volumeSeries;

    private ButtonPen editBorderButton;
    private JButton editButton;

    /** Creates a new instance of OutLine */
    public VolumeDemo() {
        super();
        editButton.addActionListener(this);
        editBorderButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == editButton) {
            ChartEditor.editSeries(volumeSeries);
        } else if (source == editBorderButton) {
            volumeSeries.setColor(volumeSeries.getLinePen().getColor());
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getHeader().setVisible(true);
        chart1.setText("Volume");
        chart1.getLegend().setVisible(false);
        chart1.getAspect().setView3D(false);
    }

    protected void initComponents() {
        super.initComponents();

        volumeSeries = new com.steema.teechart.styles.Volume(chart1.getChart());
        volumeSeries.setColor(Color.GREEN);
        volumeSeries.fillSampleValues(100);

        ChartPen tmpPen = volumeSeries.getLinePen();
        tmpPen.setColor(Color.GREEN);

        editBorderButton = new ButtonPen(tmpPen, "Border...");
        editButton = new JButton("Edit...");
    }

    protected void initGUI(){
        super.initGUI();

        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(editBorderButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(editButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }
}
