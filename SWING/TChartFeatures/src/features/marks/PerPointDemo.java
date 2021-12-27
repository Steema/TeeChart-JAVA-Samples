/*
 * PerPointDemo.java
 *
 * <p>Copyright: Copyright (c) 2004-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.marks;

import com.steema.teechart.TextShapeStyle;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.LineCap;
import com.steema.teechart.drawing.GradientDirection;
import com.steema.teechart.editors.DialogFactory;
import com.steema.teechart.styles.MarksItem;
import com.steema.teechart.styles.Pie;
import com.steema.teechart.styles.PointerStyle;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class PerPointDemo extends ChartSamplePanel
        implements ActionListener, ChangeListener {

    private Pie series;

    /** Creates a new instance of PerPointDemo */
    public PerPointDemo() {
        super();
        markSpinner.addChangeListener(this);
        resetButton.addActionListener(this);
        editButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if (source == resetButton) {
            // Remove custom marks (set all to default)
            series.getMarks().clear();
        } else if (source == editButton) {
            DialogFactory.showModal(
                series.getMarks().getItems().getItem(getSelectedMark())
            );
        }
    }

    public void stateChanged(ChangeEvent e) {
        Object source = e.getSource();
        if (source == markSpinner) {
            updateSeriesLabel();
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getAspect().setElevation(315);
        chart1.getAspect().setPerspective(0);
        chart1.getAspect().setRotation(360);
        chart1.getAspect().setOrthogonal(false);
        chart1.getPanel().getGradient().setDirection(GradientDirection.RADIAL);
        chart1.getPanel().getGradient().setEndColor(Color.SILVER);
        chart1.getPanel().getGradient().setVisible(true);
    }

    protected void initSeries() {
        series = new Pie(chart1.getChart());
        series.getMarks().setArrowLength(19);
        series.getMarks().getCallout().getBrush().setColor(Color.WHITE);
        series.getMarks().getCallout().getBrush().setVisible(false);
        series.getMarks().getCallout().getPen().setWidth(2);
        series.getMarks().getCallout().getPen().setEndCap(LineCap.ROUND);
        series.getMarks().getCallout().setStyle(PointerStyle.CIRCLE);
        series.getMarks().getCallout().setDistance(12);
        series.getMarks().getCallout().setLength(19);
        series.getMarks().getCallout().setVisible(true);
        series.getOtherSlice().getLegend().setVisible(false);
        series.getMarks().setVisible(true);
        series.setTitle("Pie");
        series.getShadow().setSize(10);
        series.getShadow().setTransparency(80);
        series.fillSampleValues();
    }

    protected void initMarks() {
        MarksItem item;
        // customize mark...
        item = series.getMarks().getItems().getItem(3);
        item.getFont().setSize(14);
        item.setColor(Color.SILVER);

        // customize another mark...
        item = series.getMarks().getItems().getItem(3);
        item.getFont().setSize(12);
        item.getFont().setColor(Color.WHITE);
        item.setColor(Color.NAVY);
        item.setShapeStyle(TextShapeStyle.ROUNDRECTANGLE);
        item.getShadow().setSize(4);
        item.getShadow().setTransparency(60);
        item.getShadow().setColor(Color.DARK_GRAY);
    }

    protected void initComponents() {
        super.initComponents();
        initSeries();
        initMarks();

        editButton = new JButton("Edit...");
        resetButton = new JButton("Reset");

        markSpinner = new JSpinner(
                new SpinnerNumberModel(
                    0,
                    0,
                    series.getCount()-1,
                    1
                    )
                );
        markSpinner.setMaximumSize(new Dimension(50, 23));
        seriesLabel = new JLabel();
        updateSeriesLabel();
    }

    protected void initGUI() {
        super.initGUI();
        JLabel tmpLabel;
        JPanel tmpPane = getButtonPane();
        {
            tmpLabel = new JLabel("Mark: ");
            tmpLabel.setDisplayedMnemonic('M');
            tmpLabel.setLabelFor(markSpinner);
            tmpPane.add(tmpLabel);
            tmpPane.add(markSpinner);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(editButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(resetButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(seriesLabel);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    protected int getSelectedMark() {
        return ((SpinnerNumberModel)(markSpinner).getModel()).getNumber().intValue();
    }

    protected void updateSeriesLabel() {
        seriesLabel.setText(series.getLabels().getString(getSelectedMark()));
    }

    private JButton resetButton, editButton;
    private JSpinner markSpinner;
    private JLabel seriesLabel;
}
