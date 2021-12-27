/*
 * StackGroupDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.bar;
import com.steema.teechart.legend.Legend;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.Bar;
import com.steema.teechart.styles.MultiBars;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class StackGroupDemo extends ChartSamplePanel
        implements ChangeListener{

    /**
     * Creates a new instance of StackGroupDemo
     */
    public StackGroupDemo() {
        super();
        stackSpinner.addChangeListener(this);
    }

    public void stateChanged(ChangeEvent e) {
        SpinnerModel tmpModel = stackSpinner.getModel();
        if (tmpModel instanceof SpinnerNumberModel) {
            setStackGroup(((SpinnerNumberModel)tmpModel).getNumber().intValue());
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getAspect().setView3D(false);
        Legend tmpLegend = chart1.getLegend();
        tmpLegend.setCheckBoxes(true);
        tmpLegend.getFont().setBold(true);
        tmpLegend.setFontSeriesColor(true);
        tmpLegend.getGradient().setEndColor(Color.SILVER);
        tmpLegend.getGradient().setMiddleColor(Color.SILVER);
        tmpLegend.getGradient().setStartColor(Color.WHITE);
        tmpLegend.getGradient().setVisible(true);
    }

    protected void initComponents() {
        super.initComponents();
        Bar barSeries = null;
        for (int i=0; i < 5; i++) {
            barSeries = new com.steema.teechart.styles.Bar(chart1.getChart());
            barSeries.setMultiBar(MultiBars.STACKED);
            barSeries.getMarks().setVisible(false);
            barSeries.fillSampleValues(6);
        }
        SpinnerModel stackModel = new SpinnerNumberModel(3,
                1,
                chart1.getSeriesCount(),
                1);
        stackSpinner = new JSpinner(stackModel);
        setStackGroup(3);
    }

    protected void initGUI() {
        super.initGUI();
        JLabel tmpLabel;
        JPanel tmpPane = getButtonPane();
        {
            tmpLabel = new JLabel("First stack has:");
            tmpLabel.setDisplayedMnemonic('F');
            tmpLabel.setLabelFor(stackSpinner);
            tmpPane.add(tmpLabel);
            tmpPane.add(stackSpinner);
            tmpPane.add(new JLabel("series"));
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private void setStackGroup(int series) {
        for(int i=0; i < series; i++) {
            ((Bar)chart1.getSeries(i)).setStackGroup(0);
        };
        for(int i=series; i < chart1.getSeriesCount(); i++) {
            ((Bar)chart1.getSeries(i)).setStackGroup(1);
        };
    }

    private JSpinner stackSpinner;
}
