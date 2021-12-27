/*
 * AxesDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.axes;

import com.steema.teechart.axis.Axes;
import com.steema.teechart.editors.DialogFactory;
import com.steema.teechart.styles.HorizontalAxis;
import com.steema.teechart.styles.Line;
import com.steema.teechart.styles.VerticalAxis;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import features.ChartSamplePanel;
import features.utils.EnumStrings;

/**
 *
 * @author tom
 */
public class AxesDemo extends ChartSamplePanel
        implements ActionListener, ItemListener {

    private Line series;

    /** Creates a new instance of AxesDemo */
    public AxesDemo() {
        super();
        editButton.addActionListener(this);
        vertAxisList.addActionListener(this);
        horizAxisList.addActionListener(this);
        showButton.addItemListener(this);
        gridLinesButton.addItemListener(this);
        depthButton.addItemListener(this);
        view3DButton.addItemListener(this);
        behindButton.addItemListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == vertAxisList) {
            switch (vertAxisList.getSelectedIndex()) {
                case 0: series.setVerticalAxis(VerticalAxis.LEFT) ; break;
                case 1: series.setVerticalAxis(VerticalAxis.RIGHT); break;
                default: series.setVerticalAxis(VerticalAxis.BOTH);
            }
        } else if (source == horizAxisList) {
            switch (horizAxisList.getSelectedIndex()) {
                case 0: series.setHorizontalAxis(HorizontalAxis.TOP) ; break;
                case 1: series.setHorizontalAxis(HorizontalAxis.BOTTOM); break;
                default: series.setHorizontalAxis(HorizontalAxis.BOTH);
            }
        } else if (source == editButton) {
            DialogFactory.showModal(chart1.getAxes());
        }
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == showButton) {
            chart1.getAxes().setVisible(isSelected);
        } else if (source == gridLinesButton) {
            for (int t=0; t < chart1.getAxes().getCount(); t++) {
                chart1.getAxes().getAxis(t).getGrid().setVisible(isSelected);
            }
            behindButton.setEnabled(isSelected);
        } else if (source == depthButton) {
            chart1.getAxes().getDepth().setVisible(isSelected);
            if (isSelected) {
                chart1.getPanel().setMarginRight(15);
            } else {
                chart1.getPanel().setMarginRight(3);
            }
        } else if (source == view3DButton) {
            chart1.getAspect().setView3D(isSelected);
        } else if (source == behindButton) {
            chart1.getAxes().setDrawBehind(isSelected);
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getLegend().setVisible(false);
    }

    protected void initComponents() {
        super.initComponents();
        series = new Line(chart1.getChart());
        series.fillSampleValues(10);

        Axes tmpAxes = chart1.getAxes();
        showButton = new JCheckBox("Show Axes");
        showButton.setSelected(tmpAxes.getVisible());
        gridLinesButton = new JCheckBox("Grid lines");
        gridLinesButton.setSelected(tmpAxes.getLeft().getGrid().getVisible());
        depthButton = new JCheckBox("Show depth axis");
        depthButton.setSelected(tmpAxes.getDepth().getVisible());
        view3DButton = new JCheckBox("3D");
        view3DButton.setSelected(chart1.getAspect().getView3D());
        behindButton = new JCheckBox("Behind");
        behindButton.setSelected(tmpAxes.getDrawBehind());
        horizAxisList = new JComboBox(EnumStrings.VERT_ALIGNMENT);
        horizAxisList.setPreferredSize(new Dimension(100, 20));
        horizAxisList.setSelectedIndex(1);
        vertAxisList = new JComboBox(EnumStrings.HORIZ_ALIGNMENT);
        vertAxisList.setPreferredSize(new Dimension(100, 20));
        editButton = new JButton("Edit...");
    }

    protected void initGUI() {
        super.initGUI();
        JLabel tmpLabel;
        JPanel hPane;
        JPanel tmpPane = getButtonPane();

        tmpPane.setLayout( new BoxLayout(tmpPane, BoxLayout.Y_AXIS) );
        hPane = new JPanel();
        {
            hPane.setLayout( new BoxLayout(hPane, BoxLayout.X_AXIS) );
            tmpLabel = new JLabel("Horiz:");
            tmpLabel.setDisplayedMnemonic('H');
            tmpLabel.setLabelFor(horizAxisList);
            hPane.add(tmpLabel);
            hPane.add(horizAxisList);
            hPane.add(Box.createHorizontalStrut(10));
            tmpLabel = new JLabel("Vert: ");
            tmpLabel.setDisplayedMnemonic('V');
            tmpLabel.setLabelFor(vertAxisList);
            hPane.add(tmpLabel);
            hPane.add(vertAxisList);
            hPane.add(Box.createHorizontalStrut(10));
            hPane.add(editButton);
            hPane.add(Box.createHorizontalGlue());
        }
        tmpPane.add(hPane);
        hPane = new JPanel();
        {
            hPane.setLayout( new BoxLayout(hPane, BoxLayout.X_AXIS) );
            hPane.add(showButton);
            hPane.add(Box.createHorizontalStrut(10));
            hPane.add(gridLinesButton);
            hPane.add(Box.createHorizontalStrut(10));
            hPane.add(view3DButton);
            hPane.add(Box.createHorizontalStrut(10));
            hPane.add(behindButton);
            hPane.add(Box.createHorizontalStrut(10));
            hPane.add(depthButton);
            hPane.add(Box.createHorizontalGlue());
        }
        tmpPane.add(hPane);
    }

    private JCheckBox showButton, gridLinesButton, depthButton, view3DButton, behindButton;
    private JComboBox horizAxisList, vertAxisList;
    private JButton editButton;
}
