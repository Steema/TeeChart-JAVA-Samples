package features.tool;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.LineCap;
import com.steema.teechart.styles.Line;
import com.steema.teechart.themes.ThemesList;
import com.steema.teechart.tools.AxisBreak;
import com.steema.teechart.tools.AxisBreakStyle;
import com.steema.teechart.tools.AxisBreaksTool;
import features.ChartSamplePanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

/**
 *
 * @author Steema Software 2011
 */
public class AxisBreakDemo extends ChartSamplePanel
    implements ActionListener, ItemListener {

    AxisBreaksTool tool1;

    private JCheckBox leftTopButton;

    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void itemStateChanged(ItemEvent e) {

       Object source = e.getItemSelectable();
       boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);

       if (source == leftTopButton) {
            tool1.setActive(isSelected);

            if (!isSelected)
              tool1.getBreaks().clear();
            else
            {
                AxisBreak aBreak=new AxisBreak(tool1);
                aBreak.setStartValue(12);
                aBreak.setEndValue(21);
                aBreak.setStyle(AxisBreakStyle.SMALLZIGZAG);

                AxisBreak aBreak2=new AxisBreak(tool1);
                aBreak2.setStartValue(33);
                aBreak2.setEndValue(42);
                aBreak2.setStyle(AxisBreakStyle.SMALLZIGZAG);

                tool1.getBreaks().add(aBreak);
                tool1.getBreaks().add(aBreak2);

            }

        }
    }

     /**
     * Creates a new instance of AxisBreakDemo
     */
    public AxisBreakDemo() {
        super();
        leftTopButton.addItemListener(this);
    }

        protected void initComponents() {
        super.initComponents();

        chart1.getChart().getAspect().setView3D(false);
        chart1.getChart().getLegend().setVisible(false);

        Line tmpSeries = new com.steema.teechart.styles.Line(chart1.getChart());
        tmpSeries.setColor(Color.GREEN_YELLOW);
        tmpSeries.getLinePen().setWidth(2);
//        tmpSeries.getLinePen().setEndCap(LineCap.BEVEL);
//        tmpSeries.getPointer().getPen().setColor(Color.fromArgb(128, 71, 119, 0));
//        tmpSeries.getPointer().setVertSize(4);
//        tmpSeries.getPointer().setHorizSize(4);
//        tmpSeries.getPointer().setVisible(true);

        tmpSeries.setSmoothed(true);

        tmpSeries.fillSampleValues(50);
        tmpSeries.getMarks().setVisible(false);

        tool1 = new AxisBreaksTool(chart1.getChart().getAxes().getBottom());
        tool1.setAxis(chart1.getAxes().getBottom());

        AxisBreak aBreak=new AxisBreak(tool1);
        aBreak.setStartValue(12);
        aBreak.setEndValue(21);
        aBreak.setStyle(AxisBreakStyle.SMALLZIGZAG);

        AxisBreak aBreak2=new AxisBreak(tool1);
        aBreak2.setStartValue(33);
        aBreak2.setEndValue(42);
        aBreak2.setStyle(AxisBreakStyle.SMALLZIGZAG);

        tool1.getBreaks().add(aBreak);
        tool1.getBreaks().add(aBreak2);
        tool1.setGapSize(20);
        tool1.getBrush().setColor(Color.GRAY);

        leftTopButton = new JCheckBox("Active Axis Break");
        leftTopButton.setSelected(true);

        ThemesList.applyTheme(chart1.getChart(), 1);
    }

        protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(leftTopButton);

        }
    }

}
