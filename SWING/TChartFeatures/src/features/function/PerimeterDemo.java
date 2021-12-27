/*
 * PerimeterDemo.java
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
import com.steema.teechart.functions.Perimeter;
import com.steema.teechart.styles.Line;
import com.steema.teechart.styles.Points;
import com.steema.teechart.styles.ValueListOrder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class PerimeterDemo extends ChartSamplePanel
    implements ActionListener {

    private Points series;
    private Perimeter function;

    /** Creates a new instance of PerimeterDemo */
    public PerimeterDemo() {
        super();
        randomButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        fillSeries();
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
        chart1.getLegend().getFont().setSize(13);
        chart1.getLegend().getFont().setName("Times New Roman");
        chart1.getLegend().getShadow().setVisible(false);
        chart1.getLegend().getSymbol().getPen().setVisible(false);
        chart1.getLegend().setTransparent(true);

        chart1.setText("Perimeter function");
        ChartFont font = chart1.getHeader().getFont();
        font.setColor(Color.BLACK);
        font.setSize(16);
        font.setName("Times New Roman");
        font.setBold(true);

        Axis axis;
        axis = chart1.getAxes().getBottom();
        axis.getGrid().setColor(Color.BLACK);
        axis.getGrid().setStyle(DashStyle.SOLID);
        axis.getGrid().setCentered(true);
        axis.getLabels().getFont().setSize(13);
        axis.getLabels().getFont().setName("Times New Roman");
        axis.getMinorTicks().setVisible(false);
        axis.getTicksInner().setVisible(false);
        axis.getTicks().setColor(Color.BLACK);

        axis = chart1.getAxes().getLeft();
        axis.getGrid().setColor(Color.BLACK);
        axis.getGrid().setStyle(DashStyle.SOLID);
        axis.getLabels().getFont().setSize(13);
        axis.getLabels().getFont().setName("Times New Roman");
        axis.getMinorTicks().setVisible(false);
        axis.getTicksInner().setVisible(false);
        axis.getTicks().setColor(Color.BLACK);
    }

    protected void initComponents() {
        super.initComponents();
        series = new Points(chart1.getChart());

        function = new com.steema.teechart.functions.Perimeter();
        function.setChart(chart1.getChart());
        function.setPeriod(0); //all points

        Line functionSeries = new Line(chart1.getChart());
        functionSeries.setTitle("Perimeter");
        functionSeries.setDataSource(series);
        functionSeries.getXValues().setOrder(ValueListOrder.NONE);
        //functionSeries.setVerticalAxis(VerticalAxis.RIGHT);
        functionSeries.setFunction(function);

        randomButton = new JButton("New random values");

        fillSeries();
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(randomButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private void fillSeries() {
         Random generator = new Random();
         series.clear();
         for (int t=0; t<99; t++) {
             series.add(generator.nextInt(100)*generator.nextInt(10),
                     generator.nextInt(100)*generator.nextInt(10));
         }
         function.recalculate();
    }

    private JButton randomButton;
}
