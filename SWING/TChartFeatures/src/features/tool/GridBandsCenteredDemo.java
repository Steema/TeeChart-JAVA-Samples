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
import com.steema.teechart.drawing.GradientDirection;
import com.steema.teechart.drawing.HatchStyle;
import com.steema.teechart.editors.ButtonPen;
import com.steema.teechart.editors.DialogFactory;
import com.steema.teechart.styles.HorizBar;
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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author tom
 */
public class GridBandsCenteredDemo extends ChartSamplePanel
        implements ActionListener, ItemListener, ChangeListener {

    private GridBand tool;
    private HorizBar horizBar1;

    /** Creates a new instance of GridBandsDemo */
    public GridBandsCenteredDemo() {
        super();
        band1Button.addActionListener(this);
        band2Button.addActionListener(this);
        view3DButton.addItemListener(this);
        gridCentered.addChangeListener(this);
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

        horizBar1 = new HorizBar(chart1.getChart());
         horizBar1.fillSampleValues();
            horizBar1.setBarHeightPercent(55);
            horizBar1.getGradient().setVisible(true);
            horizBar1.getGradient().setDirection(GradientDirection.HORIZONTAL);
            horizBar1.getMarks().setTransparent(true);
            horizBar1.setMarksOnBar(true);
            horizBar1.getPen().setVisible(false);
      

        // Create GridBandTool...
        tool = new com.steema.teechart.tools.GridBand(chart1.getChart());
        tool.setAxis(chart1.getAxes().getLeft());

        // cosmetic examples:
        //GetTeeBrush(0,Band1.Image.Bitmap);
        tool.getBand1().setColor(Color.fromArgb(224, 224, 224));
        tool.getBand2().setColor(Color.fromArgb(192, 192, 192));

        // Change Left axis grid
        chart1.getAxes().getLeft().getGrid().setColor(Color.fromArgb(254, 255,0,0));

        band1Button = new JButton("Band 1...");
        band2Button = new JButton("Band 2...");
        gridCentered = new JCheckBox();
        gridCentered.setSelected(true);
        view3DButton = new JCheckBox("View 3D");
        gridCentered.setText("Axes Left grid centered");
        chart1.getAxes().getLeft().setGridCentered(true);
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
            tmpPane.add(gridCentered);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }
    private JButton band1Button, band2Button;
    private JCheckBox view3DButton, gridCentered;

    public void stateChanged(ChangeEvent e) {
        Object aux = e.getSource();
        if (aux == gridCentered) {
            if (gridCentered.isSelected()) {
                chart1.getAxes().getLeft().setGridCentered(true);
            } else {
                chart1.getAxes().getLeft().setGridCentered(false);
            }
        }
    }
}
