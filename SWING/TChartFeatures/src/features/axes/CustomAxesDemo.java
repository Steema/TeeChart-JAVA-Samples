/*
 * CustomAxesDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.axes;

import com.steema.teechart.axis.Axis;
import com.steema.teechart.axis.CustomAxes;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.editors.DialogFactory;
import com.steema.teechart.styles.Line;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class CustomAxesDemo extends ChartSamplePanel
    implements ActionListener, ItemListener {

    /** Creates a new instance of CustomAxesDemo */
    public CustomAxesDemo() {
        super();
        editButton.addActionListener(this);
        showButton.addItemListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == editButton) {
            if (chart1.getAxes().getCustom().size()==0) {
                DialogFactory.showModal(chart1.getAxes().getLeft());
            } else {
                DialogFactory.showModal(chart1.getAxes().getCustom().getAxis(0));
            }
        }
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == showButton) {
            for (int t=0; t < chart1.getAxes().getCustom().size(); t++) {
                chart1.getAxes().getCustom().getAxis(t).setVisible(isSelected);
            }
        }
    }

    protected void initAxes() {
       Axis tmpAxis;
       tmpAxis = chart1.getAxes().getCustom().getNew();
       tmpAxis.setHorizontal(false);
       tmpAxis.setOtherSide(false);
       tmpAxis.getLabels().setColor(Color.GREEN);
       tmpAxis.setStartPosition(50.0);

       tmpAxis = chart1.getAxes().getCustom().getNew();
       tmpAxis.setHorizontal(true);
       tmpAxis.setOtherSide(false);
       tmpAxis.getLabels().setColor(Color.RED);
       tmpAxis.setRelativePosition(50.0);

       tmpAxis = chart1.getAxes().getCustom().getNew();
       tmpAxis.getGrid().setVisible(false);
       tmpAxis.setHorizontal(true);
       tmpAxis.setOtherSide(false);
       tmpAxis.getLabels().setColor(Color.YELLOW);
       tmpAxis.setStartPosition(55.0);

       tmpAxis = chart1.getAxes().getCustom().getNew();
       tmpAxis.getGrid().setVisible(false);
       tmpAxis.setHorizontal(false);
       tmpAxis.setOtherSide(true);
       tmpAxis.getLabels().setColor(Color.YELLOW);
       tmpAxis.setStartPosition(50.0);
    }

    protected void initChart() {
        super.initChart();
        chart1.getAspect().setView3D(false);
        Axis tmpAxis;
        tmpAxis = chart1.getAxes().getBottom();
        tmpAxis.getGrid().setVisible(false);
        tmpAxis.getLabels().getFont().setColor(Color.GREEN);
        tmpAxis.setEndPosition(50.0);
        tmpAxis = chart1.getAxes().getLeft();
        tmpAxis.getLabels().getFont().setColor(Color.RED);
        tmpAxis.setEndPosition(50.0);
    }

    protected void initComponents() {
        super.initComponents();
        initAxes();

        CustomAxes tmpAxes = chart1.getAxes().getCustom();
        Line tmpSeries;
        for (int t=0; t<3; t++) {
            tmpSeries = new Line(chart1.getChart());
            tmpSeries.fillSampleValues(20);
            switch (t) {
                case 0: tmpSeries.setCustomHorizAxis(1); break;
                case 1: tmpSeries.setCustomVertAxis(0); break;
                case 2: {
                    tmpSeries.setCustomHorizAxis(2);
                    tmpSeries.setCustomVertAxis(3);
                    break;
                }
            }
        }

        editButton = new JButton("Edit...");
        showButton = new JCheckBox("Show custom axes");
        showButton.setSelected(true);
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(showButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(editButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }
    private JButton editButton;
    private JCheckBox showButton;
}
