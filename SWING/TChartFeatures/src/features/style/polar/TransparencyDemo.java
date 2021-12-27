/*
 * TransparencyDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.polar;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.HatchStyle;
import com.steema.teechart.styles.Polar;
import javax.swing.Box;
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
public class TransparencyDemo extends ChartSamplePanel
    implements ChangeListener {

    private Polar series1, series2, series3;

    /**
     * Creates a new instance of TransparencyDemo
     */
    public TransparencyDemo() {
        super();
        trans1Spinner.addChangeListener(this);
        trans2Spinner.addChangeListener(this);
        trans2Spinner.addChangeListener(this);
    }

    public void stateChanged(ChangeEvent e) {
        Object source = e.getSource();
        if (source == trans1Spinner) {
            series1.setTransparency(((SpinnerNumberModel)trans1Spinner.getModel()).getNumber().intValue());
            //series1.repaint();
        } else if (source == trans2Spinner) {
            series2.setTransparency(((SpinnerNumberModel)trans2Spinner.getModel()).getNumber().intValue());
            series2.repaint();
        } else if (source == trans3Spinner) {
            series3.setTransparency(((SpinnerNumberModel)trans3Spinner.getModel()).getNumber().intValue());
            series3.repaint();
        }
    }

    protected void initComponents() {
        super.initComponents();

        series1 = new com.steema.teechart.styles.Polar(chart1.getChart());
        series1.getBrush().setColor(Color.RED);
        series1.getBrush().setStyle(HatchStyle.BACKWARDDIAGONAL);
        series1.getPointer().getBrush().setColor(Color.WHITE);

        series2 = new com.steema.teechart.styles.Polar(chart1.getChart());
        series2.getBrush().setColor(Color.WHITE);
        series2.getBrush().setStyle(HatchStyle.CROSS);
        series2.getPointer().getPen().setColor(Color.BLUE);

        series3 = new com.steema.teechart.styles.Polar(chart1.getChart());
        series3.getBrush().setColor(Color.YELLOW);
        series3.getBrush().setStyle(HatchStyle.FORWARDDIAGONAL);
        series3.getPointer().getPen().setColor(Color.FUCHSIA);

        for (int t=0; t < chart1.getSeriesCount(); t++) {
            chart1.getSeries(t).fillSampleValues(10);
            ((Polar)chart1.getSeries(t)).getPointer().setVisible(true);
            chart1.getSeries(t).getMarks().setVisible(false);
            ((Polar)chart1.getSeries(t)).setTransparency(30);
            ((Polar)chart1.getSeries(t)).setCircled(true);
        }

        trans1Spinner = new JSpinner(new SpinnerNumberModel(
                30,
                0,
                100,
                5));
        trans2Spinner = new JSpinner(new SpinnerNumberModel(
                30,
                0,
                100,
                5));
        trans3Spinner = new JSpinner(new SpinnerNumberModel(
                30,
                0,
                100,
                5));
    }

    protected void initGUI() {
        super.initGUI();
        chart1.getHeader().setVisible(true);
        chart1.setText("Transparency in Polar Series");
        chart1.getAxes().getBottom().setIncrement(10.0);
        chart1.getAspect().setView3D(false);
        JPanel tmpPane = getButtonPane();
        {
            JLabel tmpLabel;
            tmpLabel = new JLabel("Transparency:");
            tmpLabel.setDisplayedMnemonic('T');
            tmpLabel.setLabelFor(trans1Spinner);
            tmpPane.add(tmpLabel);
            tmpPane.add(trans1Spinner);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(trans2Spinner);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(trans3Spinner);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JSpinner trans1Spinner, trans2Spinner, trans3Spinner;
}
