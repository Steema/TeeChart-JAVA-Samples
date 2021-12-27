/*
 * MarginPixelsDemo.java
 *
 * <p>Copyright: Copyright (c) 2004-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.panel;

import com.steema.teechart.BevelStyle;
import com.steema.teechart.PanelMarginUnits;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.GradientDirection;
import com.steema.teechart.editors.ButtonPen;
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
public class RoundBorderDemo extends ChartSamplePanel
        implements ChangeListener, ItemListener {

    private ButtonPen penButton;

    /**
     * Creates a new instance of MarginPixelsDemo
     */
    public RoundBorderDemo() {
        super();
        roundingSpinner.addChangeListener(this);
        roundedButton.addItemListener(this);
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == roundedButton) {
            if (isSelected) {
                chart1.getPanel().setBorderRound(0);
                roundingSpinner.setValue(new Integer(0));
            }
        }
    }

    public void stateChanged(ChangeEvent e) {
        Object source = e.getSource();
        if (source == roundingSpinner) {
            int value = ((SpinnerNumberModel)roundingSpinner.getModel()).getNumber().intValue();
            chart1.getPanel().setBorderRound(value);
            roundedButton.setSelected(value == 0);
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getHeader().setText("Round Borders");
        chart1.getHeader().getFont().setSize(19);
        chart1.getHeader().getFont().setBold(true);
        chart1.getHeader().setVisible(true);
        chart1.getPanel().getShadow().setColor(Color.BLACK);
        chart1.getPanel().setBevelOuter(BevelStyle.NONE);
        chart1.getPanel().getGradient().setDirection(GradientDirection.RADIAL);
        chart1.getPanel().getGradient().setEndColor(Color.TEAL);
        chart1.getPanel().getGradient().setVisible(true);
        chart1.getPanel().setMarginRight(10);
        chart1.getPanel().setMarginUnits(PanelMarginUnits.PIXELS);

        chart1.getPanel().getBorderPen().setColor(Color.NAVY);
        chart1.getPanel().getBorderPen().setWidth(5);
        chart1.getPanel().getBorderPen().setVisible(true);
        chart1.getPanel().setBorderRound(30);
    }

    protected void initComponents() {
        super.initComponents();

        penButton = new ButtonPen();
        penButton.setPen(chart1.getPanel().getBorderPen());

        roundingSpinner = new JSpinner(
                new SpinnerNumberModel(
                    chart1.getPanel().getBorderRound(),
                    0,
                    1000,
                    5
                    )
                );
        roundedButton = new JCheckBox("No rounding");
        roundedButton.setSelected(chart1.getPanel().getBorderRound()==0);
    }

    protected void initGUI() {
        super.initGUI();
        JLabel tmpLabel;
        JPanel tmpPane = getButtonPane();
        {
            tmpLabel = new JLabel("Rounding amount:");
            tmpLabel.setDisplayedMnemonic('R');
            tmpLabel.setLabelFor(roundingSpinner);
            tmpPane.add(tmpLabel);
            tmpPane.add(roundingSpinner);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(roundedButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(penButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JCheckBox roundedButton;
    private JSpinner roundingSpinner;
}
