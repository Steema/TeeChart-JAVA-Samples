/*
 * LineDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.line;
import com.steema.teechart.editors.ChartEditor;
import com.steema.teechart.styles.CustomStack;
import com.steema.teechart.styles.Line;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Random;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class LineDemo extends ChartSamplePanel
    implements ActionListener, ItemListener {

    private JButton editButton;
    private JCheckBox[] optionButtons;

    /** Creates a new instance of LineDemo */
    public LineDemo() {
        super();
        editButton.addActionListener(this);
        for (int i=0; i < optionButtons.length; i++) {
            optionButtons[i].addItemListener(this);
        }
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == editButton) {
            ChartEditor.editChart(chart1.getChart());
        }
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);

        if (source == optionButtons[0]) {
            for (int i=0; i < chart1.getSeriesCount(); i++) {
                ((Line)chart1.getSeries(i)).setStairs(isSelected);
            }
        } else if (source == optionButtons[1]) {
            for (int i=0; i < chart1.getSeriesCount(); i++) {
                chart1.getSeries(i).getMarks().setVisible(isSelected);
            }
        } else if (source == optionButtons[2]) {
            for (int i=0; i < chart1.getSeriesCount(); i++) {
                ((Line)chart1.getSeries(i)).getPointer().setVisible(isSelected);
            }
        } else if (source == optionButtons[3]) {
            ((Line)chart1.getSeries(0)).setStacked(isSelected ? CustomStack.STACK : CustomStack.NONE);
        } else if (source == optionButtons[4]) {
            chart1.getAspect().setView3D(isSelected);
        }
    }

    protected void initComponents() {
        super.initComponents();

        Random generator = new Random();
        Line lineSeries;
        for (int i=0; i<2; i++) {
            lineSeries = new com.steema.teechart.styles.Line(chart1.getChart());
            lineSeries.setStairs(false);
            lineSeries.getMarks().setVisible(false);
            lineSeries.getPointer().setVisible(false);
            for (int t=0; t < 20; t++) {
                lineSeries.add(generator.nextInt(100));
            }
        }
        chart1.getSeries(0).setNull(3); // <-- null point example
        chart1.getSeries(1).setNull(10); // <-- null point example

        editButton = new JButton("Edit...");
        optionButtons  = new JCheckBox[5];

        optionButtons[0] = new JCheckBox("Stairs");
        optionButtons[1] = new JCheckBox("Marks");
        optionButtons[2] = new JCheckBox("Pointers");
        optionButtons[3] = new JCheckBox("Stacked");
        optionButtons[4] = new JCheckBox("3D");
        optionButtons[4].setSelected(true);
    }

    protected void initGUI() {
        super.initGUI();
        chart1.getHeader().setVisible(true);
        chart1.setText("Lines with some null points");

        JPanel tmpPane = getButtonPane();
        {
            for (int i=0; i < optionButtons.length; i++) {
                tmpPane.add(optionButtons[i]);
                tmpPane.add(Box.createHorizontalStrut(10));
            }
            tmpPane.add(editButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }
}
