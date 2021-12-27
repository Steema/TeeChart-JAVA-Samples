/*
 * SmoothSegmentDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.contour;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.events.TextResolver;
import com.steema.teechart.styles.Contour;
import com.steema.teechart.styles.ContourLevel;
import com.steema.teechart.styles.MarksStyle;
import com.steema.teechart.tools.MarksTip;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Box;
import javax.swing.JCheckBox;
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
public class SmoothSegmentDemo extends ChartSamplePanel
        implements ItemListener, ChangeListener {

    private Contour series;
    private MarksTip tool;

    /**
     * Creates a new instance of SmoothSegmentDemo
     */
    public SmoothSegmentDemo() {
        super();
        smoothLinesButton.addItemListener(this);
        interpolateButton.addItemListener(this);
        segmentPointsButton.addItemListener(this);
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == smoothLinesButton) {
            //series.getSmoothing().setActive(isSelected);
            interpolateButton.setEnabled(isSelected);
            factorSpinner.setEnabled(isSelected);
        } else if ( source == interpolateButton) {
            //series.getSmoothing().setInterpolate(isSelected);
        } else if ( source == segmentPointsButton) {
            chart1.getChart().invalidate();
        }
    }

    public void stateChanged(ChangeEvent e) {
        Object source = e.getSource();
        if (source == factorSpinner) {
            refreshFactor();
        }
    }

    //TODO Events, Smoothing;

    protected void initChart() {
        super.initChart();
        chart1.getLegend().setVisible(false);
        chart1.getHeader().setVisible(true);
        chart1.setText("Smooth Contour lines");
        chart1.getAspect().setView3D(false);
    }

    protected void initComponents() {
        super.initComponents();

        series = new com.steema.teechart.styles.Contour(chart1.getChart());
        series.setAutomaticLevels(false);
        series.fillSampleValues(6);
        series.setYPosition(0.19);
        setCustomLevels();

        tool = new MarksTip(chart1.getChart());
        tool.setStyle(MarksStyle.VALUE);
        tool.setToolTipResolver( new TextResolver() {
            public String getText(Object sender, String text) {
                String result=text;
                int tmpLevel=-1;
                int tmpPoint=-1;
                int tmpSegment=-1;
                // Find level and segment under mouse cursor...
                /*
                    TODO: implement segments in Level

                with Chart1.GetCursorPos do
                    tmpLevel:=Series1.Levels.Clicked(x,y,tmpSegment,tmpPoint);
                */

                // If mouse is over a segment line...
                if (tmpLevel!=-1) {
                    result = "Level: "+tmpLevel+"\n"
                            + "Segment: "+tmpSegment+"\n"
                            + "Point: "+tmpPoint;
                } else {
                    result = "?";
                }
                return result;
            };
        });

        smoothLinesButton = new JCheckBox("Smooth lines");
        interpolateButton = new JCheckBox("Interpolate");
        segmentPointsButton = new JCheckBox("Draw segment points");
        factorSpinnerModel = new SpinnerNumberModel(
                4,
                1,
                Integer.MAX_VALUE,
                1);
        factorSpinner = new JSpinner(factorSpinnerModel);
        factorSpinner.setEnabled(false);
        refreshFactor();
    }

    protected void initGUI() {
        super.initGUI();

        JLabel tmpLabel;
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(smoothLinesButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(interpolateButton);
            tmpLabel = new JLabel("Factor:");
            tmpLabel.setDisplayedMnemonic('F');
            tmpLabel.setLabelFor(factorSpinner);
            tmpPane.add(tmpLabel);
            tmpPane.add(factorSpinner);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(segmentPointsButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private void setCustomLevels() {
        series.setNumLevels(10);
        ContourLevel tmpLevel;
        tmpLevel = new ContourLevel(series);
        tmpLevel.setColor(Color.RED);
        tmpLevel.setUpToValue(0.82);
        series.getLevels().add(tmpLevel);
        tmpLevel = new ContourLevel(series);
        tmpLevel.setColor(Color.GREEN);
        tmpLevel.setUpToValue(-0.62);
        series.getLevels().add(tmpLevel);
        tmpLevel = new ContourLevel(series);
        tmpLevel.setColor(Color.YELLOW);
        tmpLevel.setUpToValue(-0.41);
        series.getLevels().add(tmpLevel);
        tmpLevel = new ContourLevel(series);
        tmpLevel.setColor(Color.BLUE);
        tmpLevel.setUpToValue(-0.21);
        series.getLevels().add(tmpLevel);
        tmpLevel = new ContourLevel(series);
        tmpLevel.setColor(Color.WHITE);
        tmpLevel.setUpToValue(-0.012);
        series.getLevels().add(tmpLevel);
        tmpLevel = new ContourLevel(series);
        tmpLevel.setColor(Color.GRAY);
        tmpLevel.setUpToValue(0.19);
        series.getLevels().add(tmpLevel);
        tmpLevel = new ContourLevel(series);
        tmpLevel.setColor(Color.FUCHSIA);
        tmpLevel.setUpToValue(0.39);
        series.getLevels().add(tmpLevel);
        tmpLevel = new ContourLevel(series);
        tmpLevel.setColor(Color.TEAL);
        tmpLevel.setUpToValue(0.59);
        series.getLevels().add(tmpLevel);
        tmpLevel = new ContourLevel(series);
        tmpLevel.setColor(Color.NAVY);
        tmpLevel.setUpToValue(0.79);
        series.getLevels().add(tmpLevel);
        tmpLevel = new ContourLevel(series);
        tmpLevel.setColor(Color.MAROON);
        tmpLevel.setUpToValue(1);
        series.getLevels().add(tmpLevel);
    }

    private void refreshFactor() {
        int factor = factorSpinnerModel.getNumber().intValue();
        //series.getSmoothing().setFactor(factor);;
        series.repaint();
    }

    private JCheckBox smoothLinesButton, interpolateButton, segmentPointsButton;
    private JSpinner factorSpinner;
    private SpinnerNumberModel factorSpinnerModel;
}
