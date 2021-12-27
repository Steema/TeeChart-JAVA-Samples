/*
 * SARDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.function;
import com.steema.teechart.axis.Axis;
import com.steema.teechart.BevelStyle;
import com.steema.teechart.drawing.ChartFont;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.DashStyle;
import com.steema.teechart.functions.SAR;
import com.steema.teechart.styles.Line;
import com.steema.teechart.styles.Candle;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;
import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JFormattedTextField;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class SARDemo extends ChartSamplePanel
    implements PropertyChangeListener {

    private Candle series;
    private SAR function;

    /** Creates a new instance of VarianceDemo */
    public SARDemo() {
        super();
        accFactor.addPropertyChangeListener("value",this);
        maxStep.addPropertyChangeListener("value",this);
    }
    
     public void propertyChange(PropertyChangeEvent e) {
        Object source = e.getSource();
        double tmpValue=-1;
        if (source == accFactor) {
            tmpValue = ((Number)accFactor.getValue()).doubleValue();
            function.SetAF(tmpValue);
        }
        else if (source == maxStep) {
            tmpValue = ((Number)maxStep.getValue()).doubleValue();
            function.SetMS(tmpValue);
        }
     }


    protected void initChart() {
        super.initChart();
        chart1.getAspect().setView3D(false);
        chart1.getAspect().setChart3DPercent(15);
        chart1.getPanel().setBevelOuter(BevelStyle.NONE);
        chart1.getPanel().setColor(Color.SILVER);
        chart1.getWalls().getBack().setColor(Color.WHITE);
        chart1.getWalls().getBack().setTransparent(false);
        chart1.getWalls().getLeft().setColor(Color.WHITE);
        chart1.getWalls().getRight().setColor(Color.WHITE);
        chart1.getLegend().getFont().setSize(12);
        chart1.getLegend().getFont().setName("Lucida Console");
        chart1.getLegend().getShadow().setColor(Color.GRAY);
        chart1.setText("SAR function");
        ChartFont font = chart1.getHeader().getFont();
        font.setColor(Color.BLACK);
        font.setSize(13);
        font.setName("Lucida Console");
        font.setBold(true);

        Axis axis;
        axis = chart1.getAxes().getBottom();
        axis.getGrid().setVisible(false);
        axis.getLabels().getFont().setName("Lucida Console");
        axis.getMinorTicks().setLength(3);
        axis.getMinorTicks().setColor(Color.BLACK);
        axis.getTicksInner().setLength(6);
        axis.getTicksInner().setColor(Color.BLACK);
        axis.getTicks().setLength(0);

        axis = chart1.getAxes().getLeft();
        axis.getGrid().setColor(Color.SILVER);
        axis.getGrid().setStyle(DashStyle.SOLID);
        axis.getLabels().getFont().setName("Lucida Console");
        axis.getMinorTicks().setLength(3);
        axis.getMinorTicks().setColor(Color.BLACK);
        axis.getTicksInner().setLength(6);
        axis.getTicksInner().setColor(Color.BLACK);
        axis.getTicks().setLength(0);
    }

    protected void initComponents() {
        super.initComponents();
        
        series = new Candle(chart1.getChart());
        series.setTitle("Source");
        series.fillSampleValues(10);

        function = new com.steema.teechart.functions.SAR();
        function.setChart(chart1.getChart());
        function.setPeriod(0); //all points

        Line functionSeries = new Line(chart1.getChart());
        functionSeries.getMarks().setVisible(true);
        functionSeries.getMarks().setArrowLength(2);
        functionSeries.getMarks().setTransparent(true);
        functionSeries.setTitle("SAR");
        functionSeries.setDataSource(series);
        functionSeries.setFunction(function);

        NumberFormat limitFormat = NumberFormat.getNumberInstance();;
        accFactor = new JFormattedTextField(limitFormat);
        accFactor.setValue(new Double(0.02));
        maxStep = new JFormattedTextField(limitFormat);
        maxStep.setValue(new Double(0.2));
    }

    protected void initGUI() {
        super.initGUI();
        JLabel tmpLabel;
        JPanel tmpPane = getButtonPane();
        {
            tmpLabel = new JLabel("Acceleration factor:");
            tmpLabel.setDisplayedMnemonic('U');
            tmpLabel.setLabelFor(accFactor);
            tmpPane.add(tmpLabel);
            tmpPane.add(accFactor);
            tmpPane.add(Box.createHorizontalStrut(10));

            tmpLabel = new JLabel("Maximum step:");
            tmpLabel.setLabelFor(maxStep);
            tmpPane.add(tmpLabel);
            tmpPane.add(maxStep);
        }
    }

    private JFormattedTextField  accFactor, maxStep;
}

