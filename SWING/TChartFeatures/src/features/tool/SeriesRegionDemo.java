/*
 * SeriesRegionDemo.java
 *
 * <p>Copyright: (c) 2005-2014 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.tool;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.editors.ChartEditor;
import com.steema.teechart.legend.LegendStyle;
import com.steema.teechart.misc.Utils;
import com.steema.teechart.styles.Line;
import com.steema.teechart.themes.ThemesList;
import com.steema.teechart.tools.SeriesRegion;
import features.ChartSamplePanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author yeray
 */
public class SeriesRegionDemo extends ChartSamplePanel
        implements ActionListener, ChangeListener {

    private SeriesRegion seriesRegionTool1;

    /**
     * Creates a new instance of SeriesBandDemo
     */
    public SeriesRegionDemo() {
        super();
    }

    protected void initChart() {
        super.initChart();
        chart1.getAspect().setView3D(false);
        chart1.getHeader().setVisible(true);
        chart1.setText("SeriesRegion Tool Example");
        chart1.getLegend().setLegendStyle(LegendStyle.SERIES);
    }

    protected void initComponents() {
        super.initComponents();

        line1 = new Line(chart1.getChart());
        line1.fillSampleValues(20);
        line1.getLinePen().setWidth(2);
        line1.setColor(Color.GOLD);
        line1.getLinePen().setColor(Utils.darkenColor(Color.GOLD, 60));

        seriesRegionTool1 = new SeriesRegion(chart1.getChart());
        seriesRegionTool1.setSeries(line1);

        seriesRegionTool1.setAutoBound(false);
        seriesRegionTool1.setUseOrigin(true);
        seriesRegionTool1.setOrigin(500);
        seriesRegionTool1.setLowerBound(line1.getXValues().getValue(2));
        seriesRegionTool1.setUpperBound(line1.getXValues().getValue(15));

        seriesRegionTool1.getGradient().setVisible(true);
        seriesRegionTool1.getGradient().setTransparency(40);

        seriesRegionTool1.getPen().setVisible(false);

        ThemesList.applyTheme(chart1.getChart(), 1);
    }

    protected void initGUI() {
        super.initGUI();

        JPanel tmpPane = getButtonPane();
        {
            editButton = new JButton("Edit...");
            editButton.addActionListener(this);

            lowerLabel = new JLabel();
            lowerLabel.setText("Lower:");
            lowerSpinner = new JSpinner();
            lowerSpinner.setModel(new javax.swing.SpinnerNumberModel(2, 0, 19, 1));
            lowerSpinner.setToolTipText("");
            lowerSpinner.setMaximumSize(new java.awt.Dimension(100, 100));
            lowerSpinner.setValue(seriesRegionTool1.getLowerBound());
            lowerSpinner.addChangeListener(this);

            upperLabel = new JLabel();
            upperLabel.setText("Upper:");
            upperSpinner = new JSpinner();
            upperSpinner.setModel(new javax.swing.SpinnerNumberModel(15, 0, 19, 1));
            upperSpinner.setToolTipText("");
            upperSpinner.setMaximumSize(new java.awt.Dimension(100, 100));
            upperSpinner.setValue(seriesRegionTool1.getUpperBound());
            upperSpinner.addChangeListener(this);

            useOriginCheckBox = new JCheckBox();
            useOriginCheckBox.setMnemonic('U');
            useOriginCheckBox.setSelected(true);
            useOriginCheckBox.setText("Use origin");
            useOriginCheckBox.addActionListener(this);

            originSpinner = new JSpinner();
            originSpinner.setModel(new javax.swing.SpinnerNumberModel(500, 0, 5000, 10));
            originSpinner.setToolTipText("");
            originSpinner.setMaximumSize(new java.awt.Dimension(100, 100));
            originSpinner.setValue(seriesRegionTool1.getOrigin());
            originSpinner.addChangeListener(this);

            drawBehindCheckBox = new JCheckBox();
            drawBehindCheckBox.setMnemonic('D');
            drawBehindCheckBox.setSelected(true);
            drawBehindCheckBox.setText("Draw behind");
            drawBehindCheckBox.addActionListener(this);

            view3DCheckBox = new JCheckBox();
            view3DCheckBox.setMnemonic('V');
            view3DCheckBox.setSelected(false);
            view3DCheckBox.setText("View 3D");
            view3DCheckBox.addActionListener(this);
        }
        {
            tmpPane.add(editButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(lowerLabel);
            tmpPane.add(Box.createHorizontalStrut(3));
            tmpPane.add(lowerSpinner);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(upperLabel);
            tmpPane.add(Box.createHorizontalStrut(3));
            tmpPane.add(upperSpinner);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(useOriginCheckBox);
            tmpPane.add(originSpinner);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(drawBehindCheckBox);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(view3DCheckBox);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private Line line1;
    private JButton editButton;
    private JLabel lowerLabel, upperLabel;
    private JSpinner lowerSpinner, upperSpinner, originSpinner;
    private JCheckBox useOriginCheckBox, drawBehindCheckBox, view3DCheckBox;

    private boolean isChanging = false;

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (!isChanging) {
            isChanging = true;

            if (source == editButton) {
                ChartEditor.editTool(seriesRegionTool1);
            } else if (source == useOriginCheckBox) {
                seriesRegionTool1.setUseOrigin(useOriginCheckBox.isSelected());
                originSpinner.setEnabled(useOriginCheckBox.isSelected());
            } else if (source == drawBehindCheckBox) {
                seriesRegionTool1.setDrawBehindSeries(drawBehindCheckBox.isSelected());
            } else if (source == view3DCheckBox) {
                chart1.getAspect().setView3D(view3DCheckBox.isSelected());
            }

            chart1.refreshControl();
            isChanging = false;
        }
    }

    public void stateChanged(ChangeEvent e) {
        Object aux = e.getSource();

        if (!isChanging) {
            isChanging = true;

            if (aux == lowerSpinner) {
                seriesRegionTool1.setLowerBound(line1.getXValues().getValue((Integer) lowerSpinner.getValue()));
            } else if (aux == upperSpinner) {
                seriesRegionTool1.setUpperBound(line1.getXValues().getValue((Integer) upperSpinner.getValue()));
            } else if (aux == originSpinner) {
                seriesRegionTool1.setOrigin((Integer) originSpinner.getValue());
            }

            chart1.refreshControl();
            isChanging = false;
        }
    }
}
