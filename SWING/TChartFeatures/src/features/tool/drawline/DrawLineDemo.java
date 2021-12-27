/*
 * DrawLineDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.tool.drawline;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.editors.ButtonPen;
import com.steema.teechart.events.ChangeEvent;
import com.steema.teechart.styles.FastLine;
import com.steema.teechart.tools.DrawLine;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
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
public class DrawLineDemo extends ChartSamplePanel
        implements ActionListener, ItemListener {

    private DrawLine tool;

    /**
     * Creates a new instance of DrawLineDemo
     */
    public DrawLineDemo() {
        super();
        activeButton.addItemListener(this);
        enableDrawButton.addItemListener(this);
        enableSelectButton.addItemListener(this);
        deleteButton.addActionListener(this);
        penButton.addActionListener(this);
        mouseButtonList.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == mouseButtonList) {
            int tmpValue = MouseEvent.BUTTON1;
            switch (mouseButtonList.getSelectedIndex()) {
                case 0: tmpValue = MouseEvent.BUTTON1; break;
                case 1: tmpValue = MouseEvent.BUTTON3; break;
                case 2: tmpValue = MouseEvent.BUTTON2; break;
            }
            tool.setButton(tmpValue);
        } else if (source == deleteButton) {
            tool.deleteSelected();
            deleteButton.setEnabled(false);
            countLinesLabel.setText(Integer.toString(tool.getLines().size()));
        } else if (source == penButton) {
            chart1.repaint();
        }
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);

        if (source == activeButton) {
            tool.setActive(isSelected);
        } else if (source == enableDrawButton) {
            tool.setEnableDraw(isSelected);
        } else if (source == enableSelectButton) {
            tool.setEnableSelect(isSelected);
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getAspect().setView3D(false);
    }

    protected void initComponents() {
        super.initComponents();

        FastLine tmpLine = new com.steema.teechart.styles.FastLine(chart1.getChart());
        tmpLine.fillSampleValues(20);

        tool = new com.steema.teechart.tools.DrawLine(chart1.getChart());
        tool.getPen().setColor(Color.BLUE);
        tool.addDrawLineListener( tool.new DrawLineAdapter() {
             public void lineNew(ChangeEvent e) {
                 countLinesLabel.setText(Integer.toString(tool.getLines().size()));
             };
             public void lineSelected(ChangeEvent e) {
                 deleteButton.setEnabled(true);
             };
        });

        penButton = new ButtonPen( tool.getPen() );

        countLinesLabel = new JLabel("0");

        deleteButton = new JButton("Delete");
        deleteButton.setEnabled(false);
        activeButton = new JCheckBox("Active");
        activeButton.setSelected(true);
        enableDrawButton = new JCheckBox("Enable Draw");
        enableDrawButton.setSelected(true);
        enableSelectButton = new JCheckBox("Enable Selection");
        enableSelectButton.setSelected(true);
        mouseButtonList = new JComboBox(EnumStrings.MOUSE_BUTTONS);
        mouseButtonList.setSelectedIndex(0);
    }

    protected void initGUI() {
        super.initGUI();
        JLabel tmpLabel;
        JPanel tmpPane = getButtonPane();
        {
            JPanel vPane1 = new JPanel();
            {
                vPane1.setLayout( new BoxLayout(vPane1, BoxLayout.Y_AXIS) );
                vPane1.add(activeButton);
                vPane1.add(enableDrawButton);
                vPane1.add(enableSelectButton);
            }
            tmpPane.add(vPane1);
            tmpPane.add(Box.createHorizontalStrut(20));
            tmpPane.add(new JLabel("Lines:"));
            tmpPane.add(countLinesLabel);
            tmpPane.add(Box.createHorizontalStrut(20));
            JPanel vPane2 = new JPanel();
            {
                vPane2.setLayout( new BoxLayout(vPane2, BoxLayout.Y_AXIS) );
                vPane2.add(penButton);
                vPane2.add(deleteButton);
            }
            tmpPane.add(vPane2);
            tmpPane.add(Box.createHorizontalStrut(20));
            tmpLabel = new JLabel("Mouse Button:");
            tmpLabel.setDisplayedMnemonic('M');
            tmpLabel.setLabelFor(mouseButtonList);
            tmpPane.add(tmpLabel);
            tmpPane.add(mouseButtonList);
            mouseButtonList.setMaximumSize(new Dimension(0, MAX_COMBOBOX_HEIGHT));
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private ButtonPen penButton;
    private JButton deleteButton;
    private JCheckBox activeButton, enableDrawButton, enableSelectButton;
    private JLabel countLinesLabel;
    private JComboBox mouseButtonList;

    private static final int MAX_COMBOBOX_HEIGHT = 24;
}
