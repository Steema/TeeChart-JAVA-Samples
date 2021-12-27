/*
 * ColorBandDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.tool.colorband;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.HatchStyle;
import com.steema.teechart.drawing.GradientDirection;
import com.steema.teechart.styles.Bar;
import com.steema.teechart.tools.ColorBand;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
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
public class ColorBandDemo extends ChartSamplePanel
        implements ChangeListener, ItemListener {

    /**
     * Creates a new instance of ColorBandDemo
     */
    public ColorBandDemo() {
        super();
        gradientButton.addItemListener(this);
        drawBehindButton.addItemListener(this);
        drawBehindAxesButton.addItemListener(this);
        invertAxisButton.addItemListener(this);
        showBandsButton.addItemListener(this);
        bandSpinner.addChangeListener(this);
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        ColorBand selTool = getSelectedBand();

        if (source == invertAxisButton) {
            chart1.getAxes().getLeft().setInverted(isSelected);
        } else if (source == showBandsButton) {
            for (int t=0; t < chart1.getTools().size(); t++) {
                if (chart1.getTools().getTool(t) instanceof ColorBand) {
                    chart1.getTools().getTool(t).setActive(isSelected);
                }
            }
        } else if (source == gradientButton) {
            selTool.getGradient().setVisible(isSelected);
        } else if (source == drawBehindButton) {
            selTool.setDrawBehind(isSelected);
        } else if (source == drawBehindAxesButton) {
            //@TODO
        }
    }

    public void stateChanged(ChangeEvent e) {
        Object source = e.getSource();

        if (source == bandSpinner) {
            refreshBandProperties();
        };
    }

    protected void initChart() {
        super.initChart();
        chart1.getLegend().setVisible(false);
    }

    protected void initComponents() {
        super.initComponents();

        Bar tmpSeries = new com.steema.teechart.styles.Bar(chart1.getChart());
        tmpSeries.add(new int[] {30,150,75,280,600});

        ColorBand tmpTool;

        // tool1
        tmpTool = new com.steema.teechart.tools.ColorBand(chart1.getChart());
        tmpTool.getBrush().setStyle(HatchStyle.BACKWARDDIAGONAL);
        tmpTool.setEnd(100.0);
        tmpTool.getPen().setVisible(false);
        tmpTool.setAxis(chart1.getAxes().getLeft());

        //tool2
        tmpTool = new com.steema.teechart.tools.ColorBand(chart1.getChart());
        tmpTool.getBrush().setColor(Color.BLUE);
        tmpTool.setEnd(200.0);
        tmpTool.setStart(100.0);
        tmpTool.getPen().setVisible(false);
        tmpTool.setAxis(chart1.getAxes().getLeft());

        //tool3
        tmpTool = new com.steema.teechart.tools.ColorBand(chart1.getChart());
        tmpTool.getBrush().setColor(Color.YELLOW);
        tmpTool.getGradient().setDirection(GradientDirection.HORIZONTAL);
        tmpTool.getGradient().setEndColor(Color.GRAY);
        tmpTool.getGradient().setMiddleColor(Color.WHITE);
        tmpTool.setEnd(300.0);
        tmpTool.setStart(200.0);
        tmpTool.getPen().setVisible(false);
        tmpTool.setAxis(chart1.getAxes().getLeft());

        //tool4
        tmpTool = new com.steema.teechart.tools.ColorBand(chart1.getChart());
        tmpTool.getBrush().setColor(Color.LIME);
        tmpTool.setEnd(400.0);
        tmpTool.setStart(300.0);
        tmpTool.getPen().setVisible(false);
        tmpTool.setAxis(chart1.getAxes().getLeft());

        //tool5
        tmpTool = new com.steema.teechart.tools.ColorBand(chart1.getChart());
        tmpTool.getBrush().setColor(Color.WHITE);
        tmpTool.setEnd(700.0);
        tmpTool.setStart(500.0);
        tmpTool.getPen().setVisible(false);
        tmpTool.setAxis(chart1.getAxes().getLeft());

        gradientButton = new JCheckBox("Gradient");
        drawBehindButton = new JCheckBox("Draw Behind");
        drawBehindAxesButton = new JCheckBox("Draw Behind Axes");
        invertAxisButton = new JCheckBox("Invert Axis");
        showBandsButton = new JCheckBox("Show Bands");
        showBandsButton.setSelected(true);

        bandSpinnerModel = new SpinnerNumberModel(0,
                0,
                chart1.getTools().size()-1,
                1);
        bandSpinner = new JSpinner(bandSpinnerModel);

        refreshBandProperties();
    }

    protected void initGUI() {
        super.initGUI();
        JLabel tmpLabel;
        JPanel hPane;
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.setLayout( new BoxLayout(tmpPane, BoxLayout.Y_AXIS) );
            hPane = new JPanel();
            {
                hPane.setLayout( new BoxLayout(hPane, BoxLayout.X_AXIS) );
                hPane.add(showBandsButton);
                hPane.add(invertAxisButton);
            }
            tmpPane.add(hPane);
            hPane = new JPanel();
            {
                hPane.setLayout( new BoxLayout(hPane, BoxLayout.X_AXIS) );
                tmpLabel = new JLabel("Band:");
                tmpLabel.setDisplayedMnemonic('B');
                tmpLabel.setLabelFor(bandSpinner);
                hPane.add(tmpLabel);
                hPane.add(bandSpinner);
                hPane.add(Box.createHorizontalStrut(10));
                hPane.add(gradientButton);
                hPane.add(drawBehindButton);
                hPane.add(drawBehindAxesButton);
            }
            tmpPane.add(hPane);
        }
    }

    private ColorBand getSelectedBand() {
        int selBand = bandSpinnerModel.getNumber().intValue();
        return (ColorBand)chart1.getTools().getTool(selBand); //assume only ColorBand tools
    }

    private void refreshBandProperties() {
        ColorBand tmpTool = getSelectedBand();
        gradientButton.setSelected(tmpTool.getGradient().getVisible());
        drawBehindButton.setSelected(tmpTool.getDrawBehind());
        //todo  drawBehindAxis
    }

    private JCheckBox gradientButton, drawBehindButton, drawBehindAxesButton, invertAxisButton, showBandsButton;
    private JSpinner bandSpinner;
    private SpinnerNumberModel bandSpinnerModel;
}
