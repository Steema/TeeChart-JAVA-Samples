/*
 * MinPixelsDemo.java
 *
 * <p>Copyright: Copyright (c) 2004-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.zoom;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.DashStyle;
import com.steema.teechart.styles.HorizLine;
import com.steema.teechart.styles.PointerStyle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import features.ChartSamplePanel;
import features.utils.EnumStrings;

/**
 *
 * @author tom
 */
public class MinPixelsDemo extends ChartSamplePanel
        implements ActionListener, ChangeListener {

    /** Creates a new instance of MinPixelsDemo */
    public MinPixelsDemo() {
        super();
        mouseButtonList.addActionListener(this);
        pixelSpinner.addChangeListener(this);
    }

    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if (source == mouseButtonList) {
            switch (mouseButtonList.getSelectedIndex()) {
                case 0: chart1.getZoom().setMouseButton(MouseEvent.BUTTON1); break;
                case 1: chart1.getZoom().setMouseButton(MouseEvent.BUTTON2); break;
                case 2: chart1.getZoom().setMouseButton(MouseEvent.BUTTON3); break;
            }
        }
    }

    public void stateChanged(ChangeEvent e) {
        Object source = e.getSource();
        if (source == pixelSpinner) {
            int pixels = ((SpinnerNumberModel)pixelSpinner.getModel()).getNumber().intValue();
            chart1.getZoom().setMinPixels(pixels);
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getAspect().setView3D(false);
    }

    protected void initComponents() {
        super.initComponents();
        HorizLine series = new HorizLine(chart1.getChart());
        series.getMarks().setVisible(false);
        series.setColor(Color.WHITE);
        series.getLinePen().setStyle(DashStyle.DOT);
        series.getPointer().getBrush().setColor(Color.BLUE);
        series.getPointer().setInflateMargins(true);
        series.getPointer().setStyle(PointerStyle.RECTANGLE);
        series.getPointer().setVisible(true);
        series.fillSampleValues(80);

        pixelSpinner = new JSpinner(
                new SpinnerNumberModel(
                chart1.getZoom().getMinPixels(),
                0,
                100,
                1
                )
                );

        mouseButtonList = new JComboBox(EnumStrings.MOUSE_BUTTONS);
        mouseButtonList.setSelectedIndex(chart1.getZoom().getMouseButton());
    }

    protected void initGUI() {
        super.initGUI();
        JLabel tmpLabel;
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(new JLabel("Drag to zoom..."));
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpLabel = new JLabel("Min. Pixels:");
            tmpLabel.setDisplayedMnemonic('M');
            tmpLabel.setLabelFor(pixelSpinner);
            tmpPane.add(tmpLabel);
            tmpPane.add(pixelSpinner);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpLabel = new JLabel("Mouse Button:");
            tmpLabel.setDisplayedMnemonic('B');
            tmpLabel.setLabelFor(mouseButtonList);
            tmpPane.add(tmpLabel);
            tmpPane.add(mouseButtonList);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JComboBox mouseButtonList;
    private JSpinner pixelSpinner;
}
