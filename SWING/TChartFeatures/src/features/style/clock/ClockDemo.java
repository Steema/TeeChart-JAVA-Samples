/*
 * ClockDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.clock;

import com.steema.teechart.BevelStyle;
import com.steema.teechart.TChart;
import com.steema.teechart.Wall;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.DashStyle;
import com.steema.teechart.drawing.Gradient;
import com.steema.teechart.styles.Clock;
import com.steema.teechart.styles.ClockStyle;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
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
import features.BasicSamplePanel;

/**
 *
 * @author tom
 */
public class ClockDemo extends BasicSamplePanel
    implements ChangeListener, ItemListener {

    private TChart[] chart;
    private Clock[] clock;

    /** Creates a new instance of ClockDemo */
    public ClockDemo() {
        super();
        JPanel tmpPanel = new JPanel();
        tmpPanel.setLayout( new GridBagLayout() );
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.3;
        c.weighty = 0.3;
        c.gridheight = 1;
        tmpPanel.add(chart[1], c);

        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 1.0;
        c.weighty = 0.4;
        c.gridheight = 2;
        tmpPanel.add(chart[3], c);

        c.gridx = 0;
        c.gridy = 1;
        c.weighty = 1.0;
        c.gridheight = 2;
        tmpPanel.add(chart[2], c);

        c.gridx = 1;
        c.gridy = 2;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.gridwidth = 1;
        c.gridheight = 1;
        tmpPanel.add(chart[0], c);

        setSamplePane(tmpPanel);

        romanButton.addItemListener(this);
        localTimeSpinner.addChangeListener(this);
        refreshSpinner.addChangeListener(this);
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == romanButton) {
            ClockStyle tmpStyle = isSelected ? ClockStyle.ROMAN : ClockStyle.DECIMAL;
            for (int t=0; t < clock.length; t++) {
                clock[t].setStyle(tmpStyle);
            }
        }
    }

    public void stateChanged(ChangeEvent e) {
        Object source = e.getSource();
        if (source == refreshSpinner) {
            int delay = ((SpinnerNumberModel)refreshSpinner.getModel()).getNumber().intValue();
            for (int t=0; t < clock.length; t++) {
                clock[t].getTimer().setDelay(delay);
            }
        }
    }

    protected void initCharts() {
        Gradient tmpGradient;
        Wall tmpWall;

        chart = new TChart[4];
        for (int t=0; t < chart.length; t++)  {
            chart[t] = new TChart();
            chart[t].getAspect().setView3D(false);
            chart[t].getAxes().setVisible(false);
            chart[t].getAxes().getBottom().setIncrement(30.0);
            chart[t].getFooter().setVisible(false);
            chart[t].getFrame().setVisible(false);
            chart[t].getHeader().setVisible(false);
            chart[t].getLegend().setVisible(false);
            chart[t].getPanning().setActive(false);
            chart[t].getWalls().setView3D(false);
            chart[t].getZoom().setAllow(false);
            chart[t].setClipPoints(false);
            tmpWall = chart[t].getWalls().getBack();
            tmpWall.getBrush().setColor(Color.WHITE);
            tmpWall.getBrush().setVisible(false);
            tmpWall.getPen().setVisible(false);
        }

        TChart tmpChart;

        /* chart0 */
        tmpChart = chart[0];
        tmpChart.getPanel().setBevelOuter(BevelStyle.NONE);
        tmpGradient = tmpChart.getPanel().getGradient();
        //tmpGradient.setDirection(FROMCENTER) TODO
        tmpGradient.setEndColor(Color.SILVER);
        tmpGradient.setStartColor(Color.BLACK);
        tmpGradient.setVisible(true);

        /* chart1 */
        tmpChart = chart[1];
        tmpChart.getPanel().setBevelOuter(BevelStyle.NONE);
        tmpGradient = tmpChart.getPanel().getGradient();
        //tmpGradient.setDirection(FROMTOPLEFT) TODO
        tmpGradient.setEndColor(Color.WHITE);
        tmpGradient.setStartColor(Color.ORANGE);
        tmpGradient.setVisible(true);

        /* chart2 */
        tmpChart = chart[2];
        tmpGradient = tmpChart.getPanel().getGradient();
        //tmpGradient.setDirection(FROMCENTER) TODO
        tmpGradient.setEndColor(Color.BLACK);
        tmpGradient.setStartColor(Color.LIGHT_YELLOW); //TODO
        tmpGradient.setVisible(true);

        /* chart3 */
        tmpChart = chart[3];
        tmpChart.getPanel().setBevelOuter(BevelStyle.LOWERED);
        tmpChart.getPanel().setBevelInner(BevelStyle.LOWERED);
        tmpGradient = tmpChart.getPanel().getGradient();
        tmpGradient.setEndColor(Color.SILVER);
        tmpGradient.setStartColor(Color.BLACK);
        tmpGradient.setVisible(true);
    }

    protected void initClocks() {

        clock = new Clock[4];
        for (int t=0; t < clock.length; t++) {
            clock[t] = new Clock(chart[t].getChart());
            clock[t].getMarks().setVisible(false);
        }
        Clock tmpClock;

        /* Clock0 */
        tmpClock = clock[0];
        tmpClock.getBrush().setColor(Color.RED);
        tmpClock.getCircleLabelsFont().setColor(Color.LIME);
        tmpClock.getCircleLabelsFont().setSize(13);
        tmpClock.getCircleLabelsFont().setBold(true);
        tmpClock.getCirclePen().setColor(Color.YELLOW);
        tmpClock.getPenHours().setColor(Color.BLACK);
        tmpClock.getPenMinutes().setColor(Color.BLACK);
        tmpClock.getPenSeconds().setColor(Color.WHITE);

        /* Clock1 */
        tmpClock = clock[1];
        tmpClock.setColor(Color.WHITE);
        tmpClock.getBrush().setColor(Color.YELLOW);
        tmpClock.getCircleLabelsFont().setColor(Color.RED);
        tmpClock.getCircleLabelsFont().setSize(16);
        tmpClock.getCircleLabelsFont().setBold(true);
        tmpClock.getCirclePen().setVisible(false);
        tmpClock.getPenHours().setColor(Color.BLUE);
        tmpClock.getPenMinutes().setColor(Color.BLACK);
        tmpClock.getPenSeconds().setColor(Color.WHITE);

        /* Clock2 */
        tmpClock = clock[2];
        tmpClock.setColor(Color.LIME);
        tmpClock.getBrush().setColor(Color.LIME);
        tmpClock.getCircleLabelsFont().setColor(Color.WHITE);
        tmpClock.getCircleLabelsFont().setSize(13);
        tmpClock.getCirclePen().setColor(Color.BLUE);
        tmpClock.getCirclePen().setStyle(DashStyle.DOT);
        tmpClock.getPenHours().setColor(Color.BLACK);
        tmpClock.getPenMinutes().setColor(Color.BLACK);
        tmpClock.getPenSeconds().setColor(Color.WHITE);
        tmpClock.setStyle(ClockStyle.DECIMAL);

        /* Clock3 */
        tmpClock = clock[3];
        tmpClock.setColor(Color.LIME);
        tmpClock.getBrush().setColor(Color.WHITE);
        tmpClock.getCircleLabelsFont().setColor(Color.MAROON);
        tmpClock.getCircleLabelsFont().setSize(16);
        tmpClock.getCircleLabelsFont().setBold(true);
        tmpClock.getCircleLabelsFont().setItalic(true);
        tmpClock.getCirclePen().setVisible(false);
        tmpClock.getPenHours().setColor(Color.TEAL);
        tmpClock.getPenHours().setWidth(3);
        tmpClock.getPenMinutes().setColor(Color.BLACK);
        tmpClock.getPenSeconds().setColor(Color.WHITE);
        tmpClock.getPenSeconds().setWidth(2);
    }

    protected void initComponents() {
        super.initComponents();
        initCharts();
        initClocks();

        romanButton = new JCheckBox("Roman numbers");
        localTimeSpinner = new JSpinner(
            new SpinnerNumberModel(
                0,
                -24,
                24,
                1
            )
        );
        refreshSpinner = new JSpinner(
            new SpinnerNumberModel(
                1000,
                1,
                30000,
                100
            )
        );
    }

    protected void initGUI() {
        super.initGUI();
        JLabel tmpLabel;
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(romanButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpLabel = new JLabel("Local time +/-");
            tmpLabel.setDisplayedMnemonic('L');
            tmpLabel.setLabelFor(localTimeSpinner);
            tmpPane.add(tmpLabel);
            tmpPane.add(localTimeSpinner);
            tmpPane.add( new Label("hours") );
            tmpPane.add(Box.createHorizontalStrut(20));
            tmpLabel = new JLabel("Refresh");
            tmpLabel.setDisplayedMnemonic('R');
            tmpLabel.setLabelFor(refreshSpinner);
            tmpPane.add(tmpLabel);
            tmpPane.add(refreshSpinner);
            tmpPane.add( new Label("msec") );
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JCheckBox romanButton;
    private JSpinner localTimeSpinner, refreshSpinner;
    private final static String URL_CLOCK_BACKGROUND = "background.png";
}
