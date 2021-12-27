/*
 * GridBandsDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.tool;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.DashStyle;
import com.steema.teechart.drawing.HatchStyle;
import com.steema.teechart.editors.ButtonPen;
import com.steema.teechart.editors.DialogFactory;
import com.steema.teechart.styles.Line;
import com.steema.teechart.tools.GridBand;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class GridBandsDemo extends ChartSamplePanel
    implements ActionListener, ItemListener {

    private GridBand tool;

    /** Creates a new instance of GridBandsDemo */
    public GridBandsDemo() {
        super();
        band1Button.addActionListener(this);
        band2Button.addActionListener(this);
        view3DButton.addItemListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == band1Button) {
            DialogFactory.showModal(tool.getBand1());
        } else if (source == band2Button) {
            DialogFactory.showModal(tool.getBand2());
        }
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);

        if (source == view3DButton) {
            chart1.getAspect().setView3D(isSelected);
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.setText("Axis Grid Bands");
        chart1.getAspect().setView3D(false);
    }

    protected void initComponents() {
        super.initComponents();

        Line tmpSeries = new com.steema.teechart.styles.Line(chart1.getChart());
        tmpSeries.fillSampleValues();
        tmpSeries.getLinePen().setWidth(2);
        tmpSeries.getPointer().setVisible(false);
        tmpSeries.getMarks().setVisible(false);
        tmpSeries.setColor(Color.GREEN);

        // Create GridBandTool...
        tool = new com.steema.teechart.tools.GridBand(chart1.getChart());
        tool.setAxis(chart1.getAxes().getLeft());

        // cosmetic examples:
        //GetTeeBrush(0,Band1.Image.Bitmap);
        tool.getBand1().setColor(Color.BLUE);

        tool.getBand2().setStyle(HatchStyle.CROSS);
        tool.getBand2().setColor(Color.WHITE);

        // Change Left axis grid
        chart1.getAxes().getLeft().getGrid().setColor(Color.RED);
        chart1.getAxes().getLeft().getGrid().setStyle(DashStyle.SOLID);

        band1Button = new JButton("Band 1...");
        band2Button = new JButton("Band 2...");
        gridButton = new ButtonPen(chart1.getAxes().getLeft().getGrid(), "Grid...");
        view3DButton = new JCheckBox("View 3D");
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(band1Button);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(band2Button);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(view3DButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(gridButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private ButtonPen gridButton;
    private JButton band1Button, band2Button;
    private JCheckBox view3DButton;
}
