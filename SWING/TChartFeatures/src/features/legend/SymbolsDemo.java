/*
 * SymbolsDemo.java
 *
 * <p>Copyright: Copyright (c) 2004-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.legend;

import com.steema.teechart.legend.LegendSymbolPosition;
import com.steema.teechart.legend.LegendSymbolSize;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.Contour;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
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
public class SymbolsDemo extends ChartSamplePanel
    implements ActionListener, ChangeListener, ItemListener {

    /** Creates a new instance of SymbolsDemo */
    public SymbolsDemo() {
        super();
        continuousButton.addItemListener(this);
        positionList.addActionListener(this);
        widthSpinner.addChangeListener(this);
    }

    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if (source == positionList) {
            switch (positionList.getSelectedIndex()) {
                case 0: chart1.getLegend().getSymbol().setPosition(LegendSymbolPosition.LEFT) ; break;
                case 1: chart1.getLegend().getSymbol().setPosition(LegendSymbolPosition.RIGHT); break;
                default: chart1.getLegend().getSymbol().setPosition(LegendSymbolPosition.LEFT);
            }
        }
    }


    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == continuousButton) {
            chart1.getLegend().getSymbol().setContinuous(isSelected);
        }
    }

    public void stateChanged(ChangeEvent e) {
        Object source = e.getSource();
        if (source == widthSpinner) {
            int width = ((SpinnerNumberModel)((JSpinner)source).getModel()).getNumber().intValue();
            chart1.getLegend().getSymbol().setWidth(width);
        }
    }

    protected void initChart() {
        super.initChart();

        chart1.getWalls().getBack().setColor(Color.AQUA);
        chart1.getWalls().getBack().setSize(10);
        chart1.getWalls().getBack().setTransparent(false);
        chart1.getWalls().getBottom().setSize(10);
        chart1.getWalls().getLeft().setSize(10);

        chart1.getAxes().getBottom().setInverted(true);
        chart1.getAxes().getDepth().setInverted(true);
        chart1.getAxes().getDepth().setVisible(true);
        chart1.getAxes().getLeft().setInverted(true);

        chart1.getLegend().getSymbol().setContinuous(true);
        chart1.getLegend().getSymbol().setWidthUnits(LegendSymbolSize.PIXELS);

        chart1.getAspect().setElevation(300);
        chart1.getAspect().setOrthogonal(false);
        chart1.getAspect().setPerspective(74);
        chart1.getAspect().setRotation(360);
        chart1.getAspect().setZoom(65);
        chart1.getAspect().setChart3DPercent(70);

    }

    protected void initComponents() {
        super.initComponents();

        Contour series = new Contour(chart1.getChart());
        series.setValueFormat("0.000");
        series.fillSampleValues(10);

        continuousButton = new JCheckBox("Continuous");
        continuousButton.setSelected(chart1.getLegend().getSymbol().getContinuous());

        widthSpinner = new JSpinner(
            new SpinnerNumberModel(
                chart1.getLegend().getSymbol().getWidth(),
                0,
                100,
                1
            )
        );

        positionList = new JComboBox(new String[]{"Left", "Right"});
        positionList.setPreferredSize(new Dimension(100, 20));
        positionList.setSelectedIndex(chart1.getLegend().getSymbol().getPosition().getValue());
    }

    protected void initGUI() {
        super.initGUI();
        JLabel tmpLabel;
        JPanel tmpPane = getButtonPane();
        {
            tmpLabel = new JLabel("Symbol width:");
            tmpLabel.setDisplayedMnemonic('w');
            tmpLabel.setLabelFor(widthSpinner);
            tmpPane.add(tmpLabel);
            tmpPane.add(widthSpinner);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpLabel = new JLabel("Position:");
            tmpLabel.setDisplayedMnemonic('P');
            tmpLabel.setLabelFor(positionList);
            tmpPane.add(tmpLabel);
            tmpPane.add(positionList);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(continuousButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JCheckBox continuousButton;
    private JComboBox positionList;
    private JSpinner widthSpinner;
}
