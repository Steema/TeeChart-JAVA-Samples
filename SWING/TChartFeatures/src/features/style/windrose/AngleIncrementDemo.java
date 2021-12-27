/*
 * AngleIncrementDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.windrose;
import com.steema.teechart.legend.Legend;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.GradientDirection;
import com.steema.teechart.styles.WindRose;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class AngleIncrementDemo extends ChartSamplePanel
    implements ActionListener {

    private WindRose series1;

    /** Creates a new instance of AngleIncrementDemo */
    public AngleIncrementDemo() {
        super();
        anglesList.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == anglesList) {
            switch (anglesList.getSelectedIndex()) {
                case 0: series1.setAngleIncrement(15); break;
                case 1: series1.setAngleIncrement(30); break;
                case 2: series1.setAngleIncrement(45); break;
                case 3: series1.setAngleIncrement(90); break;
            }
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getAspect().setView3D(false);

        Legend tmpLegend = chart1.getLegend();
        //tmpLegend.setColorWidth(35);
        tmpLegend.getFont().setColor(Color.RED);
        tmpLegend.getFont().setSize(15);
        tmpLegend.getFont().setBold(true);
        tmpLegend.getGradient().setStartColor(Color.GRAY);
        tmpLegend.getGradient().setVisible(true);
        tmpLegend.getGradient().setDirection(GradientDirection.HORIZONTAL);
        tmpLegend.getSymbol().setWidth(35);

        chart1.getAxes().getBottom().setIncrement(10.0);
        chart1.getPanel().setColor(Color.GRAY);
    }

    protected void initComponents() {
        super.initComponents();

        series1 = new WindRose(chart1.getChart());
        series1.fillSampleValues(20);
        series1.setCircled(true);
        series1.setAngleIncrement(15);
        series1.getBrush().setVisible(false);
        series1.getMarks().setVisible(false);
        series1.setCircleBackColor(Color.YELLOW);
        series1.getCircleLabelsFont().setColor(Color.BLUE);
        series1.getCircleLabelsFont().setBold(true);
        series1.getCircleLabelsFont().setItalic(true);
        series1.getCirclePen().setColor(Color.RED);
        series1.getCirclePen().setWidth(2);
        series1.getPointer().getBrush().setColor(Color.BLUE);
        series1.getPointer().getPen().setColor(Color.WHITE);
        series1.getPointer().setHorizSize(2);
        series1.getPointer().setVertSize(2);
        series1.getPointer().setVisible(true);
        series1.getPen().setColor(Color.LIME);
        series1.getPen().setWidth(2);

        anglesList = new JComboBox(new String[] {"15", "30", "45", "90"});
        anglesList.setSelectedIndex(0);
    }

    protected void initGUI() {
        super.initGUI();
        JLabel tmpLabel;
        JPanel tmpPane = getButtonPane();
        {
            tmpLabel = new JLabel("Angle Increment:");
            tmpLabel.setDisplayedMnemonic('A');
            tmpLabel.setLabelFor(anglesList);
            tmpPane.add(tmpLabel);
            tmpPane.add(anglesList);
            tmpPane.add(new JLabel("degrees"));
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JComboBox anglesList;
}
